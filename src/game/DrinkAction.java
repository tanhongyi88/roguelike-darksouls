package game;

import edu.monash.fit2099.engine.*;

/**
 * DrinkAction class represents the action when Player drinks the Estus Flask
 *
 * @author
 * @version 1.0.0
 */
public class DrinkAction extends Action {

    private Player player;
    private EstusFlask estusFlask;

    /**
     * Constructor for the DrinkAction
     *
     * @param player the player drinking the Estus Flask
     */
    public DrinkAction(Player player) {
        this.player = player;
        this.estusFlask = new EstusFlask("Estus Flask");
        player.addItemToInventory(estusFlask);
    }

    /**
     * Performs the DrinkAction.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        EstusFlask estusFlask = player.getEstusFlask();

        if(estusFlask.getNumOfEstusFlask() == 0){
            return "No available Estus Flask!";
        }
        else{
            if(player.getHitPoints() < player.getMaxHitPoints()){
                int points = player.getMaxHitPoints();
                player.heal(points);
                estusFlask.decrementNumberOfEstusFlask();
                return actor + " drinks Estus Flask!";
            }
        }
        return actor + "'s hitpoints is full!";
    }

    /**
     * Returns the key used in the menu to trigger this Action.
     *
     * @return The key ("a") for DrinkAction in the menu
     */
    @Override
    public String hotkey(){
        return "a";
    }

    /**
     * Returns a descriptive string that the player drinks the Estus Flask
     *
     * @param actor The actor performing the action.
     * @return String that needs to be shown on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Unkindled drinks Estus Flask (" + player.getEstusFlask().getNumOfEstusFlask() + "/" + player.getEstusFlask().getMaxNumOfEstusFlask() +")";
    }
}
