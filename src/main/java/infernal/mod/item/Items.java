package infernal.mod.item;

import infernal.mod.InfernalMod;
import infernal.mod.item.types.HandbellItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class Items {
    public static final Item HANDBELL = registerItem(
            "handbell",
            new HandbellItem(new FabricItemSettings())
    );

    private static Item registerItem(String name, Item item) {
        // register an item
        return Registry.register(Registries.ITEM, new Identifier(InfernalMod.MOD_ID, name), item);
    }


    public static void addItemsToItemGroup() {
        //addToItemGroup(ItemGroups.INGREDIENTS, EXAMPLE);
        //addToItemGroup(ModItemGroup.VARIOUS, EXAMPLE);
        addToItemGroup(ItemGroups.TOOLS, HANDBELL);
    }


    private static void addToItemGroup(ItemGroup group, Item item) {
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
    }


    public static void registerModItems() {
        InfernalMod.LOGGER.info("Registering Items");
        addItemsToItemGroup();
    }
}
