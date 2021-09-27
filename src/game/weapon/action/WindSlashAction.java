package game.weapon.action;

import edu.monash.fit2099.engine.*;
import game.enums.Status;
import game.player.Player;
import game.weapon.StormRuler;
/**
 * The class representing the use of Wind Slash Action when wielding Storm Ruler
 *
 * @author Afrida Jahin
 * @version 1.0.0
 */
public class WindSlashAction extends WeaponAction {
    private StormRuler stormRuler;
    private Actor boss;

    /**
     * Constructor for WindSlashAction
     *
     * @param stormRuler The weapon using this action
     * @param actor The weapon's target
     */
    public WindSlashAction(WeaponItem stormRuler, Actor actor) {
        super(stormRuler);
        this.boss = actor;
    }

    /**
     * Performs the Charge Action
     *
     * @param actor The actor performing the action
     * @param map The map where the actor currently is
     * @return An appropriate string to show if attack has been made
     */
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

    /**
     * Return a descriptive string to show Wind Slash Attack has been made
     *
     * @param actor The actor performing the action
     * @return A string to be displayed in the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " performs Wind Slash on " + boss + "!";
    }
}
