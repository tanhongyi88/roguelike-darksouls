package game.enemy;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;

/**
 * CinderOfTheLord class represents the corpse of the boss in the game
 *
 * @author Lee Jia Yi
 * @version 1.0.0
 */
public class CinderOfTheLord extends Item {
    /***
     * Constructor for the Cinder Of Lord (corpse) class
     *
     * @param lord the Lord Of Cinder that drops the Cinder of The Lord
     */
    public CinderOfTheLord(LordOfCinder lord) {
        super("Cinder of " + lord.getName(), '%', true);
        this.addCapability(Abilities.TRADE);
    }
}
