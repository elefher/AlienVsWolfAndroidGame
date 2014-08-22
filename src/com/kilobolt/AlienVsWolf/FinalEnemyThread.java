package com.kilobolt.AlienVsWolf;

import java.util.ArrayList;

import com.kilobolt.framework.Graphics;

public class FinalEnemyThread implements Runnable {
	public static LastBigEnemyLevel lastBigEnemy;
	Thread currentThread = null;
	ArrayList<?> projectilesLastEnemy = null;

	public FinalEnemyThread(LastBigEnemyLevel lastBigEnemy) {
		this.lastBigEnemy = lastBigEnemy;
	}

	public void updateBigEnemy() {
		currentThread = new Thread(this);
		currentThread.start();
	}

	@Override
	public void run() {
		// Update the final enemy, shoot and set the rate of attack
		GameScreen.lastBigEnemy.update();
		GameScreen.lastBigEnemy.enemyLevelUpdate();

		projectilesLastEnemy = GameScreen.lastBigEnemy.getProjectilesEn();
		for (int p = 0; p < projectilesLastEnemy.size(); p++) {
			ProjectTileLastEnemy pLastEnemy = (ProjectTileLastEnemy) projectilesLastEnemy
					.get(p);
			if (pLastEnemy.isVisible() == true && GameScreen.lastBigEnemy.centerX < 1100) {
				pLastEnemy.update();
			} else {
				projectilesLastEnemy.remove(p);
			}
		}
	}

	public void draw(final Graphics g) {
		new Thread(new Runnable() {
			public void run() {
				projectilesLastEnemy = GameScreen.lastBigEnemy.getProjectilesEn();

				for (int p = 0; p < projectilesLastEnemy.size(); p++) {
					ProjectTileLastEnemy pLastEnemy = (ProjectTileLastEnemy) projectilesLastEnemy
							.get(p);
					if(GameScreen.robot.level < 5){
						g.drawImage(GameScreen.firepunch2.getImage(), pLastEnemy.getX(), pLastEnemy.getY(), 0, 0, 23, 17);
					} else if(GameScreen.robot.level == 5 || GameScreen.robot.level == 6 || GameScreen.robot.level == 7 ||
							GameScreen.robot.level == 8 || GameScreen.robot.level == 9){
						g.drawImage(GameScreen.lastEnemyBomb5.getImage(), pLastEnemy.getX(), pLastEnemy.getY(), 0, 0, 50, 27);
					} else if(GameScreen.robot.level == 10){
						g.drawImage(GameScreen.lastEnemyBomb5.getImage(), pLastEnemy.getX(), pLastEnemy.getY(), 0, 0, 50, 27);
					}
				}
			}
		}).start();
	}

	public void destroy() {
		projectilesLastEnemy.clear();
	}
}