package alephinfinity1.forgeblock.misc.drops;

import java.util.ArrayList;
import java.util.List;

import alephinfinity1.forgeblock.init.ModItems;
import alephinfinity1.forgeblock.misc.drops.DropData.DropType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class LapisZombieDrops {
	
	private static List<DropData> drops = null;
	
	public static void initialize() {
		drops = new ArrayList<>();
		drops.add(new DropData(new ItemStack(Items.ROTTEN_FLESH), DropType.COMMON, 1.0f, 2));
		drops.add(new DropData(new ItemStack(ModItems.LAPIS_HELMET.get()), DropType.RARE_ARMOR, 0.005f));
		drops.add(new DropData(new ItemStack(ModItems.LAPIS_CHESTPLATE.get()), DropType.RARE_ARMOR, 0.005f));
		drops.add(new DropData(new ItemStack(ModItems.LAPIS_LEGGINGS.get()), DropType.RARE_ARMOR, 0.005f));
		drops.add(new DropData(new ItemStack(ModItems.LAPIS_BOOTS.get()), DropType.RARE_ARMOR, 0.005f));
	}

	public static List<DropData> getDrops() {
		return drops;
	}
	
	public static List<ItemStack> drop(PlayerEntity player) {
		List<ItemStack> droppedItems = new ArrayList<>();
		for(DropData drop : drops) {
			droppedItems.add(drop.drop(player, true));
		}
		return droppedItems;
	}
	
	@SubscribeEvent
	public static void onLivingDrops(LivingDropsEvent event) {
		if(event.getEntityLiving() instanceof ZombieEntity) {
			if(event.getSource().getTrueSource() != null) {
				if(event.getSource().getTrueSource() instanceof PlayerEntity) {
					PlayerEntity player = (PlayerEntity) event.getSource().getTrueSource();
					World world = event.getDrops().iterator().next().getEntityWorld();
					double x = event.getDrops().iterator().next().getPosX();
					double y = event.getDrops().iterator().next().getPosY();
					double z = event.getDrops().iterator().next().getPosZ();
					
					List<ItemStack> droppedItems = drop(player);
					event.getDrops().clear();
					for(ItemStack stack : droppedItems) {
						event.getDrops().add(new ItemEntity(world, x, y, z, stack));
					}
				}
			}
		}
	}

}
