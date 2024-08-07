package net.freedinner.items_displayed.item;

import net.freedinner.items_displayed.ItemsDisplayed;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static final TagKey<Item> SHERDS = register("sherds");
    public static final TagKey<Item> SMITHING_TEMPLATES = register("smithing_templates");

    private static TagKey<Item> register(String name) {
        return TagKey.of(RegistryKeys.ITEM, Identifier.of(ItemsDisplayed.MOD_ID, name));
    }

    public static void registerTags() {
        ItemsDisplayed.LOGGER.info("Registering tags");
    }
}
