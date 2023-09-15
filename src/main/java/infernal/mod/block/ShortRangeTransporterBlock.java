package infernal.mod.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.BlockState;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ShortRangeTransporterBlock
        extends TrapdoorBlock {
    private PlayerEntity user;

    public ShortRangeTransporterBlock(Settings settings, BlockSetType blockSetType) {

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
        /*
        if (this.material == Material.METAL) {
            return ActionResult.PASS;
        }
        state = state.cycle(OPEN);
        world.setBlockState(pos, state, Block.NOTIFY_LISTENERS);

        // update fluids after changing shape
        if (state.get(WATERLOGGED).booleanValue()) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        */

        if (world.isClient) {
            // client side
            // play activation sound
            this.playToggleSound(player, world, pos, state.get(OPEN));
        } else {
            this.user = player;
            world.scheduleBlockTick(new BlockPos(pos), this, 20);
            // FIXME: add ticking variable and check it to avoid scheduling multiple ticks
        }

        // return success
        return ActionResult.success(world.isClient);
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.isClient) {
            // client side
        } else {
            //
            // server side

            /*
            Item mainHand = player.getMainHandStack().getItem();
            Item offHand = player.getOffHandStack().getItem();
            Item TransporterInstance = state.getBlock().asItem();
            if (mainHand == TransporterInstance || offHand == TransporterInstance) {
                //(TransporterBlock) hand;
                //player.swingHand(hand);
                return ActionResult.SUCCESS;
            }

             */

            // if no transporter in hand
            //boolean foundTransporter;
            List<BlockPos> transporters = Blocks.getBlocksInVicinityByType(world, pos, 64, state);

            // exclude current block from list
            for (int i = 0; i < transporters.size(); i++) {
                if (transporters.get(i).asLong() == pos.asLong()) {
                    transporters.remove(i);
                }
            }
            // TODO: go to closest!
            // teleport to the nearest transporter
            //player.sendMessage(Text.of("Found (" + transporters.size() + ") transporters in vicinity!"), false);

            for (int i = 0; i < transporters.size(); i++) {
                //player.sendMessage(Text.of("Found a transporter in vicinity!"), false);
                this.user.teleport(
                        (double) transporters.get(i).getX() + 0.5,
                        (double) transporters.get(i).getY() + 0.5,
                        (double) transporters.get(i).getZ() + 0.5
                );
            }
        }
    }

    @Override
    protected void playToggleSound(@Nullable PlayerEntity player, World world, BlockPos pos, boolean open) {
        //world.playSound(player, pos, open ? this.blockSetType.trapdoorOpen() : this.blockSetType.trapdoorClose(), SoundCategory.BLOCKS, 1.0f, world.getRandom().nextFloat() * 0.1f + 0.9f);
        world.playSound(player, pos, SoundEvent.of(SoundEvents.BLOCK_NOTE_BLOCK_BELL.value().getId()), SoundCategory.BLOCKS, 1.0f, 1.0f);
        //world.emitGameEvent(player, open ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        /*
        if (world.isClient) {
            // client side
        } else {
            boolean bl = world.isReceivingRedstonePower(pos);
            if (bl != state.get(POWERED)) {
                // TODO: implement this? redstone
                this.playToggleSound(null, world, pos, bl);
            }
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
        */
    }

}

// FIXME: when placing a transporter on activated block it becomes tilted
