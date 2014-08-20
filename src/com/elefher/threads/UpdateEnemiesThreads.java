package com.elefher.threads;

import java.util.ArrayList;

import com.elefher.wolfhuntergame.GameScreen;
import com.elefher.wolfhuntergame.ProjecttileEnemy;

public class UpdateEnemiesThreads implements Runnable {

	Thread currentThread = null;

	public void UpdateEnemies() {
		currentThread = new Thread(this);
		currentThread.start();
	}

	@Override
	public void run() {
		for (int l = 0; l < GameScreen.hbs.size(); l++) {
			// Update enemies
			GameScreen.hbs.get(l).update();
			if (GameScreen.hbs.get(l).centerX < 1280) {
				GameScreen.hbs.get(l).attack();
			}

			/*
			 * If health of enemy is 0 then remove the item of hbs this part of
			 * code connects with enemy update() function if enemy located over
			 * 400 pixel from robot then destroy this enemy
			 */
			if (GameScreen.hbs.get(l).health <= 0) {
				GameScreen.hbs.remove(l);
				if (GameScreen.hbs.size() == 0) {
					GameScreen.hbs = null;
					break;
				}
				continue;
			}

			// Set enemies shoot
			ArrayList projectilesEnemies = GameScreen.hbs.get(l)
					.getProjectilesEn();
			int pESize = projectilesEnemies.size();
			for (int p = 0; p < pESize; p++) {
				ProjecttileEnemy pEnemy = (ProjecttileEnemy) projectilesEnemies
						.get(p);
				if (pEnemy.isVisible() == true
						&& GameScreen.hbs.get(l).centerX < 1280) {
					pEnemy.update();
					GameScreen.hbs.get(l).setReadyToFire(false);
				} else {
					GameScreen.hbs.get(l).getProjectilesEn().remove(p);
					// projectilesEnemies.remove(p);
				}
			}
			// Set the rate of attack
			if (pESize < 1) {
				GameScreen.hbs.get(l).setReadyToFire(true);
			}
		}
	}
}
