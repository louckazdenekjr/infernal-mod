package infernal.mod.block.types;

import infernal.mod.block.BlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TransporterBlockEntity extends BlockEntity {
    private World world;
    private double target_x;
    private double target_y;
    private double target_z;
    private NbtElement target_dim;

    public TransporterBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntities.TRANSPORTER_BLOCK_ENTITY_TYPE, pos, state);
        world = getWorld();

        //placer.playSound(SoundEvents.BLOCK_PISTON_EXTEND, 1.0f, 1.0f);
        //getWorld().playSound((BlockEntity) this, pos, HANDBELL_RINGING, SoundCategory.MASTER, 1.0f, 1.0f, true);
        //getWorld().playSound((double) pos.getX(), (double) pos.getY(), (double) pos.getZ(), SoundEvents.BLOCK_CHAIN_FALL, SoundCategory.AMBIENT, 1.0f, 1.0f, true
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        // Read your block entity's data
        super.readNbt(nbt);

        // read custom nbt
        this.target_x = nbt.getDouble("target_x");
        this.target_y = nbt.getDouble("target_y");
        this.target_z = nbt.getDouble("target_z");
        this.target_dim = nbt.get("target_dim");
    }

    public void acceptParameter(double target_x, double target_y, double target_z, NbtElement target_dim) {
        this.target_x = target_x;
        this.target_y = target_y;
        this.target_z = target_z;
        this.target_dim = target_dim;
        
        //PlayerEntity placer = getWorld().getPlayerByUuid(UUID.fromString(placerUuid));
        //placer.sendMessage(Text.of("PARAMETERS PASSED"));
    }

    public void teleportPlayer(PlayerEntity player) {
        player.teleport(this.target_x, this.target_y, this.target_z); // TODO: check non-null
        player.sendMessage(Text.of("TELEPORTING!"));
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        // write custom nbt
        nbt.putDouble("target_x", target_x);
        nbt.putDouble("target_y", target_y);
        nbt.putDouble("target_z", target_z);
        nbt.put("target_dim", target_dim);

        // Write your block entity's data
        super.writeNbt(nbt);
    }
}
