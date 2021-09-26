package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.player.Player;
import game.weapon.GiantAxe;

public class BuyAxeAction extends Action {
    private Player player;
    private GiantAxe giantAxe;
    private SwapWeaponAction swapWeapon;
    private int soulPrice;

    public BuyAxeAction(Player player){
        this.player = player;
        this.giantAxe = new GiantAxe();
        this.swapWeapon = new SwapWeaponAction(giantAxe);
        this.soulPrice = 1000;
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
            return "Purchase successful: Giant Axe received!";
        }
    }

    @Override
    public String hotkey() { return "G"; }

    @Override
    public String menuDescription(Actor actor) {
        return "Buy Giant Axe from Fire Keeper (" + soulPrice + " souls)";
    }
}
