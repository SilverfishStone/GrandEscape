package grandescape.world_items.worldgen.structures;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import grandescape.GrandEscape;
import grandescape.function.util.AllTags;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
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
                            ConstantHeight.of(VerticalAnchor.absolute(0)),
                            true,
                            Heightmap.Types.WORLD_SURFACE_WG
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
                                    .terrainAdapation(TerrainAdjustment.BEARD_THIN)
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
                                    .generationStep(GenerationStep.Decoration.TOP_LAYER_MODIFICATION)
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
                                    .terrainAdapation(TerrainAdjustment.BEARD_THIN)
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
            craterVillage(context);
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
        }
    }
}
