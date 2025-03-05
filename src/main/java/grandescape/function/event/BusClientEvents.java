package grandescape.function.event;

import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import grandescape.GrandEscape;
import grandescape.world_items.block.blockentities.GrandBlockEntities;

@EventBusSubscriber(modid = GrandEscape.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class BusClientEvents {
    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(GrandBlockEntities.TREASURE_CHEST.get(), ChestRenderer::new);
    }
}
