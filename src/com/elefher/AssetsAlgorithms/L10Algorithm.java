package com.elefher.AssetsAlgorithms;

import com.elefher.AssetsAlgorithms.L1Algorithm.EnemyStand;
import com.elefher.wolfhuntergame.Animation;
import com.elefher.wolfhuntergame.GameScreen;
import com.kilobolt.framework.Graphics;

public class L10Algorithm {
	public enum EnemyAttacks {
		Down, Middle
	}

	public static EnemyAttacks EA = null;
	int countShoots = 0;
	long pastTime = System.currentTimeMillis();
	public static Animation currentAnim;
	
	public L10Algorithm(){
		EA = EnemyAttacks.Down;
		currentAnim = GameScreen.lastEnemyAnim10;
	}

	public void attackAlgorithm() {
		if (EA == EnemyAttacks.Down) {
			DownAttack();
		}
		if (EA == EnemyAttacks.Middle) {
			MiddleAttack();
		}
	}

	public void MiddleAttack() {
		if (GameScreen.lastBigEnemy.centerY > 200) {
			GameScreen.lastBigEnemy.centerY -= 5;
			return;
		}
		if (countShoots <= 10) {
			long current = System.currentTimeMillis();
			if (current >= (pastTime + 0.2 * 1000)) {
				pastTime = System.currentTimeMillis();
				GameScreen.lastBigEnemy.setReadyToFire(true);
				int randomY = (int) (Math.random() * 100 + 20);
				GameScreen.lastBigEnemy.attack(50, randomY, "d");
				countShoots++;
			} 
			if(countShoots % 5 == 0) {
				GameScreen.lastBigEnemy.setReadyToFire(true);
				int randomY = (int) (Math.random() * 100 + 5);
				GameScreen.lastBigEnemy.attack(50, randomY, "f");
			}
		}else{
			if(GameScreen.lastBigEnemy.getProjectilesEn().size() == 0){
				countShoots = 0;
				EA = EnemyAttacks.Down;
			}
		}
	}

	public void DownAttack() {
		if(GameScreen.lastBigEnemy.centerY < 500){
			GameScreen.lastBigEnemy.centerY += 5;
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
				int randomY = (int) (Math.random() * 100 + 20);
				GameScreen.lastBigEnemy.attack(50, randomY, "f");
				countShoots++;
			} else {
				GameScreen.lastBigEnemy.setReadyToFire(false);
			}
		}
	}

	public void superDownAttack() {
		long current = System.currentTimeMillis();

		if (current <= (pastTime + 1 * 1000)) { // multiply by 1000 to get
												// milliseconds
			GameScreen.lastBigEnemy.setReadyToFire(true);
			int randomY = (int) (Math.random() * 100 + 20);
			GameScreen.lastBigEnemy.attack(50, randomY, "f");
		} else {
			if(GameScreen.lastBigEnemy.getProjectilesEn().size() == 0){
				countShoots = 0;
				EA = EnemyAttacks.Middle;
			}
		}
	}
	
	public void paint(Graphics g) {
		g.drawImage(currentAnim.getImage(),
				GameScreen.lastBigEnemy.getCenterX(),
				GameScreen.lastBigEnemy.getCenterY());

		if (GameScreen.lastBigEnemy.hurted) {
			GameScreen.lastBigEnemy.paintHurted(g);
		}
	}
}
