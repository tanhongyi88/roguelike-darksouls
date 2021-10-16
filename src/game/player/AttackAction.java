package game.player;

import java.util.Random;

import edu.monash.fit2099.engine.*;

/**
 * Special Action for attacking other Actors.
 *
 * @author Lee Jia Yi
 * @version 1.0.0
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor for the AttackAction.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	/**
	 * Performs the AttackAction.
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return String that represents the AttackAction
	 */
	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();
		String result = "";

		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}

		int damage = weapon.damage();
		result += actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		target.hurt(damage);

		if (!target.isConscious()){
			result += System.lineSeparator() + new DeathAction(actor).execute(target, map);
		}
		return result;
	}

	/**
	 * Returns a descriptive string to be displayed in the menu
	 *
	 * @param actor The actor performing the action.
	 * @return String that describes the AttackAction
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}
}
