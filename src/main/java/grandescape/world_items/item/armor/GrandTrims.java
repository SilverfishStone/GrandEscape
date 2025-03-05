package grandescape.world_items.item.armor;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import grandescape.GrandEscape;
import grandescape.world_items.item.GrandItems;

import java.util.Map;

public class GrandTrims extends TrimMaterials {

    public static final ResourceKey<TrimMaterial> POISON_TRIM = registryKey("hunger_gem");

    public static void bootstrap(BootstrapContext<TrimMaterial> pContext) {
        register(pContext, POISON_TRIM, GrandItems.HUNGER_GEM.get(), Style.EMPTY.withColor(11415640), 1.1F);
    }

    private static void register(BootstrapContext<TrimMaterial> pContext, ResourceKey<TrimMaterial> pMaterialKey, Item pIngredient, Style pStyle, float pItemModelIndex) {
        register(pContext, pMaterialKey, pIngredient, pStyle, pItemModelIndex, Map.of());
    }

    private static void register(BootstrapContext<TrimMaterial> pContext, ResourceKey<TrimMaterial> pMaterialKey, Item pIngredient, Style pStyle, float pItemModelIndex, Map<Holder<ArmorMaterial>, String> pOverrideArmorMaterials) {
        TrimMaterial trimmaterial = TrimMaterial.create(pMaterialKey.location().getPath(), pIngredient, pItemModelIndex, Component.translatable(Util.makeDescriptionId("trim_material", pMaterialKey.location())).withStyle(pStyle), pOverrideArmorMaterials);
        pContext.register(pMaterialKey, trimmaterial);
    }

    private static ResourceKey<TrimMaterial> registryKey(String pKey) {
        return ResourceKey.create(Registries.TRIM_MATERIAL, ResourceLocation.fromNamespaceAndPath(GrandEscape.MODID, pKey));
    }
}

