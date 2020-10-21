package alephinfinity1.forgeblock.entity;

import alephinfinity1.forgeblock.ForgeBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ExperienceBottleEntity;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class FBExperienceBottleEntity extends ExperienceBottleEntity {
	
	private final int minXp;
	private final int maxXp;
	//private static final DataParameter<Integer> MIN_XP_PARAM = EntityDataManager.createKey(FBExperienceBottleEntity.class, DataSerializers.VARINT);
	//private static final DataParameter<Integer> MAX_XP_PARAM = EntityDataManager.createKey(FBExperienceBottleEntity.class, DataSerializers.VARINT);

	public FBExperienceBottleEntity(World worldIn, LivingEntity throwerIn) {
		super(worldIn, throwerIn);
		minXp = 3;
		maxXp = 11;
	}
	
	public FBExperienceBottleEntity(World worldIn, LivingEntity throwerIn, int minXp, int maxXp) {
		super(worldIn, throwerIn);
		this.minXp = minXp;
		this.maxXp = maxXp;
	}
	
	public FBExperienceBottleEntity(EntityType<? extends ExperienceBottleEntity> p_i50152_1_, World p_i50152_2_) {
		super(p_i50152_1_, p_i50152_2_);
		minXp = 3;
		maxXp = 11;
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (!this.world.isRemote) {
			this.world.playEvent(2002, new BlockPos(this), PotionUtils.getPotionColor(Potions.WATER));
			int i = this.getXp();
			ForgeBlock.LOGGER.debug("XP Value: " + Integer.toString(i));

			while(i > 0) {
				int j = ExperienceOrbEntity.getXPSplit(i);
				i -= j;
				this.world.addEntity(new ExperienceOrbEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ(), j));
			}

			this.remove();
		}

	}
	
	public int getXp() {
		//
		ForgeBlock.LOGGER.debug("Min XP Value: " + Integer.toString(minXp));
		ForgeBlock.LOGGER.debug("Max XP Value: " + Integer.toString(maxXp));
		return this.minXp + this.world.rand.nextInt((maxXp - minXp) / 2 + 1) + this.world.rand.nextInt((maxXp - minXp) / 2 + 1);
	}
	
	@Override
	protected Item getDefaultItem() {
		return Items.EXPERIENCE_BOTTLE;
	}

}
