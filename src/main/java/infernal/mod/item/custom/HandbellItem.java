package infernal.mod.item.custom;

import infernal.mod.entity.ai.goal.HandbellSummoningGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

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

        if (!isCoolingDown) { // if ready
            // both logical sides
            // start cooldown
            cooldownManager.set(this, 60);

            if (world.isClient) {
                // client side
                // play sound effect
                user.playSound(SoundEvents.BLOCK_BELL_USE, 1.0f, 1.4f);
            } else {
                // server side
                ArrayList entities = (ArrayList) world.getEntitiesByType(
                        TypeFilter.instanceOf(TameableEntity.class),
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
                user.sendMessage(Text.literal("Found " + entities.size() + " tameable entities!"), true);

                // this needs to be last, cuz java no likey
                for (int i = 0; i <= entities.size(); i++) {
                    if (entities.get(i) instanceof TameableEntity target) {
                        HandbellSummoningGoal summoningGoal = new HandbellSummoningGoal(
                                target,
                                2.0
                                );
                        target.goalSelector.add(
                                10,
                                summoningGoal
                        );


                        //target_tameable.setInSittingPose(true);
                    }
                }
            }

        }

        // play item animation
        return TypedActionResult.success(user.getStackInHand(hand));
    }

}
