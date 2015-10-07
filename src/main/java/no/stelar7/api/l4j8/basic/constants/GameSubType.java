package no.stelar7.api.l4j8.basic.constants;

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
    NORMAL_3x3,
    /**
     * Dominion/Crystal Scar games
     */
    ODIN_UNRANKED,
    /**
     * ARAM / Howling Abyss games
     */
    ARAM_UNRANKED_5x5,
    /**
     * Summoner's Rift and Crystal Scar games played against Intro, Beginner, or Intermediate AI
     */
    BOT,
    /**
     * Twisted Treeline games played against AI
     */
    BOT_3x3,
    /**
     * Summoner's Rift ranked solo queue games
     */
    RANKED_SOLO_5x5,
    /**
     * Twisted Treeline ranked team games
     */
    RANKED_TEAM_3x3,
    /**
     * Summoner's Rift ranked team games
     */
    RANKED_TEAM_5x5,
    /**
     * One for All games
     */
    ONEFORALL_5x5,
    /**
     * Snowdown Showdown 1x1 games
     */
    FIRSTBLOOD_1x1,
    /**
     * Snowdown Showdown 2x2 games
     */
    FIRSTBLOOD_2x2,
    /**
     * Summoner's Rift 6x6 Hexakill games
     */
    SR_6x6,
    /**
     * Team Builder games
     */
    CAP_5x5,
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
    BILGEWATER,;

    public static GameSubType getFromCode(String subType)
    {
        return Stream.of(values()).filter(t -> t.name().equals(subType)).findFirst().get();
    }
}
