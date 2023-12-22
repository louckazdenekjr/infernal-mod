package infernal.mod.block.types;

import infernal.mod.block.BlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class TransporterBlockEntity extends BlockEntity {
    private double target_x;
    private double target_y;
    private double target_z;
    private int target_dim;
    private boolean paired;

    public TransporterBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntities.TRANSPORTER_BLOCK_ENTITY_TYPE, pos, state);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        // Read your block entity's data
        super.readNbt(nbt);

        // read custom nbt
        this.target_x = nbt.getDouble("target_x");
        this.target_y = nbt.getDouble("target_y");
        this.target_z = nbt.getDouble("target_z");
        this.target_dim = nbt.getInt("target_dim");
        this.paired = nbt.getBoolean("paired");
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        // write custom nbt
        nbt.putDouble("target_x", this.target_x);
        nbt.putDouble("target_y", this.target_y);
        nbt.putDouble("target_z", this.target_z);
        nbt.putInt("target_dim", this.target_dim);
        nbt.putBoolean("paired", this.paired);

        // Write your block entity's data
        super.writeNbt(nbt);
    }

    public void attemptPairing(NbtCompound TARGET_POSITION, NbtElement TARGET_DIMENSION, PlayerEntity player) {
        int target_x_raw = TARGET_POSITION.getInt("X");
        int target_y_raw = TARGET_POSITION.getInt("Y");
        int target_z_raw = TARGET_POSITION.getInt("Z");
        BlockPos targetPos = new BlockPos(target_x_raw, target_y_raw, target_z_raw);
        BlockEntity blockEntity = getWorld().getBlockEntity(targetPos);
        if (blockEntity instanceof TransporterBlockEntity) {
            this.target_x = target_x_raw + 0.5;
            this.target_y = target_y_raw + 0.5;
            this.target_z = target_z_raw + 0.5;
            this.target_dim = TARGET_DIMENSION.asString().hashCode();
            this.paired = true;
        }
    }

    public void teleportPlayer(PlayerEntity player) {
        // TODO: dimension check - here or at pairing
        if (this.paired) {
            player.teleport(this.target_x, this.target_y, this.target_z);
        }
    }

}
