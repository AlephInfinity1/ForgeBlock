package alephinfinity1.forgeblock.entity;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.world.World;

public class SpecialZealotEntity extends ZealotEntity {

	public SpecialZealotEntity(EntityType<? extends EndermanEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.registerFBAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(2000.0D);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1250.0D);
		this.getAttribute(FBAttributes.CRIT_CHANCE).setBaseValue(0.0D);
	}
	
	@Override
	public void tick() {
		super.tick();
		if(this.ticksExisted % 5 == 0) {
			if(this.getHeldBlockState() == null)
				this.setHeldBlockState(Blocks.END_PORTAL_FRAME.getDefaultState());
			if(this.getHeldBlockState() != null)
				if(!this.getHeldBlockState().equals(Blocks.END_PORTAL_FRAME.getDefaultState()))
					this.setHeldBlockState(Blocks.END_PORTAL_FRAME.getDefaultState());
		}
	}
	
	@Override
	public int getLevel() {
		return 55;
	}

}
