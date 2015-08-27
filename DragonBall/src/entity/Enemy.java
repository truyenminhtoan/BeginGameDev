/*
 * @(#)Enemy.java	1.0  Aug 25, 2015 
 *
 * Copyright 2015 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package entity;

import tilemap.TileMap;

/**
 *  Ke thu
 *  @author: toantm
 *  @since: Aug 25, 2015
 */
public class Enemy extends MapObject{
	protected int health;
	protected int maxHealth;
	protected boolean dead;
	protected int damage;
	
	protected boolean flinching;
	protected long flinchTime;	
	
	public Enemy(TileMap tm){
		super(tm);
	}
	
	public void hit(int damage){
		if(dead || flinching){
			return;
		}
		health-=damage;
		if(health<0){
			health=0;
		}
		if(health==0){
			dead=true;
		}
		flinching=true;
		flinchTime=System.nanoTime();
	}

	public boolean isDead() {
		return dead;
	}

	public int getDamage() {
		return damage;
	}
	
	public void update(){
		
	}
}
