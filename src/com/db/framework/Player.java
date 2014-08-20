package com.db.framework;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Player implements Serializable{
	private long id;
	private String player;
	private int food;
	private int lives;
	private int level;
	private String active;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}
	
	public int getFood() {
		return food;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}
	
	public int getLives() {
		return lives;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}

	public void setFood(int food) {
		this.food = food;
	}
	
	public void setActive(String active) {
		this.active = active;
	}

	public String getActive() {
		return active;
	}

	// Will be used by the ArrayAdapter in the ListView
	@Override
	public String toString() {
		return player;
	}
}