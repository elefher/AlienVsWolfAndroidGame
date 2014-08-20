package com.elefher.threads;

import java.util.ArrayList;

import com.elefher.wolfhuntergame.GameScreen;
import com.generic.framework.implementation.BrokenRocket;
import com.generic.framework.implementation.Rocket;

public class UpdateBrokenRocketsThread implements Runnable {
	Thread currentThread = null;
	ArrayList<BrokenRocket> brokenRockets = null;
	
	public void UpdateBrokenRockets(ArrayList<BrokenRocket> brokenRockets) {
		this.brokenRockets = brokenRockets;
		currentThread = new Thread(this);
		currentThread.start();
	}
	
	@Override
	public void run() {
		for(int i = 0; i < this.brokenRockets.size(); i++){
			if(!this.brokenRockets.get(i).hit){
				this.brokenRockets.get(i).update();
			}else{
				this.brokenRockets.remove(i);
				if(this.brokenRockets.size() == 0){
					GameScreen.uBRT = null;
				}
			}
		}
	}
}