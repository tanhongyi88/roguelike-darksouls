package game.enemy;

import edu.monash.fit2099.engine.*;
import game.behaviour.*;
import game.enums.Status;
import game.interfaces.*;
import game.player.AttackAction;

import java.util.ArrayList;

public class Mimic extends Actor implements Soul {
    private ArrayList<Behaviour> behaviours = new ArrayList<>();
    private final static int MIMIC_SOULS = 200;
    private final static int MAXIMUM_CHEST_TOKEN_OF_SOUL = 3;
    /**
     * Constructor.
     */
    public Mimic() {
        super("Mimic", 'M', 100);
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(55, "kicks");
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        for(game.interfaces.Behaviour Behaviour : behaviours) {
            Action action = Behaviour.getAction(this, map);
            if (action != null){
                return action;
            }
        }
        return new WanderBehaviour();
    }

    /**
     * Returns a collection of the Actions that the otherActor can do to Mimic.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            behaviours.add(0, new AttackBehaviour(otherActor, direction));
            behaviours.add(0, new FollowBehaviour(otherActor));
            actions.add(new AttackAction(this,direction));
        }
        return actions;
    }

    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(MIMIC_SOULS);
    }

    /**
     * A toString method for the Mimic's class
     *
     * @return String that represents the Mimic's information(hitpoints and weapon)
     */
    @Override
    public String toString(){
        return name + " (" + hitPoints + "/" + maxHitPoints +")(no Weapon)";
    }
}
