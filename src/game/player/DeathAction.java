package game.player;

import edu.monash.fit2099.engine.*;
import game.enemy.*;
import game.enums.Abilities;
import game.reset.ResetAction;

/**
 * DeathAction class represents the death of an actor
 *
 * @author Lee Jia Yi
 * @version 1.0.0
 */
public class DeathAction extends Action {
    /**
     * The Actor that kills the target
     */
    protected Actor killer;

    /**
     * Constructor for the DeathAction.
     *
     * @param killer the Actor that kills the target
     */
    public DeathAction(Actor killer) {
        this.killer = killer;
    }

    /**
     * Constructor for the DeathAction.
     */
    public DeathAction(){
    }

    /**
     * Performs the DeathAction.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String that represents the DeathAction
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        if (actor instanceof Skeleton && actor.hasCapability(Abilities.RESURRECT)){
            if (((Skeleton) actor).resurrect(map)){
                result += System.lineSeparator() + actor + " is resurrected.";
            }
            else{
                result += System.lineSeparator() + actor + " is killed.";
                actor.asSoul().transferSouls(killer.asSoul());
            }
        }
        else {
            Actions dropActions = new Actions();
            // drop all items
            for (Item item : actor.getInventory())
                dropActions.add(item.getDropAction(actor));
            for (Action drop : dropActions)
                drop.execute(actor, map);

            if (!(actor instanceof Player)) {
                Location currentLocation = map.locationOf(actor);
                map.removeActor(actor);
                actor.asSoul().transferSouls(killer.asSoul());
                result += System.lineSeparator() + actor + " is killed.";

                if (actor instanceof LordOfCinder) {
                    result += System.lineSeparator() + """
                            	
                            	
                            ██╗      ██████╗ ██████╗ ██████╗      ██████╗ ███████╗     ██████╗██╗███╗   ██╗██████╗ ███████╗██████╗     ███████╗ █████╗ ██╗     ██╗     ███████╗███╗   ██╗
                            ██║     ██╔═══██╗██╔══██╗██╔══██╗    ██╔═══██╗██╔════╝    ██╔════╝██║████╗  ██║██╔══██╗██╔════╝██╔══██╗    ██╔════╝██╔══██╗██║     ██║     ██╔════╝████╗  ██║
                            ██║     ██║   ██║██████╔╝██║  ██║    ██║   ██║█████╗      ██║     ██║██╔██╗ ██║██║  ██║█████╗  ██████╔╝    █████╗  ███████║██║     ██║     █████╗  ██╔██╗ ██║
                            ██║     ██║   ██║██╔══██╗██║  ██║    ██║   ██║██╔══╝      ██║     ██║██║╚██╗██║██║  ██║██╔══╝  ██╔══██╗    ██╔══╝  ██╔══██║██║     ██║     ██╔══╝  ██║╚██╗██║
                            ███████╗╚██████╔╝██║  ██║██████╔╝    ╚██████╔╝██║         ╚██████╗██║██║ ╚████║██████╔╝███████╗██║  ██║    ██║     ██║  ██║███████╗███████╗███████╗██║ ╚████║
                            ╚══════╝ ╚═════╝ ╚═╝  ╚═╝╚═════╝      ╚═════╝ ╚═╝          ╚═════╝╚═╝╚═╝  ╚═══╝╚═════╝ ╚══════╝╚═╝  ╚═╝    ╚═╝     ╚═╝  ╚═╝╚══════╝╚══════╝╚══════╝╚═╝  ╚═══╝
                            	
                            """;
                    currentLocation.addItem(new CinderOfTheLord((LordOfCinder) actor));
                }
            }
            else{
                result += System.lineSeparator() + "YYY     YYY   .0OO000.     UU       UU     DD\"\"\"Db     III   EEEEEEEEEEE  DD\"\"\"Db";
                result += System.lineSeparator() + " YYY   YYY  00'      `00   UU       UU     DD    `Db.  III   EE           DD    `Db.";
                result += System.lineSeparator() + "  YYY YYY  OO          00  UU       UU     DD     `Db  III   EE           DD     `Db";
                result += System.lineSeparator() + "   'YYY'   OO          00  UU       UU     DD      DD  III   EEEEEEEEE    DD      DD";
                result += System.lineSeparator() + "    YYY    OO          00  UU       UU     DD      DD  III   EE           DD      DD";
                result += System.lineSeparator() + "    YYY     OO.      ,00   UU.     ,UU     DD    ,DP'  III   EE           DD    ,DP'";
                result += System.lineSeparator() + "    YYY       'OO0000'      'UUUUUUU'      DDmm,DP'    III   EEEEEEEEEEE  DDmm,DP'";
                result += System.lineSeparator() + "The world is resetting...";

                map.moveActor(actor,((Player) actor).getSpawnLocation());
                result += System.lineSeparator() + new ResetAction(((Player) actor).getPreviousLocation()).execute(actor, map);
            }
        }
        return result;
    }

    /**
     * Returns a descriptive string of DeathAction to be displayed in the menu
     *
     * @param actor The actor performing the action.
     * @return String that describes the DeathAction
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " is killed.";
    }
}
