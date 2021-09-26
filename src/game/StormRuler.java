package game;

import game.enums.Abilities;

import java.util.Random;

/**
 * StormRuler class represents the Storm Ruler Weapon
 * Can only be equippes by the Player
 *
 * @author
 * @version 1.0.0
 */
public class StormRuler extends GameWeaponItem{
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
}
