package com.elefher.threads;

import java.util.ArrayList;

import android.graphics.Color;
import android.graphics.Rect;

import com.kilobolt.AlienVsWolf.Animation;
import com.kilobolt.AlienVsWolf.Assets;
import com.kilobolt.AlienVsWolf.Background;
import com.kilobolt.AlienVsWolf.GameScreen;
import com.kilobolt.AlienVsWolf.ProjecttileEnemy;
import com.kilobolt.AlienVsWolf.Robot;
import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Image;
import com.kilobolt.framework.Screen;

public class PlatformMovingThread implements Runnable {

	Graphics g;
	public Image imgTile;
	public int width, height, pointx, pointy;
	private int speedX;
	public Rect r = new Rect(0, 0, 0, 0);
	private Background bg = GameScreen.getBg1();

	public PlatformMovingThread(Graphics graphics, Image tileplatform, int pointx, int pointy) {
		g = graphics;
		this.imgTile = tileplatform;
		this.width = this.imgTile.getWidth();
		this.height = this.imgTile.getHeight();
		this.pointx = pointx;
		this.pointy = pointy;
	}

	// Behavioral Methods
	public void update() {
		speedX = bg.getSpeedX() * 5;
		this.pointx += speedX;
		r.set(this.pointx, this.pointy, this.width, this.height*6);

		if (Rect.intersects(r, Robot.rect2)) {
			System.out.println("rect");
			//checkCollision();
		}
	}
	
	private void checkCollision() {
		/*if (Rect.intersects(r, Robot.rect) || Rect.intersects(r, Robot.rect2)
				|| Rect.intersects(r, Robot.rect3)
				|| Rect.intersects(r, Robot.rect4)) {
			visible = false;
		}*/
		if (Rect.intersects(r, Robot.rect2)) {
			System.out.println("set");
			GameScreen.robot.setJumped(false);
			GameScreen.robot.setSpeedY(0);
			GameScreen.robot.setCenterY(this.pointy - 63);
		}
		
	}

	public void paint() {
		g.drawImage(this.imgTile, this.pointx, this.pointy);
		g.drawRect(this.pointx, this.pointy, this.width, this.height, Color.argb(0, 200, 200, 100));
		//g.drawRect(Robot.centerX - 15, Robot.centerY + 40, 10, 35, Color.argb(200, 200, 35, 100));
		//g.drawRect(Robot.rect.left, Robot.rect.top + 63, Robot.rect.left, Robot.rect.top, Color.argb(200, 200, 35, 100));
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	// Behavioral Methods
	/*
	 * public void update() { // follow(); centerX += speedX; speedX =
	 * bg.getSpeedX() * 5 + movementSpeed; r.set(centerX, centerY, centerX +
	 * 100, centerY + 100);
	 * 
	 * if (Rect.intersects(r, Robot.yellowRed)) { checkCollision(); } }
	 * 
	 * private void checkCollision() { if (Rect.intersects(r, Robot.rect) ||
	 * Rect.intersects(r, Robot.rect2) || Rect.intersects(r, Robot.rect3) ||
	 * Rect.intersects(r, Robot.rect4)) {
	 * 
	 * } }
	 */

}
