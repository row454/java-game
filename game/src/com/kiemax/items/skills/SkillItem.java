package com.kiemax.items.skills;

import java.awt.image.BufferedImage;

import com.kiemax.items.Item;

public class SkillItem extends SkillBase {

	public SkillItem(ISkill function, int x, int y, BufferedImage texture, SkillBase prerequisite, int price, Item item) {
		super(function, x, y, texture, prerequisite, price);
	}
	
	
	public SkillItem(ISkill function, int x, int y, BufferedImage texture, int price, Item item) {
		super(function, x, y, texture, price);
	}

}
