package game.weapon;

import game.enums.Abilities;

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
}
