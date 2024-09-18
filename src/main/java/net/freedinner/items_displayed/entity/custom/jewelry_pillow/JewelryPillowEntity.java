package net.freedinner.items_displayed.entity.custom.jewelry_pillow;

import net.freedinner.items_displayed.entity.custom.AbstractDisplayEntity;
import net.freedinner.items_displayed.item.ModItems;
import net.freedinner.items_displayed.item.ModTags;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class JewelryPillowEntity extends AbstractDisplayEntity {
    private static final TrackedData<Byte> PILLOW_COLOR_TRACKER = DataTracker.registerData(JewelryPillowEntity.class, TrackedDataHandlerRegistry.BYTE);
    private static final String PILLOW_COLOR_NBT_KEY = "pillow_color";

    public JewelryPillowEntity(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        dataTracker.startTracking(PILLOW_COLOR_TRACKER, (byte) 0);
    }

    public DyeColor getColor() {
        return DyeColor.byId(this.dataTracker.get(PILLOW_COLOR_TRACKER) & 15);
    }

    public void setColor(DyeColor color) {
        byte b = this.dataTracker.get(PILLOW_COLOR_TRACKER);
        this.dataTracker.set(PILLOW_COLOR_TRACKER, (byte)(b & 240 | color.getId() & 15));
    }

    @Override
    public ActionResult interact(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);

        if (player.isSpectator()) {
            return ActionResult.SUCCESS;
        }

        if (itemStack.getItem() instanceof DyeItem dye && this.getColor() != dye.getColor()) {
            this.getWorld().playSoundFromEntity(player, this, SoundEvents.ITEM_DYE_USE, SoundCategory.PLAYERS, 1.0f, 1.0f);

            if (!this.getWorld().isClient) {
                this.setColor(dye.getColor());
                itemStack.decrement(1);
            }

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
        return stack.isIn(ModTags.GEMSTONE_SHAPED) || stack.isIn(ModTags.CRYSTAL_SHAPED);
    }

    @Override
    protected Item getEntityItem() {
        return switch (this.getColor()) {
            case WHITE -> ModItems.WHITE_JEWELRY_PILLOW;
            case ORANGE -> ModItems.ORANGE_JEWELRY_PILLOW;
            case MAGENTA -> ModItems.MAGENTA_JEWELRY_PILLOW;
            case LIGHT_BLUE -> ModItems.LIGHT_BLUE_JEWELRY_PILLOW;
            case YELLOW -> ModItems.YELLOW_JEWELRY_PILLOW;
            case LIME -> ModItems.LIME_JEWELRY_PILLOW;
            case PINK -> ModItems.PINK_JEWELRY_PILLOW;
            case GRAY -> ModItems.GRAY_JEWELRY_PILLOW;
            case LIGHT_GRAY -> ModItems.LIGHT_GRAY_JEWELRY_PILLOW;
            case CYAN -> ModItems.CYAN_JEWELRY_PILLOW;
            case PURPLE -> ModItems.PURPLE_JEWELRY_PILLOW;
            case BLUE -> ModItems.BLUE_JEWELRY_PILLOW;
            case BROWN -> ModItems.BROWN_JEWELRY_PILLOW;
            case GREEN -> ModItems.GREEN_JEWELRY_PILLOW;
            case RED -> ModItems.RED_JEWELRY_PILLOW;
            case BLACK -> ModItems.BLACK_JEWELRY_PILLOW;
        };
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);

        nbt.putByte(PILLOW_COLOR_NBT_KEY, (byte) this.getColor().getId());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);

        if (nbt.contains(PILLOW_COLOR_NBT_KEY)) {
            this.setColor(DyeColor.byId(nbt.getByte(PILLOW_COLOR_NBT_KEY)));
        }
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
        getWorld().playSound(null, getX(), getY(), getZ(), SoundEvents.BLOCK_WOOL_BREAK, getSoundCategory(), 1.0f, 1.0f);
    }

    @Override
    protected void playPutSound() {
        getWorld().playSound(null, getX(), getY(), getZ(), SoundEvents.BLOCK_WOOL_PLACE, getSoundCategory(), 1.0f, 1.0f);
    }

    @Override
    protected SoundEvent getHitSound() {
        return SoundEvents.BLOCK_WOOL_HIT;
    }

    @Override
    public FallSounds getFallSounds() {
        return new FallSounds(SoundEvents.BLOCK_WOOL_FALL, SoundEvents.BLOCK_WOOL_FALL);
    }

    @Override
    @Nullable
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.BLOCK_WOOL_HIT;
    }

    @Override
    @Nullable
    protected SoundEvent getDeathSound() {
        return SoundEvents.BLOCK_WOOL_BREAK;
    }
}
