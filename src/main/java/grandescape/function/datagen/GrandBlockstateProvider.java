package grandescape.function.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import grandescape.GrandEscape;
import grandescape.world_items.block.GrandBlocks;

public class GrandBlockstateProvider extends BlockStateProvider {

    public GrandBlockstateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, GrandEscape.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlockWithItem(GrandBlocks.ABSORBING_MUSHROOM.get(), models().cross(blockTexture(GrandBlocks.ABSORBING_MUSHROOM.get()).getPath(),
                blockTexture(GrandBlocks.ABSORBING_MUSHROOM.get())).renderType("cutout"));
        simpleBlockWithItem(GrandBlocks.POTTED_ABSORBING_MUSHROOM.get(), models().singleTexture("potted_absorbing_mushroom", ResourceLocation.withDefaultNamespace( "flower_pot_cross"), "plant",
                blockTexture(GrandBlocks.ABSORBING_MUSHROOM.get())).renderType("cutout"));
        blockWithItem(GrandBlocks.ABSORBING_MUSHROOM_BLOCK);
    }



    private void blockWithItem(DeferredBlock<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
