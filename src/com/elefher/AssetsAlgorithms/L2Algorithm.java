package com.elefher.AssetsAlgorithms;

import com.elefher.wolfhuntergame.Animation;
import com.elefher.wolfhuntergame.GameScreen;
import com.kilobolt.framework.Graphics;

public class L2Algorithm {
	public enum EnemyMove {
		Stand, Jump, Run, Flip, Shoot, Kick
	}

	public static EnemyMove EM = null;
	public static Animation currentAnim;
	public int countShoot = 0;

	int countShoots = 0;
	long pastTime = System.currentTimeMillis();
	public boolean refreshPastTime = true;
	
	public L2Algorithm() {
		EM = EnemyMove.Stand;
		currentAnim = GameScreen.lastEnemyAnim2;
	}

	public void attackAlgorithm() {
		//EM = EnemyMove.Flip;
		if (EM == EnemyMove.Stand) {
			currentAnim = GameScreen.lastEnemyAnim2;
			GameScreen.lastBigEnemy.centerX = 1000;
			if (refreshPastTime) {
				pastTime = System.currentTimeMillis();
				refreshPastTime = false;
			}
			standAttack();
		}

		if (EM == EnemyMove.Jump) {
			currentAnim = GameScreen.lastEnemyjumpAnim2;
			GameScreen.lastBigEnemy.jump();
			if(GameScreen.lastBigEnemy.centerY == 560){
				GameScreen.lastBigEnemy.setJumped(false);
				EM = EnemyMove.Run;
			}
		}

		if (EM == EnemyMove.Run) {
			currentAnim = GameScreen.lastEnemyrunAnim2;
			run(30);
		}
		
		if (EM == EnemyMove.Flip) {
			currentAnim = GameScreen.lastEnemyflipAnim2;
			flip(30);
		}
		
		if (EM == EnemyMove.Shoot) {
			currentAnim = GameScreen.lastEnemyshootAnim2;
			if (refreshPastTime) {
				pastTime = System.currentTimeMillis();
				refreshPastTime = false;
			}
			GameScreen.lastBigEnemy.centerX = 1000;
			shoot();
		}
		
		if (EM == EnemyMove.Kick) {
			currentAnim = GameScreen.lastEnemykickAnim2;
			if (refreshPastTime) {
				pastTime = System.currentTimeMillis();
				refreshPastTime = false;
			}
			kick();
		}
		/*
		 * Set the enemy on the tile
		 */
		if (GameScreen.lastBigEnemy.getCenterY() > 560) {
			GameScreen.lastBigEnemy.setJumped(false);
			GameScreen.lastBigEnemy.setSpeedY(0);
			GameScreen.lastBigEnemy.setCenterY(560);
		}
	}
	
	private void kick(){
		long current = System.currentTimeMillis();
		if (current <= (pastTime + 5 * 1000)) {
			GameScreen.lastBigEnemy.centerX -= 13;
		}else{
			refreshPastTime = true;
			EM = EnemyMove.Stand;
		}		
	}
	
	private void shoot(){
		long current = System.currentTimeMillis();
		if (current <= (pastTime + 10 * 1000)) {
			if(!GameScreen.lastEnemyAnim2.hasNextFrame() /*&& countShoot < 3*/){
				countShoot++;
				GameScreen.lastBigEnemy.setReadyToFire(true);
				int randomY = (int) (Math.random() * 500 + 60);
				GameScreen.lastBigEnemy.attack(50, randomY, "f");
			}
		}else{
			countShoot = 0;
			refreshPastTime = true;
			EM = EnemyMove.Kick;
		}
	}
	
	private void flip(int x){
		if(GameScreen.lastBigEnemy.centerX > x){
			GameScreen.lastBigEnemy.centerX -= 13;
		} else if(GameScreen.lastBigEnemy.centerX < x){
			GameScreen.lastBigEnemy.centerX += 10;
		}else{
			EM = EnemyMove.Shoot;
		}
	}

	public void run(int x) {
		if(GameScreen.lastBigEnemy.centerX > x){
			GameScreen.lastBigEnemy.centerX -= 13;
		} else if(GameScreen.lastBigEnemy.centerX < x){
			GameScreen.lastBigEnemy.centerX += 10;
		}else{
			GameScreen.lastBigEnemy.centerX = 1000;
			EM = EnemyMove.Flip;
		}
	}

	public void standAttack() {
		long current = System.currentTimeMillis();
		if (current <= (pastTime + 2 * 1000)) {
			//shoot();
		} else {
			EM = EnemyMove.Jump;
			refreshPastTime = true;
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