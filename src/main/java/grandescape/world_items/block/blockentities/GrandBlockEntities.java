package grandescape.world_items.block.blockentities;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import grandescape.GrandEscape;
import grandescape.world_items.block.GrandBlocks;

import java.util.function.Supplier;

public class GrandBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, GrandEscape.MODID);

    public static final Supplier<BlockEntityType<TreasureChestBlockEntity>> TREASURE_CHEST =
            BLOCK_ENTITIES.register("pedestal_be", () -> BlockEntityType.Builder.of(
                    TreasureChestBlockEntity::new, GrandBlocks.TREASURE_CHEST.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}

