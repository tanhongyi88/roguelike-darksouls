package game.behaviour;

import edu.monash.fit2099.engine.*;
import game.interfaces.Behaviour;
import game.player.AttackAction;
import game.weapon.DarkmoonLongbow;

/**
 * RangeWeaponBehaviour class represents the range attack of a weapon
 *
 * @author Lee Jia Yi
 * @version 1.0.0
 */
public class RangedWeaponBehaviour extends WeaponAction implements Behaviour {
    private Actor target;

    /**
     * Constructor for the RangedWeaponBehaviour.
     *
     * @param target the Actor to attack
     */
    public RangedWeaponBehaviour(Actor target, DarkmoonLongbow darkmoonLongbow){
        super(darkmoonLongbow);
        this.target = target;
    }

    /**
     * Performs the AttackAction.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String that represents the AttackAction
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return System.lineSeparator() + new AttackAction(target, "").execute(actor, map);
    }

    /**
     * Creates the AttackAction if an actor has RangedWeaponBehaviour
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return an AttackAction
     */
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

                        // find if there is any object that can block the attack, nullify the attack if there is
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

    /**
     * Returns a descriptive string of AttackAction to be displayed in the menu
     *
     * @param actor The actor performing the action.
     * @return String that describes the AttackAction
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target;
    }

}
