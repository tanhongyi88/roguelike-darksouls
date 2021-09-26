package game.ground;

import edu.monash.fit2099.engine.*;
import game.enums.Status;

/**
 * The gorge or endless gap that is dangerous for the Player.
 *
 * @author Lee Jia Yi, Tan Hong Yi
 * @version 1.0.0
 */
public class Valley extends Ground {

	/**
	 * Constructor for the Valley class
	 * Represented on the game map as '+'
	 */
	public Valley() {
		super('+');
	}

	/**
	 * Returns the action that can be done in the Valley.
	 *
	 * @param actor the Actor acting
	 * @param location the current Location
	 * @param direction the direction of the Ground from the Actor
	 * @return blank actions
	 */
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction) {
		if (location.getActor() == actor && location.getGround() instanceof Valley) {
			actor.hurt(Integer.MAX_VALUE);
		}
		return new Actions();
	}

	/**
	 * Checks and only allows the player to enter the valley
	 *
	 * @param actor the Actor to check
	 * @return false or actor cannot enter.
	 */
	@Override
	public boolean canActorEnter(Actor actor){
		return actor.hasCapability(Status.ABLE_TO_ENTER_VALLEY);
	}
}
