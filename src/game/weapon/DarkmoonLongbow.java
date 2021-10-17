package game.weapon;

import edu.monash.fit2099.engine.*;
import game.behaviour.RangedWeaponBehaviour;
import game.enums.Abilities;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class represents the Longbow Weapon
 *
 * @author Lee Jia Yi, Afrida Jahin
 * @version 1.0.0
 */
public class DarkmoonLongbow extends GameWeaponItem{
    private static final int CRITICAL_STRIKE_PROBABILITY = 15;

    /**
     * Constructor for the Longbow
     */
    public DarkmoonLongbow(){
        super("Darkmoon Longbow", 'd', 70, "pierces", 80);
        this.addCapability(Abilities.SWAP);
    }

    /**
     * Passive skill (Critical Strike) for the Broadsword.
     * Has a 15% probability to double the damage
     *
     * @return int that represents the damage
     */
    @Override
    public int damage() {
        Random random = new Random();

        if(random.nextDouble()*100 <= CRITICAL_STRIKE_PROBABILITY){
            return damage*2;
        }
        return damage;
    }

//    /**
//     * Gets a list of allowable actions to the DarkmoonLongbow.
//     *
//     * @return a list of Actions
//     */
//    @Override
//    public List<Action> getAllowableActions() {
//        List<Action> actions = new ArrayList<>();
//        actions.add(new RangedWeaponBehaviour());     // adds the action to the weapon
//        return actions;
//    }
}
