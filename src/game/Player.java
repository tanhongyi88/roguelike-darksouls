package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Soul_I;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Soul_I {

	private final Menu menu = new Menu();
	private Souls soul;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hit points
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Abilities.REST);
		this.soul = new Souls("PlayerSouls",'$',true,0);
		this.addItemToInventory(soul);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		display.println(name + " (" + hitPoints + "/" + maxHitPoints + ")");
		actions.add(new DrinkAction(this));
		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	@Override
	public void transferSouls(Souls soulObject) {
		soul.transferSouls(soulObject);
	}

	@Override
	public boolean addSouls(int soul_amount) {
		boolean success=false;
		if (soul.subtractSouls(soul_amount)){
			success=true;
		}
		return success;
	}

	@Override
	public boolean subtractSouls(int soul_amount) {
		boolean success = false;
		if (soul.subtractSouls(soul_amount)){
			success=true;
		}
		return success;
	}

	@Override
	public void heal(int points) {
		double healPoints = points*0.4;
		hitPoints += healPoints;
		hitPoints = Math.min(hitPoints, maxHitPoints);
	}

	public int getMaxHitPoints(){
		return maxHitPoints;
	}

	public int getHitPoints(){
		return hitPoints;
	}
}
