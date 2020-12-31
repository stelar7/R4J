package no.stelar7.api.r4j.pojo.val.match;

import no.stelar7.api.r4j.basic.constants.types.val.*;
import no.stelar7.api.r4j.basic.utils.sql.*;

import java.io.Serializable;
import java.util.*;

public class RoundResult implements Serializable
{
    private static final long serialVersionUID = -7840217641700030054L;
    
    private int                    roundNum;
    private String                 roundResult;
    private RoundResultType        roundCeremony;
    private TeamType               winningTeam;
    private String                 bombPlanter;
    private String                 bombDefuser;
    private int                    plantRoundTime;
    private List<PlayerLocation>   plantPlayerLocations;
    private Location               plantLocation;
    private PlantSiteType          plantSite;
    private int                    defuseRoundTime;
    private List<PlayerLocation>   defusePlayerLocations;
    private Location               defuseLocation;
    private List<PlayerRoundStats> playerStats;
    private RoundResultCodeType    roundResultCode;
    
    public int getRoundNum()
    {
        return roundNum;
    }
    
    public String getRoundResult()
    {
        return roundResult;
    }
    
    public RoundResultType getRoundCeremony()
    {
        return roundCeremony;
    }
    
    public TeamType getWinningTeam()
    {
        return winningTeam;
    }
    
    public String getBombPlanter()
    {
        return bombPlanter;
    }
    
    public String getBombDefuser()
    {
        return bombDefuser;
    }
    
    public int getPlantRoundTime()
    {
        return plantRoundTime;
    }
    
    public List<PlayerLocation> getPlantPlayerLocations()
    {
        return plantPlayerLocations;
    }
    
    public Location getPlantLocation()
    {
        return plantLocation;
    }
    
    public PlantSiteType getPlantSite()
    {
        return plantSite;
    }
    
    public int getDefuseRoundTime()
    {
        return defuseRoundTime;
    }
    
    public List<PlayerLocation> getDefusePlayerLocations()
    {
        return defusePlayerLocations;
    }
    
    public Location getDefuseLocation()
    {
        return defuseLocation;
    }
    
    public List<PlayerRoundStats> getPlayerStats()
    {
        return playerStats;
    }
    
    public RoundResultCodeType getRoundResultCode()
    {
        return roundResultCode;
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
        RoundResult that = (RoundResult) o;
        return Objects.equals(roundNum, that.roundNum) &&
               Objects.equals(roundResult, that.roundResult) &&
               Objects.equals(roundCeremony, that.roundCeremony) &&
               Objects.equals(winningTeam, that.winningTeam) &&
               Objects.equals(bombPlanter, that.bombPlanter) &&
               Objects.equals(bombDefuser, that.bombDefuser) &&
               Objects.equals(plantRoundTime, that.plantRoundTime) &&
               Objects.equals(plantPlayerLocations, that.plantPlayerLocations) &&
               Objects.equals(plantLocation, that.plantLocation) &&
               Objects.equals(plantSite, that.plantSite) &&
               Objects.equals(defuseRoundTime, that.defuseRoundTime) &&
               Objects.equals(defusePlayerLocations, that.defusePlayerLocations) &&
               Objects.equals(defuseLocation, that.defuseLocation) &&
               Objects.equals(playerStats, that.playerStats) &&
               Objects.equals(roundResultCode, that.roundResultCode);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(roundNum, roundResult, roundCeremony, winningTeam, bombPlanter, bombDefuser, plantRoundTime, plantPlayerLocations, plantLocation, plantSite, defuseRoundTime, defusePlayerLocations, defuseLocation, playerStats, roundResultCode);
    }
    
    @Override
    public String toString()
    {
        return "RoundResult{" +
               "roundNum=" + roundNum +
               ", roundResult='" + roundResult + '\'' +
               ", roundCeremony='" + roundCeremony + '\'' +
               ", winningTeam='" + winningTeam + '\'' +
               ", bombPlanter='" + bombPlanter + '\'' +
               ", bombDefuser='" + bombDefuser + '\'' +
               ", plantRoundTime=" + plantRoundTime +
               ", plantPlayerLocations=" + plantPlayerLocations +
               ", plantLocation=" + plantLocation +
               ", plantSite='" + plantSite + '\'' +
               ", defuseRoundTime=" + defuseRoundTime +
               ", defusePlayerLocations=" + defusePlayerLocations +
               ", defuseLocation=" + defuseLocation +
               ", playerStats=" + playerStats +
               ", roundResultCode='" + roundResultCode + '\'' +
               '}';
    }
    
    @SQLTypeMap
    private static Map<String, String> getTypeMap()
    {
        Map<String, String> returnMap = new HashMap<>();
        
        returnMap.put("roundNum", "int8");
        returnMap.put("roundResult", "text");
        returnMap.put("roundCeremony", "text");
        returnMap.put("winningTeam", "text");
        returnMap.put("bombPlanter", "text");
        returnMap.put("bombDefuser", "text");
        returnMap.put("plantRoundTime", "int32");
        returnMap.put("plantSite", "text");
        returnMap.put("defuseRoundTime", "int32");
        returnMap.put("roundResultCode", "text");
        
        return returnMap;
    }
    
    @SQLExtraMap
    private static Map<String, String> getExtraMap()
    {
        Map<String, String> returnMap = new HashMap<>();
        
        return returnMap;
    }
}
