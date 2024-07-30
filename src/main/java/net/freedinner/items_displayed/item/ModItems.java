package net.freedinner.items_displayed.item;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.freedinner.items_displayed.ItemsDisplayed;
import net.freedinner.items_displayed.item.custom.ItemDisplayItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item ITEM_DISPLAY = register("item_display",
            new ItemDisplayItem(new Item.Settings()));

    private static Item register(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(ItemsDisplayed.MOD_ID, name), item);
    }

    public static void registerItems() {
        ItemsDisplayed.LOGGER.info("Registering items");

        FuelRegistry.INSTANCE.add(ITEM_DISPLAY, 250);
    }
}
