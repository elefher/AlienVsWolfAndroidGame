package com.elefher.wolfhuntergame;

import java.util.ArrayList;

import com.elefher.wolfhuntergame.GameScreen.GameState;

import android.graphics.Rect;

public class Robot {

	// Constants are Here
	final int JUMPSPEED = -15;
	final int MOVESPEED = 9;

	public static int centerX;
	public static int centerY;
	public static String gunName;
	public static int damageRate;
	public static String stand = "f";
	public int power = 100;
	public int lives;
	public int level;
	public int storeFood;

	private boolean jumped = false;
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean ducked = false;
	private boolean up = false;
	private boolean upAForward = false;
	private boolean upAForwardBack = false;
	private boolean readyToFire = false;
	public boolean hurted = false;
	public boolean jumpeHit = false;

	private static int speedX = 0;
	private static int speedY = 0;
	private int jumpCounter = 0;
	public static Rect rectHead = new Rect(0, 0, 0, 0);
	public static Rect rectBody = new Rect(0, 0, 0, 0);
	public static Rect rectFoot = new Rect(0, 0, 0, 0);
	public static Rect rect = new Rect(0, 0, 0, 0);
	public static Rect rect2 = new Rect(0, 0, 0, 0);
	public static Rect rect3 = new Rect(0, 0, 0, 0);
	public static Rect rect4 = new Rect(0, 0, 0, 0);
	public static Rect yellowRed = new Rect(0, 0, 0, 0);

	public static Rect footleft = new Rect(0, 0, 0, 0);
	public static Rect footright = new Rect(0, 0, 0, 0);

	private Background bg1 = GameScreen.getBg1();
	private Background bg2 = GameScreen.getBg2();

	private ArrayList<Projectile> projectiles = null;
	
	Robot(){
		// initialize variables
		centerX = 230;
		centerY = 200;
		gunName = "nil";
		damageRate = 1;
		projectiles = new ArrayList<Projectile>();
	}

	public void update() {
		// Moves Character or Scrolls Background accordingly.

		if (speedX < 0) {
			centerX += speedX;
		}
		if (speedX == 0 || speedX < 0) {
			bg1.setSpeedX(0);
			bg2.setSpeedX(0);

		}
		if (centerX <= 400 && speedX > 0) {
			centerX += speedX;
		}
		
		if (speedX > 0 && centerX > 400 && GameScreen.state != GameState.Leader) {
			bg1.setSpeedX(-MOVESPEED / 4);
			bg2.setSpeedX(-MOVESPEED / 4);
		} else if (speedX > 0 && centerX < 900 && bg1.getBgX() <= -5100
				&& bg1.getBgX() >= -6000) {
			centerX += speedX;
		}

		// Updates Y Position
		centerY += speedY;
		//System.out.println(centerY);
		// Handles Jumping
		if(centerY == 617){
			jumpCounter = 0;
		}

		speedY += 1;

		if (speedY > 3) {
			jumped = true;
		}

		// Prevents going beyond X coordinate of 0
		if (centerX + speedX <= 60) {
			centerX = 61;
		}

		rect.set(centerX - 34, centerY - 63, centerX + 10/*31*/, centerY);
		rect2.set(rect.left, rect.top + 63, rect.left + /* 10 */68, rect.top + 128);
		rect3.set(rect.left - 26, rect.top + 32, rect.left, rect.top + 52);
		rect4.set(rect.left + 68, rect.top + 32, rect.left + 10/* 94 */,
				rect.top + 52);
		rectHead.set(centerX - 50, centerY - 70, centerX, centerY);
		rectBody.set(rect.left, rect.top + 63, rect.left + 10, rect.top + 128);
		rectFoot.set(rect.left - 26, rect.top + 32, rect.left, rect.top + 52);
		yellowRed.set(centerX - 110, centerY - 110, centerX + 70, centerY + 70);
		//yellowRed.set(centerX - 70, centerY - 55, 110, 145);
		footleft.set(centerX - 50, centerY + 20, centerX, centerY + 35);
		//footleft.set(centerX - 15, centerY + 40, 10, 35);
		footright.set(centerX, centerY + 20, centerX + 50, centerY + 35);
	}

	public void moveRight() {
		if (ducked == false) {
			speedX = MOVESPEED;
		}
	}

	public void moveLeft() {
		if (ducked == false) {
			speedX = -MOVESPEED;
		}
	}

	public void stopRight() {
		setMovingRight(false);
		stop();
	}

	public void stopLeft() {
		setMovingLeft(false);
		stop();
	}

	private void stop() {
		if (isMovingRight() == false && isMovingLeft() == false) {
			speedX = 0;
		}

		if (isMovingRight() == false && isMovingLeft() == true) {
			moveLeft();
		}

		if (isMovingRight() == true && isMovingLeft() == false) {
			moveRight();
		}

	}

	public void jump() {
		if (jumpCounter <= 1) {
			speedY = JUMPSPEED;
			jumpCounter++;
			jumped = true;
		} else {
			if (jumped == false) {
				jumpCounter = 0;
			}
		}
	}

	public void shoot(int x, int y, String direction) {
		Projectile p = new Projectile(centerX + x, centerY - y, this.damageRate, direction);
		projectiles.add(p);
	}

	public static int getCenterX() {
		return centerX;
	}

	public static int getCenterY() {
		return centerY;
	}

	public boolean isJumped() {
		return jumped;
	}

	public static int getSpeedX() {
		return speedX;
	}

	public static int getSpeedY() {
		return speedY;
	}

	public int getStoreFood() {
		return this.storeFood;
	}

	public void setStoreFood(int storeFood) {
		this.storeFood += storeFood;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setJumped(boolean jumped) {
		this.jumped = jumped;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public boolean isDucked() {
		return ducked;
	}
	
	public boolean isUp(){
		return up;
	}
	
	public void setUp(boolean up) {
		this.up = up;
	}
	
	public boolean isUpAForward(){
		return upAForward;
	}
	
	public boolean isUpAForwardBack(){
		return upAForwardBack;
	}
	
	public void setUpAForwardBack(boolean upAForwardBack){
		this.upAForwardBack = upAForwardBack;
	}
	
	public void setUpAForward(boolean upAForward){
		this.upAForward = upAForward;
	}

	public void setDucked(boolean ducked) {
		this.ducked = ducked;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public ArrayList getProjectiles() {
		return projectiles;
	}

	public boolean isReadyToFire() {
		return readyToFire;
	}

	public void setReadyToFire(boolean readyToFire) {
		this.readyToFire = readyToFire;
	}

	public int getPower() {
		return this.power;
	}
	
	/*
	 * This function used when the character hit on a tile with his head
	 */
	public void setPosAfterJumpHit(int tileY){
		speedY = Math.abs(tileY-(tileY-speedY) + JUMPSPEED);		
	}
}