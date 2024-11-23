package net.freedinner.items_displayed.entity.custom.item_display;

import net.freedinner.items_displayed.entity.custom.AbstractDisplayEntity;
import net.freedinner.items_displayed.item.ModItems;
import net.freedinner.items_displayed.item.ModTags;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ItemDisplayEntity extends AbstractDisplayEntity {
    public ItemDisplayEntity(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public ActionResult interact(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);

        if (player.isSpectator()) {
            return ActionResult.SUCCESS;
        }

        if (player.getWorld().isClient) {
            return ActionResult.CONSUME;
        }

        if (tryDisplayItem(player, itemStack, hand)) {
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    @Override
    public boolean canEquip(ItemStack stack) {
        return stack.isIn(ModTags.SHERD_SHAPED) || stack.isIn(ModTags.TEMPLATE_SHAPED) || stack.isIn(ModTags.DISC_SHAPED);
    }

    @Override
    protected Item getEntityItem() {
        return ModItems.ITEM_DISPLAY;
    }

    @Override
    protected void spawnBreakParticles() {
        if (getWorld() instanceof ServerWorld serverWorld) {
            BlockStateParticleEffect particles = new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.OAK_WOOD.getDefaultState());
            serverWorld.spawnParticles(particles, getX(), getBodyY(0.6666666666666666), getZ(), 10, getWidth() / 4.0f, getHeight() / 4.0f, getWidth() / 4.0f, 0.05);
        }
    }

    @Override
    protected void playBreakSound() {
        getWorld().playSound(null, getX(), getY(), getZ(), SoundEvents.ENTITY_ARMOR_STAND_BREAK, getSoundCategory(), 1.0f, 1.0f);
    }

    @Override
    protected void playPutSound() {
        getWorld().playSound(null, getX(), getY(), getZ(), SoundEvents.BLOCK_CHISELED_BOOKSHELF_PLACE, getSoundCategory(), 1.0f, 1.0f);
    }

    @Override
    protected SoundEvent getHitSound() {
        return SoundEvents.ENTITY_ARMOR_STAND_HIT;
    }

    @Override
    public FallSounds getFallSounds() {
        return new FallSounds(SoundEvents.ENTITY_ARMOR_STAND_FALL, SoundEvents.ENTITY_ARMOR_STAND_FALL);
    }

    @Override
    @Nullable
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_ARMOR_STAND_HIT;
    }

    @Override
    @Nullable
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_ARMOR_STAND_BREAK;
    }
}
