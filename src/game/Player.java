package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Resettable;
import game.interfaces.Soul;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Soul, Resettable {

	private Location previousLocation;
	private final Menu menu = new Menu();

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Abilities.REST);
		this.registerInstance();
	}

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

			map.moveActor(this, new Location(map, 38, 12));
			return new ResetAction(this.previousLocation);
		}
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// update the actor previous location every turn by injection
		this.previousLocation = map.locationOf(this);

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	@Override
	public void transferSouls(Soul soulObject) {
		//TODO: transfer Player's souls to another Soul's instance.
	}

	/**
	 * Reset player's instance, including hitpoints, EstusFlask chargers and location.
	 */
	@Override
	public void resetInstance() {
		// reset hitpoints to max hitpoints
		this.hitPoints = this.maxHitPoints;

		// reset EstusFlask
//		// find estus flask in inventory and reset
//		for (Item item: this.inventory) {
//			if (item == EstusFlask) {
//				item.numOfEstusFlask = 3;
//			}
//		}

	}

	@Override
	public boolean isExist() {
		return true;
	}
}
