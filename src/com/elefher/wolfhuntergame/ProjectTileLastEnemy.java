package com.elefher.wolfhuntergame;

import android.graphics.Rect;

import com.elefher.AssetsAlgorithms.L10Algorithm;
import com.elefher.AssetsAlgorithms.L10Algorithm.EnemyAttacks;
import com.elefher.AssetsAlgorithms.L1Algorithm;
import com.elefher.AssetsAlgorithms.L2Algorithm;
import com.elefher.AssetsAlgorithms.L1Algorithm.EnemyStand;
import com.elefher.AssetsAlgorithms.L2Algorithm.EnemyMove;
import com.elefher.AssetsAlgorithms.L5Algorithm;
import com.elefher.AssetsAlgorithms.L5Algorithm.EnemyCombo;

public class ProjectTileLastEnemy {

	private int x, y, speedX;
	private boolean visible;
	private final String owner;
	private final String direction;

	private Rect r;

	public ProjectTileLastEnemy(int startX, int startY, String owner,
			String direction) {
		x = startX;
		y = startY;
		this.owner = owner;
		this.direction = direction;
		speedX = 8;
		visible = true;

		r = new Rect(0, 0, 0, 0);
	}

	public void update() {
		if (this.owner.equals("bE")) {
			if ((L1Algorithm.ES == EnemyStand.Down || 
					L1Algorithm.ES == EnemyStand.Middle || L5Algorithm.EC == EnemyCombo.Down ||
					L10Algorithm.EA == EnemyAttacks.Down || L10Algorithm.EA == EnemyAttacks.Middle || 
					L2Algorithm.EM == EnemyMove.Shoot || L2Algorithm.EM == EnemyMove.Stand)
					&& direction.equals("f")) {
				x -= speedX;
			} else if (L1Algorithm.ES == EnemyStand.Middle
					&& direction.equals("d") || L10Algorithm.EA == EnemyAttacks.Middle
							&& direction.equals("d")) {
				x -= speedX;
				y += speedX / 2;
			}
		}
		// x -= speedX;

		r.set(x, y, x + 10, y + 5);
		if (x <= 0) {
			visible = false;
			r = null;
		}
		if (visible) {
			checkCollision();
		}
	}

	private void checkCollision() {
		/*
		 * for(Heliboy item: GameScreen.hbs){ if (Rect.intersects(r, item.r)){
		 * visible = false;
		 * 
		 * if (item.health > 0) { item.health -= 1; } if (item.health == 0) {
		 * try { item.die(item); } catch (Throwable e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); } }
		 * 
		 * } }
		 */
		if (Rect.intersects(r, GameScreen.robot.rect)
				|| Rect.intersects(r, GameScreen.robot.rect2)
				&& !GameScreen.robot.isDucked()) {
			visible = false;

			if (GameScreen.robot.power > 0) {
				GameScreen.robot.power -= 1;
				GameScreen.robot.hurted = true;
			}
			/*
			 * if (GameScreen.robot.power == 0) { }
			 */
		}
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
