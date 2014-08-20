package com.elefher.AssetsAlgorithms;

import com.elefher.wolfhuntergame.Animation;
import com.elefher.wolfhuntergame.GameScreen;
import com.kilobolt.framework.Graphics;

public class L1Algorithm {
	public enum EnemyStand {
		Down, Middle
	}

	public static EnemyStand ES = null;
	int countShoots = 0;
	long pastTime = System.currentTimeMillis();
	public static Animation currentAnim;
	
	public L1Algorithm(){
		ES = EnemyStand.Down;
		currentAnim = GameScreen.lastEnemyAnim1;
	}

	public void attackAlgorithm() {
		if (ES == EnemyStand.Down) {
			DownAttack();
		}
		if (ES == EnemyStand.Middle) {
			MiddleAttack();
		}
	}

	public void MiddleAttack() {
		if (GameScreen.lastBigEnemy.centerY > 80) {
			GameScreen.lastBigEnemy.centerY -= 1;
			return;
		}
		if (countShoots <= 15) {
			long current = System.currentTimeMillis();
			if (current >= (pastTime + 0.2 * 1000)) {
				pastTime = System.currentTimeMillis();
				GameScreen.lastBigEnemy.setReadyToFire(true);
				int randomY = (int) (Math.random() * 200 + 60);
				GameScreen.lastBigEnemy.attack(50, randomY, "d");
				countShoots++;
			} 
			if(countShoots % 5 == 0) {
				GameScreen.lastBigEnemy.setReadyToFire(true);
				int randomY = (int) (Math.random() * 100 + 80);
				GameScreen.lastBigEnemy.attack(50, randomY, "f");
			}
		}else{
			if(GameScreen.lastBigEnemy.getProjectilesEn().size() == 0){
				countShoots = 0;
				ES = EnemyStand.Down;
			}
		}
	}

	public void DownAttack() {
		if(GameScreen.lastBigEnemy.centerY < 430){
			GameScreen.lastBigEnemy.centerY += 1;
			return;
		}
		if (countShoots >= 10) {
			GameScreen.lastBigEnemy.setReadyToFire(true);
			superDownAttack();
		} else {
			long current = System.currentTimeMillis();
			if (current >= (pastTime + 1 * 1000)) { // multiply by 1000 to get
													// milliseconds
				pastTime = System.currentTimeMillis();
				GameScreen.lastBigEnemy.setReadyToFire(true);
				GameScreen.lastBigEnemy.setReadyToFire(true);
				int randomY = (int) (Math.random() * 200 + 60);
				GameScreen.lastBigEnemy.attack(50, randomY, "f");
				countShoots++;
			} else {
				GameScreen.lastBigEnemy.setReadyToFire(false);
			}
		}
	}

	public void superDownAttack() {
		long current = System.currentTimeMillis();

		if (current <= (pastTime + 3 * 1000)) { // multiply by 1000 to get
												// milliseconds
			GameScreen.lastBigEnemy.setReadyToFire(true);
			int randomY = (int) (Math.random() * 200 + 60);
			GameScreen.lastBigEnemy.attack(50, randomY, "f");
		} else {
			if(GameScreen.lastBigEnemy.getProjectilesEn().size() == 0){
				countShoots = 0;
				ES = EnemyStand.Middle;
			}
		}
	}
	
	public void paint(Graphics g) {
		g.drawImage(currentAnim.getImage(),
				GameScreen.lastBigEnemy.getCenterX(),
				GameScreen.lastBigEnemy.getCenterY());

		// g.drawRect(GameScreen.lastBigEnemy.centerX + 25,
		// GameScreen.lastBigEnemy.centerY + 70, 50, 50, Color.CYAN);
		// g.drawRect(GameScreen.lastBigEnemy.centerX,
		// GameScreen.lastBigEnemy.centerY - 63, GameScreen.lastBigEnemy.centerX
		// - 68, GameScreen.lastBigEnemy.centerY - 128, Color.CYAN);

		if (GameScreen.lastBigEnemy.hurted) {
			GameScreen.lastBigEnemy.paintHurted(g);
		}
	}
}