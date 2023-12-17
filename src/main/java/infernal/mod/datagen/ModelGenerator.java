package infernal.mod.datagen;


import infernal.mod.block.Blocks;
import infernal.mod.item.Items;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;


public class ModelGenerator extends FabricModelProvider {
    public ModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.BEJEWELED_DEEPSLATE);
        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.BEJEWELED_SANDSTONE);
        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.POLISHED_CALCITE);
        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.CALCITE_BRICKS);
        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.CRACKED_CALCITE_BRICKS);
        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.BEJEWELED_CALCITE_BRICKS);
        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.BEJEWELED_STONE_BRICKS);
        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.POLISHED_TERRACOTTA);
        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.BEJEWELED_TERRACOTTA);
        blockStateModelGenerator.registerTrapdoor(Blocks.SHORT_RANGE_TRANSPORTER);

        //blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.TRANSPORTER);
        //
        //TexturedModel transporterModel = blockStateModelGenerator.texturedModels.getOrDefault(
                //        Blocks.TRANSPORTER,
        //        TexturedModel.CUBE_ALL.get(Blocks.TRANSPORTER)
                //);
        //
        //new BlockStateModelGenerator.BlockTexturePool(transporterModel.getTextures())).base(Blocks.TRANSPORTER, transporterModel.getModel();

        //Block block = net.minecraft.block.Blocks.OAK_PLANKS;
        //Identifier identifier = ModelIds.getBlockModelId(block);
        //TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(block);
        //Block block2 = net.minecraft.block.Blocks.PETRIFIED_OAK_SLAB;
        //Identifier identifier2 = Models.SLAB.upload(block2, texturedModel.getTextures(), this.modelCollector);
        //Identifier identifier3 = Models.SLAB_TOP.upload(block2, texturedModel.getTextures(), this.modelCollector);
        //this.blockStateCollector.accept(createSlabBlockState(block2, identifier2, identifier3, identifier));

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
        itemModelGenerator.register(Items.HANDBELL, Models.GENERATED);
        //itemModelGenerator.register(Blocks.transporterBlockItem, Models.GENERATED);
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
