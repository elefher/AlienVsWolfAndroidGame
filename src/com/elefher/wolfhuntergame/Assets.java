package com.elefher.wolfhuntergame;

import java.util.ArrayList;

import com.kilobolt.framework.Image;
import com.kilobolt.framework.Music;
import com.kilobolt.framework.Sound;

public class Assets {
	
	public static Image menu, nil, finish, finishLevel, life, splash, background, characterup, characterupback, characterfu,
	characterfuback, characterrun1, characterrun2, characterrun3, characterrun4, characterrun5, characterrun6, characterrun7,
	characterrun8, characterrun9, characterrun10, characterrun11, characterrun12, characterrun13, characterrun14,
	characterrun15, characterrun16, characterrun17, character, character2, character3, character4, character5, character6,
	character7,	character8, character9, character10, character11, character12, character13, character14, character15,
	character16, character17, character18, character19, character20, character21, characterhurt1, characterhurt2, characterhurt3,
	characterhurt4, characterhurt5,	heliboy, heliboy2, heliboy3, heliboy4, heliboy5, gun1, explosion1, explosion2,
	explosion3, explosion4, explosion5, target, pause;
	public static ArrayList<Image> characterbackList, characterrunbackList, characterjumpbackList, characterhurtbackList,
	biglastenemy1, biglastenemy2, biglastenemyrun2, biglastenemyjump2, biglastenemykick2, biglastenemyshoot2, biglastenemyflip2, firepunch2, 
	explosionC,	explosionB, explosionD, rocket, brokenRocket1, brokenRocket2, brokenRocket3, nilA, gun, fireball, lightball, ring, rocketb, skeletonrun,
	tankbomb, tankwolf, flywolf, supergunbullet;
	public static Image tiledirt, tilegrassTop, tilegrassBot, tileLeft, tileRight, tilegrassLeft, tilegrassRight, characterJump, tileplatform,  
	characterJump2, characterJump3, characterJump4, characterJump5, characterJump6, characterJump7,
	characterJump8, characterJump9, characterJump10, characterJump11, characterJump12, characterJump13,
	characterJump14, characterJump15, characterDown, characterDownBack, tilestand1, tilestand2, tilestand3, tilestand4,
	tileice1, tileice2, tileice3;
	public static Image buttona, buttonb, joystick, joystickright, joystickleft, joystickup, joystickdown,
	joystickupright, joystickupleft;
	public static Sound click, nilgun, jump;
	public static Music theme;
	
	public static void load(SampleGame sampleGame) {
		// TODO Auto-generated method stub
		theme = sampleGame.getAudio().createMusic("menutheme.mp3");
		theme.setLooping(true);
		theme.setVolume(0.85f);
		theme.play();
	}
	
	public static void load(NewLevelGame newLevelGame) {
		// TODO Auto-generated method stub
		theme = newLevelGame.getAudio().createMusic("menutheme.mp3");
		theme.setLooping(true);
		theme.setVolume(0.85f);
		theme.play();
	}	
}