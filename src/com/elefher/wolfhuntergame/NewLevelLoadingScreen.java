package com.elefher.wolfhuntergame;

import java.util.ArrayList;

import android.content.Context;

import com.db.framework.Player;
import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Image;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.Graphics.ImageFormat;

public class NewLevelLoadingScreen extends Screen {
	Context context;
	Player player;
	private static boolean AssetsLoaded = false;

	public NewLevelLoadingScreen(Context context, Game game, Player player) {
		super(game);
		this.context = context;
		this.player = player;
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();

		// load standard assets
		if (!AssetsLoaded) {
			Assets.characterbackList = new ArrayList<Image>(21);
			for (int i = 0; i < 21; i++) {
				Assets.characterbackList.add(g.newImage("character" + (i + 1)
						+ "back.png", ImageFormat.ARGB4444));
			}

			Assets.characterrunbackList = new ArrayList<Image>(17);
			for (int i = 0; i < 17; i++) {
				Assets.characterrunbackList.add(g.newImage("characterrun"
						+ (i + 1) + "back.png", ImageFormat.ARGB4444));
			}

			Assets.characterjumpbackList = new ArrayList<Image>(15);
			for (int i = 0; i < 15; i++) {
				Assets.characterjumpbackList.add(g.newImage("jumped" + (i + 1)
						+ "back.png", ImageFormat.ARGB4444));
			}

			Assets.characterhurtbackList = new ArrayList<Image>(5);
			for (int i = 0; i < 5; i++) {
				Assets.characterhurtbackList.add(g.newImage("characterhurt"
						+ (i + 1) + "back.png", ImageFormat.ARGB4444));
			}

			Assets.rocket = new ArrayList<Image>(24);
			for (int i = 0; i < 24; i++) {
				Assets.rocket.add(g.newImage("rocket" + (i + 1) + ".png",
						ImageFormat.ARGB4444));
			}

			Assets.rocketb = new ArrayList<Image>(4);
			for (int i = 0; i < 4; i++) {
				Assets.rocketb.add(g.newImage("rocketb" + (i + 1) + ".png",
						ImageFormat.ARGB4444));
			}

			Assets.brokenRocket1 = new ArrayList<Image>(4);
			for (int i = 0; i < 4; i++) {
				Assets.brokenRocket1.add(g.newImage("brokenrocket1" + (i + 1)
						+ ".png", ImageFormat.ARGB4444));
			}

			Assets.brokenRocket2 = new ArrayList<Image>(4);
			for (int i = 0; i < 4; i++) {
				Assets.brokenRocket2.add(g.newImage("brokenrocket2" + (i + 1)
						+ ".png", ImageFormat.ARGB4444));
			}

			Assets.brokenRocket3 = new ArrayList<Image>(11);
			for (int i = 0; i < 11; i++) {
				Assets.brokenRocket3.add(g.newImage("brokenrocket3" + (i + 1)
						+ ".png", ImageFormat.ARGB4444));
			}

			Assets.skeletonrun = new ArrayList<Image>(11);
			for (int i = 0; i < 11; i++) {
				Assets.skeletonrun.add(g.newImage("skeletonrun" + (i + 1)
						+ ".png", ImageFormat.ARGB4444));
			}

			Assets.explosionD = new ArrayList<Image>(6);
			for (int i = 0; i < 6; i++) {
				Assets.explosionD.add(g.newImage("explosiond" + (i + 1)
						+ ".png", ImageFormat.ARGB4444));
			}

			Assets.explosionC = new ArrayList<Image>(7);
			for (int i = 0; i < 7; i++) {
				Assets.explosionC.add(g.newImage("explosionc" + (i + 1)
						+ ".png", ImageFormat.ARGB4444));
			}

			Assets.explosionB = new ArrayList<Image>(16);
			for (int i = 0; i < 16; i++) {
				Assets.explosionB.add(g.newImage("explosionb" + (i + 1)
						+ ".png", ImageFormat.ARGB4444));
			}

			Assets.nilA = new ArrayList<Image>(7);
			for (int i = 0; i < 7; i++) {
				Assets.nilA.add(g.newImage("null" + (i + 1) + ".png",
						ImageFormat.ARGB4444));
			}

			Assets.gun = new ArrayList<Image>(4);
			for (int i = 0; i < 4; i++) {
				Assets.gun.add(g.newImage("gun" + (i + 1) + ".png",
						ImageFormat.ARGB4444));
			}
			
			Assets.supergunbullet = new ArrayList<Image>(1);
			for (int i = 0; i < 1; i++) {
				Assets.supergunbullet.add(g.newImage("supergunbullet" + (i + 1) + ".png",
						ImageFormat.ARGB4444));
			}

			Assets.fireball = new ArrayList<Image>(6);
			for (int i = 0; i < 6; i++) {
				Assets.fireball.add(g.newImage("fireball" + (i + 1) + ".png",
						ImageFormat.ARGB4444));
			}

			Assets.lightball = new ArrayList<Image>(6);
			for (int i = 0; i < 6; i++) {
				Assets.lightball.add(g.newImage("lightball" + (i + 1) + ".png",
						ImageFormat.ARGB4444));
			}

			Assets.ring = new ArrayList<Image>(7);
			for (int i = 0; i < 7; i++) {
				Assets.ring.add(g.newImage("ring" + (i + 1) + ".png",
						ImageFormat.ARGB4444));
			}
			
			Assets.target = g.newImage("target.png", ImageFormat.ARGB4444);

			Assets.characterfu = g.newImage("characterfu.png",
					ImageFormat.ARGB4444);
			Assets.characterfuback = g.newImage("characterfuback.png",
					ImageFormat.ARGB4444);
			Assets.characterup = g.newImage("characterup.png",
					ImageFormat.ARGB4444);
			Assets.characterupback = g.newImage("characterupback.png",
					ImageFormat.ARGB4444);
			Assets.character = g
					.newImage("character.png", ImageFormat.ARGB4444);
			Assets.character2 = g.newImage("character2.png",
					ImageFormat.ARGB4444);
			Assets.character3 = g.newImage("character3.png",
					ImageFormat.ARGB4444);
			Assets.character4 = g.newImage("character4.png",
					ImageFormat.ARGB4444);
			Assets.character5 = g.newImage("character5.png",
					ImageFormat.ARGB4444);
			Assets.character6 = g.newImage("character6.png",
					ImageFormat.ARGB4444);
			Assets.character7 = g.newImage("character7.png",
					ImageFormat.ARGB4444);
			Assets.character8 = g.newImage("character8.png",
					ImageFormat.ARGB4444);
			Assets.character9 = g.newImage("character9.png",
					ImageFormat.ARGB4444);
			Assets.character10 = g.newImage("character10.png",
					ImageFormat.ARGB4444);
			Assets.character11 = g.newImage("character11.png",
					ImageFormat.ARGB4444);
			Assets.character12 = g.newImage("character12.png",
					ImageFormat.ARGB4444);
			Assets.character13 = g.newImage("character13.png",
					ImageFormat.ARGB4444);
			Assets.character14 = g.newImage("character14.png",
					ImageFormat.ARGB4444);
			Assets.character15 = g.newImage("character15.png",
					ImageFormat.ARGB4444);
			Assets.character16 = g.newImage("character16.png",
					ImageFormat.ARGB4444);
			Assets.character17 = g.newImage("character17.png",
					ImageFormat.ARGB4444);
			Assets.character18 = g.newImage("character18.png",
					ImageFormat.ARGB4444);
			Assets.character19 = g.newImage("character19.png",
					ImageFormat.ARGB4444);
			Assets.character20 = g.newImage("character20.png",
					ImageFormat.ARGB4444);
			Assets.character21 = g.newImage("character21.png",
					ImageFormat.ARGB4444);

			Assets.characterJump = g.newImage("jumped.png",
					ImageFormat.ARGB4444);
			Assets.characterJump2 = g.newImage("jumped2.png",
					ImageFormat.ARGB4444);
			Assets.characterJump3 = g.newImage("jumped3.png",
					ImageFormat.ARGB4444);
			Assets.characterJump4 = g.newImage("jumped4.png",
					ImageFormat.ARGB4444);
			Assets.characterJump5 = g.newImage("jumped5.png",
					ImageFormat.ARGB4444);
			Assets.characterJump6 = g.newImage("jumped6.png",
					ImageFormat.ARGB4444);
			Assets.characterJump7 = g.newImage("jumped7.png",
					ImageFormat.ARGB4444);
			Assets.characterJump8 = g.newImage("jumped8.png",
					ImageFormat.ARGB4444);
			Assets.characterJump9 = g.newImage("jumped9.png",
					ImageFormat.ARGB4444);
			Assets.characterJump10 = g.newImage("jumped10.png",
					ImageFormat.ARGB4444);
			Assets.characterJump11 = g.newImage("jumped11.png",
					ImageFormat.ARGB4444);
			Assets.characterJump12 = g.newImage("jumped12.png",
					ImageFormat.ARGB4444);
			Assets.characterJump13 = g.newImage("jumped13.png",
					ImageFormat.ARGB4444);
			Assets.characterJump14 = g.newImage("jumped14.png",
					ImageFormat.ARGB4444);
			Assets.characterJump15 = g.newImage("jumped15.png",
					ImageFormat.ARGB4444);

			Assets.characterDown = g.newImage("down.png", ImageFormat.ARGB4444);
			Assets.characterDownBack = g.newImage("downback.png",
					ImageFormat.ARGB4444);

			Assets.characterrun1 = g.newImage("characterrun1.png",
					ImageFormat.ARGB4444);
			Assets.characterrun2 = g.newImage("characterrun2.png",
					ImageFormat.ARGB4444);
			Assets.characterrun3 = g.newImage("characterrun3.png",
					ImageFormat.ARGB4444);
			Assets.characterrun4 = g.newImage("characterrun4.png",
					ImageFormat.ARGB4444);
			Assets.characterrun5 = g.newImage("characterrun5.png",
					ImageFormat.ARGB4444);
			Assets.characterrun6 = g.newImage("characterrun6.png",
					ImageFormat.ARGB4444);
			Assets.characterrun7 = g.newImage("characterrun7.png",
					ImageFormat.ARGB4444);
			Assets.characterrun8 = g.newImage("characterrun8.png",
					ImageFormat.ARGB4444);
			Assets.characterrun9 = g.newImage("characterrun9.png",
					ImageFormat.ARGB4444);
			Assets.characterrun10 = g.newImage("characterrun10.png",
					ImageFormat.ARGB4444);
			Assets.characterrun11 = g.newImage("characterrun11.png",
					ImageFormat.ARGB4444);
			Assets.characterrun12 = g.newImage("characterrun12.png",
					ImageFormat.ARGB4444);
			Assets.characterrun13 = g.newImage("characterrun13.png",
					ImageFormat.ARGB4444);
			Assets.characterrun14 = g.newImage("characterrun14.png",
					ImageFormat.ARGB4444);
			Assets.characterrun15 = g.newImage("characterrun15.png",
					ImageFormat.ARGB4444);
			Assets.characterrun16 = g.newImage("characterrun16.png",
					ImageFormat.ARGB4444);
			Assets.characterrun17 = g.newImage("characterrun17.png",
					ImageFormat.ARGB4444);
			Assets.characterhurt1 = g.newImage("characterhurt1.png",
					ImageFormat.ARGB4444);
			Assets.characterhurt2 = g.newImage("characterhurt2.png",
					ImageFormat.ARGB4444);
			Assets.characterhurt3 = g.newImage("characterhurt3.png",
					ImageFormat.ARGB4444);
			Assets.characterhurt4 = g.newImage("characterhurt4.png",
					ImageFormat.ARGB4444);
			Assets.characterhurt5 = g.newImage("characterhurt5.png",
					ImageFormat.ARGB4444);

			Assets.life = g.newImage("life.png", ImageFormat.RGB565);

			Assets.pause = g.newImage("pause.png", ImageFormat.RGB565);
			Assets.joystick = g.newImage("joystick.png", ImageFormat.RGB565);
			Assets.joystickright = g.newImage("joystickright.png",
					ImageFormat.RGB565);
			Assets.joystickleft = g.newImage("joystickleft.png",
					ImageFormat.RGB565);
			Assets.joystickup = g
					.newImage("joystickup.png", ImageFormat.RGB565);
			Assets.joystickdown = g.newImage("joystickdown.png",
					ImageFormat.RGB565);
			Assets.joystickupright = g.newImage("joystickupright.png",
					ImageFormat.RGB565);
			Assets.joystickupleft = g.newImage("joystickupleft.png",
					ImageFormat.RGB565);
			Assets.buttona = g.newImage("buttona.png", ImageFormat.RGB565);
			Assets.buttonb = g.newImage("buttonb.png", ImageFormat.RGB565);

			Assets.explosion1 = g
					.newImage("explosion1.png", ImageFormat.RGB565);
			Assets.explosion2 = g
					.newImage("explosion2.png", ImageFormat.RGB565);
			Assets.explosion3 = g
					.newImage("explosion3.png", ImageFormat.RGB565);
			Assets.explosion4 = g
					.newImage("explosion4.png", ImageFormat.RGB565);
			Assets.explosion5 = g
					.newImage("explosion5.png", ImageFormat.RGB565);

			Assets.finishLevel = g.newImage("finishlevel.png",
					ImageFormat.RGB565);

			// sounds
			Assets.nilgun = game.getAudio().createSound("bulletnilgun.mp3");
			// Assets.nilgun = game.getAudio().createSound("bulletnilgun.wav");
			Assets.jump = game.getAudio().createSound("jump.mp3");

			AssetsLoaded = true;
		}

		// load assets for level 1
		if (this.player.getLevel() == 1) {
			Assets.firepunch2 = new ArrayList<Image>(5);
			for (int i = 0; i < 5; i++) {
				Assets.firepunch2.add(g.newImage(
						"level" + this.player.getLevel() + "/firepunch"
								+ (i + 1) + ".png", ImageFormat.ARGB4444));
			}

			Assets.biglastenemy1 = new ArrayList<Image>(5);
			for (int i = 0; i < 5; i++) {
				Assets.biglastenemy1.add(g.newImage(
						"level" + this.player.getLevel() + "/biglastenemy"
								+ (i + 1) + ".png", ImageFormat.ARGB4444));
			}

			/*
			 * Assets.tilestand1 = g.newImage("level" + this.player.getLevel() +
			 * "/tilesnow1.png", ImageFormat.RGB565); Assets.tilestand2 =
			 * g.newImage("level" + this.player.getLevel() + "/tilesnow2.png",
			 * ImageFormat.RGB565); Assets.tilestand3 = g.newImage("level" +
			 * this.player.getLevel() + "/tilesnow3.png", ImageFormat.RGB565);
			 * Assets.tilestand4 = g.newImage("level" + this.player.getLevel() +
			 * "/tilesnow4.png", ImageFormat.RGB565);
			 * 
			 * Assets.tileice1 = g.newImage("level" + this.player.getLevel() +
			 * "/tileice1.png", ImageFormat.RGB565); Assets.tileice2 =
			 * g.newImage("level" + this.player.getLevel() + "/tileice2.png",
			 * ImageFormat.RGB565); Assets.tileice3 = g.newImage("level" +
			 * this.player.getLevel() + "/tileice3.png", ImageFormat.RGB565);
			 * Assets.tileLeft = g.newImage("level" + this.player.getLevel() +
			 * "/tiledirtleft.png", ImageFormat.RGB565); Assets.tileRight =
			 * g.newImage("level" + this.player.getLevel() +
			 * "/tiledirtright.png", ImageFormat.RGB565);
			 */
		}

		// Load big enemy assets for level 2, 3 and 4
		if (this.player.getLevel() == 2 || this.player.getLevel() == 3
				|| this.player.getLevel() == 4) {
			Assets.biglastenemy2 = new ArrayList<Image>(4);
			for (int i = 0; i < 4; i++) {
				Assets.biglastenemy2.add(g.newImage(
						"level" + this.player.getLevel() + "/biglastenemy"
								+ (i + 1) + ".png", ImageFormat.ARGB4444));
			}

			Assets.biglastenemyrun2 = new ArrayList<Image>(4);
			for (int i = 0; i < 4; i++) {
				Assets.biglastenemyrun2.add(g.newImage(
						"level" + this.player.getLevel() + "/biglastenemyrun"
								+ (i + 1) + ".png", ImageFormat.ARGB4444));
			}

			Assets.biglastenemyjump2 = new ArrayList<Image>(6);
			for (int i = 0; i < 6; i++) {
				Assets.biglastenemyjump2.add(g.newImage(
						"level" + this.player.getLevel() + "/biglastenemyjump"
								+ (i + 1) + ".png", ImageFormat.ARGB4444));
			}

			Assets.biglastenemykick2 = new ArrayList<Image>(4);
			for (int i = 0; i < 4; i++) {
				Assets.biglastenemykick2.add(g.newImage(
						"level" + this.player.getLevel() + "/biglastenemykick"
								+ (i + 1) + ".png", ImageFormat.ARGB4444));
			}

			Assets.biglastenemyshoot2 = new ArrayList<Image>(6);
			for (int i = 0; i < 6; i++) {
				Assets.biglastenemyshoot2.add(g.newImage(
						"level" + this.player.getLevel() + "/biglastenemyshoot"
								+ (i + 1) + ".png", ImageFormat.ARGB4444));
			}

			Assets.biglastenemyflip2 = new ArrayList<Image>(6);
			for (int i = 0; i < 6; i++) {
				Assets.biglastenemyflip2.add(g.newImage(
						"level" + this.player.getLevel() + "/biglastenemyflip"
								+ (i + 1) + ".png", ImageFormat.ARGB4444));
			}

			Assets.firepunch2 = new ArrayList<Image>(5);
			for (int i = 0; i < 5; i++) {
				Assets.firepunch2.add(g.newImage(
						"level" + this.player.getLevel() + "/firepunch"
								+ (i + 1) + ".png", ImageFormat.ARGB4444));
			}
		}

		// Load big enemy assets for level 5, 6, 7, 8 and 9
		if (this.player.getLevel() == 5 || this.player.getLevel() == 6
				|| this.player.getLevel() == 7 || this.player.getLevel() == 8
				|| this.player.getLevel() == 9) {
			Assets.tankbomb = new ArrayList<Image>(1);
			for (int i = 0; i < 1; i++) {
				Assets.tankbomb
						.add(g.newImage("level" + this.player.getLevel()
								+ "/tankbomb" + (i + 1) + ".png",
								ImageFormat.ARGB4444));
			}

			Assets.tankwolf = new ArrayList<Image>(8);
			for (int i = 0; i < 8; i++) {
				Assets.tankwolf
						.add(g.newImage("level" + this.player.getLevel()
								+ "/tankwolf" + (i + 1) + ".png",
								ImageFormat.ARGB4444));
			}
		}

		// Load big enemy assets for level 10
		if (this.player.getLevel() == 10) {
			Assets.tankbomb = new ArrayList<Image>(1);
			for (int i = 0; i < 1; i++) {
				Assets.tankbomb
						.add(g.newImage("level" + this.player.getLevel()
								+ "/tankbomb" + (i + 1) + ".png",
								ImageFormat.ARGB4444));
			}
			Assets.flywolf = new ArrayList<Image>(1);
			for (int i = 0; i < 1; i++) {
				Assets.flywolf
						.add(g.newImage("level" + this.player.getLevel()
								+ "/flywolf" + (i + 1) + ".png",
								ImageFormat.ARGB4444));
			}
		}

		// load different assets every time
		Assets.background = null;
		Assets.background = g.newImage("level" + this.player.getLevel()
				+ "/background.png", ImageFormat.RGB565);

		Assets.heliboy = g.newImage("level" + this.player.getLevel()
				+ "/heliboy.png", ImageFormat.ARGB4444);
		Assets.heliboy2 = g.newImage("level" + this.player.getLevel()
				+ "/heliboy2.png", ImageFormat.ARGB4444);
		Assets.heliboy3 = g.newImage("level" + this.player.getLevel()
				+ "/heliboy3.png", ImageFormat.ARGB4444);
		Assets.heliboy4 = g.newImage("level" + this.player.getLevel()
				+ "/heliboy4.png", ImageFormat.ARGB4444);
		Assets.heliboy5 = g.newImage("level" + this.player.getLevel()
				+ "/heliboy5.png", ImageFormat.ARGB4444);

		Assets.tilestand1 = g.newImage("level" + this.player.getLevel()
				+ "/tilesnow1.png", ImageFormat.RGB565);
		Assets.tilestand2 = g.newImage("level" + this.player.getLevel()
				+ "/tilesnow2.png", ImageFormat.RGB565);
		Assets.tilestand3 = g.newImage("level" + this.player.getLevel()
				+ "/tilesnow3.png", ImageFormat.RGB565);
		Assets.tilestand4 = g.newImage("level" + this.player.getLevel()
				+ "/tilesnow4.png", ImageFormat.RGB565);

		Assets.tileice1 = g.newImage("level" + this.player.getLevel()
				+ "/tileice1.png", ImageFormat.RGB565);
		Assets.tileice2 = g.newImage("level" + this.player.getLevel()
				+ "/tileice2.png", ImageFormat.RGB565);
		Assets.tileice3 = g.newImage("level" + this.player.getLevel()
				+ "/tileice3.png", ImageFormat.RGB565);
		Assets.tileLeft = g.newImage("level" + this.player.getLevel()
				+ "/tiledirtleft.png", ImageFormat.RGB565);
		Assets.tileRight = g.newImage("level" + this.player.getLevel()
				+ "/tiledirtright.png", ImageFormat.RGB565);

		/*
		 * Assets.tiledirt = g.newImage("level" + this.player.getLevel() +
		 * "/tiledirt.png", ImageFormat.RGB565); Assets.tilegrassTop =
		 * g.newImage("level" + this.player.getLevel() + "/tilegrasstop.png",
		 * ImageFormat.RGB565); Assets.tilegrassBot = g.newImage("level" +
		 * this.player.getLevel() + "/tilegrassbot.png", ImageFormat.RGB565);
		 * Assets.tilegrassLeft = g.newImage("level" + this.player.getLevel() +
		 * "/tilegrassleft.png", ImageFormat.RGB565); Assets.tilegrassRight =
		 * g.newImage("level" + this.player.getLevel() + "/tilegrassright.png",
		 * ImageFormat.RGB565);
		 */

		// This is how you would load a sound if you had one.
		// Assets.click = game.getAudio().createSound("explode.ogg");

		game.setScreen(new GameScreen(this.context, game, this.player));
	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.splash, 0, 0);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {

	}
}