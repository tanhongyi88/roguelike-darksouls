package game.vendor;

import edu.monash.fit2099.engine.*;
import game.player.Player;
import game.weapon.Broadsword;
import game.weapon.action.SwapWeaponAction;

public class BuySwordAction extends Action {
    //private Actor actor;
    private Player player;
    private Broadsword broadSword;
    private SwapWeaponAction swapWeapon;
    private int soulPrice;

    public BuySwordAction(Player player){
        this.player = player;
        this.broadSword = new Broadsword();
        this.swapWeapon = new SwapWeaponAction(broadSword);
        this.soulPrice = 500;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        int playerSouls = player.getSouls();

        if (playerSouls!= soulPrice){
            return "Purchase failed: Not enough souls!";
        }
        else{
            player.subtractSouls(soulPrice);
            swapWeapon.execute(player,map);
            return "Purchase successful: Broad Sword received!";
        }
    }

    @Override
    public String hotkey() { return "B"; }

    @Override
    public String menuDescription(Actor actor) {
        return "Buy Broad Sword from Fire Keeper (" + soulPrice + " souls)";
    }
}
