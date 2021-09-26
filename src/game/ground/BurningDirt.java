package game.ground;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

public class BurningDirt extends Ground {
    private int burningAge = 0;
    private static final int BURNING_ROUNDS = 3;

    public BurningDirt() {
        super('v');
    }

    /**
     *
     * @param location The location of the Ground
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
