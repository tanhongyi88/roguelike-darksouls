package game.reset;

import edu.monash.fit2099.engine.*;

/**
 * ResetAction class that represents the reset action during interaction with Bonfire or Player dies
 *
 * @author Tan Hong Yi
 * @version 1.0.0
 */
public class ResetAction extends Action {
    /**
     * placeToRest stores the name of Bonfire to rest at
     */
    private String bonfireName;

    /**
     * previousLocation stores the location before being killed (Fall into valley or by enemies)
     */
    private Location previousLocation;
    /**
     * previousTokenLocation stores the location of souls token dropped when player dies
     */
    private Location previousTokenLocation;
    /**
     * soulsToken stores the token of souls dropped when player dies
     */
    private Item soulsToken;

    /**
     * Constructor to create an Action that will reset the Player's hitpoints, EstusFlask's charges and
     * only be used when player is being killed (Not reset by interaction with Bonfire)
     *
     * @param placeWherePlayerDied Location that represents the place where the player died
     */
    public ResetAction (Location placeWherePlayerDied) {
        this.previousLocation = placeWherePlayerDied;
    }

    /**
     * Constructor to create an Action that will reset the player without resetting the location and
     * only be used when player interacts with Bonfire to reset
     */
    public ResetAction(String placeToRest) {
        this.bonfireName = placeToRest;
    }

    /**
     * Overriding execute method in Action class
     * Rest at Bonfire (fully recover hit points and EstusFlask charges)
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String describes the action of resting is done
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager.getInstance().run();
        if (this.previousLocation != null) {
            if (this.previousTokenLocation != null) {
                this.previousTokenLocation.removeItem(this.soulsToken);
            }
            Item token = new TokenOfSouls("Token of Souls", '$', true, actor.asSoul());
            this.previousLocation.addItem(token);
            this.previousTokenLocation = this.previousLocation;
            this.soulsToken = token;
        }
        return actor + " is returned to Bonfire";
    }

    /**
     * Overriding menuDescription method in Action class
     * A string describing the RestAction for displaying in the UI menu.
     *
     * @param actor The actor performing the action.
     * @return a String that describes resting at shrine
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Rest at " + bonfireName + "'s Bonfire";
    }

}
