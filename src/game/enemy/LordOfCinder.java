package game.enemy;

import edu.monash.fit2099.engine.*;
import game.behaviour.FollowBehaviour;
import game.player.AttackAction;
import game.behaviour.AttackBehaviour;
import game.enums.Status;
import game.interfaces.*;

import java.util.ArrayList;

/**
 * The boss of Design o' Souls
 * An abstract class where all the boss in the game can inherits from
 *
 * @author Lee Jia Yi
 * @version 1.1.0
 */
public abstract class LordOfCinder extends Actor implements Soul{
    private ArrayList<Behaviour> behaviours = new ArrayList<>();
    private final static int LORD_OF_CINDER_SOULS = 5000;

    /**
     * Constructor for the Lord of Cinder class
     *
     * @param name          the name of the boss
     * @param displayChar   the character that will represent the boss in the display
     * @param hitPoints     the boss's starting hit points
     */
    public LordOfCinder(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }

    /**
     * Returns the name of the Lord Of Cinder
     *
     * @return String that represents the name
     */
    public String getName(){
        return this.name;
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
     * Transfers the souls (5000 souls) to Player Soul's instance after Lord of Cinder is killed
     *
     * @param playerSoul the player souls.
     */
    public void transferSouls(Soul playerSoul) {
        playerSoul.addSouls(LORD_OF_CINDER_SOULS);
    }

    /**
     * A toString method for the Lord Of Cinder's class
     *
     * @return String that represents the Lord Of Cinder's information(hitpoints and weapon)
     */
    public String toString(){
        return name + " (" + hitPoints + "/" + maxHitPoints +")(" + getWeapon() + ")";
    }
}
