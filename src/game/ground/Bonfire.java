package game.ground;
import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.enums.Status;
import game.player.ActivateBonfireAction;
import game.BonfireManager;
import game.reset.ResetAction;

/**
 * The place for the Player to trigger the reset action
 *
 * @author Tan Hong Yi
 * @version 1.0.0
 */
public class Bonfire extends Ground{

    /**
     * Name of the Bonfire
     */
    private String name;

    /**
     * BonfireManager that stores the location of bonfires that can be teleported
     */
    private BonfireManager bonfireManager;

    /**
     * Constructor for the Bonfire class
     * Represented on the game map as 'B'
     */
    public Bonfire(String name, BonfireManager bonfireManager) {
        super('B');
        this.name = name;
        this.bonfireManager = bonfireManager;
    }

    /**
     * Return a list of actions that can be done on Bonfire
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return actions that can be done related to Bonfire
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();
        if (!this.hasCapability(Status.IS_LIT)) {
            actions.add(new ActivateBonfireAction(location));
            return actions;
        }
        else if (actor.hasCapability(Abilities.REST) && actor.hasCapability(Abilities.TELEPORT)) {
            actions.add(new ResetAction(name));
            actions.add(bonfireManager.getTeleportActions(location));
        }
        return actions;
    }

    /**
     * Actor is not allowed to enter Bonfire
     *
     * @param actor the Actor to check
     * @return false if the actor is not allowed to enter
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return true;
    }


    @Override
    public String toString() {
        return name;
    }
}
