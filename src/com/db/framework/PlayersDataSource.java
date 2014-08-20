package com.db.framework;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class PlayersDataSource {

	// Database fields
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
			MySQLiteHelper.COLUMN_PLAYER, MySQLiteHelper.COLUMN_FOOD,
			MySQLiteHelper.COLUMN_LIVES, MySQLiteHelper.COLUMN_LEVEL,
			MySQLiteHelper.COLUMN_ACTIVE };

	private static Cursor cursorSearch;
	private static Player updatedPlayer;

	public PlayersDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Player createPlayer(String player) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_PLAYER, player);
		values.put(MySQLiteHelper.COLUMN_FOOD, 0);
		values.put(MySQLiteHelper.COLUMN_LIVES, 1);
		values.put(MySQLiteHelper.COLUMN_LEVEL, 1);
		values.put(MySQLiteHelper.COLUMN_ACTIVE, "NO");
		
		long insertId = database.insert(MySQLiteHelper.TABLE_PLAYERS, null,
				values);
		Cursor cursor = database.query(MySQLiteHelper.TABLE_PLAYERS,
				allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		Player newPlayer = cursorToPlayer(cursor);
		cursor.close();
		return newPlayer;
	}

	public void deletePlayer(String player) {
		database.delete(MySQLiteHelper.TABLE_PLAYERS, MySQLiteHelper.COLUMN_PLAYER
				+ " = '" + player + "'", null);
	}

	public List<Player> getAllPlayers() {
		List<Player> players = new ArrayList<Player>();
		Player player = null;
		Cursor cursor = database.query(MySQLiteHelper.TABLE_PLAYERS,
				allColumns, null, null, null, null, MySQLiteHelper.COLUMN_ID
						+ " DESC");

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			player = cursorToPlayer(cursor);
			players.add(player);
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		return players;
	}

	private Player cursorToPlayer(Cursor cursor) {
		Player player = new Player();
		player.setId(cursor.getLong(0));
		player.setPlayer(cursor.getString(1));
		player.setFood(cursor.getInt(2));
		player.setLives(cursor.getInt(3));
		player.setLevel(cursor.getInt(4));
		player.setActive(cursor.getString(5));
		return player;
	}

	public int setPlayer(String username) {
		List<Player> players = getAllPlayers();
		int playersSize = players.size();

		ContentValues values = new ContentValues();

		values.put(MySQLiteHelper.COLUMN_ACTIVE, "NO");
		// this command update all this.players to Active "NO"
		database.update(MySQLiteHelper.TABLE_PLAYERS, values, null, null);
		// this command update specific this.player to Active "YES"
		values.put(MySQLiteHelper.COLUMN_ACTIVE, "YES");
		int check = database.update(MySQLiteHelper.TABLE_PLAYERS, values,
				MySQLiteHelper.COLUMN_PLAYER + "='" + username + "'", null);
		return check;
	}

	public Player getActivePlayer() {
		Cursor selectCursor = selectCursorWithColumn(MySQLiteHelper.COLUMN_ACTIVE);
		/*
		 * Cursor cursor = database.query(MySQLiteHelper.TABLE_PLAYERS,
		 * allColumns, MySQLiteHelper.COLUMN_ACTIVE + "='YES'", null, null,
		 * null, null);
		 */
		Player player = null;
		
		if(selectCursor.moveToFirst()){			
			player = cursorToPlayer(selectCursor);
			selectCursor.moveToNext(); 
		}	
		// make sure to close the cursor
		selectCursor.close();
		
		return player;
	}

	public Player savePlayerData(Player dataPlayer) {
		Player np = null;
		cursorSearch = selectCursorWithColumn(MySQLiteHelper.COLUMN_ACTIVE);

		cursorSearch.moveToFirst();
		np = cursorToPlayer(cursorSearch);
		cursorSearch.moveToNext();
		// make sure to close the cursor
		cursorSearch.close();
		
		if (np.getPlayer().equals(dataPlayer.getPlayer())) {
			ContentValues values = new ContentValues();
			values.put(MySQLiteHelper.COLUMN_FOOD,
					np.getFood() + dataPlayer.getFood());
			values.put(MySQLiteHelper.COLUMN_LIVES,
					dataPlayer.getLives());
			values.put(MySQLiteHelper.COLUMN_LEVEL,
					dataPlayer.getLevel());
			
			int check = database.update(
					MySQLiteHelper.TABLE_PLAYERS,
					values,
					MySQLiteHelper.COLUMN_PLAYER + "='"
							+ dataPlayer.getPlayer() + "'", null);

			if (check == 1) {
				cursorSearch = selectCursorWithColumn(MySQLiteHelper.COLUMN_ACTIVE);

				cursorSearch.moveToFirst();
				this.updatedPlayer = cursorToPlayer(cursorSearch);
				cursorSearch.moveToNext();

				// make sure to close the cursor
				cursorSearch.close();
			}
		}
		return this.updatedPlayer;
	}
	
	public Player saveGameOverPlayerData(Player gameOverPlayer){
		Player np = null;
		cursorSearch = selectCursorWithColumn(MySQLiteHelper.COLUMN_ACTIVE);

		cursorSearch.moveToFirst();
		np = cursorToPlayer(cursorSearch);
		cursorSearch.moveToNext();
		// make sure to close the cursor
		cursorSearch.close();
		
		if (np.getPlayer().equals(gameOverPlayer.getPlayer())) {
			ContentValues values = new ContentValues();
			int check = 0;
			if(gameOverPlayer.getLives() == 0){
				gameOverPlayer.setLives(1);
				gameOverPlayer.setLevel(1);
				gameOverPlayer.setFood(0);
				
				values.put(MySQLiteHelper.COLUMN_LIVES,
						gameOverPlayer.getLives());
				values.put(MySQLiteHelper.COLUMN_LEVEL,
						gameOverPlayer.getLevel());
				values.put(MySQLiteHelper.COLUMN_FOOD,
						gameOverPlayer.getFood());
			}else{
				values.put(MySQLiteHelper.COLUMN_LIVES,
						gameOverPlayer.getLives());
			}	
			check = database.update(
					MySQLiteHelper.TABLE_PLAYERS,
					values,
					MySQLiteHelper.COLUMN_PLAYER + "='"
							+ gameOverPlayer.getPlayer() + "'", null);

			if (check == 1) {
				cursorSearch = selectCursorWithColumn(MySQLiteHelper.COLUMN_ACTIVE);

				cursorSearch.moveToFirst();
				this.updatedPlayer = cursorToPlayer(cursorSearch);
				cursorSearch.moveToNext();

				// make sure to close the cursor
				cursorSearch.close();
			}
		}
		return this.updatedPlayer;
	}

	public Cursor selectCursorWithColumn(String columnDep) {
		Cursor cursor = database.query(MySQLiteHelper.TABLE_PLAYERS,
				allColumns, columnDep + "='YES'", null, null, null, null);
		return cursor;
	}
}