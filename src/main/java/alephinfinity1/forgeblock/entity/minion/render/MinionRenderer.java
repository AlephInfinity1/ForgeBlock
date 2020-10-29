package alephinfinity1.forgeblock.entity.minion.render;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.entity.minion.MinionEntity;
import alephinfinity1.forgeblock.entity.minion.model.MinionModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class MinionRenderer extends MobRenderer<MinionEntity, MinionModel<MinionEntity>> {

  private static final ResourceLocation MINION_TEXTURES = new ResourceLocation(ForgeBlock.MOD_ID,
      "textures/entity/enderman/enderman.png");

  public MinionRenderer(EntityRendererManager renderManagerIn) {
    super(renderManagerIn, new MinionModel<>(), 0.7F);
  }

  @Override
  public ResourceLocation getEntityTexture(MinionEntity entity) {
    return MINION_TEXTURES;
  }
}
