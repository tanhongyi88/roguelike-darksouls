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
     * Constructor.
     * All Skeletons are represented by an 's' and have 100 hit points.
     * @param name        the name of the Skeleton
     */
    public Skeleton(String name, Actor player) {
        super(name, 's', 10);
        behaviours.add(new UndeadAttack(player));
        behaviours.add(new FollowBehaviour(player));
        behaviours.add(new WanderBehaviour());
        this.addCapability(Abilities.RESURRECT);
        this.addItemToInventory(getRandomWeapon());
    }

    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this,direction));
        }
        return actions;
    }


    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        skeletonLocation.add(map.locationOf(this));
//        if (lastAction.getNextAction() != null)
//            return lastAction.getNextAction();

        // loop through all behaviours
        for(Behaviour Behaviour : behaviours) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new WanderBehaviour();
    }

    public boolean resurrect(Skeleton skeleton, GameMap map){
        Random random = new Random();
        Boolean isResurrect = false;

        if(skeleton.hasCapability(Abilities.RESURRECT)){
            if(random.nextDouble()*100 <= RESURRECT_PROBABILITY){
                map.moveActor(skeleton, skeletonLocation.get(0));
                skeleton.hitPoints = maxHitPoints;
                skeleton.removeCapability(Abilities.RESURRECT);
                isResurrect = true;
            }
            else{
                map.removeActor(skeleton);
            }
        }
        return isResurrect;
    }

    public void transferSouls(Soul soul) {
        soul.addSouls(SKELETON_SOULS);
    }

    public Item getRandomWeapon(){
        Random random = new Random();
        Weapon[] weapons = {new Broadsword(), new GiantAxe()};
        return (Item) weapons[random.nextInt(weapons.length)];
    }

    public String toString(){
        return name + " (" + hitPoints + "/" + maxHitPoints +")(" + getWeapon() + ")";
    }
}