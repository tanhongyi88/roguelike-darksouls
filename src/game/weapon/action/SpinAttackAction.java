package game.weapon.action;

import edu.monash.fit2099.engine.*;

/**
 * The class representing the use of Spin Attack Action when wielding a Giant Axe
 *
 * @author Afrida Jahin
 * @version 1.1.0
 */
public class SpinAttackAction extends WeaponAction {
    private Actor target;

    /**
     * Constructor for ChargeAction
     *
     * @param giantAxe The weapon using this action
     */
    public SpinAttackAction(WeaponItem giantAxe) {
        super(giantAxe);
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
        String result = actor + " performs Spin Attack";

        Location here = map.locationOf(actor);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if(destination.containsAnActor()){
                target = destination.getActor();
                target.hurt(25);
                result += System.lineSeparator() + actor + "does Spin Attack on " + target;
            }
        }
        return result;
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
