package com.elefher.threads;

import java.util.ArrayList;

import android.graphics.Color;

import com.elefher.wolfhuntergame.GameScreen;
import com.elefher.wolfhuntergame.LastBigEnemyLevel;
import com.elefher.wolfhuntergame.Life;
import com.elefher.wolfhuntergame.ProjecttileEnemy;
import com.elefher.wolfhuntergame.Robot;
import com.elefher.wolfhuntergame.Weapon;
import com.kilobolt.framework.Graphics;

public class UpdateAssetsThreads implements Runnable {

	Thread currentThread = null;
	public Robot robot = null;
	//public static Life life1 = null;
	public boolean updateLifeFood;

	public UpdateAssetsThreads() {
		//life1 = new Life(x, y, lifeValue);
		updateLifeFood = false;
	}

	public void updateLife(Robot robot) {
		this.robot = robot;
		currentThread = new Thread(this);
		currentThread.start();
	}

	@Override
	public void run() {
		// update weapons
		/*for(int i = 0; i < this.weapons.size(); i++){
			if(this.weapons.get(i).visible == true){
				this.weapons.get(i).update();
			}else{
				this.weapons.get(i).isEnable = true;
				this.weapons.get(i).destroy();
				this.weapons.remove(i);
			}
		}*/

		// update life
		if (!GameScreen.life1.tookRobotLife) {
			if (GameScreen.life1.isVisible() == true) {
				GameScreen.life1.update();
			} else {
				robot.lives += GameScreen.life1.lifeValue;
				GameScreen.life1.tookRobotLife = true;
				GameScreen.life1.lifeValue = 0;
				GameScreen.life1.destroy();
			}
		}

		// update life 2
		/*if (!updateLifeFood) {
			if (robot.storeFood >= 1000 && !updateLifeFood) {
				robot.lives += life1.lifeValue;
				updateLifeFood = true;
			}
		}*/
	}
}