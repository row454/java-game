package com.kiemax.items.skills;

import java.awt.image.BufferedImage;

public abstract class SkillBase {
	
	public boolean unlockable = false, unlocked = false;
	public SkillBase prerequisite;
	public ISkill function;
	
	public SkillBase(ISkill function, int x, int y, BufferedImage texture, SkillBase prerequisite, int price) {
		this.prerequisite = prerequisite;
		this.function = function;
	}
	public SkillBase(ISkill function, int x, int y, BufferedImage texture, int price) {
		unlockable = true;
		prerequisite = null;
		this.function = function;
	}
	
	public void tick() {
		if (!unlockable)
			if (prerequisite.unlocked = true)
				unlockable = true;
		function.onTick();
	}
}