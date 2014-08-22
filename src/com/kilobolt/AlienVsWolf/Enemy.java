package com.kilobolt.AlienVsWolf;

import java.util.ArrayList;

import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Image;

import android.graphics.Rect;

public class Enemy {

	public int power, centerX, speedX, centerY;
	private Background bg = GameScreen.getBg1();
	private Robot robot = GameScreen.getRobot();

	public Rect r = new Rect(0, 0, 0, 0);
	public int health = 5;
	public boolean hurted = false;

	private int movementSpeed;
	private boolean readyToFire = true;
	public Image explosion1 = null;
	public Animation exanim1 = null;

	public ArrayList<ProjecttileEnemy> projectilesEn = new ArrayList<ProjecttileEnemy>();
	Enemy(){
		explosion1 = Assets.explosion1;
		exanim1 = new Animation();
		exanim1.addFrame(explosion1, 200);
	}

	// Behavioral Methods
	public void update() {
		follow();
		centerX += speedX;
		speedX = bg.getSpeedX() * 5 + movementSpeed;
		r.set(centerX - 25, centerY - 25, centerX + 25, centerY + 35);
		
		if (Rect.intersects(r, Robot.yellowRed)) {
			checkCollision();
		}
	}

	private void checkCollision() {
		if (Rect.intersects(r, Robot.rect) || Rect.intersects(r, Robot.rect2)
				|| Rect.intersects(r, Robot.rect3)
				|| Rect.intersects(r, Robot.rect4)) {
			GameScreen.robot.centerX -= 20;
			GameScreen.robot.hurted = true;
			GameScreen.robot.power -= 1;
		}
		/*if(Rect.intersects(r, Robot.rect)){
			System.out.println("1");
		}
		if(Rect.intersects(r, Robot.rect2)){
			System.out.println("2");
		}
		if(Rect.intersects(r, Robot.rect3)){
			System.out.println("3");
		}
		if(Rect.intersects(r, Robot.rect4)){
			System.out.println("4");
		}*/
	}
	
	public void paintHurted(Graphics g, int x, int y){
		g.drawImage(exanim1.getImage(), x, y);
		hurted = false;
	}

	public void follow() {

		if (centerX < -95 || centerX > 1280) {
			movementSpeed = 0;
		}

		else if (Math.abs(robot.getCenterX() - centerX) < 5) {
			movementSpeed = 0;
		}

		else {

			if (robot.getCenterX() >= centerX) {
				movementSpeed = 1;
			} else {
				movementSpeed = -1;
			}
		}
		
		/*
		 * If enemy located over 400 pixel behind from the robot then destroy itself
		 * 
		 * */
		if(robot.getCenterX() - centerX > 400){
			health = 0;
		}
	}

	protected void die() {
		this.setCenterX(-200);
	}

	public void attack() {
		if (readyToFire) {
			ProjecttileEnemy pE = new ProjecttileEnemy(centerX + 50,
					centerY/* - 25*/, "e", "f");
			projectilesEn.add(pE);
		}
	}

	public ArrayList<ProjecttileEnemy> getProjectilesEn() {
		return projectilesEn;
	}

	public int getPower() {
		return power;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public Background getBg() {
		return bg;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setBg(Background bg) {
		this.bg = bg;
	}

	public boolean isReadyToFire() {
		return readyToFire;
	}

	public void setReadyToFire(boolean readyToFire) {
		this.readyToFire = readyToFire;
	}
}