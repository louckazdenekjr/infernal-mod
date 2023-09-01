package infernal.mod.data;


import com.google.common.collect.Lists;
import infernal.mod.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Util;

import java.util.List;
import java.util.function.Consumer;


public class ModRecipeGenerator extends FabricRecipeProvider {
    public static final List<ItemConvertible> CRACKED_CALCITE_BRICKS_PRE = Util.make(Lists.newArrayList(), list -> {
        list.add(ModBlocks.CALCITE_BRICKS);
    });

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
                .input('#', Items.POLISHED_DEEPSLATE)
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

        // offer recipe for calcite bricks
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CALCITE_BRICKS, 4)
                .pattern("##")
                .pattern("##")
                .input('#', ModBlocks.POLISHED_CALCITE)
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.POLISHED_CALCITE), conditionsFromItem((ModBlocks.POLISHED_CALCITE)))
                .offerTo(exporter);

        offerSmelting(
                exporter,
                CRACKED_CALCITE_BRICKS_PRE,
                RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.CRACKED_CALCITE_BRICKS,
                0.1f,
                200,
                "blocks"
        );

        // offer recipe for bejeweled calcite bricks
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BEJEWELED_CALCITE_BRICKS, 4)
                .pattern("###")
                .pattern("#X#")
                .pattern("###")
                .input('#', ModBlocks.CALCITE_BRICKS)
                .input('X', Items.AMETHYST_BLOCK)
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.CALCITE_BRICKS),
                        new InventoryChangedCriterion.Conditions(
                                EntityPredicate.Extended.EMPTY,
                                NumberRange.IntRange.ANY,
                                NumberRange.IntRange.ANY,
                                NumberRange.IntRange.ANY,
                                new ItemPredicate[]{
                                        ItemPredicate.Builder.create().items(new ItemConvertible[]{ModBlocks.CALCITE_BRICKS}).count(NumberRange.IntRange.ANY).build(),
                                        ItemPredicate.Builder.create().items(new ItemConvertible[]{Items.AMETHYST_BLOCK}).count(NumberRange.IntRange.ANY).build()
                                }
                        )
                )
                .offerTo(exporter);

        // offer recipe for bejeweled stone bricks
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BEJEWELED_STONE_BRICKS, 4)
                .pattern("###")
                .pattern("#X#")
                .pattern("###")
                .input('#', Blocks.CHISELED_STONE_BRICKS)
                .input('X', Items.LAPIS_BLOCK)
                .criterion(FabricRecipeProvider.hasItem(Blocks.CHISELED_STONE_BRICKS),
                        new InventoryChangedCriterion.Conditions(
                                EntityPredicate.Extended.EMPTY,
                                NumberRange.IntRange.ANY,
                                NumberRange.IntRange.ANY,
                                NumberRange.IntRange.ANY,
                                new ItemPredicate[]{
                                        ItemPredicate.Builder.create().items(new ItemConvertible[]{Blocks.CHISELED_STONE_BRICKS}).count(NumberRange.IntRange.ANY).build(),
                                        ItemPredicate.Builder.create().items(new ItemConvertible[]{Items.LAPIS_BLOCK}).count(NumberRange.IntRange.ANY).build()
                                }
                        )
                )
                .offerTo(exporter);

        // offer recipe for polished terracotta
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_TERRACOTTA, 4)
                .pattern("##")
                .pattern("##")
                .input('#', Blocks.TERRACOTTA)
                .criterion(FabricRecipeProvider.hasItem(Items.TERRACOTTA), conditionsFromItem((Items.TERRACOTTA)))
                .offerTo(exporter);

        // offer recipe for bejeweled terracotta
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BEJEWELED_TERRACOTTA, 4)
                .pattern("###")
                .pattern("#X#")
                .pattern("###")
                .input('#', ModBlocks.POLISHED_TERRACOTTA)
                .input('X', Items.EMERALD)
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.POLISHED_TERRACOTTA),
                        new InventoryChangedCriterion.Conditions(
                                EntityPredicate.Extended.EMPTY,
                                NumberRange.IntRange.ANY,
                                NumberRange.IntRange.ANY,
                                NumberRange.IntRange.ANY,
                                new ItemPredicate[]{
                                        ItemPredicate.Builder.create().items(new ItemConvertible[]{ModBlocks.POLISHED_TERRACOTTA}).count(NumberRange.IntRange.ANY).build(),
                                        ItemPredicate.Builder.create().items(new ItemConvertible[]{Items.EMERALD}).count(NumberRange.IntRange.ANY).build()
                                }
                        )
                )
                .offerTo(exporter);

        // offer recipe for polished calcite
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_CALCITE, 4)
                .pattern("##")
                .pattern("##")
                .input('#', Blocks.CALCITE)
                .criterion(FabricRecipeProvider.hasItem(Items.CALCITE), conditionsFromItem((Items.CALCITE)))
                .offerTo(exporter);

    }
}

// TODO: add find condition for handbell
// TODO: figure out what is the condition to get a smelting recipe in vanilla!
// TODO: hi-res for bej stone bricks and others
// TODO: add recipe for transporter after adding cursed metal