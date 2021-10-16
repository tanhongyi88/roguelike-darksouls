package game.enemy;

import edu.monash.fit2099.engine.*;
import game.behaviour.RangedWeaponBehaviour;
import game.weapon.DarkmoonLongbow;

/**
 * AldrichTheDevourer class represents the second boss in the game
 *
 * @author Lee Jia Yi
 * @version 1.0.0
 */
public class AldrichTheDevourer extends LordOfCinder{

    /**
     * Constructor for the Aldrich The Devourer class
     *
     * @param name        the name of the boss
     * @param displayChar the character that will represent the boss in the display
     * @param hitPoints   the boss's starting hit points
     */
    public AldrichTheDevourer(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addItemToInventory(new DarkmoonLongbow());
    }

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        addBehaviour(new RangedWeaponBehaviour(otherActor));
        return super.getAllowableActions(otherActor, direction, map);
    }
}
