package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.PickUpItemAction;
import game.interfaces.Soul;

public class TokenOfSouls extends Item implements Soul{

    private int soulAmount;

    /***
     * Constructor.
     * @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     * @param soulsObject
     */
    public TokenOfSouls(String name, char displayChar, boolean portable, Soul soulsObject) {
        super(name, displayChar, portable);

    }

    /**
     * Overriding getPickUpAction so that Souls can be retrieved using RetrieveSoulsAction
     * @param actor an actor that will interact with this item
     * @return
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        if(portable)
            return new RetrieveSoulsAction(this);

        return null;
    }

    @Override
    public void transferSouls(Souls soulObject) {

    }

    @Override
    public boolean addSouls(int soul_amount) {
        return Soul.super.addSouls(soul_amount);
    }

    @Override
    public boolean subtractSouls(int soul_amount) {
        return Soul.super.subtractSouls(soul_amount);
    }
}

