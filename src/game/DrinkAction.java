package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class DrinkAction extends Action {

    private EstusFlask estusFlask;
    private Player player;

    //constructor
    public DrinkAction(Player player) {
        this.estusFlask = new EstusFlask();
        this.player = player;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if(estusFlask.getNumOfEstusFlask() < estusFlask.getMaxNumOfEstusFlask()){
            return "No available Estus Flask!";
        }
        else{
            if(player.getHitPoints() < player.getMaxHitPoints()){
                int points = player.getMaxHitPoints();
                player.heal(points);
                estusFlask.decrementNumberOfEstusFlask();
                return actor + " drinks Estus Flask!";
            }
        }
        return actor + "'s hitpoints is full!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Unkindled drinks Estus Flask (" + estusFlask.getNumOfEstusFlask() + "/" + estusFlask.getMaxNumOfEstusFlask() +")";
    }
}
