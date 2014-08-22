package com.kilobolt.AlienVsWolf;

import android.graphics.Rect;

public class Pottery {
	public int centerX, centerY;
	private boolean visible = true;
	public int lifeValue;
	public static boolean tookRobotLife;
	
	private Background bg = GameScreen.getBg1();
	//private Robot robot = GameScreen.getRobot();

	public Rect r = new Rect(0, 0, 0, 0);
	
	private int ringX, ringY, speedX;
	
	Pottery(){
		tookRobotLife = false;
	}
	// Behavioral Methods
	public void update() {
		speedX = bg.getSpeedX() * 5;
		centerX += speedX;
		r.set(centerX, centerY, centerX + 40, centerY + 40);

		if (Rect.intersects(r, Robot.yellowRed)) {
			checkCollision();
		}
	}
	
	public void setlifeValue(int lifeValue){
		this.lifeValue = lifeValue;
	}
	
	public int getlifeValue(){
		return this.lifeValue;
	}

	private void checkCollision() {
		if (Rect.intersects(r, Robot.rect) || Rect.intersects(r, Robot.rect2)
				|| Rect.intersects(r, Robot.rect3)
				|| Rect.intersects(r, Robot.rect4)) {
			visible = false;
		}
		/*if (Rect.intersects(r, Robot.rect)) {
			System.out.println("1");
			visible = false;
		}
		if (Rect.intersects(r, Robot.rect2)) {
			System.out.println("2");
			visible = false;
		}
		if (Rect.intersects(r, Robot.rect3)) {
			System.out.println("3");
			visible = false;
		}
		if (Rect.intersects(r, Robot.rect4)) {
			System.out.println("4");
			visible = false;
		}*/
		
	}


	public void destroy() {
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
