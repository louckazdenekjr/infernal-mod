package infernal.mod.data;


import infernal.mod.InfernalMod;
import infernal.mod.block.ModBlocks;
import infernal.mod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.util.Identifier;


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
        blockStateModelGenerator.registerTrapdoor((Block) ModBlocks.TRANSPORTER);
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

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.HANDBELL, Models.GENERATED);
        //itemModelGenerator.register(ModItems.RAW_CITRINE, Models.GENERATED);
    }
}
