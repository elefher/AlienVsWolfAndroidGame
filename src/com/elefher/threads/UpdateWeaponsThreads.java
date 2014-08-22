package com.elefher.threads;
import java.util.ArrayList;

import com.kilobolt.AlienVsWolf.GameScreen;
import com.kilobolt.AlienVsWolf.Weapon;

public class UpdateWeaponsThreads implements Runnable {
	Thread currentThread = null;
	ArrayList<Weapon> weapons = null;
	
	public void UpdateWeapons(ArrayList<Weapon> weapons) {
		this.weapons = weapons;
		currentThread = new Thread(this);
		currentThread.start();
	}
	
	@Override
	public void run() {
		for(int i = 0; i < this.weapons.size(); i++){
			if(this.weapons.get(i).visible == true){
				this.weapons.get(i).update();
			}else{
				this.weapons.get(i).isEnable = true;
				this.weapons.get(i).destroy();
				this.weapons.remove(i);
				if(this.weapons.size() == 0){
					GameScreen.uWT = null;
				}
			}
		}
	}
}