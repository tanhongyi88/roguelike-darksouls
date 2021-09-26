package game;

import game.enums.Abilities;

import java.util.Random;

public class StormRuler extends GameWeaponItem{
    private static final int CRITICAL_STRIKE_PROBABILITY = 20;
    /**
     * Constructor.
     */
    public StormRuler() {
        super("Storm Ruler", '7', 70, "hits", 60);
        this.addCapability(Abilities.SWAP);
    }

    @Override
    public int damage(){
        Random random = new Random();

        if(random.nextDouble()*100 <= CRITICAL_STRIKE_PROBABILITY){
            return damage*2;
        }
        return damage;
    }
}
