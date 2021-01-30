package alephinfinity1.forgeblock.entity;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.attribute.FBAttributes;
import alephinfinity1.forgeblock.init.ModItems;
import alephinfinity1.forgeblock.item.FBArmorItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.ZombieVillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class Lv1ZombieVillagerEntity extends ZombieVillagerEntity implements IFBEntity {

	public Lv1ZombieVillagerEntity(EntityType<? extends ZombieVillagerEntity> p_i50186_1_, World p_i50186_2_) {
		super(p_i50186_1_, p_i50186_2_);
		this.setChild(false);
	}
	
	/**
	 * Copied from {@link MobEntity#processInteract} in order to prevent curing.
	 */
	@Override
	public boolean processInteract(PlayerEntity player, Hand hand) {
		return false;
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.registerFBAttributes();
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)0.3F);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(120.0D);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(24.0D);
		this.getAttribute(FBAttributes.CRIT_CHANCE).setBaseValue(0.0D);
		this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.0D);
	}
	
	@Override
	protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
		this.setItemStackToSlot(EquipmentSlotType.HEAD, ((FBArmorItem) (ModItems.LEATHER_HELMET.get())).getDisplayStack());
		this.setItemStackToSlot(EquipmentSlotType.CHEST, ((FBArmorItem) (ModItems.LEATHER_CHESTPLATE.get())).getDisplayStack());
		this.setItemStackToSlot(EquipmentSlotType.LEGS, ((FBArmorItem) (ModItems.LEATHER_LEGGINGS.get())).getDisplayStack());
		this.setItemStackToSlot(EquipmentSlotType.FEET, ((FBArmorItem) (ModItems.LEATHER_BOOTS.get())).getDisplayStack());
	}
	
	@Override
	public ResourceLocation getLootTable() {
		return new ResourceLocation(ForgeBlock.MOD_ID, "lv1_zombie");
	}

	@Override
	public int getLevel() {
		return 1;
	}

	@Override
	public double getCoins() {
		return 1;
	}

	@Override
	public double getCombatXP() {
		return 7;
	}

}
