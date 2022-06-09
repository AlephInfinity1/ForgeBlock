package alephinfinity1.forgeblock.entity;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.attribute.FBAttributes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class SvenFollowerEntity extends Lv15WolfEntity {

	public SvenFollowerEntity(EntityType<? extends WolfEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.registerFBAttributes();
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)0.45F);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(120000.0D);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1200.0D);
		this.getAttribute(FBAttributes.CRIT_CHANCE).setBaseValue(0.0D);
	}
	
	@Override
	public ResourceLocation getLootTable() {
		return new ResourceLocation(ForgeBlock.MOD_ID, "old_wolf");
	}
	
	@Override
	public int getLevel() {
		return 170;
	}

	@Override
	public double getCoins() {
		return 0D;
	}

	@Override
	public double getCombatXP() {
		return 250;
	}

}
