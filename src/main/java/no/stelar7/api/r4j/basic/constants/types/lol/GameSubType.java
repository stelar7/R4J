package no.stelar7.api.r4j.basic.constants.types.lol;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

import java.util.*;
import java.util.stream.*;

public enum GameSubType implements CodedEnum
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
    SIEGE,
    /**
     *
     */
    RANKED_FLEX_SR,
    /**
     * Ranked Flex games on Twisted Treeline
     */
    RANKED_FLEX_TT,
    /**
     * Darkstar games
     */
    DARKSTAR,
    /**
     * Invasion games
     */
    STARGUARDIAN,
    /**
     * Overcharge games?
     */
    OVERCHARGE,
    /**
     * Odyssey games
     */
    ODYSSEY;
    
    public Optional<GameSubType> getFromCode(final String subType)
    {
        return Stream.of(GameSubType.values()).filter(t -> t.name().equalsIgnoreCase(subType)).findFirst();
    }
    
    @Override
    public String prettyName()
    {
        switch (this)
        {
            
            case NONE:
                return "Custom Game";
            case NORMAL:
                return "Summoners Rift Unranked";
            case NORMAL_3X3:
                return "Twisted Treeline Unranked";
            case ODIN_UNRANKED:
                return "Dominion Unranked";
            case ARAM_UNRANKED_5X5:
                return "ARAM Unranked";
            case BOT:
                return "Summoners Rift Bot Game";
            case BOT_3X3:
                return "Twisted Treeline Bot Game";
            case RANKED_SOLO_5X5:
                return "Summoners Rift Ranked Solo";
            case RANKED_TEAM_3X3:
                return "Twisted Treeline Ranked Team";
            case RANKED_TEAM_5X5:
                return "Summoners Rift Ranked Team";
            case ONEFORALL_5X5:
                return "One for All";
            case FIRSTBLOOD_1X1:
                return "Snowdown Showdown 1v1";
            case FIRSTBLOOD_2X2:
                return "Snowdown Showdown 2v2";
            case SR_6X6:
                return "Summoners Rift Hexakill";
            case CAP_5X5:
                return "Teambuilder";
            case URF:
                return "Ultra Rapid Fire";
            case URF_BOT:
                return "Ulta Rapid Fire Bot Game";
            case NIGHTMARE_BOT:
                return "Summoners Rift Nightmare Bots";
            case ASCENSION:
                return "Ascension";
            case HEXAKILL:
                return "Twisted Treeline Hexakill";
            case KING_PORO:
                return "King Poro";
            case COUNTER_PICK:
                return "Nemesis";
            case BILGEWATER:
                return "Blackmarket Brawlers";
            case SIEGE:
                return "Nexus Siege";
            case RANKED_FLEX_SR:
                return "Summoners Rift Ranked Flex";
            case RANKED_FLEX_TT:
                return "Twisted Treeline Ranked Flex";
            case DARKSTAR:
                return "Darkstar";
            case STARGUARDIAN:
                return "Invasion";
            case OVERCHARGE:
                return "Overcharge";
            case ODYSSEY:
                return "Odyssey";
            default:
                return "This enum does not have a pretty name";
        }
    }
}
