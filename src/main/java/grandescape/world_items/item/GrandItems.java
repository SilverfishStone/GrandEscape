package grandescape.world_items.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import grandescape.GrandEscape;
import grandescape.world_items.item.armor.GrandArmorMaterials;
import grandescape.world_items.item.custom.*;

import static net.minecraft.world.item.Tiers.DIAMOND;

public class GrandItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(GrandEscape.MODID);

    public static final DeferredItem<Item> STALE_BREAD = ITEMS.register("stale_bread",
            () -> new Item(new Item.Properties().food(GrandFoods.ROTTEN_BREAD)));
    public static final DeferredItem<Item> FROZEN_DIAMOND = ITEMS.register("frozen_diamond",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> HUNGER_GEM = ITEMS.register("hunger_gem",
            () -> new SpecialGemItem(new Item.Properties()));
    public static final DeferredItem<Item> DUTCHMANS_KEY = ITEMS.register("dutchmans_key",
            () -> new KeyItem(new Item.Properties()));

    public static final DeferredItem<Item> FROST_WALKERS = ITEMS.register("frost_walkers",
            () -> new SpecialArmorItems(ArmorMaterials.DIAMOND, ArmorItem.Type.BOOTS, new Item.Properties(), 1));
    public static final DeferredItem<Item> PIGLIN_BOOTS = ITEMS.register("piglin_boots",
            () -> new SpecialArmorItems(GrandArmorMaterials.PIGLIN, ArmorItem.Type.BOOTS, new Item.Properties(), 2));
    public static final DeferredItem<Item> PIGLIN_LEGGINGS = ITEMS.register("piglin_leggings",
            () -> new SpecialArmorItems(GrandArmorMaterials.PIGLIN, ArmorItem.Type.LEGGINGS, new Item.Properties(), 2));
    public static final DeferredItem<Item> PIGLIN_CHESTPLATE = ITEMS.register("piglin_chestplate",
            () -> new SpecialArmorItems(GrandArmorMaterials.PIGLIN, ArmorItem.Type.CHESTPLATE, new Item.Properties(), 2));
    public static final DeferredItem<Item> PIGLIN_HELMET = ITEMS.register("piglin_helmet",
            () -> new SpecialArmorItems(GrandArmorMaterials.PIGLIN, ArmorItem.Type.HELMET, new Item.Properties(), 2));
    public static final DeferredItem<Item> ANGEL_SHOES = ITEMS.register("angel_shoes",
            () -> new SpecialArmorItems(ArmorMaterials.DIAMOND, ArmorItem.Type.BOOTS, new Item.Properties(), 3));
    public static final DeferredItem<Item> REAPER = ITEMS.register("reaper",
            () -> new ReaperItem(DIAMOND, new Item.Properties().stacksTo(1).attributes(HoeItem.createAttributes(DIAMOND, 2, -2.6f))));
    public static final DeferredItem<Item> CUTLASS = ITEMS.register("cutlass",
            () -> new SpecialSwordItem(DIAMOND, new Item.Properties().stacksTo(1).attributes(SwordItem.createAttributes(DIAMOND, 0, -1.2f))));


    public static boolean inHandSlots (LivingEntity living, Item item) {
        return living.getItemBySlot(EquipmentSlot.OFFHAND).is(item) || living.getItemBySlot(EquipmentSlot.MAINHAND).is(item);
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
