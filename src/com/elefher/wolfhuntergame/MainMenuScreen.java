package com.elefher.wolfhuntergame;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.db.framework.Player;
import com.db.framework.PlayersDataSource;
import com.generic.framework.implementation.AndroidEvents;
import com.generic.framework.implementation.AndroidPaintStrings;
import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.Graphics.ImageFormat;
import com.kilobolt.framework.Input.TouchEvent;

public class MainMenuScreen extends Screen {

	Context context;
	Player player;
	private AndroidEvents eventsClass;
	private AndroidPaintStrings paintClass;
	Paint paintInfo, paintN;

	public MainMenuScreen(Context context, Game game) {
		super(game);
		
		// initialize menu image
		Graphics g = game.getGraphics();
		Assets.menu = g.newImage("menu.png", ImageFormat.RGB565);
		
		this.context = context;
		eventsClass = new AndroidEvents();
		paintClass = new AndroidPaintStrings();
		
		Typeface type = Typeface.createFromAsset(this.context.getAssets(),
				"fonts/vampire-games-3d.ttf");
		
		paintInfo = new Paint();		
		paintInfo = paintClass.PaintStrings(Color.BLACK, 60, Paint.Align.CENTER);
		paintInfo.setTypeface(type);
		
		paintN = new Paint();		
		paintN = paintClass.PaintStrings(Color.RED, 60, Paint.Align.CENTER);
		paintN.setTypeface(type);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();

		if (player == null) {
			getActivePlayer();
		}

		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (inBounds(event, 440, 530, 430, 100)) {
					// executes when touch "Play"  old cords 90, 550, 320, 120
					if(player == null){
						registrationActivity();
						return;
					}
					
					if (this.player.getLevel() == 1) {
						Intent i1 = new Intent(this.context, NewLevelGame.class);
						i1.putExtra("player", this.player);
						i1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						this.context.startActivity(i1);
						//game.setScreen(new GameScreen(this.context, game, this.player));
					} else if (this.player.getLevel() == 11){
						Intent i1 = new Intent(this.context, TheEnd.class);
						i1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						this.context.startActivity(i1);
					} else {
						Intent i1 = new Intent(this.context, NewLevelGame.class);
						i1.putExtra("player", this.player);
						i1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						this.context.startActivity(i1);
					}
				} else if (inBounds(event, 300, 200, 770, 70)) {
					// Starts the second activity in order to reg player old cords 90, 375, 410, 80
					registrationActivity();
				} else if (inBounds(event, 0, 0, 170, 60)) {
					Intent i1 = new Intent(this.context, Licences.class);
					i1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					this.context.startActivity(i1);					
				}/*else if (inBounds(event, 90, 220, 170, 80)) {
					// executes when touch "Load"
					System.out.println("touch load");
				} */
			}
		}
	}

	// Implement the method inBounds of AndroidEvents class
	public boolean inBounds(TouchEvent event, int x, int y, int width,
			int height) {
		return eventsClass.inBounds(event, x, y, width, height);
	}
	
	public void registrationActivity(){
		Intent mIntent = new Intent(this.context, CreatePlayer.class);
		mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		this.context.startActivity(mIntent);
		this.player = null;
	}

	public void getActivePlayer() {
		PlayersDataSource pds = new PlayersDataSource(this.context);
		pds.open();
		this.player = pds.getActivePlayer();
		pds.close();
	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.menu, 0, 0);

		// Display robot info if it is selected
		if(this.player != null){
			if(this.player.getLevel() == 11){
				g.drawString("The End", 620, 330, paintInfo);
				g.drawString("Create a new player", 630, 380, paintInfo);
				g.drawString("and start over again.", 630, 440, paintInfo);
			} else {
				g.drawString("Name: " + this.player.getPlayer(), 520, 330, paintInfo);
				g.drawString("Lives: " + this.player.getLives(), 520, 380, paintInfo);
				g.drawString("Food: " + this.player.getFood(), 520, 430, paintInfo);
				g.drawString("Level: " + this.player.getLevel(), 510, 480, paintInfo);
			}
		}else{
			g.drawString("You have to", 560, 330, paintInfo);
			g.drawString("create a player!", 570, 380, paintInfo);
			g.drawString("Just hit", 550, 440, paintInfo);
			g.drawString("CREATE PLAYER!", 560, 480, paintN);
		}
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
