package game.weapon.action;

import edu.monash.fit2099.engine.*;
import game.enums.Status;
import game.player.Player;
import game.weapon.StormRuler;

public class WindSlashAction extends WeaponAction {
    private StormRuler stormRuler;
    private Actor boss;

    public WindSlashAction(WeaponItem stormRuler, Actor actor) {
        super(stormRuler);
        this.boss = actor;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if(stormRuler.hasCapability(Status.CHARGED)){
            Location here = map.locationOf(actor);
            for (Exit exit : here.getExits()) {
                Location destination = exit.getDestination();
                if (destination.getActor() == boss) {
                    stormRuler.changeHitRate(100);
                    stormRuler.changeDamage(140);
                    actor.addCapability(Status.STUN);
                    return menuDescription(actor);
                }
                stormRuler.changeHitRate(60);
                stormRuler.changeDamage(70);
            }
        }
        else{
            return "Not fully charged yet!";
        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " performs Wind Slash on " + boss + "!";
    }
}
