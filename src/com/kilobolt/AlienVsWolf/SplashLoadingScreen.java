package com.kilobolt.AlienVsWolf;

import android.content.Context;

import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.Graphics.ImageFormat;

public class SplashLoadingScreen extends Screen {
	Context context;

	public SplashLoadingScreen(Context context, Game game) {
		super(game);
		this.context = context;
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		//Assets.splash = g.newImage("splash.jpg", ImageFormat.RGB565);
		Assets.splash = g.newImage("splash.png", ImageFormat.RGB565);

		//game.setScreen(new LoadingScreen(this.context, game));
		game.setScreen(new MainMenuScreen(this.context, game));
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