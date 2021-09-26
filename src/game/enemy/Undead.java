package game.enemy;

import edu.monash.fit2099.engine.*;
import game.player.AttackAction;
import game.behaviour.*;
import game.enums.Status;
import game.interfaces.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Undead class represents the undead minion in the game
 *
 * @author Lee Jia Yi
 * @version 1.0.0
 */
public class Undead extends Actor implements Soul{
	private ArrayList<Behaviour> behaviours = new ArrayList<>();
	private final static int UNDEAD_SOULS = 50;

	/** 
	 * Constructor for the Undead class.
	 * All Undeads are represented by an 'u' and have 50 hit points.
	 *
	 * @param name the name of this Undead
	 */
	public Undead(String name) {
		super(name, 'u', 50);
		behaviours.add(new WanderBehaviour());
	}

	/**
	 * Returns a collection of the Actions that the otherActor can do to Undead.
	 *
	 * @param otherActor the Actor that might be performing attack
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return list of actions
	 * @see Status#HOSTILE_TO_ENEMY
	 */
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions actions = new Actions();
		if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
			behaviours.add(0, new AttackBehaviour(otherActor, direction));
			behaviours.add(1, new FollowBehaviour(otherActor));
			actions.add(new AttackAction(this,direction));
		}
		return actions;
	}

	/**
	 * Selects and returns an action for Undead to perform on the current turn.
	 *
	 * @param actions    collection of possible Actions for Undead
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return The Action to be performed by the Undead
	 * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		for(Behaviour Behaviour : behaviours) {
			Action action = Behaviour.getAction(this, map);
			if (action != null){
				return action;
			}
		}
		return new WanderBehaviour();
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