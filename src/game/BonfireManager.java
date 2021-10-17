package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;
import game.enums.Status;

import java.util.ArrayList;

/**
 * The manager which stores all the locations of all the bonfires in the world
 *
 * @author Tan Hong Yi
 * @version 1.0.0
 */
public class BonfireManager {

    /**
     * an array list of locations of all the bonfires
     */
    private final ArrayList<Location> locToTeleport = new ArrayList<>();
    /**
     * an array list of all directions to the bonfire
     */
    private final ArrayList<String> directionName = new ArrayList<>();

    /**
     * Constructor
     */
    public BonfireManager() {
    }

    /**
     * Add location that can be teleported to the BonfireManager
     *
     * @param direction to the bonfire
     * @param location that can be teleported
     */
    public void addLocation(String direction, Location location) {
        this.locToTeleport.add(location);
        this.directionName.add(direction);
    }

    /**
     * Returns the length of hash map that the manager is storing
     *
     * @return an integer representing the size of hash map
     */
    private int getSize() {
        return this.locToTeleport.size();
    }

    /**
     * Get teleport actions from the locations of all available bonfire
     *
     * @param currentLocation the bonfire location the player is interacting with
     * @return actions to teleport (MoveActorActions)
     */
    public Actions getTeleportActions(Location currentLocation) {
        Actions actions = new Actions();
        for (int i = 0; i < getSize(); i++) {
            if (!currentLocation.equals(locToTeleport.get(i)) && locToTeleport.get(i).getGround().hasCapability(Status.IS_LIT)) {
                actions.add(new MoveActorAction(locToTeleport.get(i), directionName.get(i)));
            }
        }
        return actions;
    }

}
