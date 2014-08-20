package com.elefher.wolfhuntergame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Graphics.ImageFormat;
import com.kilobolt.framework.Screen;

public class LoadingScreen extends Screen {
	Context context;

	public LoadingScreen(Context context, Game game) {
		super(game);
		this.context = context;
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		
		//Assets.menu = g.newImage("menu.png", ImageFormat.RGB565);
		Assets.background = g.newImage("level1/background.png", ImageFormat.RGB565);
		Assets.character = g.newImage("character.png", ImageFormat.ARGB4444);
		Assets.character2 = g.newImage("character2.png", ImageFormat.ARGB4444);
		Assets.character3 = g.newImage("character3.png", ImageFormat.ARGB4444);
		Assets.character4 = g.newImage("character4.png", ImageFormat.ARGB4444);
		Assets.character5 = g.newImage("character5.png", ImageFormat.ARGB4444);
		Assets.character6 = g.newImage("character6.png", ImageFormat.ARGB4444);
		Assets.character7 = g.newImage("character7.png", ImageFormat.ARGB4444);
		Assets.character8 = g.newImage("character8.png", ImageFormat.ARGB4444);
		Assets.character9 = g.newImage("character9.png", ImageFormat.ARGB4444);
		Assets.character10 = g.newImage("character10.png", ImageFormat.ARGB4444);
		Assets.character11 = g.newImage("character11.png", ImageFormat.ARGB4444);
		Assets.character12 = g.newImage("character12.png", ImageFormat.ARGB4444);
		Assets.character13 = g.newImage("character13.png", ImageFormat.ARGB4444);
		Assets.character14 = g.newImage("character14.png", ImageFormat.ARGB4444);
		Assets.character15 = g.newImage("character15.png", ImageFormat.ARGB4444);
		Assets.character16 = g.newImage("character16.png", ImageFormat.ARGB4444);
		Assets.character17 = g.newImage("character17.png", ImageFormat.ARGB4444);
		Assets.character18 = g.newImage("character18.png", ImageFormat.ARGB4444);
		Assets.character19 = g.newImage("character19.png", ImageFormat.ARGB4444);
		Assets.character20 = g.newImage("character20.png", ImageFormat.ARGB4444);
		Assets.character21 = g.newImage("character21.png", ImageFormat.ARGB4444);
		
		Assets.characterJump = g.newImage("jumped.png", ImageFormat.ARGB4444);
		Assets.characterJump2 = g.newImage("jumped2.png", ImageFormat.ARGB4444);
		Assets.characterJump3 = g.newImage("jumped3.png", ImageFormat.ARGB4444);
		Assets.characterJump4 = g.newImage("jumped4.png", ImageFormat.ARGB4444);
		Assets.characterJump5 = g.newImage("jumped5.png", ImageFormat.ARGB4444);
		Assets.characterJump6 = g.newImage("jumped6.png", ImageFormat.ARGB4444);
		Assets.characterJump7 = g.newImage("jumped7.png", ImageFormat.ARGB4444);
		Assets.characterJump8 = g.newImage("jumped8.png", ImageFormat.ARGB4444);
		Assets.characterJump9 = g.newImage("jumped9.png", ImageFormat.ARGB4444);
		Assets.characterJump10 = g.newImage("jumped10.png", ImageFormat.ARGB4444);
		Assets.characterJump11 = g.newImage("jumped11.png", ImageFormat.ARGB4444);
		Assets.characterJump12 = g.newImage("jumped12.png", ImageFormat.ARGB4444);
		Assets.characterJump13 = g.newImage("jumped13.png", ImageFormat.ARGB4444);
		Assets.characterJump14 = g.newImage("jumped14.png", ImageFormat.ARGB4444);
		Assets.characterJump15 = g.newImage("jumped15.png", ImageFormat.ARGB4444);
	
		Assets.characterDown = g.newImage("down.png", ImageFormat.ARGB4444);
		
		Assets.characterrun1 = g.newImage("characterrun1.png", ImageFormat.ARGB4444);
		Assets.characterrun2 = g.newImage("characterrun2.png", ImageFormat.ARGB4444);
		Assets.characterrun3 = g.newImage("characterrun3.png", ImageFormat.ARGB4444);
		Assets.characterrun4 = g.newImage("characterrun4.png", ImageFormat.ARGB4444);
		Assets.characterrun5 = g.newImage("characterrun5.png", ImageFormat.ARGB4444);
		Assets.characterrun6 = g.newImage("characterrun6.png", ImageFormat.ARGB4444);
		Assets.characterrun7 = g.newImage("characterrun7.png", ImageFormat.ARGB4444);
		Assets.characterrun8 = g.newImage("characterrun8.png", ImageFormat.ARGB4444);
		Assets.characterrun9 = g.newImage("characterrun9.png", ImageFormat.ARGB4444);
		Assets.characterrun10 = g.newImage("characterrun10.png", ImageFormat.ARGB4444);
		Assets.characterrun11 = g.newImage("characterrun11.png", ImageFormat.ARGB4444);
		Assets.characterrun12 = g.newImage("characterrun12.png", ImageFormat.ARGB4444);
		Assets.characterrun13 = g.newImage("characterrun13.png", ImageFormat.ARGB4444);
		Assets.characterrun14 = g.newImage("characterrun14.png", ImageFormat.ARGB4444);
		Assets.characterrun15 = g.newImage("characterrun15.png", ImageFormat.ARGB4444);
		Assets.characterrun16 = g.newImage("characterrun16.png", ImageFormat.ARGB4444);
		Assets.characterrun17 = g.newImage("characterrun17.png", ImageFormat.ARGB4444);

		Assets.heliboy = g.newImage("level1/heliboy.png", ImageFormat.ARGB4444);
		Assets.heliboy2 = g.newImage("level1/heliboy2.png", ImageFormat.ARGB4444);
		Assets.heliboy3 = g.newImage("level1/heliboy3.png", ImageFormat.ARGB4444);
		Assets.heliboy4 = g.newImage("level1/heliboy4.png", ImageFormat.ARGB4444);
		Assets.heliboy5 = g.newImage("level1/heliboy5.png", ImageFormat.ARGB4444);

		//Assets.lastEnemy1 = g.newImage("level1/biglastenemy1.png", ImageFormat.ARGB4444);
		
		Assets.tiledirt = g.newImage("level1/tiledirt.png", ImageFormat.RGB565);
		Assets.tilegrassTop = g.newImage("level1/tilegrasstop.png", ImageFormat.RGB565);
		Assets.tilegrassBot = g.newImage("level1/tilegrassbot.png", ImageFormat.RGB565);
		Assets.tilegrassLeft = g.newImage("level1/tilegrassleft.png",ImageFormat.RGB565);
		Assets.tilegrassRight = g.newImage("level1/tilegrassright.png",ImageFormat.RGB565);
		//Assets.ring = g.newImage("ring.png",ImageFormat.RGB565);
		Assets.finish = g.newImage("level1/finish.png",ImageFormat.RGB565);
		Assets.finishLevel = g.newImage("level1/finishlevel.png", ImageFormat.RGB565);
		Assets.life = g.newImage("life.png", ImageFormat.RGB565);
		Assets.gun1 = g.newImage("gun1.png", ImageFormat.RGB565);
		Assets.nil = g.newImage("null.png", ImageFormat.RGB565);

		Assets.joystick = g.newImage("joystick.png", ImageFormat.RGB565);
		Assets.joystickright = g.newImage("joysticright.png", ImageFormat.RGB565);
		Assets.joystickleft = g.newImage("joystickleft.png", ImageFormat.RGB565);
		Assets.joystickup = g.newImage("joystickup.png", ImageFormat.RGB565);
		Assets.joystickdown = g.newImage("joystickdown.png", ImageFormat.RGB565);
		Assets.joystickupright = g.newImage("joystickupright.png", ImageFormat.RGB565);
		Assets.joystickupleft = g.newImage("joystickupleft.png", ImageFormat.RGB565);
		Assets.buttona = g.newImage("buttona.png", ImageFormat.RGB565);
		Assets.buttonb = g.newImage("buttonb.png", ImageFormat.RGB565);
		// This is how you would load a sound if you had one.
		// Assets.click = game.getAudio().createSound("explode.ogg");

		game.setScreen(new MainMenuScreen(this.context, game));
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