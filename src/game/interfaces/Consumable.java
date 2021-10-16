package game.interfaces;

/**
 * A contract for consumable instance (in other words, items that can be consumed by player).
 * The instance must be an Item.
 */
public interface Consumable {

    /**
     * Returns the number of consumable available to the player.
     *
     * @return the number of consumable available.
     */
    int getNumberOfConsumable();

    /**
     * Returns the maximum number of consumable available to the player.
     *
     * @return the number of consumable available.
     */
    int getMaximumConsumable();

    /**
     * Uses and decrements a consumable .
     */
    void useConsumable();
}
