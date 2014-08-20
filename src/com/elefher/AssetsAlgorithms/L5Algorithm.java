package com.elefher.AssetsAlgorithms;

import com.elefher.wolfhuntergame.Animation;
import com.elefher.wolfhuntergame.GameScreen;
import com.kilobolt.framework.Graphics;

public class L5Algorithm {
	public enum EnemyCombo {
		Down, Move
	}

	public static EnemyCombo EC = null;
	int countShoots = 0;
	long pastTime = System.currentTimeMillis();
	public static Animation currentAnim;
	
	public L5Algorithm(){
		EC = EnemyCombo.Down;
		currentAnim = GameScreen.lastEnemyAnim5;
	}

	public void attackAlgorithm() {
		if (EC == EnemyCombo.Down) {
			DownAttack();
		}
		if (EC == EnemyCombo.Move) {
			MoveAttack();
		}
	}

	public void MoveAttack() {
		if(GameScreen.lastBigEnemy.centerX > 30){
			GameScreen.lastBigEnemy.centerX -= 13;
		} else if(GameScreen.lastBigEnemy.centerX < 30){
			GameScreen.lastBigEnemy.centerX += 10;
		}else{
			GameScreen.lastBigEnemy.centerX = 1000;
			EC = EnemyCombo.Down;
		}
	}

	public void DownAttack() {
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
			int randomY = (int) (Math.random() * 100 + 50);
			GameScreen.lastBigEnemy.attack(50, randomY, "f");
		} else {
			if(GameScreen.lastBigEnemy.getProjectilesEn().size() == 0){
				countShoots = 0;
				EC = EnemyCombo.Move;
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