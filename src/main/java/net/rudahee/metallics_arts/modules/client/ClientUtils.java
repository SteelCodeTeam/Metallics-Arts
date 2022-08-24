package net.rudahee.metallics_arts.modules.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.rudahee.metallics_arts.data.network.UpdateBurnPacket;
import net.rudahee.metallics_arts.data.network.UpdateDecantPacket;
import net.rudahee.metallics_arts.data.network.UpdateStoragePacket;
import net.rudahee.metallics_arts.modules.data_player.IDefaultInvestedPlayerData;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.enums.metals.Metal;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class ClientUtils {

    @Nullable
    public static RayTraceResult getMouseOverExtended(float dist) {
        Minecraft mc = Minecraft.getInstance();
        float partialTicks = mc.getFrameTime();
        RayTraceResult objectMouseOver = null;
        Entity entity = mc.getCameraEntity();

        if (entity != null) {
            if (mc.level != null) {
                objectMouseOver = entity.pick(dist, partialTicks, false);
                Vector3d vec3d = entity.getEyePosition(partialTicks);
                boolean flag = false;
                int i = 3;
                double d1;

                d1 = objectMouseOver.getLocation().distanceToSqr(vec3d);

                Vector3d vec3d1 = entity.getViewVector(1.0F);
                Vector3d vec3d2 = vec3d.add(vec3d1.x * dist, vec3d1.y * dist, vec3d1.z * dist);
                float f = 1.0F;
                AxisAlignedBB axisalignedbb = entity.getBoundingBox().expandTowards(vec3d1.scale(dist)).inflate(1.0D, 1.0D, 1.0D);
                EntityRayTraceResult entityraytraceresult = ProjectileHelper.getEntityHitResult(entity, vec3d, vec3d2, axisalignedbb, (e) -> true, d1);
                if (entityraytraceresult != null) {
                    Entity entity1 = entityraytraceresult.getEntity();
                    Vector3d vec3d3 = entityraytraceresult.getLocation();
                    double d2 = vec3d.distanceToSqr(vec3d3);
                    if (d2 < d1) {
                        objectMouseOver = entityraytraceresult;
                    }
                }
            }
        }
        return objectMouseOver;
    }

    public static void drawMetalLine(Vector3d player, Vector3d dest, float width, float r, float g, float b) {
        RenderSystem.lineWidth(width);

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuilder();

        buffer.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION_COLOR);
        buffer.vertex(player.x(), player.y(), player.z()).color(r, g, b, 0.8f).endVertex();
        buffer.vertex(dest.x(), dest.y(), dest.z()).color(r, g, b, 0.8f).endVertex();
        tessellator.end();

    }

    public static void toggleBurn(MetalsNBTData  metal, IDefaultInvestedPlayerData capability) {
        if (!capability.hasAllomanticPower(metal)) {
            return;
        }

        ModNetwork.sendToServer(new UpdateBurnPacket(metal, !capability.isBurning(metal)));

        if (capability.getAllomanticAmount(metal) > 0) {
            capability.setBurning(metal, !capability.isBurning(metal));
        }

        // play a sound effect

        /*if (capability.isBurning(metal)) {
            player.playSound(new SoundEvent(new ResourceLocation("item.flintandsteel.use")), 1, 5);
        } else {
            player.playSound(new SoundEvent(new ResourceLocation("block.fire.extinguish")), 1, 4);
        }*/
    }

    public static  void toggleDecant (MetalsNBTData metal, IDefaultInvestedPlayerData capability ){
        if (!capability.hasFeruchemicPower(metal)||!capability.getMetalMindEquiped(metal.getGroup())){
            return;
        }

        if (capability.isStoring(metal)) {
            ModNetwork.sendToServer(new UpdateStoragePacket(metal, false));
        }
        ModNetwork.sendToServer(new UpdateDecantPacket(metal, !capability.isDecanting(metal)));

    }

    public static  void  toggleStorage (MetalsNBTData metal, IDefaultInvestedPlayerData capability ){
        if (!capability.hasFeruchemicPower(metal)||!capability.getMetalMindEquiped(metal.getGroup())){
            return;
        }
        if (capability.isDecanting(metal)) {
            ModNetwork.sendToServer(new UpdateDecantPacket(metal, false));
        }
        ModNetwork.sendToServer(new UpdateStoragePacket(metal, !capability.isStoring(metal)));
    }
}
