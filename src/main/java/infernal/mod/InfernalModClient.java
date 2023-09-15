package infernal.mod;

import infernal.mod.block.Blocks;
import infernal.mod.model.Models;
import net.fabricmc.api.ClientModInitializer;

public class InfernalModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // client-side stuff
        Blocks.registerTransparentBlocks();
        Models.registerModModels();

        // go to common initialization
        InfernalMod mod = new InfernalMod();
        mod.onInitialize();
    }
}
