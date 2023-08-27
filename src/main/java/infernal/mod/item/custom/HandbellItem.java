package infernal.mod.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class HandbellItem extends Item {
    public HandbellItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        // get context
        ItemCooldownManager cooldownManager = user.getItemCooldownManager();
        boolean isCoolingDown = cooldownManager.isCoolingDown(this);

        // play sound effect
        user.playSound(SoundEvents.BLOCK_BELL_USE, 1.0f, 1.4f);

        if (world.isClient) {
            // client side
        } else {
            // server side
            if (!isCoolingDown) { // if ready
                ArrayList entities = (ArrayList) world.getEntitiesByType(
                        TypeFilter.instanceOf(net.minecraft.entity.passive.CatEntity.class),
                        new Box(
                                user.getX() - 32,
                                user.getY() - 32,
                                user.getZ() - 32,
                                user.getX() + 32,
                                user.getY() + 32,
                                user.getZ() + 32
                        ),
                        EntityPredicates.VALID_ENTITY
                );

                // report entity count
                user.sendMessage(Text.literal("Found " + entities.size() + " cats!"), true);

                // start cooldown
                cooldownManager.set(this, 60);

                // this needs to be last, cuz java no likey
                for (int i = 0; i <= entities.size(); i++) {
                    Entity target = (Entity) entities.get(i);
                    target.addVelocity(0, 4, 0);
                }
            }


        }

        // play item animation
        return TypedActionResult.success(user.getStackInHand(hand));
    }

}
