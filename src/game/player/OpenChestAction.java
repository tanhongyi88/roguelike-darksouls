package game.player;

import edu.monash.fit2099.engine.*;
import game.enemy.Mimic;
import game.ground.Dirt;
import game.reset.TokenOfSouls;
import java.util.Random;

/**
 * OpenChestAction class represents the action when Player opens a Chest
 *
 * @author Lee Jia Yi
 * @version 1.0.0
 */
public class OpenChestAction extends Action {
    private Location chestLocation;
    private Mimic mimic;
    private final static int MIMIC_PROBABILITY = 50;
    private final static int MAXIMUM_CHEST_TOKEN_OF_SOUL = 3;

    /**
     * Constructor for the OpenChestAction
     *
     * @param chestLocation the location of the Chest
     */
    public OpenChestAction(Location chestLocation) {
        this.chestLocation = chestLocation;
        this.mimic = new Mimic();
    }

    /**
     * Performs the OpenChestAction.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of that the chest has opened to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Random random = new Random();
        if(random.nextDouble()*100 <= MIMIC_PROBABILITY){
            for (int i = random.nextInt(MAXIMUM_CHEST_TOKEN_OF_SOUL); i < MAXIMUM_CHEST_TOKEN_OF_SOUL; i++){
                chestLocation.addItem(new TokenOfSouls(chestLocation.getGround().asSoul()));
            }
        }
        else{
            chestLocation.addActor(mimic);
            for (int i = random.nextInt(MAXIMUM_CHEST_TOKEN_OF_SOUL); i < MAXIMUM_CHEST_TOKEN_OF_SOUL; i++){
                mimic.addItemToInventory(new TokenOfSouls(chestLocation.getGround().asSoul()));
            }
        }
        chestLocation.setGround(new Dirt());
        return "The chest is opened!";
    }

    /**
     * Returns a descriptive string that the player opens the Chest
     *
     * @param actor The actor performing the action.
     * @return String that needs to be shown on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " opens the chest";
    }
}
