package infernal.mod.datagen;


import com.google.common.collect.Lists;
import infernal.mod.block.Blocks;
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
import net.minecraft.util.Util;

import java.util.List;
import java.util.function.Consumer;


public class RecipeGenerator extends FabricRecipeProvider {
    public static final List<ItemConvertible> CRACKED_CALCITE_BRICKS_PRE = Util.make(Lists.newArrayList(), list -> {
        list.add(Blocks.CALCITE_BRICKS);
    });

    public RecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        //offerSmelting(exporter, List.of(ModItems.RAW_CITRINE), RecipeCategory.MISC, ModItems.CITRINE,
        //        0.7f, 200, "citrine");

        //offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.CITRINE, RecipeCategory.DECORATIONS,
        //        ModBlocks.CITRINE_BLOCK);

        // offer recipe for bejeweled deepslate
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Blocks.BEJEWELED_DEEPSLATE, 4)
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
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Blocks.BEJEWELED_SANDSTONE, 4)
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
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Blocks.CALCITE_BRICKS, 4)
                .pattern("##")
                .pattern("##")
                .input('#', Blocks.POLISHED_CALCITE)
                .criterion(FabricRecipeProvider.hasItem(Blocks.POLISHED_CALCITE), conditionsFromItem((Blocks.POLISHED_CALCITE)))
                .offerTo(exporter);

        offerSmelting(
                exporter,
                CRACKED_CALCITE_BRICKS_PRE,
                RecipeCategory.BUILDING_BLOCKS,
                Blocks.CRACKED_CALCITE_BRICKS,
                0.1f,
                200,
                "blocks"
        );

        // offer recipe for bejeweled calcite bricks
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Blocks.BEJEWELED_CALCITE_BRICKS, 4)
                .pattern("###")
                .pattern("#X#")
                .pattern("###")
                .input('#', Blocks.CALCITE_BRICKS)
                .input('X', Items.AMETHYST_BLOCK)
                .criterion(FabricRecipeProvider.hasItem(Blocks.CALCITE_BRICKS),
                        new InventoryChangedCriterion.Conditions(
                                EntityPredicate.Extended.EMPTY,
                                NumberRange.IntRange.ANY,
                                NumberRange.IntRange.ANY,
                                NumberRange.IntRange.ANY,
                                new ItemPredicate[]{
                                        ItemPredicate.Builder.create().items(new ItemConvertible[]{Blocks.CALCITE_BRICKS}).count(NumberRange.IntRange.ANY).build(),
                                        ItemPredicate.Builder.create().items(new ItemConvertible[]{Items.AMETHYST_BLOCK}).count(NumberRange.IntRange.ANY).build()
                                }
                        )
                )
                .offerTo(exporter);

        // offer recipe for bejeweled stone bricks
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Blocks.BEJEWELED_STONE_BRICKS, 4)
                .pattern("###")
                .pattern("#X#")
                .pattern("###")
                .input('#', net.minecraft.block.Blocks.CHISELED_STONE_BRICKS)
                .input('X', Items.LAPIS_BLOCK)
                .criterion(FabricRecipeProvider.hasItem(net.minecraft.block.Blocks.CHISELED_STONE_BRICKS),
                        new InventoryChangedCriterion.Conditions(
                                EntityPredicate.Extended.EMPTY,
                                NumberRange.IntRange.ANY,
                                NumberRange.IntRange.ANY,
                                NumberRange.IntRange.ANY,
                                new ItemPredicate[]{
                                        ItemPredicate.Builder.create().items(new ItemConvertible[]{net.minecraft.block.Blocks.CHISELED_STONE_BRICKS}).count(NumberRange.IntRange.ANY).build(),
                                        ItemPredicate.Builder.create().items(new ItemConvertible[]{Items.LAPIS_BLOCK}).count(NumberRange.IntRange.ANY).build()
                                }
                        )
                )
                .offerTo(exporter);

        // offer recipe for polished terracotta
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_TERRACOTTA, 4)
                .pattern("##")
                .pattern("##")
                .input('#', net.minecraft.block.Blocks.TERRACOTTA)
                .criterion(FabricRecipeProvider.hasItem(Items.TERRACOTTA), conditionsFromItem((Items.TERRACOTTA)))
                .offerTo(exporter);

        // offer recipe for bejeweled terracotta
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Blocks.BEJEWELED_TERRACOTTA, 4)
                .pattern("###")
                .pattern("#X#")
                .pattern("###")
                .input('#', Blocks.POLISHED_TERRACOTTA)
                .input('X', Items.EMERALD)
                .criterion(FabricRecipeProvider.hasItem(Blocks.POLISHED_TERRACOTTA),
                        new InventoryChangedCriterion.Conditions(
                                EntityPredicate.Extended.EMPTY,
                                NumberRange.IntRange.ANY,
                                NumberRange.IntRange.ANY,
                                NumberRange.IntRange.ANY,
                                new ItemPredicate[]{
                                        ItemPredicate.Builder.create().items(new ItemConvertible[]{Blocks.POLISHED_TERRACOTTA}).count(NumberRange.IntRange.ANY).build(),
                                        ItemPredicate.Builder.create().items(new ItemConvertible[]{Items.EMERALD}).count(NumberRange.IntRange.ANY).build()
                                }
                        )
                )
                .offerTo(exporter);

        // offer recipe for polished calcite
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Blocks.POLISHED_CALCITE, 4)
                .pattern("##")
                .pattern("##")
                .input('#', net.minecraft.block.Blocks.CALCITE)
                .criterion(FabricRecipeProvider.hasItem(Items.CALCITE), conditionsFromItem((Items.CALCITE)))
                .offerTo(exporter);

    }
}

// TODO: add find condition for handbell
// TODO: figure out what is the condition to get a smelting recipe in vanilla!
// TODO: hi-res for bej stone bricks and others
// TODO: add recipe for transporter after adding cursed metal