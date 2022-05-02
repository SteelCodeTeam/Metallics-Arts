package net.rudahee.metallics_arts.modules.client.GUI;

import net.rudahee.metallics_arts.setup.enums.extras.MetalsNBTData;

import java.util.Comparator;

public class ComparatorMetals implements Comparator<MetalsNBTData> {

    @Override
    public int compare(MetalsNBTData m1, MetalsNBTData m2) {
        if (m1.getOrder() < m2.getOrder()){
            return 1;
        } else {
            return -1;
        }
    }
}
