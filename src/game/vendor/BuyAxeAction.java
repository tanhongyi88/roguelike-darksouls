package game.vendor;

import edu.monash.fit2099.engine.*;
import game.player.Player;
import game.weapon.GiantAxe;
import game.weapon.action.SwapWeaponAction;

public class BuyAxeAction extends Action {
    private Player player;
    private GiantAxe giantAxe;
    private SwapWeaponAction swapWeapon;
    private final int PRICE = 1000;

    public BuyAxeAction(Player player){
        this.player = player;
        this.giantAxe = new GiantAxe();
        this.swapWeapon = new SwapWeaponAction(giantAxe);
    }

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

    @Override
    public String hotkey() { return "G"; }

    @Override
    public String menuDescription(Actor actor) {
        return "Buy Giant Axe from Fire Keeper (" + PRICE + " souls)";
    }
}
