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

public class DeformedRevenantEntity extends Lv1ZombieEntity {
	
	public DeformedRevenantEntity(EntityType<? extends ZombieEntity> type, World worldIn) {
		super(type, worldIn);
		this.setChild(false);
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.registerFBAttributes();
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.385);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(360000.0);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1600.0);
		this.getAttribute(FBAttributes.CRIT_CHANCE).setBaseValue(0.0);
		this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.0);
	}
	
	@Override
	public ResourceLocation getLootTable() {
		return new ResourceLocation(ForgeBlock.MOD_ID, "crypt_ghoul");
	}
	
	@Override
	protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
		this.setItemStackToSlot(EquipmentSlotType.CHEST, ((FBArmorItem) (ModItems.DIAMOND_CHESTPLATE.get())).getDisplayStack());
		this.setItemStackToSlot(EquipmentSlotType.LEGS, ((FBArmorItem) (ModItems.LEATHER_LEGGINGS.get())).getDisplayStack());
		this.setItemStackToSlot(EquipmentSlotType.FEET, ((FBArmorItem) (ModItems.DIAMOND_BOOTS.get())).getDisplayStack());
		this.setItemStackToSlot(EquipmentSlotType.HEAD, ((FBArmorItem) (ModItems.LEATHER_HELMET.get())).getDisplayStack());
		this.setItemStackToSlot(EquipmentSlotType.MAINHAND, ((FBSwordItem) (ModItems.GOLDEN_SWORD.get())).getDisplayStack());
	}
	
	@Override
	public int getLevel() {
		return 300;
	}

	@Override
	public double getCoins() {
		return 0D;
	}

	@Override
	public double getCombatXP() {
		return 1200D;
	}

}
