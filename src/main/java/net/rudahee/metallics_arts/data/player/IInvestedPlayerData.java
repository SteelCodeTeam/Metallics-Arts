package net.rudahee.metallics_arts.data.player;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.rudahee.metallics_arts.data.enums.implementations.MetalsNBTData;

import java.util.ArrayList;

public interface IInvestedPlayerData {

    void tickAllomancyBurningMetals(ServerPlayer player);

    void clearListMetalBuff();
    void addListMetalBuff(MetalsNBTData metalsNBTData);
    ArrayList<MetalsNBTData> getListMetalBuff();

    boolean containsInListMetalBuff(MetalsNBTData metalsNBTData);

    void tickFeruchemyStorageMetals(ServerPlayer player);

    void tickFeruchemyDecantMetals(ServerPlayer player);

    boolean hasAllomanticPower(MetalsNBTData metal);

    boolean hasFeruchemicPower(MetalsNBTData metal);

    int getAllomanticPowerCount();

    int getFeruchemicPowerCount();

    ArrayList<MetalsNBTData> getAllomanticPowers();

    ArrayList<MetalsNBTData> getFeruchemicPowers();

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

    void addAllomanticPower(MetalsNBTData metal);

    void addFeruchemicPower(MetalsNBTData metal);

    void removeAllomanticPower(MetalsNBTData metal);

    void removeAllAllomanticPower();

    void removeFeruchemicPower(MetalsNBTData metal);

    void removeAllFeruchemicPower();

    boolean isBurning(MetalsNBTData metal);

    boolean isUsingPowers();

    MetalsNBTData getRandomBurningMetal();

    boolean isDecanting(MetalsNBTData metal);

    boolean isStoring(MetalsNBTData metal);

    int cantMetalsDecanting();

    int cantMetalsStoring();

    void setDecanting(MetalsNBTData metal, boolean value);

    void setStoring(MetalsNBTData metal, boolean value);

    void setBurning(MetalsNBTData metal, boolean state);

    void setAllomanticMetalsAmount(MetalsNBTData metal, int amt);

    boolean substractAllomanticMetalAmount(MetalsNBTData metal, int amt);

    boolean addAllomanticMetalAmount(MetalsNBTData metal, int amt);

    int getAllomanticAmount(MetalsNBTData metal);

    void drainMetals(MetalsNBTData... metals);

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
