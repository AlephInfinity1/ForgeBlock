package alephinfinity1.forgeblock.item;

import java.util.List;

import alephinfinity1.forgeblock.item.armor.UnstableDragonArmorItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class UnstableDragonArmorHandler {

	@SubscribeEvent
	public static void onTick(PlayerTickEvent event) {
		if(event.phase == Phase.START && event.player instanceof ServerPlayerEntity) {
			
			PlayerEntity player = event.player;
			World world = player.getEntityWorld();
			Iterable<ItemStack> armor = player.getArmorInventoryList();
			for(ItemStack item : armor) {
				if(!(item.getItem() instanceof UnstableDragonArmorItem)) {
					return;
				}
			}
			if(Math.random() < 0.005f) {
				AxisAlignedBB bound = new AxisAlignedBB(player.getPosition().add(-10, -10, -10), player.getPosition().add(10, 10, 10));
				List<Entity> entities = world.getEntitiesInAABBexcluding(player, bound, EntityPredicates.NOT_SPECTATING);
				for(Entity entity : entities) {
					if(!(entity instanceof LivingEntity)) return;
					if(entity.getDistanceSq(player) > 49.0f) return;
					LightningBoltEntity thunder = new LightningBoltEntity(world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), true);
					if(world instanceof ServerWorld) {
						((ServerWorld) world).addLightningBolt(thunder);
					}
					entity.attackEntityFrom(DamageSource.causeIndirectMagicDamage(player, player), 3000.0f);
				}
			}
		}
	}
}
