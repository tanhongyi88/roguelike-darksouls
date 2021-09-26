package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;
import game.enums.Abilities;

/**
 * Special Action for attacking other Actors.
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
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

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
				// remove actor
				//TODO: In A1 scenario, you must not remove a Player from the game yet. What to do, then?
				map.removeActor(target);
				target.asSoul().transferSouls(actor.asSoul());
				if (target instanceof Player) {
					// do nothing
				} else {
					map.removeActor(target);
				}

				result += System.lineSeparator() + target + " is killed.";
				if (target instanceof LordOfCinder){
					result += System.lineSeparator() + "LORD OF CINDER FALLEN";
				}
			}
		}
		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}
}
