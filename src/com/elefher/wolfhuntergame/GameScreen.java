package com.elefher.wolfhuntergame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.db.framework.Player;
import com.elefher.AssetsAlgorithms.CreateEnemies;
import com.elefher.AssetsAlgorithms.InitializeBrokenRockets;
import com.elefher.AssetsAlgorithms.InitializeBrokenRockets3;
import com.elefher.AssetsAlgorithms.InitializeFood;
import com.elefher.AssetsAlgorithms.InitializeLife;
import com.elefher.AssetsAlgorithms.InitializeRockets;
import com.elefher.AssetsAlgorithms.InitializeRocketsB;
import com.elefher.AssetsAlgorithms.InitializeSkeletonRunner;
import com.elefher.AssetsAlgorithms.InitializeWeapons;
import com.elefher.threads.UpdateAssetsThreads;
import com.elefher.threads.UpdateBrokenRockets3Thread;
import com.elefher.threads.UpdateBrokenRocketsThread;
import com.elefher.threads.UpdateEnemiesThreads;
import com.elefher.threads.UpdateRocketsBThread;
import com.elefher.threads.UpdateRocketsThread;
import com.elefher.threads.UpdateSkeletonRunnersThread;
import com.elefher.threads.UpdateWeaponsThreads;
import com.generic.framework.implementation.AndroidEvents;
import com.generic.framework.implementation.AndroidPaintStrings;
import com.generic.framework.implementation.BrokenRocket;
import com.generic.framework.implementation.BrokenRocket3;
import com.generic.framework.implementation.Rocket;
import com.generic.framework.implementation.RocketB;
import com.generic.framework.implementation.SkeletonRunner;
import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Image;
import com.kilobolt.framework.Input.TouchEvent;
import com.kilobolt.framework.Screen;

public class GameScreen extends Screen {
	enum GameState {
		Ready, Running, Paused, Finish, GameOver, ReadyForLeader, Leader
	}

	public static GameState state = null;
	Context context;

	// Variable Setup
	public static Background bg1 = null, bg2 = null;
	private static Player player;
	private AndroidEvents eventsClass;
	private AndroidPaintStrings paintClass;

	public static Robot robot = null;
	public static ArrayList<Weapon> weapons = null;
	public static ArrayList<Rocket> rockets = null;
	public static ArrayList<RocketB> rocketsB = null;
	public static ArrayList<SkeletonRunner> skeletonRunner = null;
	public static ArrayList<BrokenRocket> brokenRockets = null;
	public static ArrayList<BrokenRocket3> brokenRockets3 = null;
	public static Weapon weapon = null;
	public static int foods;

	public static ArrayList<Heliboy> hbs = null;
	public static ArrayList<FoodDrink> food = null;
	public static Life life1 = null;
	public static LastBigEnemyLevel lastBigEnemy = null;
	public static FinalEnemyThread fET = null;
	public static UpdateAssetsThreads uAT = null;
	public static UpdateWeaponsThreads uWT = null;
	public static UpdateEnemiesThreads uET = null;
	public static UpdateRocketsThread uRT = null;
	public static UpdateRocketsBThread uRTB = null;
	public static UpdateBrokenRocketsThread uBRT = null;
	public static UpdateBrokenRockets3Thread uBRT3 = null;
	public static UpdateSkeletonRunnersThread uSR = null;

	public static int num = 0;

	public static Image currentJoystickMotion, currentSprite, character,
			character2, character3, character4, character5, character6,
			character7, character8, character9, character10, character11,
			character12, character13, character14, character15, character16,
			character17, character18, character19, character20, character21,
			characterrun1, characterrun2, characterrun3, characterrun4,
			characterrun5, characterrun6, characterrun7, characterrun8,
			characterrun9, characterrun10, characterrun11, characterrun12,
			characterrun13, characterrun14, characterrun15, characterrun16,
			characterrun17, characterjump, characterjump2, characterjump3,
			characterjump4, characterjump5, characterjump6, characterjump7,
			characterjump8, characterjump9, characterjump10, characterjump11,
			characterhurt1, characterhurt2, characterhurt3, characterhurt4,
			characterhurt5, characterjump12, characterjump13, characterjump14,
			characterjump15, heliboy, heliboy2, heliboy3, heliboy4, heliboy5,
			finish, explosion1, explosion2, explosion3, explosion4, explosion5,
			life;
	public static Animation anim, animBack, ranimback, supergunbullet,
			exanimate1, hurtanim, hurtanimback, janim, janimback, ranim, hanim,
			fanim, lanim, lastEnemyAnim1, lastEnemyAnim, lastEnemyAnim2,
			lastEnemyjumpAnim2, lastEnemykickAnim2, lastEnemyflipAnim2,
			lastEnemyrunAnim2, lastEnemyshootAnim2, firepunch2, fireball,
			enemyGun, ring, lastEnemyAnim5, lastEnemyBomb5, lastEnemyAnim10;

	public int pointFood = 100;
	public int lifeValue = 1;
	public boolean saved = false;
	public boolean isAfterlLeader = false;
	public static boolean ready;

	public static ArrayList<Tile> tilearray = null;
	Rocket rocket = null;

	Paint paint1, paint2, paintInfo;

	public GameScreen(Context context, Game game, Player player) {
		super(game);

		state = GameState.Ready;
		tilearray = new ArrayList<Tile>();
		eventsClass = new AndroidEvents();
		paintClass = new AndroidPaintStrings();

		ready = false;

		this.context = context;
		this.player = player;
		this.foods = this.player.getFood();

		// Initialize game objects here
		hbs = new ArrayList<Heliboy>();
		food = new ArrayList<FoodDrink>();
		bg1 = new Background(0, 0);
		bg2 = new Background(6000, 0);
		robot = new Robot();

		robot.lives = this.player.getLives();
		robot.level = this.player.getLevel();

		weapons = new ArrayList<Weapon>();

		uAT = new UpdateAssetsThreads();
		uWT = new UpdateWeaponsThreads();
		uET = new UpdateEnemiesThreads();

		// initialize life
		InitializeLife initializeLife = new InitializeLife();
		initializeLife.setCords(Integer.toString(robot.level), this.context);
		int[] lifeSets = initializeLife.getLife();
		life1 = new Life(lifeSets[0], lifeSets[1], lifeSets[2]);

		// Initialize game's enemies
		CreateEnemies cE = new CreateEnemies(Integer.toString(robot.level),
				this.context);
		hbs = cE.getEnemies();

		// initialize game's food
		InitializeFood initializeFood = new InitializeFood();
		initializeFood.setCords(Integer.toString(robot.level), this.context);
		food = initializeFood.getFoods();

		// initialize game's weapons
		InitializeWeapons initializeWeapon = new InitializeWeapons();
		initializeWeapon.setCords(Integer.toString(robot.level), this.context);
		weapons = initializeWeapon.getWeapons();

		// initialize Assets
		animBack = new Animation();
		for (int i = 0; i < 21; i++) {
			animBack.addFrame((Image) Assets.characterbackList.get(i), 50);
		}

		ranimback = new Animation();
		for (int i = 0; i < 17; i++) {
			ranimback.addFrame((Image) Assets.characterrunbackList.get(i), 10);
		}

		janimback = new Animation();
		for (int i = 0; i < 15; i++) {
			janimback.addFrame((Image) Assets.characterjumpbackList.get(i), 50);
		}

		hurtanimback = new Animation();
		for (int i = 0; i < 5; i++) {
			hurtanimback.addFrame((Image) Assets.characterhurtbackList.get(i),
					500);
		}
		
		supergunbullet = new Animation();
		for (int i = 0; i < 1; i++) {
			supergunbullet.addFrame((Image) Assets.supergunbullet.get(i), 15);
		}

		character = Assets.character;
		character2 = Assets.character2;
		character3 = Assets.character3;
		character4 = Assets.character4;
		character5 = Assets.character5;
		character6 = Assets.character6;
		character7 = Assets.character7;
		character8 = Assets.character8;
		character9 = Assets.character9;
		character10 = Assets.character10;
		character11 = Assets.character11;
		character12 = Assets.character12;
		character13 = Assets.character13;
		character14 = Assets.character14;
		character15 = Assets.character15;
		character16 = Assets.character16;
		character17 = Assets.character17;
		character18 = Assets.character18;
		character19 = Assets.character19;
		character20 = Assets.character20;
		character21 = Assets.character21;

		characterrun1 = Assets.characterrun1;
		characterrun2 = Assets.characterrun2;
		characterrun3 = Assets.characterrun3;
		characterrun4 = Assets.characterrun4;
		characterrun5 = Assets.characterrun5;
		characterrun6 = Assets.characterrun6;
		characterrun7 = Assets.characterrun7;
		characterrun8 = Assets.characterrun8;
		characterrun9 = Assets.characterrun9;
		characterrun10 = Assets.characterrun10;
		characterrun11 = Assets.characterrun11;
		characterrun12 = Assets.characterrun12;
		characterrun13 = Assets.characterrun13;
		characterrun14 = Assets.characterrun14;
		characterrun15 = Assets.characterrun15;
		characterrun16 = Assets.characterrun16;
		characterrun17 = Assets.characterrun17;

		characterhurt1 = Assets.characterhurt1;
		characterhurt2 = Assets.characterhurt2;
		characterhurt3 = Assets.characterhurt3;
		characterhurt4 = Assets.characterhurt4;
		characterhurt5 = Assets.characterhurt5;

		characterjump = Assets.characterJump;
		characterjump2 = Assets.characterJump2;
		characterjump3 = Assets.characterJump3;
		characterjump4 = Assets.characterJump4;
		characterjump5 = Assets.characterJump5;
		characterjump6 = Assets.characterJump6;
		characterjump7 = Assets.characterJump7;
		characterjump8 = Assets.characterJump8;
		characterjump9 = Assets.characterJump9;
		characterjump10 = Assets.characterJump10;
		characterjump11 = Assets.characterJump11;
		characterjump12 = Assets.characterJump12;
		characterjump13 = Assets.characterJump13;
		characterjump14 = Assets.characterJump14;
		characterjump15 = Assets.characterJump15;

		heliboy = Assets.heliboy;
		heliboy2 = Assets.heliboy2;
		heliboy3 = Assets.heliboy3;
		heliboy4 = Assets.heliboy4;
		heliboy5 = Assets.heliboy5;

		explosion1 = Assets.explosion1;
		explosion2 = Assets.explosion2;
		explosion3 = Assets.explosion3;
		explosion4 = Assets.explosion4;
		explosion5 = Assets.explosion5;

		finish = Assets.finish;

		life = Assets.life;

		exanimate1 = new Animation();
		exanimate1.addFrame(explosion1, 100);
		exanimate1.addFrame(explosion2, 100);
		exanimate1.addFrame(explosion3, 100);
		exanimate1.addFrame(explosion4, 100);
		exanimate1.addFrame(explosion5, 100);

		anim = new Animation();
		anim.addFrame(character, 1250);
		anim.addFrame(character2, 50);
		anim.addFrame(character3, 50);
		anim.addFrame(character4, 50);
		anim.addFrame(character5, 50);
		anim.addFrame(character6, 50);
		anim.addFrame(character7, 50);
		anim.addFrame(character8, 50);
		anim.addFrame(character9, 50);
		anim.addFrame(character10, 50);
		anim.addFrame(character11, 50);
		anim.addFrame(character12, 50);
		anim.addFrame(character13, 50);
		anim.addFrame(character14, 50);
		anim.addFrame(character15, 50);
		anim.addFrame(character16, 50);
		anim.addFrame(character17, 50);
		anim.addFrame(character18, 50);
		anim.addFrame(character19, 50);
		anim.addFrame(character20, 50);
		anim.addFrame(character21, 50);

		hurtanim = new Animation();
		hurtanim.addFrame(characterhurt1, 500);
		hurtanim.addFrame(characterhurt2, 500);
		hurtanim.addFrame(characterhurt3, 500);
		hurtanim.addFrame(characterhurt4, 500);
		hurtanim.addFrame(characterhurt5, 500);

		janim = new Animation();
		janim.addFrame(characterjump, 50);
		janim.addFrame(characterjump2, 50);
		janim.addFrame(characterjump3, 50);
		janim.addFrame(characterjump4, 50);
		janim.addFrame(characterjump5, 50);
		janim.addFrame(characterjump6, 50);
		janim.addFrame(characterjump7, 50);
		janim.addFrame(characterjump8, 50);
		janim.addFrame(characterjump9, 50);
		janim.addFrame(characterjump10, 50);
		janim.addFrame(characterjump11, 50);
		janim.addFrame(characterjump12, 50);
		janim.addFrame(characterjump13, 50);
		janim.addFrame(characterjump14, 50);
		janim.addFrame(characterjump15, 50);

		ranim = new Animation();
		ranim.addFrame(characterrun1, 10);
		ranim.addFrame(characterrun2, 10);
		ranim.addFrame(characterrun3, 10);
		ranim.addFrame(characterrun4, 10);
		ranim.addFrame(characterrun5, 10);
		ranim.addFrame(characterrun6, 10);
		ranim.addFrame(characterrun7, 10);
		ranim.addFrame(characterrun8, 10);
		ranim.addFrame(characterrun9, 10);
		ranim.addFrame(characterrun10, 10);
		ranim.addFrame(characterrun11, 10);
		ranim.addFrame(characterrun12, 10);
		ranim.addFrame(characterrun13, 10);
		ranim.addFrame(characterrun14, 10);
		ranim.addFrame(characterrun15, 10);
		ranim.addFrame(characterrun16, 10);
		ranim.addFrame(characterrun17, 10);

		hanim = new Animation();
		hanim.addFrame(heliboy, 100);
		hanim.addFrame(heliboy2, 100);
		hanim.addFrame(heliboy3, 100);
		hanim.addFrame(heliboy4, 100);
		hanim.addFrame(heliboy5, 100);
		hanim.addFrame(heliboy4, 100);
		hanim.addFrame(heliboy3, 100);
		hanim.addFrame(heliboy2, 100);

		fanim = new Animation();
		fanim.addFrame(Assets.ring.get(0), 100);
		fanim.addFrame(Assets.ring.get(1), 100);
		fanim.addFrame(Assets.ring.get(2), 100);
		fanim.addFrame(Assets.ring.get(3), 100);
		fanim.addFrame(Assets.ring.get(4), 100);
		fanim.addFrame(Assets.ring.get(5), 100);
		fanim.addFrame(Assets.ring.get(6), 100);

		lanim = new Animation();
		lanim.addFrame(life, 100);

		/*
		 * Set all Assets of level 1
		 */
		if (robot.level == 1) {
			enemyGun = new Animation();
			int size = Assets.lightball.size();
			for (int i = 0; i < size; i++) {
				enemyGun.addFrame((Image) Assets.lightball.get(i), 50);
			}

			firepunch2 = new Animation();
			size = Assets.firepunch2.size();
			for (int i = 0; i < size; i++) {
				firepunch2.addFrame((Image) Assets.firepunch2.get(i), 50);
			}

			lastEnemyAnim1 = new Animation();
			size = Assets.biglastenemy1.size();
			for (int i = 0; i < size; i++) {
				lastEnemyAnim1
						.addFrame((Image) Assets.biglastenemy1.get(i), 30);
			}

			// Initialize Rockets for level 1
			// rocket = new Rocket(700, 580, 1, 10, 10);
			rockets = new ArrayList<Rocket>();
			InitializeRockets initialeRockets = new InitializeRockets();
			initialeRockets.setCords(Integer.toString(robot.level),
					this.context);
			rockets = initialeRockets.getRockets();

			uRT = new UpdateRocketsThread();

			// Initialize Broken Rockets for level 1
			brokenRockets = new ArrayList<BrokenRocket>();
			InitializeBrokenRockets initialeBrokenRockets = new InitializeBrokenRockets();
			initialeBrokenRockets.setCords(Integer.toString(robot.level),
					this.context);
			brokenRockets = initialeBrokenRockets.getBrokenRockets();

			uBRT = new UpdateBrokenRocketsThread();
		}

		/*
		 * Set all Assets of level 2
		 */
		if (robot.level == 2) {
			enemyGun = new Animation();
			int size = Assets.lightball.size();
			for (int i = 0; i < size; i++) {
				enemyGun.addFrame((Image) Assets.lightball.get(i), 50);
			}

			lastEnemyAnim2 = new Animation();
			size = Assets.biglastenemy2.size();
			for (int i = 0; i < size; i++) {
				lastEnemyAnim2
						.addFrame((Image) Assets.biglastenemy2.get(i), 75);
			}

			lastEnemyjumpAnim2 = new Animation();
			size = Assets.biglastenemyjump2.size();
			for (int i = 0; i < size; i++) {
				lastEnemyjumpAnim2.addFrame(
						(Image) Assets.biglastenemyjump2.get(i), 100);
			}

			lastEnemykickAnim2 = new Animation();
			size = Assets.biglastenemykick2.size();
			for (int i = 0; i < size; i++) {
				lastEnemykickAnim2.addFrame(
						(Image) Assets.biglastenemykick2.get(i), 50);
			}

			lastEnemyflipAnim2 = new Animation();
			size = Assets.biglastenemyflip2.size();
			for (int i = 0; i < size; i++) {
				lastEnemyflipAnim2.addFrame(
						(Image) Assets.biglastenemyflip2.get(i), 50);
			}

			lastEnemyrunAnim2 = new Animation();
			size = Assets.biglastenemyrun2.size();
			for (int i = 0; i < size; i++) {
				lastEnemyrunAnim2.addFrame(
						(Image) Assets.biglastenemyrun2.get(i), 50);
			}

			lastEnemyshootAnim2 = new Animation();
			size = Assets.biglastenemyshoot2.size();
			for (int i = 0; i < size; i++) {
				lastEnemyshootAnim2.addFrame(
						(Image) Assets.biglastenemyshoot2.get(i), 100);
				if (i == size - 1) {
					lastEnemyshootAnim2.addFrame(
							(Image) Assets.biglastenemyshoot2.get(i), 400);
				}
			}

			firepunch2 = new Animation();
			size = Assets.firepunch2.size();
			for (int i = 0; i < size; i++) {
				firepunch2.addFrame((Image) Assets.firepunch2.get(i), 50);
			}

			fireball = new Animation();
			size = Assets.fireball.size();
			for (int i = 0; i < size; i++) {
				fireball.addFrame((Image) Assets.fireball.get(i), 50);
			}

			// Initialize Rockets for level 2
			rockets = new ArrayList<Rocket>();
			InitializeRockets initialeRockets = new InitializeRockets();
			initialeRockets.setCords(Integer.toString(robot.level),
					this.context);
			rockets = initialeRockets.getRockets();

			uRT = new UpdateRocketsThread();

			// Initialize Broken Rockets for level 2
			brokenRockets = new ArrayList<BrokenRocket>();
			InitializeBrokenRockets initialeBrokenRockets = new InitializeBrokenRockets();
			initialeBrokenRockets.setCords(Integer.toString(robot.level),
					this.context);
			brokenRockets = initialeBrokenRockets.getBrokenRockets();

			uBRT = new UpdateBrokenRocketsThread();
		}

		/*
		 * Set all Assets of level 3
		 */
		if (robot.level == 3) {
			enemyGun = new Animation();
			int size = Assets.lightball.size();
			for (int i = 0; i < size; i++) {
				enemyGun.addFrame((Image) Assets.lightball.get(i), 50);
			}

			lastEnemyAnim2 = new Animation();
			size = Assets.biglastenemy2.size();
			for (int i = 0; i < size; i++) {
				lastEnemyAnim2
						.addFrame((Image) Assets.biglastenemy2.get(i), 75);
			}

			lastEnemyjumpAnim2 = new Animation();
			size = Assets.biglastenemyjump2.size();
			for (int i = 0; i < size; i++) {
				lastEnemyjumpAnim2.addFrame(
						(Image) Assets.biglastenemyjump2.get(i), 100);
			}

			lastEnemykickAnim2 = new Animation();
			size = Assets.biglastenemykick2.size();
			for (int i = 0; i < size; i++) {
				lastEnemykickAnim2.addFrame(
						(Image) Assets.biglastenemykick2.get(i), 50);
			}

			lastEnemyflipAnim2 = new Animation();
			size = Assets.biglastenemyflip2.size();
			for (int i = 0; i < size; i++) {
				lastEnemyflipAnim2.addFrame(
						(Image) Assets.biglastenemyflip2.get(i), 50);
			}

			lastEnemyrunAnim2 = new Animation();
			size = Assets.biglastenemyrun2.size();
			for (int i = 0; i < size; i++) {
				lastEnemyrunAnim2.addFrame(
						(Image) Assets.biglastenemyrun2.get(i), 50);
			}

			lastEnemyshootAnim2 = new Animation();
			size = Assets.biglastenemyshoot2.size();
			for (int i = 0; i < size; i++) {
				lastEnemyshootAnim2.addFrame(
						(Image) Assets.biglastenemyshoot2.get(i), 100);
				if (i == size - 1) {
					lastEnemyshootAnim2.addFrame(
							(Image) Assets.biglastenemyshoot2.get(i), 400);
				}
			}

			firepunch2 = new Animation();
			size = Assets.firepunch2.size();
			for (int i = 0; i < size; i++) {
				firepunch2.addFrame((Image) Assets.firepunch2.get(i), 50);
			}

			fireball = new Animation();
			size = Assets.fireball.size();
			for (int i = 0; i < size; i++) {
				fireball.addFrame((Image) Assets.fireball.get(i), 50);
			}

			// Initialize Rockets for level 3
			rockets = new ArrayList<Rocket>();
			InitializeRockets initialeRockets = new InitializeRockets();
			initialeRockets.setCords(Integer.toString(robot.level),
					this.context);
			rockets = initialeRockets.getRockets();

			uRT = new UpdateRocketsThread();

			// Initialize Broken Rockets for level 3
			brokenRockets = new ArrayList<BrokenRocket>();
			InitializeBrokenRockets initialeBrokenRockets = new InitializeBrokenRockets();
			initialeBrokenRockets.setCords(Integer.toString(robot.level),
					this.context);
			brokenRockets = initialeBrokenRockets.getBrokenRockets();

			uBRT = new UpdateBrokenRocketsThread();
		}

		/*
		 * Set all Assets of level 4
		 */
		if (robot.level == 4) {
			enemyGun = new Animation();
			int size = Assets.lightball.size();
			for (int i = 0; i < size; i++) {
				enemyGun.addFrame((Image) Assets.lightball.get(i), 50);
			}

			lastEnemyAnim2 = new Animation();
			size = Assets.biglastenemy2.size();
			for (int i = 0; i < size; i++) {
				lastEnemyAnim2
						.addFrame((Image) Assets.biglastenemy2.get(i), 75);
			}

			lastEnemyjumpAnim2 = new Animation();
			size = Assets.biglastenemyjump2.size();
			for (int i = 0; i < size; i++) {
				lastEnemyjumpAnim2.addFrame(
						(Image) Assets.biglastenemyjump2.get(i), 100);
			}

			lastEnemykickAnim2 = new Animation();
			size = Assets.biglastenemykick2.size();
			for (int i = 0; i < size; i++) {
				lastEnemykickAnim2.addFrame(
						(Image) Assets.biglastenemykick2.get(i), 50);
			}

			lastEnemyflipAnim2 = new Animation();
			size = Assets.biglastenemyflip2.size();
			for (int i = 0; i < size; i++) {
				lastEnemyflipAnim2.addFrame(
						(Image) Assets.biglastenemyflip2.get(i), 50);
			}

			lastEnemyrunAnim2 = new Animation();
			size = Assets.biglastenemyrun2.size();
			for (int i = 0; i < size; i++) {
				lastEnemyrunAnim2.addFrame(
						(Image) Assets.biglastenemyrun2.get(i), 50);
			}

			lastEnemyshootAnim2 = new Animation();
			size = Assets.biglastenemyshoot2.size();
			for (int i = 0; i < size; i++) {
				lastEnemyshootAnim2.addFrame(
						(Image) Assets.biglastenemyshoot2.get(i), 100);
				if (i == size - 1) {
					lastEnemyshootAnim2.addFrame(
							(Image) Assets.biglastenemyshoot2.get(i), 400);
				}
			}

			firepunch2 = new Animation();
			size = Assets.firepunch2.size();
			for (int i = 0; i < size; i++) {
				firepunch2.addFrame((Image) Assets.firepunch2.get(i), 50);
			}

			fireball = new Animation();
			size = Assets.fireball.size();
			for (int i = 0; i < size; i++) {
				fireball.addFrame((Image) Assets.fireball.get(i), 50);
			}

			// Initialize Rockets for level 4
			rockets = new ArrayList<Rocket>();
			InitializeRockets initialeRockets = new InitializeRockets();
			initialeRockets.setCords(Integer.toString(robot.level),
					this.context);
			rockets = initialeRockets.getRockets();

			uRT = new UpdateRocketsThread();

			// Initialize RocketsB for level 4
			rocketsB = new ArrayList<RocketB>();
			InitializeRocketsB initialRocketsB = new InitializeRocketsB();
			initialRocketsB.setCords(Integer.toString(robot.level),
					this.context);
			rocketsB = initialRocketsB.getRockets();

			uRTB = new UpdateRocketsBThread();
		}

		/*
		 * Set all Assets of level 5, 6, 7 ,8 and 9
		 */
		if (robot.level == 5 || robot.level == 6 || robot.level == 7
				|| robot.level == 8 || robot.level == 9) {
			enemyGun = new Animation();
			int size = Assets.lightball.size();
			for (int i = 0; i < size; i++) {
				enemyGun.addFrame((Image) Assets.lightball.get(i), 50);
			}

			lastEnemyAnim5 = new Animation();
			size = Assets.tankwolf.size();
			for (int i = 0; i < size; i++) {
				lastEnemyAnim5.addFrame((Image) Assets.tankwolf.get(i), 75);
			}

			lastEnemyBomb5 = new Animation();
			size = Assets.tankbomb.size();
			for (int i = 0; i < size; i++) {
				lastEnemyBomb5.addFrame((Image) Assets.tankbomb.get(i), 75);
			}

			// Initialize Rockets for level 5
			rockets = new ArrayList<Rocket>();
			InitializeRockets initialeRockets = new InitializeRockets();
			initialeRockets.setCords(Integer.toString(robot.level),
					this.context);
			rockets = initialeRockets.getRockets();

			uRT = new UpdateRocketsThread();

			// Initialize RocketsB for level 5
			rocketsB = new ArrayList<RocketB>();
			InitializeRocketsB initialRocketsB = new InitializeRocketsB();
			initialRocketsB.setCords(Integer.toString(robot.level),
					this.context);
			rocketsB = initialRocketsB.getRockets();

			uRTB = new UpdateRocketsBThread();

			// Initialize Broken Rockets for level 5
			brokenRockets = new ArrayList<BrokenRocket>();
			InitializeBrokenRockets initialeBrokenRockets = new InitializeBrokenRockets();
			initialeBrokenRockets.setCords(Integer.toString(robot.level),
					this.context);
			brokenRockets = initialeBrokenRockets.getBrokenRockets();

			uBRT = new UpdateBrokenRocketsThread();

			// Initialize Broken Rockets 3 for level 5
			brokenRockets3 = new ArrayList<BrokenRocket3>();
			InitializeBrokenRockets3 initialeBrokenRockets3 = new InitializeBrokenRockets3();
			initialeBrokenRockets3.setCords(Integer.toString(robot.level),
					this.context);
			brokenRockets3 = initialeBrokenRockets3.getBrokenRockets();

			uBRT3 = new UpdateBrokenRockets3Thread();

			// Initialize SkeletonRunner for level 5
			skeletonRunner = new ArrayList<SkeletonRunner>();
			InitializeSkeletonRunner initialSkeletonRunner = new InitializeSkeletonRunner();
			initialSkeletonRunner.setCords(Integer.toString(robot.level),
					this.context);
			skeletonRunner = initialSkeletonRunner.getSkeletons();

			uSR = new UpdateSkeletonRunnersThread();
		}

		/*
		 * Set all Assets of level 10
		 */
		if (robot.level == 10) {
			enemyGun = new Animation();
			int size = Assets.lightball.size();
			for (int i = 0; i < size; i++) {
				enemyGun.addFrame((Image) Assets.lightball.get(i), 50);
			}
			
			lastEnemyAnim10 = new Animation();
			size = Assets.flywolf.size();
			for (int i = 0; i < size; i++) {
				lastEnemyAnim10.addFrame((Image) Assets.flywolf.get(i), 75);
			}

			lastEnemyBomb5 = new Animation();
			size = Assets.tankbomb.size();
			for (int i = 0; i < size; i++) {
				lastEnemyBomb5.addFrame((Image) Assets.tankbomb.get(i), 75);
			}

			// Initialize Rockets for level 10
			rockets = new ArrayList<Rocket>();
			InitializeRockets initialeRockets = new InitializeRockets();
			initialeRockets.setCords(Integer.toString(robot.level),
					this.context);
			rockets = initialeRockets.getRockets();

			uRT = new UpdateRocketsThread();

			// Initialize RocketsB for level 10
			rocketsB = new ArrayList<RocketB>();
			InitializeRocketsB initialRocketsB = new InitializeRocketsB();
			initialRocketsB.setCords(Integer.toString(robot.level),
					this.context);
			rocketsB = initialRocketsB.getRockets();

			uRTB = new UpdateRocketsBThread();

			// Initialize Broken Rockets for level 10
			brokenRockets = new ArrayList<BrokenRocket>();
			InitializeBrokenRockets initialeBrokenRockets = new InitializeBrokenRockets();
			initialeBrokenRockets.setCords(Integer.toString(robot.level),
					this.context);
			brokenRockets = initialeBrokenRockets.getBrokenRockets();

			uBRT = new UpdateBrokenRocketsThread();

			// Initialize Broken Rockets 3 for level 10
			brokenRockets3 = new ArrayList<BrokenRocket3>();
			InitializeBrokenRockets3 initialeBrokenRockets3 = new InitializeBrokenRockets3();
			initialeBrokenRockets3.setCords(Integer.toString(robot.level),
					this.context);
			brokenRockets3 = initialeBrokenRockets3.getBrokenRockets();

			uBRT3 = new UpdateBrokenRockets3Thread();

			// Initialize SkeletonRunner for level 10
			skeletonRunner = new ArrayList<SkeletonRunner>();
			InitializeSkeletonRunner initialSkeletonRunner = new InitializeSkeletonRunner();
			initialSkeletonRunner.setCords(Integer.toString(robot.level),
					this.context);
			skeletonRunner = initialSkeletonRunner.getSkeletons();

			uSR = new UpdateSkeletonRunnersThread();
		}

		currentSprite = anim.getImage();

		loadMap();

		// Defining paint objects
		paint1 = new Paint();
		paint2 = new Paint();
		paintInfo = new Paint();

		Typeface type = Typeface.createFromAsset(this.context.getAssets(),
				"fonts/vampire-games-3d.ttf");

		paint1 = paintClass.PaintStrings(Color.RED, 70, Paint.Align.CENTER);
		paint2 = paintClass.PaintStrings(Color.WHITE, 150, Paint.Align.CENTER);
		paintInfo = paintClass.PaintStrings(Color.RED, 75, Paint.Align.CENTER);

		paint1.setTypeface(type);
		paint2.setTypeface(type);
		paintInfo.setTypeface(type);

		// Initialize joystick's image
		currentJoystickMotion = Assets.joystick;
	}

	private void loadMap() {
		ArrayList lines = new ArrayList();
		int width = 0;
		int height = 0;

		Scanner scanner = null;
		if (robot.level == 1) {
			scanner = new Scanner(SampleGame.map);
		} else {
			scanner = new Scanner(NewLevelGame.map);
		}

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();

			// no more lines to read
			if (line == null) {
				break;
			}

			if (!line.startsWith("!")) {
				lines.add(line);
				width = Math.max(width, line.length());

			}
		}
		height = lines.size();

		for (int j = 0; j < 18/* 12 */; j++) {
			String line = (String) lines.get(j);
			for (int i = 0; i < width; i++) {

				if (i < line.length()) {
					char ch = line.charAt(i);
					Tile t = new Tile(i, j, Character.getNumericValue(ch));
					tilearray.add(t);
				}

			}
		}
		scanner.close();
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

		// We have four separate update methods in this example.
		// Depending on the state of the game, we call different update methods.

		if (state == GameState.Ready)
			updateReady(touchEvents);
		if (state == GameState.ReadyForLeader)
			updateReadyLeader(touchEvents);
		if (state == GameState.Running)
			updateRunning(touchEvents, deltaTime);
		if (state == GameState.Paused)
			updatePaused(touchEvents);
		if (state == GameState.Finish)
			updateFinish(touchEvents);
		if (state == GameState.GameOver)
			updateGameOver(touchEvents);
		if (state == GameState.Leader)
			updateLeader(touchEvents);
	}

	private void updateReady(List<TouchEvent> touchEvents) {

		// This example starts with a "Ready" screen.
		// When the user touches the screen, the game begins.
		// state now becomes GameState.Running.
		// Now the updateRunning() method will be called!

		if (touchEvents.size() > 0)
			state = GameState.Running;
	}

	private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
		// 1. All touch input is handled here:
		joystickEvents(touchEvents);

		// 2. Check miscellaneous events like death:
		if (robot.lives == 0) {
			state = GameState.GameOver;
		}

		if (bg2.getBgX() < 100) {
			state = GameState.Finish;
		}

		if (bg1.getBgX() <= /*-100*/-5300 && !isAfterlLeader) {
			state = GameState.ReadyForLeader;
		}

		// 3. Call individual update() methods here.
		// This is where all the game updates happen.
		// For example, robot.update();
		robot.update();

		// set the right img if robot jumped
		robotPoiseJump();

		ArrayList projectiles = robot.getProjectiles();
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = (Projectile) projectiles.get(i);
			if (p.isVisible() == true) {
				p.update();
			} else {
				projectiles.remove(i);
			}
		}

		updateTiles();

		int foodSize = food.size();
		for (int i = 0; i < foodSize; i++) {
			if (food.get(i).isVisible() == true) {
				food.get(i).update();
			} else {
				robot.setStoreFood(food.get(i).getPoints());
				food.get(i).setPoints(0);
				food.get(i).destroy();
			}
		}

		// update life, weapons
		uAT.updateLife(robot);
		if (uWT != null) {
			uWT.UpdateWeapons(weapons);
		}

		// Update all enemies,shoot and set the rate of attack
		if (hbs != null) {
			uET.UpdateEnemies();
		}
		// pMT.update();
		// update rockets
		if (uRT != null) {
			uRT.UpdateRockets(rockets);
		}

		if (uRTB != null) {
			uRTB.UpdateRockets(rocketsB);
		}

		if (uSR != null) {
			uSR.UpdateSkeletonRunners(skeletonRunner);
		}

		// update broken rockets
		if (uBRT != null) {
			uBRT.UpdateBrokenRockets(brokenRockets);
		}

		if (uBRT3 != null) {
			uBRT3.UpdateBrokenRockets(brokenRockets3);
		}

		bg1.update();
		bg2.update();
		animate();

		if (robot.getCenterY() > 700 || robot.getPower() <= 0) {
			state = GameState.GameOver;
		}
	}

	// Implement the method inBounds of AndroidEvents class
	public boolean inBounds(TouchEvent event, int x, int y, int width,
			int height) {
		return eventsClass.inBounds(event, x, y, width, height);
	}

	private void updatePaused(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (inBounds(event, 0, 0, 800, 240)) {

					if (!inBounds(event, 0, 0, 50, 66)) {
						resume();
					}
				}

				if (inBounds(event, 0, 240, 800, 240)) {
					nullify();
					goToMenu();
				}
			}
		}
	}

	private void updateReadyLeader(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (inBounds(event, 520, 260, 200, 200)) {
					state = GameState.Leader;
				}
			}
		}
	}

	private void updateFinish(List<TouchEvent> touchEvents) {
		this.player.setFood(robot.getStoreFood());
		this.player.setLives(robot.lives);
		game.setScreen(new FinishGameScreen(this.context, game, this.player));
		nullify();
		return;
	}

	private void updateGameOver(List<TouchEvent> touchEvents) {
		if (!saved) {
			this.player.setLives(robot.lives <= 1 ? 0 : robot.lives - 1);

			GameOverUpdate gou = new GameOverUpdate(this.context, this.player);
			saved = true;
		}

		int len = touchEvents.size();

		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DOWN) {
				if (inBounds(event, 0, 0, 1280, 720)) {
					nullify();
					game.setScreen(new MainMenuScreen(this.context, game));
					return;
				}
			}
		}
	}

	private void updateLeader(List<TouchEvent> touchEvents) {
		// initialize the last enemy
		if (lastBigEnemy == null) {
			robot.setSpeedX(0);
			robot.stopRight();
			robot.stopLeft();
			robot.setDucked(false);
			robot.centerX = 0;

			if (robot.level == 1) {
				lastBigEnemy = new LastBigEnemyLevel(1000, 430);
				lastBigEnemy.health = 50;
			}
			if (robot.level == 2) {
				lastBigEnemy = new LastBigEnemyLevel(1000, 600);
				lastBigEnemy.health = 50;
			}
			if (robot.level == 3) {
				lastBigEnemy = new LastBigEnemyLevel(1000, 600);
				lastBigEnemy.health = 50;
			}
			if (robot.level == 4) {
				lastBigEnemy = new LastBigEnemyLevel(1000, 600);
				lastBigEnemy.health = 50;
			}
			if (robot.level == 5) {
				lastBigEnemy = new LastBigEnemyLevel(1000, 560);
				lastBigEnemy.health = 50;
			}
			if (robot.level == 6) {
				lastBigEnemy = new LastBigEnemyLevel(1000, 560);
				lastBigEnemy.health = 60;
			}
			if (robot.level == 7) {
				lastBigEnemy = new LastBigEnemyLevel(1000, 560);
				lastBigEnemy.health = 70;
			}
			if (robot.level == 8) {
				lastBigEnemy = new LastBigEnemyLevel(1000, 560);
				lastBigEnemy.health = 80;
			}
			if (robot.level == 9) {
				lastBigEnemy = new LastBigEnemyLevel(1000, 560);
				lastBigEnemy.health = 90;
			}
			if (robot.level == 10) {
				lastBigEnemy = new LastBigEnemyLevel(1000, 500);
				lastBigEnemy.health = 100;
			}
		}

		// 1. All touch input is handled here:
		joystickEvents(touchEvents);

		// 2. Check miscellaneous events like win the last big enemy
		if (lastBigEnemy.health == 0) {
			isAfterlLeader = true;
			lastBigEnemy.setReadyToFire(false);
			lastBigEnemy = null;
			fET.destroy();
			fET = null;
			state = GameState.Running;
			return;
		}

		// 3. All updates about leader is here
		robot.update();
		robotPoiseJump();

		ArrayList projectiles = robot.getProjectiles();

		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = (Projectile) projectiles.get(i);
			if (p.isVisible() == true) {
				p.update();
			} else {
				projectiles.remove(i);
			}
		}

		updateTiles();

		if (fET == null) {
			fET = new FinalEnemyThread(lastBigEnemy);
		}
		fET.updateBigEnemy();

		animate();

		if (robot.getPower() <= 0) {
			state = GameState.GameOver;
		}
	}

	private void robotPoiseJump() {
		if (robot.isJumped()) {
			if (robot.stand.equals("f")) {
				currentSprite = janim.getImage();
			} else if (robot.stand.equals("b")) {
				currentSprite = janimback.getImage();
			}
		} else if (robot.isJumped() == false && robot.isDucked() == false
				&& robot.isUp() == false && robot.isUpAForward() == false
				&& !robot.isMovingRight() && !robot.isMovingLeft()
				&& !robot.isUpAForward() && !robot.isUpAForwardBack()) {
			if (robot.stand.equals("f")) {
				currentSprite = anim.getImage();
			} else if (robot.stand.equals("b")) {
				currentSprite = animBack.getImage();
			}
		} else if (robot.isMovingRight()) {
			currentSprite = ranim.getImage();
		} else if (robot.isMovingLeft()) {
			currentSprite = ranimback.getImage();
		}
		if (robot.hurted) {
			if (robot.stand.equals("f")) {
				currentSprite = hurtanim.getImage();
			} else if (robot.stand.equals("b")) {
				currentSprite = hurtanimback.getImage();
			}
			robot.hurted = false;
		}
	}

	private void joystickEvents(List<TouchEvent> touchEvents) {
		// 1. All touch input is handled here:
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);

			if (event.type == TouchEvent.TOUCH_DRAGGED) {
				// button moves right
				if (inBounds(event, 250, 485, 90, 90)) {
					currentJoystickMotion = Assets.joystickright;
					robot.setDucked(false);
					robot.moveRight();
					robot.setMovingRight(true);
					robot.stand = "f";
					robot.setMovingLeft(false);
					robot.setUpAForward(false);
					robot.setUpAForwardBack(false);
				}
				// button moves left
				else if (inBounds(event, 50, 485, 90, 90)) {
					currentJoystickMotion = Assets.joystickleft;
					robot.setDucked(false);
					robot.moveLeft();
					robot.setMovingLeft(true);
					robot.stand = "b";
					robot.setMovingRight(false);
					robot.setUpAForward(false);
					robot.setUpAForwardBack(false);
				}
				// button sets character down
				else if (inBounds(event, 150, 585, 90, 95)
						&& robot.isJumped() == false) {
					currentJoystickMotion = Assets.joystickdown;
					if (robot.stand.equals("f")) {
						currentSprite = Assets.characterDown;
					} else if (robot.stand.equals("b")) {
						currentSprite = Assets.characterDownBack;
					}
					robot.setDucked(true);
					robot.setUp(false);
					robot.setUpAForward(false);
					robot.setUpAForwardBack(false);
					robot.setSpeedX(0);
				}
				// button sets character up
				else if (inBounds(event, 150, 380, 90, 95)
						&& robot.isJumped() == false) {
					currentJoystickMotion = Assets.joystickup;
					if (robot.stand.equals("f")) {
						currentSprite = Assets.characterup;
					} else if (robot.stand.equals("b")) {
						currentSprite = Assets.characterupback;
					}
					robot.setDucked(false);
					robot.setUpAForward(false);
					robot.setUpAForwardBack(false);
					robot.setUp(true);
					robot.setSpeedX(0);
				}
				// button sets character up and forward
				else if (inBounds(event, 250, 380, 90, 95)
						&& robot.isJumped() == false) {
					currentJoystickMotion = Assets.joystickupright;
					currentSprite = Assets.characterfu;
					robot.stand = "f";
					robot.setMovingRight(false);
					robot.setMovingLeft(false);
					robot.setDucked(false);
					robot.setUp(false);
					robot.setUpAForward(true);
					robot.setUpAForwardBack(false);
					robot.setSpeedX(0);
				}
				// button sets character up and back
				else if (inBounds(event, 50, 380, 90, 95)
						&& robot.isJumped() == false) {
					currentJoystickMotion = Assets.joystickupleft;
					currentSprite = Assets.characterfuback;
					robot.stand = "b";
					robot.setMovingRight(false);
					robot.setMovingLeft(false);
					robot.setDucked(false);
					robot.setUp(false);
					robot.setUpAForward(false);
					robot.setUpAForwardBack(true);
					robot.setSpeedX(0);
				}
			}

			if (event.type == TouchEvent.TOUCH_DOWN) {
				// button jump
				if (inBounds(event, 1150, 498, 100, 100)) {
					Assets.jump.play(0.85f);
					robot.jump();
					currentSprite = anim.getImage();
					robot.setDucked(false);
					robot.setUp(false);
				}
				// button shoots
				if (inBounds(event, 1040, 598, 100, 100)) {
					robot.setReadyToFire(true);
					if (robot.isReadyToFire()) {
						Assets.nilgun.play(0.85f);
						if (robot.isDucked() && robot.stand.equals("f")) {
							robot.shoot(20, 0, "ff");
						} else if (robot.isDucked() && robot.stand.equals("b")) {
							robot.shoot(20, 25, "fb");
						} else if (robot.isUp()) {
							robot.shoot(20, 25, "u");
						} else if (robot.isUpAForward()) {
							robot.shoot(20, 25, "fu");
						} else if (robot.isUpAForwardBack()) {
							robot.shoot(20, 25, "bu");
						} else if (robot.stand.equals("f")) {
							robot.shoot(20, 25, "ff");
						} else if (robot.stand.equals("b")) {
							robot.shoot(20, 25, "fb");
						}
					}
				}
			}

			if (event.type == TouchEvent.TOUCH_UP) {
				if (inBounds(event, 0, 415, 65, 65)) {
					currentSprite = anim.getImage();
					robot.setDucked(false);
				}

				if (inBounds(event, 0, 0, 50, 66)) {
					pause();
				}

				// set the robot in original position
				if (inBounds(event, 150, 585, 90, 95)
						|| inBounds(event, 150, 380, 90, 95)
						|| inBounds(event, 250, 380, 90, 95)
						|| inBounds(event, 50, 380, 90, 95)
						|| inBounds(event, 140, 470, 110, 115)
						|| inBounds(event, 240, 575, 105, 105)
						|| inBounds(event, 45, 575, 105, 105)
						|| inBounds(event, 250, 485, 90, 90)
						|| inBounds(event, 50, 485, 90, 90)) {
					currentJoystickMotion = Assets.joystick;
					robot.stopRight();
					robot.stopLeft();
					robot.setDucked(false);
					robot.setUp(false);
					robot.setUpAForward(false);
				}

				// when don't touch up in these areas then deactivate any event
				if ((!inBounds(event, 45, 380, 300, 300) && !inBounds(event,
						1040, 498, 250, 250))) {
					currentJoystickMotion = Assets.joystick;
					robot.setSpeedX(0);
					robot.stopRight();
					robot.stopLeft();
					robot.setDucked(false);
					robot.setUp(false);
				}
			}
		}
	}

	private void updateTiles() {

		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = (Tile) tilearray.get(i);
			t.update();
		}
	}

	@Override
	public void paint(float deltaTime) {
		final Graphics g = game.getGraphics();

		g.drawImage(Assets.background, bg1.getBgX(), bg1.getBgY());
		g.drawImage(Assets.background, bg2.getBgX(), bg2.getBgY());
		paintTiles(g);

		ArrayList projectiles = robot.getProjectiles();
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = (Projectile) projectiles.get(i);
			int x = 0, y = 0;
			if (p.direction.equals("ff") || p.direction.equals("fb")) {
				x = 10;
				y = 5;
			} else if (p.direction.equals("u") || p.direction.equals("fu")
					|| p.direction.equals("bu")) {
				x = 5;
				y = 10;
			}
			if (robot.gunName.equals("nil")) {
				g.drawRect(p.getX(), p.getY(), x, y, Color.RED);
			} else if (robot.gunName.equals("gun1")) {
				g.drawImage(supergunbullet.getImage(), p.getX(), p.getY(), 0, 0, 30, 19);
			}
		}

		// draw shoots of all enemies
		if (hbs != null) {
			for (int l = 0; l < hbs.size(); l++) {
				if (!hbs.get(l).getProjectilesEn().isEmpty()) {
					ProjecttileEnemy pEnemy = (ProjecttileEnemy) hbs.get(l)
							.getProjectilesEn().get(0);
					g.drawImage(enemyGun.getImage(), pEnemy.getX(),
							pEnemy.getY());
				}
			}
		}

		// draw the shoots only of the last big enemy
		if (fET != null) {
			fET.draw(g);
		}

		// First draw the game elements.
		g.drawImage(currentSprite, robot.getCenterX() - 61,
				robot.getCenterY() - 63);

		// Draw enemies
		if (hbs != null) {
			for (Heliboy item : hbs) {
				g.drawImage(hanim.getImage(), item.getCenterX() + 50,
						item.getCenterY() - 48);
				if (item.hurted) {
					item.paintHurted(g, item.centerX, item.centerY);
				}
			}
		}

		// draw foods
		for (FoodDrink f : food) {
			g.drawImage(fanim.getImage(), f.getCenterX() + 50,
					f.getCenterY() - 48);
		}

		// draw life
		g.drawImage(lanim.getImage(), life1.getCenterX(), life1.getCenterY());

		// draw weapons
		for (Weapon w : weapons) {
			g.drawImage(w.anim.getImage(), w.centerX, w.centerY);
			w.anim.update(10);
		}

		// draw last enemy
		if (state == GameState.Leader) {
			if (robot.level == 1) {
				g.drawImage(lastEnemyAnim1.getImage(),
						lastBigEnemy.getCenterX(), lastBigEnemy.getCenterY());
				if (lastBigEnemy.hurted) {
					lastBigEnemy.paintHurted(g);
				}
			}

			if (robot.level == 2 || robot.level == 3 || robot.level == 4) {
				lastBigEnemy.paintEnemy(g);
			}

			if (robot.level == 5 || robot.level == 6 || robot.level == 7
					|| robot.level == 8 || robot.level == 9) {
				lastBigEnemy.paintEnemy(g);
			}
			
			if (robot.level == 10) {
				lastBigEnemy.paintEnemy(g);
			}
		}

		// update rockets
		if (uRT != null) {
			for (Rocket r : rockets) {
				r.paint(g);
			}
		}

		if (uRTB != null) {
			for (RocketB r : rocketsB) {
				r.paint(g);
			}
		}

		if (uSR != null) {
			for (SkeletonRunner sr : skeletonRunner) {
				sr.paint(g);
			}
		}

		// update broken rockets
		if (uBRT != null) {
			for (BrokenRocket br : brokenRockets) {
				br.paint(g);
			}
		}

		if (uBRT3 != null) {
			for (BrokenRocket3 br3 : brokenRockets3) {
				br3.paint(g);
			}
		}

		// Example:
		// g.drawImage(Assets.background, 0, 0);
		// g.drawImage(Assets.character, characterX, characterY);

		// Secondly, draw the UI above the game elements.
		if (state == GameState.Ready)
			drawReadyUI();
		if (state == GameState.ReadyForLeader)
			drawReadyLeaderUI();
		if (state == GameState.Running || state == GameState.Leader)
			drawRunningUI();
		if (state == GameState.Paused)
			drawPausedUI();
		if (state == GameState.GameOver)
			drawGameOverUI();

	}

	private void paintTiles(Graphics g) {
		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = (Tile) tilearray.get(i);
			if (t.type != 0 && t.type != 9) {
				g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY());
			}
			if (t.type == 9) {
				g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY());
			}
		}
	}

	public void animate() {
		anim.update(10);
		animBack.update(10);
		hanim.update(50);
		fanim.update(70);
		janim.update(50);
		janimback.update(50);
		hurtanim.update(100);
		hurtanimback.update(100);
		ranim.update(10);
		ranimback.update(10);
		if (robot.level == 1) {
			lastEnemyAnim1.update(10);
			firepunch2.update(20);
		}
		if (robot.level == 2 || robot.level == 3 || robot.level == 4) {
			lastEnemyAnim2.update(10);
			lastEnemyjumpAnim2.update(100);
			lastEnemyrunAnim2.update(10);
			lastEnemyflipAnim2.update(10);
			lastEnemyshootAnim2.update(10);
			lastEnemykickAnim2.update(10);
			firepunch2.update(20);
			fireball.update(20);
		}
		if (robot.level == 5 || robot.level == 6 || robot.level == 7
				|| robot.level == 8 || robot.level == 9) {
			lastEnemyAnim5.update(10);
			lastEnemyBomb5.update(10);
		}
		if(robot.level == 10){
			lastEnemyAnim10.update(10);
			lastEnemyBomb5.update(10);
		}
		enemyGun.update(20);
		supergunbullet.update(10);
	}

	private void nullify() {
		// Set all variables to null. You will be recreating them in the
		// constructor.
		lastEnemyjumpAnim2 = null;
		lastEnemykickAnim2 = null;
		lastEnemyflipAnim2 = null;
		lastEnemyrunAnim2 = null;
		lastEnemyshootAnim2 = null;
		firepunch2 = null;
		fireball = null;
		//gunbulletnil = null;
		supergunbullet = null;
		hurtanimback = null;
		ranimback = null;
		animBack = null;
		janimback = null;
		// fooddrink = null;
		finish = null;
		paint1 = null;
		tilearray = null;
		paint2 = null;
		paintInfo = null;
		eventsClass = null;
		paintClass = null;
		bg1 = null;
		bg2 = null;
		robot = null;
		hbs = null;
		food = null;
		life1 = null;
		life = null;
		uAT = null;
		fET = null;
		uET = null;
		uWT = null;
		weapons = null;
		weapon = null;
		lastBigEnemy = null;
		lastEnemyAnim2 = null;
		lastEnemyAnim5 = null;
		lastEnemyAnim10 = null;
		currentSprite = null;
		character = null;
		character2 = null;
		character3 = null;
		character4 = null;
		character5 = null;
		character6 = null;
		character7 = null;
		character8 = null;
		character9 = null;
		character10 = null;
		character11 = null;
		character12 = null;
		character13 = null;
		character14 = null;
		character15 = null;
		character16 = null;
		character17 = null;
		character18 = null;
		character19 = null;
		character20 = null;
		character21 = null;
		characterhurt1 = null;
		characterhurt2 = null;
		characterhurt3 = null;
		characterhurt4 = null;
		characterhurt5 = null;
		characterrun1 = null;
		characterrun2 = null;
		characterrun3 = null;
		characterrun4 = null;
		characterrun5 = null;
		characterrun6 = null;
		characterrun7 = null;
		characterrun8 = null;
		characterrun9 = null;
		characterrun10 = null;
		characterrun11 = null;
		characterrun12 = null;
		characterrun13 = null;
		characterrun14 = null;
		characterrun15 = null;
		characterrun16 = null;
		characterrun17 = null;
		characterjump = null;
		characterjump2 = null;
		characterjump3 = null;
		characterjump4 = null;
		characterjump5 = null;
		characterjump6 = null;
		characterjump7 = null;
		characterjump8 = null;
		characterjump9 = null;
		characterjump10 = null;
		characterjump11 = null;
		characterjump12 = null;
		characterjump13 = null;
		characterjump14 = null;
		characterjump15 = null;
		heliboy = null;
		heliboy2 = null;
		heliboy3 = null;
		heliboy4 = null;
		heliboy5 = null;
		anim = null;
		janim = null;
		hanim = null;
		ranim = null;
		fanim = null;
		lanim = null;
		hurtanim = null;
		lastEnemyAnim = null;
		lastEnemyAnim1 = null;
		this.player = null;

		// Call garbage collector to clean up memory.
		System.gc();
	}

	private void drawReadyLeaderUI() {
		Graphics g = game.getGraphics();

		g.drawARGB(155, 0, 0, 0);
		g.drawImage(Assets.target, 520, 260, 0, 0, 200, 200);
	}

	private void drawReadyUI() {
		Graphics g = game.getGraphics();

		g.drawARGB(155, 0, 0, 0);
		g.drawString("Tap to Start.", 620, 240, paint1);
	}

	private void drawRunningUI() {
		Graphics g = game.getGraphics();

		g.drawRect(0, 0, 1285, 66, Color.argb(155, 0, 0, 0));
		g.drawImage(currentJoystickMotion, 45, 380, 0, 0, 300, 300);
		g.drawImage(Assets.buttona, 1040, 600, 0, 0, 100, 100);
		g.drawImage(Assets.buttonb, 1150, 500, 0, 0, 100, 100);
		g.drawImage(Assets.pause, 0, 0, 0, 0, 50, 66);

		// Display robot power
		g.drawString("Power: " + robot.getPower() + "%", 300, 50, paintInfo);
		// Display robot score
		g.drawString("S: " + robot.getStoreFood(), 750, 50, paintInfo);
		// Display robot lives
		g.drawString("L: " + robot.lives, 1100, 50, paintInfo);
		// Display game level
		// g.drawString("Stage: " + robot.level, 1100, 50, paintInfo);
	}

	private void drawPausedUI() {
		Graphics g = game.getGraphics();
		// Darken the entire screen so you can display the Paused screen.
		g.drawARGB(155, 0, 0, 0);
		g.drawString("Resume", 690, 165, paint2);
		g.drawString("Menu", 690, 360, paint2);
	}

	private void drawGameOverUI() {
		Graphics g = game.getGraphics();
		g.drawRect(0, 0, 1280, 720, Color.BLACK);
		g.drawString("GAME OVER.", 690, 360, paint2);
		g.drawString("Tap to return.", 690, 400, paint1);
	}

	@Override
	public void pause() {
		if (state == GameState.Running)
			state = GameState.Paused;
	}

	@Override
	public void resume() {
		if (state == GameState.Paused)
			state = GameState.Running;
	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {
		pause();
	}

	private void goToMenu() {
		game.setScreen(new MainMenuScreen(this.context, game));
	}

	public static Background getBg1() {
		// TODO Auto-generated method stub
		return bg1;
	}

	public static Background getBg2() {
		// TODO Auto-generated method stub
		return bg2;
	}

	public static Robot getRobot() {
		// TODO Auto-generated method stub
		return robot;
	}
}