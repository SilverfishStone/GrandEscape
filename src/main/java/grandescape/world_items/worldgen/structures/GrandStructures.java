package grandescape.world_items.worldgen.structures;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import grandescape.GrandEscape;
import grandescape.function.util.AllTags;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.structure.*;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class GrandStructures {

    public static ResourceKey<Structure> ANCHOR_ALTAR = createKey("anchor_altar");
    public static ResourceKey<Structure> ATLANTIS_TEMPLE = createKey("atlantis_temple");
    public static ResourceKey<Structure> CLOUD_TEMPLE = createKey("cloud_temple");
    public static ResourceKey<Structure> CRATER_VILLAGE = createKey("crater_village");
    public static ResourceKey<Structure> DESERT_CATACOMBS = createKey("desert_catacombs");
    public static ResourceKey<Structure> DUTCHMAN = createKey("dutchman");
    public static ResourceKey<Structure> GIANT_SHROOM = createKey("giant_shroom");
    public static ResourceKey<Structure> ICE_SETTLEMENT = createKey("ice_settlement");
    public static ResourceKey<Structure> LITTLE_TREEHOUSE = createKey("little_treehouse");
    public static ResourceKey<Structure> MOUNTAIN_CAMP = createKey("mountain_camp");
    public static ResourceKey<Structure> PHANTOM_LIGHTHOUSE = createKey("phantom_lighthouse");
    public static ResourceKey<Structure> PIGLIN_EXPLORER_CAMP = createKey("piglin_explorer_camp");
    public static ResourceKey<Structure> STRIDER_HUT = createKey("strider_hut");
    public static ResourceKey<Structure> PRISMARINE_LAB = createKey("prismarine_lab");

    private static ResourceKey<Structure> createKey(String name) {
        return ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(GrandEscape.MODID, name));
    }

    public static class StructureGens {
        public static void bootstrap(BootstrapContext<Structure> context) {
            HolderGetter<Biome> holdergetter = context.lookup(Registries.BIOME);
            HolderGetter<StructureTemplatePool> holdergetter1 = context.lookup(Registries.TEMPLATE_POOL);
            context.register(
                    ANCHOR_ALTAR,
                    new JigsawStructure(
                            new Structure.StructureSettings.Builder(holdergetter.getOrThrow(BiomeTags.HAS_BASTION_REMNANT))
                                    .spawnOverrides(
                                            Map.of(
                                                    MobCategory.MONSTER,
                                                    new StructureSpawnOverride(
                                                            StructureSpawnOverride.BoundingBoxType.STRUCTURE,
                                                            WeightedRandomList.create(new MobSpawnSettings.SpawnerData(EntityType.PIGLIN, 1, 1, 1))
                                                    )
                                            )
                                    )
                                    .terrainAdapation(TerrainAdjustment.BEARD_THIN)
                                    .build(),
                            holdergetter1.getOrThrow(START),
                            7,
                            ConstantHeight.of(VerticalAnchor.absolute(50)),
                            true
                    )
            );
            context.register(
                    ATLANTIS_TEMPLE,
                    new JigsawStructure(
                            new Structure.StructureSettings.Builder(holdergetter.getOrThrow(BiomeTags.IS_OCEAN))
                                    .spawnOverrides(
                                            Map.of(
                                                    MobCategory.MONSTER,
                                                    new StructureSpawnOverride(
                                                            StructureSpawnOverride.BoundingBoxType.STRUCTURE,
                                                            WeightedRandomList.create(new MobSpawnSettings.SpawnerData(EntityType.DROWNED, 1, 1, 3))
                                                    )
                                            )
                                    )
                                    .terrainAdapation(TerrainAdjustment.BEARD_THIN)
                                    .build(),
                            holdergetter1.getOrThrow(ATLANTIS_TEMPLE_P),
                            7,
                            ConstantHeight.of(VerticalAnchor.absolute(0)),
                            true,
                            Heightmap.Types.OCEAN_FLOOR_WG
                    )
            );
            context.register(
                    CLOUD_TEMPLE,
                    new JigsawStructure(
                            new Structure.StructureSettings.Builder(holdergetter.getOrThrow(BiomeTags.IS_OVERWORLD))
                                    .build(),
                            holdergetter1.getOrThrow(CLOUD_TEMPLE_P),
                            2,
                            ConstantHeight.of(VerticalAnchor.absolute(200)),
                            true
                    )
            );
            context.register(
                    CRATER_VILLAGE,
                    new JigsawStructure(
                            new Structure.StructureSettings.Builder(holdergetter.getOrThrow(AllTags.Biomes.BASALT))
                                    .generationStep(GenerationStep.Decoration.UNDERGROUND_DECORATION)
                                    .terrainAdapation(TerrainAdjustment.BEARD_BOX)
                                    .build(),
                            holdergetter1.getOrThrow(CRATER_VILLAGE_CENTER),
                            Optional.empty(),
                            7,
                            ConstantHeight.of(VerticalAnchor.absolute(33)),
                            false,
                            Optional.empty(),
                            116,
                            List.of(),
                            JigsawStructure.DEFAULT_DIMENSION_PADDING,
                            JigsawStructure.DEFAULT_LIQUID_SETTINGS
                    )
            );

            context.register(
                    DESERT_CATACOMBS,
                    new JigsawStructure(
                            new Structure.StructureSettings.Builder(holdergetter.getOrThrow(BiomeTags.HAS_DESERT_PYRAMID))
                                    .generationStep(GenerationStep.Decoration.UNDERGROUND_STRUCTURES)
                                    .terrainAdapation(TerrainAdjustment.BURY)
                                    .build(),
                            holdergetter1.getOrThrow(DESERT_CATACOMBS_O),
                            Optional.empty(),
                            7,
                            ConstantHeight.of(VerticalAnchor.absolute(-30)),
                            false,
                            Optional.of(Heightmap.Types.WORLD_SURFACE_WG),
                            116,
                            List.of(),
                            JigsawStructure.DEFAULT_DIMENSION_PADDING,
                            LiquidSettings.IGNORE_WATERLOGGING
                    )
            );

            context.register(
                    DUTCHMAN,
                    new JigsawStructure(
                            new Structure.StructureSettings.Builder(holdergetter.getOrThrow(BiomeTags.IS_OCEAN))
                                    .generationStep(GenerationStep.Decoration.SURFACE_STRUCTURES)
                                    .build(),
                            holdergetter1.getOrThrow(DUTCHMAN_FRONT),
                            Optional.empty(),
                            4,
                            ConstantHeight.of(VerticalAnchor.absolute(-5)),
                            false,
                            Optional.of(Heightmap.Types.WORLD_SURFACE_WG),
                            100,
                            List.of(),
                            JigsawStructure.DEFAULT_DIMENSION_PADDING,
                            LiquidSettings.APPLY_WATERLOGGING
                    )
            );

            context.register(
                    GIANT_SHROOM,
                    new JigsawStructure(
                            new Structure.StructureSettings.Builder(holdergetter.getOrThrow(AllTags.Biomes.MUSHROOM))
                                    .terrainAdapation(TerrainAdjustment.BEARD_THIN)
                                    .build(),
                            holdergetter1.getOrThrow(GIANT_SHROOM_START),
                            7,
                            ConstantHeight.of(VerticalAnchor.absolute(0)),
                            true,
                            Heightmap.Types.WORLD_SURFACE_WG
                    )
            );
            context.register(
                    ICE_SETTLEMENT,
                    new JigsawStructure(
                            new Structure.StructureSettings.Builder(holdergetter.getOrThrow(BiomeTags.SPAWNS_SNOW_FOXES))
                                    .terrainAdapation(TerrainAdjustment.BEARD_BOX)
                                    .build(),
                            holdergetter1.getOrThrow(ICE_SETTLEMENT_START),
                            7,
                            ConstantHeight.of(VerticalAnchor.absolute(0)),
                            true,
                            Heightmap.Types.WORLD_SURFACE_WG
                    )
            );

            context.register(
                    LITTLE_TREEHOUSE,
                    new JigsawStructure(
                            new Structure.StructureSettings.Builder(holdergetter.getOrThrow(BiomeTags.IS_TAIGA))
                                    .terrainAdapation(TerrainAdjustment.BEARD_THIN)
                                    .build(),
                            holdergetter1.getOrThrow(LITTLE_TREEHOUSE_START),
                            7,
                            ConstantHeight.of(VerticalAnchor.absolute(0)),
                            true,
                            Heightmap.Types.WORLD_SURFACE_WG
                    )
            );

            context.register(
                    MOUNTAIN_CAMP,
                    new JigsawStructure(
                            new Structure.StructureSettings.Builder(holdergetter.getOrThrow(BiomeTags.IS_MOUNTAIN))
                                    .terrainAdapation(TerrainAdjustment.BEARD_THIN)
                                    .build(),
                            holdergetter1.getOrThrow(MOUNTAIN_CAMP_START),
                            7,
                            ConstantHeight.of(VerticalAnchor.absolute(0)),
                            true,
                            Heightmap.Types.WORLD_SURFACE_WG
                    )
            );

            context.register(
                    PHANTOM_LIGHTHOUSE,
                    new JigsawStructure(
                            new Structure.StructureSettings.Builder(holdergetter.getOrThrow(BiomeTags.IS_BEACH))
                                    .terrainAdapation(TerrainAdjustment.BEARD_THIN)
                                    .build(),
                            holdergetter1.getOrThrow(LIGHTHOUSE_CABIN),
                            7,
                            ConstantHeight.of(VerticalAnchor.absolute(0)),
                            true,
                            Heightmap.Types.WORLD_SURFACE_WG
                    )
            );
            context.register(
                    PIGLIN_EXPLORER_CAMP,
                    new JigsawStructure(
                            new Structure.StructureSettings.Builder(holdergetter.getOrThrow(BiomeTags.IS_NETHER))
                                    .generationStep(GenerationStep.Decoration.TOP_LAYER_MODIFICATION)
                                    .terrainAdapation(TerrainAdjustment.BEARD_THIN)
                                    .build(),
                            holdergetter1.getOrThrow(PIGLIN_EXPLORER_CAMP_START),
                            Optional.empty(),
                            7,
                            ConstantHeight.of(VerticalAnchor.absolute(50)),
                            false,
                            Optional.empty(),
                            50,
                            List.of(),
                            JigsawStructure.DEFAULT_DIMENSION_PADDING,
                            JigsawStructure.DEFAULT_LIQUID_SETTINGS
                    )
            );

            context.register(
                    STRIDER_HUT,
                    new JigsawStructure(
                            new Structure.StructureSettings.Builder(holdergetter.getOrThrow(BiomeTags.IS_NETHER))
                                    .generationStep(GenerationStep.Decoration.TOP_LAYER_MODIFICATION)
                                    .terrainAdapation(TerrainAdjustment.BEARD_BOX)
                                    .build(),
                            holdergetter1.getOrThrow(STRIDER_HUT_CORRAL),
                            Optional.empty(),
                            7,
                            ConstantHeight.of(VerticalAnchor.absolute(33)),
                            false,
                            Optional.empty(),
                            50,
                            List.of(),
                            JigsawStructure.DEFAULT_DIMENSION_PADDING,
                            JigsawStructure.DEFAULT_LIQUID_SETTINGS
                    )
            );

            context.register(
                    PRISMARINE_LAB,
                    new JigsawStructure(
                            new Structure.StructureSettings.Builder(holdergetter.getOrThrow(BiomeTags.IS_DEEP_OCEAN))
                                    .generationStep(GenerationStep.Decoration.SURFACE_STRUCTURES)
                                    .terrainAdapation(TerrainAdjustment.BEARD_BOX)
                                    .build(),
                            holdergetter1.getOrThrow(LAB_CENTER),
                            Optional.empty(),
                            7,
                            ConstantHeight.of(VerticalAnchor.absolute(50)),
                            false,
                            Optional.empty(),
                            116,
                            List.of(),
                            JigsawStructure.DEFAULT_DIMENSION_PADDING,
                            LiquidSettings.IGNORE_WATERLOGGING
                    )
            );
        }

    }
    public static final ResourceKey<StructureTemplatePool> START = createPoolKey("anchor_altar/start");
    public static final ResourceKey<StructureTemplatePool> ATLANTIS_TEMPLE_P = createPoolKey("atlantis_temple");
    public static final ResourceKey<StructureTemplatePool> CLOUD_TEMPLE_P = createPoolKey("cloud_temple");
    public static final ResourceKey<StructureTemplatePool> CRATER_VILLAGE_CENTER = createPoolKey("crater_village/center");
    public static final ResourceKey<StructureTemplatePool> CRATER_VILLAGE_CRATER = createPoolKey("crater_village/crater");
    public static final ResourceKey<StructureTemplatePool> CRATER_VILLAGE_HOUSE = createPoolKey("crater_village/house");
    public static final ResourceKey<StructureTemplatePool> CRATER_VILLAGE_PATH = createPoolKey("crater_village/path");
    public static final ResourceKey<StructureTemplatePool> CRATER_VILLAGE_WALL = createPoolKey("crater_village/wall");
    public static final ResourceKey<StructureTemplatePool> DESERT_CATACOMBS_C = createPoolKey("desert_catacombs/catacomb");
    public static final ResourceKey<StructureTemplatePool> DESERT_CATACOMBS_O = createPoolKey("desert_catacombs/corridor");
    public static final ResourceKey<StructureTemplatePool> DESERT_CATACOMBS_H = createPoolKey("desert_catacombs/hall");
    public static final ResourceKey<StructureTemplatePool> DUTCHMAN_FRONT = createPoolKey("dutchman/dutchman_front");
    public static final ResourceKey<StructureTemplatePool> DUTCHMAN_BACK = createPoolKey("dutchman/ship");
    public static final ResourceKey<StructureTemplatePool> GIANT_SHROOM_START = createPoolKey("giant_shroom");
    public static final ResourceKey<StructureTemplatePool> ICE_SETTLEMENT_START = createPoolKey("ice_settlement");
    public static final ResourceKey<StructureTemplatePool> LITTLE_TREEHOUSE_START = createPoolKey("little_treehouse");
    public static final ResourceKey<StructureTemplatePool> MOUNTAIN_CAMP_START = createPoolKey("mountain_camp");
    public static final ResourceKey<StructureTemplatePool> PIGLIN_EXPLORER_CAMP_START = createPoolKey("piglin_explorer_camp_start");
    public static final ResourceKey<StructureTemplatePool> LIGHTHOUSE_CABIN = createPoolKey("phantom_lighthouse/cabin");
    public static final ResourceKey<StructureTemplatePool> LIGHTHOUSE_TOWER = createPoolKey("phantom_lighthouse/tower");
    public static final ResourceKey<StructureTemplatePool> STRIDER_HUT_CORRAL = createPoolKey("coral");
    public static final ResourceKey<StructureTemplatePool> LAB_CENTER = createPoolKey("prismarine_lab/entrance");
    public static final ResourceKey<StructureTemplatePool> LAB_HALLS = createPoolKey("prismarine_lab/halls");
    public static final ResourceKey<StructureTemplatePool> DEEP_LAB = createPoolKey("prismarine_lab/deep_lab");

    public static ResourceKey<StructureTemplatePool> createPoolKey(String name) {
        return ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(GrandEscape.MODID, name));
    }
    public static class StructurePools {
        public static void bootstrap(BootstrapContext<StructureTemplatePool> context) {
            HolderGetter<StructureProcessorList> holdergetter = context.lookup(Registries.PROCESSOR_LIST);
            Holder<StructureProcessorList> holder = holdergetter.getOrThrow(ProcessorLists.OUTPOST_ROT);
            HolderGetter<StructureTemplatePool> holdergetter1 = context.lookup(Registries.TEMPLATE_POOL);
            Holder<StructureTemplatePool> holder1 = holdergetter1.getOrThrow(Pools.EMPTY);
            context.register(
                    START,
                    new StructureTemplatePool(
                            holder1, ImmutableList.of(Pair.of(StructurePoolElement.single("grandescape:anchor_altar"), 1)), StructureTemplatePool.Projection.RIGID
                    )
            );
            context.register(
                    ATLANTIS_TEMPLE_P,
                    new StructureTemplatePool(
                            holder1, ImmutableList.of(Pair.of(StructurePoolElement.single("grandescape:atlantis_temple"), 1)), StructureTemplatePool.Projection.RIGID
                    )
            );
            context.register(
                    CLOUD_TEMPLE_P,
                    new StructureTemplatePool(
                            holder1, ImmutableList.of(Pair.of(StructurePoolElement.single("grandescape:cloud_temple"), 1)), StructureTemplatePool.Projection.RIGID
                    )
            );

            context.register(
                    DESERT_CATACOMBS_C,
                    new StructureTemplatePool(
                            holder1, ImmutableList.of(Pair.of(StructurePoolElement.single("grandescape:desert_catacombs/bend1"), 5), Pair.of(StructurePoolElement.single("grandescape:desert_catacombs/cross"), 10), Pair.of(StructurePoolElement.single("grandescape:desert_catacombs/hall"), 5), Pair.of(StructurePoolElement.single("grandescape:desert_catacombs/parkour"), 1), Pair.of(StructurePoolElement.single("grandescape:desert_catacombs/temple"), 1), Pair.of(StructurePoolElement.single("grandescape:desert_catacombs/tomb"), 5)), StructureTemplatePool.Projection.RIGID
                    )
            );

            context.register(
                    DESERT_CATACOMBS_O,
                    new StructureTemplatePool(
                            holder1, ImmutableList.of(Pair.of(StructurePoolElement.single("grandescape:desert_catacombs/corridor"), 5)), StructureTemplatePool.Projection.RIGID
                    )
            );

            context.register(
                    DESERT_CATACOMBS_H,
                    new StructureTemplatePool(
                            holder1, ImmutableList.of(Pair.of(StructurePoolElement.single("grandescape:desert_catacombs/bend1"), 5), Pair.of(StructurePoolElement.single("grandescape:desert_catacombs/cross"), 5), Pair.of(StructurePoolElement.single("grandescape:desert_catacombs/hall"), 5), Pair.of(StructurePoolElement.single("grandescape:desert_catacombs/tomb"), 3)), StructureTemplatePool.Projection.RIGID
                    )
            );

            context.register(
                    DUTCHMAN_FRONT,
                    new StructureTemplatePool(
                            holder1, ImmutableList.of(Pair.of(StructurePoolElement.single("grandescape:dutchman/frontship"), 1)), StructureTemplatePool.Projection.RIGID
                    )
            );
            context.register(
                    DUTCHMAN_BACK,
                    new StructureTemplatePool(
                            holder1, ImmutableList.of(Pair.of(StructurePoolElement.single("grandescape:dutchman/backship"), 1)), StructureTemplatePool.Projection.RIGID
                    )
            );
            context.register(
                    GIANT_SHROOM_START,
                    new StructureTemplatePool(
                            holder1, ImmutableList.of(Pair.of(StructurePoolElement.single("grandescape:giant_shroom"), 1)), StructureTemplatePool.Projection.RIGID
                    )
            );
            context.register(
                    ICE_SETTLEMENT_START,
                    new StructureTemplatePool(
                            holder1, ImmutableList.of(Pair.of(StructurePoolElement.single("grandescape:ice_settlement/ice_settlement"), 1)), StructureTemplatePool.Projection.RIGID
                    )
            );
            context.register(
                    LITTLE_TREEHOUSE_START,
                    new StructureTemplatePool(
                            holder1, ImmutableList.of(Pair.of(StructurePoolElement.single("grandescape:little_treehouse"), 1)), StructureTemplatePool.Projection.RIGID
                    )
            );
            context.register(
                    MOUNTAIN_CAMP_START,
                    new StructureTemplatePool(
                            holder1, ImmutableList.of(Pair.of(StructurePoolElement.single("grandescape:mountain_camp"), 1)), StructureTemplatePool.Projection.RIGID
                    )
            );
            context.register(
                    PIGLIN_EXPLORER_CAMP_START,
                    new StructureTemplatePool(
                            holder1, ImmutableList.of(Pair.of(StructurePoolElement.single("grandescape:piglin_explorer_camp/piglin_explorer_camp1"), 1)), StructureTemplatePool.Projection.RIGID
                    )
            );
            context.register(
                    LIGHTHOUSE_CABIN,
                    new StructureTemplatePool(
                            holder1, ImmutableList.of(Pair.of(StructurePoolElement.single("grandescape:phantom_lighthouse/cabin"), 1)), StructureTemplatePool.Projection.RIGID
                    )
            );
            context.register(
                    LIGHTHOUSE_TOWER,
                    new StructureTemplatePool(
                            holder1, ImmutableList.of(Pair.of(StructurePoolElement.single("grandescape:phantom_lighthouse/tower_bottom"), 1),
                            Pair.of(StructurePoolElement.single("grandescape:phantom_lighthouse/tower_top"), 1)), StructureTemplatePool.Projection.RIGID
                    )
            );
            context.register(
                    STRIDER_HUT_CORRAL,
                    new StructureTemplatePool(
                            holder1, ImmutableList.of(Pair.of(StructurePoolElement.single("grandescape:strider_hut/strider_hut"), 1)), StructureTemplatePool.Projection.RIGID
                    )
            );
            craterVillage(context);
            aquaLabs(context);
        }

        private static void craterVillage (BootstrapContext<StructureTemplatePool> context) {
            HolderGetter<StructureTemplatePool> holdergetter1 = context.lookup(Registries.TEMPLATE_POOL);
            Holder<StructureTemplatePool> holder1 = holdergetter1.getOrThrow(Pools.EMPTY);
            context.register(CRATER_VILLAGE_CENTER, new StructureTemplatePool(holder1, ImmutableList.of(Pair.of(StructurePoolElement.single(craterVillagePiece( "crater_center")), 1)), StructureTemplatePool.Projection.RIGID));
            context.register(CRATER_VILLAGE_CRATER, new StructureTemplatePool(holder1, ImmutableList.of(Pair.of(StructurePoolElement.single(craterVillagePiece( "crater_se")), 1), Pair.of(StructurePoolElement.single(craterVillagePiece( "crater_ne")), 1),Pair.of(StructurePoolElement.single(craterVillagePiece( "crater_sw")), 1), Pair.of(StructurePoolElement.single(craterVillagePiece( "crater_nw")), 1)), StructureTemplatePool.Projection.RIGID));
            context.register(CRATER_VILLAGE_PATH, new StructureTemplatePool(holder1, ImmutableList.of(Pair.of(StructurePoolElement.single(craterVillagePiece( "path_east")), 1), Pair.of(StructurePoolElement.single(craterVillagePiece( "path_west")), 1),Pair.of(StructurePoolElement.single(craterVillagePiece( "path_north")), 1), Pair.of(StructurePoolElement.single(craterVillagePiece( "path_south")), 1)), StructureTemplatePool.Projection.RIGID));
            context.register(CRATER_VILLAGE_HOUSE, new StructureTemplatePool(holder1, ImmutableList.of(Pair.of(StructurePoolElement.single(craterVillagePiece( "house1")), 1), Pair.of(StructurePoolElement.single(craterVillagePiece( "house2")), 1),Pair.of(StructurePoolElement.single(craterVillagePiece( "house3")), 1), Pair.of(StructurePoolElement.single(craterVillagePiece( "house4")), 1)), StructureTemplatePool.Projection.RIGID));
            context.register(CRATER_VILLAGE_WALL, new StructureTemplatePool(holder1, ImmutableList.of(Pair.of(StructurePoolElement.single(craterVillagePiece( "wall_east")), 1), Pair.of(StructurePoolElement.single(craterVillagePiece( "wall_west")), 1),Pair.of(StructurePoolElement.single(craterVillagePiece( "wall_north")), 1), Pair.of(StructurePoolElement.single(craterVillagePiece( "wall_south")), 1), Pair.of(StructurePoolElement.single(craterVillagePiece( "wall_east2")), 1), Pair.of(StructurePoolElement.single(craterVillagePiece( "wall_west2")), 1),Pair.of(StructurePoolElement.single(craterVillagePiece( "wall_north2")), 1), Pair.of(StructurePoolElement.single(craterVillagePiece( "wall_south2")), 1)), StructureTemplatePool.Projection.RIGID));

        }

        private static void aquaLabs (BootstrapContext<StructureTemplatePool> context) {
            HolderGetter<StructureTemplatePool> holdergetter1 = context.lookup(Registries.TEMPLATE_POOL);
            Holder<StructureTemplatePool> holder1 = holdergetter1.getOrThrow(Pools.EMPTY);
            context.register(LAB_CENTER, new StructureTemplatePool(holder1, ImmutableList.of(Pair.of(StructurePoolElement.single(prismLabUpper( "lab_entrance")), 1)), StructureTemplatePool.Projection.RIGID));
            context.register(LAB_HALLS, new StructureTemplatePool(holder1, ImmutableList.of(
                    Pair.of(StructurePoolElement.single(prismLabUpper( "lab_room1")), 10),
                    Pair.of(StructurePoolElement.single(prismLabUpper( "dive")), 3),
                    Pair.of(StructurePoolElement.single(prismLabUpper( "upper_hall1")), 3),
                    Pair.of(StructurePoolElement.single(prismLabUpper( "upper_hall2")), 3),
                    Pair.of(StructurePoolElement.single(prismLabUpper( "upper_hall3")), 3),
                    Pair.of(StructurePoolElement.single(prismLabUpper( "upper_hall4")), 3),
                    Pair.of(StructurePoolElement.single(prismLabUpper( "upper_hall5")), 3),
                    Pair.of(StructurePoolElement.single(prismLabUpper( "upper_hall6")), 3),
                    Pair.of(StructurePoolElement.single(prismLabUpper( "upper_hall7")), 3),
                    Pair.of(StructurePoolElement.single(prismLabUpper( "upper_hall8")), 3),

                    Pair.of(StructurePoolElement.single(prismLabLower( "down_tube")), 1),
                    Pair.of(StructurePoolElement.single(prismLabLower( "lower_cross")), 2),
                    Pair.of(StructurePoolElement.single(prismLabLower( "lower_hall1")), 8),
                    Pair.of(StructurePoolElement.single(prismLabLower( "lower_hall2")), 7),
                    Pair.of(StructurePoolElement.single(prismLabLower( "lower_hall_center")), 1),
                    Pair.of(StructurePoolElement.single(prismLabLower( "lower_hall_cross")), 5),
                    Pair.of(StructurePoolElement.single(prismLabLower( "lower_hall_cross2")), 5),
                    Pair.of(StructurePoolElement.single(prismLabLower( "lab_hall")), 5)
            ),
                    StructureTemplatePool.Projection.RIGID));

            context.register(DEEP_LAB, new StructureTemplatePool(holder1, ImmutableList.of(
                    Pair.of(StructurePoolElement.single(prismLabDeep( "deep_lab_bottom")), 5),
                    Pair.of(StructurePoolElement.single(prismLabDeep( "deep_lab_bottom2")), 5),
                    Pair.of(StructurePoolElement.single(prismLabDeep( "deep_lab_top1")), 5)
            ),
                    StructureTemplatePool.Projection.RIGID));
        }

        private static String prismLabUpper (String add) {
            return "grandescape:prismarine_lab/upper/" + add;
        }
        private static String prismLabLower (String add) {
            return "grandescape:prismarine_lab/lower/" + add;
        }
        private static String prismLabDeep (String add) {
            return "grandescape:prismarine_lab/deep_lab/" + add;
        }

        private static String craterVillagePiece (String add) {
            return "grandescape:crater_village/" + add;
        }
    }

    public static class StructureSets {
        static ResourceKey<StructureSet> ANCHOR_ALTARS = register("anchor_altars");
        static ResourceKey<StructureSet> UNDERSEA_TEMPLES = register("undersea_temples");
        static ResourceKey<StructureSet> CLOUD_ISLANDS = register("cloud_islands");
        static ResourceKey<StructureSet> NETHER_VILLAGES = register("nether_villages");
        static ResourceKey<StructureSet> CATACOMBS = register("catacombs");
        static ResourceKey<StructureSet> SHIPS = register("ships");
        static ResourceKey<StructureSet> GIANT_MUSHROOMS = register("giant_mushrooms");
        static ResourceKey<StructureSet> SETTLEMENTS = register("settlements");
        static ResourceKey<StructureSet> TREEHOUSES = register("treehouses");
        static ResourceKey<StructureSet> MOUNTAIN_CAMPS = register("mountain_camps");
        static ResourceKey<StructureSet> EXPLORER_CAMPS = register("explorer_camps");
        static ResourceKey<StructureSet> LIGHTHOUSES = register("lighthouses");
        static ResourceKey<StructureSet> STRIDER_HUTS = register("strider_huts");
        static ResourceKey<StructureSet> AQUA_LABS = register("aqua_labs");

        private static ResourceKey<StructureSet> register(String name) {
            return ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(GrandEscape.MODID, name));
        }

        public static void bootstrap(BootstrapContext<StructureSet> context) {
            HolderGetter<Structure> holdergetter = context.lookup(Registries.STRUCTURE);
            HolderGetter<Biome> holdergetter1 = context.lookup(Registries.BIOME);
            Holder.Reference<StructureSet> reference = context.register(
                    ANCHOR_ALTARS,
                    new StructureSet(
                            List.of(
                                    StructureSet.entry(holdergetter.getOrThrow(ANCHOR_ALTAR))
                            ),
                            new RandomSpreadStructurePlacement(34, 8, RandomSpreadType.LINEAR, 83937543)
                    )
            );
            Holder.Reference<StructureSet> reference2 = context.register(
                    UNDERSEA_TEMPLES,
                    new StructureSet(
                            List.of(
                                    StructureSet.entry(holdergetter.getOrThrow(ATLANTIS_TEMPLE))
                            ),
                            new RandomSpreadStructurePlacement(34, 8, RandomSpreadType.LINEAR, 954643279)
                    )
            );
            Holder.Reference<StructureSet> reference3 = context.register(
                    CLOUD_ISLANDS,
                    new StructureSet(
                            List.of(
                                    StructureSet.entry(holdergetter.getOrThrow(CLOUD_TEMPLE))
                            ),
                            new RandomSpreadStructurePlacement(40, 8, RandomSpreadType.LINEAR, 748493372)
                    )
            );
            Holder.Reference<StructureSet> reference4 = context.register(
                    NETHER_VILLAGES,
                    new StructureSet(
                            List.of(
                                    StructureSet.entry(holdergetter.getOrThrow(CRATER_VILLAGE))
                            ),
                            new RandomSpreadStructurePlacement(32, 8, RandomSpreadType.LINEAR, 436632432)
                    )
            );

            Holder.Reference<StructureSet> reference5 = context.register(
                    CATACOMBS,
                    new StructureSet(
                            List.of(
                                    StructureSet.entry(holdergetter.getOrThrow(DESERT_CATACOMBS))
                            ),
                            new RandomSpreadStructurePlacement(32, 8, RandomSpreadType.LINEAR, 896749327)
                    )
            );

            Holder.Reference<StructureSet> reference6 = context.register(
                    SHIPS,
                    new StructureSet(
                            List.of(
                                    StructureSet.entry(holdergetter.getOrThrow(DUTCHMAN))
                            ),
                            new RandomSpreadStructurePlacement(32, 8, RandomSpreadType.LINEAR, 987786533)
                    )
            );
            Holder.Reference<StructureSet> reference7 = context.register(
                    GIANT_MUSHROOMS,
                    new StructureSet(
                            List.of(
                                    StructureSet.entry(holdergetter.getOrThrow(GIANT_SHROOM))
                            ),
                            new RandomSpreadStructurePlacement(32, 8, RandomSpreadType.LINEAR, 234455673)
                    )
            );
            Holder.Reference<StructureSet> reference8 = context.register(
                    SETTLEMENTS,
                    new StructureSet(
                            List.of(
                                    StructureSet.entry(holdergetter.getOrThrow(ICE_SETTLEMENT))
                            ),
                            new RandomSpreadStructurePlacement(32, 8, RandomSpreadType.LINEAR, 563735265)
                    )
            );

            Holder.Reference<StructureSet> reference9 = context.register(
                    TREEHOUSES,
                    new StructureSet(
                            List.of(
                                    StructureSet.entry(holdergetter.getOrThrow(LITTLE_TREEHOUSE))
                            ),
                            new RandomSpreadStructurePlacement(32, 8, RandomSpreadType.LINEAR, 332342228)
                    )
            );

            Holder.Reference<StructureSet> reference10 = context.register(
                    MOUNTAIN_CAMPS,
                    new StructureSet(
                            List.of(
                                    StructureSet.entry(holdergetter.getOrThrow(MOUNTAIN_CAMP))
                            ),
                            new RandomSpreadStructurePlacement(32, 8, RandomSpreadType.LINEAR, 756392261)
                    )
            );
            Holder.Reference<StructureSet> reference11 = context.register(
                    EXPLORER_CAMPS,
                    new StructureSet(
                            List.of(
                                    StructureSet.entry(holdergetter.getOrThrow(PIGLIN_EXPLORER_CAMP))
                            ),
                            new RandomSpreadStructurePlacement(32, 8, RandomSpreadType.LINEAR, 999994333)
                    )
            );
            Holder.Reference<StructureSet> reference12 = context.register(
                    LIGHTHOUSES,
                    new StructureSet(
                            List.of(
                                    StructureSet.entry(holdergetter.getOrThrow(PHANTOM_LIGHTHOUSE))
                            ),
                            new RandomSpreadStructurePlacement(32, 8, RandomSpreadType.LINEAR, 758438201)
                    )
            );
            Holder.Reference<StructureSet> reference13 = context.register(
                    STRIDER_HUTS,
                    new StructureSet(
                            List.of(
                                    StructureSet.entry(holdergetter.getOrThrow(STRIDER_HUT))
                            ),
                            new RandomSpreadStructurePlacement(32, 8, RandomSpreadType.LINEAR, 934874272)
                    )
            );
            Holder.Reference<StructureSet> reference14 = context.register(
                    AQUA_LABS,
                    new StructureSet(
                            List.of(
                                    StructureSet.entry(holdergetter.getOrThrow(PRISMARINE_LAB))
                            ),
                            new RandomSpreadStructurePlacement(32, 8, RandomSpreadType.LINEAR, 994869234)
                    )
            );
        }
    }
}
