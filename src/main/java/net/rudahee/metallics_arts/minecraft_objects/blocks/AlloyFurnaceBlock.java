package net.rudahee.metallics_arts.minecraft_objects.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.rudahee.metallics_arts.minecraft_objects.containers.AlloyFurnaceContainer;
import net.rudahee.metallics_arts.minecraft_objects.tile_entity.AlloyFurnaceTileEntity;
import net.rudahee.metallics_arts.minecraft_objects.tile_entity.ModTileEntities;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class AlloyFurnaceBlock extends Block {
    public AlloyFurnaceBlock(Properties properties) {
        super(properties);
    }

    @Deprecated
    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos,
                                             PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isClientSide) {
            TileEntity tileEntity = worldIn.getBlockEntity(pos);
                if(tileEntity instanceof AlloyFurnaceTileEntity) {
                    INamedContainerProvider containerProvider = createContainerProvider(worldIn, pos);

                    if (player instanceof ServerPlayerEntity) {
                        NetworkHooks.openGui((ServerPlayerEntity) player, containerProvider, tileEntity.getBlockPos());
                    } else {
                        NetworkHooks.openGui((ServerPlayerEntity) player, containerProvider, new Consumer<PacketBuffer>() {
                            @Override
                            public void accept(PacketBuffer buffer) {
                                buffer.writeInt(player.getId());
                            }
                        });
                        return ActionResultType.SUCCESS;

                    }
                } else {
                    throw new IllegalStateException("Our Container provider is missing!");
                }
        }
        return ActionResultType.SUCCESS;
    }

    private INamedContainerProvider createContainerProvider(World worldIn, BlockPos pos) {
        return new INamedContainerProvider() {
            @Override
            public ITextComponent getDisplayName() {
                return new TranslationTextComponent("screen.metallics_arts.alloy_furnace");
            }

            @Nullable
            @Override
            public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
                return new AlloyFurnaceContainer(i, worldIn, pos, playerInventory, playerEntity);
            }
        };
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntities.ALLOY_FURNACE_TILE_ENTITY.get().create();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}
