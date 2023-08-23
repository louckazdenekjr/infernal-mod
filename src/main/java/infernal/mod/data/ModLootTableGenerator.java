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

        // with and without silk touch
        //addDrop(ModBlocks.CITRINE_ORE, oreDrops(ModBlocks.CITRINE_ORE, ModItems.RAW_CITRINE));
    }
}