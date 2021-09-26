package game.ground;

import edu.monash.fit2099.engine.*;
import game.enemy.Undead;

import java.util.Random;

public class Cemetery extends Ground {
    public final static int SPAWN_PROBABILITY = 25;

    public Cemetery() {
        super('c');
    }

    @Override
    public boolean canActorEnter(Actor actor){
        return false;
    }

    @Override
    public void tick(Location location){
        Random random = new Random();
        if(random.nextDouble()*100 <= SPAWN_PROBABILITY){
            Undead undead = new Undead("Undead");
            location.addActor(undead);
        }
    }
}
