package infernal.mod;


import infernal.mod.datagen.BlockTagGenerator;
import infernal.mod.datagen.LootTableGenerator;
import infernal.mod.datagen.ModelGenerator;
import infernal.mod.datagen.RecipeGenerator;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;


public class InfernalModDataGen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(LootTableGenerator::new);
        pack.addProvider(RecipeGenerator::new);
        pack.addProvider(ModelGenerator::new);
        pack.addProvider(BlockTagGenerator::new);
    }
}