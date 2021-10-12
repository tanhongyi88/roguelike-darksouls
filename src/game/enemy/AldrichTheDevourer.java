package game.enemy;

import game.interfaces.Soul;
import game.weapon.DarkmoonLongbow;

public class AldrichTheDevourer extends LordOfCinder{

    public AldrichTheDevourer(String name, char displayChar, int hitPoints){
        super(name, displayChar, hitPoints);
        this.addItemToInventory(new DarkmoonLongbow());
    }

    /**
     * Transfer current instance's souls to another Soul instance.
     *
     * @param soulObject a target souls.
     */
    @Override
    public void transferSouls(Soul soulObject) { }
}
