package infernal.mod.block;

import infernal.mod.block.types.TransporterBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static infernal.mod.InfernalMod.LOGGER;
import static infernal.mod.InfernalMod.MOD_ID;

public class BlockEntities {
    public static final BlockEntityType TRANSPORTER_BLOCK_ENTITY_TYPE = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            new Identifier(MOD_ID, "transporter_block_entity"),
            FabricBlockEntityTypeBuilder.create(TransporterBlockEntity::new, Blocks.transporterBlockBlock).build()
    );

    //registerBlockEntityType("transporter_block_entity",new TransporterBlockEntity(),transporterBlockBlock);

    //----------------------------------------
    /*
    private static BlockEntityType registerBlockEntityType(String name, r<BlockEntity> blockEntitySupplier, Block block) {
        return Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier(MOD_ID, name),
                BlockEntityType.Builder.create(blockEntitySupplier, block).build(null)
        );

        //return Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(MOD_ID, name), FabricBlockEntityTypeBuilder.create(blockEntity::new, block).build());
        //final BlockEntityType<DemoBlockEntity>

        // register block entity
        //BlockEntity blockEntity = Registry.register(BLOCK_ENTITY_TYPE, new Identifier(MOD_ID, name), blockEntity);
        //return BlockEntityType.Builder.create(blockEntity::new, block).build(null);
    }
     */

    public static void registerModBlockEntities() {
        LOGGER.info("Registering Block Entities");
    }

}
