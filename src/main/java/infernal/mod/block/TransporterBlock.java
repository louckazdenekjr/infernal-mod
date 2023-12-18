package infernal.mod.block;

import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
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

public class TransporterBlock extends Block {
    public static final DirectionProperty FACING;
    protected static final VoxelShape SHAPE;
    private static final Text TITLE = Text.translatable("container.transporter");
    public NbtCompound TARGET_POSITION = null;
    public String TARGET_DIMENSION = null;

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
            }

            player.sendMessage(Text.of("NBT Data:"));
            player.sendMessage(Text.of(String.valueOf(TARGET_POSITION)));
            player.sendMessage(Text.of(TARGET_DIMENSION));
            player.sendMessage(Text.of(""));


            // if the teleporter is linked
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

                player.teleport(x,y,z);

                // play swing animation
                return ActionResult.SUCCESS;
            }


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

    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (!world.isClient) { // server side
            NbtCompound nbt = itemStack.getOrCreateNbt();
            setTransporterValues(
                    nbt.getCompound("TargetPos"),
                    nbt.getString("TargetDimension")
            );
        }
    }

    private void setTransporterValues(NbtCompound compound, String TargetDimension) {
        TARGET_POSITION = compound;
        TARGET_DIMENSION = TargetDimension;
    }

}
