package com.kiemax.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;


public class Assets {
	public static final Font font28 = PreLoaderImage.font28;
	public static final BufferedImage start[] = {PreLoaderImage.getAssets().get(1200), PreLoaderImage.getAssets().get(1201), PreLoaderImage.getAssets().get(1202)};
	public static final BufferedImage inventory = PreLoaderImage.inventory;
	public static BufferedImage[] getStart() {
		return start;
	}
}
