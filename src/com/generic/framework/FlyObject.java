package com.generic.framework;

import com.kilobolt.AlienVsWolf.Animation;
import com.kilobolt.AlienVsWolf.Background;
import com.kilobolt.AlienVsWolf.GameScreen;
import com.kilobolt.framework.Graphics;

import android.graphics.Rect;

public abstract class FlyObject {
	public int centerX,
			   centerY,
	           power,
	           speedX;
	public final int MOVESPEED;
	public Rect r = new Rect(0, 0, 0, 0);
	public Background bg = GameScreen.getBg1();
	public Animation flyObjectAnim = null, destroyAnim = null;
	public boolean hit = false;
	
	public FlyObject(int centerX, int centerY, int power, int speedX, int moveSpeed){
		this.centerX = centerX;
		this.centerY = centerY;
		this.power = power;
		this.speedX = speedX;
		MOVESPEED = moveSpeed;
	}
	
	public void setFlyObjectR(int left, int top, int right, int bottom){
		r.set(left, top, right, bottom);
	}
	
	public void updateAnim(){
		flyObjectAnim.update(10);
		destroyAnim.update(10);
	}
	
	public abstract void setFlyobjectAnim();	
	public abstract void update();
	public abstract void checkCollision();
	public abstract void paint(Graphics g);
	public abstract void setDestroyAnim();
	public abstract void playDestroyAnim(Graphics g);
}