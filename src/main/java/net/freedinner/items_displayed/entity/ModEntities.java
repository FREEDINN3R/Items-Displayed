package net.freedinner.items_displayed.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.freedinner.items_displayed.ItemsDisplayed;
import net.freedinner.items_displayed.entity.custom.item_display.ItemDisplayEntity;
import net.freedinner.items_displayed.entity.custom.jewelry_pillow.JewelryPillowEntity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModEntities {
    public static final EntityType<ItemDisplayEntity> ITEM_DISPLAY = Registry.register(
            Registries.ENTITY_TYPE,
            ItemsDisplayed.id( "item_display"),
            FabricEntityTypeBuilder
                    .create(SpawnGroup.MISC, ItemDisplayEntity::new)
                    .dimensions(EntityDimensions.fixed(0.7f, 0.7f))
                    .build()
    );

    public static final EntityType<JewelryPillowEntity> JEWELRY_PILLOW = Registry.register(
            Registries.ENTITY_TYPE,
            ItemsDisplayed.id( "jewelry_pillow"),
            FabricEntityTypeBuilder
                    .create(SpawnGroup.MISC, JewelryPillowEntity::new)
                    .dimensions(EntityDimensions.fixed(0.75f, 0.4f))
                    .build()
    );

    static {
        FabricDefaultAttributeRegistry.register(ITEM_DISPLAY, ItemDisplayEntity.createLivingAttributes());
        FabricDefaultAttributeRegistry.register(JEWELRY_PILLOW, JewelryPillowEntity.createLivingAttributes());
    }

    public static void registerEntities() {
        ItemsDisplayed.LOGGER.info("Registering entities");
    }
}
