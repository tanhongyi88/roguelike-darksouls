package game.player;

import edu.monash.fit2099.engine.*;
import game.interfaces.Consumable;
import game.interfaces.Resettable;

import java.util.ArrayList;
import java.util.List;

/**
 * EstusFlask class represents the Estus Flask for the player
 *
 * @author Lee Jia Yi
 * @version 1.1.0
 */
public class EstusFlask extends Item implements Resettable, Consumable {
    private int numOfEstusFlask;
    private final int MAX_NUM_OF_ESTUS_FLASK = 3;
    private Player player;

    /**
     * Constructor for the Estus Flask.
     */
    public EstusFlask(Player player) {
        super("Estus Flask", 'e', false);
        this.numOfEstusFlask = MAX_NUM_OF_ESTUS_FLASK;
        this.player = player;
        this.registerInstance();
    }

    /**
     * Gets a list of allowable actions to the Estus Flask.
     *
     * @return a list of Actions
     */
    @Override
    public List<Action> getAllowableActions() {
        List<Action> actions = new ArrayList<>();
        actions.add(new DrinkAction(player, this));
        return actions;
    }

    /**
     * Returns the number of Estus Flask available
     *
     * @return int that represents the number of Estus Flask
     */
    @Override
    public int getNumberOfConsumable() {
        return numOfEstusFlask;
    }

    /**
     * Returns the maximum number of Estus Flask in a game.
     * By default, the maximum is 3.
     *
     * @return int that represents the maximum number of Estus Flask
     */
    @Override
    public int getMaximumConsumable() {
        return MAX_NUM_OF_ESTUS_FLASK;
    }

    /**
     * Reduces the number of Estus Flask by 1.
     */
    @Override
    public void useConsumable() {
        this.numOfEstusFlask -= 1;
    }

    /**
     * Resets the number of Estus FLask to maximum(3).
     */
    @Override
    public void resetInstance() {
        this.numOfEstusFlask = MAX_NUM_OF_ESTUS_FLASK;
    }

    /**
     * Checks for the existence of Estus Flask in the game
     *
     * @return true as Estus Flask always exist
     */
    @Override
    public boolean isExist() {
        return true;
    }
}
