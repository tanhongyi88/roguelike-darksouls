package game.weapon.action;

import edu.monash.fit2099.engine.*;
import game.ground.BurningDirt;
import game.weapon.YhormsGreatMachete;
import game.player.Player;
import game.ground.Dirt;

/**
 * The class representing the use of Ember Form by Yhorm The Giant while wielding his Great Machete
 *
 * @author Tan Hong Yi
 * @version 1.0.0
 */
public class EmberFormAction extends Action {
    private final static int BURN_DAMAGE = 25;
    private final YhormsGreatMachete greatMachete;
    private Location burningLocation;
    private boolean burnStart;


    /**
     * Constructor for EmberFormAction
     *
     * @param greatMachete The weapon using this action
     */
    public EmberFormAction(YhormsGreatMachete greatMachete) {
        this.greatMachete = greatMachete;
        this.burnStart = false;
    }

    /**
     * Burns the dirt at surroundings and damage the player if the player is on the burning ground.
     * Raise the hit rate of Yhorm's Great Machete from 60% to 90%.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return  a string describing YhormTheGiant is engulfed with fire
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        
        if (!burnStart) {
            this.burningLocation = map.locationOf(actor);
            this.greatMachete.increaseHitRateWhenEmberForm();
            this.burnStart = true;
        }
        if (burnStart) {
            for (Exit exit : this.burningLocation.getExits()) {
                Location destination = exit.getDestination();
                if (destination.getGround() instanceof Dirt) {
                    destination.setGround(new BurningDirt());
                    destination.getGround().tick(destination);
                }
                if (destination.getActor() instanceof Player) {
                    destination.getActor().hurt(BURN_DAMAGE);

                }
            }
        }
        return menuDescription(actor);
    }

    /**
     * Return a descriptive string to show the use of Ember Form
     * @param actor The actor performing the action
     * @return A string to be displayed in the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + "burns the area and skin is engulfed with fire (Ember Form)";
    }
}
