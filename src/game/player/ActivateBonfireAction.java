package game.player;

import edu.monash.fit2099.engine.*;
import game.enums.Status;

public class ActivateBonfireAction extends Action {

    private Location location;
    /**
     * Constructor
     *
     * @param location
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
