package grandescape.world_items.block;

import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import grandescape.GrandEscape;
import grandescape.world_items.block.custom.CannonballBlock;
import grandescape.world_items.block.custom.SkeletonBlock;
import grandescape.world_items.block.custom.TreasureChestBlock;
import grandescape.world_items.item.GrandItems;

import java.util.function.Function;

public class GrandBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(GrandEscape.MODID);

    public static final DeferredBlock<Block> SKELETON = registerBlock("skeleton",
            set -> new SkeletonBlock(BlockBehaviour.Properties.of()), BlockBehaviour.Properties.ofFullCopy(Blocks.BONE_BLOCK).sound(SoundType.BONE_BLOCK).noOcclusion().noCollission());
    public static final DeferredBlock<Block> ABSORBING_MUSHROOM = registerBlock("absorbing_mushroom",
            set -> new MushroomBlock(TreeFeatures.HUGE_RED_MUSHROOM, BlockBehaviour.Properties.of()), BlockBehaviour.Properties.ofFullCopy(Blocks.RED_MUSHROOM).mapColor(MapColor.COLOR_YELLOW).lightLevel((absorb) -> 15));
    public static final DeferredBlock<Block> POTTED_ABSORBING_MUSHROOM = registerBlock("potted_absorbing_mushroom",
            set -> new FlowerPotBlock(() -> ((FlowerPotBlock)Blocks.FLOWER_POT), GrandBlocks.ABSORBING_MUSHROOM, BlockBehaviour.Properties.of()), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_RED_MUSHROOM).noOcclusion());
    public static final DeferredBlock<Block> ABSORBING_MUSHROOM_BLOCK = registerBlock("absorbing_mushroom_block",
            set -> new Block(BlockBehaviour.Properties.of()), BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM_BLOCK).mapColor(MapColor.COLOR_YELLOW).lightLevel((absorb) -> 15));


    public static final DeferredBlock<Block> TREASURE_CHEST = registerBlock("treasure_chest",
            set -> new TreasureChestBlock(BlockBehaviour.Properties.of()), BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST));
    public static final DeferredBlock<Block> CANNON_BALL_PILE = registerBlock("cannonball",
            set -> new CannonballBlock(BlockBehaviour.Properties.of().noOcclusion()), BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, ? extends T> block, BlockBehaviour.Properties properties) {
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, block, properties);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> DeferredItem<Item> registerBlockItem(String name, DeferredBlock<T> block) {
        return GrandItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
