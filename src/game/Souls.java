package game;

import edu.monash.fit2099.engine.Item;

public class Souls extends Item {
    private int numberOfSouls;
    private final int startingSoul=0;

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public Souls(String name, char displayChar, boolean portable, int startingSoul) {
        super(name, displayChar, portable);
        this.numberOfSouls = startingSoul;
    }

    public int getNumberOfSouls(){ return numberOfSouls; }

    public void addSouls(int souls){
        numberOfSouls += souls;
    }

    public void subtractSouls(int souls){
        numberOfSouls -= souls;
    }

    //public void transferSouls(int souls){ }
}
