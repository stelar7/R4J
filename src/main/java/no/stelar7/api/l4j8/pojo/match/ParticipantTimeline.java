package no.stelar7.api.l4j8.pojo.match;

import no.stelar7.api.l4j8.basic.constants.Lane;
import no.stelar7.api.l4j8.basic.constants.Role;

public class ParticipantTimeline
{

    private ParticipantTimelineData ancientGolemAssistsPerMinCounts;
    private ParticipantTimelineData ancientGolemKillsPerMinCounts;
    private ParticipantTimelineData assistedLaneDeathsPerMinDeltas;
    private ParticipantTimelineData assistedLaneKillsPerMinDeltas;
    private ParticipantTimelineData baronAssistsPerMinCounts;
    private ParticipantTimelineData baronKillsPerMinCounts;
    private ParticipantTimelineData creepsPerMinDeltas;
    private ParticipantTimelineData csDiffPerMinDeltas;
    private ParticipantTimelineData damageTakenDiffPerMinDeltas;
    private ParticipantTimelineData damageTakenPerMinDeltas;
    private ParticipantTimelineData dragonAssistsPerMinCounts;
    private ParticipantTimelineData dragonKillsPerMinCounts;
    private ParticipantTimelineData elderLizardAssistsPerMinCounts;
    private ParticipantTimelineData elderLizardKillsPerMinCounts;
    private ParticipantTimelineData goldPerMinDeltas;
    private ParticipantTimelineData inhibitorAssistsPerMinCounts;
    private ParticipantTimelineData inhibitorKillsPerMinCounts;
    private ParticipantTimelineData towerAssistsPerMinCounts;
    private ParticipantTimelineData towerKillsPerMinCounts;
    private ParticipantTimelineData towerKillsPerMinDeltas;
    private ParticipantTimelineData vilemawAssistsPerMinCounts;
    private ParticipantTimelineData vilemawKillsPerMinCounts;
    private ParticipantTimelineData wardsPerMinDeltas;
    private ParticipantTimelineData xpDiffPerMinDeltas;
    private ParticipantTimelineData xpPerMinDeltas;
    private String                  lane;
    private String                  role;

    /**
     * Gets the ancient golem assists per min counts.
     *
     * @return the ancient golem assists per min counts
     */
    public ParticipantTimelineData getAncientGolemAssistsPerMinCounts()
    {
        return ancientGolemAssistsPerMinCounts;
    }

    /**
     * Gets the ancient golem kills per min counts.
     *
     * @return the ancient golem kills per min counts
     */
    public ParticipantTimelineData getAncientGolemKillsPerMinCounts()
    {
        return ancientGolemKillsPerMinCounts;
    }

    /**
     * Gets the assisted lane deaths per min deltas.
     *
     * @return the assisted lane deaths per min deltas
     */
    public ParticipantTimelineData getAssistedLaneDeathsPerMinDeltas()
    {
        return assistedLaneDeathsPerMinDeltas;
    }

    /**
     * Gets the assisted lane kills per min deltas.
     *
     * @return the assisted lane kills per min deltas
     */
    public ParticipantTimelineData getAssistedLaneKillsPerMinDeltas()
    {
        return assistedLaneKillsPerMinDeltas;
    }

    /**
     * Gets the baron assists per min counts.
     *
     * @return the baron assists per min counts
     */
    public ParticipantTimelineData getBaronAssistsPerMinCounts()
    {
        return baronAssistsPerMinCounts;
    }

    /**
     * Gets the baron kills per min counts.
     *
     * @return the baron kills per min counts
     */
    public ParticipantTimelineData getBaronKillsPerMinCounts()
    {
        return baronKillsPerMinCounts;
    }

    /**
     * Gets the creeps per min deltas.
     *
     * @return the creeps per min deltas
     */
    public ParticipantTimelineData getCreepsPerMinDeltas()
    {
        return creepsPerMinDeltas;
    }

    /**
     * Gets the cs diff per min deltas.
     *
     * @return the cs diff per min deltas
     */
    public ParticipantTimelineData getCsDiffPerMinDeltas()
    {
        return csDiffPerMinDeltas;
    }

    /**
     * Gets the damage taken diff per min deltas.
     *
     * @return the damage taken diff per min deltas
     */
    public ParticipantTimelineData getDamageTakenDiffPerMinDeltas()
    {
        return damageTakenDiffPerMinDeltas;
    }

    /**
     * Gets the damage taken per min deltas.
     *
     * @return the damage taken per min deltas
     */
    public ParticipantTimelineData getDamageTakenPerMinDeltas()
    {
        return damageTakenPerMinDeltas;
    }

    /**
     * Gets the dragon assists per min counts.
     *
     * @return the dragon assists per min counts
     */
    public ParticipantTimelineData getDragonAssistsPerMinCounts()
    {
        return dragonAssistsPerMinCounts;
    }

    /**
     * Gets the dragon kills per min counts.
     *
     * @return the dragon kills per min counts
     */
    public ParticipantTimelineData getDragonKillsPerMinCounts()
    {
        return dragonKillsPerMinCounts;
    }

    /**
     * Gets the elder lizard assists per min counts.
     *
     * @return the elder lizard assists per min counts
     */
    public ParticipantTimelineData getElderLizardAssistsPerMinCounts()
    {
        return elderLizardAssistsPerMinCounts;
    }

    /**
     * Gets the elder lizard kills per min counts.
     *
     * @return the elder lizard kills per min counts
     */
    public ParticipantTimelineData getElderLizardKillsPerMinCounts()
    {
        return elderLizardKillsPerMinCounts;
    }

    /**
     * Gets the gold per min deltas.
     *
     * @return the gold per min deltas
     */
    public ParticipantTimelineData getGoldPerMinDeltas()
    {
        return goldPerMinDeltas;
    }

    /**
     * Gets the inhibitor assists per min counts.
     *
     * @return the inhibitor assists per min counts
     */
    public ParticipantTimelineData getInhibitorAssistsPerMinCounts()
    {
        return inhibitorAssistsPerMinCounts;
    }

    /**
     * Gets the inhibitor kills per min counts.
     *
     * @return the inhibitor kills per min counts
     */
    public ParticipantTimelineData getInhibitorKillsPerMinCounts()
    {
        return inhibitorKillsPerMinCounts;
    }

    /**
     * Gets the lane.
     *
     * @return the lane
     */
    public String getLaneId()
    {
        return lane;
    }

    /**
     * Gets the lane.
     *
     * @return the lane
     */
    public Lane getLane()
    {
        return Lane.getFromCode(lane);
    }

    /**
     * Gets the role.
     *
     * @return the role
     */
    public String getRoleId()
    {
        return role;
    }

    /**
     * Gets the role.
     *
     * @return the role
     */
    public Role getRole()
    {
        return Role.getFromCode(role);
    }

    /**
     * Gets the tower assists per min counts.
     *
     * @return the tower assists per min counts
     */
    public ParticipantTimelineData getTowerAssistsPerMinCounts()
    {
        return towerAssistsPerMinCounts;
    }

    /**
     * Gets the tower kills per min counts.
     *
     * @return the tower kills per min counts
     */
    public ParticipantTimelineData getTowerKillsPerMinCounts()
    {
        return towerKillsPerMinCounts;
    }

    /**
     * Gets the tower kills per min deltas.
     *
     * @return the tower kills per min deltas
     */
    public ParticipantTimelineData getTowerKillsPerMinDeltas()
    {
        return towerKillsPerMinDeltas;
    }

    /**
     * Gets the vilemaw assists per min counts.
     *
     * @return the vilemaw assists per min counts
     */
    public ParticipantTimelineData getVilemawAssistsPerMinCounts()
    {
        return vilemawAssistsPerMinCounts;
    }

    /**
     * Gets the vilemaw kills per min counts.
     *
     * @return the vilemaw kills per min counts
     */
    public ParticipantTimelineData getVilemawKillsPerMinCounts()
    {
        return vilemawKillsPerMinCounts;
    }

    /**
     * Gets the wards per min deltas.
     *
     * @return the wards per min deltas
     */
    public ParticipantTimelineData getWardsPerMinDeltas()
    {
        return wardsPerMinDeltas;
    }

    /**
     * Gets the xp diff per min deltas.
     *
     * @return the xp diff per min deltas
     */
    public ParticipantTimelineData getXpDiffPerMinDeltas()
    {
        return xpDiffPerMinDeltas;
    }

    /**
     * Gets the xp per min deltas.
     *
     * @return the xp per min deltas
     */
    public ParticipantTimelineData getXpPerMinDeltas()
    {
        return xpPerMinDeltas;
    }

}
