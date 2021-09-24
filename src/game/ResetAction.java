package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class ResetAction extends Action {

    private Location previousLocation;

    /**
     * Constructor to create an Action that will reset the Player's hitpoints, EstusFlask's charges
     *
     */
    public ResetAction (Location placeWherePlayerDied) {
        this.previousLocation = placeWherePlayerDied;

    }

    public ResetAction() {

    }

    /**
     * Rest at Bonfire
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return null
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager.getInstance().run();
        return menuDescription(actor);
    }

    /**
     * A string describing the RestAction for displaying in the UI menu.
     * @param actor The actor performing the action.
     * @return null
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + "Rest at Shrine";
    }

    /**
     * Returns this Action's hotkey.
     *
     * @return the hotkey
     */
    @Override
    public String hotkey() {
        return "R";
    }
}
