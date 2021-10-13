package game.player;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;
import game.enums.Status;

import java.util.ArrayList;

public class BonfireManager {

    private ArrayList<Location> locToTeleport = new ArrayList<>();
    private ArrayList<String> directionName = new ArrayList<>();

    public BonfireManager() {
    }

    /**
     * Add location that can be teleported to the BonfireManager
     *
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

    public Actions getMoveActions(Location currentLocation) {
        Actions actions = new Actions();
        for (int i = 0; i < getSize(); i++) {
            if (!currentLocation.equals(locToTeleport.get(i)) && locToTeleport.get(i).getGround().hasCapability(Status.IS_LIT)) {
                actions.add(new MoveActorAction(locToTeleport.get(i), directionName.get(i)));
            }
        }
        return actions;
    }

}
