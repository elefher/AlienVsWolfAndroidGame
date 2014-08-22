package com.elefher.threads;

import java.util.ArrayList;

import com.generic.framework.implementation.BrokenRocket;
import com.generic.framework.implementation.BrokenRocket3;
import com.kilobolt.AlienVsWolf.GameScreen;

public class UpdateBrokenRockets3Thread implements Runnable {
	Thread currentThread = null;
	ArrayList<BrokenRocket3> brokenRockets = null;
	
	public void UpdateBrokenRockets(ArrayList<BrokenRocket3> brokenRockets) {
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
					GameScreen.uBRT3 = null;
				}
			}
		}
	}
}