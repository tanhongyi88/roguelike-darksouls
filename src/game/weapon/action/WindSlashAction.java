package game.weapon.action;

import edu.monash.fit2099.engine.*;
import game.enums.Status;
import game.player.Player;
import game.weapon.StormRuler;

public class WindSlashAction extends WeaponAction {
    private StormRuler stormRuler;
    private Player player;
    private Actor boss;
    private ChargeAction charge;

    public WindSlashAction(WeaponItem stormRuler, Player player, Actor actor) {
        super(stormRuler);
        this.player = player;
        this.boss = actor;
        this.charge = new ChargeAction(stormRuler, player);
    }
    public ChargeAction getCharge(){ return charge; }

    @Override
    public String execute(Actor actor, GameMap map) {

        ChargeAction charge = getCharge();

        if(charge.getWeaponCharge() ==0){
            return "Need to charge first!";
        }
        else{
            if(charge.getWeaponCharge()< charge.getMAX_CHARGE()){
                return "Storm Ruler still charging...";
            }
            if(charge.getWeaponCharge()==charge.getMAX_CHARGE()){
                Location here = map.locationOf(actor);
                for (Exit exit : here.getExits()) {
                    Location destination = exit.getDestination();
                    if (destination.getActor() == boss) {
                        stormRuler.changeHitRate(100);
                        stormRuler.changeDamage(140);
                        actor.addCapability(Status.STUN);
                        return menuDescription(boss);
                    }
                    stormRuler.changeHitRate(60);
                    stormRuler.changeDamage(70);
                }
            }
        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Unkindled performs Wind Slash on Yhorm the Giant!";
    }
}
