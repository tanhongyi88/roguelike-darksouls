package game.ground;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.enums.Abilities;

public class FireKeeper extends Ground {

    public FireKeeper() {
        super('F');
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();
        if (actor.hasCapability(Abilities.BUY)) {
            //actions.add(new BuySwordAction(actor), new BuyAxeAction(actor));
        }
        return actions;
    }
}
