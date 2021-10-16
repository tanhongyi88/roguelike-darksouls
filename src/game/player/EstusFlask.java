package game.player;

import edu.monash.fit2099.engine.*;
import game.interfaces.Resettable;

/**
 * EstusFlask class represents the Estus Flask for the player
 *
 * @author Lee Jia Yi
 * @version 1.0.1
 */
public class EstusFlask extends Item implements Resettable {
    private int numOfEstusFlask;
    private final int MAX_NUM_OF_ESTUS_FLASK = 3;

    /**
     * Constructor for the Estus Flask.
     */
    public EstusFlask() {
        super("Estus Flask", 'e', false);
        this.numOfEstusFlask = MAX_NUM_OF_ESTUS_FLASK;
        this.registerInstance();
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

    @Override
    public void resetInstance() {
        this.numOfEstusFlask = MAX_NUM_OF_ESTUS_FLASK;
    }

    @Override
    public boolean isExist() {
        return true;
    }
}
