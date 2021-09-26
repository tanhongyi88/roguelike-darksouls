package game.ground;
import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.reset.ResetAction;


public class Bonfire extends Ground{


    public Bonfire() {
        super('B');
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
            actions.add(new ResetAction());
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
