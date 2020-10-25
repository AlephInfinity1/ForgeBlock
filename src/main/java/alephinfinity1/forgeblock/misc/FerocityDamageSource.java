package alephinfinity1.forgeblock.misc;

import net.minecraft.entity.Entity;
import net.minecraft.util.EntityDamageSource;

public class FerocityDamageSource extends EntityDamageSource {
	
	private final int stack;

	public FerocityDamageSource(String damageTypeIn, Entity damageSourceEntityIn, int stack) {
		super(damageTypeIn, damageSourceEntityIn);
		this.stack = stack;
	}
	
	//Used to recursively calculate ferocity, reducing it by 100 each extra strike.
	public int getStacking() {
		return stack;
	}

}
