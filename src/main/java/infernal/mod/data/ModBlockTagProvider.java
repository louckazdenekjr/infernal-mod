package infernal.mod.data;


import infernal.mod.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import java.util.concurrent.CompletableFuture;


public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.BEJEWELED_DEEPSLATE)
                .add(ModBlocks.BEJEWELED_SANDSTONE)
                .add(ModBlocks.POLISHED_CALCITE)
                .add(ModBlocks.CALCITE_BRICKS)
                .add(ModBlocks.CRACKED_CALCITE_BRICKS)
                .add(ModBlocks.BEJEWELED_CALCITE_BRICKS)
                .add(ModBlocks.BEJEWELED_STONE_BRICKS)
                .add(ModBlocks.POLISHED_TERRACOTTA)
                .add(ModBlocks.BEJEWELED_TERRACOTTA);

        // wooden tool level is implied
        // other tool levels assigned here
        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.BEJEWELED_DEEPSLATE);

    }
}