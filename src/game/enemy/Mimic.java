package game.enemy;

import edu.monash.fit2099.engine.*;
import game.ground.Chest;
import game.interfaces.*;

import java.util.ArrayList;

/**
 * Mimic class represents the mimic in the game
 *
 * @author Lee Jia Yi
 * @version 1.0.0
 */
public class Mimic extends Enemy implements Resettable {
    private ArrayList<Behaviour> behaviours = new ArrayList<>();
    private Location initLocation;
    private boolean isExist;
    private final static int MIMIC_SOULS = 200;

    /**
     * Constructor for the Mimic class.
     * All Mimics are represented by an 'M' and have 100 hit points.
     */
    public Mimic(Location initLocation) {
        super("Mimic", 'M', 100);
        this.initLocation = initLocation;
        this.isExist = true;
        this.registerInstance();
    }

    /**
     * Creates and returns an intrinsic weapon for Undead
     * Skeleton 'kicks' for 55 damage.
     *
     * @return A freshly-instantiated IntrinsicWeapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(55, "kicks");
    }

    /**
     * Transfers the souls (200 souls) to Player Soul's instance after Mimic is killed
     *
     * @param soulObject a target souls.
     */
    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(MIMIC_SOULS);
    }

    /**
     * A toString method for the Mimic's class
     *
     * @return String that represents the Mimic's information(hitpoints and weapon)
     */
    @Override
    public String toString(){
        return name + " (" + hitPoints + "/" + maxHitPoints +")(no Weapon)";
    }

    /**
     * Reset Mimic into chest.
     */
    @Override
    public void resetInstance() {
        if(isExist){
            this.hitPoints = maxHitPoints;
            initLocation.map().moveActor(this, initLocation);
            initLocation.map().removeActor(this);
            initLocation.setGround(new Chest());
            isExist = false;
        }
    }

    /**
     * Checks for the existence of Mimic in the game
     *
     * @return true is the Mimic exist; false otherwise
     */
    @Override
    public boolean isExist() {
        return this.isExist;
    }
}
