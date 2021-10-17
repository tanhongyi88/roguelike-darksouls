package game.player;

import edu.monash.fit2099.engine.*;
import game.enums.Status;

/**
 * The action to lit the bonfire before the bonfire is able to function
 *
 * @author Tan Hong Yi
 * @version 1.0.0
 */
public class ActivateBonfireAction extends Action {

    /**
     * location that contains Bonfire
     */
    private Location location;
    /**
     * Constructor
     *
     * @param location location that contains Bonfire
     */
    public ActivateBonfireAction(Location location) {
        this.location = location;
    }

    /**
     * Activate the bonfire by litting it
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String describing the bonfire is activated
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        this.location.getGround().addCapability(Status.IS_LIT);
        Player player = (Player) actor;
        player.updateSpawnLocation(this.location);
        return this.location.getGround() + " is lit";
    }

    /**
     * Returns a description that a specific bonfire has been lit
     *
     * @param actor The actor performing the action.
     * @return a String describing the bonfire has been lit
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Lit " + this.location.getGround() + "'s bonfire";
    }
}
