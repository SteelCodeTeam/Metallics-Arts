package net.rudahee.metallics_arts.data.player;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.rudahee.metallics_arts.data.enums.implementations.EttmetalState;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;

import java.util.ArrayList;

/**
 * This interface is used to define the required methods that must exist in the InvestedPlayerData class.
 *
 * @author SteelCode Team
 * @since 1.5.1
 *
 * @see InvestedPlayerData
 */
public interface IInvestedPlayerData {
    void tickAllomancyBurningMetals(ServerPlayer player);

    void setEttmetalState(EttmetalState state);

    EttmetalState getEttmetalState();

    Inventory restoreInventory();

    void keepInventory (Inventory inventory);

    boolean isBurningAnything();

    boolean isTappingAnything();

    boolean isStoringAnything();

    void clearMetalsEnhanced();

    void addMetalsEnhanced(MetalTagEnum metalTagEnum);

    ArrayList<MetalTagEnum> getMetalsEnhanced();

    boolean containsMetalsEnhanced(MetalTagEnum metalTagEnum);

    boolean hasAllomanticPower(MetalTagEnum metal);

    boolean hasFeruchemicPower(MetalTagEnum metal);

    int getAllomanticPowerCount();

    int getFeruchemicPowerCount();

    boolean hasAnyAllomanticPower();

    boolean hasAnyFeruchemicPower();

    ArrayList<MetalTagEnum> getAllomanticPowers();

    ArrayList<MetalTagEnum> getFeruchemicPowers();

    void drainMetals(MetalTagEnum... metals);

    boolean hasMetalMindEquiped(int group);

    void setMetalMindEquiped(int group, boolean value);

    ArrayList<Boolean> getMetalMindEquipedList();

    void setMetalMindEquipedList(ArrayList<Boolean> list);

    boolean getEnhanced();

    void setEnhanced(boolean isEnhanced);

    boolean hasModifiedHealth();

    void setModifiedHealth(boolean modified);

    void setMistborn(boolean mistborn);

    void setFullFeruchemist(boolean feruchemic);

    void setFullInvested(boolean invested);

    boolean isMistborn();

    boolean isFullFeruchemist();

    boolean isFullInvested();

    boolean isInvested();

    void setInvested(boolean invested);

    void setUninvested();

    void addAllomanticPower(MetalTagEnum metal);

    void addFeruchemicPower(MetalTagEnum metal);

    void addAllAllomantic();

    void addAllFeruchemic();

    //REMOVE
    void removeAllomanticPower(MetalTagEnum metal);

    void removeAllAllomanticPower();

    void removeFeruchemicPower(MetalTagEnum metal);

    void removeAllFeruchemicPower();

    boolean isTapping(MetalTagEnum metal);

    boolean isStoring(MetalTagEnum metal);

    boolean isBurning(MetalTagEnum metal);

    boolean isUsingPowers();

    int cantMetalsTapping();

    int cantMetalsStoring();

    void setTapping(MetalTagEnum metal, boolean value);

    void setStoring(MetalTagEnum metal, boolean value);

    void setBurning(MetalTagEnum metal, boolean value);

    void setAllomanticMetalsAmount(MetalTagEnum metal, int qty);

    boolean addAllomanticMetalAmount(MetalTagEnum metal, int qty);

    boolean substractAllomanticMetalAmount(MetalTagEnum metal, int qty);

    int getAllomanticAmount(MetalTagEnum metal);

    CompoundTag save();

    void load(CompoundTag nbt);
}
