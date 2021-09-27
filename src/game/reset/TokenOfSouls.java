package game.reset;

import edu.monash.fit2099.engine.*;
import game.interfaces.Soul;

/**
 * TokenOfSouls class represents the soul object that is dropped onto the ground when player dies
 *
 * @author Tan Hong Yi
 * @version 1.0.0
 */
public class TokenOfSouls extends Item implements Soul{

    /**
     * soulAmount stores the number of souls that player had before player dies
     */
    private int soulAmount;

    /***
     * Constructor.
     * When this token is being created, the transfer of souls happens at the same time
     *
     * @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     * @param soulsObject soulsObject obtained to form this token (always from player)
     */
    public TokenOfSouls(String name, char displayChar, boolean portable, Soul soulsObject) {
        super(name, displayChar, portable);
        soulsObject.transferSouls(this);
    }

    /**
     * Overriding getPickUpAction so that Souls can be retrieved using RetrieveSoulsAction
     *
     * @param actor an actor that will interact with this item
     * @return Retrieve action if token is portable (always portable) otherwise null
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        if(portable)
            return new RetrieveSoulsAction(this, this.soulAmount);
        return null;
    }

    /**
     * Overriding transferSouls method in Soul interface
     * This method will transfer local soul amount to other souls instance
     *
     * @param soulObject a target souls.
     */
    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(this.soulAmount);
        this.soulAmount = 0;
    }

    /**
     * Overriding addSouls method in Soul interface
     * this method will increase the local soul amount by argument soulAmount
     *
     * @param soulAmount number of souls
     * @return true if addSouls is done, false otherwise
     */
    @Override
    public boolean addSouls(int soulAmount) {
        this.soulAmount += soulAmount;
        return true;
    }

}

