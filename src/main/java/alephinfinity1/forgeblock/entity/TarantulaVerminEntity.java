package alephinfinity1.forgeblock.entity;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.world.World;

public class TarantulaVerminEntity extends DasherSpiderEntity {

	public TarantulaVerminEntity(EntityType<? extends SpiderEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.registerFBAttributes();
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)0.65F);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(54000.0D);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(360.0D);
		this.getAttribute(FBAttributes.CRIT_CHANCE).setBaseValue(0.0D);
	}
	
	@Override
	public int getLevel() {
		return 120;
	}

	@Override
	public double getCoins() {
		return 0D;
	}

	@Override
	public double getCombatXP() {
		return 150;
	}

}
