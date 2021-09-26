package game;

import game.enums.Abilities;

public class GiantAxe extends GameWeaponItem {
    /**
     * Constructor.
     */
    public GiantAxe() {
        super("Giant Axe", 'g', 50, "hacks", 80);
        this.addCapability(Abilities.SWAP);
    }
}
