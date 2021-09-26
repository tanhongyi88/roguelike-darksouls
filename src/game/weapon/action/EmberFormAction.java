package game.weapon.action;

import edu.monash.fit2099.engine.*;
import game.ground.BurningDirt;
import game.weapon.YhormsGreatMachete;
import game.player.Player;
import game.ground.Dirt;

public class EmberFormAction extends Action {
    private final static int BURN_DAMAGE = 25;
    private final YhormsGreatMachete greatMachete;
    private Location burningLocation;
    private boolean burnStart;


    /**
     * Constructor
     */
    public EmberFormAction(YhormsGreatMachete greatMachete) {
        this.greatMachete = greatMachete;
        this.burnStart = false;
    }

    /**
     * Burns the dirt at surroundings and damage the player if the player is on the burning ground.
     * Raise the hitrate of Yhorms Great Machete from 60% to 90%.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return  a string describing YhormTheGiant is engulfed with fire
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        
        if (!burnStart) {
            this.burningLocation = map.locationOf(actor);
            this.greatMachete.increaseHitRateWhenEmberFore();
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

    @Override
    public String menuDescription(Actor actor) {
        return actor + "burns the area and skin is engulfed with fire (Ember Form)";
    }
}
