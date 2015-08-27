/*
 * @(#)Slugger.java	1.0  Aug 25, 2015 
 *
 * Copyright 2015 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package entity.enemy;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import tilemap.TileMap;
import entity.Animation;
import entity.Enemy;

/**
 * Mô tả đoạn code bên dưới
 * 
 * @author: toantm
 * @since: Aug 25, 2015
 */
public class Slugger extends Enemy {

	private BufferedImage[] sprites;

	/**
	 * @param tm
	 */
	public Slugger(TileMap tm) {
		super(tm);
		moveSpeed = 0.3;
		maxSpeed = 0.3;
		fallSpeed = 0.2;
		maxFallSpeed = 10.0;

		width = 30;
		height = 30;
		cwidth = 20;
		cheight = 20;

		health = maxHealth = 2;
		damage = 1;

		// load enmery
		try {
			BufferedImage spritesheet = ImageIO.read(getClass()
					.getResourceAsStream("/Sprites/Enemies/slugger.gif"));
			sprites = new BufferedImage[3];
			for (int i = 0; i < sprites.length; i++) {
				sprites[i] = spritesheet.getSubimage(i * width, 0, width,
						height);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		animation = new Animation();
		animation.setDeplay(300);
		animation.setFrames(sprites);
		right = true;
		facingRight=true;
	}

	private void getNextPosition() {
		// movement
		if (left) {
			dx -= moveSpeed;
			if (dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		} else if (right) {
			dx += moveSpeed;
			if (dx > maxSpeed) {
				dx = maxSpeed;
			}
		}
		if(falling){
			dy+=fallSpeed;
		}
		
	}

	public void update() {
		// update position
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
		//check flinching
		if(flinching){
			long eslapsed=(System.nanoTime()-flinchTime)/1000000;
			if(eslapsed>400){
				flinching=false;
			}
		}
		
		//if it hits a wall, go other direction
		if(right && dx==0){
			right=false;
			left=true;
			facingRight=false;
		} else if(left && dx==0){
			right=true;
			left=false;
			facingRight=true;
		}
		//update animation
		animation.update();
		
	}

	public void draw(Graphics2D g) {
/*		if(notOnScreen()){
			return;
		}*/
		setMapPosition();
		super.draw(g);
	}

}
