package game.ground;

import edu.monash.fit2099.engine.*;
import game.enemy.Undead;

import java.util.Random;

/**
 * The place to spawn Skeleton.
 *
 * @author Lee Jia Yi
 * @version 1.0.0
 */
public class Cemetery extends Ground {
    public final static int SPAWN_PROBABILITY = 25;

    /**
     * Constructor for the Cemetery class
     * Represented on the game map as 'c'
     */
    public Cemetery() {
        super('c');
    }

    /**
     * Checks if the actor is allowed to enter the Cemetery
     *
     * @param actor the Actor to check
     * @return false as all actors are not allowed to enter the cemetery
     */
    @Override
    public boolean canActorEnter(Actor actor){
        return false;
    }

    /**
     * Called once per turn, so that the Cemetery can spawn Skeleton at every turn
     * Has a spawn probability of 25%
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location){
        Random random = new Random();
        if(random.nextDouble()*100 <= SPAWN_PROBABILITY){
            Undead undead = new Undead("Undead");
            location.addActor(undead);
        }
    }
}

