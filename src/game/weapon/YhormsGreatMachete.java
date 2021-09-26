package game.weapon;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.weapon.action.EmberFormAction;

/**
 * YhormsGreatMachete class represents the Yhorms Great Machete Weapon
 * Can only be equipped by Yhorm the Giant
 *
 * @author
 * @version 1.0.0
 */
public class YhormsGreatMachete extends GameWeaponItem {

    /**
     * Constructor for the YhormsGreatMachete Weapon.
     **/
    public YhormsGreatMachete() {
        super("Yhorm's Great Machete", 'y', 95, "strucks", 60);
    }

    /**
     * Passive skill (Rage Mode) for the Yhorms Great Machete.
     * Increases the success hit rate by 30% (hit rate: 60% + 30% = 90%).
     *
     * @return int that represents the hit rate of the weapon
     */
    public void increaseHitRateWhenEmberForm() {
        this.hitRate = 90;
    }

}
