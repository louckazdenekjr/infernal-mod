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
                    // TODO: offset should be handled in the entity teleport method entirely
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
            // TODO: if it's already paired, play failure sound
            return false;
        }

        long linkerLong = pos.asLong();
        long targetLong = new BlockPos(
                (int) (linker_x - 0.5),
                (int) (linker_y - 0.5),
                (int) (linker_z - 0.5)
        ).asLong();

        if (linkerLong != targetLong) {
            this.target_x = linker_x;
            this.target_y = linker_y;
            this.target_z = linker_z;
            this.paired = true;
            return true;
        } else {
            return false;
        }
    }

    public void processUnpairingRequest() {
        if (this.paired) {
            // get target entity
            BlockPos targetPos = new BlockPos( // TODO: get rid of manual offset?
                    (int) (this.target_x - 0.5),
                    (int) (this.target_y - 0.5),
                    (int) (this.target_z - 0.5)
            );
            BlockEntity blockEntity = getWorld().getBlockEntity(targetPos);
            if (blockEntity instanceof TransporterBlockEntity) {
                // unpair current entity
                this.paired = false;

                // unpair target entity
                ((TransporterBlockEntity) blockEntity).setPaired(false);
            }
        }
    }

    public void setPaired(boolean newState) {
        this.paired = newState;
    }

    public void teleportPlayer(PlayerEntity player) {
        if (this.paired) {
            player.teleport(this.target_x, this.target_y, this.target_z);
        }
    }

}

/* TODO
1. play pairing sound on successful pairing
--?. play failure sound on failed pairing
3. play teleport sound on teleporting
--?. play failure sound if clicked transporter is not linked
4. play unpairing sound on unpairing?
 */