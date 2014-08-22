package com.kilobolt.AlienVsWolf;

import android.content.Context;

import com.db.framework.Player;
import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.Graphics.ImageFormat;

public class NewLevelSplashLoadingScreen extends Screen {
	Context context;
	Player player;
	
	public NewLevelSplashLoadingScreen(Context context, Game game, Player player) {
		super(game);
		this.context = context;
		this.player = player;
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		//Assets.splash = g.newImage("splash.jpg", ImageFormat.RGB565);
		Assets.splash = g.newImage("splash.png", ImageFormat.RGB565);

		game.setScreen(new NewLevelLoadingScreen(this.context, game, this.player));
	}

	@Override
	public void paint(float deltaTime) {

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