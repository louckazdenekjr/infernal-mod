package infernal.mod.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.BlockState;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TransporterBlock
        extends TrapdoorBlock {
    public TransporterBlock(Settings settings, BlockSetType blockSetType) {

        super(settings, blockSetType);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            // client side
            // play activation sound
            this.playToggleSound(player, world, pos, state.get(OPEN));
        } else {
            // server side
            //boolean foundTransporter;

            /*
            player.teleport(
                    (double) transporters.get(i).getX() + 0.5,
                    (double) transporters.get(i).getY() + 0.5,
                    (double) transporters.get(i).getZ() + 0.5
            );
            */

            player.sendMessage(Text.of("Not implemented!"), false);
        }

        // return success
        return ActionResult.success(world.isClient);
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
                // TODO: implement this! redstone
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
// TODO: