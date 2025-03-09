package grandescape.world_items.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import grandescape.GrandEscape;
import grandescape.world_items.block.GrandBlocks;

import java.util.function.Supplier;

public class AllTab {
     public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, GrandEscape.MODID);

    public static final Supplier<CreativeModeTab> GRAND_ESCAPE_TAB = CREATIVE_MODE_TAB.register("grand_escape_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(GrandBlocks.CANNON_BALL_PILE.get()))
                    .title(Component.translatable("creativetab.grandescape.grand_escape_items_tab"))
                    .displayItems((itemDisplayParameters, pOutput) -> {
                        pOutput.accept(GrandItems.REAPER.get());
                        pOutput.accept(GrandItems.CUTLASS.get());
                        pOutput.accept(GrandItems.FROST_WALKERS.get());
                        pOutput.accept(GrandItems.ANGEL_SHOES.get());
                        pOutput.accept(GrandItems.PIGLIN_BOOTS.get());
                        pOutput.accept(GrandItems.PIGLIN_CHESTPLATE.get());
                        pOutput.accept(GrandItems.PIGLIN_LEGGINGS.get());
                        pOutput.accept(GrandItems.FROZEN_DIAMOND.get());
                        pOutput.accept(GrandItems.HUNGER_GEM.get());
                        pOutput.accept(GrandItems.DUTCHMANS_KEY.get());
                        pOutput.accept(GrandItems.STALE_BREAD.get());
                        pOutput.accept(GrandItems.DIVING_BELL.get());

                        pOutput.accept(GrandBlocks.TREASURE_CHEST.get());
                        pOutput.accept(GrandBlocks.CANNON_BALL_PILE.get());
                        pOutput.accept(GrandBlocks.SKELETON.get());
                        pOutput.accept(GrandBlocks.ABSORBING_MUSHROOM.get());
                        pOutput.accept(GrandBlocks.ABSORBING_MUSHROOM_BLOCK.get());
                        }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
