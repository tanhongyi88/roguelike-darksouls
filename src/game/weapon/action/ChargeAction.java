package game.weapon.action;

import edu.monash.fit2099.engine.*;
import game.enums.Status;
import game.player.Player;
import game.weapon.StormRuler;

public class ChargeAction extends WeaponAction {
    private StormRuler stormRuler;
    private int weaponCharge;
    private final int MAX_CHARGE = 3;

    public ChargeAction(WeaponItem stormRuler) {
        super(stormRuler);
        this.weaponCharge = 0;
    }

    public void increaseCharge(){ weaponCharge +=1; }

    @Override
    public String execute(Actor actor, GameMap map) {

        if(weaponCharge < MAX_CHARGE){
            if(weaponCharge ==0){
                increaseCharge();
                return menuDescription(actor);
            }
            if(weaponCharge>0){
                increaseCharge();
                return menuDescription(actor);
            }
            actor.addCapability(Status.DISARM);
        }
        if(weaponCharge==MAX_CHARGE){
            actor.removeCapability(Status.DISARM);
            stormRuler.addCapability(Status.CHARGED);
            return "Charge complete!";
        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " charges Storm Ruler (" + weaponCharge + "/" + MAX_CHARGE + ")";
    }
}
