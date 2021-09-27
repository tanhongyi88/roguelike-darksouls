package game.weapon.action;

import edu.monash.fit2099.engine.*;
import game.weapon.GiantAxe;

/**
 * The class representing the use of Spin Attack Action when wielding a Giant Axe
 *
 * @author Afrida Jahin
 * @version 1.0.0
 */
public class SpinAttackAction extends WeaponAction {
    private GiantAxe giantAxe;
    private Actor target;

    /**
     * Constructor for ChargeAction
     *
     * @param giantAxe The weapon using this action
     * @param target The weapon's target
     */
    public SpinAttackAction(WeaponItem giantAxe, Actor target) {
        super(giantAxe);
        this.target = target;
    }

    /**
     * Performs the Spin Attack Action
     *
     * @param actor The actor performing the action
     * @param map The map where the actor currently is
     * @return An appropriate string using menuDescription
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        Location here = map.locationOf(actor);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.getActor() == target) {
                target.hurt(25);
                return actor + "does Spin Attack on" + target;
            }
        }
        return null;
    }

    @Override
    public String hotkey() {
        return "A";
    }

    /**
     * Return a descriptive string to show Spin Attack has been made
     *
     * @param actor The actor performing the action
     * @return A string to be displayed in the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Perform Spin Attack";
    }
}
