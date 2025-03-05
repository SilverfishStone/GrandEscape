package grandescape.function.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import grandescape.GrandEscape;
import grandescape.function.util.AllTags;
import grandescape.world_items.item.GrandItems;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class GrandItemTagGenerator extends ItemTagsProvider {
    public GrandItemTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, GrandEscape.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ItemTags.FREEZE_IMMUNE_WEARABLES)
                .add(GrandItems.FROST_WALKERS.get());

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
