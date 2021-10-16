package game.ground;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.player.Player;
import game.vendor.BuyAxeAction;
import game.vendor.BuyBowAction;
import game.vendor.BuyMacheteAction;
import game.vendor.BuySwordAction;

/**
 * The place for the Player to trade.
 *
 * @author Afrida Jahin
 * @version 1.0.0
 */
public class FireKeeper extends Ground {

    /**
     * Constructor for the FireKeeper class
     * Represented on the game map as 'F'
     */
    public FireKeeper() {
        super('F');
    }

    /**
     * Returns the action that can be done in the FireKeeper.
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return actions that can be done related to the FireKeeper
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();
        if (actor.hasCapability(Abilities.BUY)) {
            actions.add(new BuySwordAction((Player) actor));
            actions.add(new BuyAxeAction((Player) actor));
            actions.add(new BuyMacheteAction((Player) actor));
            actions.add(new BuyBowAction((Player) actor));
        }
        return actions;
    }

    /**
     * Actor is not allowed to enter/stand on FireKeeper
     *
     * @param actor the Actor to check
     * @return false if actor not allowed to enter
     */
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}
