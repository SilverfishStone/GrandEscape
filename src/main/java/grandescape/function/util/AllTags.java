package grandescape.function.util;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;
import grandescape.GrandEscape;

public class AllTags {

    public static class Items {

        public static final TagKey<Item> SCYTHES = tag("scythes");
        public static final TagKey<Item> CHEST_KEYS = tag("chest_keys");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(GrandEscape.MODID, name));
        }
    }
    public static class Biomes {
        public static final TagKey<Biome> BASALT = create("basalt");
        public static final TagKey<Biome> MUSHROOM = create("mushroom");

        public static TagKey<Biome> create(String pName) {
            return TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(GrandEscape.MODID, pName));
        }
    }

    public static class Effects {

        public static final TagKey<MobEffect> POISON = tag("poison");

        private static TagKey<MobEffect> tag(String name) {
            return AllTags.createEffect(ResourceLocation.fromNamespaceAndPath(GrandEscape.MODID, name));
        }
    }
    public static TagKey<MobEffect> createEffect(final ResourceLocation name) {
        return TagKey.create(Registries.MOB_EFFECT, name);
    }

    public static class Enchantments {
        public static final TagKey<Enchantment> CAN_BE_ON_FROST_WALKERS = create("basalt");

        public static TagKey<Enchantment> create(String pName) {
            return TagKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(GrandEscape.MODID, pName));
        }
    }


    public static <T> boolean isInTag(T value, TagKey<T> tagKey) {
        Registry<T> registry = (Registry<T>) BuiltInRegistries.REGISTRY.get(tagKey.registry().location());
        assert registry != null;
        return registry.getOrCreateTag(tagKey).contains(registry.wrapAsHolder(value));
    }
}
