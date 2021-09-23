package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class ResetAction extends Action {

    protected String hotKey;

    /**
     * Constructor to create an Action that will reset the Player's hitpoints, EstusFlask's charges
     *
     * @param hotkey the menu hotkey for this action
     */
    public ResetAction (String hotkey) {
        this.hotKey = hotkey;
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
        return hotKey;
    }
}
