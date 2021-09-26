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
 * @author
 * @version 1.0.0
 */
public class StormRuler extends GameWeaponItem {
    private static final int CRITICAL_STRIKE_PROBABILITY = 20;

    /**
     * Constructor for the StormRuler Weapon.
     **/
    public StormRuler() {
        super("Storm Ruler", '7', 70, "hits", 60);
        this.addCapability(Abilities.SWAP);
    }

    /**
     * Passive skill (Critical Strike) for the Storm Ruler.
     * Has a 20% probability to double the damage
     *
     * @return int that represents the damage
     */
    @Override
    public int damage(){
        Random random = new Random();

        if(random.nextDouble()*100 <= CRITICAL_STRIKE_PROBABILITY){
            return damage*2;
        }
        return damage;
    }

    public void changeHitRate(int hitRate){
        this.hitRate = hitRate;
    }

    public void changeDamage(int damage){
        this.damage = damage;
    }

    @Override
    public WeaponAction getActiveSkill(Actor target, String direction) {
        if(this.hasCapability(Status.CHARGED)){
            return new WindSlashAction(this,target);
        }
        else{
            return new ChargeAction(this);
        }
    }
}
