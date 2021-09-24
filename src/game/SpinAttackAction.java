package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponAction;
import edu.monash.fit2099.engine.WeaponItem;

public class SpinAttackAction extends WeaponAction {
    private MeleeWeapon giantAxe;

    public SpinAttackAction(WeaponItem giantAxe) {
        super(giantAxe);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
