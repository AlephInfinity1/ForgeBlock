package alephinfinity1.forgeblock.client;

import alephinfinity1.forgeblock.ForgeBlock;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.entity.layers.HeadLayer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;

public class TribeMemberRender<T extends MobEntity, M extends BipedModel<T>> extends MobRenderer<T, M> {
    //private static final ResourceLocation DEFAULT_RES_LOC = new ResourceLocation("textures/entity/steve.png");
    private static final ResourceLocation tribeTexture = new ResourceLocation(ForgeBlock.MOD_ID, "textures/entity/kalhuiki_tribe_member.png");

    public TribeMemberRender(EntityRendererManager renderManagerIn, M modelBipedIn, float shadowSize) {
        super(renderManagerIn, modelBipedIn, shadowSize);
        this.addLayer(new HeadLayer<>(this));
        this.addLayer(new ElytraLayer<>(this));
        this.addLayer(new HeldItemLayer<>(this));
        this.addLayer(new BipedArmorLayer<>(this, new BipedModel<>(0.5F), new BipedModel<>(1.0F)));
   }


    public ResourceLocation getEntityTexture(T entity) {
        return tribeTexture;
    }
}