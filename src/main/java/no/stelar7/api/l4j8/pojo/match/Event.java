package no.stelar7.api.l4j8.pojo.match;

import java.util.List;

import no.stelar7.api.l4j8.basic.constants.AscencionType;
import no.stelar7.api.l4j8.basic.constants.BuildingType;
import no.stelar7.api.l4j8.basic.constants.EventType;
import no.stelar7.api.l4j8.basic.constants.Lane;
import no.stelar7.api.l4j8.basic.constants.LevelUpType;
import no.stelar7.api.l4j8.basic.constants.MonsterType;
import no.stelar7.api.l4j8.basic.constants.Point;
import no.stelar7.api.l4j8.basic.constants.TowerType;
import no.stelar7.api.l4j8.basic.constants.WardType;

public class Event
{
    private String        ascendedType;
    private List<Integer> assistingParticipantIds;
    private String        buildingType;
    private Integer       creatorId;
    private String        eventType;
    private Integer       itemAfter;
    private Integer       itemBefore;
    private Integer       itemId;
    private Integer       killerId;
    private String        laneType;
    private String        levelUpType;
    private String        monsterType;
    private Integer       participantId;
    private String        pointCaptured;
    private Position      position;
    private Integer       skillSlot;
    private Integer       teamId;
    private Long          timestamp;
    private String        towerType;
    private Integer       victimId;
    private String        wardType;

    /**
     * The ascended type of the event. Only present if relevant. Note that CLEAR_ASCENDED refers to when a participants kills the ascended player. (Legal values: CHAMPION_ASCENDED, CLEAR_ASCENDED, MINION_ASCENDED)
     *
     * @return String
     */
    public String getAscendedTypeId()
    {
        return ascendedType;
    }

    /**
     * The ascended type of the event as AscencionType
     * 
     * @return AscencionType
     */
    public AscencionType getAscendedType()
    {
        return AscencionType.getFromCode(ascendedType);
    }

    /**
     * The assisting participant IDs of the event. Only present if relevant.
     *
     * @return List<Integer>
     */
    public List<Integer> getAssistingParticipantIds()
    {
        return assistingParticipantIds;
    }

    /**
     * The building type of the event. Only present if relevant. (Legal values: INHIBITOR_BUILDING, TOWER_BUILDING)
     *
     * @return the building type
     */
    public String getBuildingTypeId()
    {
        return buildingType;
    }

    /**
     * The building type as a BuildingType
     * 
     * @return BuildingType
     */
    public BuildingType getBuildingType()
    {
        return BuildingType.getFromCode(buildingType);
    }

    /**
     * The creator ID of the event. Only present if relevant.
     *
     * @return Integer
     */
    public Integer getCreatorId()
    {
        return creatorId;
    }

    /**
     * Event type. (Legal values: ASCENDED_EVENT, BUILDING_KILL, CAPTURE_POINT, CHAMPION_KILL, ELITE_MONSTER_KILL, ITEM_DESTROYED, ITEM_PURCHASED, ITEM_SOLD, ITEM_UNDO, PORO_KING_SUMMON, SKILL_LEVEL_UP, WARD_KILL, WARD_PLACED)
     *
     * @return String
     */
    public String getEventTypeId()
    {
        return eventType;
    }

    /**
     * The event type as EventType
     * 
     * @return Event
     */
    public EventType getEventType()
    {
        return EventType.getFromCode(eventType);
    }

    /**
     * The ending item ID of the event. Only present if relevant.
     *
     * @return Integer
     */
    public Integer getItemAfter()
    {
        return itemAfter;
    }

    /**
     * The starting item ID of the event. Only present if relevant.
     *
     * @return Integer
     */
    public Integer getItemBefore()
    {
        return itemBefore;
    }

    /**
     * The item ID of the event. Only present if relevant.
     *
     * @return Integer
     */
    public Integer getItemId()
    {
        return itemId;
    }

    /**
     * The killer ID of the event. Only present if relevant. Killer ID 0 indicates a minion.
     *
     * @return Integer
     */
    public Integer getKillerId()
    {
        return killerId;
    }

    /**
     * The lane type of the event. Only present if relevant. (Legal values: BOT_LANE, MID_LANE, TOP_LANE)
     *
     * @return the lane type
     */
    public String getLaneType()
    {
        return laneType;
    }

    /**
     * The lane inferred from the type
     * 
     * @return Lane
     */
    public Lane getLane()
    {
        return Lane.getFromCode(laneType);
    }

    /**
     * The level up type of the event. Only present if relevant. (Legal values: EVOLVE, NORMAL)
     *
     * 
     * @return String
     */
    public String getLevelUpTypeId()
    {
        return levelUpType;
    }

    /**
     * The LevelUpType inferred from the id
     * 
     * @return LevelUpType
     */
    public LevelUpType getLevelUpType()
    {
        return LevelUpType.getFromCode(levelUpType);
    }

    /**
     * The monster type of the event. Only present if relevant. (Legal values: BARON_NASHOR, BLUE_GOLEM, DRAGON, RED_LIZARD, VILEMAW)
     *
     * @return String
     */
    public String getMonsterTypeId()
    {
        return monsterType;
    }

    /**
     * The MonsterType inferred from the id
     * 
     * @return MonsterType
     */
    public MonsterType getMonsterType()
    {
        return MonsterType.getFromCode(monsterType);
    }

    /**
     * The participant ID of the event. Only present if relevant.
     *
     * @return Integer
     */
    public Integer getParticipantId()
    {
        return participantId;
    }

    /**
     * The point captured in the event. Only present if relevant. (Legal values: POINT_A, POINT_B, POINT_C, POINT_D, POINT_E)
     *
     * @return String
     */
    public String getPointCapturedId()
    {
        return pointCaptured;
    }

    /**
     * The point captured inferred from the id
     * 
     * @return Point
     */
    public Point getPointCaptured()
    {
        return Point.getFromCode(pointCaptured);
    }

    /**
     * The position of the event. Only present if relevant.
     *
     * @return Position
     */
    public Position getPosition()
    {
        return position;
    }

    /**
     * The skill slot of the event. Only present if relevant.
     *
     * @return Integer
     */
    public Integer getSkillSlot()
    {
        return skillSlot;
    }

    /**
     * The team ID of the event. Only present if relevant.
     *
     * @return Integer
     */
    public Integer getTeamId()
    {
        return teamId;
    }

    /**
     * Represents how many milliseconds into the game the event occurred.
     *
     * @return Long
     */
    public Long getTimestamp()
    {
        return timestamp;
    }

    /**
     * The tower type of the event. Only present if relevant. (Legal values: BASE_TURRET, FOUNTAIN_TURRET, INNER_TURRET, NEXUS_TURRET, OUTER_TURRET, UNDEFINED_TURRET)
     *
     * @return String
     */
    public String getTowerTypeId()
    {
        return towerType;
    }

    /**
     * The tower type inferred from the id
     * 
     * @return TowerType
     */
    public TowerType getTowerType()
    {
        return TowerType.getFromCode(towerType);
    }

    /**
     * The victim ID of the event. Only present if relevant.
     *
     * @return Integer
     */
    public Integer getVictimId()
    {
        return victimId;
    }

    /**
     * The ward type of the event. Only present if relevant. (Legal values: SIGHT_WARD, TEEMO_MUSHROOM, UNDEFINED, VISION_WARD, YELLOW_TRINKET, YELLOW_TRINKET_UPGRADE)
     *
     * @return String
     */
    public String getWardTypeId()
    {
        return wardType;
    }

    /**
     * The ward type inferred from the id
     * 
     * @return WardType
     */
    public WardType getWardType()
    {
        return WardType.getFromCode(wardType);
    }
}
