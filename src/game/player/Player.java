package game.player;

import edu.monash.fit2099.engine.*;
import game.enums.*;
import game.interfaces.*;
import game.weapon.Broadsword;
import game.weapon.action.SwapWeaponAction;

/**
 * Class representing the Player.
 *
 * @author Lee Jia Yi, Tan Hong Yi
 * @version 1.1.0
 */
public class Player extends Actor implements Soul, Resettable {

	private Location previousLocation;
	private final Menu menu = new Menu();
	private int numberOfSoul;
	private Location spawnLocation;

	/**
	 * Constructor for the Player class.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints, Location spawnLocation) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.ABLE_TO_ENTER_VALLEY);
		this.addCapability(Status.ABLE_TO_STEP_ON_FLOOR);
		this.addCapability(Abilities.REST);
		this.addCapability(Abilities.BUY);
		this.addCapability(Abilities.TELEPORT);
		this.addItemToInventory(new Broadsword());
		this.addItemToInventory(new EstusFlask(this));
		this.registerInstance();
		this.numberOfSoul = 0;
		this.spawnLocation = spawnLocation;
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
		if (!isConscious()){
			return new DeathAction();
		}
		swapWeapon(map);

		actions.add(this.getWeapon().getActiveSkill(this, ""));
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		display.println(name + " (" + hitPoints + "/" + maxHitPoints + ")" + ", holding " + getWeapon() +  ", " + numberOfSoul + " souls");
		// update the actor previous location every turn by injection
		updatePlayerLocation(map.locationOf(this));

		return menu.showMenu(this, actions, display);
	}

	/**
	 * Swap Weapon after the player has picked up any weapon(Storm Ruler)
	 *
	 * @param map map that contains player
	 */
	private void swapWeapon(GameMap map) {
		Item currentWeapon = (Item) this.getWeapon();
		Item previousWeapon = this.getInventory().get(this.getInventory().size()-1);

		if(previousWeapon.hasCapability(Abilities.SWAP)) {
			this.removeItemFromInventory(currentWeapon);
			SwapWeaponAction swap = new SwapWeaponAction(previousWeapon);
			swap.execute(this, map);
		}
	}

	/**
	 * Returns the number of souls for Player
	 *
	 * @return int that represents the number of souls
	 */
	public int getSouls(){
		return this.numberOfSoul;
	}

	/**
	 * Transfers the souls to another Soul's instance after Player is killed
	 *
	 * @param soulObject a target souls.
	 */
	@Override
	public void transferSouls(Soul soulObject) {
		soulObject.addSouls(numberOfSoul);
		this.numberOfSoul = 0;
	}

	/**
	 * Adds the the number of souls
	 *
	 * @param soulAmount int that represents the number of souls to be added
	 * @return true after the souls is added
	 */
	@Override
	public boolean addSouls(int soulAmount) {
		this.numberOfSoul += soulAmount;
		return true;
	}

	/**
	 * Subtract the number of souls
	 *
	 * @param soulAmount int that represents the number of souls to be subtracted
	 * @return true if the number of souls to be deducted is lesser than the total soul; false otherwise
	 */
	@Override
	public boolean subtractSouls(int soulAmount) {
		if (soulAmount < numberOfSoul){
			this.numberOfSoul -= soulAmount;
			return true;
		}
		return false;
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
	 * Returns the previous location for the player
	 *
	 * @return Location that represents the player's previous location
	 */
	public Location getPreviousLocation() {
		return previousLocation;
	}

	/**
	 * Reset player's instance, including hitpoints and location.
	 */
	@Override
	public void resetInstance() {
		this.hitPoints = this.maxHitPoints;
	}

	/**
	 * Checks for the existence of the player in the game
	 *
	 * @return true as the player always exist
	 */
	@Override
	public boolean isExist() {
		return true;
	}

	/**
	 * Update the player previous location
	 * @param newLocation
	 */
	private void updatePlayerLocation(Location newLocation) {
		this.previousLocation = newLocation;
	}

	/**
	 * Update the bonfire that player last interacts with
	 *
	 * @param newLocation
	 */
	public void updateSpawnLocation(Location newLocation) {
		this.spawnLocation = newLocation;
	}

	/**
	 * Getter of spawnLocation
	 *
	 * @return this.spawnLocation
	 */
	public Location getSpawnLocation() {
		return this.spawnLocation;
	}
}
