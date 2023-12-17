package infernal.mod.block;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class TransporterBlockItem extends BlockItem {
    public TransporterBlockItem(Block block, Item.Settings settings) {
        super(block, settings);
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos blockPos = context.getBlockPos();
        World world = context.getWorld();
        if (!world.getBlockState(blockPos).isOf(Blocks.TRANSPORTER)) {
            return super.useOnBlock(context);
        } else {
            context.getPlayer().playSound(SoundEvents.BLOCK_BELL_USE, 1.0f, 1.4f);

            PlayerEntity playerEntity = context.getPlayer();
            ItemStack itemStack = context.getStack();

            /*
            boolean bl = !playerEntity.getAbilities().creativeMode && itemStack.getCount() == 1;
            if (bl) {
                this.writeNbt(world.getRegistryKey(), blockPos, itemStack.getOrCreateNbt());
            } else {
                ItemStack itemStack2 = new ItemStack(Items.COMPASS, 1);
                NbtCompound nbtCompound = itemStack.hasNbt() ? itemStack.getNbt().copy() : new NbtCompound();
                itemStack2.setNbt(nbtCompound);
                if (!playerEntity.getAbilities().creativeMode) {
                    itemStack.decrement(1);
                }

                this.writeNbt(world.getRegistryKey(), blockPos, nbtCompound);
                if (!playerEntity.getInventory().insertStack(itemStack2)) {
                    playerEntity.dropItem(itemStack2, false);
                }
            }
             */


            return ActionResult.success(world.isClient);
        }
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
