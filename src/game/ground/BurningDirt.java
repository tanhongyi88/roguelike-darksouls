package game.ground;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * The burning dirt when Ember Form is activated.
 *
 * @author
 * @version 1.0.0
 */
public class BurningDirt extends Ground {
    private int burningAge = 0;
    private static final int BURNING_ROUNDS = 3;

    /**
     * Constructor for the BurningDirt class
     * Represented on the game map as 'v'
     */
    public BurningDirt() {
        super('v');
    }

    /**
     * Dirt is only burned for 3 rounds
     *
     * @param location The location of the burning dirt
     */
    public void tick(Location location) {
        burningAge++;
        if(burningAge <= BURNING_ROUNDS){
            displayChar = 'v';
        }else if (burningAge > BURNING_ROUNDS){
            displayChar = '.';
        }
    }
}
