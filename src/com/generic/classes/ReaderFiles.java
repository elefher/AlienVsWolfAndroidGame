package com.generic.classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.elefher.wolfhuntergame.NewLevelGame;
import com.elefher.wolfhuntergame.SampleGame;
import com.elefher.wolfhuntergame.R;

public class ReaderFiles {

	Context context;

	public ReaderFiles(Context context) {
		this.context = context;
	}

	public ArrayList<String> readFile(String level, String file)
			throws FileNotFoundException, IOException {
		AssetManager assetManager = this.context.getResources().getAssets();
		ArrayList<String> rawLines = new ArrayList<String>();

		// To load text file
		InputStream input = assetManager.open("level" + level + "/" + file);
		Scanner scanner = new Scanner(input);

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();

			// no more lines to read
			if (line == null) {
				break;
			}

			if (!line.startsWith("!")) {
				rawLines.add(line);
			}
		}
		scanner.close();
		return rawLines;
	}
}