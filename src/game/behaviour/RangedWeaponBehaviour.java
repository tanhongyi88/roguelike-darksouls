package game.behaviour;

import edu.monash.fit2099.engine.*;
import game.interfaces.Behaviour;
import game.player.AttackAction;

public class RangedWeaponBehaviour extends Action implements Behaviour {
    private Actor target;

    public RangedWeaponBehaviour(Actor target){
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return System.lineSeparator() + new AttackAction(target, "").execute(actor, map);
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);

        int currentDistance = Math.abs(here.x() - there.x()) + Math.abs(here.y() - there.y());
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                int newDistance = Math.abs(destination.x() - there.x()) + Math.abs(destination.y() - there.y());
                if (currentDistance <= 3 && newDistance > currentDistance) {
                    NumberRange xs, ys;
                    if (here.x() == there.x() || here.y() == there.y()) {
                        xs = new NumberRange(Math.min(here.x(), there.x()), Math.abs(here.x() - there.x()) + 1);
                        ys = new NumberRange(Math.min(here.y(), there.y()), Math.abs(here.y() - there.y()) + 1);

                        for (int x : xs) {
                            for (int y : ys) {
                                if (map.at(x, y).getGround().blocksThrownObjects())
                                    return null;
                            }
                        }
                        return this;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target;
    }

}
