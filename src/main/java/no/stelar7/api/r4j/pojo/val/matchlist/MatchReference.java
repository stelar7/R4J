package no.stelar7.api.r4j.pojo.val.matchlist;

import no.stelar7.api.r4j.basic.constants.types.val.GameQueueType;

import java.io.Serializable;
import java.util.Objects;

public class MatchReference implements Serializable
{
    private static final long serialVersionUID = -2998111548318274081L;

    private String matchId;
    private Long   gameStartTimeMillis;
    private GameQueueType queueId;

    public String getMatchId()
    {
        return matchId;
    }

    public Long getGameStartTimeMillis()
    {
        return gameStartTimeMillis;
    }

    public GameQueueType getQueueId()
    {
        return queueId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        MatchReference match = (MatchReference) o;
        return Objects.equals(matchId, match.matchId) &&
               Objects.equals(gameStartTimeMillis, match.gameStartTimeMillis) &&
               Objects.equals(queueId, match.queueId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(matchId, gameStartTimeMillis, queueId);
    }

    @Override
    public String toString()
    {
        return "Match{" +
               "matchId='" + matchId + '\'' +
               ", gameStartTimeMillis=" + gameStartTimeMillis +
               ", queueId='" + queueId + '\'' +
               '}';
    }
}
