package game.vendor;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.enemy.CinderOfTheLord;
import game.enums.Abilities;
import game.player.Player;
import game.weapon.DarkmoonLongbow;
import game.weapon.action.SwapWeaponAction;

import java.util.List;

/**
 * The class representing the action of purchasing Aldrich the Devourer's Longbow from the Vendor
 *
 * @author Afrida Jahin
 * @version 1.0.0
 */
public class BuyBowAction extends Action {
    private Player player;
    private DarkmoonLongbow longbow;
    private SwapWeaponAction swapWeapon;

    /**
     * Constructor for BuyBowAction
     *
     * @param player the Player buying the weapon
     */
    public BuyBowAction(Player player){
        this.player = player;
        this.longbow = new DarkmoonLongbow();
        this.swapWeapon = new SwapWeaponAction(longbow);
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
                if(((CinderOfTheLord) cinders).getName().equals("Cinder of Aldrich The Devourer")){
                    player.removeItemFromInventory(cinders);
                    swapWeapon.execute(player,map);
                    return "Purchase successful: Darkmoon Longbow received!";
                }
                else{
                    return "Purchase failed: Wrong Cinder of Lord!";
                }
            }
        }
        return "Purchase failed: Need Cinders of Aldrich the Devourer!";
    }

    /**
     * Returns the key used in the menu to trigger this Action
     *
     * @return The key for BuyBowAction in the menu
     */
    @Override
    public String hotkey() {
        return "D";
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Buy Darkmoon Longbow from Fire Keeper";
    }
}
