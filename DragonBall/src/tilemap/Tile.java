/*
 * @(#)Tile.java	1.0  Aug 19, 2015 
 *
 * Copyright 2015 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package tilemap;

import java.awt.image.BufferedImage;

/**
 *  Mô tả đoạn code bên dưới
 *  @author: toantm
 *  @since: Aug 19, 2015
 */
public class Tile {
	private BufferedImage image;
	private int type;
	
	//tile types
	public static final int NORMAL=0;
	public static final int BLOCKED=1;
	
	public Tile(BufferedImage image, int type){
		this.image=image;
		this.type=type;
	}

	public BufferedImage getImage() {
		return image;
	}

	public int getType() {
		return type;
	}
}
