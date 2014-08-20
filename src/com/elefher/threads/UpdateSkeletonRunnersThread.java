package com.elefher.threads;

import java.util.ArrayList;

import com.elefher.wolfhuntergame.GameScreen;
import com.generic.framework.implementation.RocketB;
import com.generic.framework.implementation.SkeletonRunner;

public class UpdateSkeletonRunnersThread implements Runnable {
	Thread currentThread = null;
	ArrayList<SkeletonRunner> skeletonRunner = null;
	
	public void UpdateSkeletonRunners(ArrayList<SkeletonRunner> skeletonRunner) {
		this.skeletonRunner = skeletonRunner;
		currentThread = new Thread(this);
		currentThread.start();
	}
	
	@Override
	public void run() {
		for(int i = 0; i < this.skeletonRunner.size(); i++){
			if(!this.skeletonRunner.get(i).hit){
				this.skeletonRunner.get(i).update();
			}else{
				this.skeletonRunner.remove(i);
				if(this.skeletonRunner.size() == 0){
					GameScreen.uSR = null;
				}
			}
		}
	}
}