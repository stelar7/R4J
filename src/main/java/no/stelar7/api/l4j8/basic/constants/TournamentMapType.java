package no.stelar7.api.l4j8.basic.constants;

import java.util.Optional;
import java.util.stream.Stream;

public enum TournamentMapType
{
	/**
	 * Summoner's Rift Current Version
	 */
	SUMMONERS_RIFT(11),
	/**
	 * The Crystal Scar Dominion Map
	 * <p>
	 * <p>
	 * Dominion is gone...
	 */
	@Deprecated CRYSTAL_SCAR(8),
	/**
	 * Twisted Treeline Current Version
	 */
	TWISTED_TREELINE(10),
	/**
	 * Howling Abyss ARAM Map
	 */
	HOWLING_ABYSS(12);
	
	public static Optional<Team> getFromCode(final Number teamId)
	{
		return Stream.of(Team.values()).filter(t -> t.code.equals(teamId)).findFirst();
	}
	
	final Integer code;
	
	TournamentMapType(final Integer code)
	{
		this.code = code;
	}
	
	public Integer getCode()
	{
		return this.code;
	}
	
}
