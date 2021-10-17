package game.ground;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.reset.ResetAction;

/**
 * The place for the Player to go to the other map
 *
 * @author Tan Hong Yi
 * @version 1.0.0
 */
public class FogDoor extends Ground {

    /**
     * Location that this door is connected to
     */
    private Location locationToGo;
    /**
     * name of location that this door is connected to
     */
    private String direction;

    /**
     * Constructor for the FogDoor class
     * Represented on the game map as '='
     * @param location location that this door is connected to
     * @param direction direction name when player enters the door
     */
    public FogDoor(Location location, String direction) {
        super('=');
        this.locationToGo = location;
        this.direction = direction;
    }

    /**
     * Get allowable actions from FogDoor when the player gets near or stands on the FogDoor
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return MoveActorAction
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        return new Actions(new MoveActorAction(this.locationToGo, this.direction));
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return true;
    }
}
