package game.reset;

import edu.monash.fit2099.engine.*;

/**
 * RetrieveSoulsAction is to collect the lost souls that had dropped when the player died
 *
 * @author Tan Hong Yi
 * @version 1.0.0
 */
public class RetrieveSoulsAction extends PickUpItemAction {

    /**
     * soulAmountToRetrieve stores the number of souls that Player had before player dies
     */
    private int soulAmountToRetrieve;

    /**
     * Constructor
     *
     * @param souls souls object from player
     * @param soulAmountToRetrieve number of souls that player had before player dies
     */
    public RetrieveSoulsAction(Item souls, int soulAmountToRetrieve) {
        super(souls);
        this.soulAmountToRetrieve = soulAmountToRetrieve;
    }

    /**
     * Retrieve souls and remove the souls item from the ground
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String that describes how much of souls being retrieved
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).removeItem(item);
        item.asSoul().transferSouls(actor.asSoul());
        return "Retrieved " + soulAmountToRetrieve + " souls!";
    }

    /**
     * Description text to describe retrieve soul action option in console.
     *
     * @param actor The actor performing the action.
     * @return String that describes the action in menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Retrieve lost souls (" + soulAmountToRetrieve + " souls)";
    }
}
