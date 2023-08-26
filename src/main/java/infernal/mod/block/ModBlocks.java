package infernal.mod.block;

import infernal.mod.InfernalMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ModBlocks {
    // register bejeweled deepslate
    public static final Block BEJEWELED_DEEPSLATE = registerBlock(
            "bejeweled_deepslate",
            new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DEEPSLATE)),
            ItemGroups.BUILDING_BLOCKS
        );

    // register bejeweled sandstone
    public static final Block BEJEWELED_SANDSTONE = registerBlock(
            "bejeweled_sandstone",
            new Block(FabricBlockSettings.copyOf(Blocks.CUT_SANDSTONE)),
            ItemGroups.BUILDING_BLOCKS
    );

    // register calcite bricks
    public static final Block CALCITE_BRICKS = registerBlock(
            "calcite_bricks",
            new Block(FabricBlockSettings.copyOf(Blocks.CALCITE)),
            ItemGroups.BUILDING_BLOCKS
    );

    // register cracked calcite bricks
    public static final Block CRACKED_CALCITE_BRICKS = registerBlock(
            "cracked_calcite_bricks",
            new Block(FabricBlockSettings.copyOf(ModBlocks.CALCITE_BRICKS)),
            ItemGroups.BUILDING_BLOCKS
    );

    // register bejeweled calcite bricks
    public static final Block BEJEWELED_CALCITE_BRICKS = registerBlock(
            "bejeweled_calcite_bricks",
            new Block(FabricBlockSettings.copyOf(CALCITE_BRICKS)),
            ItemGroups.BUILDING_BLOCKS
    );

    // register bejeweled stone bricks
    public static final Block BEJEWELED_STONE_BRICKS = registerBlock(
            "bejeweled_stone_bricks",
            new Block(FabricBlockSettings.copyOf(Blocks.CHISELED_STONE_BRICKS)),
            ItemGroups.BUILDING_BLOCKS
    );

    // register bejeweled bejeweled terracota
    public static final Block POLISHED_TERRACOTTA = registerBlock(
            "polished_terracotta",
            new Block(FabricBlockSettings.copyOf(Blocks.TERRACOTTA)),
            ItemGroups.BUILDING_BLOCKS
    );

    // register bejeweled bejeweled terracota
    public static final Block BEJEWELED_TERRACOTTA = registerBlock(
            "bejeweled_terracotta",
            new Block(FabricBlockSettings.copyOf(POLISHED_TERRACOTTA)),
            ItemGroups.BUILDING_BLOCKS
    );


    private static Item registerBlockItem(String name, Block block, ItemGroup group) {
        Item item = Registry.register(Registries.ITEM, new Identifier(InfernalMod.MOD_ID, name), new BlockItem(block, new FabricItemSettings()));
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
        return item;
    }

    private static Block registerBlock(String name, Block block, ItemGroup group) {
        registerBlockItem(name, block, group);
        return Registry.register(Registries.BLOCK, new Identifier(InfernalMod.MOD_ID, name), block);
    }

    public static void registerModBlocks() {
        InfernalMod.LOGGER.info("Registering ModBlocks");
    }
}
