package game.behaviour;

import edu.monash.fit2099.engine.*;
import game.player.AttackAction;
import game.interfaces.Behaviour;

public class AttackBehaviour implements Behaviour {
    private Actor target;
    private String direction;

    public AttackBehaviour(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

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
