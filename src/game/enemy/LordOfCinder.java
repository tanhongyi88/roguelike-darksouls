package game.enemy;

import edu.monash.fit2099.engine.*;
import game.player.AttackAction;
import game.behaviour.AttackBehaviour;
import game.enums.Status;
import game.interfaces.*;

import java.util.ArrayList;

/**
 * The boss of Design o' Souls
 * FIXME: This boss is Boring. It does nothing. You need to implement features here.
 * TODO: Could it be an abstract class? If so, why and how?
 */
public abstract class LordOfCinder extends Actor implements Soul{
    private ArrayList<Behaviour> behaviours = new ArrayList<>();

    /**
     * Constructor.
     */
    public LordOfCinder(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }

    /**
     * Returns a collection of the Actions that the otherActor can do to Skeleton.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            behaviours.add(0, new AttackBehaviour(otherActor, direction));
            actions.add(new AttackAction(this,direction));
        }
        return actions;
    }

    /**
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return DoNothingAction
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        for(game.interfaces.Behaviour Behaviour : behaviours) {
            Action action = Behaviour.getAction(this, map);

            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }
}
