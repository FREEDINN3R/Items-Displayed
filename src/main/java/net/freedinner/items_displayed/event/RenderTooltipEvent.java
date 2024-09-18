package net.freedinner.items_displayed.event;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.freedinner.items_displayed.config.ModConfigs;
import net.freedinner.items_displayed.util.BlockItemMapper;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class RenderTooltipEvent implements ItemTooltipCallback {
    @Override
    public void getTooltip(ItemStack stack, TooltipContext context, List<Text> lines) {
        if (ModConfigs.APPEND_ITEM_TOOLTIPS && BlockItemMapper.getBlockOrNull(stack.getItem()) != null) {
            lines.add(Text.translatable("item.items_displayed.tooltip.shift_place").formatted(Formatting.GRAY));
        }
    }
}
