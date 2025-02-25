package com.kiemax.gfx;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

public class FontLoader {
	public static Font loadFont(String path, float size) {
		try {
			return Font.createFont(Font.TRUETYPE_FONT, FontLoader.class.getResource(path).openConnection().getInputStream()).deriveFont(Font.PLAIN, size);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
}
