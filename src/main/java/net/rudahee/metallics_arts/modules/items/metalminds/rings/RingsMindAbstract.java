package net.rudahee.metallics_arts.modules.items.metalminds.rings;

import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;


public class RingsMindAbstract extends Item {


    private CompoundNBT nbt;
    private int maxCapacity;

    public RingsMindAbstract(Properties properties) {
        super(properties);
    }

    public RingsMindAbstract() {
        super(new Properties());

    }

    public void storing(String metal, int qty){
        this.nbt.putInt(metal,this.nbt.getInt(metal)+qty);

    }
    public void decanting(String metal, int qty){
        this.nbt.putInt(metal,this.nbt.getInt(metal)-qty);
    }

    public CompoundNBT getNbt() {
        return nbt;
    }

    public void setNbt(CompoundNBT nbt) {
        this.nbt = nbt;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
}
