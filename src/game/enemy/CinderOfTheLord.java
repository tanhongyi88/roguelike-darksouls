package game.enemy;

import edu.monash.fit2099.engine.Item;
import game.enums.Abilities;

public class CinderOfTheLord extends Item {
    /***
     * Constructor.
     */
    public CinderOfTheLord(LordOfCinder lord) {
        super("Cinder of "+lord.getName(),'%',true);
        this.addCapability(Abilities.TRADE);
    }

    /**
     * Returns the name of the Cinder of The Lord
     *
     * @return String that represents the name
     */
    public String getName(){
        return name;
    }
}
