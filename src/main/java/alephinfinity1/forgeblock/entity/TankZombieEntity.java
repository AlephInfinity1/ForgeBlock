package alephinfinity1.forgeblock.entity;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.attribute.FBAttributes;
import alephinfinity1.forgeblock.init.ModItems;
import alephinfinity1.forgeblock.item.armor.FBArmorItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class TankZombieEntity extends Lv1ZombieEntity {
	
	public TankZombieEntity(EntityType<? extends ZombieEntity> type, World worldIn) {
		super(type, worldIn);
		this.setChild(false);
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.registerFBAttributes();
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.255);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1500.0);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(80.0);
		this.getAttribute(FBAttributes.DEFENSE).setBaseValue(1000.0);
		this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(19999.0);
	}
	
	@Override
	public ResourceLocation getLootTable() {
		return new ResourceLocation(ForgeBlock.MOD_ID, "lv1_zombie");
	}
	
	@Override
	protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
		this.setItemStackToSlot(EquipmentSlotType.CHEST, ((FBArmorItem) (ModItems.DIAMOND_CHESTPLATE.get())).getDisplayStack());
		this.setItemStackToSlot(EquipmentSlotType.LEGS, ((FBArmorItem) (ModItems.DIAMOND_LEGGINGS.get())).getDisplayStack());
		this.setItemStackToSlot(EquipmentSlotType.FEET, ((FBArmorItem) (ModItems.DIAMOND_BOOTS.get())).getDisplayStack());
        this.setItemStackToSlot(EquipmentSlotType.HEAD, ((FBArmorItem) (ModItems.DIAMOND_HELMET.get())).getDisplayStack());
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		this.playSound(SoundEvents.BLOCK_ANVIL_BREAK, 0.95F, 1.0F);
		return SoundEvents.ENTITY_ZOMBIE_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_ZOMBIE_DEATH;
	}
	
	@Override
	public int getLevel() {
		return 90;
	}

	@Override
	public double getCoins() {
		return 1D;
	}

	@Override
	public double getCombatXP() {
		return 84D;
	}

}
