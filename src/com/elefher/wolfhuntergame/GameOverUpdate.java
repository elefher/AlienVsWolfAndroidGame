package com.elefher.wolfhuntergame;

import com.db.framework.Player;
import com.db.framework.PlayersDataSource;

import android.content.Context;

public class GameOverUpdate {
	
	private PlayersDataSource datasource;
	Context context;
	
	//private static Player player;
	
	public GameOverUpdate(Context context, Player player){
		this.context = context;
		//this.player = player;
		
		datasource = new PlayersDataSource(this.context);
		datasource.open();
		
		datasource.saveGameOverPlayerData(player);
		datasource.close();
	}
}
