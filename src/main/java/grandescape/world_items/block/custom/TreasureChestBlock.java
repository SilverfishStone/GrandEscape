package grandescape.world_items.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import grandescape.world_items.block.blockentities.GrandBlockEntities;
import grandescape.world_items.block.blockentities.TreasureChestBlockEntity;

import java.util.UUID;

public class TreasureChestBlock extends ChestBlock {
    public UUID TREASURE_CHEST = UUID.fromString("1651610-ec00-42b4-8576-c4246f6fa776");
    public TreasureChestBlock(Properties prop) {
        super(prop, GrandBlockEntities.TREASURE_CHEST::get);
    }

    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TreasureChestBlockEntity(pPos, pState);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            MenuProvider menuprovider;
            menuprovider = super.getMenuProvider(state, level, pos);
            if (menuprovider != null && player.getPersistentData().getBoolean("canloot")) {
                player.openMenu(menuprovider);
                player.awardStat(super.getOpenChestStat());
                PiglinAi.angerNearbyPiglins(player, true);
            }
            return InteractionResult.CONSUME;
        }
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (!pState.is(pNewState.getBlock())) {
            BlockEntity blockentity = pLevel.getBlockEntity(pPos);
            if (blockentity instanceof Container) {
                pLevel.updateNeighbourForOutputSignal(pPos, this);
            }

            if (pState.hasBlockEntity() && (!pState.is(pNewState.getBlock()) || !pNewState.hasBlockEntity())) {
                pLevel.removeBlockEntity(pPos);
            }
        }
    }

    protected Stat<ResourceLocation> getOpenChestStat() {
        return Stats.CUSTOM.get(Stats.OPEN_CHEST);
    }
}
