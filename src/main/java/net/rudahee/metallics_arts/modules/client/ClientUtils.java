package net.rudahee.metallics_arts.modules.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.rudahee.metallics_arts.data.network.UpdateBurnPacket;
import net.rudahee.metallics_arts.data.network.UpdateDecantPacket;
import net.rudahee.metallics_arts.data.network.UpdateStoragePacket;
import net.rudahee.metallics_arts.modules.data_player.IDefaultInvestedPlayerData;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import top.theillusivec4.curios.api.CuriosApi;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class ClientUtils {

    @Nullable
    public static HitResult getMouseOverExtended(float dist) {
        Minecraft mc = Minecraft.getInstance();
        float partialTicks = mc.getFrameTime();
        HitResult objectMouseOver = null;
        Entity entity = mc.getCameraEntity();

        if (entity != null) {
            if (mc.level != null) {
                objectMouseOver = entity.pick(dist, partialTicks, false);
                Vec3 vec3d = entity.getEyePosition(partialTicks);
                boolean flag = false;
                int i = 3;
                double d1;

                d1 = objectMouseOver.getLocation().distanceToSqr(vec3d);

                Vec3 vec3d1 = entity.getViewVector(1.0F);
                Vec3 vec3d2 = vec3d.add(vec3d1.x * dist, vec3d1.y * dist, vec3d1.z * dist);
                float f = 1.0F;
                AABB axisalignedbb = entity.getBoundingBox().expandTowards(vec3d1.scale(dist)).inflate(1.0D, 1.0D, 1.0D);
                EntityHitResult entityraytraceresult = ProjectileUtil.getEntityHitResult(entity, vec3d, vec3d2, axisalignedbb, (e) -> true, d1);
                if (entityraytraceresult != null) {
                    Entity entity1 = entityraytraceresult.getEntity();
                    Vec3 vec3d3 = entityraytraceresult.getLocation();
                    double d2 = vec3d.distanceToSqr(vec3d3);
                    if (d2 < d1) {
                        objectMouseOver = entityraytraceresult;
                    }
                }
            }
        }
        return objectMouseOver;
    }

    public static void drawMetalLine(PoseStack stack, Vec3 player, Vec3 dest, float width, float r, float g, float b) {

        //        RenderSystem.lineWidth(width);
        Tesselator tessellator = Tesselator.getInstance();
        BufferBuilder builder = tessellator.getBuilder();

        builder.begin(VertexFormat.Mode.LINES, DefaultVertexFormat.POSITION_COLOR);
        Matrix4f matrix4f = stack.last().pose();
        builder.vertex(matrix4f, (float) player.x, (float) player.y, (float) player.z).color(r, g, b, 0.6f).endVertex();
        builder.vertex(matrix4f, (float) dest.x, (float) dest.y, (float) dest.z).color(r, g, b, 0.6f).endVertex();
        RenderSystem.lineWidth(width);

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

    private static int actualFeruchemicReserve = -1;
    private static boolean isBand = false;

    public static void toggleDecant(MetalsNBTData metal, IDefaultInvestedPlayerData capability, Player player){
        if (!capability.hasFeruchemicPower(metal)||!capability.getMetalMindEquiped(metal.getGroup())){
            return;
        }

        CuriosApi.getCuriosHelper().getEquippedCurios(player).ifPresent(curioData -> {
            for (int i=0; i < curioData.getSlots(); i++) {

                if (curioData.getStackInSlot(i).getItem().getDescriptionId().toLowerCase().contains(metal.getNameLower())){
                    if (curioData.getStackInSlot(i).hasTag()) {
                        actualFeruchemicReserve = curioData.getStackInSlot(i).getTag().getInt(metal.getNameLower() + "_feruchemic_reserve");
                    }
                    isBand = curioData.getStackInSlot(i).getItem().toString().toLowerCase().contains("band");
                }
            }
        });


        if (actualFeruchemicReserve <= 0) {
            ModNetwork.sendToServer(new UpdateDecantPacket(metal, false));
        } else {
            if (capability.isStoring(metal)) {
                ModNetwork.sendToServer(new UpdateStoragePacket(metal, false));
            }
            ModNetwork.sendToServer(new UpdateDecantPacket(metal, !capability.isDecanting(metal)));
        }

    }
    public static void toggleStorage(MetalsNBTData metal, IDefaultInvestedPlayerData capability, Player player){
        if (!capability.hasFeruchemicPower(metal)||!capability.getMetalMindEquiped(metal.getGroup())){
            return;
        }
        CuriosApi.getCuriosHelper().getEquippedCurios(player).ifPresent(curioData -> {
            for (int i=0; i < curioData.getSlots(); i++) {

                if (curioData.getStackInSlot(i).getItem().getDescriptionId().toLowerCase().contains(metal.getNameLower())){

                    if (curioData.getStackInSlot(i).hasTag()) {
                        actualFeruchemicReserve = curioData.getStackInSlot(i).getTag().getInt(metal.getNameLower() + "_feruchemic_reserve");
                    }
                    isBand = curioData.getStackInSlot(i).getItem().toString().toLowerCase().contains("band");
                }
            }
        });
        if (isBand) {
            if (actualFeruchemicReserve >= metal.getMaxReserveBand()) {
                ModNetwork.sendToServer(new UpdateStoragePacket(metal, false));
            } else {
                if (capability.isDecanting(metal)) {
                    ModNetwork.sendToServer(new UpdateDecantPacket(metal, false));
                }
                ModNetwork.sendToServer(new UpdateStoragePacket(metal, !capability.isStoring(metal)));
            }
        } else {
            if (actualFeruchemicReserve >= metal.getMaxReserveRing()) {
                ModNetwork.sendToServer(new UpdateStoragePacket(metal, false));
            } else {
                if (capability.isDecanting(metal)) {
                    ModNetwork.sendToServer(new UpdateDecantPacket(metal, false));
                }
                ModNetwork.sendToServer(new UpdateStoragePacket(metal, !capability.isStoring(metal)));
            }
        }
    }
}
