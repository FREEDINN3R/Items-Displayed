package net.freedinner.items_displayed.item.custom;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.client.item.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public class DebugBlockItem extends BlockItem {
    public DebugBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("item.items_displayed.tooltip.debug"));
    }
}
