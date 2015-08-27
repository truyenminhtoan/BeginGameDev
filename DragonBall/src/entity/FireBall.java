/*
 * @(#)FireBall.java	1.0  Aug 25, 2015 
 *
 * Copyright 2015 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import tilemap.TileMap;

/**
 *  Mô tả đoạn code bên dưới
 *  @author: toantm
 *  @since: Aug 25, 2015
 */
public class FireBall extends MapObject{

	private boolean hit;
	private boolean remove;
	private BufferedImage[] sprites;
	private BufferedImage[] hitSprites;
	/**
	 * @param tm
	 */
	public FireBall(TileMap tm, boolean right) {
		super(tm);
		facingRight=true;
		moveSpeed=3.8;
		if(right){
			dx=moveSpeed;
		}else{
			dx=-moveSpeed;
		}
		
		width=30;
		height=30;
		cwidth=14;
		cheight=14;
		
		//load sprites
		try {
			BufferedImage spritesheet=ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/fireball.gif"));
			sprites=new BufferedImage[4];
			for (int i = 0; i < sprites.length; i++) {
				sprites[i]=spritesheet.getSubimage(i*width, 0, width, height);
			}
			hitSprites=new BufferedImage[3];
			for (int i = 0; i < hitSprites.length; i++) {
				hitSprites[i]=spritesheet.getSubimage(i*width, height, width, height);
			}
			animation=new Animation();
			animation.setDeplay(70);
			animation.setFrames(sprites);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setHit(){
		if(hit){
			return;
		}
		hit=true;
		animation.setFrames(hitSprites);
		animation.setDeplay(70);
		dx=0;
	}
	
	public boolean shouldRemove(){
		return remove;
	}
	
	public void update(){
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
		if(dx==0 && !hit){
			setHit();
		}
		
		animation.update();
		if(hit && animation.hasPlayedOne()){
			remove=true;
		}
	}
	
	public void draw(Graphics2D g){
		if(notOnScreen())
			return;
		setMapPosition();
		super.draw(g);	
	}

}
