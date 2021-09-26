package game.enemy;

import edu.monash.fit2099.engine.*;
import game.behaviour.FollowBehaviour;
import game.enums.Abilities;
import game.weapon.YhormsGreatMachete;
import game.enums.Status;
import game.interfaces.*;
import game.weapon.action.EmberFormAction;
import java.util.ArrayList;

/**
 * YhormTheGiant class represents the first boss in the game
 *
 * @author
 * @version 1.0.0
 */
public class YhormTheGiant extends LordOfCinder {
    private ArrayList<Behaviour> behaviours = new ArrayList<>();
    private final static int YHORM_THE_GIANT_SOULS = 5000;

    /**
     * Constructor for the Undead class.
     * All Yhorm The Giant are represented by an 'Y' and have 500 hit points.
     *
     * @param name          the name of the boss
     * @param displayChar   the character that will represent the boss in the display
     * @param hitPoints     the boss's starting hit points
     * @param player        Actor that represents the player to follow and attack
     */
    public YhormTheGiant(String name, char displayChar, int hitPoints, Actor player) {
        super(name, displayChar, hitPoints);
        behaviours.add(new FollowBehaviour(player));
        this.addCapability(Status.WEAK_TO_STORM_RULER);
        this.addItemToInventory(new YhormsGreatMachete());
    }

    /**
     * Transfers the souls (5000 souls) to Player Soul's instance after Yhorm The Giant is killed
     *
     * @param playerSoul the player souls.
     */
    @Override
    public void transferSouls(Soul playerSoul) {
        playerSoul.addSouls(YHORM_THE_GIANT_SOULS);
    }

    /**
     * Selects and returns an action for Yhorm The Giant to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if(this.hasCapability(Status.STUN)){
            this.removeCapability(Status.STUN);
            return new DoNothingAction();
        }
        if(this.getHitPoints() < this.getMaxHitPoints()/2) {
            this.addCapability(Abilities.EMBER_FORM);
            display.println("Raargh~!!");
            return new EmberFormAction();
        }

        return super.playTurn(actions, lastAction, map, display);
    }

    private int getHitPoints() {
        return this.hitPoints;
    }

    private int getMaxHitPoints() {
        return this.maxHitPoints;
    }

    /**
     * A toString method for the Yhorm The Giant's class
     *
     * @return String that represents the Yhorm The Giant's information(hitpoints and weapon)
     */
    public String toString(){
        return name + " (" + hitPoints + "/" + maxHitPoints +")(" + getWeapon() + ")";
    }
}
