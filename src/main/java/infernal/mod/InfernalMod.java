package infernal.mod;


import infernal.mod.block.BlockEntities;
import infernal.mod.block.Blocks;
import infernal.mod.item.ItemGroups;
import infernal.mod.item.Items;
import infernal.mod.sound.SoundEvents;
import infernal.mod.village.Trades;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class InfernalMod implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.

    public static final Logger LOGGER = LoggerFactory.getLogger("infernal-mod");
    public static final String MOD_ID = "infernal-mod";


    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        ItemGroups.registerItemGroups();
        Items.registerModItems();
        Blocks.registerModBlocks();
        BlockEntities.registerModBlockEntities();
        SoundEvents.registerModSoundEvents();
        Trades.registerModTrades();

        LOGGER.info("Initializing Infernal Mod.");
    }
}

