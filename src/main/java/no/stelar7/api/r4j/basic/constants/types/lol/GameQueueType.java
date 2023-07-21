package no.stelar7.api.r4j.basic.constants.types.lol;

import no.stelar7.api.r4j.basic.constants.types.CodedEnum;

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
    NEXUS_BLITZ(new Integer[]{1200, 1300}),
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
    TUTORIAL_MODULE_1(new Integer[]{2000}),
    TUTORIAL_MODULE_2(new Integer[]{2010}),
    TUTORIAL_MODULE_3(new Integer[]{2020}),
    
    /**
     * Teamfight Tactics (Normal)
     */
    TEAMFIGHT_TACTICS(new Integer[]{1090}),
    
    /**
     * Teamfight Tactics (Ranked)
     */
    TEAMFIGHT_TACTICS_RANKED(new Integer[]{1100}, "RANKED_TFT"),
    /**
     * Teamfight Tactics (Tutorial)
     */
    TEAMFIGHT_TACTICS_TUTORIAL(new Integer[]{1110}),
    /**
     * Teamfight Tactics (Simulation)
     */
    TEAMFIGHT_TACTICS_SIMULATION(new Integer[]{1111}),
    /**
     * Teamfight Tactics (Hyper roll)
     */
    TEAMFIGHT_TACTICS_HYPER_ROLL(new Integer[]{1130}, "RANKED_TFT_TURBO"),
    /**
     * Teamfight Tactics (Hyper roll) (1v0)
     */
    TEAMFIGHT_TACTICS_HYPER_ROLL_1V0(new Integer[]{1131}),
    
    /**
     * Teamfight Tactics (Normal Double Up 2v0)
     */
    TEAMFIGHT_TACTICS_DOUBLE_UP_2V0(new Integer[]{1142, 1151, 1161}),
    
    /**
     * Teamfight Tactics (Normal Double Up 4v0)
     */
    TEAMFIGHT_TACTICS_DOUBLE_UP_4V0(new Integer[]{1143, 1152, 1162}),
    
    /**
     * Teamfight Tactics (Normal Double Up)
     */
    TEAMFIGHT_TACTICS_DOUBLE_UP(new Integer[]{1140, 1150}, "RANKED_TFT_PAIRS"),
    
    /**
     * Teamfight Tactics (Normal Double Up 1v7 bots)
     */
    TEAMFIGHT_TACTICS_DOUBLE_UP_1V7_BOTS(new Integer[]{1141}),
    
    TEAMFIGHT_TACTICS_DOUBLE_UP_WORKSHOP(new Integer[]{1160}, "RANKED_TFT_DOUBLE_UP"),
    
    TEAMFIGHT_TACTICS_FORTUNE_FAVOR(new Integer[]{1170}),
    
    TEAMFIGHT_TACTICS_SOUL_BRAWL(new Integer[]{1180}),
    
    /**
     * 2v2v2v2
     */
    CHERRY(new Integer[]{1700, 1701, 1704}),
    
    /**
     * Ultra Rapid Fire games
     */
    URF_1V1(new Integer[]{1901}),
    /**
     * Ultra Rapid Fire games
     */
    URF_2V2(new Integer[]{1902}),
    /**
     * Ultra Rapid Fire games
     */
    URF_3V3(new Integer[]{1903}),
    /**
     * Ultra Rapid Fire games
     */
    URF_4V4(new Integer[]{1904}),
    /**
     * Ultra Rapid Fire games
     */
    URF(new Integer[]{1900}),
    
    
    /**
     * Ultimate Spellbook
     */
    ULTBOOK(new Integer[]{1400}),
    
    ONE_VS_ONE(new Integer[]{1401}),
    TWO_VS_TWO(new Integer[]{1402}),
    THREE_VS_THREE(new Integer[]{1403}),
    FOUR_VS_FOUR(new Integer[]{1404}),
    
    TFT_CUSTOM(new Integer[]{3000}),
    TFT_HYPER_ROLL_CUSTOM(new Integer[]{3010}),

    ARAM_CLASH(new Integer[]{720}),
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
    
    public Optional<GameQueueType> getFromCode(String value)
    {
        try
        {
            
            if (value.contains("."))
            {
                value = value.substring(0, value.indexOf("."));
            }
            
            return getFromId(Integer.parseInt(value));
        } catch (NumberFormatException e)
        {
            return getFromString(value);
        }
    }
    
    @Override
    public String prettyName()
    {
        switch (this)
        {
            
            case CUSTOM:
                return "Custom";
            case NORMAL_3X3_BLIND_PICK_OLD:
                return "3v3 Blind Pick";
            case NORMAL_5X5_BLIND_PICK_OLD:
            case NORMAL_5V5_BLIND_PICK:
                return "5v5 Blind Pick";
            case NORMAL_5X5_DRAFT:
                return "5v5 Draft Pick";
            case RANKED_SOLO_5X5:
                return "5v5 Ranked Solo";
            case RANKED_PREMADE_5X5:
                return "5v5 Ranked Duo";
            case RANKED_PREMADE_3X3:
                return "3v3 Ranked Duo";
            case RANKED_TEAM_3X3:
                return "3v3 Ranked Team";
            case RANKED_TEAM_5X5:
                return "5v5 Ranked Team";
            case ODIN_5X5_BLIND:
                return "Dominion Blind Pick";
            case ODIN_5X5_DRAFT:
                return "Dominion Draft Pick";
            case BOT_5X5:
                return "Summoners Rift Bots";
            case BOT_ODIN_5X5:
                return "Dominion Bots";
            case BOT_5X5_INTRO_OLD:
                return "Summoners Rift Bots Intro Difficulty";
            case BOT_5X5_BEGINNER_OLD:
                return "Summoners Rift Bots Beginner Difficulty";
            case BOT_5X5_INTERMEDIATE_OLD:
                return "Summoners Rift Bots Intermediate Difficulty";
            case BOT_3X3_BEGINNER_OLD:
                return "Twisted Treeline Bots Beginner Difficulty";
            case GROUP_FINDER_5X5:
                return "Teambuilder";
            case ARAM_5X5_OLD:
                return "ARAM";
            case ONEFORALL_5X5:
                return "One for All";
            case FIRSTBLOOD_1X1:
                return "1v1 Snowdown Showdown";
            case FIRSTBLOOD_2X2:
                return "2v2 Snowdown Showdown";
            case HEXAKILL_6X6_SR:
                return "Summoners Rift Hexakill";
            case URF_1V1:
            case URF_2V2:
            case URF_3V3:
            case URF_4V4:
            case URF_5X5:
            case URF:
                return "URF";
            case ONE_FOR_ALL_MIRROR:
                return "One for All Mirror Mode";
            case BOT_URF_5X5:
                return "URF Bots";
            case NIGHTMARE_BOT_5X5_RANK1:
                return "Doombots Rank 1";
            case NIGHTMARE_BOT_5X5_RANK2:
                return "Doombots Rank 2";
            case NIGHTMARE_BOT_5X5_RANK5:
                return "Doombots Rank 5";
            case ASCENSION_5X5:
                return "Ascension";
            case HEXAKILL:
                return "Hexakill";
            case BILGEWATER_ARAM_5X5:
                return "ARAM Butchers Bridge Version";
            case KING_PORO_5X5:
                return "King Poro";
            case COUNTER_PICK:
                return "Nemesis";
            case BILGEWATER_5X5:
                return "Blackmarket Brawlers";
            case NEXUS_SIEGE_OLD:
                return "Nexus Siege";
            case DEFINITELY_NOT_DOMINION_5X5:
                return "Definitely Not Dominion";
            case ALL_RANDOM_URF:
                return "ARURF";
            case SNOW_BATTLE_ARURF:
                return "ARURF Snow Skin";
            case OVERCHARGE:
                return "Overcharge";
            case ARAM_5X5:
                return "ARAM";
            case TEAM_BUILDER_DRAFT_UNRANKED_5X5:
                return "5v5 Dynamic Queue";
            case TEAM_BUILDER_DRAFT_RANKED_5X5:
                return "5v5 Dynamic Ranked Queue";
            case TEAM_BUILDER_RANKED_SOLO:
                return "5v5 Dynamic Ranked Solo Queue";
            case RANKED_FLEX_SR:
                return "5v5 Ranked Flex Queue";
            case ARAM:
                return "ARAM";
            case NORMAL_3X3_BLIND_PICK:
                return "3v3 Normal Flex Queue";
            case RANKED_FLEX_TT:
                return "3v3 Ranked Flex Queue";
            case ASSASSINATE_5X5:
                return "Blood Hunt Assassin";
            case DARKSTAR_3X3:
                return "Darkstar";
            case CLASH:
                return "Clash";
            case BOT_3X3_INTERMEDIATE:
                return "3v3 Bots Intermediate Difficulty";
            case BOT_3X3_INTRO:
                return "3v3 Bots Intro Difficulty";
            case BOT_3X3_BEGINNER:
                return "3v3 Bots Beginner Difficulty";
            case BOT_5X5_INTRO:
                return "5v5 Bots Intro Difficulty";
            case BOT_5X5_BEGINNER:
                return "5v5 Bots Beginner Difficulty";
            case BOT_5X5_INTERMEDIATE:
                return "5v5 Bots Intermediate Difficulty";
            case NEXUS_SIEGE:
                return "Nexus Siege";
            case NIGHTMARE_BOT_5X5_VOTE:
                return "Doombots Vote Difficulty";
            case NIGHTMARE_BOT_5X5:
                return "Doombots";
            case INVASION_NORMAL:
                return "Invasion Normal Difficulty";
            case INVASION_ONSLAUGHT:
                return "Invasion Onslaught Difficulty";
            case NEXUS_BLITZ:
                return "Nexus Blitz";
            case ODYSSEY_INTRO:
                return "Odyssey Intro Difficulty";
            case ODYSSEY_CADET:
                return "Odyssey Cadet Difficulty";
            case ODYSSEY_CREWMEMBER:
                return "Odyssey Crewmember Difficulty";
            case ODYSSEY_CAPTAIN:
                return "Odyssey Captain Difficulty";
            case ODYSSEY_ONSLAUGHT:
                return "Odyssey Onslaught Difficulty";
            case TUTORIAL_MODULE_1:
                return "Tutorial Part 1";
            case TUTORIAL_MODULE_2:
                return "Tutorial Part 2";
            case TUTORIAL_MODULE_3:
                return "Tutorial Part 3";
            case TEAMFIGHT_TACTICS:
                return "Teamfight Tactics";
            case TEAMFIGHT_TACTICS_RANKED:
                return "Teamfight Tactics Ranked";
            case TEAMFIGHT_TACTICS_TUTORIAL:
                return "Teamfight Tactics Tutorial";
            case TEAMFIGHT_TACTICS_HYPER_ROLL:
                return "Teamfight Tactics Hyper Roll";
            case ARAM_CLASH:
                return "ARAM Clash";
            default:
                return "This enum does not have a pretty name";
        }
    }
    
    @Override
    public String commonName()
    {
        switch (this)
        {
            
            case CUSTOM:
                return "Custom";
            case NORMAL_3X3_BLIND_PICK_OLD:
            case RANKED_FLEX_TT:
                return "3v3 Blind Pick";
            case NORMAL_5X5_BLIND_PICK_OLD:
            case NORMAL_5V5_BLIND_PICK:
                return "5v5 Blind Pick";
            case NORMAL_5X5_DRAFT:
            case TEAM_BUILDER_DRAFT_UNRANKED_5X5:
                return "5v5 Draft Pick";
            case RANKED_SOLO_5X5:
            case TEAM_BUILDER_RANKED_SOLO:
            case TEAM_BUILDER_DRAFT_RANKED_5X5:
                return "5v5 Ranked Solo";
            case RANKED_PREMADE_5X5:
                return "5v5 Ranked Duo";
            case RANKED_PREMADE_3X3:
                return "3v3 Ranked Duo";
            case RANKED_TEAM_3X3:
                return "3v3 Ranked Team";
            case RANKED_TEAM_5X5:
                return "5v5 Ranked Team";
            case ODIN_5X5_BLIND:
                return "Dominion Blind Pick";
            case ODIN_5X5_DRAFT:
                return "Dominion";
            case BOT_5X5:
            case BOT_5X5_INTRO_OLD:
            case BOT_5X5_BEGINNER_OLD:
            case BOT_5X5_INTERMEDIATE_OLD:
            case BOT_5X5_INTRO:
            case BOT_5X5_BEGINNER:
            case BOT_5X5_INTERMEDIATE:
                return "VS AI";
            case BOT_ODIN_5X5:
                return "Dominion VS AI";
            case BOT_3X3_BEGINNER_OLD:
            case BOT_3X3_INTERMEDIATE:
            case BOT_3X3_INTRO:
            case BOT_3X3_BEGINNER:
                return "Twisted Treeline VS AI";
            case GROUP_FINDER_5X5:
                return "Teambuilder";
            case ARAM_5X5:
            case ARAM_5X5_OLD:
            case BILGEWATER_ARAM_5X5:
                return "ARAM";
            case ONEFORALL_5X5:
                return "One for All";
            case FIRSTBLOOD_1X1:
                return "1v1 Snowdown Showdown";
            case FIRSTBLOOD_2X2:
                return "2v2 Snowdown Showdown";
            case HEXAKILL:
            case HEXAKILL_6X6_SR:
                return "Hexakill";
            case URF_1V1:
            case URF_2V2:
            case URF_3V3:
            case URF_4V4:
            case URF_5X5:
            case URF:
                return "URF";
            case ONE_FOR_ALL_MIRROR:
                return "One for All Mirror Mode";
            case BOT_URF_5X5:
                return "URF VS AI";
            case NIGHTMARE_BOT_5X5_RANK1:
            case NIGHTMARE_BOT_5X5_RANK2:
            case NIGHTMARE_BOT_5X5_RANK5:
                return "Doombots";
            case ASCENSION_5X5:
                return "Ascension";
            case KING_PORO_5X5:
                return "King Poro";
            case COUNTER_PICK:
                return "Nemesis";
            case BILGEWATER_5X5:
                return "Blackmarket Brawlers";
            case NEXUS_SIEGE_OLD:
                return "Nexus Siege";
            case DEFINITELY_NOT_DOMINION_5X5:
                return "Definitely Not Dominion";
            case ALL_RANDOM_URF:
            case SNOW_BATTLE_ARURF:
                return "ARURF";
            case OVERCHARGE:
                return "Overcharge";
            case RANKED_FLEX_SR:
                return "5v5 Ranked Flex Queue";
            case ARAM:
                return "ARAM";
            case NORMAL_3X3_BLIND_PICK:
                return "3v3 Normal Flex Queue";
            case ASSASSINATE_5X5:
                return "Blood Hunt Assassin";
            case DARKSTAR_3X3:
                return "Darkstar";
            case CLASH:
                return "Clash";
            case NEXUS_SIEGE:
                return "Nexus Siege";
            case NIGHTMARE_BOT_5X5_VOTE:
            case NIGHTMARE_BOT_5X5:
                return "Doombots";
            case INVASION_NORMAL:
            case INVASION_ONSLAUGHT:
                return "Invasion";
            case NEXUS_BLITZ:
                return "Nexus Blitz";
            case ODYSSEY_INTRO:
            case ODYSSEY_CADET:
            case ODYSSEY_CREWMEMBER:
            case ODYSSEY_CAPTAIN:
            case ODYSSEY_ONSLAUGHT:
                return "Odyssey";
            case TUTORIAL_MODULE_1:
            case TUTORIAL_MODULE_2:
            case TUTORIAL_MODULE_3:
                return "Tutorial";
            case TEAMFIGHT_TACTICS:
            case TEAMFIGHT_TACTICS_RANKED:
            case TEAMFIGHT_TACTICS_TUTORIAL:
            case TEAMFIGHT_TACTICS_HYPER_ROLL:
                return "Teamfight Tactics";
            case ARAM_CLASH:
                return "ARAM Clash";
            default:
                return "This enum does not have a pretty name";
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
