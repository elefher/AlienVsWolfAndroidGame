package com.kilobolt.AlienVsWolf;

import java.util.ArrayList;

import com.elefher.AssetsAlgorithms.L10Algorithm;
import com.elefher.AssetsAlgorithms.L1Algorithm;
import com.elefher.AssetsAlgorithms.L2Algorithm;
import com.elefher.AssetsAlgorithms.L5Algorithm;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Image;

import android.graphics.Color;
import android.graphics.Rect;

public class EnemyLevel {

	public static int power, centerX, centerY;
	final int JUMPSPEED = -15;
	private boolean jumped = false;
	private int jumpCounter = 0;
	private int speedX = 0;
	private int speedY = 0;
	private Background bg = GameScreen.getBg1();
	private Robot robot = GameScreen.getRobot();
	private boolean firtsTime = true;

	L1Algorithm l1;
	L2Algorithm l2;
	L5Algorithm l5;
	L10Algorithm l10;

	public Rect r = new Rect(0, 0, 0, 0);
	public Rect rFoot = new Rect(0, 0, 0, 0);
	
	public int health = 0;
	public boolean bigEnemyCreated = false;
	public boolean hurted = false;

	private int movementSpeed;
	private boolean readyToFire = true;
	public Animation exanim1 = new Animation();
	public Image explosion1;

	private ArrayList<ProjectTileLastEnemy> projectilesEn = new ArrayList<ProjectTileLastEnemy>();

	EnemyLevel() {
		explosion1 = Assets.explosion1;
		exanim1 = new Animation();
		exanim1.addFrame(explosion1, 200);
		firtsTime = true;
	}

	// Behavioral Methods
	public void update() {
		if(GameScreen.robot.level == 2 || GameScreen.robot.level == 3 || GameScreen.robot.level == 4){
			centerY += speedY;
			
			// Handles Jumping
			if(centerY == 560){
				jumpCounter = 0;
			}

			speedY += 1;

			if (speedY > 3) {
				jumped = true;
			}
		}

		
		r.set(centerX, centerY, centerX + 200, centerY + 200);
		//rFoot.set(centerX + 25, centerY + 70, 50, 50);
		rFoot.set(centerX, centerY - 63, centerX - 68, centerY - 128);
		

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
			GameScreen.robot.power -= 2;
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
	
	public boolean isJumped() {
		return jumped;
	}
	
	public void setJumped(boolean jumped) {
		this.jumped = jumped;
	}

	protected void die() {
		this.setCenterX(-500);
	}

	public void paintHurted(Graphics g) {
		g.drawImage(exanim1.getImage(), GameScreen.lastBigEnemy.r.centerX(),
				GameScreen.lastBigEnemy.r.centerY());
		exanim1.update(50);
		hurted = false;
	}

	public void attack(int x, int y, String direction) {
		if (readyToFire) {
			ProjectTileLastEnemy pE = new ProjectTileLastEnemy(centerX + x,
					centerY + y, "bE", direction);
			projectilesEn.add(pE);
		}
	}

	public void enemyLevelUpdate() {
		if (GameScreen.robot.level == 1) {
			if (firtsTime) {
				l1 = new L1Algorithm();
				firtsTime = false;
			}
			l1.attackAlgorithm();
		} else if (GameScreen.robot.level == 2) {
			if (firtsTime) {
				l2 = new L2Algorithm();
				firtsTime = false;
			}
			l2.attackAlgorithm();
		} else if (GameScreen.robot.level == 3) {
			if (firtsTime) {
				l2 = new L2Algorithm();
				firtsTime = false;
			}
			l2.attackAlgorithm();
		} else if (GameScreen.robot.level == 4) {
			if (firtsTime) {
				l2 = new L2Algorithm();
				firtsTime = false;
			}
			l2.attackAlgorithm();
		} else if (GameScreen.robot.level == 5 || GameScreen.robot.level == 6 ||
				GameScreen.robot.level == 7 || GameScreen.robot.level == 8 ||
				GameScreen.robot.level == 9) {
			if (firtsTime) {
				l5 = new L5Algorithm();
				firtsTime = false;
			}
			l5.attackAlgorithm();
		} else if (GameScreen.robot.level == 10) {
			if (firtsTime) {
				l10 = new L10Algorithm();
				firtsTime = false;
			}
			l10.attackAlgorithm();
		}								
	}

	public void paintEnemy(Graphics g) {
		if (l1 != null) {
			l1.paint(g);
		}

		if (l2 != null) {
			l2.paint(g);
		}
		
		if (l5 != null) {
			l5.paint(g);
		}
		
		if (l10 != null) {
			l10.paint(g);
		}
	}

	public ArrayList getProjectilesEn() {
		return projectilesEn;
	}

	public int getPower() {
		return power;
	}

	public int getSpeedX() {
		return speedX;
	}
	
	public int getSpeedY() {
		return speedY;
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
	
	public void setSpeedY(int speedY) {
		this.speedY = speedY;
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