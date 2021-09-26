package game.ground;

import edu.monash.fit2099.engine.*;
import game.enums.Status;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {

	/**
	 * Constructor for the Floor class
	 * Represented on the game map as '_'
	 */
	public Floor() {
		super('_');
	}

	/**
	 * Checks and only allows the player to enter the floor.
	 *
	 * @param actor the Actor to check
	 * @return true only to player, false to all enemies
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return actor.hasCapability(Status.ABLE_TO_STEP_ON_FLOOR);
	}
}
