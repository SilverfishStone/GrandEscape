package grandescape.function.datagen;

import grandescape.world_items.worldgen.structures.GrandStructures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import grandescape.GrandEscape;
import grandescape.world_items.enchantments.GrandEnchantments;
import grandescape.world_items.item.armor.GrandTrims;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class GrandDatapackProviders extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.TRIM_MATERIAL, GrandTrims::bootstrap)

            .add(Registries.STRUCTURE, GrandStructures.StructureGens::bootstrap)
            .add(Registries.TEMPLATE_POOL, GrandStructures.StructurePools::bootstrap)
            .add(Registries.STRUCTURE_SET, GrandStructures.StructureSets::bootstrap)

            .add(Registries.ENCHANTMENT, GrandEnchantments::bootstrap);


    public GrandDatapackProviders(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER,Set.of(GrandEscape.MODID));
    }
}
