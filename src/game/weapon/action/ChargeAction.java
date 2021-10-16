package game.weapon.action;

import edu.monash.fit2099.engine.*;
import game.enums.Status;
import game.player.Player;
import game.weapon.StormRuler;

/**
 * The class representing the use of Charge Action when wielding Storm Ruler
 *
 * @author Afrida Jahin
 * @version 1.0.0
 */
public class ChargeAction extends WeaponAction {
    private int weaponCharge;
    private final int MAX_CHARGE = 3;

    /**
     * Constructor for ChargeAction
     *
     * @param stormRuler The weapon using this action
     */
    public ChargeAction(WeaponItem stormRuler) {
        super(stormRuler);
        this.weaponCharge = 0;
    }

    /**
     * Increase the charge number of the weapon
     */
    public void increaseCharge(){ weaponCharge +=1; }

    /**
     * Performs the Charge Action
     *
     * @param actor The actor performing the action
     * @param map The map where the actor currently is
     * @return An appropriate string to show the charging status of the weapon
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        if(weaponCharge < MAX_CHARGE){
            if(weaponCharge ==0){
                increaseCharge();
                return "Charging...(" + weaponCharge + "/" + MAX_CHARGE + ")";
            }
            if(weaponCharge>0){
                increaseCharge();
                return "Charging...(" + weaponCharge + "/" + MAX_CHARGE + ")";
            }
            actor.addCapability(Status.DISARM);
        }
        if(weaponCharge==MAX_CHARGE){
            actor.removeCapability(Status.DISARM);
            weapon.addCapability(Status.CHARGED);
            return "Charge complete!";
        }
        return null;
    }

    @Override
    public String hotkey() {
        return "C";
    }

    /**
     * Return a descriptive string to show charging status
     *
     * @param actor The actor performing the action
     * @return A string to be displayed in the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Perform Charge (" + weaponCharge + "/" + MAX_CHARGE + ")";
    }
}
