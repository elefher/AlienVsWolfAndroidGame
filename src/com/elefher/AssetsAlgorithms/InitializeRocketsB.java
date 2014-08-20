package com.elefher.AssetsAlgorithms;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.generic.classes.ReaderFiles;
import com.generic.framework.implementation.RocketB;

public class InitializeRocketsB {
	public List<Integer> pixelY = new ArrayList<Integer>();
	public List<Integer> pixelX = new ArrayList<Integer>();
	public List<Integer> power = new ArrayList<Integer>();
	public List<Integer> speedX = new ArrayList<Integer>();
	public List<Integer> moveSpeed = new ArrayList<Integer>();
	ArrayList<RocketB> rocket = null;

	public void setCords(String level, Context context) {
		rocket = new ArrayList<RocketB>();

		ReaderFiles readerFiles = new ReaderFiles(context);
		try {
			ArrayList<String> foodCordinates = readerFiles
					.readFile(level, "rocketsB.txt");
			for (String cords : foodCordinates) {
				String[] cordsArray = cords.split(" ");
				pixelX.add(Integer.parseInt(cordsArray[0]));
				pixelY.add(Integer.parseInt(cordsArray[1]));
				power.add(Integer.parseInt(cordsArray[2]));
				speedX.add(Integer.parseInt(cordsArray[3]));
				moveSpeed.add(Integer.parseInt(cordsArray[4]));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<RocketB> getRockets() {
		int length = pixelX.size();
		for (int i = 0; i < length; i++) {
			rocket.add(new RocketB(pixelX.get(i), pixelY.get(i), power.get(i), speedX.get(i), moveSpeed.get(i)));
		}
		return rocket;
	}

}
