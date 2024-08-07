package net.freedinner.items_displayed.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.freedinner.items_displayed.ItemsDisplayed;
import net.freedinner.items_displayed.entity.custom.ItemDisplayEntity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<ItemDisplayEntity> ITEM_DISPLAY = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(ItemsDisplayed.MOD_ID, "item_display"),
            EntityType.Builder
                    .create(ItemDisplayEntity::new, SpawnGroup.MISC)
                    .dimensions(0.7f, 0.7f)
                    .build()
    );

    static {
        FabricDefaultAttributeRegistry.register(ITEM_DISPLAY, ItemDisplayEntity.createLivingAttributes());
    }

    public static void registerEntities() {
        ItemsDisplayed.LOGGER.info("Registering entities");
    }
}
