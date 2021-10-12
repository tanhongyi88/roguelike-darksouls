package game.player;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import game.enums.Status;

public class ActivateBonfireAction extends Action {

    private Ground bonfire;
    /**
     * Constructor
     *
     * @param bonfire
     */
    public ActivateBonfireAction(Ground bonfire) {
        this.bonfire = bonfire;
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
        this.bonfire.addCapability(Status.IS_LIT);
        return menuDescription(actor);
    }

    /**
     * Returns a description that a specific bonfire has been lit
     *
     * @param actor The actor performing the action.
     * @return a String describing the bonfire has been lit
     */
    @Override
    public String menuDescription(Actor actor) {
        return bonfire + " has been lit.";
    }
}
