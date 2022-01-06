package com.github.amusingimpala75.fabricgeneratortypes.api;

import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import java.util.function.BiFunction;

/**
 * Takes in a {@link DynamicRegistryManager} and a seed, and returns a {@link ChunkGenerator}
 * */
@FunctionalInterface
public interface ChunkGeneratorFactory extends BiFunction<DynamicRegistryManager, Long, ChunkGenerator> {
}
