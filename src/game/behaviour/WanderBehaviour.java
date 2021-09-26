package game.behaviour;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.*;
import game.interfaces.Behaviour;

/**
 * WanderBehaviour class represents the wander behaviour of an actor
 *
 * @author
 * @version 1.0.0
 */
public class WanderBehaviour extends Action implements Behaviour {
	
	private final Random random = new Random();

	/**
	 * Returns a MoveAction to wander to a random location, if possible.  
	 * If no movement is possible, returns null.
	 * 
	 * @param actor the Actor enacting the behaviour
	 * @param map the map that actor is currently on
	 * @return an Action, or null if no MoveAction is possible
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		ArrayList<Action> actions = new ArrayList<Action>();
		
		for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
            	actions.add(exit.getDestination().getMoveAction(actor, "around", exit.getHotKey()));
            }
        }
		
		if (!actions.isEmpty()) {
			return actions.get(random.nextInt(actions.size()));
		}
		else {
			return null;
		}

	}

	/**
	 * Returns the string when the actor wanders
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return String that represents the wander action
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		return menuDescription(actor);
	}

	/**
	 * Returns a descriptive string when the actor wanders
	 *
	 * @param actor The actor performing the action.
	 * @return String that represents the wander action
	 */
	@Override
	public String menuDescription(Actor actor) {
		return "Raagrh...";
	}
}
