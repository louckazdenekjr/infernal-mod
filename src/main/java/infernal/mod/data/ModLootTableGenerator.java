package infernal.mod.data;


import infernal.mod.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;


public class ModLootTableGenerator extends FabricBlockLootTableProvider {
    public ModLootTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        // drops itself
        addDrop(ModBlocks.BEJEWELED_DEEPSLATE);
        addDrop(ModBlocks.BEJEWELED_SANDSTONE);
        addDrop(ModBlocks.CALCITE_BRICKS);
        addDrop(ModBlocks.CRACKED_CALCITE_BRICKS);
        addDrop(ModBlocks.BEJEWELED_CALCITE_BRICKS);
        addDrop(ModBlocks.BEJEWELED_STONE_BRICKS);
        addDrop(ModBlocks.POLISHED_TERRACOTTA);
        addDrop(ModBlocks.BEJEWELED_TERRACOTTA);
        addDrop(ModBlocks.POLISHED_CALCITE);

        // with and without silk touch
        //addDrop(ModBlocks.CITRINE_ORE, oreDrops(ModBlocks.CITRINE_ORE, ModItems.RAW_CITRINE));
    }
}