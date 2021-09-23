package edu.monash.fit2099.engine.addons;

import game.interfaces.Soul_I;

/**
 * NOTE: Use this interface to add specific features according to the Assignment's scenario
 */
public interface DesignOSoulsAddOn {
    /**
     * Allows upcasting for Actor, Ground, or Item to a Soul instance if possible.
     *
     * @return a reference to the current Actor/Ground/Item as type Soul,
     *         or null if this Actor/Ground/Item doesn't implement Soul.
     */
    default Soul_I asSoul(){return this instanceof Soul_I ? (Soul_I) this : null;}
}
