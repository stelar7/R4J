package no.stelar7.api.l4j8.basic.constants.types;

import java.util.*;
import java.util.stream.*;

public enum SummonerSpellType
{
    /**
     * Removes all disables and summoner spell debuffs affecting your champion and lowers the duration of incoming disables by 65% for 3 seconds.
     */
    CLEANSE(1, "SummonerBoost"),
    /**
     * After channeling for 3.5 seconds, teleports your champion to target allied structure, minion, or ward.
     */
    TELEPORT(12, "SummonerTeleport"),
    /**
     * Quickly travel to the Poro King's side.
     */
    TO_THE_KING(30, "SummonerPoroRecall"),
    /**
     * Ignites target enemy champion, dealing 70-410 true damage (depending on champion level) over 5 seconds, grants you vision of the target, and reduces healing effects on them for the duration.
     */
    IGNITE(14, "SummonerDot"),
    /**
     * Your champion can move through units and has 27% increased Movement Speed for 10 seconds
     */
    GHOST(6, "SummonerHaste"),
    /**
     * Throw a snowball in a straight line at your enemies. If it hits an enemy, they become marked and your champion can quickly travel to the marked target as a follow up.
     */
    MARK(32, "SummonerSnowball"),
    /**
     * Restores 90-345 Health (depending on champion level) and grants 30% Movement Speed for 1 second to you and target allied champion. This healing is halved for units recently affected by Summoner Heal.
     */
    HEAL(7, "SummonerHeal"),
    /**
     * Deals 390-1000 true damage (depending on champion level) to target epic or large monster or enemy minion.
     */
    SMITE(11, "SummonerSmite"),
    /**
     * Exhausts target enemy champion, reducing their Movement Speed and Attack Speed by 30%, their Armor and Magic Resist by 10, and their damage dealt by 40% for 2.5 seconds.
     */
    EXHAUST(3, "SummonerExhaust"),
    /**
     * Throw a Poro at your enemies. If it hits, you can quickly travel to your target as a follow up.
     */
    PORO_TOSS(31, "SummonerPoroThrow"),
    /**
     * Restores 40% of your champion's maximum Mana. Also restores allies for 40% of their maximum Mana
     */
    CLARITY(13, "SummonerMana"),
    /**
     * Reveals a small area of the map for your team for 5 seconds.
     */
    CLAIRVOYANCE(2, "SummonerClairvoyance"),
    /**
     * Shields your champion for 115-455 (depending on champion level) for 2 seconds.
     */
    BARRIER(21, "SummonerBarrier"),
    /**
     * Teleports your champion a short distance toward your cursor's location.
     */
    FLASH(4, "SummonerFlash"),
    /**
     * Allied Turret: Grants massive regeneration for 8 seconds. Enemy Turret: Reduces damage dealt by 80% for 8 seconds.
     */
    GARRISON(17, "SummonerOdinGarrison"),
    /**
     * Instantly revives your champion at your team's Summoner Platform and increases their Movement Speed for a short duration.
     */
    REVIVE(10, "SummonerRevive");
    
    private final Integer id;
    private final String  apiName;
    
    /**
     * Instantiates a new summoner spell.
     *
     * @param id the id
     */
    SummonerSpellType(final Integer id, final String name)
    {
        this.id = id;
        this.apiName = name;
    }
    
    /**
     * Returns an SummonerSpellType from the provided code.
     *
     * @param type the type
     * @return SummonerSpellType
     */
    public static Optional<SummonerSpellType> getFromCode(final String type)
    {
        return Stream.of(SummonerSpellType.values()).filter(t -> t.getValue().equals(Integer.valueOf(type))).findFirst();
    }
    
    /**
     * Gets the id.
     *
     * @return the id
     */
    public Integer getValue()
    {
        return this.id;
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
