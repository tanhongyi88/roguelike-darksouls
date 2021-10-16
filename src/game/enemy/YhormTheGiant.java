package game.enemy;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.weapon.YhormsGreatMachete;
import game.enums.Status;
import game.weapon.action.BurnGroundAction;

/**
 * YhormTheGiant class represents the first boss in the game
 *
 * @author Lee Jia Yi, Tan Hong Yi
 * @version 1.1.0
 */
public class YhormTheGiant extends LordOfCinder {
    private YhormsGreatMachete greatMachete;

    /**
     * Constructor for the Undead class.
     * All Yhorm The Giant are represented by an 'Y' and have 500 hit points.
     *
     * @param name          the name of the boss
     * @param displayChar   the character that will represent the boss in the display
     * @param hitPoints     the boss's starting hit points
     */
    public YhormTheGiant(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.WEAK_TO_STORM_RULER);
        this.greatMachete = new YhormsGreatMachete();
        this.addItemToInventory(this.greatMachete);
    }

    /**
     * Selects and returns an action for Yhorm The Giant to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return A collection of Actions
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if(this.hasCapability(Status.STUN)){
            this.removeCapability(Status.STUN);
            return new DoNothingAction();
        }
        if(this.getHitPoints() < this.getMaxHitPoints()/2) {
            this.greatMachete.addCapability(Abilities.EMBER_FORM);
            display.println("Raargh~!!");
            return new BurnGroundAction(this.greatMachete);
        }

        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * Returns the hitpoints for Yhorm The Giant
     *
     * @return int that represents the hitpoints
     */
    private int getHitPoints() {
        return this.hitPoints;
    }

    /**
     * Returns the maximum hitpoints for Yhorm The Giant
     *
     * @return int that represents the maximum hitpoints
     */
    private int getMaxHitPoints() {
        return this.maxHitPoints;
    }
}
