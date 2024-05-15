package no.stelar7.api.r4j.basic.constants.types.lol;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.*;
import java.util.stream.Stream;

public enum SummonerSpellType implements CodedEnum
{
    /**
     * Used for bots that didn't know how to use summoners
     */
    UNKNOWN("UNKNOWN", 0),
    /**
     * Removes all disables and summoner spell debuffs affecting your champion and lowers the duration of incoming disables by 65% for 3 seconds.
     */
    CLEANSE("SummonerBoost", 1),
    /**
     * After channeling for 3.5 seconds, teleports your champion to target allied structure, minion, or ward.
     */
    TELEPORT("SummonerTeleport", 12),
    /**
     * Quickly travel to the Poro King's side.
     */
    TO_THE_KING("SummonerPoroRecall", 30),
    /**
     * Ignites target enemy champion, dealing 70-410 true damage (depending on champion level) over 5 seconds, grants you vision of the target, and reduces healing effects on them for the duration.
     */
    IGNITE("SummonerDot", 14),
    /**
     * Your champion can move through units and has 27% increased Movement Speed for 10 seconds
     */
    GHOST("SummonerHaste", 6),
    /**
     * Throw a snowball in a straight line at your enemies. If it hits an enemy, they become marked and your champion can quickly travel to the marked target as a follow up.
     */
    MARK("SummonerSnowball", 32),
    /**
     * Restores 90-345 Health (depending on champion level) and grants 30% Movement Speed for 1 second to you and target allied champion. This healing is halved for units recently affected by Summoner Heal.
     */
    HEAL("SummonerHeal", 7),
    /**
     * Deals 390-1000 true damage (depending on champion level) to target epic or large monster or enemy minion.
     */
    SMITE("SummonerSmite", 11),
    /**
     * Exhausts target enemy champion, reducing their Movement Speed and Attack Speed by 30%, their Armor and Magic Resist by 10, and their damage dealt by 40% for 2.5 seconds.
     */
    EXHAUST("SummonerExhaust", 3),
    /**
     * Throw a Poro at your enemies. If it hits, you can quickly travel to your target as a follow up.
     */
    PORO_TOSS("SummonerPoroThrow", 31),
    /**
     * Restores 40% of your champion's maximum Mana. Also restores allies for 40% of their maximum Mana
     */
    CLARITY("SummonerMana", 13),
    /**
     * Reveals a small area of the map for your team for 5 seconds.
     */
    CLAIRVOYANCE("SummonerClairvoyance", 2),
    /**
     * Shields your champion for 115-455 (depending on champion level) for 2 seconds.
     */
    BARRIER("SummonerBarrier", 21),
    /**
     * Teleports your champion a short distance toward your cursor's location.
     */
    FLASH("SummonerFlash", 4, 2202, 2203),
    /**
     * Allied Turret: Grants massive regeneration for 8 seconds. Enemy Turret: Reduces damage dealt by 80% for 8 seconds.
     */
    GARRISON("SummonerOdinGarrison", 17),
    /**
     * Instantly revives your champion at your team's Summoner Platform and increases their Movement Speed for a short duration.
     */
    REVIVE("SummonerRevive", 10),
    /**
     * In Nexus Siege, Summoner Spells are replaced with Siege Weapon Slots. Spend Crystal Shards to buy single-use Siege Weapons from the item shop, then use your Summoner Spell keys to activate them!
     */
    NEXUS_SIEGE_1("SiegeEmptySlot", 33),
    /**
     * In Nexus Siege, Summoner Spells are replaced with Siege Weapon Slots. Spend Crystal Shards to buy single-use Siege Weapons from the item shop, then use your Summoner Spell keys to activate them!
     */
    NEXUS_SIEGE_2("SiegeEmptySlot", 34),
    /**
     * Summoner spells are disabled in this mode.
     */
    DARK_STAR_1("Summoner_Empty", 35),
    /**
     * Summoner spells are disabled in this mode.
     */
    DARK_STAR_2("Summoner_Empty", 36),
    /**
     * It's a snowball! It's a Poro! It's...uh...one of those.
     */
    SNOW_MARK("SummonerMark", 39),
    /**
     * Temporarily shields 75-330 (depending on champion level) damage from your champion. After 2.5 seconds the shield expires and your champion is teleported to safety. Melee champions travel extra distance.
     */
    BACKTRACK("Summoner_Backtrack", 5),
    /**
     * Revive a fallen friend by remaining next to them for 2 seconds. Exiting the area early does not consume your cooldown.
     */
    RESUSCITATE("SummonerTemp2", 50),
    /**
     * Your champion gains increased Movement Speed and can move through units for 10 seconds. Grants a maximum of 28-45% (depending on champion level) Movement Speed after accelerating for 2 seconds.
     */
    GHOST_2("Summoner_haste", 51),
    /**
     * Dash through spacetime, becoming briefly untargetable and invulnerable as you rapidly move towards a location.
     */
    WARP("SummonerTemp1", 52),
    /**
     * This slot will be replaced by another champion's ultimate selected at the start of the game. There will be 30 seconds to select an ultimate. Be prepared!
     */
    ULTBOOK_1("Summoner_Empty", 54),
    /**
     * This slot will be replaced by another champion's ultimate selected at the start of the game. There will be 30 seconds to select an ultimate. Be prepared!
     */
    AUTO_SMITE("Summoner_EmptySmite", 55),
    /**
     * Gain a short burst of Move Speed, increased while running away from enemy champions.
     */
    FLEE("Summoner_Flee", 2201),
    
    ;
    
    
    private final Integer[] ids;
    private final String  apiName;
    
    /**
     * Instantiates a new summoner spell.
     *
     * @param ids the id
     */
    SummonerSpellType(final String name, final Integer... ids)
    {
        this.ids = ids;
        this.apiName = name;
    }
    
    /**
     * Returns an SummonerSpellType from the provided code.
     *
     * @param type the type
     * @return SummonerSpellType
     */
    public Optional<SummonerSpellType> getFromCode(final String type)
    {
        Optional<SummonerSpellType> byName = Stream.of(SummonerSpellType.values()).filter(t -> t.getApiName().equals(type)).findFirst();
        if (byName.isPresent())
        {
            return byName;
        }
        
        return Stream.of(SummonerSpellType.values()).filter(t -> Arrays.asList(t.ids).contains(Integer.parseInt(type))).findFirst();
    }
    
    @Override
    public String prettyName()
    {
        switch (this) {
            case UNKNOWN:
                return "Unknown";
            case CLEANSE:
                return "Cleanse";
            case TELEPORT:
                return "Teleport";
            case TO_THE_KING:
                return "To The King";
            case IGNITE:
                return "Ignite";
            case GHOST:
            case GHOST_2:
                return "Ghost";
            case MARK:
                return "Mark";
            case HEAL:
                return "Heal";
            case SMITE:
                return "Smite";
            case EXHAUST:
                return "Exhaust";
            case PORO_TOSS:
                return "Poro Toss";
            case CLARITY:
                return "Clarity";
            case CLAIRVOYANCE:
                return "Clairvoyance";
            case BARRIER:
                return "Barrier";
            case FLASH:
                return "Flash";
            case GARRISON:
                return "Garrison";
            case REVIVE:
                return "Revive";
            case NEXUS_SIEGE_1:
                return "Siege Slot 1";
            case NEXUS_SIEGE_2:
                return "Siege Slot 2";
            case DARK_STAR_1:
                return "Disabled";
            case DARK_STAR_2:
                return "Disabled";
            case SNOW_MARK:
                return "Snow Mark";
            case BACKTRACK:
                return "Backtrack";
            case RESUSCITATE:
                return "Resuscitate";
            case WARP:
                return "Warp";
            default:
                return "This enum does not have a pretty name";
        }
    }
    
    /**
     * Gets the id.
     *
     * @return the id
     */
    public Integer getValue()
    {
        return this.ids[0];
    }
    
    /**
     * The name of the spell as used in the rest of the API
     *
     * @return String
     */
    public String getApiName()
    {
        return apiName;
    }
}
