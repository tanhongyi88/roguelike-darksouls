package game.weapon;

import edu.monash.fit2099.engine.*;

public class GameWeaponItem extends WeaponItem {
    /**
     * Constructor for GameWeaponItem
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */
    public GameWeaponItem(String name, char displayChar, int damage, String verb, int hitRate) {
        super(name, displayChar, damage, verb, hitRate);
    }

    /**
     * Drops the items from an actor.
     *
     * @param actor an actor that will interact with this item
     * @return null because weapons cannot be dropped
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }
}
