package grandescape.world_items.block.blockentities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TreasureChestBlockEntity extends ChestBlockEntity {
    public TreasureChestBlockEntity(BlockPos pos, BlockState state) {
        super(GrandBlockEntities.TREASURE_CHEST.get(), pos, state);
    }
}
