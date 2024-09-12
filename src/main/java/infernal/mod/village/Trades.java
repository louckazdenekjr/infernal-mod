package infernal.mod.village;

import infernal.mod.InfernalMod;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;


public class Trades {
    public static void registerCustomTrade() {
        // Register custom trades for a specific profession and level
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1, factories -> {
            factories.add(new CustomTrade()); // Add your custom trade
        });
    }


    public static class CustomTrade implements TradeOffers.Factory {
        @Override
        public TradeOffer create(Entity entity, Random random) {
            // Define the trade
            return new TradeOffer(
                    new ItemStack(Items.DIRT, 1),      // Input ItemStack
                    new ItemStack(Items.EMERALD, 1),   // Output ItemStack
                    2,                                       // Max uses
                    10,                                      // Villager XP reward
                    0.05f                                    // Price multiplier
            );
        }
    }

    public static void registerModTrades() {
        InfernalMod.LOGGER.info("Registering Trades");
        registerCustomTrade();
    }
}