package game.vendor;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.enemy.CinderOfTheLord;
import game.enemy.YhormTheGiant;
import game.enums.Abilities;
import game.player.Player;
import game.weapon.YhormsGreatMachete;
import game.weapon.action.SwapWeaponAction;

import java.util.List;

/**
 * The class representing the action of purchasing  Yhorm's Great Machete from the Vendor
 *
 * @author Afrida Jahin
 * @version 1.0.0
 */
public class BuyMacheteAction extends Action {
    private Player player;
    private YhormsGreatMachete machete;
    private SwapWeaponAction swapWeapon;

    /**
     * Constructor for BuyMacheteAction
     *
     * @param player the Player buying the weapon
     */
    public BuyMacheteAction(Player player){
        this.player = player;
        this.machete = new YhormsGreatMachete();
        this.swapWeapon = new SwapWeaponAction(machete);
    }

    /**
     * Performs the Buy Action
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        List<Item> playerInventory = player.getInventory();

        for (Item cinders : playerInventory) {
            if (cinders instanceof CinderOfTheLord){
                if(((CinderOfTheLord) cinders).getName().equals("Cinder of Yhorm The Giant")){
                    player.removeItemFromInventory(cinders);
                    swapWeapon.execute(player,map);
                    return "Purchase successful: Yhorm's Great Machete received!";
                }
                else{
                    return "Purchase failed: Wrong Cinder of Lord!";
                }
            }
        }
        return "Purchase failed: Need Cinders of Yhorm the Giant!";
    }

    /**
     * Returns the key used in the menu to trigger this Action
     *
     * @return The key for BuyMacheteAction in the menu
     */
    @Override
    public String hotkey() { return "M"; }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Buy Yhorm's Great Machete from Fire Keeper";
    }
}
