package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.*;
import game.enemy.*;
import game.ground.*;
import game.player.Player;
import game.weapon.StormRuler;


/**
 * The main class for the Jurassic World game.
 *
 * @author Afrida Jahin, Lee Jia Yi, Tan Hong Yi
 * @version 1.1.0
 */
public class Application {

	public static void main(String[] args) {

			World world = new World(new Display());

			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Valley(),
					new Cemetery(), new FireKeeper(), new Chest());

			List<String> map = Arrays.asList(
					"..++++++..+++...........................++++......+++.................+++.......",
					"........+++++..............................+++++++.................+++++........",
					"...........+++.......................................................+++++......",
					"................................................?.......................++......",
					"....?......................................................c.............+++....",
					"............................+.............................................+++...",
					".............................+++.......++++.....................................",
					"......................c......++.......+......................++++...............",
					".............................................................+++++++............",
					"...............?..................###___###...c...............+++...............",
					"..................................#_______#......................+++............",
					"...........++.....................#_______#.............?.........+.............",
					".........+++......................#F______#........................++...........",
					"............+++...................####_####..........................+..........",
					"..............+.....................?................................++.........",
					"..............++.................................................++++++.........",
					"............+++...................................................++++..........",
					"+...............................................?..................++...........",
					"++...+++.........................................................++++...........",
					"+++...............?....c.................+++........................+.++........",
					"++++.......++++.........................++.........................+....++......",
					"#####___#####++++......................+...............................+..+.....",
					"_..._....._._#.++......................+...................................+....",
					"...+.__..+...#+++.......................................c...................+...",
					"...+.....+._.#.+.....+++++...++....................................?.........++.",
					"___.......___#.++++++++++++++.+++.............................................++");
			GameMap gameMap = new GameMap(groundFactory, map);
			world.addGameMap(gameMap);

			Actor player = new Player("Unkindled (Player)", '@', 100, gameMap.at(38,11));
			world.addPlayer(player, gameMap.at(38, 12));

			// Place Yhorm the Giant/boss in the map
			gameMap.at(6, 25).addActor(new YhormTheGiant("Yhorm The Giant", 'Y', 500));

			// Place Storm Ruler near to boss in the map
			gameMap.at(7,25).addItem(new StormRuler());

			// Place Skeleton in the map
			gameMap.at(38,20).addActor(new Skeleton("Skeleton", player));
			gameMap.at(12,17).addActor(new Skeleton("Skeleton", player));
			gameMap.at(60,23).addActor(new Skeleton("Skeleton", player));
			gameMap.at(42,3).addActor(new Skeleton("Skeleton", player));
			gameMap.at(75,2).addActor(new Skeleton("Skeleton", player));
			gameMap.at(35,15).addActor(new Skeleton("Skeleton", player));
			gameMap.at(20,7).addActor(new Skeleton("Skeleton", player));
			gameMap.at(2,1).addActor(new Skeleton("Skeleton", player));


			// initialise second map Anor Londo
			FancyGroundFactory groundFactory2 = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(),
                    new Valley(), new Cemetery(), new Chest());

			List<String> map2 = Arrays.asList(
					"..........+++...........................................+++........",
					".......+..++..........................................+++++...?....",
					"...........+++.........................................+++++.......",
					"....c.................?...................................++.......",
					"...........................................................+++.....",
					"............................................................+++....",
					"...................................++++............................",
					"...c............................?.............++++.................",
					"......?.......................................+++...+..............",
					"................................................+++....?...........",
					"....................##___#####################.....................",
					".............++.....#................#.......#.....................",
					"....................#...#.....#.......+..#._.#.....................",
					".......+.+..........#._......................#............c........",
					"....................#...#................#...#.........++..........",
					"................++..#.........#.......__.....#.....................",
					"...............+....##___#####################.....................",
					".........................?.........................++..............",
					"..?................................................................");

			GameMap gameMap2 = new GameMap(groundFactory2, map2);
			world.addGameMap(gameMap2);

			gameMap.at(38,25).setGround(new FogDoor(gameMap2.at(30,0), "to Anor Londo"));
			gameMap2.at(30,0).setGround(new FogDoor(gameMap.at(38,25), "to Profane Capital"));

			BonfireManager bonfireManager = new BonfireManager();
			bonfireManager.addLocation("to Firelink Shrine", gameMap.at(38, 11));
			bonfireManager.addLocation("to Anor Londo", gameMap2.at(35, 0));

			gameMap.at(38, 11).setGround(new Bonfire("Firelink Shrine", bonfireManager));
			gameMap2.at(35, 0).setGround(new Bonfire("Anor Londo", bonfireManager));

			// Place AldrichTheDevourer/boss in the map
			gameMap2.at(32, 13).addActor(new AldrichTheDevourer("Aldrich The Devourer", 'A', 350));

			// Place Skeleton in the map
			gameMap2.at(38,2).addActor(new Skeleton("Skeleton", player));
			gameMap2.at(12,17).addActor(new Skeleton("Skeleton", player));
			gameMap2.at(60,2).addActor(new Skeleton("Skeleton", player));

			world.run();

	}
}
