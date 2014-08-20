package com.generic.framework.implementation;

import android.graphics.Rect;

import com.elefher.wolfhuntergame.Animation;
import com.elefher.wolfhuntergame.Assets;
import com.elefher.wolfhuntergame.GameScreen;
import com.elefher.wolfhuntergame.Robot;
import com.generic.framework.FlyObject;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Image;

public class BrokenRocket extends FlyObject {

	int sizeDestroyAnim, DesptroyAnimrepeats = 0;
	int totalSpeed = 0;

	public BrokenRocket(int centerX, int centerY, int power, int speedX,
			int moveSpeed) {
		super(centerX, centerY, power, speedX, moveSpeed);
		setFlyobjectAnim();
		setDestroyAnim();
	}

	@Override
	public void update() {
		if (Math.abs(Robot.centerX - centerX) > 1280) {
			totalSpeed = 0;
		} else {
			totalSpeed = MOVESPEED;
		}
		centerX += speedX;
		centerY -= totalSpeed;
		speedX = bg.getSpeedX() * 5 + totalSpeed;
		setFlyObjectR(centerX - 50, centerY - 25, centerX + 50, centerY + 25);

		if (centerX < 0 || centerY > 617) {
			hit = true;
		}

		if (Rect.intersects(r, Robot.yellowRed)) {
			checkCollision();
		}
	}

	@Override
	public void checkCollision() {
		if (Rect.intersects(r, Robot.rect) || Rect.intersects(r, Robot.rect2)
				|| Rect.intersects(r, Robot.rect3)
				|| Rect.intersects(r, Robot.rect4)) {
			GameScreen.robot.hurted = true;
			GameScreen.robot.power -= power;
			hit = true;
		}
	}

	@Override
	public void setFlyobjectAnim() {
		flyObjectAnim = new Animation();
		int size = Assets.brokenRocket1.size();
		for (int i = 0; i < size; i++) {
			flyObjectAnim.addFrame((Image) Assets.brokenRocket1.get(i), 10);
		}
	}

	@Override
	public void setDestroyAnim() {
		destroyAnim = new Animation();
		sizeDestroyAnim = Assets.explosionB.size();
		for (int i = 0; i < sizeDestroyAnim; i++) {
			destroyAnim.addFrame((Image) Assets.explosionB.get(i), 100);
		}
	}

	@Override
	public void paint(Graphics g) {
		if (!hit) {
			g.drawImage(flyObjectAnim.getImage(), centerX, centerY);
		} else {
			if (DesptroyAnimrepeats <= sizeDestroyAnim) {
				playDestroyAnim(g);
				DesptroyAnimrepeats++;
			}
		}
		updateAnim();
	}

	@Override
	public void playDestroyAnim(Graphics g) {
		g.drawImage(destroyAnim.getImage(), centerX, centerY);
	}
}