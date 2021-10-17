package game.weapon.action;

import edu.monash.fit2099.engine.*;
import game.enemy.YhormTheGiant;
import game.enums.Status;

/**
 * The class representing the use of Wind Slash Action when wielding Storm Ruler
 *
 * @author Afrida Jahin
 * @version 1.1.0
 */
public class WindSlashAction extends WeaponAction {
    private Actor boss;

    /**
     * Constructor for WindSlashAction
     *
     * @param stormRuler The weapon using this action
     */
    public WindSlashAction(WeaponItem stormRuler) {
        super(stormRuler);
    }

    /**
     * Performs the Charge Action
     *
     * @param actor The actor performing the action
     * @param map The map where the actor currently is
     * @return An appropriate string to show if attack has been made
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if(weapon.hasCapability(Status.CHARGED)){
            Location here = map.locationOf(actor);
            for (Exit exit : here.getExits()) {
                Location destination = exit.getDestination();
                if (destination.getActor() instanceof YhormTheGiant) {
                    boss = destination.getActor();
                    boss.addCapability(Status.STUN);
                    weapon.removeCapability(Status.CHARGED);
                    return actor + " uses Wind Slash on Yhorm The Giant!";
                }
            }
        }
        return "Not fully charged yet";
    }

    /**
     * Return a descriptive string to show Wind Slash Attack has been made
     *
     * @param actor The actor performing the action
     * @return A string to be displayed in the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Perform Wind Slash";
    }
}
