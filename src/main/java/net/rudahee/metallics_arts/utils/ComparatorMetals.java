package net.rudahee.metallics_arts.utils;

import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;

import java.util.Comparator;

/**
 * Comparator implementation for MetalTagEnum.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class ComparatorMetals implements Comparator<MetalTagEnum> {


    /**
     * Implementation of a Share for the enum MetalTagEnum.
     * The objective of this method is to return a number depending on the internal order of the metals in MetalTagEnum.
     *
     * @param metal1 the first MetalTagEnum to be compared.
     * @param metal2 the second MetalTagEnum to be compared.
     *
     * @return int returns -1 if the first metal passed by parameter comes before the second, otherwise returns 1.
     *
     * @see MetalTagEnum
     */
    @Override
    public int compare(MetalTagEnum metal1, MetalTagEnum metal2) {
        if (metal1.getOrder() < metal2.getOrder()){
            return 1;
        } else {
            return -1;
        }
    }
}
