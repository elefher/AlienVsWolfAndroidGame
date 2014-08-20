package com.db.framework;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	// Set player columns
	public static final String TABLE_PLAYERS = "players";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_PLAYER = "player";
	public static final String COLUMN_FOOD = "food";
	public static final String COLUMN_LIVES = "lives";
	public static final String COLUMN_LEVEL = "level";
	public static final String COLUMN_ACTIVE = "active";

	private static final String DATABASE_NAME = "players.db";
	private static final int DATABASE_VERSION = 6;

	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_PLAYERS + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_PLAYER
			+ " text not null," + COLUMN_FOOD + " INTEGER DEFAULT 0, "
			+ COLUMN_LIVES + " INTEGER DEFAULT 1, " + COLUMN_LEVEL
			+ " INTEGER DEFAULT 1, " + COLUMN_ACTIVE + " text not null);";

	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MySQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYERS);
		onCreate(db);
	}

}