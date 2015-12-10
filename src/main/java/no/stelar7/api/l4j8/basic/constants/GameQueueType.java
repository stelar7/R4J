package no.stelar7.api.l4j8.basic.constants;

import java.util.Optional;
import java.util.stream.Stream;

public enum GameQueueType
{
    /**
     * Custom games
     */
    CUSTOM(0),
    /**
     * Normal 3v3 games
     */
    NORMAL_3x3(8),
    /**
     * Normal 5v5 Blind Pick games
     */
    NORMAL_5x5_BLIND(2),
    /**
     * Normal 5v5 Draft Pick games
     */
    NORMAL_5x5_DRAFT(14),
    /**
     * Ranked Solo 5v5 games
     */
    RANKED_SOLO_5x5(4),
    /**
     * Ranked Premade 5v5 games
     */
    @Deprecated RANKED_PREMADE_5x5(6),
    /**
     * Ranked Premade 3v3 games
     */
    @Deprecated RANKED_PREMADE_3x3(9),
    /**
     * Ranked Team 3v3 games
     */
    RANKED_TEAM_3x3(41),
    /**
     * Ranked Team 5v5 games
     */
    RANKED_TEAM_5x5(42),
    /**
     * Dominion 5v5 Blind Pick games
     */
    ODIN_5x5_BLIND(16),
    /**
     * Dominion 5v5 Draft Pick games
     */
    ODIN_5x5_DRAFT(17),
    /**
     * Historical Summoner's Rift Coop vs AI games
     */
    @Deprecated BOT_5x5(7),
    /**
     * Dominion Coop vs AI games
     */
    BOT_ODIN_5x5(25),
    /**
     * Summoner's Rift Coop vs AI Intro Bot games
     */
    BOT_5x5_INTRO(31),
    /**
     * Summoner's Rift Coop vs AI Beginner Bot games
     */
    BOT_5x5_BEGINNER(32),
    /**
     * Historical Summoner's Rift Coop vs AI Intermediate Bot games
     */
    BOT_5x5_INTERMEDIATE(33),
    /**
     * Twisted Treeline Coop vs AI games
     */
    BOT_TT_3x3(52),
    /**
     * Team Builder games
     */
    GROUP_FINDER_5x5(61),
    /**
     * ARAM games
     */
    ARAM_5x5(65),
    /**
     * One for All games
     */
    ONEFORALL_5x5(70),
    /**
     * Snowdown Showdown 1v1 games
     */
    FIRSTBLOOD_1x1(72),
    /**
     * Snowdown Showdown 2v2 games
     */
    FIRSTBLOOD_2x2(73),
    /**
     * Summoner's Rift 6x6 Hexakill games
     */
    SR_6x6(75),
    /**
     * Ultra Rapid Fire games
     */
    URF_5x5(76),
    /**
     * Ultra Rapid Fire games played against AI games
     */
    BOT_URF_5x5(83),
    /**
     * Doom Bots Rank 1 games
     */
    NIGHTMARE_BOT_5x5_RANK1(91),
    /**
     * Doom Bots Rank 2 games
     */
    NIGHTMARE_BOT_5x5_RANK2(92),
    /**
     * Doom Bots Rank 5 games
     */
    NIGHTMARE_BOT_5x5_RANK5(93),
    /**
     * Ascension games
     */
    ASCENSION_5x5(96),
    /**
     * Twisted Treeline 6x6 Hexakill games
     */
    HEXAKILL(98),
    /**
     * Butcher's Bridge games
     */
    BILGEWATER_ARAM_5x5(100),
    /**
     * King Poro games
     */
    KING_PORO_5x5(300),
    /**
     * Nemesis games
     */
    COUNTER_PICK(310),
    /**
     * Black Market Brawlers games
     */
    BILGEWATER_5x5(313);

    public static Optional<GameQueueType> getFromCode(final Integer gameQueueConfigId)
    {
        return Stream.of(GameQueueType.values()).filter(t -> t.code.equals(gameQueueConfigId)).findFirst();
    }

    public static Optional<GameQueueType> getFromCode(final String queueType)
    {
        return Stream.of(GameQueueType.values()).filter(t -> t.name().equalsIgnoreCase(queueType)).findFirst();
    }

    Integer code;

    GameQueueType(final Integer code)
    {
        this.code = code;
    }

    public Integer getCode()
    {
        return this.code;
    }
}
