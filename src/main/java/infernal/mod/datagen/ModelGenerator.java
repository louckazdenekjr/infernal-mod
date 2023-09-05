package infernal.mod.datagen;


import infernal.mod.block.ModBlocks;
import infernal.mod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
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
        blockStateModelGenerator.registerTrapdoor(ModBlocks.SHORT_RANGE_TRANSPORTER);

        /*
        TextureMap textureMap = TextureMap.all(ModBlocks.TRANSPORTER);
        TextureMap textureMap2 = TextureMap.sideEnd(TextureMap.getSubId(ModBlocks.TRANSPORTER, "_side"), textureMap.getTexture(TextureKey.TOP));
        Identifier identifier = Models.SLAB.upload(ModBlocks.TRANSPORTER, textureMap2, blockStateModelGenerator.modelCollector);
        Identifier identifier2 = Models.SLAB_TOP.upload(ModBlocks.TRANSPORTER, textureMap2, blockStateModelGenerator.modelCollector);
        Identifier identifier3 = Models.CUBE_COLUMN.uploadWithoutVariant(ModBlocks.TRANSPORTER, "_double", textureMap2, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(createSlabBlockState(ModBlocks.TRANSPORTER, identifier, identifier2, identifier3));
       // blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(ModBlocks.TRANSPORTER, Models.CUBE_ALL.upload(ModBlocks.TRANSPORTER, textureMap, blockStateModelGenerator.modelCollector)));
        */
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.HANDBELL, Models.GENERATED);
        //itemModelGenerator.register(ModItems.RAW_CITRINE, Models.GENERATED);
    }

    /*
    BlockStateSupplier TransporterStateSupplier = createSlabBlockState(
            (Block) ModBlocks.TRANSPORTER,
            new Identifier(InfernalMod.MOD_ID, "infernal-mod.transporter_bottom"),
            new Identifier(InfernalMod.MOD_ID, "infernal-mod.transporter_top"),
            new Identifier(InfernalMod.MOD_ID, "infernal-mod.transporter_full")
    );
    blockStateModelGenerator.blockStateCollector.accept(TransporterStateSupplier);
    blockStateModelGenerator.registerParentedItemModel(TransporterStateSupplier, new Identifier(InfernalMod.MOD_ID, "infernal-mod.transporter_full"));
    */
    /*
    blockStateModelGenerator.registerDoubleBlock(
            ModBlocks.TRANSPORTER,
            new Identifier(InfernalMod.MOD_ID, "infernal-mod.transporter_top"),
            new Identifier(InfernalMod.MOD_ID, "infernal-mod.transporter_bottom")
    );
            BlockStateModelGenerator.createSlabBlockState(
                    ModBlocks.TRANSPORTER,
                    new Identifier(InfernalMod.MOD_ID, "infernal-mod.transporter_bottom"),
                    new Identifier(InfernalMod.MOD_ID, "infernal-mod.transporter_top"),
                    new Identifier(InfernalMod.MOD_ID, "infernal-mod.transporter_full")
            )
     */

}
