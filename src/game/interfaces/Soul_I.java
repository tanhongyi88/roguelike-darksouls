package game.interfaces;

import edu.monash.fit2099.engine.addons.DesignOSoulsAddOn;
import game.Souls;

/**
 * A contract for soul-able instance (in other words, object/instance that has souls).
 * The instance can be Actor, Item, or even Ground.
 * @see DesignOSoulsAddOn
 */
public interface Soul_I {

    /**
     * Transfer current instance's souls to another Soul instance.
     * @param soulObject a target souls.
     */
    void transferSouls(Souls soulObject);

    /**
     * Increase souls to current instance's souls.
     * By default, it cannot increase the souls.
     * You may override this method to implement its functionality.
     *
     * @param soul_amount number of souls to be incremented.
     * @return transaction status. True if addition successful, otherwise False.
     */
    default boolean addSouls(int soul_amount){ return false;}

    /**
     * Allow other classes to deduct the number of this instance's souls
     * By default, an instance cannot get its own souls subtracted.
     * You may override this method to conduct subtraction on current souls.
     *
     * @param soul_amount number souls to be deducted
     * @return transaction status. True if subtraction successful, otherwise False.
     */
    default boolean subtractSouls(int soul_amount){ return false;}

}
