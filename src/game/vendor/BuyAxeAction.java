package game.vendor;

import edu.monash.fit2099.engine.*;
import game.player.Player;
import game.weapon.GiantAxe;
import game.weapon.action.SwapWeaponAction;

/**
 * The class representing the action of purchasing a Broad Sword from the Vendor
 *
 * @author Afrida Jahin
 * @version 1.0.0
 */
public class BuyAxeAction extends Action {
    private Player player;
    private GiantAxe giantAxe;
    private SwapWeaponAction swapWeapon;
    private final int PRICE = 1000;

    /**
     * Constructor for BuyAxeAction
     *
     * @param player the Player buying the weapon
     */
    public BuyAxeAction(Player player){
        this.player = player;
        this.giantAxe = new GiantAxe();
        this.swapWeapon = new SwapWeaponAction(giantAxe);
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
            return "Purchase successful: Giant Axe received!";
        }
        else{
            return "Purchase failed: Not enough souls!";
        }
    }

    /**
     * Returns the key used in the menu to trigger this Action
     *
     * @return The key for BuyAxeAction in the menu
     */
    @Override
    public String hotkey() { return "G"; }

    /**
     * Return a descriptive string to show Player can buy Giant Axe from Fire Keeper
     *
     * @param actor The actor performing the action.
     * @return A string to be displayed in the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Buy Giant Axe from Fire Keeper (" + PRICE + " souls)";
    }
}
