package com.kilobolt.AlienVsWolf;

import java.util.ArrayList;

import com.kilobolt.framework.Image;

import android.graphics.Rect;

public class Weapon {
	public int centerX, centerY, speedX/*, power*/;
	public boolean visible = true;

	private Background bg = GameScreen.getBg1();
	private Robot robot = GameScreen.getRobot();
	
	public Rect r = new Rect(0, 0, 0, 0);

	public static int SHOOTSPEED = 9;
	public boolean isReadyToFire = false;
	public boolean isEnable = false;
	public final Animation anim = new Animation();
	public String weaponName;
	public Image animimage;
	public final int damageRate;

	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

	public Weapon(int x, int y, /*int power,*/ String weaponName, int damageRate) {
		this.centerX = x;
		this.centerY = y;
		//this.power = power;
		this.damageRate = damageRate;
		
		if(weaponName.equals("gun1")){
			int sizeAnim = Assets.gun.size();
			for(int i = 0; i < sizeAnim; i++){
				this.anim.addFrame(Assets.gun.get(i), 100);
			}
		}else if(weaponName.equals("nil")){
			int sizeAnim = Assets.nilA.size();
			for(int i = 0; i < sizeAnim; i++){
				this.anim.addFrame(Assets.nilA.get(i), 10);
			}
		}
		
		this.weaponName = weaponName;
	}

	// Behavioral Methods
	public void update() {
		speedX = bg.getSpeedX() * 5;
		centerX += speedX;
		r.set(centerX, centerY, centerX + 40, centerY + 40);

		if (Rect.intersects(r, Robot.yellowRed)) {
			checkCollision();
		}
	}
	
	private void checkCollision() {
		if (Rect.intersects(r, Robot.rect) || Rect.intersects(r, Robot.rect2)
				|| Rect.intersects(r, Robot.rect3)
				|| Rect.intersects(r, Robot.rect4)) {
			GameScreen.robot.gunName = this.weaponName;
			GameScreen.robot.damageRate = this.damageRate;
			GameScreen.weapon = this;
			visible = false;
		}
	}

	/*public void shoot(int x, int y) {
		if (isReadyToFire && isEnable) {
			Projectile p = new Projectile(Robot.centerX + x, Robot.centerY - y, this.damageRate);
			projectiles.add(p);
		}
	}*/
	
	public ArrayList getProjectiles() {
		return projectiles;
	}
	
	public void destroy() {
		this.centerX = -200;
	}
}