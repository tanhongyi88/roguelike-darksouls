package game;

import edu.monash.fit2099.engine.*;
import game.interfaces.Resettable;

public class EstusFlask extends Item {
    private int numOfEstusFlask;
    private final int MAX_NUM_OF_ESTUS_FLASK = 3;

    public EstusFlask(String name) {
        super(name, 'e', false);
        this.numOfEstusFlask = MAX_NUM_OF_ESTUS_FLASK;
    }

    public int getNumOfEstusFlask() {
        return numOfEstusFlask;
    }

    public int getMaxNumOfEstusFlask(){
        return MAX_NUM_OF_ESTUS_FLASK;
    }

    public void decrementNumberOfEstusFlask(){
        numOfEstusFlask -= 1;
    }

    public void refillEstusFlask() {
        this.numOfEstusFlask = MAX_NUM_OF_ESTUS_FLASK;
    }
}
