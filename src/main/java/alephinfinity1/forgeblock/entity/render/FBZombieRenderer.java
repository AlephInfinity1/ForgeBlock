package alephinfinity1.forgeblock.entity.render;

import alephinfinity1.forgeblock.entity.Lv1ZombieEntity;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.ZombieModel;

public class FBZombieRenderer extends AbstractZombieRenderer<Lv1ZombieEntity, ZombieModel<Lv1ZombieEntity>> {

	public FBZombieRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new ZombieModel<>(0.0F, false), new ZombieModel<>(0.5F, true), new ZombieModel<>(1.0F, true));
	}

}
