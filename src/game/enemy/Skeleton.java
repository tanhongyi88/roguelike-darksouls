package game.enemy;

import edu.monash.fit2099.engine.*;
import game.behaviour.*;
import game.enums.*;
import game.interfaces.*;
import game.weapon.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Skeleton class represents the skeleton in the game
 *
 * @author Lee Jia Yi
 * @version 1.0.0
 */
public class Skeleton extends Enemy{
    private ArrayList<Location> skeletonLocation = new ArrayList<>();
    private final static int RESURRECT_PROBABILITY = 50;
    private final static int SKELETON_SOULS = 250;
    /**
     * Constructor for the Skeleton class.
     * All Skeletons are represented by an 's' and have 100 hit points.
     *
     * @param name    String that represents the name of the Skeleton
     * @param player  Actor that represents the player to follow and attack
     */
    public Skeleton(String name, Actor player) {
        super(name, 's', 100);
        addBehaviour(new WanderBehaviour());
        this.addCapability(Abilities.RESURRECT);
        this.addItemToInventory(getRandomWeapon());
    }

    /**
     * Selects and returns an action for Skeleton to perform on the current turn.
     *
     * @param actions    collection of possible Actions for Skeleton
     * @param lastAction The Action Skeleton took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing Skeleton
     * @param display    the I/O object to which messages may be written
     * @return The Action to be performed by the Skeleton
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        skeletonLocation.add(map.locationOf(this));
        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * Resurrect the Skeleton with a 50% probability
     *
     * @param map the map containing Skeleton
     * @return true if is resurrected; false otherwise
     */
    public boolean resurrect(GameMap map){
        Random random = new Random();
        boolean isResurrect = false;

        if(this.hasCapability(Abilities.RESURRECT)){
            if(random.nextDouble()*100 <= RESURRECT_PROBABILITY){
                map.moveActor(this, skeletonLocation.get(0));
                this.hitPoints = maxHitPoints;
                this.removeCapability(Abilities.RESURRECT);
                isResurrect = true;
            }
            else{
                map.removeActor(this);
            }
        }
        return isResurrect;
    }

    /**
     * Randomly returns a Weapon for Skeleton to equip
     *
     * @return A random Weapon(Broadsword, GiantAxe)
     */
    public Item getRandomWeapon(){
        Random random = new Random();
        Weapon[] weapons = {new Broadsword(), new GiantAxe()};
        return (Item) weapons[random.nextInt(weapons.length)];
    }

    /**
     * A toString method for the Skeleton's class
     *
     * @return String that represents the Skeleton's information(hitpoints and weapon)
     */
    @Override
    public String toString(){
        return name + " (" + hitPoints + "/" + maxHitPoints +")(" + getWeapon() + ")";
    }

    /**
     * Transfers the souls (250 souls) to Player Soul's instance after Skeleton is killed
     *
     * @param soulObject a target souls.
     */
    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(SKELETON_SOULS);
    }
}