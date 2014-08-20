package com.elefher.threads;

import java.util.ArrayList;

import com.elefher.wolfhuntergame.GameScreen;
import com.elefher.wolfhuntergame.Weapon;
import com.generic.framework.implementation.Rocket;

public class UpdateRocketsThread implements Runnable {
	Thread currentThread = null;
	ArrayList<Rocket> rockets = null;
	
	public void UpdateRockets(ArrayList<Rocket> rockets) {
		this.rockets = rockets;
		currentThread = new Thread(this);
		currentThread.start();
	}
	
	@Override
	public void run() {
		for(int i = 0; i < this.rockets.size(); i++){
			if(!this.rockets.get(i).hit){
				this.rockets.get(i).update();
			}else{
				this.rockets.remove(i);
				if(this.rockets.size() == 0){
					GameScreen.uRT = null;
				}
			}
		}
	}
}