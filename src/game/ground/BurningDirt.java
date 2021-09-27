package game.ground;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * This class represents the temporary new ground, the burning dirt when Ember Form is activated.
 *
 * @author Tan Hong Yi
 * @version 1.0.0
 */
public class BurningDirt extends Ground {
    /**
     * burningAge stores how many rounds the dirt has been burning
     */
    private int burningAge = 0;

    /**
     * BURNING_ROUNDS stores the maximum rounds dirt ground can be burned
     */
    private static final int BURNING_ROUNDS = 3;

    /**
     * Constructor for the BurningDirt class
     * Represented on the game map as 'v'
     */
    public BurningDirt() {
        super('v');
    }

    /**
     * Dirt starts burning
     *
     * @param location The location of centre of burning area
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
