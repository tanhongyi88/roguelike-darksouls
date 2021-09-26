package game.vendor;

import edu.monash.fit2099.engine.*;
import game.MeleeWeapon;
import game.player.Player;
import game.weapon.action.SwapWeaponAction;

public class VendorAction extends Action {
    private Player player;
    private String hotKey;
    private MeleeWeapon broadSword;
    private MeleeWeapon giantAxe;
    private SwapWeaponAction weaponExchange;


    public void buyAction(String hotKey, Player player, GameMap map){
        if(hotKey == "a"){
            player.subtractSouls(500);
            weaponExchange.execute(player,map);
        }
        if (hotKey == "b"){
            player.subtractSouls(1000);
            weaponExchange.execute(player, map);
        }
    }


    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return null;
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
