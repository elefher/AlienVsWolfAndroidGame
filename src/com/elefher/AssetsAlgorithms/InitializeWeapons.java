package com.elefher.AssetsAlgorithms;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.elefher.wolfhuntergame.Weapon;
import com.generic.classes.ReaderFiles;

public class InitializeWeapons {
	public List<Integer> pixelY = new ArrayList<Integer>();
	public List<Integer> pixelX = new ArrayList<Integer>();
	public List<String> weaponName = new ArrayList<String>();
	public List<Integer> damageRate = new ArrayList<Integer>();
	private ArrayList<Weapon> weapon = null;

	public void setCords(String level, Context context) {
		weapon = new ArrayList<Weapon>();

		ReaderFiles readerFiles = new ReaderFiles(context);
		try {
			ArrayList<String> foodCordinates = readerFiles
					.readFile(level, "weapons.txt");
			for (String cords : foodCordinates) {
				String[] cordsArray = cords.split(" ");
				pixelX.add(Integer.parseInt(cordsArray[0]));
				pixelY.add(Integer.parseInt(cordsArray[1]));
				weaponName.add(cordsArray[2]);
				damageRate.add(Integer.parseInt(cordsArray[3]));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Weapon> getWeapons() {
		int length = pixelX.size();
		for (int i = 0; i < length; i++) {
			weapon.add(new Weapon(pixelX.get(i), pixelY.get(i), weaponName.get(i), damageRate.get(i)));
		}
		return weapon;
	}
}
