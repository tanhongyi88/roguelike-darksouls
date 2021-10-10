package game.enemy;

import edu.monash.fit2099.engine.*;
import game.behaviour.*;
import game.enums.Status;
import game.ground.Chest;
import game.interfaces.*;
import game.player.AttackAction;

import java.util.ArrayList;

/**
 * Mimic class represents the mimic in the game
 *
 * @author Lee Jia Yi
 * @version 1.0.0
 */
public class Mimic extends Actor implements Soul, Resettable {
    private ArrayList<Behaviour> behaviours = new ArrayList<>();
    private Location initLocation;
    private boolean isExist;
    private final static int MIMIC_SOULS = 200;

    /**
     * Constructor for the Mimic class.
     * All Mimics are represented by an 'M' and have 100 hit points.
     */
    public Mimic(Location initLocation) {
        super("Mimic", 'M', 100);
        this.initLocation = initLocation;
        this.isExist = true;
        this.registerInstance();
    }

    /**
     * Creates and returns an intrinsic weapon for Undead
     * Skeleton 'kicks' for 55 damage.
     *
     * @return A freshly-instantiated IntrinsicWeapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(55, "kicks");
    }

    /**
     * Selects and returns an action for Undead to perform on the current turn.
     *
     * @param actions    collection of possible Actions for Mimic
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return The Action to be performed by the Undead
     * @see Actor#playTurn(Actions, Action, GameMap, Display)
     */
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
            behaviours.add(1, new FollowBehaviour(otherActor));
            actions.add(new AttackAction(this,direction));
        }
        return actions;
    }

    /**
     * Transfers the souls (200 souls) to Player Soul's instance after Mimic is killed
     *
     * @param soulObject a target souls.
     */
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

    /**
     * Reset Mimic into chest.
     */
    @Override
    public void resetInstance() {
        if(isExist){
            this.hitPoints = maxHitPoints;
            initLocation.map().moveActor(this, initLocation);
            initLocation.map().removeActor(this);
            initLocation.setGround(new Chest());
            isExist = false;
        }
    }

    /**
     * Checks for the existence of Mimic in the game
     *
     * @return true is the Mimic exist; false otherwise
     */
    @Override
    public boolean isExist() {
        return this.isExist;
    }
}
