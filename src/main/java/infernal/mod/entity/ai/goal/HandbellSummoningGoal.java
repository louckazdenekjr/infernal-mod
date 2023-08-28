package infernal.mod.entity.ai.goal;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.NoPenaltyTargeting;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class HandbellSummoningGoal
        extends Goal {
    public static final int RANGE_Y = 1;
    protected final PathAwareEntity mob;
    protected final double speed;
    protected double targetX;
    protected double targetY;
    protected double targetZ;
    protected boolean active;

    public HandbellSummoningGoal(PathAwareEntity mob, double speed) {
        this.mob = mob;
        this.speed = speed;
        this.setControls(EnumSet.of(Goal.Control.MOVE));
    }

    @Override
    public boolean canStart() {
        BlockPos blockPos;

        blockPos = this.locateClosestWater(this.mob.world, this.mob, 5);
        this.targetX = blockPos.getX();
        this.targetY = blockPos.getY();
        this.targetZ = blockPos.getZ();
        return false;

    }

    public boolean isActive() {
        return this.active;
    }

    @Override
    public void start() {
        this.mob.getNavigation().startMovingTo(this.targetX, this.targetY, this.targetZ, this.speed);
        this.active = true;
    }

    @Override
    public void stop() {
        this.active = false;
    }

    @Override
    public boolean shouldContinue() {
        return !this.mob.getNavigation().isIdle();
    }

    @Nullable
    protected BlockPos locateClosestWater(BlockView world, Entity entity, int rangeX) {
        BlockPos blockPos = entity.getBlockPos();
        if (!world.getBlockState(blockPos).getCollisionShape(world, blockPos).isEmpty()) {
            return null;
        }
        return BlockPos.findClosest(entity.getBlockPos(), rangeX, 1, pos -> world.getFluidState((BlockPos)pos).isIn(FluidTags.WATER)).orElse(null);
    }
}