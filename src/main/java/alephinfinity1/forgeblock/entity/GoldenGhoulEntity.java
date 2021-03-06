package alephinfinity1.forgeblock.entity;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.attribute.FBAttributes;
import alephinfinity1.forgeblock.init.ModItems;
import alephinfinity1.forgeblock.item.armor.FBArmorItem;
import alephinfinity1.forgeblock.item.swords.FBSwordItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class GoldenGhoulEntity extends Lv1ZombieEntity {
	
	public GoldenGhoulEntity(EntityType<? extends ZombieEntity> type, World worldIn) {
		super(type, worldIn);
		this.setChild(false);
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.registerFBAttributes();
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.185);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(45000.0);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(800.0);
		this.getAttribute(FBAttributes.CRIT_CHANCE).setBaseValue(0.0);
		this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.0);
	}
	
	@Override
	public ResourceLocation getLootTable() {
		return new ResourceLocation(ForgeBlock.MOD_ID, "golden_ghoul");
	}
	
	@Override
	protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
		this.setItemStackToSlot(EquipmentSlotType.CHEST, ((FBArmorItem) (ModItems.GOLDEN_CHESTPLATE.get())).getDisplayStack());
		this.setItemStackToSlot(EquipmentSlotType.LEGS, ((FBArmorItem) (ModItems.GOLDEN_LEGGINGS.get())).getDisplayStack());
		this.setItemStackToSlot(EquipmentSlotType.FEET, ((FBArmorItem) (ModItems.GOLDEN_BOOTS.get())).getDisplayStack());
		this.setItemStackToSlot(EquipmentSlotType.MAINHAND, ((FBSwordItem) (ModItems.GOLDEN_SWORD.get())).getDisplayStack());
	}
	
	@Override
	public int getLevel() {
		return 60;
	}

	@Override
	public double getCoins() {
		return 30D;
	}

	@Override
	public double getCombatXP() {
		return 50D;
	}

}
