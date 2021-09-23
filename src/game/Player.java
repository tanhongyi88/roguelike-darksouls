package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Soul;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Soul {

	private final Menu menu = new Menu();

	/**
	 * Constructor for the Player class.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.ABLE_TO_ENTER_VALLEY);
		this.addCapability(Status.ABLE_TO_STEP_ON_FLOOR);
		this.addCapability(Abilities.REST);
		this.addItemToInventory(new Broadsword());
	}

	/**
	 * Returns a collection of the Actions that the otherActor can do to Player.
	 *
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return A collection of Actions
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		display.println(name + " (" + hitPoints + "/" + maxHitPoints + ")" + ", holding " + getWeapon());
		actions.add(new DrinkAction(this));
		return menu.showMenu(this, actions, display);
	}

	/**
	 * Transfers the souls to another Soul's instance after Player is killed
	 *
	 * @param soulObject a target souls.
	 */
	@Override
	public void transferSouls(Soul soulObject) {
		//TODO: transfer Player's souls to another Soul's instance.
	}

	/**
	 * Add points to the current Actor's hitpoint total.
	 *
	 * @param points number of hitpoints to add.
	 */
	@Override
	public void heal(int points) {
		double healPoints = points*0.4;
		hitPoints += healPoints;
		hitPoints = Math.min(hitPoints, maxHitPoints);
	}

	/**
	 * Returns the maximum hitpoints for the player
	 *
	 * @return int that represent the player's maximum hitpoints
	 */
	public int getMaxHitPoints(){
		return maxHitPoints;
	}

	/**
	 * Returns the hitpoint for the player
	 *
	 * @return int that represents the player's hitpoints
	 */
	public int getHitPoints(){
		return hitPoints;
	}

	/**
	 * Gets the Estus Flask for the Player from the inventory
	 *
	 * @return the player's EstusFlask
	 */
	public EstusFlask getEstusFlask() {
		for (Item item : inventory) {
			if (item instanceof EstusFlask){
				return (EstusFlask) item;
			}
		}
		return null;
	}
}
