package game.enemy;

import edu.monash.fit2099.engine.*;
import game.behaviour.*;
import game.interfaces.*;
import java.util.Random;

/**
 * Undead class represents the undead minion in the game
 *
 * @author Lee Jia Yi
 * @version 1.1.0
 */
public class Undead extends Enemy{
	private final static int UNDEAD_SOULS = 50;

	/** 
	 * Constructor for the Undead class.
	 * All Undeads are represented by an 'u' and have 50 hit points.
	 *
	 * @param name the name of this Undead
	 */
	public Undead(String name) {
		super(name, 'u', 50);
		addBehaviour(new WanderBehaviour());
	}

	/**
	 * Creates and returns an intrinsic weapon for Undead
	 * Skeleton 'punches' or 'thwacks' for 20 damage.
	 *
	 * @return A freshly-instantiated IntrinsicWeapon
	 */
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		String[] s = {"punches", "thwacks"};
		Random random = new Random();
		int select = random.nextInt(s.length);

		return new IntrinsicWeapon(20, s[select]);
	}

	/**
	 * A toString method for the Undead's class
	 *
	 * @return String that represents the Undead's information(hitpoints and weapon)
	 */
	@Override
	public String toString(){
		return name + " (" + hitPoints + "/" + maxHitPoints +")(no Weapon)";
	}

	/**
	 * Transfers the souls (50 souls) to Player Soul's instance after Undead is killed
	 *
	 * @param soulObject a target souls.
	 */
	@Override
	public void transferSouls(Soul soulObject) {
		soulObject.addSouls(UNDEAD_SOULS);
	}
}