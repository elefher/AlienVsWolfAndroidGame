package com.kilobolt.AlienVsWolf;

import java.util.ArrayList;

import com.kilobolt.framework.Image;

import android.graphics.Rect;

public class Ring {
	public int centerX, centerY;
	private boolean visible = true;
	public int points;
	
	private Background bg = GameScreen.getBg1();
	private Robot robot = GameScreen.getRobot();

	public Rect r = new Rect(0, 0, 0, 0);
	
	private int ringX, ringY, speedX;

	// Behavioral Methods
	public void update() {
		speedX = bg.getSpeedX() * 5;
		centerX += speedX;
		r.set(centerX, centerY, centerX /*+ 40*/, centerY /*+ 40*/);

		if (Rect.intersects(r, Robot.yellowRed)) {
			checkCollision();
		}
	}
	
	public void setPoints(int points){
		this.points = points;
	}
	
	public int getPoints(){
		return this.points;
	}

	private void checkCollision() {
		if (Rect.intersects(r, Robot.rect) || Rect.intersects(r, Robot.rect2)
				|| Rect.intersects(r, Robot.rect3)
				|| Rect.intersects(r, Robot.rect4)) {
			visible = false;
		}
	}


	protected void destroy() {
		this.setCenterX(-200);
	}
	
	public boolean isVisible() {
		return visible;
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

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setBg(Background bg) {
		this.bg = bg;
	}
}
