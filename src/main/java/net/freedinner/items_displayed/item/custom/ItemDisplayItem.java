package net.freedinner.items_displayed.item.custom;

import net.freedinner.items_displayed.config.ModConfigs;
import net.freedinner.items_displayed.entity.ModEntities;
import net.freedinner.items_displayed.entity.custom.item_display.ItemDisplayEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.List;
import java.util.function.Consumer;

public class ItemDisplayItem extends Item {
    public ItemDisplayItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);

        if (!ModConfigs.APPEND_EXTRA_TOOLTIPS) {
            return;
        }

        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("item.items_displayed.tooltip.can_be_used_to").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("item.items_displayed.tooltip.item_display_0").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("item.items_displayed.tooltip.item_display_1").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("item.items_displayed.tooltip.item_display_2").formatted(Formatting.GRAY));
        }
        else {
            tooltip.add(Text.translatable("item.items_displayed.tooltip.shift_more_info").formatted(Formatting.GRAY));
        }
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getSide() == Direction.DOWN) {
            return ActionResult.FAIL;
        }

        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos().offset(context.getSide());

        if (!enoughSpaceAt(blockPos, world)) {
            return ActionResult.FAIL;
        }

        if (world instanceof ServerWorld serverWorld) {
            ItemDisplayEntity itemDisplayEntity = createItemDisplay(serverWorld, context, blockPos);

            if (itemDisplayEntity == null) {
                return ActionResult.FAIL;
            }

            setItemDisplayRotation(itemDisplayEntity, context);
            summonItemDisplay(serverWorld, itemDisplayEntity, context.getPlayer());
        }

        context.getStack().decrement(1);
        return ActionResult.success(world.isClient);
    }

    private boolean enoughSpaceAt(BlockPos blockPos, World world) {
        Vec3d vec3d = Vec3d.ofBottomCenter(blockPos);
        Box box = ModEntities.ITEM_DISPLAY.getDimensions().getBoxAt(vec3d.getX(), vec3d.getY(), vec3d.getZ());

        return world.isSpaceEmpty(box) && world.getOtherEntities(null, box).isEmpty();
    }

    private ItemDisplayEntity createItemDisplay(ServerWorld serverWorld, ItemUsageContext context, BlockPos blockPos) {
        Consumer<ItemDisplayEntity> consumer = EntityType.copier(serverWorld, context.getStack(), context.getPlayer());
        return ModEntities.ITEM_DISPLAY.create(serverWorld, consumer, blockPos, SpawnReason.SPAWN_EGG, true, false);
    }

    private void setItemDisplayRotation(ItemDisplayEntity entity, ItemUsageContext context) {
        float angle = MathHelper.wrapDegrees(context.getPlayerYaw() - 180.0f);
        float minRotation = ModConfigs.ENTITY_ROTATION_ANGLE;
        angle = MathHelper.floor((angle + minRotation / 2.0) / minRotation) * minRotation;

        entity.setEntityRotation(angle);
    }

    private void summonItemDisplay(ServerWorld serverWorld, ItemDisplayEntity entity, PlayerEntity player) {
        serverWorld.spawnEntityAndPassengers(entity);
        serverWorld.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.BLOCK_LADDER_PLACE, SoundCategory.BLOCKS, 0.75f, 0.8f);
        entity.emitGameEvent(GameEvent.ENTITY_PLACE, player);
    }
}
