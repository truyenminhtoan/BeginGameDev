/*
 * @(#)Animation.java	1.0  Aug 20, 2015 
 *
 * Copyright 2015 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package entity;

import java.awt.image.BufferedImage;

/**
 *  Mô tả đoạn code bên dưới
 *  @author: toantm
 *  @since: Aug 20, 2015
 */
public class Animation {
	private BufferedImage[] frames;
	private int currentFrame;
	
	private long startTime;
	private long deplay;
	private boolean playedOne;
	
	public void Animation(){
		playedOne=false;
	}
	
	public void setFrames(BufferedImage[] frames){
		this.frames=frames;
		currentFrame=0;
		startTime=System.nanoTime();
		playedOne=false;
	}
	
	public void setDeplay(long d){
		deplay=d;
	}
	
	public void setFrame(int i){
		currentFrame=i;
	}
	
	public void update(){
		if(deplay==-1){
			return;
		}
		long elapsed=(System.nanoTime()-startTime)/1000000;
		if(elapsed>deplay){
			currentFrame++;
			startTime=System.nanoTime();
		}
		if(currentFrame==frames.length){
			currentFrame=0;
			playedOne=true;
		}
	}
	
	public int getFrame(){
		return currentFrame;
	}
	
	public BufferedImage getImage(){
		return frames[currentFrame];
	}
	
	public boolean hasPlayedOne(){
		return playedOne;
	}
}
