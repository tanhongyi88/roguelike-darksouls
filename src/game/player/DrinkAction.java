package game.player;

import edu.monash.fit2099.engine.*;
import game.interfaces.Consumable;

/**
 * DrinkAction class represents the action when Player drinks the Estus Flask
 *
 * @author Lee Jia Yi
 * @version 1.1.0
 */
public class DrinkAction extends Action {

    private Player player;
    private Consumable consumable;

    /**
     * Constructor for the DrinkAction
     *
     * @param player the player drinking the Estus Flask
     */
    public DrinkAction(Player player, Consumable consumable) {
        this.player = player;
        this.consumable = consumable;
    }

    /**
     * Performs the DrinkAction.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of drink action to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        if(consumable.getNumberOfConsumable() == 0){
            return "No available " + consumable + "!";
        }
        else{
            if(player.getHitPoints() < player.getMaxHitPoints()){
                int points = player.getMaxHitPoints();
                player.heal(points);
                consumable.useConsumable();
                return actor + " drinks " + consumable + "!";
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
        return "Unkindled drinks " + consumable + "(" + consumable.getNumberOfConsumable() + "/" + consumable.getMaximumConsumable() +")";
    }
}
