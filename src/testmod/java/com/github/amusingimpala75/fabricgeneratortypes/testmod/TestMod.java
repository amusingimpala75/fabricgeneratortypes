package com.github.amusingimpala75.fabricgeneratortypes.testmod;

import com.github.amusingimpala75.fabricgeneratortypes.api.ChunkGeneratorFactory;
import com.github.amusingimpala75.fabricgeneratortypes.api.FabricGeneratorTypes;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.chunk.FlatChunkGenerator;
import net.minecraft.world.gen.chunk.FlatChunkGeneratorConfig;
import net.minecraft.world.gen.chunk.StructuresConfig;

import java.util.Collections;
import java.util.Optional;

public class TestMod implements ModInitializer {

    private static final String MODID = "fabric-generator-types-testmod";

    public static Identifier id(String path) { return new Identifier(MODID, path); }

    private static final ChunkGeneratorFactory FLAT_CHUNK_GENERATOR_FACTORY = (manager, seed) -> {
        FlatChunkGeneratorConfig config = new FlatChunkGeneratorConfig(new StructuresConfig(Optional.empty(), Collections.emptyMap()), manager.get(Registry.BIOME_KEY));
        config.updateLayerBlocks();
        return new FlatChunkGenerator(config);
    };

    @Override
    public void onInitialize() {
        FabricGeneratorTypes.registerGeneratorType(
                id("non_default/first"),
                FLAT_CHUNK_GENERATOR_FACTORY
        );
        FabricGeneratorTypes.registerGeneratorType(
                id("non_default/second"),
                FLAT_CHUNK_GENERATOR_FACTORY
        );
        FabricGeneratorTypes.registerGeneratorTypeAsDefault(
                id("default/first"),
                FLAT_CHUNK_GENERATOR_FACTORY
        );
        FabricGeneratorTypes.registerGeneratorTypeAsDefault(
                id("default/second"),
                FLAT_CHUNK_GENERATOR_FACTORY
        );
    }
}