package net.rudahee.metallics_arts.modules.player;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

import java.util.ArrayList;

public interface IInvestedPlayerData {

    void tickAllomancyBurningMetals(ServerPlayerEntity player);

    void tickFeruchemyStorageMetals(ServerPlayerEntity player);
    void tickFeruchemyDecantMetals(ServerPlayerEntity player);


    boolean hasAllomanticPower(MetalsNBTData metal);
    boolean hasFeruchemicPower(MetalsNBTData metal);


    int getAllomanticPowerCount();
    int getFeruchemicPowerCount();


    ArrayList<MetalsNBTData> getAllomanticPowers();
    ArrayList<MetalsNBTData> getFeruchemicPowers();

    void setMistborn();
    void setCompleteFeruchemic();

    void setCompleteInvested();

    boolean isMistborn();
    boolean isCompleteFeruchemic();
    boolean isCompleteInvested();

    boolean isUninvested();

    void setUninvested();

    void addAllomanticPower(MetalsNBTData metal);
    void addFeruchemicPower(MetalsNBTData metal);

    void revokeAllomanticPower(MetalsNBTData metal);
    void revokeFeruchemicPower(MetalsNBTData metal);


    boolean isBurning(MetalsNBTData metal);
    boolean isDecanting(MetalsNBTData metal);
    boolean isStoring(MetalsNBTData metal);


    void setBurning(MetalsNBTData metal, boolean metalBurning);
    void setDecanting(MetalsNBTData metal, boolean metalDecanting);
    void setStoring(MetalsNBTData metal, boolean metalStoring);


    void setAllomanticMetalsAmount(MetalsNBTData metal, int amt);
    void setFeruchemicMetalsAmount(MetalsNBTData metal, int amt);


    int getAllomanticAmount(MetalsNBTData metal);
    int getFeruchemicAmount(MetalsNBTData metal);

}
