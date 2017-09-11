package no.stelar7.api.l4j8.basic.constants.types;

import java.util.*;
import java.util.stream.*;

public enum GameQueueType
{
    /**
     * Custom games
     */
    CUSTOM(0),
    /**
     * Normal 3v3 games
     */
    NORMAL_3X3(8),
    /**
     * Normal 5v5 Blind Pick games
     */
    NORMAL_5X5_BLIND(2),
    /**
     * Normal 5v5 Draft Pick games
     */
    NORMAL_5X5_DRAFT(14),
    /**
     * Ranked Solo 5v5 games
     */
    RANKED_SOLO_5X5(4, "RANKED_SOLO_5x5"),
    /**
     * Ranked Premade 5v5 games
     */
    RANKED_PREMADE_5X5(6),
    /**
     * Ranked Premade 3v3 games
     */
    RANKED_PREMADE_3X3(9),
    /**
     * Ranked TeamType 3v3 games
     */
    RANKED_TEAM_3X3(41),
    /**
     * Ranked TeamType 5v5 games
     */
    RANKED_TEAM_5X5(42),
    /**
     * Dominion 5v5 Blind Pick games
     */
    ODIN_5X5_BLIND(16),
    /**
     * Dominion 5v5 Draft Pick games
     */
    ODIN_5X5_DRAFT(17),
    /**
     * Historical Summoner's Rift Coop vs AI games
     */
    BOT_5X5(7),
    /**
     * Dominion Coop vs AI games
     */
    BOT_ODIN_5X5(25),
    /**
     * Summoner's Rift Coop vs AI Intro Bot games
     */
    BOT_5X5_INTRO_OLD(31),
    /**
     * Summoner's Rift Coop vs AI Beginner Bot games
     */
    BOT_5X5_BEGINNER_OLD(32),
    /**
     * Historical Summoner's Rift Coop vs AI Intermediate Bot games
     */
    BOT_5X5_INTERMEDIATE_OLD(33),
    /**
     * Twisted Treeline Coop vs AI games
     */
    BOT_3X3_BEGINNER_OLD(52),
    /**
     * Team Builder games
     */
    GROUP_FINDER_5X5(61),
    /**
     * ARAM games
     */
    ARAM_5X5_OLD(65),
    /**
     * One for All games
     */
    ONEFORALL_5X5(70),
    /**
     * Snowdown Showdown 1v1 games
     */
    FIRSTBLOOD_1X1(72),
    /**
     * Snowdown Showdown 2v2 games
     */
    FIRSTBLOOD_2X2(73),
    /**
     * Summoner's Rift 6x6 Hexakill games
     */
    HEXAKILL_6X6_SR(75),
    /**
     * Ultra Rapid Fire games
     */
    URF_5X5(76),
    /**
     * One for All (Mirror mode)
     */
    ONE_FOR_ALL_MIRROR(78),
    /**
     * Ultra Rapid Fire games played against AI games
     */
    BOT_URF_5X5(83),
    /**
     * Doom Bots Rank 1 games
     */
    NIGHTMARE_BOT_5X5_RANK1(91),
    /**
     * Doom Bots Rank 2 games
     */
    NIGHTMARE_BOT_5X5_RANK2(92),
    /**
     * Doom Bots Rank 5 games
     */
    NIGHTMARE_BOT_5X5_RANK5(93),
    /**
     * Ascension games
     */
    ASCENSION_5X5(96),
    /**
     * Twisted Treeline 6x6 Hexakill games
     */
    HEXAKILL(98),
    /**
     * Butcher's Bridge games
     */
    BILGEWATER_ARAM_5X5(100),
    /**
     * King Poro games
     */
    KING_PORO_5X5(300),
    /**
     * Nemesis games
     */
    COUNTER_PICK(310),
    /**
     * Black Market Brawlers games
     */
    BILGEWATER_5X5(313),
    /**
     * Nexux Siege games
     */
    SIEGE(315),
    /**
     * Definitely Not Dominion games
     */
    DEFINITELY_NOT_DOMINION_5X5(317),
    /**
     * All Random URF games
     */
    ALL_RANDOM_URF(318),
    /**
     * All Random Summoner's Rift games
     */
    ARAM_5X5(325),
    /**
     * Normal 5v5 Draft Pick games (Dynamic Queue)
     */
    TEAM_BUILDER_DRAFT_UNRANKED_5X5(400),
    /**
     * Ranked 5v5 Draft Pick games (Dynamic Queue)
     */
    TEAM_BUILDER_DRAFT_RANKED_5X5(410),
    /**
     * Ranked 5v5 Draft Pick games (Flex Queue)
     */
    TEAM_BUILDER_RANKED_SOLO(420),
    /**
     * Normal 5v5 Blind Pick games
     */
    NORMAL_5V5_BLIND_PICK(430),
    /**
     * Ranked 5v5 Draft Pick games (Flex Queue)
     */
    RANKED_FLEX_SR(440),
    /**
     * ARAM games
     */
    ARAM(450),
    /**
     * Normal 3v3 Blind Pick Twisted Treeline games
     */
    NORMAL_3X3_BLIND_PICK(460),
    /**
     * Ranked 3v3 Draft Pick games (Flex Queue)
     */
    RANKED_FLEX_TT(470),
    /**
     * Blood Hunt Assassin games
     */
    ASSASSINATE_5X5(600),
    /**
     * Darkstar games
     */
    DARKSTAR_3X3(610),
    /**
     * BOT 3x3 games
     */
    BOT_3X3_INTERMEDIATE(800),
    /**
     * BOT 3x3 games
     */
    BOT_3X3_INTRO(810),
    /**
     * BOT 3x3 games
     */
    BOT_3X3_BEGINNER(820),
    /**
     * BOT 5x5 games
     */
    BOT_5X5_INTRO(830),
    /**
     * BOT 5x5 games
     */
    BOT_5X5_BEGINNER(840),
    /**
     * BOT 5x5 games
     */
    BOT_5X5_INTERMEDIATE(850),
    /**
     * Invasion games
     */
    INVASION_NORMAL(980),
    /**
     * Invasion games
     */
    INVASION_ONSLAUGHT(990);
    
    private final Integer code;
    private final String  apiname;
    
    GameQueueType(final Integer code)
    {
        this(code, "");
    }
    
    GameQueueType(final Integer code, final String apiName)
    {
        this.code = code;
        this.apiname = apiName;
    }
    
    public static Optional<GameQueueType> getFromCode(final String value)
    {
        try
        {
            return getFromId(Integer.parseInt(value));
        } catch (NumberFormatException e)
        {
            return getFromString(value);
        }
    }
    
    public static Optional<GameQueueType> getFromString(final String value)
    {
        return Stream.of(GameQueueType.values()).filter(t -> t.name().equalsIgnoreCase(value)).findFirst();
    }
    
    public static Optional<GameQueueType> getFromId(final int value)
    {
        return Stream.of(GameQueueType.values()).filter(t -> t.code.equals(value)).findFirst();
    }
    
    public String getApiName()
    {
        return "".equals(apiname) ? this.name() : apiname;
    }
    
    public Integer getValue()
    {
        return this.code;
    }
}
