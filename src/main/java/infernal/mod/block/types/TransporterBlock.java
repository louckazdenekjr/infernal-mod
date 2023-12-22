package infernal.mod.block.types;

import infernal.mod.block.Blocks;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.screen.StonecutterScreenHandler;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class TransporterBlock extends BlockWithEntity {
    public static final DirectionProperty FACING;
    protected static final VoxelShape SHAPE;
    private static final Text TITLE = Text.translatable("container.transporter");

    static {
        FACING = HorizontalFacingBlock.FACING;
        SHAPE = SlabBlock.BOTTOM_SHAPE;
    }

    public TransporterBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) { // server side
            // if another teleporter in hand
            ItemStack heldItem = player.getStackInHand(hand);
            if (!heldItem.isEmpty() && heldItem.getItem() == Blocks.transporterBlockItem) {
                return ActionResult.FAIL;
                // FAIL goes to item actions
            }

            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof TransporterBlockEntity) {
                ((TransporterBlockEntity) blockEntity).teleportPlayer(player);
            }


            // if the teleporter is linked
            /*
            if (!TARGET_DIMENSION.isEmpty() && !TARGET_POSITION.isEmpty()) {
                // TODO: implement world check
                // TODO: implement both sided linking

                // teleport player
                player.sendMessage(Text.of("TELEPORTING:"));

                double x = TARGET_POSITION.getInt("X") + 0.5;
                double y = TARGET_POSITION.getInt("Y") + 0.5;
                double z = TARGET_POSITION.getInt("Z") + 0.5;

                player.sendMessage(Text.of(String.valueOf(x)));
                player.sendMessage(Text.of(String.valueOf(y)));
                player.sendMessage(Text.of(String.valueOf(z)));
                player.sendMessage(Text.of(""));

                player.teleport(x, y, z);

                // play swing animation
                return ActionResult.SUCCESS;
            }
             */


            // otherwise
            return ActionResult.PASS;
        } else { // client side
            //player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
            //player.incrementStat(Stats.INTERACT_WITH_STONECUTTER);
            //return ActionResult.CONSUME;
            return ActionResult.PASS;
        }
    }

    @Nullable
    public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        return new SimpleNamedScreenHandlerFactory((syncId, playerInventory, player) -> {
            return new StonecutterScreenHandler(syncId, playerInventory, ScreenHandlerContext.create(world, pos));
        }, TITLE);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    public boolean hasSidedTransparency(BlockState state) {
        return true;
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
            NbtCompound nbt = itemStack.getOrCreateNbt();
            setTransporterValues(
                    world,
                    pos,
                    placer,
                    nbt
            );
        }

    private void setTransporterValues(World world, BlockPos pos, LivingEntity placer, NbtCompound nbt) {
        if (world.isClient) { // client side
            //world.playSound(
            //        (double) pos.getX(),
            //        (double) pos.getY(),
            //        (double) pos.getZ(),
            //        SoundEvents.BLOCK_PISTON_EXTEND,
            //        SoundCategory.MASTER,
            //        1.0f,
            //        1.0f,
            //        false
            //);
            //placer.sendMessage(Text.of("CLIENT TRYING TO DO STUFF"));
        } else { // server side
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof TransporterBlockEntity) {
                NbtCompound TARGET_POSITION = nbt.getCompound("TargetPos");
                double x = TARGET_POSITION.getInt("X") + 0.5;
                double y = TARGET_POSITION.getInt("Y") + 0.5;
                double z = TARGET_POSITION.getInt("Z") + 0.5;

                NbtElement TARGET_DIMENSION = nbt.get("TargetDimension");

                ((TransporterBlockEntity) blockEntity).acceptParameter(x,y,z,TARGET_DIMENSION);
            }
        }
    }


    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        TransporterBlockEntity blockEntity = new TransporterBlockEntity(pos, state);

        //blockEntity.acceptParameter(player);
        return blockEntity;
    }
}
