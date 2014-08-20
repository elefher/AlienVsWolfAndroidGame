package com.elefher.AssetsAlgorithms;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.generic.classes.ReaderFiles;

public class InitializeLife {
	public List<Integer> pixelY = new ArrayList<Integer>();
	public List<Integer> pixelX = new ArrayList<Integer>();
	public List<Integer> value = new ArrayList<Integer>();
	private int[] cordsSets = new int[3];

	public void setCords(String level, Context context) {
		ReaderFiles readerFiles = new ReaderFiles(context);
		try {
			ArrayList<String> foodCordinates = readerFiles
					.readFile(level, "life.txt");
			for (String cords : foodCordinates) {
				String[] cordsArray = cords.split(" ");
				pixelX.add(Integer.parseInt(cordsArray[0]));
				pixelY.add(Integer.parseInt(cordsArray[1]));
				value.add(Integer.parseInt(cordsArray[2]));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int[] getLife() { 
		cordsSets[0] = pixelX.get(0);
		cordsSets[1] = pixelY.get(0);
		cordsSets[2] = value.get(0);
		return cordsSets;
	}
}
