package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.WeaponAction;
import game.enums.Abilities;

public class YhormsGreatMachete extends GameWeaponItem{

    /**
     * Constructor
     */
    public YhormsGreatMachete() {
        super("Yhorm's Great Machete", 'y', 95, "hit", 60);
    }

    public int chanceToHit(){
        if(this.hasCapability(Abilities.EMBER_FORM)){
            return this.hitRate + 30;
        }
        return this.hitRate;
    }

    @Override
    public WeaponAction getActiveSkill(Actor target, String direction){
        return null;
    }
}
