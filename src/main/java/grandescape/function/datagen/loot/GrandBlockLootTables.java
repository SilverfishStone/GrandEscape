package grandescape.function.datagen.loot;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import grandescape.world_items.block.GrandBlocks;
import grandescape.world_items.block.custom.CannonballBlock;

import java.util.List;
import java.util.Set;

public class GrandBlockLootTables extends BlockLootSubProvider {

    public GrandBlockLootTables(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        this.add(GrandBlocks.SKELETON.get(), (p_249779_) -> this.createSingleItemTableWithSilkTouch(p_249779_, Items.BONE));
        this.add(GrandBlocks.ABSORBING_MUSHROOM_BLOCK.get(), (p_248785_) -> this.createMushroomBlockDrop(p_248785_, GrandBlocks.ABSORBING_MUSHROOM.get()));
        this.dropSelf(GrandBlocks.ABSORBING_MUSHROOM.get());
        this.dropSelf(GrandBlocks.TREASURE_CHEST.get());
        this.add(GrandBlocks.CANNON_BALL_PILE.get(), (ball) -> createCannonBallDrops(GrandBlocks.CANNON_BALL_PILE.get()));
        this.add(GrandBlocks.POTTED_ABSORBING_MUSHROOM.get(), createPotFlowerItemTable(GrandBlocks.ABSORBING_MUSHROOM.get()));
    }

    protected LootTable.Builder createCannonBallDrops(Block pCannonBall) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(this.applyExplosionDecay(pCannonBall, LootItem.lootTableItem(pCannonBall).apply(List.of(2, 3, 4, 5, 6, 7), (p_249985_) -> SetItemCountFunction.setCount(ConstantValue.exactly((float)p_249985_.intValue())).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pCannonBall).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CannonballBlock.CANNON_BALLS, p_249985_)))))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return GrandBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
