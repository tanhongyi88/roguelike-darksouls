package game.weapon;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.WeaponAction;
import game.enums.Abilities;
import game.weapon.action.SpinAttackAction;

/**
 * GiantAxe class represents the Giant Axe Weapon
 *
 * @author
 * @version 1.0.0
 */
public class GiantAxe extends GameWeaponItem {

    /**
     * Constructor for the GiantAxe Weapon.
     **/
    public GiantAxe() {
        super("Giant Axe", 'g', 50, "hacks", 80);
        this.addCapability(Abilities.SWAP);
    }

    @Override
    public WeaponAction getActiveSkill(Actor target, String direction) {
        return new SpinAttackAction(this, target);
    }
}
