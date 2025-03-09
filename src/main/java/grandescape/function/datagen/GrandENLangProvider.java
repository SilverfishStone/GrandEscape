package grandescape.function.datagen;

import grandescape.GrandEscape;
import grandescape.world_items.block.GrandBlocks;
import grandescape.world_items.effect.GrandPotions;
import grandescape.world_items.enchantments.GrandEnchantments;
import grandescape.world_items.item.GrandItems;
import grandescape.world_items.item.armor.GrandTrims;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class GrandENLangProvider extends LanguageProvider {
    public GrandENLangProvider(PackOutput output) {
        super(output, GrandEscape.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addItem(GrandItems.DUTCHMANS_KEY, "Dutchman's Key");
        addItem(GrandItems.ANGEL_SHOES, "Angel Shoes");
        addItem(GrandItems.CUTLASS, "Cutlass");
        addItem(GrandItems.DIVING_BELL, "Diving Bell");
        addItem(GrandItems.FROST_WALKERS, "Frost Walkers");
        addItem(GrandItems.FROZEN_DIAMOND, "Frozen Diamond");
        addItem(GrandItems.HUNGER_GEM, "Hunger Gem");
        addItem(GrandItems.PIGLIN_BOOTS, "Piglin Boots");
        addItem(GrandItems.PIGLIN_CHESTPLATE, "Piglin Chestplate");
        addItem(GrandItems.PIGLIN_LEGGINGS, "Piglin Leggings");
        addItem(GrandItems.PIGLIN_HELMET, "Piglin Helmet");
        addItem(GrandItems.STALE_BREAD, "Stale Bread");
        addItem(GrandItems.REAPER, "Reaper");
        treasureChest();
        addBlock(GrandBlocks.ABSORBING_MUSHROOM, "Absorbing Mushroom");
        addBlock(GrandBlocks.ABSORBING_MUSHROOM_BLOCK, "Absorbing Mushroom Block");
        addBlock(GrandBlocks.CANNON_BALL_PILE, "Cannonball Pile");
        addBlock(GrandBlocks.SKELETON, "Skeleton Block");
        addBlock(GrandBlocks.POTTED_ABSORBING_MUSHROOM, "Absorbing Mushroom in a Pot");
        addEnchantment(GrandEnchantments.GLEANING, "Gleaning", "More crops can be harvested harvested per level.");
        addPotion(GrandPotions.ABSORPTION, "Absorption");
        addTrim(GrandTrims.POISON_TRIM, "Hunger Gem");
        add("creativetab.grandescape.grand_escape_items_tab", "Grand Escape");
    }

    private void addPotion (Holder<Potion> potion, String translation) {
        String potionTranslation = "item.minecraft.potion.effect." + potion.getKey().location().getPath();
        add(potionTranslation, translation + "Potion");
    }

    private void addTrim (ResourceKey<TrimMaterial> trim, String translation) {
        add("trim_material.minecraft." + trim.location().getPath(), translation + " Material");
    }

    private void addEnchantment (ResourceKey<Enchantment> ench, String name, String desc) {
        String enchTranslatable = "enchantment.grandescape." + ench.location().getPath();
        add(enchTranslatable, name);
        add(enchTranslatable + ".desc", desc);
    }

    private void treasureChest() {
        addBlock(GrandBlocks.TREASURE_CHEST, "Treasure Chest");
        add("block.grandescape.treasure_chest.destroy", "Destroying a Treasure Chest will destroy the loot inside.");
        add("block.grandescape.treasure_chest.info", "Opening a Treasure Chest requires a key...");
    }
}
