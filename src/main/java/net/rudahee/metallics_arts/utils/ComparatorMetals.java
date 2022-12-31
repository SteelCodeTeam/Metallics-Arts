package net.rudahee.metallics_arts.utils;

import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;

import java.util.Comparator;

public class ComparatorMetals implements Comparator<MetalTagEnum> {

    @Override
    public int compare(MetalTagEnum m1, MetalTagEnum m2) {
        if (m1.getOrder() < m2.getOrder()){
            return 1;
        } else {
            return -1;
        }
    }
}
