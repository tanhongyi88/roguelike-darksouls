package game.behaviour;

import edu.monash.fit2099.engine.*;
import game.player.AttackAction;
import game.interfaces.Behaviour;

/**
 * AttackBehaviour class represents the attack behaviour of an actor
 *
 * @author Lee Jia Yi
 * @version 1.0.0
 */
public class AttackBehaviour implements Behaviour {
    private Actor target;
    private String direction;

    /**
     * Constructor for the AttackBehaviour.
     *
     * @param target     the Actor that is being attacked
     * @param direction  the direction of the attack
     */
    public AttackBehaviour(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    /**
     * Creates the AttackAction if an actor has AttackBehaviour
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return an AttackAction
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if(!map.contains(target) || !map.contains(actor))
            return null;

        Location here = map.locationOf(actor);

        for (Exit exit : here.getExits()){
            Location destination = exit.getDestination();
            if (destination.getActor() == target){
                return new AttackAction(target, direction);
            }
        }
        return null;
    }
}
