package infernal.mod.block;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

import static infernal.mod.InfernalMod.LOGGER;
import static infernal.mod.InfernalMod.MOD_ID;
import static net.minecraft.registry.Registries.BLOCK;
import static net.minecraft.registry.Registries.ITEM;


public class Blocks {
    // register bejeweled deepslate
    public static final Block BEJEWELED_DEEPSLATE = registerBlock(
            "bejeweled_deepslate",
            new Block(FabricBlockSettings.copyOf(net.minecraft.block.Blocks.POLISHED_DEEPSLATE)),
            ItemGroups.BUILDING_BLOCKS
    );

    // register bejeweled sandstone
    public static final Block BEJEWELED_SANDSTONE = registerBlock(
            "bejeweled_sandstone",
            new Block(FabricBlockSettings.copyOf(net.minecraft.block.Blocks.CUT_SANDSTONE)),
            ItemGroups.BUILDING_BLOCKS
    );

    // register polished calcite
    public static final Block POLISHED_CALCITE = registerBlock(
            "polished_calcite",
            new Block(FabricBlockSettings.copyOf(net.minecraft.block.Blocks.CALCITE)),
            ItemGroups.BUILDING_BLOCKS
    );

    // register calcite bricks
    public static final Block CALCITE_BRICKS = registerBlock(
            "calcite_bricks",
            new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_CALCITE)),
            ItemGroups.BUILDING_BLOCKS
    );

    // register cracked calcite bricks
    public static final Block CRACKED_CALCITE_BRICKS = registerBlock(
            "cracked_calcite_bricks",
            new Block(FabricBlockSettings.copyOf(Blocks.CALCITE_BRICKS)),
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
            new Block(FabricBlockSettings.copyOf(net.minecraft.block.Blocks.CHISELED_STONE_BRICKS)),
            ItemGroups.BUILDING_BLOCKS
    );

    // register bejeweled bejeweled terracota
    public static final Block POLISHED_TERRACOTTA = registerBlock(
            "polished_terracotta",
            new Block(FabricBlockSettings.copyOf(net.minecraft.block.Blocks.TERRACOTTA)),
            ItemGroups.BUILDING_BLOCKS
    );

    // register bejeweled bejeweled terracota
    public static final Block BEJEWELED_TERRACOTTA = registerBlock(
            "bejeweled_terracotta",
            new Block(FabricBlockSettings.copyOf(POLISHED_TERRACOTTA)),
            ItemGroups.BUILDING_BLOCKS
    );

    public static final ShortRangeTransporterBlock SHORT_RANGE_TRANSPORTER = (ShortRangeTransporterBlock) registerBlock(
            "short_range_transporter",
            new ShortRangeTransporterBlock(FabricBlockSettings.copyOf(net.minecraft.block.Blocks.IRON_TRAPDOOR), BlockSetType.IRON),
            // TODO: after implementing cursed steel, change this material
            ItemGroups.REDSTONE // TODO: implement redstone trigger as condition
    );

    public static final Block transporterBlockBlock = new TransporterBlock(FabricBlockSettings.copyOf(net.minecraft.block.Blocks.BLACKSTONE_SLAB).nonOpaque());
    public static final BlockItem transporterBlockItem = (BlockItem) new TransporterBlockItem(transporterBlockBlock, new FabricItemSettings());
    public static final TransporterBlock TRANSPORTER = (TransporterBlock) registerBlockWithCustomItem(
            "transporter",
            transporterBlockBlock,
            // TODO: after implementing cursed steel, change this material
            ItemGroups.REDSTONE, // TODO: implement redstone trigger as condition
            transporterBlockItem
    );

    //----------------------------------------

    private static Block registerBlock(String name, Block block, ItemGroup group) {
        // register block item
        Item item = Registry.register(ITEM, new Identifier(MOD_ID, name), new BlockItem(block, new FabricItemSettings()));
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));

        // register block
        return Registry.register(BLOCK, new Identifier(MOD_ID, name), block);
    }

    private static Block registerBlockWithCustomItem(String name, Block block, ItemGroup group, BlockItem blockItem) {
        // register block with a custom block item
        Item item = Registry.register(ITEM, new Identifier(MOD_ID, name), blockItem);
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));

        // register block
        return Registry.register(BLOCK, new Identifier(MOD_ID, name), block);
    }

    public static void registerModBlocks() {
        LOGGER.info("Registering ModBlocks");
    }

    public static void registerTransparentBlocks() {
        BlockRenderLayerMap.INSTANCE.putBlock(SHORT_RANGE_TRANSPORTER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TRANSPORTER, RenderLayer.getCutout());
    }

    public static List<BlockPos> getBlocksInVicinity(World world, BlockPos centerPos, int radius) {
        List<BlockPos> blocksInVicinity = new ArrayList<>();

        for (int x = centerPos.getX() - radius; x <= centerPos.getX() + radius; x++) {
            for (int y = centerPos.getY() - radius; y <= centerPos.getY() + radius; y++) {
                for (int z = centerPos.getZ() - radius; z <= centerPos.getZ() + radius; z++) {
                    BlockPos currentPos = new BlockPos(x, y, z);
                    //BlockState blockState = world.getBlockState(currentPos);
                    blocksInVicinity.add(currentPos);
                }
            }
        }

        return blocksInVicinity;
    }

    public static List<BlockPos> getBlocksInVicinityByType(World world, BlockPos centerPos, int radius, BlockState targetType) {
        List<BlockPos> blocksInVicinity = new ArrayList<>();

        for (int x = centerPos.getX() - radius; x <= centerPos.getX() + radius; x++) {
            for (int y = centerPos.getY() - radius; y <= centerPos.getY() + radius; y++) {
                for (int z = centerPos.getZ() - radius; z <= centerPos.getZ() + radius; z++) {
                    BlockPos currentPos = new BlockPos(x, y, z);
                    BlockState blockState = world.getBlockState(currentPos);

                    // Apply the filter to check if the block matches the desired type
                    if (targetType.getBlock() == blockState.getBlock()) {
                        blocksInVicinity.add(currentPos);
                    }
                }
            }
        }

        return blocksInVicinity;
    }
}


