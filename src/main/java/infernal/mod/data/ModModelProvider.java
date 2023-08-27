package infernal.mod.data;


import infernal.mod.block.ModBlocks;
import infernal.mod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;


public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.BEJEWELED_DEEPSLATE);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.BEJEWELED_SANDSTONE);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.POLISHED_CALCITE);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.CALCITE_BRICKS);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.CRACKED_CALCITE_BRICKS);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.BEJEWELED_CALCITE_BRICKS);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.BEJEWELED_STONE_BRICKS);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.POLISHED_TERRACOTTA);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.BEJEWELED_TERRACOTTA);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.HANDBELL, Models.GENERATED);
        //itemModelGenerator.register(ModItems.RAW_CITRINE, Models.GENERATED);
    }
}