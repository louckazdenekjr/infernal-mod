package infernal.mod.block;

import infernal.mod.InfernalMod;
import infernal.mod.item.ModItemGroup;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
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
            new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE)),
            ItemGroups.BUILDING_BLOCKS
        );

    // register bejeweled sandstone
    public static final Block BEJEWELED_SANDSTONE = registerBlock(
            "bejeweled_sandstone",
            new Block(FabricBlockSettings.copyOf(Blocks.CUT_SANDSTONE)),
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
