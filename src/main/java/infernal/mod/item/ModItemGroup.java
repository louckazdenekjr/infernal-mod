package infernal.mod.item;

import infernal.mod.InfernalMod;
import infernal.mod.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


public class ModItemGroup {
    public static ItemGroup VARIOUS;


    public static void registerItemGroups() {
        VARIOUS = FabricItemGroup.builder(new Identifier(InfernalMod.MOD_ID, "example"))
                .displayName(Text.translatable("itemgroup.various"))
                .icon(() -> new ItemStack(ModBlocks.BEJEWELED_DEEPSLATE)).build();
    }
}
