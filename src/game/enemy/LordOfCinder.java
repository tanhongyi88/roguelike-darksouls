package game.enemy;

import game.interfaces.*;

import java.util.ArrayList;

/**
 * The boss of Design o' Souls
 * An abstract class where all the boss in the game can inherits from
 *
 * @author Lee Jia Yi
 * @version 1.1.0
 */
public abstract class LordOfCinder extends Enemy{
    private ArrayList<Behaviour> behaviours = new ArrayList<>();
    private final static int LORD_OF_CINDER_SOULS = 5000;

    /**
     * Constructor for the Lord of Cinder class
     *
     * @param name          the name of the boss
     * @param displayChar   the character that will represent the boss in the display
     * @param hitPoints     the boss's starting hit points
     */
    public LordOfCinder(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }

    /**
     * Returns the name of the Lord Of Cinder
     *
     * @return String that represents the name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Transfers the souls (5000 souls) to Player Soul's instance after Lord of Cinder is killed
     *
     * @param playerSoul the player souls.
     */
    public void transferSouls(Soul playerSoul) {
        playerSoul.addSouls(LORD_OF_CINDER_SOULS);
    }

    /**
     * A toString method for the Lord Of Cinder's class
     *
     * @return String that represents the Lord Of Cinder's information(hitpoints and weapon)
     */
    public String toString(){
        return name + " (" + hitPoints + "/" + maxHitPoints +")(" + getWeapon() + ")";
    }
}
