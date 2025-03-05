package grandescape.world_items.effect;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import grandescape.GrandEscape;

public class GrandPotions
{
    private static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(BuiltInRegistries.POTION, GrandEscape.MODID);

    public static final Holder<Potion> ABSORPTION = POTIONS.register("absorption", () -> new Potion(new MobEffectInstance(MobEffects.ABSORPTION, 3600, 2)));
    public static final Holder<Potion> LONG_ABSORPTION = POTIONS.register("long_absorption", () -> new Potion(new MobEffectInstance(MobEffects.ABSORPTION, 9600, 2)));
    public static final Holder<Potion> STRONG_ABSORPTION = POTIONS.register("strong_absorption", () -> new Potion(new MobEffectInstance(MobEffects.ABSORPTION, 1800, 3)));
    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}