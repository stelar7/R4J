package no.stelar7.api.l4j8.basic.constants;

import java.util.stream.Stream;

public enum GameQueueType
{
    /**
     * Custom games
     */
    CUSTOM(0L),
    /**
     * Normal 3v3 games
     */
    NORMAL_3x3(8L),
    /**
     * Normal 5v5 Blind Pick games
     */
    NORMAL_5x5_BLIND(2L),
    /**
     * Normal 5v5 Draft Pick games
     */
    NORMAL_5x5_DRAFT(14L),
    /**
     * Ranked Solo 5v5 games
     */
    RANKED_SOLO_5x5(4L),
    /**
     * Ranked Premade 5v5 games
     */
    @Deprecated RANKED_PREMADE_5x5(6L),
    /**
     * Ranked Premade 3v3 games
     */
    @Deprecated RANKED_PREMADE_3x3(9L),
    /**
     * Ranked Team 3v3 games
     */
    RANKED_TEAM_3x3(41L),
    /**
     * Ranked Team 5v5 games
     */
    RANKED_TEAM_5x5(42L),
    /**
     * Dominion 5v5 Blind Pick games
     */
    ODIN_5x5_BLIND(16L),
    /**
     * Dominion 5v5 Draft Pick games
     */
    ODIN_5x5_DRAFT(17L),
    /**
     * Historical Summoner's Rift Coop vs AI games
     */
    @Deprecated BOT_5x5(7L),
    /**
     * Dominion Coop vs AI games
     */
    BOT_ODIN_5x5(25L),
    /**
     * Summoner's Rift Coop vs AI Intro Bot games
     */
    BOT_5x5_INTRO(31L),
    /**
     * Summoner's Rift Coop vs AI Beginner Bot games
     */
    BOT_5x5_BEGINNER(32L),
    /**
     * Historical Summoner's Rift Coop vs AI Intermediate Bot games
     */
    BOT_5x5_INTERMEDIATE(33L),
    /**
     * Twisted Treeline Coop vs AI games
     */
    BOT_TT_3x3(52L),
    /**
     * Team Builder games
     */
    GROUP_FINDER_5x5(61L),
    /**
     * ARAM games
     */
    ARAM_5x5(65L),
    /**
     * One for All games
     */
    ONEFORALL_5x5(70L),
    /**
     * Snowdown Showdown 1v1 games
     */
    FIRSTBLOOD_1x1(72L),
    /**
     * Snowdown Showdown 2v2 games
     */
    FIRSTBLOOD_2x2(73L),
    /**
     * Summoner's Rift 6x6 Hexakill games
     */
    SR_6x6(75L),
    /**
     * Ultra Rapid Fire games
     */
    URF_5x5(76L),
    /**
     * Ultra Rapid Fire games played against AI games
     */
    BOT_URF_5x5(83L),
    /**
     * Doom Bots Rank 1 games
     */
    NIGHTMARE_BOT_5x5_RANK1(91L),
    /**
     * Doom Bots Rank 2 games
     */
    NIGHTMARE_BOT_5x5_RANK2(92L),
    /**
     * Doom Bots Rank 5 games
     */
    NIGHTMARE_BOT_5x5_RANK5(93L),
    /**
     * Ascension games
     */
    ASCENSION_5x5(96L),
    /**
     * Twisted Treeline 6x6 Hexakill games
     */
    HEXAKILL(98L),
    /**
     * Butcher's Bridge games
     */
    BILGEWATER_ARAM_5x5(100L),
    /**
     * King Poro games
     */
    KING_PORO_5x5(300L),
    /**
     * Nemesis games
     */
    COUNTER_PICK(310L),
    /**
     * Black Market Brawlers games
     */
    BILGEWATER_5x5(313L);

    public static GameQueueType getFromCode(final Long gameQueueConfigId)
    {
        return Stream.of(GameQueueType.values()).filter(t -> t.code.equals(gameQueueConfigId)).findFirst().get();
    }

    Long code;

    GameQueueType(final Long code)
    {
        this.code = code;
    }

    public Long getCode()
    {
        return this.code;
    }

    public static GameQueueType getFromCode(String queueType)
    {
        return Stream.of(GameQueueType.values()).filter(t -> t.name().equalsIgnoreCase(queueType)).findFirst().get();
    }
}
