package game.reset;

import edu.monash.fit2099.engine.*;
import game.interfaces.Soul;

public class TokenOfSouls extends Item implements Soul{

    private int soulAmount;

    /***
     * Constructor.
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
     * @param actor an actor that will interact with this item
     * @return Retrieve action if token is portable (always portable) otherwise null
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        if(portable)
            return new RetrieveSoulsAction(this, this.soulAmount);

        return null;
    }

    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(this.soulAmount);
        this.soulAmount = 0;
    }

    @Override
    public boolean addSouls(int soulAmount) {
        this.soulAmount += soulAmount;
//        System.out.println("soulAmount: " + soulAmount);
//        System.out.println("this.soulAmount: " + this.soulAmount);
        return true;
    }

    @Override
    public boolean subtractSouls(int soulAmount) {
        return Soul.super.subtractSouls(soulAmount);
    }
}

