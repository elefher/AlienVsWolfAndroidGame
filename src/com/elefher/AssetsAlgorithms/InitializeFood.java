package com.elefher.AssetsAlgorithms;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.elefher.wolfhuntergame.FoodDrink;
import com.generic.classes.ReaderFiles;

import android.content.Context;

public class InitializeFood {
	public List<Integer> pixelY = new ArrayList<Integer>();
	public List<Integer> pixelX = new ArrayList<Integer>();
	public List<Integer> points = new ArrayList<Integer>();
	private ArrayList<FoodDrink> food = null;

	public void setCords(String level, Context context) {
		food = new ArrayList<FoodDrink>();

		ReaderFiles readerFiles = new ReaderFiles(context);
		try {
			ArrayList<String> foodCordinates = readerFiles
					.readFile(level, "foods.txt");
			for (String cords : foodCordinates) {
				String[] cordsArray = cords.split(" ");
				pixelX.add(Integer.parseInt(cordsArray[0]));
				pixelY.add(Integer.parseInt(cordsArray[1]));
				points.add(Integer.parseInt(cordsArray[2]));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<FoodDrink> getFoods() {
		int length = pixelX.size();
		for (int i = 0; i < length; i++) {
			food.add(new FoodDrink(pixelX.get(i), pixelY.get(i), points.get(i)));
		}
		return food;
	}
}