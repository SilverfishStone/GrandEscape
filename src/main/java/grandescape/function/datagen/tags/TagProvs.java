package grandescape.function.datagen.tags;

import grandescape.GrandEscape;
import grandescape.function.util.AllTags;
import grandescape.world_items.block.GrandBlocks;
import grandescape.world_items.enchantments.GrandEnchantments;
import grandescape.world_items.item.GrandItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class TagProvs {

    public static class GrandBlockTagGenerator extends BlockTagsProvider {
        public GrandBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, lookupProvider, GrandEscape.MODID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                    .add(GrandBlocks.SKELETON.get(),
                            GrandBlocks.CANNON_BALL_PILE.get());
        }
    }


    public static class GrandItemTagGenerator extends ItemTagsProvider {
        public GrandItemTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                     CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, lookupProvider, blockTags, GrandEscape.MODID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            this.tag(ItemTags.FREEZE_IMMUNE_WEARABLES)
                    .add(GrandItems.FROST_WALKERS.get());

            this.tag(ItemTags.HEAD_ARMOR_ENCHANTABLE)
                    .add(GrandItems.DIVING_BELL.get(),
                            GrandItems.PIGLIN_HELMET.get());

            this.tag(ItemTags.FOOT_ARMOR)
                    .add(GrandItems.PIGLIN_BOOTS.get(),
                            GrandItems.ANGEL_SHOES.get(),
                            GrandItems.FROST_WALKERS.get());

            this.tag(ItemTags.CHEST_ARMOR_ENCHANTABLE)
                    .add(GrandItems.PIGLIN_CHESTPLATE.get());

            this.tag(ItemTags.LEG_ARMOR_ENCHANTABLE)
                    .add(GrandItems.PIGLIN_LEGGINGS.get());

            this.tag(AllTags.Items.SCYTHES)
                    .add(GrandItems.REAPER.get())
                    .addTag(ItemTags.HOES);

            this.tag(ItemTags.PIGLIN_LOVED)
                    .add(GrandItems.PIGLIN_LEGGINGS.get(),
                            GrandItems.PIGLIN_CHESTPLATE.get(),
                            GrandItems.PIGLIN_BOOTS.get(),
                            GrandItems.PIGLIN_HELMET.get());

            this.tag(ItemTags.TRIMMABLE_ARMOR)
                    .add(GrandItems.PIGLIN_LEGGINGS.get(),
                            GrandItems.PIGLIN_CHESTPLATE.get(),
                            GrandItems.PIGLIN_BOOTS.get(),
                            GrandItems.PIGLIN_HELMET.get(),
                            GrandItems.FROST_WALKERS.get(),
                            GrandItems.ANGEL_SHOES.get());

            this.tag(ItemTags.TRIM_MATERIALS)
                    .add(GrandItems.HUNGER_GEM.get());

            this.tag(AllTags.Items.CHEST_KEYS)
                    .add(GrandItems.DUTCHMANS_KEY.get());
        }
    }



    public static class GrandEnchantTags extends EnchantmentTagsProvider {

        public GrandEnchantTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(output, lookupProvider);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            this.tag(EnchantmentTags.NON_TREASURE).add(GrandEnchantments.GLEANING);

        }
    }

    public static class GrandBiomeTags extends BiomeTagsProvider {

        public GrandBiomeTags(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
            super(output, provider);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            this.tag(AllTags.Biomes.BASALT).add(Biomes.BASALT_DELTAS);
            this.tag(AllTags.Biomes.MUSHROOM).add(Biomes.MUSHROOM_FIELDS);
        }
    }
}
