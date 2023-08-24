package infernal.mod.data;


import infernal.mod.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.book.RecipeCategory;
import java.util.function.Consumer;


public class ModRecipeGenerator extends FabricRecipeProvider {
    public ModRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        //offerSmelting(exporter, List.of(ModItems.RAW_CITRINE), RecipeCategory.MISC, ModItems.CITRINE,
        //        0.7f, 200, "citrine");

        //offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.CITRINE, RecipeCategory.DECORATIONS,
        //        ModBlocks.CITRINE_BLOCK);

        // offer recipe for bejeweled deepslate
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BEJEWELED_DEEPSLATE, 4)
            .pattern("###")
            .pattern("#X#")
            .pattern("###")
            .input('#', Items.DEEPSLATE)
            .input('X', Items.DIAMOND)
            .criterion(FabricRecipeProvider.hasItem(Items.DEEPSLATE),
                //CriterionConditions()
                new InventoryChangedCriterion.Conditions(
                    EntityPredicate.Extended.EMPTY,
                    NumberRange.IntRange.ANY,
                    NumberRange.IntRange.ANY,
                    NumberRange.IntRange.ANY,
                    new ItemPredicate[]{
                        ItemPredicate.Builder.create().items(new ItemConvertible[]{Items.DEEPSLATE}).count(NumberRange.IntRange.ANY).build(),
                        ItemPredicate.Builder.create().items(new ItemConvertible[]{Items.DIAMOND}).count(NumberRange.IntRange.ANY).build()
                    }
                )
            )
            .offerTo(exporter);

        // offer recipe for bejeweled sandstone
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BEJEWELED_SANDSTONE, 4)
                .pattern("###")
                .pattern("#X#")
                .pattern("###")
                .input('#', Items.CUT_SANDSTONE)
                .input('X', Items.REDSTONE_BLOCK)
                .criterion(FabricRecipeProvider.hasItem(Items.CUT_SANDSTONE),
                        new InventoryChangedCriterion.Conditions(
                                EntityPredicate.Extended.EMPTY,
                                NumberRange.IntRange.ANY,
                                NumberRange.IntRange.ANY,
                                NumberRange.IntRange.ANY,
                                new ItemPredicate[]{
                                        ItemPredicate.Builder.create().items(new ItemConvertible[]{Items.CUT_SANDSTONE}).count(NumberRange.IntRange.ANY).build(),
                                        ItemPredicate.Builder.create().items(new ItemConvertible[]{Items.REDSTONE_BLOCK}).count(NumberRange.IntRange.ANY).build()
                                }
                        )
                )
                .offerTo(exporter);

        // offer recipe for bejeweled sandstone
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CALCITE_BRICKS, 4)
                .pattern("##")
                .pattern("##")
                .input('#', Items.CALCITE)
                .criterion(FabricRecipeProvider.hasItem(Items.CALCITE), conditionsFromItem((Items.CALCITE))
                )
                .offerTo(exporter);

    }
}
