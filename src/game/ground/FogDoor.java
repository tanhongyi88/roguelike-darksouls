package game.ground;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * The place for the Player to go to the other map
 *
 * @author Tan Hong Yi
 * @version 1.0.0
 */
public class FogDoor extends Ground {

    /**
     * Location connected to
     */
    private Location location;

    /**
     * Constructor for the FogDoor class
     * Represented on the game map as '='
     */
    public FogDoor(Location location) {
        super('=');
        this.location = location;
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        return super.allowableActions(actor, location, direction);
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return true;
    }
}
