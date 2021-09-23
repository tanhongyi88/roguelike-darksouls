package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Soul;

import java.util.ArrayList;
import java.util.Random;

public class Skeleton extends Actor implements Soul{
    private ArrayList<Behaviour> behaviours = new ArrayList<>();
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
        behaviours.add(new UndeadAttack(player));
        behaviours.add(new FollowBehaviour(player));
        behaviours.add(new WanderBehaviour());
        this.addCapability(Abilities.RESURRECT);
        this.addItemToInventory(getRandomWeapon());
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
            actions.add(new AttackAction(this,direction));
        }
        return actions;
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

        for(Behaviour Behaviour : behaviours) {
            Action action = Behaviour.getAction(this, map);
            if (lastAction.getNextAction() != null)
                return lastAction.getNextAction();

            else if (action != null)
                return action;
        }
        return new WanderBehaviour();
    }

    /**
     * Resurrect the Skeleton with a 50% probability
     *
     * @param map the map containing Skeleton
     * @return True if is resurrected; false otherwise
     */
    public boolean resurrect(GameMap map){
        Random random = new Random();
        Boolean isResurrect = false;

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
     * Transfers the souls to the player after Skeleton is killed
     *
     * @param soul Soul that represents the player's soul
     */
    @Override
    public void transferSouls(Soul soul) {
        soul.addSouls(SKELETON_SOULS);
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
}