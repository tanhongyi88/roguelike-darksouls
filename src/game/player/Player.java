package game.player;

import edu.monash.fit2099.engine.*;
import game.action.SwapWeaponAction;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Resettable;
import game.interfaces.Soul;
import game.reset.ResetAction;
import game.weapon.Broadsword;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Soul, Resettable {

	private Location previousLocation;
	private final Menu menu = new Menu();
	private int numberOfSoul;

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
		this.addCapability(Abilities.BUY);
		this.addItemToInventory(new Broadsword());
		this.registerInstance();
		this.numberOfSoul = 0;
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
		if(!this.isConscious()){
			display.println("YYY     YYY   .0OO000.     UU       UU     DD\"\"\"Db     III   EEEEEEEEEEE  DD\"\"\"Db");
			display.println(" YYY   YYY  00'      `00   UU       UU     DD    `Db.  III   EE           DD    `Db.");
			display.println("  YYY YYY  OO          00  UU       UU     DD     `Db  III   EE           DD     `Db");
			display.println("   'YYY'   OO          00  UU       UU     DD      DD  III   EEEEEEEEE    DD      DD");
			display.println("    YYY    OO          00  UU       UU     DD      DD  III   EE           DD      DD");
			display.println("    YYY     OO.      ,00   UU.     ,UU     DD    ,DP'  III   EE           DD    ,DP'");
			display.println("    YYY       'OO0000'      'UUUUUUU'      DDmm,DP'    III   EEEEEEEEEEE  DDmm,DP'");
			display.println("The world is resetting...");

			map.moveActor(this, map.at(38,12));
			return new ResetAction(this.previousLocation);
		}
		Item currentWeapon = (Item) this.getWeapon();
		Item previousWeapon = this.getInventory().get(this.getInventory().size()-1);

		if(previousWeapon.hasCapability(Abilities.SWAP)){
			this.removeItemFromInventory(currentWeapon);
			SwapWeaponAction swap = new SwapWeaponAction(previousWeapon);
			swap.execute(this, map);
		}

		// Handle multi-turn Actions
		actions.add(new DrinkAction(this));

		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		display.println(name + " (" + hitPoints + "/" + maxHitPoints + ")" + ", holding " + getWeapon() +  ", " + numberOfSoul + " souls");


		// update the actor previous location every turn by injection
		this.previousLocation = map.locationOf(this);

		return menu.showMenu(this, actions, display);
	}

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

	@Override
	public boolean addSouls(int soulAmount) {
		this.numberOfSoul += soulAmount;
		return true;
	}

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

	/**
	 * Reset player's instance, including hitpoints, EstusFlask chargers and location.
	 */
	@Override
	public void resetInstance() {
		// reset hitpoints to max hitpoints
		this.hitPoints = this.maxHitPoints;

		// reset EstusFlask
		getEstusFlask().refillEstusFlask();
	}

	@Override
	public boolean isExist() {
		return true;
	}

	/**
	 * DrinkAction class represents the action when Player drinks the Estus Flask
	 *
	 * @author
	 * @version 1.0.0
	 */
	public static class DrinkAction extends Action {

		private Player player;
		private EstusFlask estusFlask;

		/**
		 * Constructor for the DrinkAction
		 *
		 * @param player the player drinking the Estus Flask
		 */
		public DrinkAction(Player player) {
			this.player = player;
			this.estusFlask = new EstusFlask("Estus Flask");
			player.addItemToInventory(estusFlask);
		}

		/**
		 * Performs the DrinkAction.
		 *
		 * @param actor The actor performing the action.
		 * @param map The map the actor is on.
		 * @return
		 */
		@Override
		public String execute(Actor actor, GameMap map) {

			EstusFlask estusFlask = player.getEstusFlask();

			if(estusFlask.getNumOfEstusFlask() == 0){
				return "No available Estus Flask!";
			}
			else{
				if(player.getHitPoints() < player.getMaxHitPoints()){
					int points = player.getMaxHitPoints();
					player.heal(points);
					estusFlask.decrementNumberOfEstusFlask();
					return actor + " drinks Estus Flask!";
				}
			}
			return actor + "'s hitpoints is full!";
		}

		/**
		 * Returns the key used in the menu to trigger this Action.
		 *
		 * @return The key ("a") for DrinkAction in the menu
		 */
		@Override
		public String hotkey(){
			return "a";
		}

		/**
		 * Returns a descriptive string that the player drinks the Estus Flask
		 *
		 * @param actor The actor performing the action.
		 * @return String that needs to be shown on the menu
		 */
		@Override
		public String menuDescription(Actor actor) {
			return "Unkindled drinks Estus Flask (" + player.getEstusFlask().getNumOfEstusFlask() + "/" + player.getEstusFlask().getMaxNumOfEstusFlask() +")";
		}
	}
}
