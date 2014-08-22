package com.kilobolt.AlienVsWolf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.util.Log;

import com.db.framework.Player;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.implementation.AndroidGame;
import com.kilobolt.AlienVsWolf.R;

public class NewLevelGame extends AndroidGame {

	public static String map;
	boolean firstTimeCreate = true;
	public static int level;
	Player player;

	@Override
	public Screen getInitScreen() {
		this.player = (Player) getIntent().getExtras().getSerializable("player");
		
		if (firstTimeCreate) {
			Assets.load(this);
			firstTimeCreate = false;
		}
		
		this.level = this.player.getLevel();
		InputStream is = null;
		if(this.level == 1){
			 is = getResources().openRawResource(R.raw.map1);
		}else if(this.level == 2){
			 is = getResources().openRawResource(R.raw.map2);
		}else if(this.level == 3){
			 is = getResources().openRawResource(R.raw.map3);
		}else if(this.level == 4){
			 is = getResources().openRawResource(R.raw.map4);
		}else if(this.level == 5){
			 is = getResources().openRawResource(R.raw.map5);
		}else if(this.level == 6){
			 is = getResources().openRawResource(R.raw.map6);
		}else if(this.level == 7){
			 is = getResources().openRawResource(R.raw.map7);
		}else if(this.level == 8){
			 is = getResources().openRawResource(R.raw.map8);
		}else if(this.level == 9){
			 is = getResources().openRawResource(R.raw.map9);
		}else if(this.level == 10){
			 is = getResources().openRawResource(R.raw.map10);
		}
		
		map = convertStreamToString(is);
		
		return new NewLevelSplashLoadingScreen(this.getApplicationContext(),this, this.player);

	}

	@Override
	public void onBackPressed() {
		getCurrentScreen().backButton();
	}

	private static String convertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append((line + "\n"));
			}
		} catch (IOException e) {
			Log.w("LOG", e.getMessage());
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				Log.w("LOG", e.getMessage());
			}
		}
		return sb.toString();
	}

	@Override
	public void onResume() {
		super.onResume();

		Assets.theme.play();

	}

	@Override
	public void onPause() {
		super.onPause();
		Assets.theme.pause();

	}
}