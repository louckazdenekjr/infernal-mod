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
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.screen.StonecutterScreenHandler;
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
            return ActionResult.SUCCESS; // TODO: implement hand animations relative to actions
        } else { // client side
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
            setEntityValues(world, pos, placer, nbt);
        }

    private void setEntityValues(World world, BlockPos pos, LivingEntity placer, NbtCompound nbt) {
        if (!world.isClient) { // server side
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof TransporterBlockEntity) {
                NbtCompound TARGET_POSITION = nbt.getCompound("TargetPos");
                NbtElement TARGET_DIMENSION = nbt.get("TargetDimension");
                ((TransporterBlockEntity) blockEntity).attemptPairing(TARGET_POSITION, TARGET_DIMENSION, (PlayerEntity) placer);
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
