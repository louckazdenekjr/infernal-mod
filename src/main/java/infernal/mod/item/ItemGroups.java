package infernal.mod.item;

import net.minecraft.item.ItemGroup;


public class ItemGroups {
    public static ItemGroup VARIOUS;

    public static void registerItemGroups() {
        //VARIOUS = FabricItemGroup.builder(new Identifier(InfernalMod.MOD_ID, "example"))
        //        .displayName(Text.translatable("itemgroup.various"))
        //        .icon(() -> new ItemStack(ModBlocks.BEJEWELED_DEEPSLATE)).build();
    }
}
