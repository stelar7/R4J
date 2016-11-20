package no.stelar7.api.l4j8.basic.constants;

import java.util.Optional;
import java.util.stream.Stream;

public enum LevelUpType
{
	EVOLVE,
	NORMAL;

	/**
	 * Returns an LevelUpType from the provided code
	 *
	 * @return LevelUpType
	 */
	public static Optional<LevelUpType> getFromCode(final String type)
	{
		return Stream.of(LevelUpType.values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst();
	}

	/**
	 * The code used to map strings to objects
	 *
	 * @return String
	 */
	public String getCode()
	{
		return this.name();
	}
}
