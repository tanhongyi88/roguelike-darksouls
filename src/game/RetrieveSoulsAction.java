package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.PickUpItemAction;

public class RetrieveSoulsAction extends PickUpItemAction {

    private int soulAmountToRetrieve;
    /**
     * Constructor.
     *
     * @param souls the item(Souls) to pick up
     */
    public RetrieveSoulsAction(Item souls, int soulAmountToRetrieve) {
        super(souls);
        this.soulAmountToRetrieve = soulAmountToRetrieve;
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
        map.locationOf(actor).removeItem(item);
        item.asSoul().transferSouls(actor.asSoul());
        return "Retrieved " + soulAmountToRetrieve + " souls!";
    }

    /**
     * Description text to describe this action
     *
     * @param actor The actor performing the action.
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Retrieve lost souls (" + soulAmountToRetrieve + " souls)";
    }
}
