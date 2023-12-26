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
        // FIXME animation and sound conditions are not clearly defined
        // is another transporter in hand?
        ItemStack heldItem = player.getStackInHand(hand);
        boolean transporterInHand = !heldItem.isEmpty() && heldItem.getItem() == Blocks.transporterBlockItem;

        // can own entity be found
        boolean shouldTeleport = false;
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof TransporterBlockEntity) {
            // TODO: check that own entity is paired
            shouldTeleport = true;
        }

        if (transporterInHand) {
            return ActionResult.FAIL; // FAIL goes to item actions
        }

        if (shouldTeleport) {
            //player.playSound();
            //player.playSound(SoundEvents.ENTITY_ELDER_GUARDIAN_AMBIENT, SoundCategory.MASTER, 1.0f, 0.8f);

            // play sound at own position
            world.playSound(
                pos.getX(),
                pos.getY(),
                pos.getZ(),
                SoundEvents.ENTITY_ELDER_GUARDIAN_AMBIENT,
                SoundCategory.BLOCKS,
                1.0f,
                0.8f,
                true
            );

            // play sound at target position
            /*
            int[] targetCoordinates = ((TransporterBlockEntity) blockEntity).getTargetCoordinates();
            player.sendMessage(Text.of(targetCoordinates.toString()));
            world.playSound(
                targetCoordinates[0],
                targetCoordinates[1],
                targetCoordinates[2],
                SoundEvents.ENTITY_ELDER_GUARDIAN_AMBIENT,
                SoundCategory.BLOCKS,
                1.0f,
                0.8f,
                true
            );
            */

            ((TransporterBlockEntity) blockEntity).teleportPlayer(player);
        }
        // play animation, should not be PASS - gives block placement sound
        return ActionResult.success(world.isClient());

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
            setEntityValues(world, pos, nbt);
        }

    private void setEntityValues(World world, BlockPos pos, NbtCompound nbt) {
        if (!world.isClient) { // server side
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof TransporterBlockEntity) {
                boolean INSCRIBED = nbt.getBoolean("Inscribed");
                if (INSCRIBED) {
                    NbtCompound TARGET_POSITION = nbt.getCompound("TargetPos");
                    NbtElement TARGET_DIMENSION = nbt.get("TargetDimension");
                    ((TransporterBlockEntity) blockEntity).attemptPairing(TARGET_POSITION, TARGET_DIMENSION);
                }
            }
        }
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        // do entity unlinking
        if (!world.isClient) { // server side
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof TransporterBlockEntity) {
                ((TransporterBlockEntity) blockEntity).processUnpairingRequest();
            }
        }

        // call super on break
        super.onBreak(world, pos, state, player);
    }


    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        TransporterBlockEntity blockEntity = new TransporterBlockEntity(pos, state);

        //blockEntity.acceptParameter(player);
        return blockEntity;
    }
}
// TODO: register mining speed and drops itself