package grandescape.function.datagen.loot;

import grandescape.GrandEscape;
import grandescape.world_items.block.GrandBlocks;
import grandescape.world_items.item.GrandItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.StructureTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.saveddata.maps.MapDecorationTypes;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.NumberProviders;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;

public record ChestLootProvider(HolderLookup.Provider registries) implements LootTableSubProvider {
    private static final Set<ResourceKey<LootTable>> LOCATIONS_MOD = new HashSet<>();
    private static final Set<ResourceKey<LootTable>> IMMUTABLE_LOCATIONS_MOD = Collections.unmodifiableSet(LOCATIONS_MOD);

    public static final ResourceKey<LootTable> DUTCHMANS_BARREL = register("chests/dutchman_ship/dutchman_barrel");
    public static final ResourceKey<LootTable> DUTCHMANS_BRIG = register("chests/dutchman_ship/dutchman_brig");
    public static final ResourceKey<LootTable> DUTCHMANS_TREASURE = register("chests/dutchman_ship/dutchmans_treasure");
    public static final ResourceKey<LootTable> CLOUD_TEMPLE = register("chests/cloud_temple");
    public static final ResourceKey<LootTable> DESERT_CATACOMB_ROOM = register("chests/desert_catacomb_room");
    public static final ResourceKey<LootTable> DESERT_CATACOMB_TREASURE = register("chests/desert_catacomb_treasure");
    public static final ResourceKey<LootTable> ICE_SETTLEMENT = register("chests/ice_settlement");
    public static final ResourceKey<LootTable> GRAND_SHROOM = register("chests/grand_shroom");
    public static final ResourceKey<LootTable> PHANTOM_LIGHTHOUSE = register("chests/phantom_lighthouse");
    public static final ResourceKey<LootTable> MOUNTAIN_CAMP = register("chests/mountain_camp");
    public static final ResourceKey<LootTable> PIGLIN_EXPLORER_CAMP = register("chests/piglin_explorer_camp");
    public static final ResourceKey<LootTable> STRIDER_HUT = register("chests/strider_hut");
    public static final ResourceKey<LootTable> PRISMARINE_LAB_HALL = register("chests/prismarine_lab/hall");
    public static final ResourceKey<LootTable> PRISMARINE_LAB = register("chests/prismarine_lab/upper_lab");
    public static final ResourceKey<LootTable> PRISMARINE_DEEP_LAB = register("chests/prismarine_lab/deep_lab");


    private static ResourceKey<LootTable> register(String nameS) {
        ResourceKey<LootTable> name = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(GrandEscape.MODID, nameS));
        if (LOCATIONS_MOD.add(name)) {
            return name;
        } else {
            throw new IllegalArgumentException(name.location() + " is already a registered built-in loot table");
        }
    }

    public static Set<ResourceKey<LootTable>> all() {
        return IMMUTABLE_LOCATIONS_MOD;
    }


    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        output.accept(DUTCHMANS_BARREL, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2.0F, 4.0F))
                        .add(LootItem.lootTableItem(Items.SALMON).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 7.0F))))
                        .add(LootItem.lootTableItem(Items.COD).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 7.0F))))
                        .add(LootItem.lootTableItem(Items.PUFFERFISH).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))))
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 2.0F))
                        .add(LootItem.lootTableItem(Items.AXOLOTL_BUCKET).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        .add(LootItem.lootTableItem(Items.TROPICAL_FISH).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))))
                        .add(LootItem.lootTableItem(Items.COD_BUCKET).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 9.0F)))))
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 2.0F))
                        .add(LootItem.lootTableItem(Items.COOKED_COD).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 7.0F))))
                        .add(LootItem.lootTableItem(Items.COOKED_SALMON).setWeight(8).apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 6.0F)))))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.FISHING_ROD).setWeight(5)
                                .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.0F, 30.0F)).when(LootItemRandomChanceCondition.randomChance(0.5F)))
                                .apply(new EnchantWithLevelsFunction.Builder(UniformGenerator.between(1, 60)).when(LootItemRandomChanceCondition.randomChance(0.5F))))));

        output.accept(DUTCHMANS_TREASURE, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(GrandItems.CUTLASS).setWeight(10)
                                .apply(new EnchantWithLevelsFunction.Builder(UniformGenerator.between(1, 80)).when(LootItemRandomChanceCondition.randomChance(0.5F)))))
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 3.0F))
                        .add(LootItem.lootTableItem(Items.GOLD_BLOCK).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 5.0F))))
                        .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(Items.IRON_BLOCK).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 9.0F))))));

        output.accept(DUTCHMANS_BRIG, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(GrandItems.DUTCHMANS_KEY)))
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2.0F, 4.0F))
                        .add(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))))
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(3.0F, 4.0F))
                        .add(LootItem.lootTableItem(Items.BONE).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        .add(LootItem.lootTableItem(Items.COBWEB).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.IRON_SWORD).setWeight(4)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                                .apply(new EnchantWithLevelsFunction.Builder(UniformGenerator.between(3, 35))))
                        .add(LootItem.lootTableItem(Items.GOLDEN_SWORD).setWeight(5)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                                .apply(new EnchantWithLevelsFunction.Builder(UniformGenerator.between(10, 35))))));

        output.accept(CLOUD_TEMPLE, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 3.0F))
                        .add(LootItem.lootTableItem(Items.GOLD_BLOCK).setWeight(4)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                        .add(EmptyLootItem.emptyItem().setWeight(5)))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.DIAMOND_PICKAXE)
                                .apply(new EnchantWithLevelsFunction.Builder(UniformGenerator.between(30, 70))))
                        .add(LootItem.lootTableItem(Items.DIAMOND_SWORD).setWeight(5)
                                .apply(new EnchantWithLevelsFunction.Builder(UniformGenerator.between(27, 80)))))
                .withPool(LootPool.lootPool().setRolls((UniformGenerator.between(1.0F, 3.0F)))
                        .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(5)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        .add(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(5)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(10)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(GrandItems.ANGEL_SHOES))));


        output.accept(DESERT_CATACOMB_ROOM, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2.0F, 5.0F))
                        .add(LootItem.lootTableItem(Items.SAND).setWeight(5)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))))
                        .add(EmptyLootItem.emptyItem().setWeight(5))
                        .add(LootItem.lootTableItem(Items.CHISELED_SANDSTONE).setWeight(5)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))))
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2.0F, 5.0F))
                        .add(LootItem.lootTableItem(Items.STRING).setWeight(4)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(Items.ROTTEN_FLESH)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))))
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2.0F, 4.0F))
                        .add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(5)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        .add(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(5)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(1)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))))
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 2.0F))
                        .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(1)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                        .add(LootItem.lootTableItem(GrandItems.HUNGER_GEM).setWeight(10)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))));

        output.accept(DESERT_CATACOMB_TREASURE, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2.0F, 5.0F))
                        .add(LootItem.lootTableItem(Items.SAND).setWeight(5)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))))
                        .add(EmptyLootItem.emptyItem().setWeight(5))
                        .add(LootItem.lootTableItem(Items.CHISELED_SANDSTONE).setWeight(5)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))))
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2.0F, 5.0F))
                        .add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(5)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        .add(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(5)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(5)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(1)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        .add(LootItem.lootTableItem(Items.ENCHANTED_BOOK).setWeight(10)
                                .apply(new EnchantWithLevelsFunction.Builder(UniformGenerator.between(34, 76))))
                        .add(LootItem.lootTableItem(Items.DIAMOND_SHOVEL).setWeight(5)
                                .apply(new EnchantWithLevelsFunction.Builder(UniformGenerator.between(34, 76)))))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.TNT).setWeight(5)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F))))
                        .add(EmptyLootItem.emptyItem().setWeight(5))));

        output.accept(GRAND_SHROOM, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(EmptyLootItem.emptyItem().setWeight(5))
                        .add(LootItem.lootTableItem(Items.MYCELIUM).setWeight(1)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                        .add(LootItem.lootTableItem(Items.ENCHANTED_BOOK).setWeight(5)
                                .apply(EnchantRandomlyFunction.randomApplicableEnchantment(registries))))
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 5.0F))
                        .add(LootItem.lootTableItem(Items.BROWN_MUSHROOM).setWeight(10)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        .add(LootItem.lootTableItem(Items.RED_MUSHROOM).setWeight(10)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))))
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 2.0F))
                        .add(LootItem.lootTableItem(GrandBlocks.ABSORBING_MUSHROOM).setWeight(1)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F))))
                        .add(LootItem.lootTableItem(GrandBlocks.ABSORBING_MUSHROOM).setWeight(5)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F))))));

        output.accept(ICE_SETTLEMENT, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2.0F, 5.0F))
                        .add(LootItem.lootTableItem(Items.GOLDEN_CARROT).setWeight(10)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(Items.IRON_NUGGET).setWeight(10)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))))
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2.0F, 4.0F))
                        .add(LootItem.lootTableItem(GrandItems.FROZEN_DIAMOND).setWeight(5)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                        .add(LootItem.lootTableItem(Items.BLUE_ICE).setWeight(5)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(GrandItems.FROST_WALKERS)
                                .apply(EnchantRandomlyFunction.randomEnchantment()).when(LootItemRandomChanceCondition.randomChance(0.5F))))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.IRON_SHOVEL).setWeight(10)
                                .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.0F, 150.0F))))
                        .add(LootItem.lootTableItem(Items.ENCHANTED_BOOK)
                                .apply(EnchantRandomlyFunction.randomEnchantment().withEnchantment(registrylookup.getOrThrow(Enchantments.FROST_WALKER))))
                        .add(LootItem.lootTableItem(Items.SNOW).setWeight(10)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))));

        output.accept(MOUNTAIN_CAMP, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 4.0F))
                        .add(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(10)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(Items.RAW_IRON).setWeight(10)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                        .add(LootItem.lootTableItem(Items.RAW_IRON_BLOCK).setWeight(1)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                        .add(LootItem.lootTableItem(Items.RAW_COPPER).setWeight(10)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 6.0F)))))
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 5.0F))
                        .add(LootItem.lootTableItem(Items.IRON_NUGGET).setWeight(10)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(Items.COPPER_ORE).setWeight(10)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                        .add(LootItem.lootTableItem(Items.DEEPSLATE_IRON_ORE).setWeight(10)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        .add(LootItem.lootTableItem(Items.DIAMOND_ORE).setWeight(5))
                        .add(LootItem.lootTableItem(Items.IRON_PICKAXE).setWeight(1)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))))));

        output.accept(PHANTOM_LIGHTHOUSE, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2.0F, 6.0F))
                        .add(LootItem.lootTableItem(Items.CHICKEN).setWeight(1)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        .add(LootItem.lootTableItem(Items.POISONOUS_POTATO).setWeight(1)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))))
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 3.0F))
                        .add(LootItem.lootTableItem(GrandItems.STALE_BREAD).setWeight(10)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F))))
                        .add(LootItem.lootTableItem(Items.CHICKEN).setWeight(1)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        .add(LootItem.lootTableItem(Items.BREAD).setWeight(2)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(EmptyLootItem.emptyItem().setWeight(1))
                        .add(LootItem.lootTableItem(GrandItems.REAPER).setWeight(1))));

        output.accept(PIGLIN_EXPLORER_CAMP, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2.0F, 4.0F))
                        .add(EmptyLootItem.emptyItem().setWeight(15))
                        .add(LootItem.lootTableItem(Items.COOKED_PORKCHOP).setWeight(5)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(Items.PORKCHOP).setWeight(10)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(3)))))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(2)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                        .add(LootItem.lootTableItem(Items.NETHERITE_INGOT).setWeight(1))
                        .add(LootItem.lootTableItem(GrandItems.PIGLIN_BOOTS).setWeight(8))
                        .add(LootItem.lootTableItem(GrandItems.PIGLIN_LEGGINGS).setWeight(8))
                        .add(LootItem.lootTableItem(GrandItems.PIGLIN_CHESTPLATE).setWeight(8))
                        .add(LootItem.lootTableItem(Items.NETHERITE_SCRAP).setWeight(5)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))))
                        .add(LootItem.lootTableItem(Items.LAVA_BUCKET).setWeight(10))
                        .add(LootItem.lootTableItem(Items.GILDED_BLACKSTONE).setWeight(5)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 6.0F)))))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.CROSSBOW).setWeight(4)
                                .apply(EnchantWithLevelsFunction.enchantWithLevels(registries, UniformGenerator.between(20, 70))))
                        .add(LootItem.lootTableItem(Items.ARROW).setWeight(10)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 16.0F))))
                        .add(LootItem.lootTableItem(Items.SPECTRAL_ARROW).setWeight(5)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 23.0F)))))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(10)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(5.0F, 8.0F))))
                        .add(LootItem.lootTableItem(Items.GOLD_BLOCK).setWeight(1)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                        .add(LootItem.lootTableItem(Items.GOLD_NUGGET).setWeight(5)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 20.0F))))
                        .add(LootItem.lootTableItem(Items.GOLDEN_SWORD).setWeight(1)
                                .apply(EnchantWithLevelsFunction.enchantWithLevels(registries, UniformGenerator.between(21, 70))))));

        output.accept(STRIDER_HUT, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 2.0F))
                        .add(LootItem.lootTableItem(Items.POTION).setWeight(1)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                                .apply(SetPotionFunction.setPotion(Potions.FIRE_RESISTANCE)))
                        .add(LootItem.lootTableItem(Items.POTION).setWeight(2)
                                .apply(SetPotionFunction.setPotion(Potions.AWKWARD))))
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 2.0F))
                        .add(LootItem.lootTableItem(Items.GOLD_NUGGET).setWeight(1)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 9))))
                        .add(LootItem.lootTableItem(Items.WARPED_FUNGUS).setWeight(5)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        .add(LootItem.lootTableItem(Items.WARPED_FUNGUS_ON_A_STICK).setWeight(1)))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(EmptyLootItem.emptyItem().setWeight(5))
                        .add(LootItem.lootTableItem(Items.SADDLE).setWeight(5))));

        output.accept(PRISMARINE_LAB_HALL, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2.0F, 4.0F))
                        .add(LootItem.lootTableItem(Items.DRIED_KELP).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 7.0F))))
                        .add(LootItem.lootTableItem(Items.KELP).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))))
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2.0F, 3.0F))
                        .add(LootItem.lootTableItem(Items.TROPICAL_FISH).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))))
                        .add(LootItem.lootTableItem(Items.COD).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        .add(LootItem.lootTableItem(Items.SALMON).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        .add(LootItem.lootTableItem(Items.INK_SAC).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.TRIDENT).setWeight(1)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                                .apply(new EnchantWithLevelsFunction.Builder(UniformGenerator.between(3, 35))))
                        .add(EmptyLootItem.emptyItem().setWeight(5))));


        output.accept(PRISMARINE_LAB, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2.0F, 4.0F))
                        .add(LootItem.lootTableItem(Items.DRIED_KELP).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 7.0F))))
                        .add(LootItem.lootTableItem(Items.KELP).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))))
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 3.0F))
                        .add(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))))
                        .add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        .add(LootItem.lootTableItem(Items.IRON_BLOCK).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))))
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 3.0F))
                        .add(LootItem.lootTableItem(Items.GLASS_BOTTLE).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        .add(LootItem.lootTableItem(Items.POTION).setWeight(2)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                                .apply(SetPotionFunction.setPotion(Potions.WATER_BREATHING))))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.IRON_BOOTS)
                                .apply(EnchantRandomlyFunction.randomEnchantment().withEnchantment(registrylookup.getOrThrow(Enchantments.DEPTH_STRIDER)))
                                .apply(EnchantWithLevelsFunction.enchantWithLevels(registries, UniformGenerator.between(0, 30))))));

        output.accept(PRISMARINE_DEEP_LAB, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 2.0F))
                        .add(LootItem.lootTableItem(Items.DRIED_KELP).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 7.0F))))
                        .add(LootItem.lootTableItem(Items.KELP).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))))
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2.0F, 4.0F))
                        .add(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))))
                        .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(Items.GOLD_BLOCK).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))))
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 2.0F))
                        .add(LootItem.lootTableItem(Items.GLASS_BOTTLE).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        .add(LootItem.lootTableItem(Items.POTION).setWeight(2)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                                .apply(SetPotionFunction.setPotion(Potions.WATER_BREATHING)))
                        .add(LootItem.lootTableItem(Items.HEART_OF_THE_SEA).setWeight(1)))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(GrandItems.DIVING_BELL)
                                .apply(EnchantRandomlyFunction.randomEnchantment().withEnchantment(registrylookup.getOrThrow(Enchantments.UNBREAKING)))
                                .apply(EnchantWithLevelsFunction.enchantWithLevels(registries, UniformGenerator.between(0, 30))))));

    }
}
