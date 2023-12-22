package infernal.mod.block.types;

import com.mojang.serialization.DataResult;
import infernal.mod.block.Blocks;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.nbt.NbtOps;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class TransporterBlockItem extends BlockItem implements Vanishable {
    public TransporterBlockItem(Block block, Item.Settings settings) {
        super(block, settings);
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos blockPos = context.getBlockPos();
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();
        boolean creativeMode = player.getAbilities().creativeMode;

        // use block unless holding a transporter
        if (!world.getBlockState(blockPos).isOf(Blocks.TRANSPORTER)) {
            return super.useOnBlock(context);
        }

        // if used on another transporter
        context.getPlayer().playSound(SoundEvents.BLOCK_BELL_USE, 1.0f, 0.8f);

        ItemStack itemStack = context.getStack();

        // if last stack in survival
        boolean lastInStack = itemStack.getCount() == 1;
        if (!creativeMode) { // survival mode
            if (lastInStack) {
                this.writeNbt(world.getRegistryKey(), blockPos, itemStack.getOrCreateNbt());
                return ActionResult.success(world.isClient);
            }

            ItemStack itemStack2 = new ItemStack(Blocks.transporterBlockItem, 1);
            NbtCompound nbtCompound = itemStack.hasNbt() ? itemStack.getNbt().copy() : new NbtCompound();
            itemStack2.setNbt(nbtCompound);
            // decrement stack in survival mode
            itemStack.decrement(1);

            // write nbt to new stack
            this.writeNbt(world.getRegistryKey(), blockPos, nbtCompound);

            // drop new stack if it cannot be put into inventory
            if (!player.getInventory().insertStack(itemStack2)) {
                player.dropItem(itemStack2, false);
            }

            return ActionResult.success(world.isClient);
        } else { // creative mode
            ItemStack itemStack2 = new ItemStack(Blocks.transporterBlockItem, 1);
            NbtCompound nbtCompound = itemStack.hasNbt() ? itemStack.getNbt().copy() : new NbtCompound();
            itemStack2.setNbt(nbtCompound);

            // write nbt to new stack
            this.writeNbt(world.getRegistryKey(), blockPos, nbtCompound);

            // drop new stack if it cannot be put into inventory
            if (!player.getInventory().insertStack(itemStack2)) {
                player.dropItem(itemStack2, false);
            }

            return ActionResult.success(world.isClient);
        }
    }

    private void writeNbt(RegistryKey<World> worldKey, BlockPos pos, NbtCompound nbt) {
        // get dimension
        DataResult<NbtElement> result = World.CODEC.encodeStart(NbtOps.INSTANCE, worldKey);

        // write nbt data
        result.result().ifPresent(nbtElement -> {
            nbt.put("TargetPos", NbtHelper.fromBlockPos(pos));
            nbt.put("TargetDimension", nbtElement);
            nbt.putBoolean("Inscribed", true);
        });
    }

    /*
    public ActionResult useOnBlock(ItemUsageContext context) {
        // call super
        ActionResult actionResult = this.place(new ItemPlacementContext(context));

        // implement custom check
        if (!actionResult.isAccepted()) {
            context.getPlayer().sendMessage(Text.of(actionResult.toString()), true);
            return actionResult;
        } else {
            return actionResult;
        }
    }

     */

    /*
    public ActionResult place(ItemPlacementContext context) {
        World world = context.getWorld();
        BlockPos targetBlockPos = context.getBlockPos();
        Block targetBlock = world.getBlockState(targetBlockPos).getBlock();
        Identifier registryName = Registries.BLOCK.getId(targetBlock);

        context.getPlayer().sendMessage(Text.of(registryName.toString()), true);
        context.getPlayer().playSound(SoundEvents.BLOCK_BAMBOO_WOOD_HIT, 1.0f, 1.4f);

        // call super place method
        return super.place(context);
    }
     */
}
