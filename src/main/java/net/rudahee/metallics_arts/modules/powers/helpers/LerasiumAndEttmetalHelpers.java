package net.rudahee.metallics_arts.modules.powers.helpers;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.ExplosionEvent;

public class LerasiumAndEttmetalHelpers {


    public static void ettmetalExplotion(World world, PlayerEntity player,AxisAlignedBB axisAlignedBB, BlockPos negative, BlockPos positive) {

        /*world.getEntitiesOfClass(LivingEntity.class, axisAlignedBB).forEach(entity -> {
            entity.aiStep();
        });


        BlockPos.betweenClosedStream(negative, positive).forEach(blockPos -> {
            BlockState block = world.getBlockState(blockPos);
            TileEntity tileEntity = world.getBlockEntity(blockPos);




            for (int i = 0; i < 12 * 4 / (tileEntity == null ? 10 : 1); i++) {
                if (tileEntity instanceof ITickableTileEntity) {

                }
            }
        });*/

        Explosion explosion = new Explosion(world,player,player.position().x,player.position().y,player.position().z,5,false, Explosion.Mode.DESTROY);

        new ExplosionEvent(world,explosion);
        //ExplosionEvent.Detonate(world,explosion,)


    }
}
