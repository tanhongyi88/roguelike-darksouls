package game.ground;

import edu.monash.fit2099.engine.*;
import game.enums.Status;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {

	public Floor() {
		super('_');
	}

	@Override
	public boolean canActorEnter(Actor actor) {
		return actor.hasCapability(Status.ABLE_TO_STEP_ON_FLOOR);
	}
}
