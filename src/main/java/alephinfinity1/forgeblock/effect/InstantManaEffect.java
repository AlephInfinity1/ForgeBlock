package alephinfinity1.forgeblock.effect;

import javax.annotation.Nullable;

import alephinfinity1.forgeblock.attribute.FBAttributes;
import alephinfinity1.forgeblock.misc.capability.mana.IMana;
import alephinfinity1.forgeblock.misc.capability.mana.ManaProvider;
import alephinfinity1.forgeblock.network.FBPacketHandler;
import alephinfinity1.forgeblock.network.ManaUpdatePacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.InstantEffect;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.network.PacketDistributor;

public class InstantManaEffect extends InstantEffect {

	public InstantManaEffect(EffectType p_i50392_1_, int p_i50392_2_) {
		super(p_i50392_1_, p_i50392_2_);
	}
	
	@Override
	public void performEffect(LivingEntity living, int amplifier) {
		this.affectEntity(null, null, living, amplifier, 1.0D);
	}
	
	@SuppressWarnings("resource")
	@Override
	public void affectEntity(@Nullable Entity source, @Nullable Entity indirectSource, LivingEntity entityLivingBaseIn, int amplifier, double health) {
		if(!(entityLivingBaseIn instanceof PlayerEntity)) return; //Should only affect players as only they have mana.
		if(entityLivingBaseIn.getEntityWorld().isRemote) return; //Should only run on server side. 
		PlayerEntity player = (PlayerEntity) entityLivingBaseIn;
		IMana mana = player.getCapability(ManaProvider.MANA_CAPABILITY).orElseThrow(NullPointerException::new);
		double manaAmount;
		switch(amplifier) {
		case 0:
			manaAmount = 25;
			break;
		case 1:
			manaAmount = 50;
			break;
		case 2:
			manaAmount = 75;
			break;
		case 3:
			manaAmount = 100;
			break;
		case 4:
			manaAmount = 150;
			break;
		case 5:
			manaAmount = 200;
			break;
		case 6:
			manaAmount = 300;
			break;
		case 7:
			manaAmount = 400;
			break;
		default:
			manaAmount = (amplifier - 3) * 100;
		}
		
		manaAmount *= health; //Mana given is affected by vicinity if splash/lingering potion.
		
		//Mana granted by potions cannot exceed 2 times player's max mana. Check is after vicinity check.
		manaAmount = MathHelper.clamp(manaAmount, 0.0D, (player.getAttribute(FBAttributes.INTELLIGENCE).getValue() + 100) * 2 - mana.getMana());
		
		mana.increase(manaAmount);
		FBPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new ManaUpdatePacket(mana.getMana()));
	}

}
