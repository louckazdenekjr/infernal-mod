package infernal.mod.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;


public class HandbellItem extends Item {
    public HandbellItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        // play sound effect
        user.playSound(SoundEvents.BLOCK_BELL_USE, 1.0f, 1.4f);

        // set item cooldown
        user.getItemCooldownManager().set(this, 10);

        // play item animation
        return TypedActionResult.success(user.getStackInHand(hand));
    }

}
