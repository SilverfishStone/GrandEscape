package grandescape.function.datagen;

import grandescape.function.datagen.loot.ChestLootProvider;
import grandescape.function.datagen.loot.GrandBlockLootTables;
import grandescape.function.datagen.tags.TagProvs;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import grandescape.GrandEscape;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = GrandEscape.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(), List.of(
                new LootTableProvider.SubProviderEntry(GrandBlockLootTables::new, LootContextParamSets.BLOCK),
                new LootTableProvider.SubProviderEntry(ChestLootProvider::new, LootContextParamSets.CHEST)
        ), lookupProvider));
        generator.addProvider(event.includeServer(), new GrandRecipeProvider(packOutput, lookupProvider));

        BlockTagsProvider blockTagsProvider = new TagProvs.GrandBlockTagGenerator(packOutput, lookupProvider, existingFileHelper);
        generator.addProvider(event.includeServer(), blockTagsProvider);
        generator.addProvider(event.includeServer(), new TagProvs.GrandItemTagGenerator(packOutput, lookupProvider, blockTagsProvider.contentsGetter(), existingFileHelper));
        //generator.addProvider(event.includeServer(), new TagProvs.GrandEnchantTags(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new TagProvs.GrandBiomeTags(packOutput, lookupProvider));

        generator.addProvider(event.includeClient(), new GrandItemModelProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new GrandBlockstateProvider(packOutput, existingFileHelper));

        generator.addProvider(event.includeServer(), new GrandDatapackProviders(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new GrandENLangProvider(packOutput));


    }
}


