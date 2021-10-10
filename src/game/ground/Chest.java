package game.ground;

import edu.monash.fit2099.engine.*;
import game.interfaces.Soul;
import game.player.OpenChestAction;

/**
 * Chest class represents the chest in the game
 *
 * @author Lee Jia Yi
 * @version 1.0.0
 */
public class Chest extends Ground implements Soul {
    private final static int CHEST_SOULS = 100;

    /**
     * Constructor for the Chest class
     * Represented on the game map as '?'
     */
    public Chest() {
        super('?');
    }

    /**
     * Checks if the actor is allowed to enter the Chest
     *
     * @param actor the Actor to check
     * @return false as all actors are not allowed to enter the cemetery
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    /**
     * Return a list of actions that can be done on Chest
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return actions that can be done related to Chest
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction){
        Actions actions = new Actions();
        actions.add(new OpenChestAction(location));
        return actions;
    }

    /**
     * Transfers the souls (100 souls) to a Token of Soul after Chest is opened
     *
     * @param soulObject a target souls.
     */
    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(CHEST_SOULS);
    }
}
