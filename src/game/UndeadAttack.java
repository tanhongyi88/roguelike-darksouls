package game;

import edu.monash.fit2099.engine.*;
import game.interfaces.Behaviour;

import java.util.*;

public class UndeadAttack extends Action implements Behaviour{

    private Actor target;

    /**
     * Constructor.
     *
     * @param target    the Actor to attack
     */
    public UndeadAttack(Actor target) {
        this.target = target;
    }

    public String execute(Actor actor, GameMap map) {
        int damage = 20;
        String[] s = {"punches", "thwacks"};
        Random random = new Random();
        int select = random.nextInt(s.length);
        target.hurt(damage);

        return actor + " " + s[select] + " " + target + " for " + damage + " damage.";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "";
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if(!map.contains(target) || !map.contains(actor))
            return null;

        Location here = map.locationOf(actor);

        for (Exit exit : here.getExits()){
            Location destination = exit.getDestination();
            if (destination.getActor() == target){
                return this;
            }
        }
        return null;
    }
}
