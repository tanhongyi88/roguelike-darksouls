package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.*;

/**
 * The main class for the Jurassic World game.
 *
 */
public class Application {

	public static void main(String[] args) {

			World world = new World(new Display());

			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Valley(), new Cemetery());

			List<String> map = Arrays.asList(
					"..++++++..+++...........................++++......+++.................+++.......",
					"........+++++..............................+++++++.................+++++........",
					"...........+++.......................................................+++++......",
					"........................................................................++......",
					"...........................................................c.............+++....",
					"............................+.............................................+++...",
					".............................+++.......++++.....................................",
					"......................c......++.......+......................++++...............",
					".............................................................+++++++............",
					"..................................###___###...c...............+++...............",
					"..................................#_______#......................+++............",
					"...........++.....................#_______#.......................+.............",
					".........+++......................#_______#........................++...........",
					"............+++...................####_####..........................+..........",
					"..............+......................................................++.........",
					"..............++.................................................++++++.........",
					"............+++...................................................++++..........",
					"+..................................................................++...........",
					"++...+++.........................................................++++...........",
					"+++....................c.................+++........................+.++........",
					"++++.......++++.........................++.........................+....++......",
					"#####___#####++++......................+...............................+..+.....",
					"_..._....._._#.++......................+...................................+....",
					"...+.__..+...#+++.......................................c...................+...",
					"...+.....+._.#.+.....+++++...++..............................................++.",
					"___.......___#.++++++++++++++.+++.............................................++");
			GameMap gameMap = new GameMap(groundFactory, map);
			world.addGameMap(gameMap);

			Actor player = new Player("Unkindled (Player)", '@', 100);
			world.addPlayer(player, gameMap.at(38, 12));

			// Place Yhorm the Giant/boss in the map
			gameMap.at(6, 25).addActor(new LordOfCinder());

			// Place Storm Ruler near to boss in the map
			gameMap.at(7,25).addItem(new StormRuler());

			// Place Skeleton in the map
			gameMap.at(12,17).addActor(new Skeleton("Skeleton", player));
			gameMap.at(60,23).addActor(new Skeleton("Skeleton", player));
			gameMap.at(42,3).addActor(new Skeleton("Skeleton", player));
			gameMap.at(75,2).addActor(new Skeleton("Skeleton", player));
			gameMap.at(35,15).addActor(new Skeleton("Skeleton", player));
			gameMap.at(20,7).addActor(new Skeleton("Skeleton", player));
			gameMap.at(2,1).addActor(new Skeleton("Skeleton", player));

			// Place a Hollow in the the map
			// FIXME: the Undead should be generated from the Cemetery
//			Undead undead = new Undead("Undead");
//			undead.addBehaviour(new FollowBehaviour(player));
//			undead.addBehaviour(new NormalAttack(player));
//			gameMap.at(32, 7).addActor(undead);
			world.run();
	}
}
