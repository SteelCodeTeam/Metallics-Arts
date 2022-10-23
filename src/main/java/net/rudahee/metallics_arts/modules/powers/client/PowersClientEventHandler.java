package net.rudahee.metallics_arts.modules.powers.client;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.rudahee.metallics_arts.data.network.ChangeEmotionPacket;
import net.rudahee.metallics_arts.data.network.PullAndPushBlockPacket;
import net.rudahee.metallics_arts.data.network.PullAndPushEntityPacket;
import net.rudahee.metallics_arts.data.network.PullAndPushNuggetPacket;
import net.rudahee.metallics_arts.modules.client.ClientUtils;
import net.rudahee.metallics_arts.modules.client.GUI.AllomanticMetalSelector;
import net.rudahee.metallics_arts.modules.client.GUI.FeruchemyMetalSelector;
import net.rudahee.metallics_arts.modules.client.KeyInit;
import net.rudahee.metallics_arts.modules.data_player.IDefaultInvestedPlayerData;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.modules.powers.helpers.GoldAndElectrumHelpers;
import net.rudahee.metallics_arts.modules.powers.helpers.IronAndSteelHelpers;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModItems;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PowersClientEventHandler {

    private final Minecraft mc = Minecraft.getInstance();

    private final Set<Entity> metal_entities = new HashSet<>();
    private final Set<Player> nearby_allomancers = new HashSet<>();
    private final Set<MetalBlockHelpers> metal_blobs = new HashSet<>();
    private int tickOffset = 0;

    BlockPos otherPlayerDeathPos = null;

    ResourceKey<Level> otherPlayerDimension = null;

    private int controlTick = 0;

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onClientTick(final TickEvent.ClientTickEvent event) {

        if (event.phase != TickEvent.Phase.END || this.mc.isPaused() || this.mc.player == null || !this.mc.player.isAlive()) {
            return;
        }

        if (event.phase == TickEvent.Phase.END) {
            Player player = this.mc.player;

            if (player != null && player instanceof Player) {
                player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(
                        playerCapability -> {
                            if (playerCapability.isInvested()) {

                                this.redoLists(player, playerCapability);

                                this.tickOffset = (tickOffset + 1) % 2;

                                /** LEFT CLICK (ATTACK) */

                                if (this.mc.options.keyAttack.isDown()) {
                                    HitResult trace = ClientUtils.getMouseOverExtended(11F * 1.5F);

                                    /***********************************
                                     * DO CLICK AN ENTITY WITH  - ZINC -
                                     ***********************************/
                                    if (playerCapability.isBurning(MetalsNBTData.ZINC)) {
                                        Entity entity;
                                        if ((trace != null) && (trace.getType() == HitResult.Type.ENTITY)) {
                                            entity = ((EntityHitResult) trace).getEntity();
                                            if (entity instanceof Mob) {
                                                ModNetwork.sendToServer(new ChangeEmotionPacket(entity.getId(), true));
                                            }
                                        }
                                    }

                                    /***********************************
                                     * DO CLICK AN SOMETHING WITH  - IRON -
                                     ***********************************/
                                    if (playerCapability.isBurning(MetalsNBTData.IRON)) {
                                        if (trace !=null){
                                            if (trace instanceof BlockHitResult) { // IF ITS A BLOCK
                                                BlockPos blockPosition = ((BlockHitResult) trace).getBlockPos();
                                                if (IronAndSteelHelpers.isBlockStateMetal(this.mc.level.getBlockState(blockPosition))) {
                                                    ModNetwork.sendToServer(new PullAndPushBlockPacket(blockPosition,
                                                            Math.round(IronAndSteelHelpers.PULL * IronAndSteelHelpers.getMultiplier(player, playerCapability.isBurning(MetalsNBTData.DURALUMIN) || playerCapability.getExternalEnhanced(),
                                                                    playerCapability.isBurning(MetalsNBTData.LERASIUM)))));
                                                }
                                            }
                                            if (trace instanceof EntityHitResult) {
                                                ModNetwork.sendToServer(
                                                        new PullAndPushEntityPacket(((EntityHitResult) trace).getEntity().getId(),
                                                                Math.round(IronAndSteelHelpers.PULL * IronAndSteelHelpers.getMultiplier(player,playerCapability.isBurning(MetalsNBTData.DURALUMIN) || playerCapability.getExternalEnhanced(),
                                                                        playerCapability.isBurning(MetalsNBTData.LERASIUM)))));
                                            }
                                        }
                                    }
                                    /***********************************
                                     * DO CLICK AN ENTITY WITH  - MALATIUM -
                                     ***********************************/
                                    if (playerCapability.isBurning(MetalsNBTData.MALATIUM)) {
                                        Entity entity;
                                        if ((trace != null) && (trace.getType() == HitResult.Type.ENTITY)) {
                                            entity = ((EntityHitResult) trace).getEntity();
                                            if (entity instanceof Player) {
                                                Player otherPlayer = (Player) entity;
                                                otherPlayer.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(cap -> {
                                                    otherPlayerDeathPos = new BlockPos(cap.getDeathPos()[0],cap.getDeathPos()[1],cap.getDeathPos()[2]);
                                                    otherPlayerDimension = GoldAndElectrumHelpers.getRegistryKeyFromString(cap.getDeathDimension());
                                                });
                                            }
                                        }
                                    }
                                }
                                if (!playerCapability.isBurning(MetalsNBTData.MALATIUM) && otherPlayerDeathPos != null) {
                                    otherPlayerDeathPos = null;
                                    otherPlayerDimension = null;
                                }


                                /** RIGHT CLICK (USE) */

                                if (this.mc.options.keyUse.isDown()) {
                                    HitResult trace = ClientUtils.getMouseOverExtended(11F * 1.5F);

                                    /***********************************
                                     * DO CLICK AN ENTITY WITH  - BRASS -
                                     ***********************************/
                                    if (playerCapability.isBurning(MetalsNBTData.BRASS)) {
                                        Entity entity;
                                        if ((trace != null) && (trace.getType() == HitResult.Type.ENTITY)) {
                                            entity = ((EntityHitResult) trace).getEntity();
                                            if (entity instanceof Mob) {
                                                ModNetwork.sendToServer(new ChangeEmotionPacket(entity.getId(), false));
                                            }
                                        }
                                    }

                                    /***********************************
                                     * DO CLICK AN ENTITY WITH  - STEEL -
                                     ***********************************/

                                    if (playerCapability.isBurning(MetalsNBTData.STEEL)) {
                                        if (trace !=null){
                                            if (trace instanceof BlockHitResult) { // IF ITS A BLOCK
                                                BlockPos blockPosition = ((BlockHitResult) trace).getBlockPos();
                                                if (IronAndSteelHelpers.isBlockStateMetal(this.mc.level.getBlockState(blockPosition))) {
                                                    ModNetwork.sendToServer(new PullAndPushBlockPacket(blockPosition,
                                                            Math.round(IronAndSteelHelpers.PUSH * IronAndSteelHelpers.getMultiplier(player,playerCapability.isBurning(MetalsNBTData.DURALUMIN) || playerCapability.getExternalEnhanced(),
                                                                    playerCapability.isBurning(MetalsNBTData.LERASIUM)))));
                                                }
                                            }
                                            if (trace instanceof EntityHitResult) {
                                                ModNetwork.sendToServer(
                                                        new PullAndPushEntityPacket(((EntityHitResult) trace).getEntity().getId(),
                                                                Math.round(IronAndSteelHelpers.PUSH * IronAndSteelHelpers.getMultiplier(player,playerCapability.isBurning(MetalsNBTData.DURALUMIN) || playerCapability.getExternalEnhanced(),
                                                                        playerCapability.isBurning(MetalsNBTData.LERASIUM)))));
                                            }
                                        }
                                    }
                                }

                                /*if (player.isFallFlying() && playerCapability.isBurning(MetalsNBTData.STEEL)) {
                                    player.setPose(Pose.FALL_FLYING);
                                }
                                if (!player.level.getBlockState(new BlockPos(player.position().x, player.position().y - 1, player.position().z)).is(Blocks.AIR)) {
                                    player.stopFallFlying();
                                }
                                if(!playerCapability.isBurning(MetalsNBTData.STEEL)) {
                                    player.stopFallFlying();
                                }*/

                                //if (this.mc.options.keyJump.isDown() && this.mc.options.keyShift.isDown() && playerCapability.isBurning(MetalsNBTData.STEEL)) {
                                if (this.mc.options.keyJump.isDown() && KeyInit.VERTICAL_JUMP.isDown() && playerCapability.isBurning(MetalsNBTData.STEEL)) {

                                    double x = player.getX();
                                    double y = player.getY();
                                    double z = player.getZ();

                                    BlockPos blockPos = new BlockPos(x,y,z);
                                    Vec3 vector = player.getViewVector(1.0F);

                                    double pushX;
                                    double pushZ;

                                    int maxAltitude = 10;
                                    int range = 7;

                                    if (this.mc.options.keyUp.isDown()) {
                                        pushX = x - (vector.x*range);
                                        pushZ = z - (vector.z*range);
                                    } else if (this.mc.options.keyDown.isDown()) {
                                        pushX = x + (vector.x*range);
                                        pushZ = z + (vector.z*range);

                                   /* } else if (this.mc.options.keyRight.isDown()) { // <--------- revisar aqui
                                        pushX = vector.x() >= 0 ? x - (vector.x*range) : x + (vector.x*range);
                                        pushZ = vector.z() >= 0 ? z + (vector.z*range) : z - (vector.z*range);
                                    } else if (this.mc.options.keyLeft.isDown()) {
                                        pushX = vector.x() >= 0 ? x + (vector.x*range) : x - (vector.x*range);
                                        pushZ = vector.z() >= 0 ? z - (vector.z*range) : z + (vector.z*range);*/
                                    }  else {
                                        pushX = x - (vector.x*range);
                                        pushZ = z - (vector.z*range);
                                    }
                                    blockPos = new BlockPos(pushX, y, pushZ);

                                    for (int i=0;i<maxAltitude;i++){
                                        if (player.level.getBlockState(blockPos).is(Blocks.AIR)){
                                            blockPos = new BlockPos(blockPos.getX(),blockPos.getY()-1,blockPos.getZ());
                                        }
                                    }

                                    if (!player.level.getBlockState(blockPos).is(Blocks.AIR)){
                                        // IF ITS A BLOCK
                                        BlockPos blockPosition = blockPos;

                                        if (IronAndSteelHelpers.isBlockStateMetal(this.mc.level.getBlockState(blockPosition))) {
                                            ModNetwork.sendToServer(new PullAndPushBlockPacket(blockPosition,
                                                    Math.round(IronAndSteelHelpers.PUSH * IronAndSteelHelpers.getMultiplier(player,playerCapability.isBurning(MetalsNBTData.DURALUMIN),
                                                            playerCapability.isBurning(MetalsNBTData.LERASIUM)))));
                                        } else if (haveNuggets(player) != -1) {
                                            ModNetwork.sendToServer(new PullAndPushNuggetPacket(blockPosition,
                                                    Math.round(IronAndSteelHelpers.PUSH * IronAndSteelHelpers.getMultiplier(player,playerCapability.isBurning(MetalsNBTData.DURALUMIN),
                                                            playerCapability.isBurning(MetalsNBTData.LERASIUM)))));
                                            if (controlTick == 0 ){
                                                player.getInventory().removeItem(haveNuggets(player),1);
                                                controlTick = 18;
                                            }
                                        }
                                    }
                                    if (controlTick>0) {
                                        controlTick--;
                                    }
                                }
                            }
                        });
            }
        }
    }
    public int haveNuggets (Player player){

        ArrayList <Item> list = new ArrayList<>();

        list.addAll(ModItems.ITEM_METAL_NUGGET.values());
        list.addAll(ModItems.ITEM_GEMS_NUGGET.values());

        list.add(Items.IRON_NUGGET);
        list.add(Items.GOLD_NUGGET);

        for (ItemStack stack: player.getInventory().items){
            if (list.contains(stack.getItem())){
                return player.getInventory().findSlotMatchingItem(stack);
            }
        }
        return -1;
    }

    int radius = 8;

    private void redoLists(Player player, IDefaultInvestedPlayerData playerCapability) {

        if (this.tickOffset == 0) {
            // Populate the metal lists
            this.metal_entities.clear();
            this.metal_blobs.clear();
            if (playerCapability.isBurning(MetalsNBTData.IRON) || playerCapability.isBurning(MetalsNBTData.STEEL)) {
                int max = 12;
                BlockPos negative = player.blockPosition().offset(-max, -max, -max);
                BlockPos positive = player.blockPosition().offset(max, max, max);

                // Add metal entities to metal list
                this.metal_entities.addAll(
                        player.level.getEntitiesOfClass(Entity.class, new AABB(negative, positive), e -> IronAndSteelHelpers.isEntityMetal(e) && !e.equals(player)));

                // Add metal blobs to metal list
                Stream<BlockPos> blocks = BlockPos.betweenClosedStream(negative, positive);
                blocks.filter(bp -> IronAndSteelHelpers.isBlockStateMetal(player.level.getBlockState(bp))).forEach(bp -> {
                    Set<MetalBlockHelpers> matches = this.metal_blobs.stream().filter(mbl -> mbl.isMatch(bp)).collect(Collectors.toSet());
                    switch (matches.size()) {
                        case 0: // new blob
                            this.metal_blobs.add(new MetalBlockHelpers(bp));
                            break;
                        case 1: // add to existing blob
                            matches.stream().findAny().get().add(bp);
                            break;
                        default: // this block serves as a bridge between (possibly many) existing blobs
                            this.metal_blobs.removeAll(matches);
                            MetalBlockHelpers mbb = matches.stream().reduce(null, MetalBlockHelpers::merge);
                            mbb.add(bp);
                            this.metal_blobs.add(mbb);
                            break;
                    }
                });
            }
        }
    }


    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onKeyInput(final InputEvent.Key event) {
        if (event.getAction() == GLFW.GLFW_PRESS) {
            acceptInput();
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onRenderGameOverlay(final RegisterGuiOverlaysEvent event) {
        /*Minecraft mc = Minecraft.getInstance();
        if ((mc.screen instanceof AllomanticMetalSelector) || (mc.screen instanceof FeruchemyMetalSelector)) {
            return;
        }
        //event.getOverlay() != RenderGameOverlayEvent.ElementType.EXPERIENCE
        if (event.isCancelable()) {
            return;
        }
        if (!mc.isWindowActive() || !mc.player.isAlive()) {
            return;
        }
        if (mc.screen != null && mc.screen instanceof ChatScreen) {
            return;
        }

        if (KeyInit.ALLOMANTIC_POWER_SELECTOR.isDown()){
            Player player = mc.player;
            if (mc.screen == null){
                if (player==null || !mc.isWindowActive()){
                    return;
                }
                player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data ->{

                    int num_powers = data.getAllomanticPowerCount();
                    if (num_powers == 0){
                        return;
                    }
                    else {
                        mc.setScreen(new AllomanticMetalSelector());
                    }
                });
            }
        }
        if (KeyInit.FERUCHEMIC_POWER_SELECTOR.isDown()){
            Player player = mc.player;
            if (mc.screen == null){
                if (player==null || !mc.isWindowActive()){
                    return;
                }
                player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data ->{

                    int num_powers = data.getFeruchemicPowerCount();
                    if (num_powers == 0){
                        return;
                    }
                    else {
                        mc.setScreen(new FeruchemyMetalSelector());
                    }
                });
            }
        }*/

    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onMouseInput(final InputEvent.MouseButton event) {
        if (event.getAction() == GLFW.GLFW_PRESS) {
            acceptInput();
        }
    }

    /**
     * Handles either mouse or button presses for the mod's keybinds
     */
    private void acceptInput() {

        if (!KeyInit.ALLOMANTIC_POWER_SELECTOR.isDown() && !KeyInit.FERUCHEMIC_POWER_SELECTOR.isDown()) {
            return;
        }
        if (this.mc.screen != null) {
            return;
        }
        Player player = this.mc.player;
        if (player == null || !this.mc.isWindowActive()) {
            return;
        }

        if (KeyInit.ALLOMANTIC_POWER_SELECTOR.isDown()){
            player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data ->{

                int num_powers = data.getAllomanticPowerCount();
                if (num_powers == 0){
                    return;
                }
                else {
                    mc.setScreen(new AllomanticMetalSelector());
                }
            });
        }
        if (KeyInit.FERUCHEMIC_POWER_SELECTOR.isDown()){
            player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data ->{
                int num_powers = data.getFeruchemicPowerCount();
                if (num_powers == 0){
                    return;
                }
                else {
                    mc.setScreen(new FeruchemyMetalSelector());
                }
            });
        }
    }



//    @OnlyIn(Dist.CLIENT)
//    @SubscribeEvent
//    public void onRenderWorldLast(RenderLevelLastEvent event) {
//        Player player = this.mc.player;
//        if (player == null || !player.isAlive() || this.mc.options.getCameraType().isMirrored()) {
//            return;
//        }
//
//        player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(playerCap -> {
//
//            if (!playerCap.isInvested()) {
//                return;
//            }
//
//            PoseStack matrixStack = event.getPoseStack();
//
//            matrixStack.pushPose();
//
//            Vec3 view = this.mc.cameraEntity.getEyePosition(event.getPartialTick());
//
//            matrixStack.translate(-view.x, -view.y, -view.z);
//
//            //RenderSystem.pushMatrix();
//            //RenderSystem.multMatrix(matrixStack.last().pose());
//            RenderSystem.disableTexture();
//            RenderSystem.disableDepthTest();
//            RenderSystem.depthMask(false);
//            RenderSystem.polygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
//            RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
//            RenderSystem.enableBlend();
//
//
//            double dist = 1;
//            double yaw = ((this.mc.player.yRotO + 90) * Math.PI) / 180;
//            double pitch = ((this.mc.player.xRotO + 90) * Math.PI) / 180;
//
//            Vec3 playerVector = view.add(Mth.sin((float) pitch) * Mth.cos((float) yaw) * dist, Mth.cos((float) pitch) * dist - 0.35,
//                    Mth.sin((float) pitch) * Mth.sin((float) yaw) * dist);
//
//
//            /***********************************
//             * DRAW LINES  - STEEL & IRON -
//             ***********************************/
//
//            if (playerCap.isBurning(MetalsNBTData.IRON) || playerCap.isBurning(MetalsNBTData.STEEL)) {
//                for (Entity entity : this.metal_entities) {
//                    ClientUtils.drawMetalLine(playerVector, entity.position(), 2f, 0, 0.6f, 1f);
//                }
//
//                for (MetalBlockHelpers mb : this.metal_blobs) {
//                    ClientUtils.drawMetalLine(playerVector, mb.getCenter(), Mth.clamp(0.3F + mb.size() * 0.4F, 0.5F, 7.5F), 0F, 0.6F, 1F);
//                }
//            }
//            /***********************************
//             * DRAW LINES  - BRONZE -
//             ***********************************/
//            if (playerCap.isBurning(MetalsNBTData.BRONZE)) {
//                BlockPos playerPos = player.blockPosition();
//                BlockPos negative = new BlockPos(player.position()).offset(playerPos.getX() - 12,playerPos.getX() - 12,playerPos.getX() - 12);
//                BlockPos positive = new BlockPos(player.position()).offset(playerPos.getX() + 12, playerPos.getX() + 12, playerPos.getX() + 12);
//
//                List<Player> players = player.level.getEntitiesOfClass(Player.class, new AABB(negative, positive)).stream().collect(Collectors.toList());
//
//                for (Player otherPlayer: players) {
//                    IDefaultInvestedPlayerData cap = otherPlayer.getCapability(InvestedCapability.PLAYER_CAP).orElse(null);
//
//                    if (cap.isBurningSomething() && !cap.isBurning(MetalsNBTData.COPPER)) {
//                        ClientUtils.drawMetalLine(playerVector, otherPlayer.position(), 5.0f, 0.7f, 0.20f, 0.20f);
//                    }
//                }
//            }
//
//            /***********************************
//             * DRAW LINES  - ELECTRUM -
//             ***********************************/
//            if (playerCap.isBurning(MetalsNBTData.ELECTRUM)) {
//                Vec3 vector = new Vec3(playerCap.getSpawnPos()[0], playerCap.getSpawnPos()[1], playerCap.getSpawnPos()[2]);
//
//                //if(player.level.dimension().getRegistryName().toString().equals(playerCap.getSpawnDimension())) {
//                ClientUtils.drawMetalLine(playerVector,vector, 2f, 0.6f, 0.6f, 0.1f);
//                //} else {
//                //  ClientUtils.drawMetalLine(playerVector, playerVector, 0,0,0,0);
//                //}
//            }
//            /***********************************
//             * DRAW LINES  - GOLD -
//             ***********************************/
//            if (playerCap.isBurning(MetalsNBTData.GOLD)) {
//                Vec3 vector = new Vec3(playerCap.getDeathPos()[0], playerCap.getDeathPos()[1], playerCap.getDeathPos()[2]);
//
//                //if(player.level.dimension().getRegistryName().toString().equals(playerCap.getDeathDimension())) {
//                ClientUtils.drawMetalLine(playerVector,vector, 2f, 0.6f, 0.6f, 0.1f);
//                //} else {
//                //  ClientUtils.drawMetalLine(playerVector, playerVector, 0,0,0,0);
//                //}
//            }
//
//            /***********************************
//             * DRAW LINES  - MALATIUM -
//             ***********************************/
//            if (playerCap.isBurning(MetalsNBTData.MALATIUM) && otherPlayerDeathPos != null) {
//                Vec3 vector = new Vec3(otherPlayerDeathPos.getX(), otherPlayerDeathPos.getY(), otherPlayerDeathPos.getZ());
//
//                if(player.level.dimension().equals(otherPlayerDimension)) {
//                    ClientUtils.drawMetalLine(playerVector,vector, 2.3f, 0.2f, 0.6f, 0.7f);
//                } else {
//                    ClientUtils.drawMetalLine(playerVector, playerVector, 0,0,0,0);
//                }
//            }
//
//            RenderSystem.polygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
//            RenderSystem.disableBlend();
//            RenderSystem.enableDepthTest();
//            RenderSystem.depthMask(true);
//            RenderSystem.enableTexture();
//            matrixStack.popPose();
//        });
//
//
//    }



    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onRenderLevelStage(final RenderLevelStageEvent event) {

        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_PARTICLES) {
            return;
        }

        Player player = this.mc.player;
        if (player == null || !player.isAlive() || this.mc.options.getCameraType().isMirrored()) {
            return;
        }

        player.getCapability(InvestedCapability.PLAYER_CAP).ifPresent(data -> {

            if (!data.isInvested()) {
                return;
            }
            PoseStack stack = setupPoseStack(event);
            stack.pushPose();


            double rho = 1;
            float theta = (float) ((this.mc.player.getViewYRot(event.getPartialTick()) + 90) * Math.PI / 180);
            float phi = Mth.clamp((float) ((this.mc.player.getViewXRot(event.getPartialTick()) + 90) * Math.PI / 180), 0.0001F, 3.14F);

            Vec3 playervec = this.mc.cameraEntity
                    .getEyePosition(event.getPartialTick())
                    .add(rho * Mth.sin(phi) * Mth.cos(theta), rho * Mth.cos(phi) - 0.35F, rho * Mth.sin(phi) * Mth.sin(theta));

            /*RenderSystem.disableTexture();
            RenderSystem.disableDepthTest();
            RenderSystem.depthMask(false);
            RenderSystem.polygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
            RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            RenderSystem.enableBlend();*/
            /*********************************************
             * IRON AND STEEL LINES                      *
             *********************************************/

            if ((data.isBurning(MetalsNBTData.IRON) || data.isBurning(MetalsNBTData.STEEL))) {
                for (var entity : this.metal_entities) {
                    ClientUtils.drawMetalLine(stack, playervec, entity.position(), 1.5F, 0F, 0.6F, 1F);
                }

                for (var mb : this.metal_blobs) {
                    ClientUtils.drawMetalLine(stack, playervec, mb.getCenter(), Mth.clamp(0.3F + mb.size() * 0.4F, 0.5F, 7.5F), 0F, 0.6F, 1F);
                }
            }

            /*********************************************
             * BRONZE LINES *
             *********************************************/
            if (data.isBurning(MetalsNBTData.BRONZE)) {
                BlockPos playerPos = player.blockPosition();
                BlockPos negative = new BlockPos(player.position()).offset(playerPos.getX() - 12,playerPos.getX() - 12,playerPos.getX() - 12);
                BlockPos positive = new BlockPos(player.position()).offset(playerPos.getX() + 12, playerPos.getX() + 12, playerPos.getX() + 12);

                List<Player> players = player.level.getEntitiesOfClass(Player.class, new AABB(negative, positive)).stream().collect(Collectors.toList());

                for (Player otherPlayer: players) {
                    IDefaultInvestedPlayerData cap = otherPlayer.getCapability(InvestedCapability.PLAYER_CAP).orElse(null);

                    if (cap.isBurningSomething() && !cap.isBurning(MetalsNBTData.COPPER)) {
                        ClientUtils.drawMetalLine(stack, playervec, otherPlayer.position(), 5.0F, 0.7F, 0.15F, 0.15F);
                   }
                }
            }

            /*********************************************
             * GOLD AND ELECTRUM AND MALATIUM LINES *
             *********************************************/
            if (data.isBurning(MetalsNBTData.GOLD)) {
                if(player.level.dimension().toString().equals(data.getDeathDimension())) {
                    Vec3 vector = new Vec3(data.getDeathPos()[0], data.getDeathPos()[1], data.getDeathPos()[2]);
                    ClientUtils.drawMetalLine(stack,playervec,vector, 2f, 0.6f, 0.6f, 0.1f);
                } else {
                    ClientUtils.drawMetalLine(stack,playervec,playervec, 2f, 0.6f, 0.6f, 0.1f);
                }
            }

            if (data.isBurning(MetalsNBTData.ELECTRUM)) {
                Vec3 vector = new Vec3(data.getSpawnPos()[0], data.getSpawnPos()[1], data.getSpawnPos()[2]);

                if(player.level.dimension().toString().equals(data.getSpawnDimension())) {
                    ClientUtils.drawMetalLine(stack,playervec,vector, 2f, 0.6f, 0.6f, 0.1f);
                } else {
                  ClientUtils.drawMetalLine(stack,playervec, playervec, 0,0,0,0);
                }
            }

            if (data.isBurning(MetalsNBTData.MALATIUM) && otherPlayerDeathPos != null) {
                Vec3 vector = new Vec3(otherPlayerDeathPos.getX(), otherPlayerDeathPos.getY(), otherPlayerDeathPos.getZ());

                if(player.level.dimension().equals(otherPlayerDimension)) {
                    ClientUtils.drawMetalLine(stack, playervec,vector, 2f, 0.6f, 0.6f, 0.1f);
                } else {
                    ClientUtils.drawMetalLine(stack, playervec, playervec, 0,0,0,0);
                }
            }
            teardownPoseStack(stack);

            /*RenderSystem.polygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
            RenderSystem.disableBlend();
            RenderSystem.enableDepthTest();
            RenderSystem.depthMask(true);
            RenderSystem.enableTexture();
            stack.popPose();*/
        });
    }
    private static void teardownPoseStack(PoseStack stack) {
        stack.popPose();
        RenderSystem.applyModelViewMatrix();

        RenderSystem.disableBlend();
        RenderSystem.enablePolygonOffset();
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask(true);
        RenderSystem.enableCull();
        RenderSystem.enableTexture();
    }

    private PoseStack setupPoseStack(final RenderLevelStageEvent event) {
        RenderSystem.setShader(GameRenderer::getRendertypeLinesShader);
        RenderSystem.disableTexture();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.disableCull();
        RenderSystem.enableBlend();
        RenderSystem.disablePolygonOffset();
        RenderSystem.defaultBlendFunc();

        PoseStack stack = event.getPoseStack();
        stack.pushPose();
        Vec3 view = this.mc.cameraEntity.getEyePosition(event.getPartialTick());
        stack.translate(-view.x, -view.y, -view.z);
        RenderSystem.applyModelViewMatrix();
        return stack;
    }

    public String getDimensionById(int dimension) {
        if (dimension == 0) {
            return "minecraft:overworld";
        } else if (dimension == 1) {
            return "minecraft:the_nether";
        } else {
            return "minecraft:the_end";
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onSound(PlaySoundEvent event) {

    }
}