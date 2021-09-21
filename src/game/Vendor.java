package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class Vendor extends Action {
    private Player player;
    private Item shopItem;
    private char displayChar;
    private String hotKey;
    private Souls soul;
    private MeleeWeapon broadSword;
    private MeleeWeapon giantAxe;


    public void buyAction(String hotKey, Player player){
        if(hotKey == "a"){
            player.getInventory();
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
