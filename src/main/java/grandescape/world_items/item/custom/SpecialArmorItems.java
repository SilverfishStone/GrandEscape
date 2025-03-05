package grandescape.world_items.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FrostedIceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import grandescape.world_items.item.GrandItems;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class SpecialArmorItems extends ArmorItem {
    public int type;
    public Type armor;

    public SpecialArmorItems(Holder<ArmorMaterial> pMaterial, Type pType, Properties pProperties, int type) {
        super(pMaterial, pType, pProperties);
        this.type = type;
        this.armor = pType;
    }

    @Override
    public boolean canWalkOnPowderedSnow(ItemStack stack, LivingEntity wearer) {
        return stack.is(GrandItems.FROST_WALKERS.get());
    }

    @Override
    public void inventoryTick(@NotNull ItemStack pStack, @NotNull Level pLevel, @NotNull Entity pEntity, int pSlotId, boolean pIsSelected) {
        Player player = (Player) pEntity;
        if (type == 1 && player.getItemBySlot(EquipmentSlot.FEET).is(this)) {
            float entityMoveDist = player.moveDist;
            if (!pEntity.level().isClientSide) {
                BlockPos blockpos = player.blockPosition();
                if (player.xOld != player.getX() || player.zOld != player.getZ()) {
                    onEntityMoved(player, pLevel, blockpos, 2);
                }
            }
            player.setIsInPowderSnow(false);
        }
    }

    public static void onEntityMoved(LivingEntity pLiving, Level pLevel, BlockPos pPos, int pLevelConflicting) {
        if (pLiving.onGround()) {
            BlockState blockstate = Blocks.FROSTED_ICE.defaultBlockState();
            int i = Math.min(16, 2 + pLevelConflicting);
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
            Iterator var7 = BlockPos.betweenClosed(pPos.offset(-i, -1, -i), pPos.offset(i, -1, i)).iterator();

            while(var7.hasNext()) {
                BlockPos blockpos = (BlockPos)var7.next();
                if (blockpos.closerToCenterThan(pLiving.position(), (double)i)) {
                    blockpos$mutableblockpos.set(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ());
                    BlockState blockstate1 = pLevel.getBlockState(blockpos$mutableblockpos);
                    if (blockstate1.isAir()) {
                        BlockState blockstate2 = pLevel.getBlockState(blockpos);
                        if (blockstate2 == FrostedIceBlock.meltsInto() && blockstate.canSurvive(pLevel, blockpos) && pLevel.isUnobstructed(blockstate, blockpos, CollisionContext.empty())) {
                            pLevel.setBlockAndUpdate(blockpos, blockstate);
                            pLevel.scheduleTick(blockpos, Blocks.FROSTED_ICE, Mth.nextInt(pLiving.getRandom(), 60, 120));
                        }
                    }
                }
            }
        }

    }


    @Override
    public boolean makesPiglinsNeutral(ItemStack stack, LivingEntity wearer) {
        return (this.type == 2);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public boolean isValidRepairItem(@NotNull ItemStack toRepair, @NotNull ItemStack repair) {
        boolean canRepair;
        if (type == 1) {
            canRepair = this.material.value().repairIngredient().get().test(GrandItems.FROZEN_DIAMOND.get().getDefaultInstance());
        } else {
            canRepair = this.material.value().repairIngredient().get().test(this.getDefaultInstance());
        }
        return canRepair || super.isValidRepairItem(toRepair, repair);
    }
}

