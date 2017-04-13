package no.stelar7.api.l4j8.basic.constants.types;

import java.util.Optional;
import java.util.stream.Stream;

public enum PlayerStatSummaryType
{
    /**
     * Summoner's Rift unranked games
     */
    UNRANKED,
    /**
     * Twisted Treeline unranked games
     */
    UNRANKED3X3,
    /**
     * Dominion/Crystal Scar games
     */
    ODINUNRANKED,
    /**
     * ARAM / Howling Abyss games
     */
    ARAMUNRANKED5X5,
    /**
     * Summoner's Rift and Crystal Scar games played against AI
     */
    COOPVSAI,
    /**
     * Twisted Treeline games played against AI
     */
    COOPVSAI3X3,
    /**
     * Summoner's Rift ranked solo queue games
     */
    RANKEDSOLO5X5,
    /**
     * Twisted Treeline ranked team games
     */
    RANKEDTEAM3X3,
    /**
     * Summoner's Rift ranked team games
     */
    RANKEDTEAM5X5,
    /**
     * One for All games
     */
    ONEFORALL5X5,
    /**
     * Snowdown Showdown 1x1 games
     */
    FIRSTBLOOD1X1,
    /**
     * Snowdown Showdown 2x2 games
     */
    FIRSTBLOOD2X2,
    /**
     * Summoner's Rift 6x6 Hexakill games
     */
    SUMMONERSRIFT6X6,
    /**
     * Team Builder games
     */
    CAP5X5,
    /**
     * Ultra Rapid Fire games
     */
    URF,
    /**
     * Ultra Rapid Fire games played against AI
     */
    URFBOTS,
    /**
     * Summoner's Rift games played against Nightmare AI
     */
    NIGHTMAREBOT,
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
    KINGPORO,
    /**
     * Nemesis games
     */
    COUNTERPICK,
    /**
     * Black Market Brawlers games
     */
    BILGEWATER,
    /**
     * Nexus Siege games
     */
    SIEGE,
    /**
     * Ranked Flex Summoners Rift games
     */
    RANKEDFLEXSR,
    /**
     * Ranked Flex Twisted Treeline games
     */
    RANKEDFLEXTT,;
    
    /**
     * Returns an MonsterType from the provided value
     *
     * @return MonsterType
     */
    public static Optional<PlayerStatSummaryType> getFromCode(final String type)
    {
        return Stream.of(PlayerStatSummaryType.values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst();
    }
}
