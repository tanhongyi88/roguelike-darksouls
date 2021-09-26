package game;

import edu.monash.fit2099.engine.*;
import game.interfaces.Resettable;

public class Souls extends Item implements Resettable {
    private int numberOfSouls;
    private Location soulsLocation;
    private int count;

    /***
     * Constructor.
     * @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public Souls(String name, char displayChar, boolean portable, int startingSoul, Location location) {
        super(name, displayChar, portable);
        this.numberOfSouls = startingSoul;
        this.soulsLocation = location;
        this.count = 0;
        this.registerInstance();
    }

    public PickUpItemAction getPickUpAction(Actor actor){
        return null;
    }

    public int getNumberOfSouls(){
        return numberOfSouls;
    }

    @Override
    public void resetInstance() {
        if(count==0){
            this.soulsLocation.removeItem(this);
        }
    }

    @Override
    public boolean isExist() {
        return true;
    }

//
//    public boolean addSouls(int soul_amount){
//        boolean success=false;
//        numberOfSouls += soul_amount;
//        if (numberOfSouls == (numberOfSouls+soul_amount)){
//            success=true;
//        }
//        return success;
//    }
//
//    public boolean subtractSouls(int soul_amount){
//        boolean success=false;
//        int oldAmount = numberOfSouls;
//        numberOfSouls -= soul_amount;
//        if(numberOfSouls < (oldAmount)){
//            success=true;
//        }
//        return success;
//    }
//
//    public void transferSouls(Souls nextSoul){
//        nextSoul.addSouls(this.getNumberOfSouls());
//        this.numberOfSouls=0;
//    }
}
