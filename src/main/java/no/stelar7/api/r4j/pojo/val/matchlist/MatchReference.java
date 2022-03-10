package no.stelar7.api.r4j.pojo.val.matchlist;

import java.io.Serializable;
import java.util.Objects;

public class MatchReference implements Serializable
{
    private static final long serialVersionUID = -5301457261872587385L;

    private String matchId;
    private Long   gameStartTimeMillis;
    private String queueId;

    public String getMatchId()
    {
        return matchId;
    }

    public Long getGameStartTimeMillis()
    {
        return gameStartTimeMillis;
    }

    public String getQueueId()
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
