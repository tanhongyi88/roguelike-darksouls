package game.weapon;

import game.enums.Abilities;

import java.util.Random;

/**
 * Broadsword class represents the Broadsword Weapon
 *
 * @author Lee Jia Yi, Afrida Jahin
 * @version 1.0.0
 */
public class Broadsword extends GameWeaponItem {
    private static final int CRITICAL_STRIKE_PROBABILITY = 20;

    /**
     * Constructor for the Broadsword Weapon.
     **/
    public Broadsword() {
        super("Broadsword", 'b', 30, "slashes", 80);
        this.addCapability(Abilities.SWAP);
    }

    /**
     * Passive skill (Critical Strike) for the Broadsword.
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
}
