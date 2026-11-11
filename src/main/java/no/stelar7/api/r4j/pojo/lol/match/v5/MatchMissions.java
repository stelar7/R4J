package no.stelar7.api.r4j.pojo.lol.match.v5;

import java.io.Serializable;
import java.util.*;

/**
 * The mission scores of a participant. What each score means depends on the game mode.
 */
public class MatchMissions implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Double playerScore0;
    private Double playerScore1;
    private Double playerScore2;
    private Double playerScore3;
    private Double playerScore4;
    private Double playerScore5;
    private Double playerScore6;
    private Double playerScore7;
    private Double playerScore8;
    private Double playerScore9;
    private Double playerScore10;
    private Double playerScore11;

    public Double getPlayerScore0()
    {
        return playerScore0;
    }
    
    public Double getPlayerScore1()
    {
        return playerScore1;
    }
    
    public Double getPlayerScore2()
    {
        return playerScore2;
    }
    
    public Double getPlayerScore3()
    {
        return playerScore3;
    }
    
    public Double getPlayerScore4()
    {
        return playerScore4;
    }
    
    public Double getPlayerScore5()
    {
        return playerScore5;
    }
    
    public Double getPlayerScore6()
    {
        return playerScore6;
    }
    
    public Double getPlayerScore7()
    {
        return playerScore7;
    }
    
    public Double getPlayerScore8()
    {
        return playerScore8;
    }
    
    public Double getPlayerScore9()
    {
        return playerScore9;
    }
    
    public Double getPlayerScore10()
    {
        return playerScore10;
    }
    
    public Double getPlayerScore11()
    {
        return playerScore11;
    }

    /**
     * @param index 0 to 11
     * @return the score, or null if the api did not send it
     */
    public Double getPlayerScore(int index)
    {
        Double[] scores = {playerScore0, playerScore1, playerScore2, playerScore3, playerScore4, playerScore5, playerScore6, playerScore7, playerScore8, playerScore9, playerScore10, playerScore11};
        return scores[index];
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
        MatchMissions that = (MatchMissions) o;
        return Objects.equals(playerScore0, that.playerScore0) &&
               Objects.equals(playerScore1, that.playerScore1) &&
               Objects.equals(playerScore2, that.playerScore2) &&
               Objects.equals(playerScore3, that.playerScore3) &&
               Objects.equals(playerScore4, that.playerScore4) &&
               Objects.equals(playerScore5, that.playerScore5) &&
               Objects.equals(playerScore6, that.playerScore6) &&
               Objects.equals(playerScore7, that.playerScore7) &&
               Objects.equals(playerScore8, that.playerScore8) &&
               Objects.equals(playerScore9, that.playerScore9) &&
               Objects.equals(playerScore10, that.playerScore10) &&
               Objects.equals(playerScore11, that.playerScore11);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(playerScore0, playerScore1, playerScore2, playerScore3, playerScore4, playerScore5, playerScore6, playerScore7, playerScore8, playerScore9, playerScore10, playerScore11);
    }

    @Override
    public String toString()
    {
        return "MatchMissions{" +
               "playerScore0=" + playerScore0 +
               ", playerScore1=" + playerScore1 +
               ", playerScore2=" + playerScore2 +
               ", playerScore3=" + playerScore3 +
               ", playerScore4=" + playerScore4 +
               ", playerScore5=" + playerScore5 +
               ", playerScore6=" + playerScore6 +
               ", playerScore7=" + playerScore7 +
               ", playerScore8=" + playerScore8 +
               ", playerScore9=" + playerScore9 +
               ", playerScore10=" + playerScore10 +
               ", playerScore11=" + playerScore11 +
               '}';
    }
}
