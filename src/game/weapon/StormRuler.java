package game.weapon;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.WeaponAction;
import game.enums.Abilities;
import game.enums.Status;
import game.weapon.action.ChargeAction;
import game.weapon.action.WindSlashAction;

import java.util.Random;

/**
 * StormRuler class represents the Storm Ruler Weapon
 * Can only be equipped by the Player
 *
 * @author Lee Jia Yi, Afrida Jahin
 * @version 1.1.0
 */
public class StormRuler extends GameWeaponItem {
    private static final int CRITICAL_STRIKE_PROBABILITY = 20;
    private int charge = 0;

    /**
     * Constructor for the StormRuler Weapon.
     **/
    public StormRuler() {
        super("Storm Ruler", '7', 70, "hits", 60);
        this.addCapability(Abilities.SWAP);
    }

    /**
     * Method for increasing Storm Ruler damage
     *
     * Passive skill (Critical Strike),has a 20% probability to double the damage.
     * Active skill (Wind Slash), double the original damage.
     *
     * @return int that represents the damage
     */
    @Override
    public int damage(){
        if(this.hasCapability(Status.CHARGED)){
            return this.damage = 140;
        }
        else{
            Random random = new Random();

            if(random.nextDouble()*100 <= CRITICAL_STRIKE_PROBABILITY){
                return damage*2;
            }
            return damage;
        }
    }

    /**
     * Change the hit rate of Storm Ruler based on use of Charge
     *
     * @return hitRate, updated hit rate
     */
    @Override
    public int chanceToHit() {
        if(this.hasCapability(Status.CHARGED)){
            return this.hitRate=100;
        }
        else{
            return this.hitRate=60;
        }
    }

    /**
     * Returns the active skills Charge or Wind Slash used by the Storm Ruler
     *
     * @param target the target actor
     * @param direction the direction of target, e.g. "north"
     * @return The ChargeAction or WindSlashAction
     */
    @Override
    public WeaponAction getActiveSkill(Actor target, String direction) {
        if(this.hasCapability(Status.CHARGED)){
            return new WindSlashAction(this);
        }
        else{
            return new ChargeAction(this);
        }
    }

    /**
     * Returns the number of charge for Storm Ruler
     *
     * @return int that represents the charge
     */
    public int getCharge() {
        return charge;
    }

    /**
     * Set the number of charge for Storm Ruler
     *
     * @param newCharge the new number of charge
     */
    public void setCharge(int newCharge) {
        this.charge = newCharge;
    }
}
