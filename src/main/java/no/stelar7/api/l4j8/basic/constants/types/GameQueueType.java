package no.stelar7.api.l4j8.basic.constants.types;

import java.util.*;
import java.util.stream.*;

public enum GameQueueType implements CodedEnum
{
    /**
     * Custom games
     */
    CUSTOM(new Integer[]{0}),
    /**
     * Normal 3v3 games
     */
    NORMAL_3X3_BLIND_PICK_OLD(new Integer[]{8}),
    /**
     * Normal 5v5 Blind Pick games
     */
    NORMAL_5X5_BLIND_PICK_OLD(new Integer[]{1, 2}),
    /**
     * Normal 5v5 Draft Pick games
     */
    NORMAL_5X5_DRAFT(new Integer[]{14}),
    /**
     * Ranked Solo 5v5 games
     */
    RANKED_SOLO_5X5(new Integer[]{4}, "RANKED_SOLO_5x5"),
    /**
     * Ranked Premade 5v5 games
     */
    RANKED_PREMADE_5X5(new Integer[]{6}),
    /**
     * Ranked Premade 3v3 games
     */
    RANKED_PREMADE_3X3(new Integer[]{9}),
    /**
     * Ranked TeamType 3v3 games
     */
    RANKED_TEAM_3X3(new Integer[]{41}),
    /**
     * Ranked TeamType 5v5 games
     */
    RANKED_TEAM_5X5(new Integer[]{42}),
    /**
     * Dominion 5v5 Blind Pick games
     */
    ODIN_5X5_BLIND(new Integer[]{16}),
    /**
     * Dominion 5v5 Draft Pick games
     */
    ODIN_5X5_DRAFT(new Integer[]{17}),
    /**
     * Historical Summoner's Rift Coop vs AI games
     */
    BOT_5X5(new Integer[]{7}),
    /**
     * Dominion Coop vs AI games
     */
    BOT_ODIN_5X5(new Integer[]{25}),
    /**
     * Summoner's Rift Coop vs AI Intro Bot games
     */
    BOT_5X5_INTRO_OLD(new Integer[]{31, 34}),
    /**
     * Summoner's Rift Coop vs AI Beginner Bot games
     */
    BOT_5X5_BEGINNER_OLD(new Integer[]{32, 33}),
    /**
     * Historical Summoner's Rift Coop vs AI Intermediate Bot games
     */
    BOT_5X5_INTERMEDIATE_OLD(new Integer[]{36}),
    /**
     * Twisted Treeline Coop vs AI games
     */
    BOT_3X3_BEGINNER_OLD(new Integer[]{52}),
    /**
     * Team Builder games
     */
    GROUP_FINDER_5X5(new Integer[]{61}),
    /**
     * ARAM games
     */
    ARAM_5X5_OLD(new Integer[]{62, 63, 64, 65, 67}),
    /**
     * One for All games
     */
    ONEFORALL_5X5(new Integer[]{70, 1020}),
    /**
     * Snowdown Showdown 1v1 games
     */
    FIRSTBLOOD_1X1(new Integer[]{72}),
    /**
     * Snowdown Showdown 2v2 games
     */
    FIRSTBLOOD_2X2(new Integer[]{73}),
    /**
     * Summoner's Rift 6x6 Hexakill games
     */
    HEXAKILL_6X6_SR(new Integer[]{75}),
    /**
     * Ultra Rapid Fire games
     */
    URF_5X5(new Integer[]{76}),
    /**
     * One for All (Mirror mode)
     */
    ONE_FOR_ALL_MIRROR(new Integer[]{78}),
    /**
     * Ultra Rapid Fire games played against AI games
     */
    BOT_URF_5X5(new Integer[]{83}),
    /**
     * Doom Bots Rank 1 games
     */
    NIGHTMARE_BOT_5X5_RANK1(new Integer[]{90, 91}),
    /**
     * Doom Bots Rank 2 games
     */
    NIGHTMARE_BOT_5X5_RANK2(new Integer[]{92}),
    /**
     * Doom Bots Rank 5 games
     */
    NIGHTMARE_BOT_5X5_RANK5(new Integer[]{93}),
    /**
     * Ascension games
     */
    ASCENSION_5X5(new Integer[]{910, 911, 96, 97}),
    /**
     * Twisted Treeline 6x6 Hexakill games
     */
    HEXAKILL(new Integer[]{970, 98}),
    /**
     * Butcher's Bridge games
     */
    BILGEWATER_ARAM_5X5(new Integer[]{100}),
    /**
     * King Poro games
     */
    KING_PORO_5X5(new Integer[]{920, 921, 300, 301}),
    /**
     * Nemesis games
     */
    COUNTER_PICK(new Integer[]{310}),
    /**
     * Black Market Brawlers games
     */
    BILGEWATER_5X5(new Integer[]{313}),
    /**
     * Nexux Siege games
     */
    NEXUS_SIEGE_OLD(new Integer[]{315}),
    /**
     * Definitely Not Dominion games
     */
    DEFINITELY_NOT_DOMINION_5X5(new Integer[]{316, 317}),
    /**
     * All Random URF games
     */
    ALL_RANDOM_URF(new Integer[]{900, 901, 318, 319}),
    /**
     * All Random snow URF games
     */
    SNOW_BATTLE_ARURF(new Integer[]{1010, 1011}),
    /**
     * Overcharge games
     */
    OVERCHARGE(new Integer[]{1000, 101}),
    /**
     * All Random Summoner's Rift games
     */
    ARAM_5X5(new Integer[]{325, 324}),
    /**
     * Normal 5v5 Draft Pick games (Dynamic Queue)
     */
    TEAM_BUILDER_DRAFT_UNRANKED_5X5(new Integer[]{400, 401, 402, 403}),
    /**
     * Ranked 5v5 Draft Pick games (Dynamic Queue)
     */
    TEAM_BUILDER_DRAFT_RANKED_5X5(new Integer[]{410, 411, 412, 413}),
    /**
     * Ranked 5v5 Draft Pick games (Flex Queue)
     */
    TEAM_BUILDER_RANKED_SOLO(new Integer[]{420, 421, 422}),
    /**
     * Normal 5v5 Blind Pick games
     */
    NORMAL_5V5_BLIND_PICK(new Integer[]{430, 431, 432, 433}),
    /**
     * Ranked 5v5 Draft Pick games (Flex Queue)
     */
    RANKED_FLEX_SR(new Integer[]{440, 441, 442}),
    /**
     * ARAM games
     */
    ARAM(new Integer[]{450, 451, 452, 930, 931}),
    /**
     * Normal 3v3 Blind Pick Twisted Treeline games
     */
    NORMAL_3X3_BLIND_PICK(new Integer[]{460}),
    /**
     * Ranked 3v3 Draft Pick games (Flex Queue)
     */
    RANKED_FLEX_TT(new Integer[]{470}),
    /**
     * Blood Hunt Assassin games
     */
    ASSASSINATE_5X5(new Integer[]{320, 321, 600, 601}),
    /**
     * Darkstar games
     */
    DARKSTAR_3X3(new Integer[]{610, 611}),
    /**
     * Clash games
     */
    CLASH(new Integer[]{700}),
    /**
     * BOT 3x3 games
     */
    BOT_3X3_INTERMEDIATE(new Integer[]{800, 801}),
    /**
     * BOT 3x3 games
     */
    BOT_3X3_INTRO(new Integer[]{810}),
    /**
     * BOT 3x3 games
     */
    BOT_3X3_BEGINNER(new Integer[]{820}),
    /**
     * BOT 5x5 games
     */
    BOT_5X5_INTRO(new Integer[]{830}),
    /**
     * BOT 5x5 games
     */
    BOT_5X5_BEGINNER(new Integer[]{840}),
    /**
     * BOT 5x5 games
     */
    BOT_5X5_INTERMEDIATE(new Integer[]{850}),
    /**
     * Nexus Siege games
     */
    NEXUS_SIEGE(new Integer[]{940, 941, 314, 315}),
    /**
     * Doom Bots w/ difficulty voting
     */
    NIGHTMARE_BOT_5X5_VOTE(new Integer[]{950, 951}),
    /**
     * Doom Bots games
     */
    NIGHTMARE_BOT_5X5(new Integer[]{960, 961}),
    /**
     * Invasion games
     */
    INVASION_NORMAL(new Integer[]{980, 981, 982}),
    /**
     * Invasion games
     */
    INVASION_ONSLAUGHT(new Integer[]{990}),
    /**
     * Nexus Blitz games
     */
    NEXUS_BLITZ(new Integer[]{1200}),
    /**
     * Odyssey: Extraction Intro games
     */
    ODYSSEY_INTRO(new Integer[]{1030}),
    /**
     * Odyssey: Extraction Cadet games
     */
    ODYSSEY_CADET(new Integer[]{1040}),
    /**
     * Odyssey: Extraction Crewmember games
     */
    ODYSSEY_CREWMEMBER(new Integer[]{1050}),
    /**
     * Odyssey: Extraction Captain games
     */
    ODYSSEY_CAPTAIN(new Integer[]{1060}),
    /**
     * Odyssey: Extraction Onslaught games
     */
    ODYSSEY_ONSLAUGHT(new Integer[]{1070}),
    /**
     * Tutorial games
     */
    TUTORIAL(new Integer[]{2000, 2010, 2020}),
    /**
     * Teamfight Tactics (Normal)
     */
    TEAMFIGHT_TACTICS(new Integer[]{1090}),
    /**
     * Teamfight Tactics (Ranked)
     */
    TEAMFIGHT_TACTICS_RANKED(new Integer[]{1100}, "RANKED_TFT"),
    ;
    
    private final Integer[] codes;
    private final String    apiname;
    
    GameQueueType(final Integer[] codes)
    {
        this(codes, "");
    }
    
    GameQueueType(final Integer[] codes, final String apiName)
    {
        this.codes = codes;
        this.apiname = apiName;
    }
    
    public Optional<GameQueueType> getFromCode(final String value)
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
        return Stream.of(GameQueueType.values()).filter(t -> t.name().equalsIgnoreCase(value) || t.apiname.equalsIgnoreCase(value)).findFirst();
    }
    
    public static Optional<GameQueueType> getFromId(final int value)
    {
        return Stream.of(GameQueueType.values()).filter(t -> Arrays.asList(t.codes).contains(value)).findFirst();
    }
    
    public String getApiName()
    {
        return "".equals(apiname) ? this.name() : apiname;
    }
    
    public Integer[] getValues()
    {
        return this.codes;
    }
}
