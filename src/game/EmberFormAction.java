package game;

import edu.monash.fit2099.engine.*;

public class EmberFormAction extends WeaponAction {
    private YhormsGreatMachete greatMachete;
    private final static int BURN_DAMAGE = 25;
    private Player player;
    private int counter = 1;
    private Location burningLocation;
    private boolean burnStart;


    public EmberFormAction(WeaponItem greatMachete, Player player) {
        super(greatMachete);
        this.player = player;
        this.burnStart = false;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (!burnStart) {
            this.burningLocation = map.locationOf(actor);
            this.burnStart = true;
        }
        if (burnStart) {
            for (Exit exit : this.burningLocation.getExits()) {
                Location destination = exit.getDestination();
                if (destination.getGround() instanceof Dirt && this.counter <= 3) {


                }
                if (destination.getActor() instanceof Player) {
                    destination.getActor().hurt(BURN_DAMAGE);


                }
            }
        }

        this.counter++;
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + "burns the area and skin is engulfed with fire (Ember Form)";
    }
}
