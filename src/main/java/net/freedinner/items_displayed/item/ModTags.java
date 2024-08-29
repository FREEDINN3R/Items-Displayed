package net.freedinner.items_displayed.item;

import net.freedinner.items_displayed.ItemsDisplayed;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static final TagKey<Item> GEMSTONES = register("id_gemstones");
    public static final TagKey<Item> INGOTS = register("id_ingots");
    public static final TagKey<Item> SHERDS = register("id_sherds");
    public static final TagKey<Item> SMITHING_TEMPLATES = register("id_smithing_templates");

    private static TagKey<Item> register(String name) {
        return TagKey.of(RegistryKeys.ITEM, ItemsDisplayed.id(name));
    }

    public static void registerTags() {
        ItemsDisplayed.LOGGER.info("Registering tags");
    }
}
