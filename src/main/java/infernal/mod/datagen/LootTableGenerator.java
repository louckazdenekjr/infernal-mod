package infernal.mod.datagen;


import infernal.mod.block.Blocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;


public class LootTableGenerator extends FabricBlockLootTableProvider {
    public LootTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        // drops itself
        addDrop(Blocks.BEJEWELED_DEEPSLATE);
        addDrop(Blocks.BEJEWELED_SANDSTONE);
        addDrop(Blocks.POLISHED_CALCITE);
        addDrop(Blocks.CALCITE_BRICKS);
        addDrop(Blocks.CRACKED_CALCITE_BRICKS);
        addDrop(Blocks.BEJEWELED_CALCITE_BRICKS);
        addDrop(Blocks.BEJEWELED_STONE_BRICKS);
        addDrop(Blocks.POLISHED_TERRACOTTA);
        addDrop(Blocks.BEJEWELED_TERRACOTTA);
        addDrop(Blocks.SHORT_RANGE_TRANSPORTER);
        addDrop(Blocks.TRANSPORTER);


        // with and without silk touch
        //addDrop(ModBlocks.CITRINE_ORE, oreDrops(ModBlocks.CITRINE_ORE, ModItems.RAW_CITRINE));
    }
}