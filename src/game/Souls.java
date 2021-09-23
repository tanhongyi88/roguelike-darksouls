package game;

import edu.monash.fit2099.engine.Item;
import game.interfaces.Soul_I;

public class Souls extends Item implements Soul_I {
    private int numberOfSouls;

    /***
     * Constructor.
     * @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public Souls(String name, char displayChar, boolean portable, int startingSoul) {
        super(name, displayChar, portable);
        this.numberOfSouls = startingSoul;
    }

    public int getNumberOfSouls(){ return numberOfSouls; }

    public boolean addSouls(int soul_amount){
        boolean success=false;
        numberOfSouls += soul_amount;
        if (numberOfSouls == (numberOfSouls+soul_amount)){
            success=true;
        }
        return success;
    }

    public boolean subtractSouls(int soul_amount){
        boolean success=false;
        int oldAmount = numberOfSouls;
        numberOfSouls -= soul_amount;
        if(numberOfSouls < (oldAmount)){
            success=true;
        }
        return success;
    }

    public void transferSouls(Souls nextSoul){
        nextSoul.addSouls(this.getNumberOfSouls());
        this.numberOfSouls=0;
    }
}
