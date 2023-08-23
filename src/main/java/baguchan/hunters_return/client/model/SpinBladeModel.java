package baguchan.hunters_return.client.model;// Made with Blockbench 4.8.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class SpinBladeModel<T extends Entity> extends EntityModel<T> {
    private final ModelPart spinblade;

    public SpinBladeModel(ModelPart root) {
        this.spinblade = root.getChild("spinblade");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition spinblade = partdefinition.addOrReplaceChild("spinblade", CubeListBuilder.create().texOffs(21, 23).addBox(-0.5F, -6.5F, 3.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(21, 23).addBox(-0.5F, 4.5F, -4.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(20, 5).addBox(-0.5F, -4.5F, 1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(20, 14).addBox(-0.5F, -4.5F, -4.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(21, 27).addBox(-0.5F, -3.5F, -5.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(4, 15).addBox(-0.5F, 1.5F, -4.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(21, 27).addBox(-0.5F, -5.5F, 2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(21, 27).addBox(-0.5F, 4.5F, -3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(2, 2).addBox(-0.5F, -2.5F, -2.5F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(4, 23).addBox(-0.5F, 1.5F, 1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition spinblade_r1 = spinblade.addOrReplaceChild("spinblade_r1", CubeListBuilder.create().texOffs(21, 27).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(21, 23).addBox(-0.5F, -1.5F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.5F, 5.0F, -1.5708F, 0.0F, 0.0F));

        PartDefinition spinblade_r2 = spinblade.addOrReplaceChild("spinblade_r2", CubeListBuilder.create().texOffs(21, 23).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, -5.0F, -1.5708F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        spinblade.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}