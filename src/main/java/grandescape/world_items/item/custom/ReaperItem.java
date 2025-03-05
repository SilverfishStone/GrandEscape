package grandescape.world_items.item.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import grandescape.world_items.enchantments.GrandEnchantments;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Supplier;

import static net.minecraft.world.level.block.Block.getDrops;

public class ReaperItem extends HoeItem {
    public ReaperItem(Tier pTier, Properties pProperties) {
        super(pTier, pProperties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext pContext) {
        Level world = pContext.getLevel();
        Holder<Enchantment> ench = pContext.getLevel().registryAccess().holderOrThrow(GrandEnchantments.GLEANING);
        int Ench = pContext.getItemInHand().getEnchantmentLevel(ench);
        BlockPos pos = pContext.getClickedPos();

        BlockPos northA = pContext.getClickedPos().north(1);
        BlockPos southA = pContext.getClickedPos().south(1);
        BlockPos eastA = pContext.getClickedPos().east(1);
        BlockPos westA = pContext.getClickedPos().west(1);
        BlockPos northB = pContext.getClickedPos().north(2);
        BlockPos southB = pContext.getClickedPos().south(2);
        BlockPos eastB = pContext.getClickedPos().east(2);
        BlockPos westB = pContext.getClickedPos().west(2);
        BlockPos northC = pContext.getClickedPos().north(3);
        BlockPos southC = pContext.getClickedPos().south(3);
        BlockPos eastC = pContext.getClickedPos().east(3);
        BlockPos westC = pContext.getClickedPos().west(3);
        BlockPos northA2 = pContext.getClickedPos().north(1).west(1);
        BlockPos southA2 = pContext.getClickedPos().south(1).east(1);
        BlockPos eastA2 = pContext.getClickedPos().east(1).north(1);
        BlockPos westA2 = pContext.getClickedPos().west(1).south(1);
        BlockPos northB2 = pContext.getClickedPos().north(2).west(1);
        BlockPos southB2 = pContext.getClickedPos().south(2).east(1);
        BlockPos eastB2 = pContext.getClickedPos().east(2).north(1);
        BlockPos westB2 = pContext.getClickedPos().west(2).south(1);
        BlockPos northB3 = pContext.getClickedPos().south(2).west(1);
        BlockPos southB3 = pContext.getClickedPos().north(2).east(1);
        BlockPos eastB3 = pContext.getClickedPos().west(2).north(1);
        BlockPos westB3 = pContext.getClickedPos().east(2).south(1);
        BlockPos northC3 = pContext.getClickedPos().north(2).west(2);
        BlockPos southC3 = pContext.getClickedPos().south(2).east(2);
        BlockPos eastC3 = pContext.getClickedPos().east(2).north(2);
        BlockPos westC3 = pContext.getClickedPos().west(2).south(2);

        BlockState blockstate = world.getBlockState(pos);
        BlockState north1 = world.getBlockState(northA);
        BlockState south1 = world.getBlockState(southA);
        BlockState east1 = world.getBlockState(eastA);
        BlockState west1 = world.getBlockState(westA);
        BlockState north2 = world.getBlockState(northB);
        BlockState south2 = world.getBlockState(southB);
        BlockState east2 = world.getBlockState(eastB);
        BlockState west2 = world.getBlockState(westB);
        BlockState north2A = world.getBlockState(northA2);
        BlockState south2A = world.getBlockState(southA2);
        BlockState east2A = world.getBlockState(eastA2);
        BlockState west2A = world.getBlockState(westA2);
        BlockState north3 = world.getBlockState(northC);
        BlockState south3 = world.getBlockState(southC);
        BlockState east3 = world.getBlockState(eastC);
        BlockState west3 = world.getBlockState(westC);
        BlockState north4 = world.getBlockState(northB2);
        BlockState south4 = world.getBlockState(southB2);
        BlockState east4 = world.getBlockState(eastB2);
        BlockState west4 = world.getBlockState(westB2);
        BlockState north5 = world.getBlockState(northB3);
        BlockState south5 = world.getBlockState(southB3);
        BlockState east5 = world.getBlockState(eastB3);
        BlockState west5 = world.getBlockState(westB3);
        BlockState north3A = world.getBlockState(northC3);
        BlockState south3A = world.getBlockState(southC3);
        BlockState east3A = world.getBlockState(eastC3);
        BlockState west3A = world.getBlockState(westC3);

        Block crop = blockstate.getBlock();
        if (crop instanceof CropBlock cropBlock) {
            if (cropBlock.getAge(blockstate) == cropBlock.getMaxAge()) {
                reap(0, Ench, world, blockstate, pos);
                reap(1, Ench, world, west1, westA);
                reap(1, Ench, world, east1, eastA);
                reap(1, Ench, world, north1, northA);
                reap(1, Ench, world, south1, southA);
                reap(2, Ench, world, west2, westB);
                reap(2, Ench, world, east2, eastB);
                reap(2, Ench, world, north2, northB);
                reap(2, Ench, world, south2, southB);
                reap(2, Ench, world, west2A, westA2);
                reap(2, Ench, world, east2A, eastA2);
                reap(2, Ench, world, north2A, northA2);
                reap(2, Ench, world, south2A, southA2);
                reap(3, Ench, world, west3, westC);
                reap(3, Ench, world, east3, eastC);
                reap(3, Ench, world, north3, northC);
                reap(3, Ench, world, south3, southC);
                reap(3, Ench, world, west4, westB2);
                reap(3, Ench, world, east4, eastB2);
                reap(3, Ench, world, north4, northB2);
                reap(3, Ench, world, south4, southB2);
                reap(3, Ench, world, west5, westB3);
                reap(3, Ench, world, east5, eastB3);
                reap(3, Ench, world, north5, northB3);
                reap(3, Ench, world, south5, southB3);
                reap(3, Ench, world, west3A, westC3);
                reap(3, Ench, world, east3A, eastC3);
                reap(3, Ench, world, north3A, northC3);
                reap(3, Ench, world, south3A, southC3);
                if (!gamemodeCreative(pContext.getPlayer())) {
                    pContext.getItemInHand().hurtAndBreak(1, Objects.requireNonNull(pContext.getPlayer()), Objects.requireNonNull(pContext.getItemInHand().getEquipmentSlot()));
                }
            } else {
                return InteractionResult.FAIL;
            }
            return InteractionResult.SUCCESS;
        } else {
            return super.useOn(pContext);
        }
    }

    public void reap (int required, int levelEnch, Level levelPoint, BlockState state, BlockPos position) {
        if (required <= levelEnch) {
            if (state.getBlock() instanceof CropBlock setcropBlock) {
                if (setcropBlock.getAge(state) == setcropBlock.getMaxAge()) {
                    if (levelPoint instanceof ServerLevel) {
                        getDrops(state, (ServerLevel) levelPoint, position, null).forEach((p_152406_) -> {
                            popResource(levelPoint, position, p_152406_);
                        });
                        state.spawnAfterBreak((ServerLevel) levelPoint, position, ItemStack.EMPTY, false);
                    }
                    levelPoint.setBlock(position, setcropBlock.getStateForAge(1), 3);
                }
            }
        }
    }

    @Override
    public boolean isValidRepairItem(ItemStack pToRepair, ItemStack pRepair) {
        return pRepair.is(Items.IRON_INGOT) || pRepair.is(this);
    }

    /*
        Directly from Vanilla Block Class
        Sometimes copying existing classes is the simplest solution to a problem.
         */
    public static void popResource(Level pLevel, BlockPos pPos, ItemStack pStack) {
        double d0 = (double) EntityType.ITEM.getHeight() / 2.0D;
        double d1 = (double)pPos.getX() + 0.5D + Mth.nextDouble(pLevel.random, -0.25D, 0.25D);
        double d2 = (double)pPos.getY() + 0.5D + Mth.nextDouble(pLevel.random, -0.25D, 0.25D) - d0;
        double d3 = (double)pPos.getZ() + 0.5D + Mth.nextDouble(pLevel.random, -0.25D, 0.25D);
        popResource(pLevel, () -> {
            return new ItemEntity(pLevel, d1, d2, d3, pStack);
        }, pStack);
    }
    private static void popResource(Level pLevel, Supplier<ItemEntity> pItemEntitySupplier, ItemStack pStack) {
        if (!pLevel.isClientSide && !pStack.isEmpty() && pLevel.getGameRules().getBoolean(GameRules.RULE_DOBLOCKDROPS) && !pLevel.restoringBlockSnapshots) {
            ItemEntity itementity = pItemEntitySupplier.get();
            itementity.setDefaultPickUpDelay();
            pLevel.addFreshEntity(itementity);
        }
    }

    public static boolean gamemodeCreative(Entity player) {
        if (player instanceof ServerPlayer serverPlayer) {
            return serverPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
        } else if (player.level().isClientSide() && player instanceof Player thisplayer) {
            return Minecraft.getInstance().getConnection().getPlayerInfo(thisplayer.getGameProfile().getId()) != null
                    && Minecraft.getInstance().getConnection().getPlayerInfo(thisplayer.getGameProfile().getId()).getGameMode() == GameType.CREATIVE;
        }
        return player.isInvulnerable();
    }
}
