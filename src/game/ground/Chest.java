package game.ground;

import edu.monash.fit2099.engine.*;
import game.interfaces.Soul;
import game.player.OpenChestAction;

public class Chest extends Ground implements Soul {
    private final static int CHEST_SOULS = 100;

    public Chest() {
        super('?');
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction){
        Actions actions = new Actions();
        actions.add(new OpenChestAction(location));
        return actions;
    }

    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(CHEST_SOULS);
    }
}
