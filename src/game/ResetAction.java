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
     * @return a String describes the action of resting is done
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager.getInstance().run();
        if (this.previousLocation != null) {
            this.previousLocation.addItem(new TokenOfSouls("Token of Souls", '$', true, actor.asSoul()));
        }
        return actor + " is returned to Bonfire";
    }

    /**
     * A string describing the RestAction for displaying in the UI menu.
     * @param actor The actor performing the action.
     * @return a String that describes resting at shrine
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Rest at Firelink Shrine's Bonfire";
    }

}
