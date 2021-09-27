package game.vendor;

import edu.monash.fit2099.engine.*;
import game.player.Player;
import game.weapon.Broadsword;
import game.weapon.action.SwapWeaponAction;

/**
 * The class representing the action of purchasing a Broad Sword from the Vendor
 *
 * @author Afrida Jahin
 * @version 1.0.0
 */
public class BuySwordAction extends Action {
    private Player player;
    private Broadsword broadSword;
    private SwapWeaponAction swapWeapon;
    private final int PRICE = 500;

    /**
     * Constructor for BuySwordAction
     *
     * @param player the Player buying the weapon
     */
    public BuySwordAction(Player player){
        this.player = player;
        this.broadSword = new Broadsword();
        this.swapWeapon = new SwapWeaponAction(broadSword);
    }

    /**
     * Performs the Buy Action
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return The appropriate string to show transaction success or failure
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        int playerSouls = player.getSouls();

        if (playerSouls >= PRICE){
            player.subtractSouls(PRICE);
            swapWeapon.execute(player,map);
            return "Purchase successful: Broad Sword received!";
        }
        else{
            return "Purchase failed: Not enough souls!";
        }
    }

    /**
     * Returns the key used in the menu to trigger this Action
     *
     * @return The key for BuySwordAction in the menu
     */
    @Override
    public String hotkey() { return "B"; }

    /**
     * Return a descriptive string to show Player can buy Broad Sword from Fire Keeper
     *
     * @param actor The actor performing the action.
     * @return A string to be displayed in the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Buy Broad Sword from Fire Keeper (" + PRICE + " souls)";
    }
}
