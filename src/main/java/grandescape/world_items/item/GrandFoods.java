package grandescape.world_items.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class GrandFoods {

    public static final FoodProperties ROTTEN_BREAD = new FoodProperties.Builder().alwaysEdible()
            .saturationModifier(0.5f).nutrition(7).effect(() -> new MobEffectInstance(MobEffects.HUNGER, 900, 0),  1).effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 600, 0),  0.1f).build();

}
