package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.WeaponAction;

public class YhormsGreatMachete extends GameWeaponItem{

    /**
     * Constructor
     */
    public YhormsGreatMachete() {
        super("Yhorm's Great Machete", 'y', 95, "hit", 60);
    }

    @Override
    public WeaponAction getActiveSkill(Actor target, String direction){
        return null;
    }
}
