package net.rudahee.metallics_arts.modules.player;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.rudahee.metallics_arts.data.network.NetworkUtils;
import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;
import net.rudahee.metallics_arts.setup.enums.metals.Metal;

import java.util.*;

public class DefaultInvestedPlayerData implements IInvestedPlayerData {

    private HashMap<String, HashMap> rootMetals = new HashMap<String, HashMap>() {{
        Arrays.asList(MetalsNBTData.values()).forEach(metalsNBTData -> {

            put(metalsNBTData.getNameLower(), new HashMap<String, Integer>() {{
                // 0 = false, 1 = true
                put("hasPlayerAllomanticPower", 0);
                put("hasPlayerFeruchemicPower", 0);
                put("isBurning", 0);
                put("isDecanting", 0);
                put("isStoring", 0);

                // int = quantity.
                put("actualBurnQty", 0);
                put("actualStorageQty", 0);
                put("maxBurnQty", metalsNBTData.getMaxAllomanticTicksStorage());
                put("maxStorageQty", metalsNBTData.getMaxFeruchemicTicksStorage());
            }});

        });
    }};

    public static ArrayList<String> getMetalIndex() {
        ArrayList<String> rootMetalsIndexs = new ArrayList<String>() {{
                for (MetalsNBTData metal : MetalsNBTData.values()) {
                    add(metal.getNameLower());

                }
            }};

        return rootMetalsIndexs;
    }
    public static ArrayList<String> getDataForEachMetalIndex() {
        ArrayList<String> rootMetalsIndexs = new ArrayList<String>() {{
            add("hasPlayerAllomanticPower");
            add("hasPlayerFeruchemicPower");
            add("isBurning");
            add("isDecanting");
            add("isStoring");

            // int = quantity.
            add("actualBurnQty");
            add("actualStorageQty");
            add("maxBurnQty");
            add("maxStorageQty");
        }};

        return rootMetalsIndexs;
    }
    public HashMap<String, HashMap> getMetalsRoot() {
        return rootMetals;
    }

    @Override
    public void tickAllomancyBurningMetals(ServerPlayerEntity player) {
        boolean sync = false;

        for (MetalsNBTData metal: MetalsNBTData.values()) {
            if (isBurning(metal)) {
                if (!hasAllomanticPower(metal)) {
                    this.setBurning(metal, false);
                    sync = true;
                }
                else {
                    setAllomanticMetalsAmount(metal, this.getAllomanticAmount(metal) - 1);
                    if (getAllomanticAmount(metal) <= 0) {
                        setBurning(metal, false);
                    }
                    sync = true;
                }
            }
        }

        if(sync) {
            player.getCapability(InvestedPlayerCapability.PLAYER_CAP).ifPresent(data -> NetworkUtils.sync(data, player));
        }
    }

    @Override
    public void tickFeruchemyStorageMetals(ServerPlayerEntity player) {
        boolean sync = false;

        for (MetalsNBTData metal: MetalsNBTData.values()) {
            if (isStoring(metal)) {
                if (!hasFeruchemicPower(metal)) {
                    this.setStoring(metal, false);
                    sync = true;
                }
                else {
                    setAllomanticMetalsAmount(metal, this.getFeruchemicAmount(metal) + 1);
                    if (getFeruchemicAmount(metal) >= Integer.parseInt((String) this.rootMetals.get(metal).get("maxStorageQty"))) {
                        setStoring(metal, false);
                    }
                    sync = true;
                }
            }
        }

        if(sync) {
            player.getCapability(InvestedPlayerCapability.PLAYER_CAP).ifPresent(data -> NetworkUtils.sync(data, player));
        }
    }

    @Override
    public void tickFeruchemyDecantMetals(ServerPlayerEntity player) {
        boolean sync = false;

        for (MetalsNBTData metal: MetalsNBTData.values()) {
            if (isDecanting(metal)) {
                if (!hasFeruchemicPower(metal)) {
                    this.setDecanting(metal, false);
                    sync = true;
                }
                else {
                    setAllomanticMetalsAmount(metal, this.getFeruchemicAmount(metal) + 1);
                    if (getFeruchemicAmount(metal) <= 0) {
                        setDecanting(metal, false);
                    }
                    sync = true;
                }
            }
        }

        if(sync) {
            player.getCapability(InvestedPlayerCapability.PLAYER_CAP).ifPresent(data -> NetworkUtils.sync(data, player));
        }
    }

    @Override
    public boolean hasAllomanticPower(MetalsNBTData metal) {
        return this.rootMetals.get(metal).get("hasPlayerAllomanticPower").equals(1) ? true : false;
    }

    @Override
    public boolean hasFeruchemicPower(MetalsNBTData metal) {
        return this.rootMetals.get(metal).get("hasPlayerFeruchemicPower").equals(1) ? true : false;
    }

    @Override
    public int getAllomanticPowerCount() {
        int count = 0;

        for (MetalsNBTData metal : Arrays.asList(MetalsNBTData.values()) ) {
            if(hasAllomanticPower(metal)) {
                count++;
            }
        };

        return count;
    }

    @Override
    public int getFeruchemicPowerCount() {
        int count = 0;

        for (MetalsNBTData metal : Arrays.asList(MetalsNBTData.values()) ) {
            if(hasFeruchemicPower(metal)) {
                count++;
            }
        };

        return count;
    }

    @Override
    public ArrayList<MetalsNBTData> getAllomanticPowers() {
        ArrayList<MetalsNBTData> metals = new ArrayList<>();
        for (MetalsNBTData metal : Arrays.asList(MetalsNBTData.values()) ) {
            if(hasAllomanticPower(metal)) {
                metals.add(metal);
            }
        };

        return metals;
    }

    @Override
    public ArrayList<MetalsNBTData> getFeruchemicPowers() {
        ArrayList<MetalsNBTData> metals = new ArrayList<>();
        for (MetalsNBTData metal : Arrays.asList(MetalsNBTData.values()) ) {
            if(hasFeruchemicPower(metal)) {
                metals.add(metal);
            }
        };

        return metals;
    }

    @Override
    public void setMistborn() {
        Arrays.asList(MetalsNBTData.values()).forEach(metalsNBTData -> {

            rootMetals.get(metalsNBTData.getNameLower()).replace("hasPlayerAllomanticPower", 1);

        });
    }

    @Override
    public void setCompleteFeruchemic() {
        Arrays.asList(MetalsNBTData.values()).forEach(metalsNBTData -> {

            rootMetals.get(metalsNBTData.getNameLower()).replace("hasPlayerFeruchemicPower", 1);

        });
    }

    @Override
    public void setCompleteInvested() {
        Arrays.asList(MetalsNBTData.values()).forEach(metalsNBTData -> {
            rootMetals.get(metalsNBTData.getNameLower()).replace("hasPlayerAllomanticPower", 1);
            rootMetals.get(metalsNBTData.getNameLower()).replace("hasPlayerFeruchemicPower", 1);

        });
    }

    @Override
    public boolean isMistborn() {
        for (MetalsNBTData metal : Arrays.asList(MetalsNBTData.values())) {
            if (!rootMetals.get(metal.getNameLower()).get("hasPlayerAllomanticPower").equals(1)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isCompleteFeruchemic() {
        for (MetalsNBTData metal : Arrays.asList(MetalsNBTData.values())) {
            if (!rootMetals.get(metal.getNameLower()).get("hasPlayerFeruchemicPower").equals(1)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isCompleteInvested() {
        return (isMistborn() && isCompleteFeruchemic()) ? true : false;
    }

    @Override
    public boolean isUninvested() {
        for (MetalsNBTData metal : Arrays.asList(MetalsNBTData.values())) {
            if (rootMetals.get(metal.getNameLower()).get("hasPlayerAllomanticPower").equals(1)
                || rootMetals.get(metal.getNameLower()).get("hasPlayerFeruchemicPower").equals(1)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void setUninvested() {
        Arrays.asList(MetalsNBTData.values()).forEach(metalsNBTData -> {

            rootMetals.get(metalsNBTData.getNameLower()).replace("hasPlayerAllomanticPower", 0);
            rootMetals.get(metalsNBTData.getNameLower()).replace("hasPlayerFeruchemicPower", 0);

        });
    }

    @Override
    public void addAllomanticPower(MetalsNBTData metal) {
        rootMetals.get(metal).replace("hasPlayerAllomanticPower", 1);
    }

    @Override
    public void addFeruchemicPower(MetalsNBTData metal) {
        rootMetals.get(metal).replace("hasPlayerFeruchemicPower", 1);
    }

    @Override
    public void revokeAllomanticPower(MetalsNBTData metal) {
        rootMetals.get(metal).replace("hasPlayerAllomanticPower", 0);
    }

    @Override
    public void revokeFeruchemicPower(MetalsNBTData metal) {
        rootMetals.get(metal).replace("hasPlayerFeruchemicPower", 0);
    }

    @Override
    public boolean isBurning(MetalsNBTData metal) {
        return (rootMetals.get(metal).get("isBurning").equals(1)) ? true : false;
    }

    @Override
    public boolean isDecanting(MetalsNBTData metal) {
        return (rootMetals.get(metal).get("isDecanting").equals(1)) ? true : false;
    }

    @Override
    public boolean isStoring(MetalsNBTData metal) {
        return (rootMetals.get(metal).get("isStoring").equals(1)) ? true : false;
    }

    @Override
    public void setBurning(MetalsNBTData metal, boolean metalBurning) {
        rootMetals.get(metal).replace("isBurning", metalBurning);
    }

    @Override
    public void setDecanting(MetalsNBTData metal, boolean metalDecanting) {
        rootMetals.get(metal).replace("isDecanting", metalDecanting);
    }

    @Override
    public void setStoring(MetalsNBTData metal, boolean metalStoring) {
        rootMetals.get(metal).replace("isStoring", metalStoring);
    }

    @Override
    public void setAllomanticMetalsAmount(MetalsNBTData metal, int amt) {
        rootMetals.get(metal).replace("actualBurnQty", amt);
    }

    @Override
    public void setFeruchemicMetalsAmount(MetalsNBTData metal, int amt) {
        rootMetals.get(metal).replace("actualStorageQty", amt);
    }

    @Override
    public int getAllomanticAmount(MetalsNBTData metal) {
        return Integer.parseInt((String) rootMetals.get(metal).get("actualBurnQty"));
    }

    @Override
    public int getFeruchemicAmount(MetalsNBTData metal) {
        return Integer.parseInt((String) rootMetals.get(metal).get("actualStorageQty"));
    }
}
