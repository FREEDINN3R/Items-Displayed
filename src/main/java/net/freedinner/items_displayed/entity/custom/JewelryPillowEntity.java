package net.freedinner.items_displayed.entity.custom;

import com.google.common.collect.Maps;
import net.freedinner.items_displayed.item.ModItems;
import net.freedinner.items_displayed.item.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.*;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class JewelryPillowEntity extends LivingEntity {
    public static final TrackedData<Float> PILLOW_ROTATION_TRACKER = DataTracker.registerData(JewelryPillowEntity.class, TrackedDataHandlerRegistry.FLOAT);
    private static final TrackedData<Byte> PILLOW_COLOR_TRACKER = DataTracker.registerData(JewelryPillowEntity.class, TrackedDataHandlerRegistry.BYTE);
    private static final Predicate<Entity> RIDEABLE_MINECART_PREDICATE = entity -> entity instanceof AbstractMinecartEntity && ((AbstractMinecartEntity)entity).getMinecartType() == AbstractMinecartEntity.Type.RIDEABLE;
    private static final String PILLOW_ROTATION_NBT_KEY = "pillow_rotation";
    private static final String DISPLAYED_ITEM_NBT_KEY = "displayed_item";
    private static final String PILLOW_COLOR_NBT_KEY = "pillow_color";

    private static final Map<DyeColor, ItemConvertible> DROPS;
    private static final Map<DyeColor, Integer> COLORS;

    private ItemStack displayedItem = ItemStack.EMPTY;
    private float pillowRotation = 0f;
    public long lastHitTime;

    static {
        DROPS = new HashMap<>();
        DROPS.put(DyeColor.WHITE, ModItems.WHITE_JEWELRY_PILLOW);
        DROPS.put(DyeColor.ORANGE, ModItems.ORANGE_JEWELRY_PILLOW);
        DROPS.put(DyeColor.MAGENTA, ModItems.MAGENTA_JEWELRY_PILLOW);
        DROPS.put(DyeColor.LIGHT_BLUE, ModItems.LIGHT_BLUE_JEWELRY_PILLOW);
        DROPS.put(DyeColor.YELLOW, ModItems.YELLOW_JEWELRY_PILLOW);
        DROPS.put(DyeColor.LIME, ModItems.LIME_JEWELRY_PILLOW);
        DROPS.put(DyeColor.PINK, ModItems.PINK_JEWELRY_PILLOW);
        DROPS.put(DyeColor.GRAY, ModItems.GRAY_JEWELRY_PILLOW);
        DROPS.put(DyeColor.LIGHT_GRAY, ModItems.LIGHT_GRAY_JEWELRY_PILLOW);
        DROPS.put(DyeColor.CYAN, ModItems.CYAN_JEWELRY_PILLOW);
        DROPS.put(DyeColor.PURPLE, ModItems.PURPLE_JEWELRY_PILLOW);
        DROPS.put(DyeColor.BLUE, ModItems.BLUE_JEWELRY_PILLOW);
        DROPS.put(DyeColor.BROWN, ModItems.BROWN_JEWELRY_PILLOW);
        DROPS.put(DyeColor.GREEN, ModItems.GREEN_JEWELRY_PILLOW);
        DROPS.put(DyeColor.RED, ModItems.RED_JEWELRY_PILLOW);
        DROPS.put(DyeColor.BLACK, ModItems.BLACK_JEWELRY_PILLOW);


        COLORS = Arrays.stream(DyeColor.values()).collect(Collectors.toMap((color) -> color, JewelryPillowEntity::getDyedColor));
    }

    public static int getRgbColor(DyeColor dyeColor) {
        return COLORS.get(dyeColor);
    }
    private static int getDyedColor(DyeColor color) {
        if (color == DyeColor.WHITE) {
            return -1644826;
        } else {
            int i = color.getEntityColor();
            float f = 0.75F;
            return ColorHelper.Argb.getArgb(255, MathHelper.floor((float) ColorHelper.Argb.getRed(i) * f), MathHelper.floor((float) ColorHelper.Argb.getGreen(i) * f), MathHelper.floor((float) ColorHelper.Argb.getBlue(i) * f));
        }
    }

    public JewelryPillowEntity(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(PILLOW_ROTATION_TRACKER, 0f);
        builder.add(PILLOW_COLOR_TRACKER, (byte) 0);
    }

    @Override
    public void tick() {
        super.tick();

        float angle = dataTracker.get(PILLOW_ROTATION_TRACKER);
        if (pillowRotation != angle) {
            setPillowRotation(angle);
        }
    }

    public void setPillowRotation(float angle) {
        pillowRotation = angle;
        dataTracker.set(PILLOW_ROTATION_TRACKER, angle);
    }

    public float getPillowRotation() {
        return pillowRotation;
    }

    @Override
    protected float turnHead(float bodyRotation, float headRotation) {
        prevBodyYaw = prevYaw;
        bodyYaw = getYaw();
        return 0.0f;
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

    private boolean tryDisplayItem(PlayerEntity player, ItemStack stack, Hand hand) {
        if (stack.isEmpty()) {
            if (displayedItem.isEmpty()) {
                return false;
            }

            player.setStackInHand(hand, displayedItem);
            equipStack(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
            return true;
        }

        if (!displayedItem.isEmpty() || !canEquip(stack)) {
            return false;
        }

        equipStack(EquipmentSlot.MAINHAND, stack.copyWithCount(1));
        if (!player.getAbilities().creativeMode) {
            stack.decrement(1);
        }

        playPutSound();
        return true;
    }

    @Override
    public boolean canEquip(ItemStack stack) {
        return stack.isIn(ModTags.GEMSTONE_SHAPED) || stack.isIn(ModTags.CRYSTAL_SHAPED);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);

        nbt.putFloat(PILLOW_ROTATION_NBT_KEY, pillowRotation);

        if (!displayedItem.isEmpty()) {
            nbt.put(DISPLAYED_ITEM_NBT_KEY, displayedItem.encodeAllowEmpty(this.getRegistryManager()));
        }

        nbt.putByte(PILLOW_COLOR_NBT_KEY, (byte) this.getColor().getId());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);

        if (nbt.contains(PILLOW_ROTATION_NBT_KEY)) {
            setPillowRotation(nbt.getFloat(PILLOW_ROTATION_NBT_KEY));
        }

        if (nbt.contains(DISPLAYED_ITEM_NBT_KEY)) {
            NbtCompound heldItemNbt = nbt.getCompound(DISPLAYED_ITEM_NBT_KEY);
            displayedItem = ItemStack.fromNbtOrEmpty(this.getRegistryManager(), heldItemNbt);
        }

        if (nbt.contains(PILLOW_COLOR_NBT_KEY)) {
            this.setColor(DyeColor.byId(nbt.getByte(PILLOW_COLOR_NBT_KEY)));
        }
    }

    public DyeColor getColor() {
        return DyeColor.byId(this.dataTracker.get(PILLOW_COLOR_TRACKER) & 15);
    }

    public void setColor(DyeColor color) {
        byte b = this.dataTracker.get(PILLOW_COLOR_TRACKER);
        this.dataTracker.set(PILLOW_COLOR_TRACKER, (byte)(b & 240 | color.getId() & 15));
    }

    @Override
    public Iterable<ItemStack> getHandItems() {
        return DefaultedList.ofSize(1, displayedItem);
    }

    @Override
    public Iterable<ItemStack> getArmorItems() {
        return DefaultedList.of();
    }

    @Override
    public ItemStack getEquippedStack(EquipmentSlot slot) {
        return (slot == EquipmentSlot.MAINHAND) ? displayedItem : ItemStack.EMPTY;
    }

    @Override
    public void equipStack(EquipmentSlot slot, ItemStack stack) {
        processEquippedStack(stack);

        if (slot == EquipmentSlot.MAINHAND) {
            ItemStack oldStack = displayedItem;
            displayedItem = stack;
            onEquipStack(slot, oldStack, stack);
        }
    }

    @Override
    public Arm getMainArm() {
        return Arm.RIGHT;
    }

    @Override
    public void calculateDimensions() {
        double x = getX();
        double y = getY();
        double z = getZ();

        super.calculateDimensions();
        setPosition(x, y, z);
    }

    @Override
    public float getStepHeight() {
        return 0.0f;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    protected void pushAway(Entity entity) {
    }

    @Override
    protected void tickCramming() {
        List<Entity> otherEntities = getWorld().getOtherEntities(this, getBoundingBox(), RIDEABLE_MINECART_PREDICATE);
        for (Entity entity : otherEntities) {
            if (squaredDistanceTo(entity) <= 0.2) {
                entity.pushAwayFrom(this);
            }
        }
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (getWorld().isClient || this.isRemoved()) {
            return false;
        }

        ServerWorld serverWorld = (ServerWorld) this.getWorld();

        if (source.isIn(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            kill();
            return false;
        }

        if (this.isInvulnerableTo(source)) {
            return false;
        }

        if (source.isIn(DamageTypeTags.IS_EXPLOSION)) {
            breakAndDropItem(serverWorld, source);
            kill();
            return false;
        }

        if (source.isIn(DamageTypeTags.IGNITES_ARMOR_STANDS)) {
            if (this.isOnFire()) {
                updateHealth(serverWorld, source, 0.15f);
            } else {
                setOnFireFor(5);
            }

            return false;
        }

        if (source.isIn(DamageTypeTags.BURNS_ARMOR_STANDS) && getHealth() > 0.5f) {
            updateHealth(serverWorld, source, 4.0f);
            return false;
        }

        boolean isProjectile = source.getSource() instanceof PersistentProjectileEntity;
        boolean hasPiercing = isProjectile && ((PersistentProjectileEntity)source.getSource()).getPierceLevel() > 0;
        boolean fromPlayer = source.getName().equals("player");

        if (!fromPlayer && !isProjectile) {
            return false;
        }

        Entity attacker = source.getAttacker();
        if (attacker instanceof PlayerEntity playerEntity) {
            if (!playerEntity.getAbilities().allowModifyWorld) {
                return false;
            }
        }

        if (source.isSourceCreativePlayer()) {
            playBreakSound();
            spawnBreakParticles();
            kill();
            return hasPiercing;
        }

        long currTime = getWorld().getTime();
        if (currTime - lastHitTime <= 5L || isProjectile) {
            breakAndDropItem(serverWorld, source);
            spawnBreakParticles();
            kill();
        } else {
            getWorld().sendEntityStatus(this, EntityStatuses.HIT_ARMOR_STAND);
            emitGameEvent(GameEvent.ENTITY_DAMAGE, source.getAttacker());
            lastHitTime = currTime;
        }

        return true;
    }

    @Override
    public void handleStatus(byte status) {
        if (status == EntityStatuses.HIT_ARMOR_STAND) {
            if (getWorld().isClient) {
                getWorld().playSound(getX(), getY(), getZ(), SoundEvents.BLOCK_WOOL_HIT, getSoundCategory(), 0.3f, 1.0f, false);
                lastHitTime = getWorld().getTime();
            }
        } else {
            super.handleStatus(status);
        }
    }

    @Override
    public boolean shouldRender(double distance) {
        double d = getBoundingBox().getAverageSideLength() * 4.0;
        if (Double.isNaN(d) || d == 0.0) {
            d = 4.0;
        }

        return distance < (d *= 64.0) * d;
    }

    private void spawnBreakParticles() {
        if (getWorld() instanceof ServerWorld serverWorld) {
            BlockStateParticleEffect particles = new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.OAK_WOOD.getDefaultState());
            serverWorld.spawnParticles(particles, getX(), getBodyY(0.6666666666666666), getZ(), 10, getWidth() / 4.0f, getHeight() / 4.0f, getWidth() / 4.0f, 0.05);
        }
    }

    private void updateHealth(ServerWorld world, DamageSource damageSource, float amount) {
        float f = getHealth() - amount;
        if (f <= 0.5f) {
            onBreak(world, damageSource);
            kill();
        } else {
            setHealth(f);
            emitGameEvent(GameEvent.ENTITY_DAMAGE, damageSource.getAttacker());
        }
    }

    private void breakAndDropItem(ServerWorld world, DamageSource damageSource) {
        ItemStack itemStack = new ItemStack(DROPS.get(this.getColor()));
        Block.dropStack(getWorld(), getBlockPos(), itemStack);
        onBreak(world, damageSource);
    }

    private void onBreak(ServerWorld world, DamageSource damageSource) {
        playBreakSound();
        drop(world, damageSource);

        if (!displayedItem.isEmpty()) {
            Block.dropStack(getWorld(), getBlockPos(), displayedItem);
            displayedItem = ItemStack.EMPTY;
        }
    }

    private void playBreakSound() {
        getWorld().playSound(null, getX(), getY(), getZ(), SoundEvents.BLOCK_WOOL_BREAK, getSoundCategory(), 1.0f, 1.0f);
    }

    private void playPutSound() {
        getWorld().playSound(null, getX(), getY(), getZ(), SoundEvents.BLOCK_WOOL_PLACE, getSoundCategory(), 1.0f, 1.0f);
    }

    @Override
    public void kill() {
        remove(RemovalReason.KILLED);
        emitGameEvent(GameEvent.ENTITY_DIE);
    }

    @Override
    public boolean handleAttack(Entity attacker) {
        return attacker instanceof PlayerEntity && !getWorld().canPlayerModifyAt((PlayerEntity)attacker, getBlockPos());
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

    @Override
    public void onStruckByLightning(ServerWorld world, LightningEntity lightning) {
    }

    @Override
    public boolean isAffectedBySplashPotions() {
        return false;
    }

    @Override
    public boolean isMobOrPlayer() {
        return false;
    }

    @Override
    public ItemStack getPickBlockStack() {
        return new ItemStack(DROPS.get(this.getColor()));
    }
}
