package game.weapon.action;

import edu.monash.fit2099.engine.*;
import game.enums.Status;
import game.weapon.StormRuler;

/**
 * The class representing the use of Charge Action when wielding Storm Ruler
 *
 * @author Afrida Jahin
 * @version 1.1.0
 */
public class ChargeAction extends WeaponAction {
    private int weaponCharge;
    private final int MAX_CHARGE = 3;

    /**
     * Constructor for ChargeAction
     *
     * @param stormRuler The weapon using this action
     */
    public ChargeAction(StormRuler stormRuler) {
        super(stormRuler);
        this.weaponCharge = stormRuler.getCharge();
    }

    /**
     * Performs the Charge Action
     *
     * @param actor The actor performing the action
     * @param map The map where the actor currently is
     * @return An appropriate string to show the charging status of the weapon
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        StormRuler stormRuler = (StormRuler) weapon;
        if(weaponCharge < MAX_CHARGE){
            this.weaponCharge += 1;
            stormRuler.setCharge(weaponCharge);
            actor.addCapability(Status.DISARM);
            result = "Charging...(" + weaponCharge + "/" + MAX_CHARGE + ")";
        }

        if (weaponCharge==MAX_CHARGE){
            actor.removeCapability(Status.DISARM);
            weapon.addCapability(Status.CHARGED);
            stormRuler.setCharge(0);
            return "Charge complete!";
        }
        return result;
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
