package game;

import edu.monash.fit2099.engine.*;

public class SpinAttackAction extends WeaponAction {
    private GiantAxe giantAxe;
    private Actor target;

    public SpinAttackAction(WeaponItem giantAxe, Actor target) {
        super(giantAxe);
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        Location here = map.locationOf(actor);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.getActor() == target) {
                target.hurt(25);
                return menuDescription(target);
            }
        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " performs Spin Attack on " + target + "!";
    }
}
