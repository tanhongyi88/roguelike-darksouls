package game.ground;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.player.Player;
import game.vendor.BuyAxeAction;
import game.vendor.BuySwordAction;

public class FireKeeper extends Ground {

    public FireKeeper() {
        super('F');
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();
        if (actor.hasCapability(Abilities.BUY)) {
            actions.add(new BuySwordAction((Player) actor));
            actions.add(new BuyAxeAction((Player) actor));
        }
        return actions;
    }
}
