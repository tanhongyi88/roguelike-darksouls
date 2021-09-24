package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import game.enums.Abilities;
import game.enums.Status;
import edu.monash.fit2099.engine.Location;

/**
 * The gorge or endless gap that is dangerous for the Player.
 */
public class Valley extends Ground {

	public Valley() {
		super('+');
	}

	/**
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
	 * Checks and only allowes the player to enter the valley
	 * @param actor the Actor to check
	 * @return false or actor cannot enter.
	 */
	@Override
	public boolean canActorEnter(Actor actor){
		return actor.hasCapability(Status.ABLE_TO_ENTER_VALLEY);
	}
}
