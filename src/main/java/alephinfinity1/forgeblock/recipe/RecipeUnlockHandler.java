package alephinfinity1.forgeblock.recipe;

import java.util.Collection;
import java.util.List;

import alephinfinity1.forgeblock.ForgeBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.GameRules;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/*
 * Unlocks all vanilla recipes on game login. This is because the game would restrict mod-specific recipes to collections.
 */
@Mod.EventBusSubscriber
public class RecipeUnlockHandler {

	@SubscribeEvent
	public static void onPlayerLogin(PlayerLoggedInEvent event) {
		if(!event.getPlayer().getEntityWorld().isRemote()) {
			Collection<IRecipe<?>> recipes = event.getPlayer().getEntityWorld().getRecipeManager().getRecipes();
			PlayerEntity player = event.getPlayer();
			for(IRecipe<?> recipe : recipes) {
				if(recipe.getId().getNamespace().equals(ForgeBlock.MINECRAFT_ID)) {
					player.unlockRecipes(List.of(recipe));
				}
			}
			event.getPlayer().getEntityWorld().getGameRules().get(GameRules.DO_LIMITED_CRAFTING).set(true, event.getPlayer().getServer());
		}
	}
}
