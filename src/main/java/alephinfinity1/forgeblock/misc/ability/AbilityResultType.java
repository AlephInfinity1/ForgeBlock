package alephinfinity1.forgeblock.misc.ability;

/**
 * The result of a cast spell.
 */
public enum AbilityResultType {
	SUCCESS,				//Spell is successfully cast
	NOT_ENOUGH_MANA,		//The player/mob didn't have enough mana to cast this spell.
	NO_TARGET;				//The spell didn't have any eligible targets.
}
