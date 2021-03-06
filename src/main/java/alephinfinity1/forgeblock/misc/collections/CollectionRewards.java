package alephinfinity1.forgeblock.misc.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import alephinfinity1.forgeblock.misc.capability.skills.SkillType;
import alephinfinity1.forgeblock.misc.capability.skills.SkillsHelper;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class CollectionRewards {
	
	private final @Nullable List<ResourceLocation> recipeRewards;
	private final @Nullable Map<SkillType, Double> skillXPRewards;
	private final @Nullable List<ItemStack> itemRewards;
	
	public CollectionRewards(List<ResourceLocation> recipeRewards) {
		this.recipeRewards = recipeRewards;
		this.skillXPRewards = null;
		this.itemRewards = null;
	}
	
	public CollectionRewards(ResourceLocation recipeReward) {
		List<ResourceLocation> list = new ArrayList<>();
		list.add(recipeReward);
		this.recipeRewards = list;
		this.skillXPRewards = null;
		this.itemRewards = null;
	}
	
	public CollectionRewards(Map<SkillType, Double> skillXPRewards) {
		this.recipeRewards = null;
		this.skillXPRewards = skillXPRewards;
		this.itemRewards = null;
	}
	
	public CollectionRewards(SkillType type, double amount) {
		this.recipeRewards = null;
		this.skillXPRewards = new HashMap<>();
		skillXPRewards.put(type, amount);
		this.itemRewards = null;
	}
	
	public CollectionRewards(ItemStack stack) {
		this.recipeRewards = null;
		this.skillXPRewards = null;
		this.itemRewards = new ArrayList<>();
		itemRewards.add(stack);
	}
	
	public CollectionRewards(List<ResourceLocation> recipeRewards, Map<SkillType, Double> skillXPRewards, List<ItemStack> itemRewards) {
		this.recipeRewards = recipeRewards;
		this.skillXPRewards = skillXPRewards;
		this.itemRewards = itemRewards;
	}
	
	public void applyReward(PlayerEntity player) {
		if (player.isServerWorld()) {
			if (recipeRewards != null) {
				for (ResourceLocation recipeReward : recipeRewards) {
					player.unlockRecipes(new ResourceLocation[] {recipeReward});
				}
			}
			
			if (skillXPRewards != null) {
				for (Map.Entry<SkillType, Double> entry : skillXPRewards.entrySet()) {
					SkillsHelper.addXPAndUpdate(player, entry.getKey(), entry.getValue());	
				}
			}
			
			if (itemRewards != null) {
				for (ItemStack stack : itemRewards) {
					boolean flag = player.addItemStackToInventory(stack);
					if (!flag) {
						ItemEntity item = player.dropItem(stack, false);
						if (item != null) {
							item.setNoPickupDelay();
							item.setOwnerId(player.getUniqueID());
						}
					}
				}
			}
		}
	}
	
}
