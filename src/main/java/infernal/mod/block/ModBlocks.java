package infernal.mod.block;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import static infernal.mod.InfernalMod.*;


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

    // register polished calcite
    public static final Block POLISHED_CALCITE = registerBlock(
            "polished_calcite",
            new Block(FabricBlockSettings.copyOf(Blocks.CALCITE)),
            ItemGroups.BUILDING_BLOCKS
    );

    // register calcite bricks
    public static final Block CALCITE_BRICKS = registerBlock(
            "calcite_bricks",
            new Block(FabricBlockSettings.copyOf(ModBlocks.POLISHED_CALCITE)),
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

    /*
    // register transporter
    public static final SlabBlock TRANSPORTER = (SlabBlock) registerBlock(
            "transporter",
            new SlabBlock(FabricBlockSettings.of(Material.METAL)),
            // TODO: after implementing cursed steel, change this material
            ItemGroups.REDSTONE // TODO: implement redstone trigger as condition
    );
    */

    public static final TransporterBlock TRANSPORTER = (TransporterBlock) registerBlock(
            "transporter",
            new TransporterBlock(FabricBlockSettings.copyOf(Blocks.IRON_TRAPDOOR), BlockSetType.IRON),
            // TODO: after implementing cursed steel, change this material
            ItemGroups.REDSTONE // TODO: implement redstone trigger as condition
    );

    //----------------------------------------

    private static Block registerBlock(String name, Block block, ItemGroup group) {
        // register block item
        Item item = Registry.register(Registries.ITEM, new Identifier(MOD_ID, name), new BlockItem(block, new FabricItemSettings()));
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));

        // register block
        return Registry.register(Registries.BLOCK, new Identifier(MOD_ID, name), block);
    }

    public static void registerModBlocks() {
        LOGGER.info("Registering ModBlocks");
    }

    public static void registerTransparentBlocks() {
        BlockRenderLayerMap.INSTANCE.putBlock(TRANSPORTER, RenderLayer.getCutout());
    }

    public static class TransporterBlock
    extends TrapdoorBlock {
        public TransporterBlock(Settings settings, BlockSetType blockSetType) {
            super(settings, blockSetType);
        }

        /*
            public TransporterBlock(AbstractBlock.Settings settings, BlockSetType blockSetType) {
                super(settings.sounds(blockSetType.soundType()), blockSetType);
                this.setDefaultState(
                    this.stateManager
                        .getDefaultState()
                        .with(FACING, Direction.NORTH)
                        .with(OPEN,false)
                        .with(HALF, BlockHalf.BOTTOM)
                        .with(POWERED, false)
                        .with(WATERLOGGED, false)
                )
            }
         */

        @Override
        public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
            if (this.material == Material.METAL) {
                return ActionResult.PASS;
            }
            state = state.cycle(OPEN);
            world.setBlockState(pos, state, Block.NOTIFY_LISTENERS);
            if (state.get(WATERLOGGED).booleanValue()) {
                world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
            }
            this.playToggleSound(player, world, pos, state.get(OPEN));
            return ActionResult.success(world.isClient);
        }

        @Override
        protected void playToggleSound(@Nullable PlayerEntity player, World world, BlockPos pos, boolean open) {
            //world.playSound(player, pos, open ? this.blockSetType.trapdoorOpen() : this.blockSetType.trapdoorClose(), SoundCategory.BLOCKS, 1.0f, world.getRandom().nextFloat() * 0.1f + 0.9f);
            world.playSound(player, pos, SoundEvent.of(SoundEvents.BLOCK_NOTE_BLOCK_BELL.value().getId()), SoundCategory.BLOCKS, 1.0f, 1.0f);
            world.emitGameEvent(player, open ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
        }

        @Override
        public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
            if (world.isClient) {
                return;
            }
            boolean bl = world.isReceivingRedstonePower(pos);
            if (bl != state.get(POWERED)) {
                if (state.get(OPEN) != bl) {
                    state = (BlockState)state.with(OPEN, bl);
                    this.playToggleSound(null, world, pos, bl);
                }
                world.setBlockState(pos, (BlockState)state.with(POWERED, bl), Block.NOTIFY_LISTENERS);
                if (state.get(WATERLOGGED).booleanValue()) {
                    world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
                }
            }
        }

    }
}
