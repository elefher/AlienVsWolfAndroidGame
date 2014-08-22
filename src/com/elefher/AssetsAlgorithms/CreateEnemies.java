package com.elefher.AssetsAlgorithms;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.generic.classes.ReaderFiles;
import com.kilobolt.AlienVsWolf.Heliboy;

public class CreateEnemies {
	public String level;
	public int parts;
	public List<Integer> pixelY = new ArrayList<Integer>();
	public List<Integer> pixelX = new ArrayList<Integer>();
	public static ArrayList<Heliboy> hbs = null;
	Context context;

	public CreateEnemies(String level, Context context) {
		this.level = level;
		this.context = context;
		hbs = new ArrayList<Heliboy>();

		ReaderFiles readerFiles = new ReaderFiles(this.context);
		try {
			ArrayList<String> enemiesCordinates = readerFiles
					.readFile(this.level, "enemies.txt");
			for (String cords : enemiesCordinates) {
				String[] cordsArray = cords.split(" ");
				pixelX.add(Integer.parseInt(cordsArray[0]));
				pixelY.add(Integer.parseInt(cordsArray[1]));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Heliboy> getEnemies() {
		int length = pixelX.size();
		for (int i = 0; i < length; i++) {
			hbs.add(new Heliboy(pixelX.get(i), pixelY.get(i)));
		}
		return hbs;
	}
}
