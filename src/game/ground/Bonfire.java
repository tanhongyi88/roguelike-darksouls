package game.ground;
import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
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
     * Constructor for the Bonfire class
     * Represented on the game map as 'B'
     */
    public Bonfire(String name) {
        super('B');
        this.name = name;
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
        if (actor.hasCapability(Abilities.REST)) {
            actions.add(new ResetAction(name));
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
}
