package net.kaupenjoe.tutorialmod.data;

import infernal.mod.block.ModBlocks;
import infernal.mod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.Consumer;


public class ModRecipeGenerator extends FabricRecipeProvider {
    public ModRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        //offer

        // JUST AN EXAMPLE
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.BEJEWELED_DEEPSLATE)
                .pattern("###")
                .pattern("#X#")
                .pattern("###")
                .input('#', Items.DEEPSLATE)
                .input('X', ModBlocks.BEJEWELED_DEEPSLATE)
                .criterion(FabricRecipeProvider.hasItem(Items.DEEPSLATE,
                        FabricRecipeProvider.conditionsFromItem(Items.DEEPSLATE))
                .criterion(FabricRecipeProvider.hasItem(Items.DIAMOND),
                        FabricRecipeProvider.conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ModBlocks.BEJEWELED_DEEPSLATE)
            )
        ));
    }
}