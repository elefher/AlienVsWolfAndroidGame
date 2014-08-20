package com.elefher.wolfhuntergame;

import android.graphics.Rect;

public class Projectile {

	public int x, y, speedX;
	private boolean visible;
	private Rect r;
	public static int damageRate;
	public final String direction;

	public Projectile(int startX, int startY, int damageRate, String direction) {
		x = startX;
		y = startY;
		speedX = 7;
		this.damageRate = damageRate;
		this.direction = direction;
		visible = true;

		r = new Rect(0, 0, 0, 0);
	}

	public void update() {
		if(direction.equals("u") && (GameScreen.robot.stand.equals("f") || GameScreen.robot.stand.equals("b"))){
			y -= speedX;
			x += GameScreen.bg1.getSpeedX() * 5;
		}else if(direction.equals("fb")){
			x -= speedX;
		}else if(direction.equals("ff")){
			x += speedX;
		}else if(direction.equals("fu")){
			x += speedX + GameScreen.bg1.getSpeedX() * 5;
			y -= speedX;
		}else if(direction.equals("bu")){
			x -= speedX - GameScreen.bg1.getSpeedX() * 5;
			y -= speedX;
		}
		
		r.set(x, y, x + 10, y + 5);
		if (x > 1280) {
			visible = false;
			r = null;
		}
		if (visible) {
			checkCollision();
		}
	}

	private void checkCollision() {
		if (GameScreen.hbs != null) {
			for (int hi = 0; hi < GameScreen.hbs.size(); hi++) {
				if (Rect.intersects(r, GameScreen.hbs.get(hi).r)) {
					visible = false;

					if (GameScreen.hbs.get(hi).health > 0) {
						GameScreen.hbs.get(hi).health -= this.damageRate;
						GameScreen.hbs.get(hi).hurted = true;
					}
					if (GameScreen.hbs.get(hi).health <= 0) {
						GameScreen.hbs.get(hi).die();
						GameScreen.hbs.remove(hi);
						if (GameScreen.hbs.size() == 0) {
							GameScreen.hbs = null;
							break;
						}
					}
				}
			}
		}

		if (GameScreen.lastBigEnemy != null
				&& Rect.intersects(r, GameScreen.lastBigEnemy.r)) {
			visible = false;
			if (GameScreen.lastBigEnemy.health > 0) {
				GameScreen.lastBigEnemy.health -= 1;
				GameScreen.lastBigEnemy.hurted = true;
			}
			if (GameScreen.lastBigEnemy.health == 0) {
				GameScreen.lastBigEnemy.die();
			}
		}
		/*
		 * if (Rect.intersects(r, GameScreen.hb.r)) { visible = false;
		 * 
		 * if (GameScreen.hb.health > 0) { GameScreen.hb.health -= 1; } if
		 * (GameScreen.hb.health == 0) { GameScreen.hb.die(); } }
		 */
		/*
		 * if (Rect.intersects(r, GameScreen.hb2.r)){ visible = false;
		 * 
		 * if (GameScreen.hb2.health > 0) { GameScreen.hb2.health -= 1; } if
		 * (GameScreen.hb2.health == 0) { GameScreen.hb2.setCenterX(-200);
		 * 
		 * 
		 * } } if (Rect.intersects(r, GameScreen.hb3.r)){ visible = false;
		 * 
		 * if (GameScreen.hb3.health > 0) { GameScreen.hb3.health -= 1; } if
		 * (GameScreen.hb3.health == 0) { GameScreen.hb3.setCenterX(-200); } }
		 */
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSpeedX() {
		return speedX;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
