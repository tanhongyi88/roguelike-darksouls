package game;

import edu.monash.fit2099.engine.*;

/**
 * EstusFlask class represents the Estus Flask for the player
 *
 * @author
 * @version 1.0.0
 */
public class EstusFlask extends Item {
    private int numOfEstusFlask;
    private final int MAX_NUM_OF_ESTUS_FLASK = 3;

    /**
     * Constructor for the Estus Flask.
     *
     * @param name The name of the Estus Flask
     */
    public EstusFlask(String name) {
        super(name, 'e', false);
        this.numOfEstusFlask = MAX_NUM_OF_ESTUS_FLASK;
    }

    /**
     * Returns the number of Estus Flask available
     *
     * @return int that represents the number of Estus Flask
     */
    public int getNumOfEstusFlask() {
        return numOfEstusFlask;
    }

    /**
     * Returns the maximum number of Estus Flask in a game.
     * By default, the maximum is 3.
     *
     * @return int that represents the maximum number of Estus Flask
     */
    public int getMaxNumOfEstusFlask(){
        return MAX_NUM_OF_ESTUS_FLASK;
    }

    /**
     * Reduces the number of Estus Flask by 1.
     */
    public void decrementNumberOfEstusFlask(){
        numOfEstusFlask -= 1;
    }

    /**
     * Refills the number of Estus Flask to the maximum number of Estus Flask.
     */
    public void refillEstusFlask() {
        this.numOfEstusFlask = MAX_NUM_OF_ESTUS_FLASK;
    }
}
