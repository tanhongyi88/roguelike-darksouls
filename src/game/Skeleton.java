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
    private ActorLocations locations = new ActorLocations();
    private final static int RESURRECT_PROBABILITY = 50;
    private final static int SKELETON_SOULS = 250;

    /**
     * Constructor.
     * All Skeletons are represented by an 's' and have 100 hit points.
     * @param name        the name of the Skeleton
     */
    public Skeleton(String name, Actor player) {
        super(name, 's', 100);
        behaviours.add(new NormalAttack(player));
        behaviours.add(new FollowBehaviour(player));
        behaviours.add(new WanderBehaviour());
        locations.locationOf(this);
        this.addCapability(Abilities.RESURRECT);
//        this.addItemToInventory();
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
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        // loop through all behaviours
        for(Behaviour Behaviour : behaviours) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;

        }
        return new DoNothingAction();
    }

    public void resurrect(Skeleton skeleton){
        Random random = new Random();

        if(skeleton.hasCapability(Abilities.RESURRECT)){
            if(random.nextDouble()*100 <= RESURRECT_PROBABILITY){
                skeleton.removeCapability(Abilities.RESURRECT);
            }
        }
    }

    public void transferSouls(Soul soul) {
        soul.addSouls(SKELETON_SOULS);
    }
}
