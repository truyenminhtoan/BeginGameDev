/*
 * @(#)FireBall.java	1.0  Aug 28, 2015 
 *
 * Copyright 2015 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.imageio.ImageIO;

import main.GamePanel;
import tilemap.TileMap;

/**
 * Mô tả đoạn code bên dưới
 * 
 * @author: toantm
 * @since: Aug 28, 2015
 */
public class FireBall extends MapObject {
	private boolean hit;
	private boolean remove;
	private BufferedImage[] sprites;
	private BufferedImage[] hitSprites;

	public FireBall(TileMap tm, boolean right) {
		super(tm);
		facingRight = true;
		moveSpeed = 3.8;
		if (right) {
			dx = moveSpeed;
		} else {
			dx = -moveSpeed;
		}

		width = 30;
		height = 30;
		cwidth = 14;
		cheight = 14;

		// load sprites
		try {
			BufferedImage spritesheet = ImageIO.read(getClass()
					.getResourceAsStream("/Sprites/Player/fireball.gif"));
			sprites = new BufferedImage[4];
			for (int i = 0; i < sprites.length; i++) {
				sprites[i] = spritesheet.getSubimage(i * width, 0, width,
						height);
			}
			hitSprites = new BufferedImage[3];
			for (int i = 0; i < hitSprites.length; i++) {
				hitSprites[i] = spritesheet.getSubimage(i * width, height,
						width, height);
			}
			animation = new Animation();
			animation.setDeplay(70);
			animation.setFrames(sprites);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setHit() {
		if (hit) {
			return;
		}
		hit = true;
		animation.setFrames(hitSprites);
		animation.setDeplay(70);
		dx = 0;
	}

	public boolean shouldRemove() {
		return remove;
	}

	public void update() {
		checkTileMapCollision();
		tinh();
		// table(1);
		setPosition(xtemp, ytemp);

		if (dx == 0 && !hit) {
			setHit();
		}

		animation.update();
		if (hit && animation.hasPlayedOne()) {
			remove = true;
		}
	}

	double t = 0.0;

	private void tinh() {
		xtemp = x;
		ytemp = y;
		double R = Math.toRadians(30);
		double r = Math.toRadians(40);
		double a = Math.toRadians(71);

		if (t < 500) {
			xtemp += (R + r) * Math.cos(t) - (r + a)
					* Math.cos(((R + r) / r) * t);
			ytemp += (R + r) * Math.sin(t) - (r + a)
					* Math.sin(((R + r) / r) * t);
		}else{
			t=0;
		}
		t += 0.02;
	}

	private void tinh1() {
		xtemp = x;
		ytemp = y;
		double R = 30;
		double r = 40;
		double a = 10;
		if (t < 100) {
			xtemp+= (R + r) * Math.cos(t) - (r + a)
					* Math.cos(((R + r) / r) * t);
			ytemp+= (R + r) * Math.sin(t) - (r + a)
					* Math.sin(((R + r) / r) * t);
		}
		t += 0.02;
	}

	public void checkTileMapCollision1() {
		double R = 10;
		double r = 10;
		double a = -45;
		
		currCol = (int) x / tileSize;
		currRow = (int) y / tileSize;
		
//		dx += (R + r) * Math.cos(t) - (r + a)
//				* Math.cos(((R + r) / r) * t);
//		dy += (R + r) * Math.sin(t) - (r + a)
//				* Math.sin(((R + r) / r) * t);
		

		xdest = x + dx;
		ydest = y + dy;

		xtemp = x;
		ytemp = y;

		caculateCorners(x, ydest);

		if (dy < 0) {
			if (topLeft || topRight) {
				dy = 0;
				ytemp = currRow * tileSize + cheight / 2;
			} else {
				ytemp += dy;
			}
		}
		if (dy > 0) {
			if (bottonLeft || bottonRight) {
				dy = 0;
				falling = false;
				ytemp = (currRow + 1) * tileSize - cheight / 2;
			} else {
				ytemp += dy;
			}
		}

		caculateCorners(xdest, y);
		if (dx < 0) {
			if (topLeft || bottonLeft) {
				dx = 0;
				xtemp = currCol * tileSize + cwidth / 2;
			} else {
				xtemp += dx;
			}
		}
		if (dx > 0) {
			if (topRight || bottonRight) {
				dx = 0;
				xtemp = (currCol + 1) * tileSize - cwidth / 2;
			} else {
				xtemp += dx;
			}
		}

		if (!falling) {
			caculateCorners(x, ydest + 1);
			if (!bottonLeft && !bottonRight) {
				falling = true;
			}
		}
		t += 0.02;
	}
	
	
	public void draw(Graphics2D g) {
		if (notOnScreen())
			return;
		setMapPosition();
		super.draw(g);
	}
}
