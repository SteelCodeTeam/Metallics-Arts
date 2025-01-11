package net.rudahee.metallics_arts.modules.logic.server.server_events;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.rudahee.metallics_arts.data.enums.implementations.trades.ForgeMasterTrades;
import net.rudahee.metallics_arts.data.enums.implementations.trades.HemalurgyMonkTrades;
import net.rudahee.metallics_arts.data.enums.implementations.trades.HemalurgyWarriorTrades;

import java.util.List;

public class OnVillagerTradesEvent {

    public static void onForgeMasterVillager(VillagerTradesEvent event) {
        Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
        for (ForgeMasterTrades trade: ForgeMasterTrades.values()) {
            trades.get(trade.getLevel()).add((trader, rng) -> new MerchantOffer(
                    trade.getPrimaryInput(),
                    (trade.getSecondaryInput() != null) ? trade.getSecondaryInput() : ItemStack.EMPTY,
                    trade.getOutput(),
                    trade.getMaxUses(),
                    trade.getXp(),
                    0.1F
            ));
        }
    }

    public static void onHemalurgyMonkVillager(VillagerTradesEvent event) {
        Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
        for (HemalurgyMonkTrades trade: HemalurgyMonkTrades.values()) {
            trades.get(trade.getLevel()).add((trader, rng) -> new MerchantOffer(
                    trade.getPrimaryInput(),
                    (trade.getSecondaryInput() != null) ? trade.getSecondaryInput() : ItemStack.EMPTY,
                    trade.getOutput(),
                    trade.getMaxUses(),
                    trade.getXp(),
                    0.1F
            ));
        }
    }

    public static void onHemalurgyWarriorVillager(VillagerTradesEvent event) {
        Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
        for (HemalurgyWarriorTrades trade: HemalurgyWarriorTrades.values()) {
            trades.get(trade.getLevel()).add((trader, rng) -> new MerchantOffer(
                    trade.getPrimaryInput(),
                    (trade.getSecondaryInput() != null) ? trade.getSecondaryInput() : ItemStack.EMPTY,
                    trade.getOutput(),
                    trade.getMaxUses(),
                    trade.getXp(),
                    0.1F
            ));
        }
    }
}
