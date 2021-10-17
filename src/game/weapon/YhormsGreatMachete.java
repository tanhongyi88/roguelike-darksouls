package game.weapon;

import edu.monash.fit2099.engine.Action;
import game.enums.Abilities;
import game.weapon.action.BurnGroundAction;

import java.util.ArrayList;
import java.util.List;

/**
 * YhormsGreatMachete class represents the Yhorms Great Machete Weapon
 * Can only be equipped by Yhorm the Giant
 *
 * @author Lee Jia Yi
 * @version 1.1.0
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
    @Override
    public int chanceToHit() {
        if(this.hasCapability(Abilities.EMBER_FORM)){
            return this.hitRate + 30;
        }
        return this.hitRate;
    }

    /**
     * Gets a list of allowable actions to the YhormsGreateMachete.
     *
     * @return a list of Actions
     */
    @Override
    public List<Action> getAllowableActions() {
        List<Action> actions = new ArrayList<>();
        actions.add(new BurnGroundAction(this));
        return actions;
    }

}
