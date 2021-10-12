package game.weapon;

import game.enums.Abilities;

import java.util.Random;

public class DarkmoonLongbow extends GameWeaponItem{
    private static final int CRITICAL_STRIKE_PROBABILITY = 15;

    /**
     * Constructor for the Longbow
     */
    public DarkmoonLongbow(){
        super("Darkmoon Longbow", 'd', 70, "pierces", 80);
        this.addCapability(Abilities.SWAP);
    }

    /**
     * Passive skill (Critical Strike) for the Broadsword.
     * Has a 15% probability to double the damage
     *
     * @return int that represents the damage
     */
    @Override
    public int damage() {
        Random random = new Random();

        if(random.nextDouble()*100 <= CRITICAL_STRIKE_PROBABILITY){
            return damage*2;
        }
        return damage;
    }
}
