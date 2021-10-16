package game.player;

import edu.monash.fit2099.engine.*;
import game.enemy.*;
import game.enums.Abilities;

public class DeathAction extends Action {
    protected Actor killer;

    public DeathAction(Actor killer) {
        this.killer = killer;
    }

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
        }
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " is killed.";
    }
}
