package net.freedinner.items_displayed.entity.custom;

import net.freedinner.items_displayed.item.ModItems;
import net.freedinner.items_displayed.item.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public class ItemDisplayEntity extends LivingEntity {
    private static final float DEFAULT_DISPLAY_ROTATION = 0.0f;
    public static final TrackedData<Float> DISPLAY_ROTATION_TRACKER = DataTracker.registerData(ItemDisplayEntity.class, TrackedDataHandlerRegistry.FLOAT);
    private static final Predicate<Entity> RIDEABLE_MINECART_PREDICATE = entity -> entity instanceof AbstractMinecartEntity && ((AbstractMinecartEntity)entity).getMinecartType() == AbstractMinecartEntity.Type.RIDEABLE;
    private static final String DISPLAYED_ITEM_NBT_KEY = "displayed_item";
    private static final String DISPLAY_ROTATION_NBT_KEY = "display_rotation";

    private ItemStack displayedItem = ItemStack.EMPTY;
    private float displayRotation = DEFAULT_DISPLAY_ROTATION;
    public long lastHitTime;

    public ItemDisplayEntity(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(DISPLAY_ROTATION_TRACKER, DEFAULT_DISPLAY_ROTATION);
    }

    @Override
    public void tick() {
        super.tick();

        float angle = dataTracker.get(DISPLAY_ROTATION_TRACKER);
        if (displayRotation != angle) {
            setDisplayRotation(angle);
        }
    }

    public void setDisplayRotation(float angle) {
        displayRotation = angle;
        dataTracker.set(DISPLAY_ROTATION_TRACKER, angle);
    }

    public float getDisplayRotation() {
        return displayRotation;
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
        return stack.isIn(ModTags.SHERDS) || stack.isIn(ModTags.SMITHING_TEMPLATES);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);

        if (!displayedItem.isEmpty()) {
            nbt.put(DISPLAYED_ITEM_NBT_KEY, displayedItem.encodeAllowEmpty(this.getRegistryManager()));
        }

        if (displayRotation != DEFAULT_DISPLAY_ROTATION) {
            nbt.putFloat(DISPLAY_ROTATION_NBT_KEY, displayRotation);
        }
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);

        if (nbt.contains(DISPLAYED_ITEM_NBT_KEY)) {
            NbtCompound heldItemNbt = nbt.getCompound(DISPLAYED_ITEM_NBT_KEY);
            displayedItem = ItemStack.fromNbtOrEmpty(this.getRegistryManager(), heldItemNbt);
        }

        if (nbt.contains(DISPLAY_ROTATION_NBT_KEY)) {
            setDisplayRotation(nbt.getFloat(DISPLAY_ROTATION_NBT_KEY));
        }
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
                getWorld().playSound(getX(), getY(), getZ(), SoundEvents.ENTITY_ARMOR_STAND_HIT, getSoundCategory(), 0.3f, 1.0f, false);
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
        ItemStack itemStack = new ItemStack(ModItems.ITEM_DISPLAY);
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
        getWorld().playSound(null, getX(), getY(), getZ(), SoundEvents.ENTITY_ARMOR_STAND_BREAK, getSoundCategory(), 1.0f, 1.0f);
    }

    private void playPutSound() {
        getWorld().playSound(null, getX(), getY(), getZ(), SoundEvents.BLOCK_CHISELED_BOOKSHELF_PLACE, getSoundCategory(), 1.0f, 1.0f);
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
        return new ItemStack(ModItems.ITEM_DISPLAY);
    }
}
