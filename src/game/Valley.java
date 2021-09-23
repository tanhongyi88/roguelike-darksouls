package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import game.enums.Abilities;
import game.enums.Status;

/**
 * The gorge or endless gap that is dangerous for the Player.
 */
public class Valley extends Ground {

	public Valley() {
		super('+');
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

	// FIXME: Haven't implement instantly kill.
}
