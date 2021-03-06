package com.elefher.AssetsAlgorithms;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.generic.classes.ReaderFiles;
import com.generic.framework.implementation.BrokenRocket3;

public class InitializeBrokenRockets3 {
	public List<Integer> pixelY = new ArrayList<Integer>();
	public List<Integer> pixelX = new ArrayList<Integer>();
	public List<Integer> power = new ArrayList<Integer>();
	public List<Integer> speedX = new ArrayList<Integer>();
	public List<Integer> moveSpeed = new ArrayList<Integer>();
	ArrayList<BrokenRocket3> brokenRocket3 = null;

	public void setCords(String level, Context context) {
		brokenRocket3 = new ArrayList<BrokenRocket3>();

		ReaderFiles readerFiles = new ReaderFiles(context);
		try {
			ArrayList<String> foodCordinates = readerFiles
					.readFile(level, "brokenRockets3.txt");
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

	public ArrayList<BrokenRocket3> getBrokenRockets() {
		int length = pixelX.size();
		for (int i = 0; i < length; i++) {
			brokenRocket3.add(new BrokenRocket3(pixelX.get(i), pixelY.get(i), power.get(i), speedX.get(i), moveSpeed.get(i)));
		}
		return brokenRocket3;
	}
}
