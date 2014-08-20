package com.elefher.wolfhuntergame;

import android.graphics.Rect;

import com.kilobolt.framework.Image;

public class Tile {

	private int tileX, tileY, speedX;
	public int type;
	public Image tileImage;

	private Robot robot = GameScreen.getRobot();
	private Background bg = GameScreen.getBg1();

	public static Rect r;

	public Tile(int x, int y, int typeInt) {
		tileX = x * 40;
		tileY = y * 40;

		type = typeInt;

		r = new Rect();

		if (type == 1) {
			tileImage = Assets.tileice1;
		} else if (type == 2) {
			tileImage = Assets.tileice2;
		} else if (type == 3) {
			tileImage = Assets.tileice3;
		} else if (type == 4) {
			tileImage = Assets.tileLeft;
		} else if (type == 5) {
			tileImage = Assets.tileRight;
		} else if (type == 2) {
			tileImage = Assets.tilegrassBot;
		} else if (type == 6) {
			tileImage = Assets.tilestand1;
		} else if (type == 7) {
			tileImage = Assets.tilestand2;
		} else if (type == 8) {
			tileImage = Assets.tilestand3;
		} else if (type == 9) {
			tileImage = Assets.tilestand4;
		}else {
			type = 0;
		}

	}

	public void update() {
		speedX = bg.getSpeedX() * 5;
		tileX += speedX;
		r.set(tileX, tileY, tileX + 40, tileY + 40);

		if (Rect.intersects(r, Robot.yellowRed) && type != 0) {
			checkVerticalCollision(Robot.rect, Robot.rect2);
			checkSideCollision(Robot.rect3, Robot.rect4, Robot.footleft,
					Robot.footright);
		}
	}

	public int getTileX() {
		return tileX;
	}

	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
	}

	public Image getTileImage() {
		return tileImage;
	}

	public void setTileImage(Image tileImage) {
		this.tileImage = tileImage;
	}

	public void checkVerticalCollision(Rect rtop, Rect rbot) {
		if (Rect.intersects(rtop, r)) {
			robot.setPosAfterJumpHit(r.centerY());
		}
		/*if (Rect.intersects(rtop, r) && (type == 6 || type == 7 || type == 9)) {
			System.out.println("top");
			//robot.setCenterY(tileY - 63);
		}*/

		if (Rect.intersects(rbot, r) && (type == 6 || type == 7 || type == 8 || type == 9)) {
			robot.setJumped(false);
			robot.setSpeedY(0);
			robot.setCenterY(tileY - 63);
		}
	}

	public void checkSideCollision(Rect rleft, Rect rright, Rect leftfoot,
			Rect rightfoot) {
		if (type != 5 && type != 0) {			
			if (Rect.intersects(rleft, r)) {
				//System.out.println("1");
				//robot.setCenterX(tileX + 102);
				robot.setSpeedX(0);

			} else if (Rect.intersects(leftfoot, r)) {
				//System.out.println("2");
				robot.setCenterX(tileX + 85);
				robot.setSpeedX(0);
			}

			if (Rect.intersects(rright, r)) {
				//System.out.println("3");
				robot.setCenterX(tileX - 62);

				robot.setSpeedX(0);
			} else if (Rect.intersects(rightfoot, r)) {
				//System.out.println("4");
				robot.setCenterX(tileX - 45);
				robot.setSpeedX(0);
			}
		}
	}
}