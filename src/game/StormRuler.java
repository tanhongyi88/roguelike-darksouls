package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;

public class StormRuler extends GameWeaponItem{

    /**
     * Constructor.
     */
    public StormRuler() {
        super("Storm Ruler", '7', 70, "hits", 60);
    }

    public void changeHitRate(int hitRate){ this.hitRate = hitRate; }

    public void changeDamage(int damage){ this.damage = damage; }

}
