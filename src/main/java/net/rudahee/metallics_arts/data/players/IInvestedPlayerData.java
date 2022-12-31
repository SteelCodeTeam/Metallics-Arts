package net.rudahee.metallics_arts.data.players;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;

import java.util.ArrayList;

public interface IInvestedPlayerData {

    void tickAllomancyBurningMetals(ServerPlayer player);

    void clearListMetalBuff();
    void addListMetalBuff(MetalTagEnum metalTagEnum);
    ArrayList<MetalTagEnum> getListMetalBuff();

    boolean containsInListMetalBuff(MetalTagEnum metalTagEnum);

    void tickFeruchemyStorageMetals(ServerPlayer player);

    void tickFeruchemyDecantMetals(ServerPlayer player);

    boolean hasAllomanticPower(MetalTagEnum metal);

    boolean hasFeruchemicPower(MetalTagEnum metal);

    int getAllomanticPowerCount();

    int getFeruchemicPowerCount();

    ArrayList<MetalTagEnum> getAllomanticPowers();

    ArrayList<MetalTagEnum> getFeruchemicPowers();

    void setMistborn(boolean mistborn);

    void addAllAllomantic();

    void setFullFeruchemic(boolean feruchemic);

    void addAllFeruchemic();

    void setFullInvested(boolean invested);

    boolean isMistborn();

    boolean isFullFeruchemic();

    boolean isFullInvested();

    boolean isInvested();

    void setInvested(boolean invested);

    void setUninvested();

    void addAllomanticPower(MetalTagEnum metal);

    void addFeruchemicPower(MetalTagEnum metal);

    void removeAllomanticPower(MetalTagEnum metal);

    void removeAllAllomanticPower();

    void removeFeruchemicPower(MetalTagEnum metal);

    void removeAllFeruchemicPower();

    boolean isBurning(MetalTagEnum metal);

    boolean isUsingPowers();

    MetalTagEnum getRandomBurningMetal();

    boolean isDecanting(MetalTagEnum metal);

    boolean isStoring(MetalTagEnum metal);

    int cantMetalsDecanting();

    int cantMetalsStoring();

    void setDecanting(MetalTagEnum metal, boolean value);

    void setStoring(MetalTagEnum metal, boolean value);

    void setBurning(MetalTagEnum metal, boolean state);

    void setAllomanticMetalsAmount(MetalTagEnum metal, int amt);

    boolean substractAllomanticMetalAmount(MetalTagEnum metal, int amt);

    boolean addAllomanticMetalAmount(MetalTagEnum metal, int amt);

    int getAllomanticAmount(MetalTagEnum metal);

    void drainMetals(MetalTagEnum... metals);

    int[] getDeathPos();

    int[] getSpawnPos();

    void setDeathPos(int[] deathPos);

    void setSpawnPos(int[] spawnPos);

    String getDeathDimension();

    String getSpawnDimension();

    void setDeathDimension(String dimension);

    void setSpawnDimension(String dimension);

    boolean getMetalMindEquiped (int group);

    void setMetalMindEquiped (int group,boolean value);

    boolean[] getMetalMindEquipedList ();

    void setMetalMindEquipedList (boolean [] list);

    boolean getEnhanced();

    void setEnhanced(boolean isExternalEnhanced);


    CompoundTag save();

    void load(CompoundTag nbt);
}
