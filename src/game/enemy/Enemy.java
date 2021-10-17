package game.enemy;

import edu.monash.fit2099.engine.*;
import game.behaviour.*;
import game.enums.Status;
import game.interfaces.*;
import game.player.AttackAction;

import java.util.ArrayList;

/**
 * Abstract Enemy class represents the enemy in the game
 *
 * @author Lee Jia Yi
 * @version 1.0.0
 */
public abstract class Enemy extends Actor implements Soul {
    private ArrayList<Behaviour> behaviours = new ArrayList<>();

    /**
     * Constructor for the Enemy class.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }

    /**
     * Returns a collection of the Actions that the otherActor can do to Enemy.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            behaviours.add(0, new AttackBehaviour(otherActor, direction));
            behaviours.add(1, new FollowBehaviour(otherActor));
            actions.add(new AttackAction(this,direction));
        }
        return actions;
    }

    /**
     * Selects and returns an action for Lord of Cinder to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return DoNothingAction
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        for(Behaviour Behaviour : behaviours) {
            Action action = Behaviour.getAction(this, map);

            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * Adds the behaviour of an enemy into the list of behaviours.
     *
     * @param behaviour The behaviour to be added
     */
    protected void addBehaviour(Behaviour behaviour) {
        behaviours.add(behaviour);
    }
}
