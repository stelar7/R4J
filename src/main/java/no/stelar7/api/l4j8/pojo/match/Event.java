package no.stelar7.api.l4j8.pojo.match;

import no.stelar7.api.l4j8.basic.constants.types.*;

import java.util.List;

public class Event
{
    private AscencionType ascendedType;
    private List<Integer> assistingParticipantIds;
    private BuildingType  buildingType;
    private Integer       creatorId;
    private EventType     eventType;
    private Integer       itemAfter;
    private Integer       itemBefore;
    private Integer       itemId;
    private Integer       killerId;
    private LaneType      laneType;
    private LevelUpType   levelUpType;
    private MonsterType   monsterType;
    private Integer       participantId;
    private PointType     pointCaptured;
    private Position      position;
    private Integer       skillSlot;
    private TeamType      teamId;
    private Long          timestamp;
    private TowerType     towerType;
    private Integer       victimId;
    private WardType      wardType;
    
    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (this.getClass() != obj.getClass())
        {
            return false;
        }
        final Event other = (Event) obj;
        if (this.ascendedType == null)
        {
            if (other.ascendedType != null)
            {
                return false;
            }
        } else if (!this.ascendedType.equals(other.ascendedType))
        {
            return false;
        }
        if (this.assistingParticipantIds == null)
        {
            if (other.assistingParticipantIds != null)
            {
                return false;
            }
        } else if (!this.assistingParticipantIds.equals(other.assistingParticipantIds))
        {
            return false;
        }
        if (this.buildingType == null)
        {
            if (other.buildingType != null)
            {
                return false;
            }
        } else if (!this.buildingType.equals(other.buildingType))
        {
            return false;
        }
        if (this.creatorId == null)
        {
            if (other.creatorId != null)
            {
                return false;
            }
        } else if (!this.creatorId.equals(other.creatorId))
        {
            return false;
        }
        if (this.eventType == null)
        {
            if (other.eventType != null)
            {
                return false;
            }
        } else if (!this.eventType.equals(other.eventType))
        {
            return false;
        }
        if (this.itemAfter == null)
        {
            if (other.itemAfter != null)
            {
                return false;
            }
        } else if (!this.itemAfter.equals(other.itemAfter))
        {
            return false;
        }
        if (this.itemBefore == null)
        {
            if (other.itemBefore != null)
            {
                return false;
            }
        } else if (!this.itemBefore.equals(other.itemBefore))
        {
            return false;
        }
        if (this.itemId == null)
        {
            if (other.itemId != null)
            {
                return false;
            }
        } else if (!this.itemId.equals(other.itemId))
        {
            return false;
        }
        if (this.killerId == null)
        {
            if (other.killerId != null)
            {
                return false;
            }
        } else if (!this.killerId.equals(other.killerId))
        {
            return false;
        }
        if (this.laneType == null)
        {
            if (other.laneType != null)
            {
                return false;
            }
        } else if (!this.laneType.equals(other.laneType))
        {
            return false;
        }
        if (this.levelUpType == null)
        {
            if (other.levelUpType != null)
            {
                return false;
            }
        } else if (!this.levelUpType.equals(other.levelUpType))
        {
            return false;
        }
        if (this.monsterType == null)
        {
            if (other.monsterType != null)
            {
                return false;
            }
        } else if (!this.monsterType.equals(other.monsterType))
        {
            return false;
        }
        if (this.participantId == null)
        {
            if (other.participantId != null)
            {
                return false;
            }
        } else if (!this.participantId.equals(other.participantId))
        {
            return false;
        }
        if (this.pointCaptured == null)
        {
            if (other.pointCaptured != null)
            {
                return false;
            }
        } else if (!this.pointCaptured.equals(other.pointCaptured))
        {
            return false;
        }
        if (this.position == null)
        {
            if (other.position != null)
            {
                return false;
            }
        } else if (!this.position.equals(other.position))
        {
            return false;
        }
        if (this.skillSlot == null)
        {
            if (other.skillSlot != null)
            {
                return false;
            }
        } else if (!this.skillSlot.equals(other.skillSlot))
        {
            return false;
        }
        if (this.teamId == null)
        {
            if (other.teamId != null)
            {
                return false;
            }
        } else if (!this.teamId.equals(other.teamId))
        {
            return false;
        }
        if (this.timestamp == null)
        {
            if (other.timestamp != null)
            {
                return false;
            }
        } else if (!this.timestamp.equals(other.timestamp))
        {
            return false;
        }
        if (this.towerType == null)
        {
            if (other.towerType != null)
            {
                return false;
            }
        } else if (!this.towerType.equals(other.towerType))
        {
            return false;
        }
        if (this.victimId == null)
        {
            if (other.victimId != null)
            {
                return false;
            }
        } else if (!this.victimId.equals(other.victimId))
        {
            return false;
        }
        if (this.wardType == null)
        {
            if (other.wardType != null)
            {
                return false;
            }
        } else if (!this.wardType.equals(other.wardType))
        {
            return false;
        }
        return true;
    }
    
    /**
     * The ascended type of the event as AscencionType
     *
     * @return AscencionType
     */
    public AscencionType getAscendedType()
    {
        return ascendedType;
    }
    
    /**
     * The assisting participant IDs of the event. Only present if relevant.
     *
     * @return {@code List<Integer>}
     */
    public List<Integer> getAssistingParticipantIds()
    {
        return this.assistingParticipantIds;
    }
    
    
    /**
     * The building type of the event. Only present if relevant. (Legal values: INHIBITOR_BUILDING, TOWER_BUILDING)
     *
     * @return the building type
     */
    public BuildingType getBuildingType()
    {
        return this.buildingType;
    }
    
    /**
     * The creator ID of the event. Only present if relevant.
     *
     * @return Integer
     */
    public Integer getCreatorId()
    {
        return this.creatorId;
    }
    
    
    /**
     * Event type. (Legal values: ASCENDED_EVENT, BUILDING_KILL, CAPTURE_POINT, CHAMPION_KILL, ELITE_MONSTER_KILL, ITEM_DESTROYED, ITEM_PURCHASED, ITEM_SOLD, ITEM_UNDO, PORO_KING_SUMMON, SKILL_LEVEL_UP, WARD_KILL, WARD_PLACED)
     *
     * @return String
     */
    public EventType getEventType()
    {
        return this.eventType;
    }
    
    /**
     * The ending item ID of the event. Only present if relevant.
     *
     * @return Integer
     */
    public Integer getItemAfter()
    {
        return this.itemAfter;
    }
    
    /**
     * The starting item ID of the event. Only present if relevant.
     *
     * @return Integer
     */
    public Integer getItemBefore()
    {
        return this.itemBefore;
    }
    
    /**
     * The item ID of the event. Only present if relevant.
     *
     * @return Integer
     */
    public Integer getItemId()
    {
        return this.itemId;
    }
    
    /**
     * The killer ID of the event. Only present if relevant. Killer ID 0 indicates a minion.
     *
     * @return Integer
     */
    public Integer getKillerId()
    {
        return this.killerId;
    }
    
    /**
     * The lane type of the event. Only present if relevant. (Legal values: BOT_LANE, MID_LANE, TOP_LANE)
     *
     * @return the lane type
     */
    public LaneType getLaneType()
    {
        return this.laneType;
    }
    
    /**
     * The level up type of the event. Only present if relevant. (Legal values: EVOLVE, NORMAL)
     *
     * @return String
     */
    public LevelUpType getLevelUpType()
    {
        return this.levelUpType;
    }
    
    /**
     * The monster type of the event. Only present if relevant. (Legal values: BARON_NASHOR, BLUE_GOLEM, DRAGON, RED_LIZARD, VILEMAW)
     *
     * @return String
     */
    public MonsterType getMonsterType()
    {
        return this.monsterType;
    }
    
    /**
     * The participant ID of the event. Only present if relevant.
     *
     * @return Integer
     */
    public Integer getParticipantId()
    {
        return this.participantId;
    }
    
    /**
     * The point captured in the event. Only present if relevant. (Legal values: POINT_A, POINT_B, POINT_C, POINT_D, POINT_E)
     *
     * @return String
     */
    public PointType getPointCaptured()
    {
        return this.pointCaptured;
    }
    
    /**
     * The position of the event. Only present if relevant.
     *
     * @return Position
     */
    public Position getPosition()
    {
        return this.position;
    }
    
    /**
     * The skill slot of the event. Only present if relevant.
     *
     * @return Integer
     */
    public Integer getSkillSlot()
    {
        return this.skillSlot;
    }
    
    /**
     * The team ID of the event. Only present if relevant.
     *
     * @return Integer
     */
    public TeamType getTeam()
    {
        return this.teamId;
    }
    
    /**
     * Represents how many milliseconds into the game the event occurred.
     *
     * @return Long
     */
    public Long getTimestamp()
    {
        return this.timestamp;
    }
    
    /**
     * The tower type of the event. Only present if relevant. (Legal values: BASE_TURRET, FOUNTAIN_TURRET, INNER_TURRET, NEXUS_TURRET, OUTER_TURRET, UNDEFINED_TURRET)
     *
     * @return String
     */
    public TowerType getTowerType()
    {
        return this.towerType;
    }
    
    /**
     * The victim ID of the event. Only present if relevant.
     *
     * @return Integer
     */
    public Integer getVictimId()
    {
        return this.victimId;
    }
    
    /**
     * The ward type of the event. Only present if relevant. (Legal values: SIGHT_WARD, TEEMO_MUSHROOM, UNDEFINED, VISION_WARD, YELLOW_TRINKET, YELLOW_TRINKET_UPGRADE)
     *
     * @return String
     */
    public WardType getWardType()
    {
        return this.wardType;
    }
    
    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.ascendedType == null) ? 0 : this.ascendedType.hashCode());
        result = (prime * result) + ((this.assistingParticipantIds == null) ? 0 : this.assistingParticipantIds.hashCode());
        result = (prime * result) + ((this.buildingType == null) ? 0 : this.buildingType.hashCode());
        result = (prime * result) + ((this.creatorId == null) ? 0 : this.creatorId.hashCode());
        result = (prime * result) + ((this.eventType == null) ? 0 : this.eventType.hashCode());
        result = (prime * result) + ((this.itemAfter == null) ? 0 : this.itemAfter.hashCode());
        result = (prime * result) + ((this.itemBefore == null) ? 0 : this.itemBefore.hashCode());
        result = (prime * result) + ((this.itemId == null) ? 0 : this.itemId.hashCode());
        result = (prime * result) + ((this.killerId == null) ? 0 : this.killerId.hashCode());
        result = (prime * result) + ((this.laneType == null) ? 0 : this.laneType.hashCode());
        result = (prime * result) + ((this.levelUpType == null) ? 0 : this.levelUpType.hashCode());
        result = (prime * result) + ((this.monsterType == null) ? 0 : this.monsterType.hashCode());
        result = (prime * result) + ((this.participantId == null) ? 0 : this.participantId.hashCode());
        result = (prime * result) + ((this.pointCaptured == null) ? 0 : this.pointCaptured.hashCode());
        result = (prime * result) + ((this.position == null) ? 0 : this.position.hashCode());
        result = (prime * result) + ((this.skillSlot == null) ? 0 : this.skillSlot.hashCode());
        result = (prime * result) + ((this.teamId == null) ? 0 : this.teamId.hashCode());
        result = (prime * result) + ((this.timestamp == null) ? 0 : this.timestamp.hashCode());
        result = (prime * result) + ((this.towerType == null) ? 0 : this.towerType.hashCode());
        result = (prime * result) + ((this.victimId == null) ? 0 : this.victimId.hashCode());
        result = (prime * result) + ((this.wardType == null) ? 0 : this.wardType.hashCode());
        return result;
    }
    
    @Override
    public String toString()
    {
        return "Event{" +
               "ascendedType='" + ascendedType + '\'' +
               ", assistingParticipantIds=" + assistingParticipantIds +
               ", buildingType='" + buildingType + '\'' +
               ", creatorId=" + creatorId +
               ", eventType='" + eventType + '\'' +
               ", itemAfter=" + itemAfter +
               ", itemBefore=" + itemBefore +
               ", itemId=" + itemId +
               ", killerId=" + killerId +
               ", laneType='" + laneType + '\'' +
               ", levelUpType='" + levelUpType + '\'' +
               ", monsterType='" + monsterType + '\'' +
               ", participantId=" + participantId +
               ", pointCaptured='" + pointCaptured + '\'' +
               ", position=" + position +
               ", skillSlot=" + skillSlot +
               ", teamId=" + teamId +
               ", timestamp=" + timestamp +
               ", towerType='" + towerType + '\'' +
               ", victimId=" + victimId +
               ", wardType='" + wardType + '\'' +
               '}';
    }
}
