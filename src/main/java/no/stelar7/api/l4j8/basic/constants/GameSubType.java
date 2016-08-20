package no.stelar7.api.l4j8.basic.constants;

import java.util.Optional;
import java.util.stream.Stream;

public enum GameSubType
{
    /**
     * Custom games
     */
    NONE,
    /**
     * Summoner's Rift unranked games
     */
    NORMAL,
    /**
     * Twisted Treeline unranked games
     */
    NORMAL_3X3,
    /**
     * Dominion/Crystal Scar games
     */
    ODIN_UNRANKED,
    /**
     * ARAM / Howling Abyss games
     */
    ARAM_UNRANKED_5X5,
    /**
     * Summoner's Rift and Crystal Scar games played against Intro, Beginner, or Intermediate AI
     */
    BOT,
    /**
     * Twisted Treeline games played against AI
     */
    BOT_3X3,
    /**
     * Summoner's Rift ranked solo queue games
     */
    RANKED_SOLO_5X5,
    /**
     * Twisted Treeline ranked team games
     */
    RANKED_TEAM_3X3,
    /**
     * Summoner's Rift ranked team games
     */
    RANKED_TEAM_5X5,
    /**
     * One for All games
     */
    ONEFORALL_5X5,
    /**
     * Snowdown Showdown 1x1 games
     */
    FIRSTBLOOD_1X1,
    /**
     * Snowdown Showdown 2x2 games
     */
    FIRSTBLOOD_2X2,
    /**
     * Summoner's Rift 6x6 Hexakill games
     */
    SR_6X6,
    /**
     * Team Builder games
     */
    CAP_5X5,
    /**
     * Ultra Rapid Fire games
     */
    URF,
    /**
     * Ultra Rapid Fire games played against AI
     */
    URF_BOT,
    /**
     * Summoner's Rift games played against Nightmare AI
     */
    NIGHTMARE_BOT,
    /**
     * Ascension games
     */
    ASCENSION,
    /**
     * Twisted Treeline 6x6 Hexakill games
     */
    HEXAKILL,
    /**
     * King Poro games
     */
    KING_PORO,
    /**
     * Nemesis game
     */
    COUNTER_PICK,
    /**
     * Black Market Brawlers games
     */
    BILGEWATER,
    /**
     * Nexus Siege games
     */
    SIEGE,;

    public static Optional<GameSubType> getFromCode(final String subType)
    {
        return Stream.of(GameSubType.values()).filter(t -> t.name().equalsIgnoreCase(subType)).findFirst();
    }
}
