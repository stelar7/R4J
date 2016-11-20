package no.stelar7.api.l4j8.basic.constants;

import java.util.Optional;
import java.util.stream.Stream;

public enum SummonerSpell
{
	/**
	 * Removes all disables and summoner spell debuffs affecting your champion and lowers the duration of incoming disables by 65% for 3 seconds.
	 */
	CLEANSE(1),
	/**
	 * After channeling for 3.5 seconds, teleports your champion to target allied structure, minion, or ward.
	 */
	TELEPORT(12),
	/**
	 * Quickly travel to the Poro King's side.
	 */
	TO_THE_KING(30),
	/**
	 * Ignites target enemy champion, dealing 70-410 true damage (depending on champion level) over 5 seconds, grants you vision of the target, and reduces healing effects on them for the duration.
	 */
	IGNITE(14),
	/**
	 * Your champion can move through units and has 27% increased Movement Speed for 10 seconds
	 */
	GHOST(6),
	/**
	 * Throw a snowball in a straight line at your enemies. If it hits an enemy, they become marked and your champion can quickly travel to the marked target as a follow up.
	 */
	MARK(32),
	/**
	 * Restores 90-345 Health (depending on champion level) and grants 30% Movement Speed for 1 second to you and target allied champion. This healing is halved for units recently affected by Summoner Heal.
	 */
	HEAL(7),
	/**
	 * Deals 390-1000 true damage (depending on champion level) to target epic or large monster or enemy minion.
	 */
	SMITE(11),
	/**
	 * Exhausts target enemy champion, reducing their Movement Speed and Attack Speed by 30%, their Armor and Magic Resist by 10, and their damage dealt by 40% for 2.5 seconds.
	 */
	EXHAUST(3),
	/**
	 * Throw a Poro at your enemies. If it hits, you can quickly travel to your target as a follow up.
	 */
	PORO_TOSS(31),
	/**
	 * Restores 40% of your champion's maximum Mana. Also restores allies for 40% of their maximum Mana
	 */
	CLARITY(13),
	/**
	 * Reveals a small area of the map for your team for 5 seconds.
	 */
	CLAIRVOYANCE(2),
	/**
	 * Shields your champion for 115-455 (depending on champion level) for 2 seconds.
	 */
	BARRIER(21),
	/**
	 * Teleports your champion a short distance toward your cursor's location.
	 */
	FLASH(4),
	/**
	 * Allied Turret: Grants massive regeneration for 8 seconds. Enemy Turret: Reduces damage dealt by 80% for 8 seconds.
	 */
	GARRISON(17);
	
	/**
	 * Returns an SummonerSpell from the provided code.
	 *
	 * @param type the type
	 * @return SummonerSpell
	 */
	public static Optional<SummonerSpell> getFromCode(final Integer type)
	{
		return Stream.of(SummonerSpell.values()).filter(t -> t.getId().equals(type)).findFirst();
	}
	
	final Integer id;
	
	/**
	 * Instantiates a new summoner spell.
	 *
	 * @param id the id
	 */
	SummonerSpell(final Integer id)
	{
		this.id = id;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId()
	{
		return this.id;
	}
	
}
