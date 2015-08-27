/*
 * @(#)HUB.java	1.0  Aug 25, 2015 
 *
 * Copyright 2015 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

/**
 *  Mô tả đoạn code bên dưới
 *  @author: toantm
 *  @since: Aug 25, 2015
 */
public class HUB {
	private Player player;
	private BufferedImage image;
	private Font font;
	
	public HUB(Player player){
		this.player=player;
		try {
			image=ImageIO.read(getClass().getResourceAsStream("/HUD/hud.gif"));
			font=new Font("Arial",Font.PLAIN,14);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g){
		g.drawImage(image, 0, 10, null);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString(player.getHealth()+"/"+player.getMaxHealth(), 30, 25);
		g.drawString(player.getFire()/100+"/"+player.getMaxFire()/100, 30, 45);
	}
}
