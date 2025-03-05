package grandescape;

import com.mojang.logging.LogUtils;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import grandescape.world_items.block.GrandBlocks;
import grandescape.world_items.block.blockentities.GrandBlockEntities;
import grandescape.world_items.effect.GrandPotions;
//import grandescape.world_items.item.AllTab;
import grandescape.world_items.item.GrandItems;
import org.slf4j.Logger;

@Mod(GrandEscape.MODID)
public class GrandEscape
{
    public static final String MODID = "grandescape";
    private static final Logger LOGGER = LogUtils.getLogger();

    public GrandEscape(IEventBus modEventBus) {

        modEventBus.addListener(this::commonSetup);

        GrandBlocks.register(modEventBus);
        GrandItems.register(modEventBus);
        GrandPotions.register(modEventBus);
        //AllTab.register(modEventBus);
        GrandBlockEntities.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

        if (curiosInstalled()) {
            //RegisterModCurios.register();
        }
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }

    public static boolean lootrInstalled () {
        return ModList.get().isLoaded("lootr");
    }
    public static boolean curiosInstalled () {
        return ModList.get().isLoaded("curios");
    }
}
