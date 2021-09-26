package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.PickUpItemAction;

public class RetrieveSoulsAction extends PickUpItemAction {

    /**
     * Constructor.
     *
     * @param souls the item(Souls) to pick up
     */
    public RetrieveSoulsAction(Item souls) {
        super(souls);
    }

    /**
     * Retrieve soul into inventory
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return super.execute(actor, map);
    }


    //    @Override
//    public String menuDescription(Actor actor) {
//        return "Retrieve lost souls (" + item.
//    }
}
