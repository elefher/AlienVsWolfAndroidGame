package com.kilobolt.AlienVsWolf;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.Toast;

import com.db.framework.Player;
import com.db.framework.PlayersDataSource;
import com.generic.framework.implementation.AndroidEvents;
import com.generic.framework.implementation.AndroidPaintStrings;
import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.Input.TouchEvent;

public class FinishGameScreen extends Screen {

	Context context;
	private static Player player;
	private PlayersDataSource datasource;
	private AndroidEvents eventsClass;
	private AndroidPaintStrings paintClass;
	Paint paintInfo;

	public FinishGameScreen(Context context, Game game, Player player) {
		super(game);
		eventsClass = new AndroidEvents();
		paintClass = new AndroidPaintStrings();
		
		this.context = context;
		this.player = player;
		
		paintInfo = new Paint();
		paintInfo = paintClass.PaintStrings(Color.GREEN, 40, Paint.Align.CENTER);
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		Graphics g = game.getGraphics();

		// 1. All touch input is handled here:
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DOWN) {
				// save and continue the game
				if (inBounds(event, 610, 180, 280, 360)) {
					Player upPlayer = new Player();
					datasource = new PlayersDataSource(this.context);
					datasource.open();

					// increase the level game
					this.player.setLevel(this.player.getLevel() + 1);

					upPlayer = datasource.savePlayerData(this.player);
					datasource.close();
					//Object SampleGame;
					
					if (upPlayer == null) {
						Toast.makeText(this.context,
								"Sorry but something went wrong!!",
								Toast.LENGTH_LONG).show();
					} else {
						if(this.player.getLevel() == 11){
							game.setScreen(new MainMenuScreen(this.context, game));
						}else{
							Intent i1 = new Intent(this.context, NewLevelGame.class);
							i1.putExtra("player", upPlayer);
							i1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							this.context.startActivity(i1);
						}
					}
				}
			}
		}
	}

	// Implement the method inBounds of AndroidEvents class
	public boolean inBounds(TouchEvent event, int x, int y, int width,
			int height) {
		return eventsClass.inBounds(event, x, y, width, height);
	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawARGB(155, 0, 0, 0);
		g.drawImage(Assets.finishLevel, 0, 0);
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
		android.os.Process.killProcess(android.os.Process.myPid());

	}
}
