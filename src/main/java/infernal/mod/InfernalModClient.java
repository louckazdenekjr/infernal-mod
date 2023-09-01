package infernal.mod;

import infernal.mod.block.ModBlocks;
import net.fabricmc.api.ClientModInitializer;

public class InfernalModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // client-side stuff
        ModBlocks.registerTransparentBlocks();

        // go to common initialization
        InfernalMod mod = new InfernalMod();
        mod.onInitialize();
    }
}
