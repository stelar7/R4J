package no.stelar7.api.r4j.tests.lol.div;

import no.stelar7.api.r4j.basic.cache.impl.FileSystemCacheProvider;
import no.stelar7.api.r4j.basic.calling.DataCall;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.constants.types.ApiKeyType;
import no.stelar7.api.r4j.basic.constants.types.lol.*;
import no.stelar7.api.r4j.basic.utils.Rectangle;
import no.stelar7.api.r4j.basic.utils.*;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.lol.builders.matchv5.match.MatchListBuilder;
import no.stelar7.api.r4j.pojo.lol.match.v5.*;
import no.stelar7.api.r4j.pojo.lol.staticdata.champion.StaticChampion;
import no.stelar7.api.r4j.pojo.lol.staticdata.item.Item;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import no.stelar7.api.r4j.pojo.shared.RiotAccount;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.jupiter.api.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class FrameToImageTest
{

  private final ArrayList<Pair<Integer, Integer>>     turrets    = new ArrayList<>();
  private final Map<Pair<Integer, Integer>, TeamType> turretTeam = new HashMap<>();

  private final ArrayList<Pair<Integer, Integer>>     inhib            = new ArrayList<>();
  private final Map<Pair<Integer, Integer>, TeamType> inhibTeam        = new HashMap<>();
  private final Map<Pair<Integer, Integer>, Long>     inhibDestroyTime = new HashMap<>();

  private final ArrayList<Pair<Integer, Integer>> killList = new ArrayList<>();

  private Map<TeamType, BufferedImage> turretIcon;
  private BufferedImage                swordIcon;
  private Map<TeamType, BufferedImage> inhibIcon;

  R4J api = new R4J(SecretFile.CREDS);

  @Test
  public void testStuff()
  {
    DataCall.setCacheProvider(new FileSystemCacheProvider());

    RiotAccount      account = api.getAccountAPI().getAccountByTag(LeagueShard.EUW1.toRegionShard(), "stelar7", "STL7", ApiKeyType.LOL);
    Summoner         sum     = api.getLoLAPI().getSummonerAPI().getSummonerByPUUID(LeagueShard.EUW1, account.getPUUID());
    LazyList<String> refs    = new MatchListBuilder().withPlatform(sum.getPlatform()).withPuuid(sum.getPUUID()).getLazy();
    LOLMatch         full    = LOLMatch.get(sum.getPlatform(), refs.get(0));


    TowerLocationType.getTowersMap(MapType.SUMMONERS_RIFT).forEach((k, v) -> v.forEach((k2, v2) -> v2.forEach((t, p) -> turretTeam.put(p, t))));
    turretTeam.forEach((k, v) -> turrets.add(k));


    InhibitorLocationType.getInhibMap(MapType.SUMMONERS_RIFT).forEach((k, v) -> v.forEach((t, p) -> inhibTeam.put(p, t)));
    inhibTeam.forEach((k, v) -> inhib.add(k));

    generateMinimap(full);
  }


  private void generateMinimap(LOLMatch match)
  {
    // Load map data
    try
    {

      BufferedImage image     = ImageIO.read(new URL(api.getImageAPI().getMap("map" + match.getMap().getId(), null)));
      Rectangle     mapBounds = match.getMap().getBounds();

      // load icon data
      int                          championSquareOffset  = (int) (120d / 4d);
      int                          championSquarePadding = (int) (120d / 12d);
      Map<Integer, Item>           items                 = api.getDDragonAPI().getItems();
      Map<Integer, StaticChampion> champs                = api.getDDragonAPI().getChampions();
      Map<Integer, BufferedImage>  championImages        = new HashMap<>();
      Map<Integer, BufferedImage>  itemImages            = new HashMap<>();

      match.getParticipants().forEach(p -> {
        try
        {
          BufferedImage before      = ImageIO.read(new URL(api.getImageAPI().getSquare(champs.get(p.getChampionId()), null)));
          double        scaleFactor = 1d / 4d;
          BufferedImage after       = scaleImage(before, scaleFactor);

          championImages.put(p.getParticipantId(), after);
        } catch (IOException e)
        {
          e.printStackTrace();
        }
      });

      items.values().parallelStream().forEach(i -> {
        try
        {
          BufferedImage before      = ImageIO.read(new URL(api.getImageAPI().getItem(i, null)));
          double        scaleFactor = 1d / 2d;
          BufferedImage after       = scaleImage(before, scaleFactor);

          itemImages.put(i.getId(), after);
        } catch (IOException e)
        {
          e.printStackTrace();
        }
      });

      if (turretIcon == null)
      {
        try
        {
          turretIcon = new EnumMap<>(TeamType.class);
          BufferedImage temp = ImageIO.read(new URL("https://raw.communitydragon.org/latest/game/assets/ux/minimap/icons/icon_ui_tower_minimap_order_bounty_ship.png"));
          turretIcon.put(TeamType.BLUE, scaleImage(temp, .4));

          temp = ImageIO.read(new URL("https://raw.communitydragon.org/latest/game/assets/ux/minimap/icons/icon_ui_tower_minimap_chaos_bounty_ship.png"));
          turretIcon.put(TeamType.RED, scaleImage(temp, .4));
        } catch (IOException e)
        {
          e.printStackTrace();
        }
      }

      if (inhibIcon == null)
      {
        try
        {
          inhibIcon = new EnumMap<>(TeamType.class);
          BufferedImage temp = ImageIO.read(new URL("https://raw.communitydragon.org/latest/game/assets/ux/minimap/icons/icon_ui_inhibitor_minimap_v2.png"));
          inhibIcon.put(TeamType.BLUE, scaleImage(temp, .25));

          temp = ImageIO.read(new URL("https://raw.communitydragon.org/latest/game/assets/ux/minimap/icons/icon_ui_inhibitor_minimap_v2.png"));
          inhibIcon.put(TeamType.RED, scaleImage(temp, .25));
        } catch (IOException e)
        {
          e.printStackTrace();
        }
      }

      if (swordIcon == null)
      {
        try
        {
          swordIcon = ImageIO.read(new URL("https://web.archive.org/web/20171227162735if_/https://matchhistory.na.leagueoflegends.com/assets/1.0.32/css/resources/images/scoreboardicon_score.png"));
          swordIcon = scaleImage(swordIcon, 1.5);
        } catch (IOException e)
        {
          e.printStackTrace();
        }
      }

      Map<Integer, List<Pair<Item, Integer>>> inventory = new HashMap<>();
      Map<Integer, Triplet<Integer>>          kda       = new HashMap<>();

      IntStream.rangeClosed(1, 10).forEach(i -> kda.put(i, new Triplet<>(0, 0, 0)));

      match.getTimeline().getFrames().forEach(frame -> {
        try
        {
          // Generate file output data
          String path       = match.getPlatform().getValue() + File.separator + match.getGameId();
          File   outputFile = new File(path, frame.getTimestamp() + ".png");
          outputFile.getParentFile().mkdirs();

          // Make sure map is large enough for all the data
          int           imgW   = image.getWidth() + (championSquareOffset + championSquarePadding) * 4;
          int           imgH   = (image.getHeight() + (championSquareOffset + championSquarePadding) * 11);
          BufferedImage newMap = new BufferedImage(imgW, imgH, BufferedImage.TYPE_INT_ARGB);
          Graphics2D    g      = (Graphics2D) newMap.getGraphics();

          // set background
          g.setColor(Color.black);
          g.fillRect(0, 0, newMap.getWidth(), newMap.getHeight());

          // set separator line
          g.setColor(Color.white);
          g.fillRect(0, image.getHeight() + (int) (championSquareOffset / 1.5f) + championSquarePadding / 2, newMap.getWidth(), championSquareOffset / 4);
          g.fillRect(image.getWidth() + (int) (championSquareOffset / 1.5f), 0, championSquareOffset / 4, newMap.getHeight());

          // set minimap
          g.drawImage(image, 0, 0, null);

          int[] killCount = {0};

          frame.getEvents().forEach(me -> {

            Predicate<Pair<Item, Integer>> itemIdFilter     = p -> p.getKey().getId() == me.getItemId();
            Predicate<Pair<Item, Integer>> itemAfterFilter  = p -> p.getKey().getId() == me.getAfterId();
            Predicate<Pair<Item, Integer>> itemBeforeFilter = p -> p.getKey().getId() == me.getBeforeId();


            if (!inhibDestroyTime.isEmpty())
            {
              Map<Pair<Integer, Integer>, Long> clone = new HashMap<>(inhibDestroyTime);

              clone.forEach((k, v) -> {
                if (v + (60000 * 5) <= me.getTimestamp())
                {
                  inhib.add(k);
                  inhibDestroyTime.remove(k);
                }
              });
            }

            switch (me.getType())
            {
            case ITEM_PURCHASED:
            {
              handlePurchaseEvent(items, inventory, me, itemIdFilter);
              break;
            }
            case ITEM_DESTROYED:
            case ITEM_SOLD:
            {
              handleSoldEvent(inventory, me, itemIdFilter);
              break;
            }
            case ITEM_UNDO:
            {
              handleUndoEvent(items, inventory, me, itemBeforeFilter, itemAfterFilter);
              break;
            }

            case CHAMPION_KILL:
            {
              handleKillEvent(image, mapBounds, championSquareOffset, championSquarePadding, championImages, g, killCount, me, kda);
              break;
            }

            // ignored events
            case SKILL_LEVEL_UP:
            case WARD_KILL:
            case WARD_PLACED:
            case ELITE_MONSTER_KILL:
            {
              break;
            }
            case BUILDING_KILL:
            {
              handleBuildingEvent(me);
              break;
            }
            default:
            {
              System.out.println(me);
              break;
            }
            }
          });

          for (Pair<Integer, Integer> turret : turrets)
          {
            BufferedImage turretImage = turretIcon.get(turretTeam.get(turret));

            int xPos = scale(turret.getKey(), mapBounds.getX(), mapBounds.getW(), 0, image.getWidth());
            int yPos = image.getHeight() - scale(turret.getValue(), mapBounds.getY(), mapBounds.getH(), 0, image.getHeight());

            int xOffset = (int) (-turretImage.getWidth() / 1.25f);
            int yOffset = -(turretImage.getHeight() / 2);
            xPos += xOffset;
            yPos += yOffset;

            g.drawImage(turretImage, xPos, yPos, null);
          }

          for (Pair<Integer, Integer> p : inhib)
          {
            System.out.println(inhib);
            BufferedImage inhibImage = inhibIcon.get(inhibTeam.get(p));

            if (inhibImage == null) {
              System.out.println(inhibTeam.get(p));
              System.out.println(inhibIcon);
              continue;
            }
            
            int xPos = scale(p.getKey(), mapBounds.getX(), mapBounds.getW(), 0, image.getWidth());
            int yPos = image.getHeight() - scale(p.getValue(), mapBounds.getY(), mapBounds.getH(), 0, image.getHeight());

            int xOffset = (int) (-inhibImage.getWidth() / 1.1f);
            int yOffset = -(inhibImage.getHeight() / 3);
            xPos += xOffset;
            yPos += yOffset;

            g.drawImage(inhibImage, xPos, yPos, null);
          }

          frame.getParticipantFrames().values().forEach(mpf -> {
            drawPosition(image, mapBounds, g, mpf, championImages);

            drawInventory(image, championSquareOffset, championSquarePadding, itemImages, inventory, g, mpf, championImages);

            drawKDA(kda, image, g, championSquareOffset, championSquarePadding);
          });

          for (int i = 0; i < killList.size(); i++)
          {
            Pair<Integer, Integer> k = killList.get(i);

            int xPos = k.getKey();
            int yPos = k.getValue();

            g.setColor(Color.red);
            g.fillOval(xPos - 8, yPos - 8, 16, 16);

            g.setColor(Color.black);
            g.drawString(String.valueOf(i + 1), xPos - 3, yPos + 5);

            g.drawOval(xPos - 8, yPos - 8, 16, 16);
          }

          killList.clear();

          ImageIO.write(newMap, "png", outputFile);
        } catch (IOException e)

        {
          e.printStackTrace();
        }
      });
    } catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  private void drawKDA(Map<Integer, Triplet<Integer>> kda, BufferedImage image, Graphics2D g, int offset, int padding)
  {
    Font f = g.getFont();
    g.setFont(new Font(f.getName(), f.getStyle(), 25));
    int pof = padding + offset;

    kda.forEach((k, v) -> {
      int xloc = image.getWidth() + pof;
      int yloc = image.getHeight() + (int) (pof * (k + 0.5f));
      g.drawString(v.toString(), xloc, yloc);
    });

    g.setFont(f);
  }


  private void handleBuildingEvent(TimelineFrameEvent me)
  {
    Pair<Integer, Integer> location = new Pair<>(me.getPosition().getX(), me.getPosition().getY());


    if (me.getBuildingType() == BuildingType.TOWER_BUILDING)
    {
      turrets.remove(location);
    } else
    {
      inhib.remove(location);
      inhibDestroyTime.put(location, me.getTimestamp());
    }
  }

  private BufferedImage scaleImage(BufferedImage before, double scaleFactor)
  {
    int scaledWidth  = (int) Math.ceil(before.getWidth() * scaleFactor);
    int scaledHeight = (int) Math.ceil(before.getHeight() * scaleFactor);

    BufferedImage   after = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);
    AffineTransform at    = new AffineTransform();
    at.scale(scaleFactor, scaleFactor);

    AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
    after = op.filter(before, after);
    return after;
  }

  private void drawPosition(BufferedImage image, Rectangle mapBounds, Graphics2D g, TimelineParticipantFrame mpf, Map<Integer, BufferedImage> championImages)
  {
    BufferedImage    square = championImages.get(mpf.getParticipantId());
    TimelinePosition pos    = mpf.getPosition();

    if (pos != null)
    {
      int xPos = scale(pos.getX(), mapBounds.getX(), mapBounds.getW(), 0, image.getWidth());
      int yPos = image.getHeight() - scale(pos.getY(), mapBounds.getY(), mapBounds.getH(), 0, image.getHeight());

      int xOffset = (mpf.getParticipantId() < 5) ? -(square.getWidth() / 2) : -square.getWidth();
      int yOffset = (mpf.getParticipantId() < 5) ? -(square.getHeight() / 2) : 0;
      xPos += xOffset;
      yPos += yOffset;

      g.drawImage(square, xPos, yPos, null);
    }
  }

  private void drawInventory(BufferedImage image,
      int offset,
      int padding,
      Map<Integer, BufferedImage> iImg,
      Map<Integer, List<Pair<Item, Integer>>> inventory,
      Graphics2D g,
      TimelineParticipantFrame mpf,
      Map<Integer, BufferedImage> cImg)
  {
    BufferedImage square = cImg.get(mpf.getParticipantId());

    int inventoryY = image.getHeight() + ((padding + offset) * mpf.getParticipantId());
    g.drawImage(square, offset / 4, inventoryY, null);

    List<Pair<Item, Integer>> participantInventoy = inventory.getOrDefault(mpf.getParticipantId(), new ArrayList<>());
    participantInventoy.sort(Comparator.comparing((Pair<Item, Integer> i) -> i.getKey().getGold().getTotal()).reversed());

    for (int i = 0; i < participantInventoy.size(); i++)
    {
      BufferedImage itemImage  = iImg.get(participantInventoy.get(i).getKey().getId());
      int           inventoryX = (offset + padding) * (i + 1);

      g.drawImage(itemImage, inventoryX + offset / 4, inventoryY, null);
    }
  }

  private void handleKillEvent(BufferedImage image,
      Rectangle mapBounds,
      int offset,
      int padding,
      Map<Integer, BufferedImage> cImg,
      Graphics2D g,
      int[] kills,
      TimelineFrameEvent me,
      Map<Integer, Triplet<Integer>> kda)
  {
    Triplet<Integer> killer = kda.getOrDefault(me.getKillerId(), new Triplet<>(0, 0, 0));
    kda.put(me.getKillerId(), new Triplet<>(killer.a + 1, killer.b, killer.c));

    Triplet<Integer> victim = kda.getOrDefault(me.getVictimId(), new Triplet<>(0, 0, 0));
    kda.put(me.getVictimId(), new Triplet<>(victim.a, victim.b + 1, victim.c));

    if (me.getAssistingParticipantIds() != null) {

      me.getAssistingParticipantIds().forEach(id -> {
        Triplet<Integer> assist = kda.getOrDefault(id, new Triplet<>(0, 0, 0));
        kda.put(id, new Triplet<>(assist.a, assist.b, assist.c + 1));
      });
    }

    int xPos = scale(me.getPosition().getX(), mapBounds.getX(), mapBounds.getW(), 0, image.getWidth());
    int yPos = image.getHeight() - scale(me.getPosition().getY(), mapBounds.getY(), mapBounds.getH(), 0, image.getHeight());

    killList.add(new Pair<>(xPos, yPos));

    BufferedImage killerSquare = cImg.get(me.getKillerId());
    BufferedImage deathSquare  = cImg.get(me.getVictimId());

    int offsetX = padding + offset;
    int offsetY = kills[0] * (offset + padding) + (offset / 4);

    g.drawImage(killerSquare, image.getWidth() + offsetX, offsetY, null);
    g.drawImage(swordIcon, image.getWidth() + (offsetX * 2), offsetY, null);
    g.drawImage(deathSquare, image.getWidth() + (offsetX * 3), offsetY, null);
    kills[0]++;
  }

  private void handleUndoEvent(Map<Integer, Item> items,
      Map<Integer, List<Pair<Item, Integer>>> inventory,
      TimelineFrameEvent me,
      Predicate<Pair<Item, Integer>> itemBeforeFilter,
      Predicate<Pair<Item, Integer>> afterItemFilter)
  {
    List<Pair<Item, Integer>> inv = inventory.getOrDefault(me.getParticipantId(), new ArrayList<>());

    Optional<Pair<Item, Integer>> optional = inv.stream().filter(itemBeforeFilter).findFirst();

    if (optional.isPresent())
    {
      Pair<Item, Integer> op = optional.get();
      Pair<Item, Integer> np = new Pair<>(op.getKey(), op.getValue() - 1);
      inv.remove(op);

      if (np.getValue() > 0)
      {
        inv.add(np);
      }

      Item parent = items.get(op.getKey().getId());
      if (parent.getFrom() != null)
      {
        for (String s : items.get(op.getKey().getId()).getFrom())
        {
          int iid = Integer.parseInt(s);

          Optional<Pair<Item, Integer>> innerO = inv.stream().filter(p -> p.getKey().getId() == iid).findFirst();

          if (innerO.isPresent())
          {
            Pair<Item, Integer> iop = innerO.get();
            Pair<Item, Integer> inp = new Pair<>(iop.getKey(), iop.getValue() + 1);
            inv.remove(iop);
            inv.add(inp);
          } else
          {
            Pair<Item, Integer> inp = new Pair<>(items.get(iid), 1);
            inv.add(inp);
          }
        }
      }
    } else
    {

      Optional<Pair<Item, Integer>> iopiotnal = inv.stream().filter(afterItemFilter).findFirst();

      if (iopiotnal.isPresent())
      {
        Pair<Item, Integer> op = iopiotnal.get();
        Pair<Item, Integer> np = new Pair<>(op.getKey(), op.getValue() + 1);
        inv.remove(op);
        inv.add(np);
      } else
      {
        Pair<Item, Integer> np = new Pair<>(items.get(me.getAfterId()), 1);
        inv.add(np);
      }
    }

    inventory.put(me.getParticipantId(), inv);
  }

  private void handleSoldEvent(Map<Integer, List<Pair<Item, Integer>>> inventory, TimelineFrameEvent me, Predicate<Pair<Item, Integer>> itemIdFilter)
  {
    if (me.getItemId() == 1501 || me.getItemId() == 3513)
    {
      // 3513 = rift herald is not shown in the timeline for some reason??
      // 1501 = turret armour is not in the item api??
      return;
    }

    List<Pair<Item, Integer>> inv = inventory.getOrDefault(me.getParticipantId(), new ArrayList<>());

    Optional<Pair<Item, Integer>> optional = inv.stream().filter(itemIdFilter).findFirst();

    if (optional.isPresent())
    {
      Pair<Item, Integer> op = optional.get();
      Pair<Item, Integer> np = new Pair<>(op.getKey(), op.getValue() - 1);
      inv.remove(op);

      if (np.getValue() > 0)
      {
        inv.add(np);
      }
    } else
    {
      System.out.println(me);
      System.out.println("^^^^TRIED TO REMOVE MISSING ITEM???");
      System.out.println();
    }

    inventory.put(me.getParticipantId(), inv);
  }

  private void handlePurchaseEvent(Map<Integer, Item> items,
      Map<Integer, List<Pair<Item, Integer>>> inventory,
      TimelineFrameEvent me,
      Predicate<Pair<Item, Integer>> itemIdFilter)
  {

    List<Pair<Item, Integer>> inv = inventory.getOrDefault(me.getParticipantId(), new ArrayList<>());

    Optional<Pair<Item, Integer>> optional = inv.stream().filter(itemIdFilter).findFirst();

    if (optional.isPresent())
    {
      Pair<Item, Integer> op = optional.get();
      Pair<Item, Integer> np = new Pair<>(op.getKey(), op.getValue() + 1);
      inv.remove(op);
      inv.add(np);
    } else
    {
      Pair<Item, Integer> np = new Pair<>(items.get(me.getItemId()), 1);
      inv.add(np);
    }

    inventory.put(me.getParticipantId(), inv);
  }

  private int scale(int x, int minTo, int maxTo, int minFrom, int maxFrom)
  {
    return ((maxFrom - minFrom) * (x - minTo)) / ((maxTo - minTo) + minFrom);
  }

  private static class Triplet<T>
  {
    private final T a;
    private final T b;
    private final T c;

    public Triplet(T a, T b, T c)
    {
      this.a = a;
      this.b = b;
      this.c = c;
    }

    public T getA()
    {
      return a;
    }

    public T getB()
    {
      return b;
    }

    public T getC()
    {
      return c;
    }

    @Override
    public String toString()
    {
      return a + " / " + b + " / " + c;
    }
  }
}
