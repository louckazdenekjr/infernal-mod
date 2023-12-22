package infernal.mod.block.types;

import infernal.mod.block.BlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.util.math.BlockPos;

public class TransporterBlockEntity extends BlockEntity {
    private double target_x;
    private double target_y;
    private double target_z;
    //private int target_dim;
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
        //this.target_dim = nbt.getInt("target_dim");
        this.paired = nbt.getBoolean("paired");
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        // write custom nbt
        nbt.putDouble("target_x", this.target_x);
        nbt.putDouble("target_y", this.target_y);
        nbt.putDouble("target_z", this.target_z);
        //nbt.putInt("target_dim", this.target_dim);
        nbt.putBoolean("paired", this.paired);

        // Write your block entity's data
        super.writeNbt(nbt);
    }

    public void attemptPairing(NbtCompound TARGET_POSITION, NbtElement TARGET_DIMENSION) {
        int target_x_raw = TARGET_POSITION.getInt("X");
        int target_y_raw = TARGET_POSITION.getInt("Y");
        int target_z_raw = TARGET_POSITION.getInt("Z");
        BlockPos targetPos = new BlockPos(target_x_raw, target_y_raw, target_z_raw);
        BlockEntity blockEntity = getWorld().getBlockEntity(targetPos);
        if (blockEntity instanceof TransporterBlockEntity) {
            // break if target is in different dimension
            int dim = getWorld().getRegistryKey().getValue().toString().hashCode();
            int target_dim = TARGET_DIMENSION.asString().hashCode();
            if (dim != target_dim) {
                return;
            }
            // send pairing request
            if (((TransporterBlockEntity) blockEntity).processPairingRequest(
                    pos.getX() + 0.5,
                    pos.getY() + 0.5,
                    pos.getZ() + 0.5
                    )) {
                // if it's succesful, store data
                this.target_x = target_x_raw + 0.5;
                this.target_y = target_y_raw + 0.5;
                this.target_z = target_z_raw + 0.5;
                this.paired = true;
            }
        }
    }

    public boolean processPairingRequest(double linker_x, double linker_y, double linker_z) {
        if (this.paired) {
            // TODO: if it is paired, do nothing and play failure sound
            return false;
        }

        this.target_x = linker_x;
        this.target_y = linker_y;
        this.target_z = linker_z;
        this.paired = true;
        return true;
    }

    public void teleportPlayer(PlayerEntity player) {
        if (this.paired) {
            player.teleport(this.target_x, this.target_y, this.target_z);
        }
    }

}