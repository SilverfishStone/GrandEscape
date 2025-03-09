package grandescape.function.event;

import grandescape.world_items.enchantments.GrandEnchantments;
import grandescape.world_items.item.custom.ReaperItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.armortrim.ArmorTrim;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.entity.player.UseItemOnBlockEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import grandescape.GrandEscape;
import grandescape.world_items.block.GrandBlocks;
import grandescape.world_items.effect.GrandPotions;
import grandescape.world_items.item.GrandItems;
import grandescape.world_items.item.custom.SpecialArmorItems;

import javax.annotation.Nullable;
import java.util.UUID;

@EventBusSubscriber(modid = GrandEscape.MODID)
public class SubEventCollection {
    private static final UUID SLOW_FALLING_WITH_BOOTS_ID = UUID.fromString("1651610-ec00-42b4-8576-c4246f6fa776");
    private static final AttributeModifier SLOW_FALLING_BOOTS = new AttributeModifier(ResourceLocation.withDefaultNamespace("generic.gravity"), -0.07, AttributeModifier.Operation.ADD_VALUE);
    @SubscribeEvent
    public static void playerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        Level level = player.level();
        angelBoots(player);
        hungerTrims(player);
    }

    @SubscribeEvent
    public static void playerUseTool (UseItemOnBlockEvent event) {
        if (event.getPlayer() != null) reaperEnchOnHoe(event.getItemStack(), event.getPlayer(), event.getLevel(), event.getPos());
    }

    @SubscribeEvent
    public static void onBrewingRecipeRegister(RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();

        builder.addMix(Potions.AWKWARD, GrandBlocks.ABSORBING_MUSHROOM.get().asItem(), GrandPotions.ABSORPTION);
        builder.addMix(GrandPotions.ABSORPTION, Items.REDSTONE, GrandPotions.LONG_ABSORPTION);
        builder.addMix(GrandPotions.ABSORPTION, Items.GLOWSTONE_DUST, GrandPotions.STRONG_ABSORPTION);
    }

    @SubscribeEvent
    public static void playerDestroyBegin (PlayerInteractEvent.LeftClickBlock event) {
        destroyTreasure(event.getEntity(), event.getLevel().getBlockState(event.getPos()), event.getPos(), event.getLevel());
    }

    public static void reaperEnchOnHoe (ItemStack stack, Player player, Level level, BlockPos pos) {
        HolderLookup.RegistryLookup<Enchantment> lookup = player.registryAccess().lookupOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT);
        Holder<Enchantment> gleaning = lookup.getOrThrow(GrandEnchantments.GLEANING);
        if (!(stack.getItem() instanceof ReaperItem) && stack.getEnchantmentLevel(gleaning) > 0) {
            ReaperItem.useReaper(level, player, pos, stack);
        }
    }

    public static void frostwalkers(Player player, Level level) {
        if (player.getItemBySlot(EquipmentSlot.FEET).is(GrandItems.FROST_WALKERS.get())) {
            if (!level.isClientSide) {
                float entityMoveDist = player.moveDist;
                BlockPos blockpos = player.blockPosition();
                if (player.moveDist >= entityMoveDist) {
                    SpecialArmorItems.onEntityMoved(player, level, player.blockPosition(), 2);
                }
            }
            player.setIsInPowderSnow(false);
        }
    }

    public static void angelBoots(Player player) {
        AttributeInstance gravity = player.getAttribute(Attributes.GRAVITY);
        boolean flag = player.getDeltaMovement().y <= 0.0D;
        ItemStack stack = player.getItemBySlot(EquipmentSlot.FEET);
        if (player.getItemBySlot(EquipmentSlot.FEET).is(GrandItems.ANGEL_SHOES.get()) && flag) {
            if (!gravity.hasModifier(SLOW_FALLING_BOOTS.id())) {gravity.addTransientModifier(SLOW_FALLING_BOOTS);
            }
            if (!player.level().isClientSide) {
                int nextFallTick = (int) (player.fallDistance);
                if (nextFallTick > 2) {
                    stack.hurtAndBreak(1, player, EquipmentSlot.FEET);
                    player.resetFallDistance();
                }
            }
        } else if (gravity.hasModifier(SLOW_FALLING_BOOTS.id())) {
                gravity.removeModifier(SLOW_FALLING_BOOTS);
        }
    }

    public static void hungerTrims (Player player) {
        if (trimsItem(player.level(), player, GrandItems.HUNGER_GEM.get())) {
            player.removeEffect(MobEffects.HUNGER);
            player.removeEffect(MobEffects.POISON);
        }
    }


    public static boolean trimsItem(Level world, Player player, Item material) {
        ItemStack head = player.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack body = player.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack legs = player.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack feet = player.getItemBySlot(EquipmentSlot.FEET);
        return (getTrimItem(world, head) == material) || (getTrimItem(world, body) == material) || (getTrimItem(world, legs) == material) || (getTrimItem(world, feet) == material);
    }

    @Nullable
    public static TrimMaterial getTrimMaterial(ItemStack stack) {
        ArmorTrim armorTrim = stack.get(DataComponents.TRIM);
        return armorTrim == null ? null : armorTrim.material().value();
    }

    @Nullable
    public static Item getTrimItem(Level level, ItemStack stack) {
        TrimMaterial trimMaterial = getTrimMaterial(stack);
        return trimMaterial == null ? null : trimMaterial.ingredient().value();
    }

    public static void destroyTreasure(Player player, BlockState state, BlockPos pos, Level world) {
        if ((state.getDestroyProgress(player, world, pos) > 0) && state.is(GrandBlocks.TREASURE_CHEST.get())) {
            player.displayClientMessage(Component.translatable("block.grandescape.treasure_chest.destroy"), true);
        }
    }
}
