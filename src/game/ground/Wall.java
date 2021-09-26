package game.ground;

import edu.monash.fit2099.engine.*;

public class Wall extends Ground {

	/**
	 * Constructor for the Wall class
	 * Represented on the game map as '#'
	 */
	public Wall() {
		super('#');
	}

	/**
	 * Checks if the actor can enter the wall
	 *
	 * @param actor the Actor to check
	 * @return false as no actor can enter the wall
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}

	/**
	 * Blocks thrown object to the wall
	 *
	 * @return true as it blocks all thrown object
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
