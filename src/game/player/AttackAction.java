package game.player;

import java.util.Random;

import edu.monash.fit2099.engine.*;
import game.enemy.*;
import game.enums.Abilities;

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
		result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		target.hurt(damage);

		if (!target.isConscious()) {
			if (target instanceof Skeleton && target.hasCapability(Abilities.RESURRECT)){
				if (((Skeleton) target).resurrect(map)){
					result += System.lineSeparator() + target + " is resurrected.";
				}
				else{
					result += System.lineSeparator() + target + " is killed.";
					target.asSoul().transferSouls(actor.asSoul());
				}
			}
			else{
				Actions dropActions = new Actions();
				// drop all items
				for (Item item : target.getInventory())
					dropActions.add(item.getDropAction(actor));
				for (Action drop : dropActions)
					drop.execute(target, map);

				if (!(target instanceof Player)) {
					map.removeActor(target);
					target.asSoul().transferSouls(actor.asSoul());
				}
				result += System.lineSeparator() + target + " is killed.";
				if (target instanceof LordOfCinder){

					result += System.lineSeparator() + """


					██╗      ██████╗ ██████╗ ██████╗      ██████╗ ███████╗     ██████╗██╗███╗   ██╗██████╗ ███████╗██████╗     ███████╗ █████╗ ██╗     ██╗     ███████╗███╗   ██╗
					██║     ██╔═══██╗██╔══██╗██╔══██╗    ██╔═══██╗██╔════╝    ██╔════╝██║████╗  ██║██╔══██╗██╔════╝██╔══██╗    ██╔════╝██╔══██╗██║     ██║     ██╔════╝████╗  ██║
					██║     ██║   ██║██████╔╝██║  ██║    ██║   ██║█████╗      ██║     ██║██╔██╗ ██║██║  ██║█████╗  ██████╔╝    █████╗  ███████║██║     ██║     █████╗  ██╔██╗ ██║
					██║     ██║   ██║██╔══██╗██║  ██║    ██║   ██║██╔══╝      ██║     ██║██║╚██╗██║██║  ██║██╔══╝  ██╔══██╗    ██╔══╝  ██╔══██║██║     ██║     ██╔══╝  ██║╚██╗██║
					███████╗╚██████╔╝██║  ██║██████╔╝    ╚██████╔╝██║         ╚██████╗██║██║ ╚████║██████╔╝███████╗██║  ██║    ██║     ██║  ██║███████╗███████╗███████╗██║ ╚████║
					╚══════╝ ╚═════╝ ╚═╝  ╚═╝╚═════╝      ╚═════╝ ╚═╝          ╚═════╝╚═╝╚═╝  ╚═══╝╚═════╝ ╚══════╝╚═╝  ╚═╝    ╚═╝     ╚═╝  ╚═╝╚══════╝╚══════╝╚══════╝╚═╝  ╚═══╝

					""";
				}
			}
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
