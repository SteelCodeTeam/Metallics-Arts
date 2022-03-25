package net.rudahee.metallics_arts.modules.data_player;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

import java.util.ArrayList;

public interface IDefaultInvestedPlayerData {

    void tickAllomancyBurningMetals(ServerPlayerEntity player);

    void tickFeruchemyStorageMetals(ServerPlayerEntity player);

    void tickFeruchemyDecantMetals(ServerPlayerEntity player);

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

    void setBurning(MetalsNBTData metal, boolean state);

    void setAllomanticMetalsAmount(MetalsNBTData metal, int amt);

    int getAllomanticAmount(MetalsNBTData metal);
}
