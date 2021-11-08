package net.rudahee.metallics_arts.block.alloyfurnace;

import com.mojang.datafixers.TypeRewriteRule;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.rudahee.metallics_arts.setup.ModContainerTypes;
import org.lwjgl.system.CallbackI;

import javax.annotation.Nullable;
import java.awt.*;

public class AlloyFurnaceContainer extends Container {

    private final IInventory inventory;
    private IIntArray fields;

    public AlloyFurnaceContainer (int id, PlayerInventory playerInventory, PacketBuffer buffer){
        this (id,playerInventory,new ModAlloyFurnaceTileEntity(), new IntArray(buffer.readByte()));
    }

    public AlloyFurnaceContainer (int id, PlayerInventory playerInventory, IInventory inventory, IIntArray fields){
        super(ModContainerTypes.alloyFurnace.get(), id);
        this.inventory = inventory;
        this.fields = fields;

        this.addSlot(new Slot(this.inventory,0, 56, 35));
        this.addSlot(new Slot(this.inventory, 1, 117, 35){
            @Override
            public boolean mayPlace (ItemStack stack){
                return false;
            }
        });

        //player Backpack
        for (int y = 0; y<3;++y){
            for (int x = 0; x<9; ++x){
                int index = x + y * 9;
                int posX = 8 + x * 18;
                int posY = 84 + y * 18;
                this.addSlot(new Slot(playerInventory, index, posX, posY));

            }
        }

        //player hatbar
        for (int x = 0; x < 9; ++x){
            int index = x ;
            int posX = 8 + x * 18;
            int posY = 142;
            this.addSlot(new Slot(playerInventory,index, posX, posY));
        }

    }

    public int getProgressArrowScale (){
        int progress = fields.get(0);
        if (progress > 0){
            return progress * 24 / ModAlloyFurnaceTileEntity.workTime;
        }
        return 0;
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return this.inventory.stillValid(player);
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemStack1 = slot.getItem();
            itemStack1 = itemStack1.copy();

            final int inventorySize = 2;
            final int playerInventoryEnd = inventorySize + 27;
            final int playenHotbarEnd = playerInventoryEnd + 9;

            if (index == 1) {
                if (!this.moveItemStackTo(itemStack1, inventorySize, playerInventoryEnd, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(itemStack1, itemStack);
            } else if (index != 0) {
                if (!this.moveItemStackTo(itemStack1, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemStack1, inventorySize, playenHotbarEnd, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
            if (itemStack1.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(player,itemStack1);
        }
        return itemStack;
    }
}


