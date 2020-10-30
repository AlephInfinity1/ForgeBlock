package alephinfinity1.forgeblock.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

/*
 * A placeholder item tier, as SkyBlock items do not have traditional vanilla tiers.
 */
public class FBItemTier implements IItemTier {
	
	private final int harvestLevel;

	public FBItemTier() {
		harvestLevel = 0;
	}
	
	public FBItemTier(int harvestLevel) {
		this.harvestLevel = harvestLevel;
	}

	@Override
	public float getAttackDamage() {
		
		return 0;
	}

	@Override
	public float getEfficiency() {
		return 0;
	}

	@Override
	public int getEnchantability() {
		
		return 0;
	}

	@Override
	public int getHarvestLevel() {
		return harvestLevel;
	}

	@Override
	public int getMaxUses() {
		
		return 0;
	}

	@Override
	public Ingredient getRepairMaterial() {
		
		return null;
	}

}
