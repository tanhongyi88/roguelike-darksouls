package game.weapon.action;

import edu.monash.fit2099.engine.*;
import game.weapon.YhormsGreatMachete;
import game.player.Player;

public class EmberFormAction extends WeaponAction {
    private YhormsGreatMachete greatMachete;
    private Player player;


    public EmberFormAction(WeaponItem greatMachete, Player player) {
        super(greatMachete);
        this.player = player;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.getActor() == player) {
                greatMachete.changeHitRate(90);
                return menuDescription(player);
            }
            greatMachete.changeHitRate(60);
        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Yhorm the Giant attacks " + actor+ " with active Ember Form!";
    }
}
