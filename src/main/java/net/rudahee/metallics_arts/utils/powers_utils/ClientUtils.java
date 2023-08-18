package net.rudahee.metallics_arts.utils.powers_utils;

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
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.data.IInvestedPlayerData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.network.packets.UpdateBurnPacket;
import net.rudahee.metallics_arts.setup.network.packets.UpdateStoragePacket;
import net.rudahee.metallics_arts.setup.network.packets.UpdateTapPacket;
import top.theillusivec4.curios.api.CuriosApi;

import javax.annotation.Nullable;

/**
 * A utility class containing client-side-only methods and functionality.
 *
 * This class should only be used in a client-side environment, as specified by the @OnlyIn(Dist.CLIENT) annotation.
 * Including methods and functionality specific to rendering, user input, powers controls like tap or burn metals
 * and any other client-side interactions.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
@OnlyIn(Dist.CLIENT)
public class ClientUtils {

    private static int actualFeruchemicReserve = -1;
    private static boolean isBand = false;


    /**
     * Returns an extended HitResult based on the player's current mouse position, considering a custom distance.
     * Useful for retrieving information about the entity or block the player is looking at beyond the default range.
     *
     * @param dist The custom distance for the extended mouse-over check.
     *
     * @return A HitResult object containing information about the targeted entity or block, or null if nothing is targeted.
     */
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


    /**
     * Toggles the burning state of a specific metal for an invested player.
     * If the player has the Allomantic power for the given metal and a non-zero amount of it,
     * this method sends an UpdateBurnPacket to the server and updates the burning state.
     *
     * @param metal The MetalTagEnum representing the metal to toggle the burning state for.
     * @param capability The IInvestedPlayerData object representing the invested player's data.
     */
    public static void toggleBurn(MetalTagEnum metal, IInvestedPlayerData capability) {
        if (!capability.hasAllomanticPower(metal) || capability.getAllomanticAmount(metal) == 0) {
            return;
        }
        ModNetwork.sendToServer(new UpdateBurnPacket(metal, !capability.isBurning(metal)));
        if (capability.getAllomanticAmount(metal) > 0) {
            capability.setBurning(metal, !capability.isBurning(metal));
        }
    }


    /**
     * Toggles the tapping state of a specific metal for an invested player.
     * If the player has the Feruchemic power for the given metal and has a MetalMind equipped for the metal's group,
     * this method sends an UpdateTapPacket to the server and updates the tapping state.
     *
     * @param metal The MetalTagEnum representing the metal to toggle the tapping state for.
     * @param capability The IInvestedPlayerData object representing the invested player's data.
     * @param player The Player object representing the player who is trying to toggle the tapping state.
     */
    public static void toggleTap(MetalTagEnum metal, IInvestedPlayerData capability, Player player) {
        if (!capability.hasFeruchemicPower(metal)||!capability.hasMetalMindEquiped(metal.getGroup())) {
            return;
        }

        CuriosApi.getCuriosHelper().getEquippedCurios(player).ifPresent(curioData -> {
            for (int i=0; i < curioData.getSlots(); i++) {

                if (curioData.getStackInSlot(i).getItem().getDescriptionId().toLowerCase().contains(metal.getNameLower())) {
                    if (curioData.getStackInSlot(i).hasTag()) {
                        actualFeruchemicReserve = curioData.getStackInSlot(i).getTag().getInt(metal.getNameLower() + "_feruchemic_reserve");
                    }
                    isBand = curioData.getStackInSlot(i).getItem().toString().toLowerCase().contains("band");
                }
            }
        });


        if (actualFeruchemicReserve <= 0) {
            ModNetwork.sendToServer(new UpdateTapPacket(metal, false));
        } else {
            if (capability.isStoring(metal)) {
                ModNetwork.sendToServer(new UpdateStoragePacket(metal, false));
            }
            ModNetwork.sendToServer(new UpdateTapPacket(metal, !capability.isTapping(metal)));
        }

    }

    /**
     * Toggles the storage state of a specific metal for an invested player.
     * If the player has the Feruchemic power for the given metal and has a MetalMind equipped for the metal's group,
     * this method sends an UpdateStoragePacket to the server and updates the storage state.
     *
     * @param metal The MetalTagEnum representing the metal to toggle the storage state for.
     * @param capability The IInvestedPlayerData object representing the invested player's data.
     * @param player The Player object representing the player who is trying to toggle the storage state.
     */
    public static void toggleStorage(MetalTagEnum metal, IInvestedPlayerData capability, Player player) {
        if (!capability.hasFeruchemicPower(metal)||!capability.hasMetalMindEquiped(metal.getGroup())) {
            return;
        }
        CuriosApi.getCuriosHelper().getEquippedCurios(player).ifPresent(curioData -> {
            for (int i=0; i < curioData.getSlots(); i++) {

                if (curioData.getStackInSlot(i).getItem().getDescriptionId().toLowerCase().contains(metal.getNameLower())) {

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
                if (capability.isTapping(metal)) {
                    ModNetwork.sendToServer(new UpdateTapPacket(metal, false));
                }
                ModNetwork.sendToServer(new UpdateStoragePacket(metal, !capability.isStoring(metal)));
            }
        } else {
            if (actualFeruchemicReserve >= metal.getMaxReserveRing()) {
                ModNetwork.sendToServer(new UpdateStoragePacket(metal, false));
            } else {
                if (capability.isTapping(metal)) {
                    ModNetwork.sendToServer(new UpdateTapPacket(metal, false));
                }
                ModNetwork.sendToServer(new UpdateStoragePacket(metal, !capability.isStoring(metal)));
            }
        }
    }
}
