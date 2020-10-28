package alephinfinity1.forgeblock.entity.minion.model;

import alephinfinity1.forgeblock.entity.minion.MinionEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class MinionModel<T extends MinionEntity> extends EntityModel<T> {
    private final ModelRenderer bone6;
    private final ModelRenderer bone;
    private final ModelRenderer bone5;
    private final ModelRenderer bone4;
    private final ModelRenderer bone3;
    private final ModelRenderer bone2;

    public MinionModel() {
        textureWidth = 32;
        textureHeight = 32;

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(0.0F, 24.0F, -1.0F);


        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, -4.0F, 2.0F);
        bone6.addChild(bone);
        bone.setTextureOffset(19, 5).addBox(-1.0F, 3.0F, -2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        bone.setTextureOffset(5, 19).addBox(-1.0F, 2.0F, -2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        bone.setTextureOffset(15, 17).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        bone.setTextureOffset(18, 14).addBox(-1.0F, 1.0F, -2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        bone.setTextureOffset(5, 16).addBox(-1.0F, -1.0F, -2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        bone.setTextureOffset(19, 19).addBox(-1.0F, 3.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        bone.setTextureOffset(0, 19).addBox(-1.0F, 2.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        bone.setTextureOffset(10, 17).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        bone.setTextureOffset(17, 11).addBox(-1.0F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        bone.setTextureOffset(16, 8).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(0.0F, -10.0F, -1.0F);
        bone6.addChild(bone5);
        bone5.setTextureOffset(0, 9).addBox(-1.0F, -5.0F, 2.0F, 1.0F, 1.0F, 1.0F, 1.0F, false);

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(0.0F, -10.0F, -1.0F);
        bone6.addChild(bone4);
        bone4.setTextureOffset(0, 0).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 7.0F, 0.0F, false);
        bone4.setTextureOffset(0, 0).addBox(-1.0F, -4.0F, 2.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(0.0F, -10.0F, 2.0F);
        bone6.addChild(bone3);
        bone3.setTextureOffset(0, 16).addBox(-1.0F, 3.0F, -2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        bone3.setTextureOffset(15, 3).addBox(-1.0F, 2.0F, -2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        bone3.setTextureOffset(15, 0).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        bone3.setTextureOffset(13, 14).addBox(-1.0F, 1.0F, -2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        bone3.setTextureOffset(12, 11).addBox(-1.0F, 3.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        bone3.setTextureOffset(10, 3).addBox(-1.0F, 2.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        bone3.setTextureOffset(10, 0).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        bone3.setTextureOffset(8, 9).addBox(-1.0F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(0.0F, -2.0F, 0.0F);
        bone6.addChild(bone2);
        bone2.setTextureOffset(0, 9).addBox(-1.0F, -4.0F, -1.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }


    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        bone6.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
