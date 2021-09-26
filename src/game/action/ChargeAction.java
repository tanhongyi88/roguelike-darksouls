package game.action;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponAction;
import edu.monash.fit2099.engine.WeaponItem;
import game.enums.Status;
import game.player.Player;
import game.weapon.StormRuler;

public class ChargeAction extends WeaponAction {
    private StormRuler stormRuler;
    private Player player;
    private int weaponCharge;
    private final int MAX_CHARGE = 3;

    public ChargeAction(WeaponItem stormRuler, Player player) {
        super(stormRuler);
        this.player = player;
        this.weaponCharge = 0;
    }
    public int getWeaponCharge(){
        return weaponCharge;
    }
    public int getMAX_CHARGE(){
        return MAX_CHARGE;
    }
    public void increaseCharge(){ weaponCharge +=1; }

    @Override
    public String execute(Actor actor, GameMap map) {

        if(weaponCharge < MAX_CHARGE){
            if(weaponCharge ==0){
                increaseCharge();
                return actor + " begins to charge Storm Ruler";
            }
            if(weaponCharge>0){
                increaseCharge();
                return actor + " continues to charge Storm Ruler";
            }
            actor.addCapability(Status.DISARM);
        }
        if(weaponCharge==MAX_CHARGE){
            actor.removeCapability(Status.DISARM);
            return "Charge complete!";
        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Unkindled charges Storm Ruler (" + weaponCharge + "/" + MAX_CHARGE + ")";
    }
}
