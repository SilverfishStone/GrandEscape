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
import net.minecraft.world.item.*;
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

import java.util.ArrayList;
import java.util.List;
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
        List<BlockPos> posesLevel1 = new ArrayList<>();
        List<BlockPos> posesLevel2 = new ArrayList<>();
        List<BlockPos> posesLevel3 = new ArrayList<>();
        posesLevel1.add(pContext.getClickedPos().north(1));
        posesLevel1.add(pContext.getClickedPos().south(1));
        posesLevel1.add(pContext.getClickedPos().east(1));
        posesLevel1.add(pContext.getClickedPos().west(1));
        posesLevel2.add(pContext.getClickedPos().north(2));
        posesLevel2.add(pContext.getClickedPos().south(2));
        posesLevel2.add(pContext.getClickedPos().east(2));
        posesLevel2.add(pContext.getClickedPos().west(2));
        posesLevel3.add(pContext.getClickedPos().north(3));
        posesLevel3.add(pContext.getClickedPos().south(3));
        posesLevel3.add(pContext.getClickedPos().east(3));
        posesLevel3.add(pContext.getClickedPos().west(3));
        posesLevel2.add(pContext.getClickedPos().north(1).west(1));
        posesLevel2.add(pContext.getClickedPos().south(1).east(1));
        posesLevel2.add(pContext.getClickedPos().east(1).north(1));
        posesLevel2.add(pContext.getClickedPos().west(1).south(1));
        posesLevel3.add(pContext.getClickedPos().north(2).west(1));
        posesLevel3.add(pContext.getClickedPos().south(2).east(1));
        posesLevel3.add(pContext.getClickedPos().east(2).north(1));
        posesLevel3.add(pContext.getClickedPos().west(2).south(1));
        posesLevel3.add(pContext.getClickedPos().south(2).west(1));
        posesLevel3.add(pContext.getClickedPos().north(2).east(1));
        posesLevel3.add(pContext.getClickedPos().west(2).north(1));
        posesLevel3.add(pContext.getClickedPos().east(2).south(1));
        posesLevel3.add(pContext.getClickedPos().north(2).west(2));
        posesLevel3.add(pContext.getClickedPos().south(2).east(2));
        posesLevel3.add(pContext.getClickedPos().east(2).north(2));
        posesLevel3.add(pContext.getClickedPos().west(2).south(2));

        BlockState blockstate = world.getBlockState(pos);

        Block crop = blockstate.getBlock();
        if (crop instanceof CropBlock cropBlock) {
            if (cropBlock.getAge(blockstate) == cropBlock.getMaxAge()) {
                reap(0, Ench, world, blockstate, pos);
                posesLevel1.forEach(posB -> reap(1, Ench, world, world.getBlockState(posB), posB));
                posesLevel2.forEach(posB -> reap(2, Ench, world, world.getBlockState(posB), posB));
                posesLevel3.forEach(posB -> reap(3, Ench, world, world.getBlockState(posB), posB));
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

    public static void useReaper (Level world, Player player, BlockPos pos, ItemStack stack) {
        Holder<Enchantment> ench = world.registryAccess().holderOrThrow(GrandEnchantments.GLEANING);
        int Ench = stack.getEnchantmentLevel(ench);

        List<BlockPos> posesLevel1 = new ArrayList<>();
        List<BlockPos> posesLevel2 = new ArrayList<>();
        List<BlockPos> posesLevel3 = new ArrayList<>();
        posesLevel1.add(pos.north(1));
        posesLevel1.add(pos.south(1));
        posesLevel1.add(pos.east(1));
        posesLevel1.add(pos.west(1));
        posesLevel2.add(pos.north(2));
        posesLevel2.add(pos.south(2));
        posesLevel2.add(pos.east(2));
        posesLevel2.add(pos.west(2));
        posesLevel3.add(pos.north(3));
        posesLevel3.add(pos.south(3));
        posesLevel3.add(pos.east(3));
        posesLevel3.add(pos.west(3));
        posesLevel2.add(pos.north(1).west(1));
        posesLevel2.add(pos.south(1).east(1));
        posesLevel2.add(pos.east(1).north(1));
        posesLevel2.add(pos.west(1).south(1));
        posesLevel3.add(pos.north(2).west(1));
        posesLevel3.add(pos.south(2).east(1));
        posesLevel3.add(pos.east(2).north(1));
        posesLevel3.add(pos.west(2).south(1));
        posesLevel3.add(pos.south(2).west(1));
        posesLevel3.add(pos.north(2).east(1));
        posesLevel3.add(pos.west(2).north(1));
        posesLevel3.add(pos.east(2).south(1));
        posesLevel3.add(pos.north(2).west(2));
        posesLevel3.add(pos.south(2).east(2));
        posesLevel3.add(pos.east(2).north(2));
        posesLevel3.add(pos.west(2).south(2));

        BlockState blockstate = world.getBlockState(pos);
        int i = stack.getItem() instanceof ReaperItem ? Ench : Ench - 1;

        Block crop = blockstate.getBlock();
        if (crop instanceof CropBlock cropBlock) {
            if (cropBlock.getAge(blockstate) == cropBlock.getMaxAge()) {
                if (!(stack.getItem() instanceof ReaperItem)) reapStatic(0, i, world, blockstate, pos);
                posesLevel1.forEach(posB -> reapStatic(1, i, world, world.getBlockState(posB), posB));
                posesLevel2.forEach(posB -> reapStatic(2, i, world, world.getBlockState(posB), posB));
                posesLevel3.forEach(posB -> reapStatic(3, i, world, world.getBlockState(posB), posB));
                if (!player.isCreative()) {
                    stack.hurtAndBreak(1, Objects.requireNonNull(player), Objects.requireNonNull(stack.getEquipmentSlot()));
                }
            }
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

    public static void reapStatic (int required, int levelEnch, Level levelPoint, BlockState state, BlockPos position) {
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
