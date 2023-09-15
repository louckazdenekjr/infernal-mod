package infernal.mod.datagen;


import infernal.mod.block.Blocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;


public class BlockTagGenerator extends FabricTagProvider.BlockTagProvider {
    public BlockTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(Blocks.BEJEWELED_DEEPSLATE)
                .add(Blocks.BEJEWELED_SANDSTONE)
                .add(Blocks.POLISHED_CALCITE)
                .add(Blocks.CALCITE_BRICKS)
                .add(Blocks.CRACKED_CALCITE_BRICKS)
                .add(Blocks.BEJEWELED_CALCITE_BRICKS)
                .add(Blocks.BEJEWELED_STONE_BRICKS)
                .add(Blocks.POLISHED_TERRACOTTA)
                .add(Blocks.BEJEWELED_TERRACOTTA)
                .add(Blocks.SHORT_RANGE_TRANSPORTER);
        // TODO: remove explicit tags from blocks that inherit properties

        // wooden tool level is implied
        // other tool levels assigned here
        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(Blocks.SHORT_RANGE_TRANSPORTER);

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(Blocks.BEJEWELED_DEEPSLATE);
    }
}