/*
 * @(#)Explosion.java	1.0  Aug 25, 2015 
 *
 * Copyright 2015 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

/**
 *  Mô tả đoạn code bên dưới
 *  @author: toantm
 *  @since: Aug 25, 2015
 */
public class Explosion {
	private int x;
	private int y;
	private int xmap;
	private int ymap;
	
	private int width;
	private int height;
	
	private Animation animation;
	private BufferedImage [] sprites;
	
	private boolean remove;
	
	public Explosion(int x, int y){
		this.x=x;
		this.y=y;
		
		width=30;
		height=30;
		
		try {
			BufferedImage spritesheet=ImageIO.read(getClass().getResourceAsStream("/Sprites/Enemies/explosion.gif"));
			sprites=new BufferedImage[6];
			for (int i = 0; i < sprites.length; i++) {
				sprites[i]=spritesheet.getSubimage(i*width, 0, width, height);
			}
			animation=new Animation();
			animation.setDeplay(70);
			animation.setFrames(sprites);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(){
		animation.update();
		if(animation.hasPlayedOne()){
			remove=true;
		}
	}
	
	public boolean shouldRemove(){
		return remove;
	}
	
	public void setMapPosition(int x, int y){
		xmap=x;
		ymap=y;
	}
	
	public void draw(Graphics2D g){
		g.drawImage(animation.getImage(), x+xmap-width/2, y+ymap-height/2, null);
	}
}
