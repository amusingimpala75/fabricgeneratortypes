package com.github.amusingimpala75.fabricgeneratortypes.impl;

import com.github.amusingimpala75.fabricgeneratortypes.api.ChunkGeneratorFactory;
import com.github.amusingimpala75.fabricgeneratortypes.impl.client.FabricGeneratorTypesClientImpl;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.ApiStatus;

import java.util.Optional;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
@ApiStatus.Internal
public final class FabricGeneratorTypesImpl extends NonInstantiable {

    private static final Logger LOGGER = LogManager.getLogger();

    private static Optional<ChunkGeneratorFactory> defaultChunkGenerator = Optional.empty();

    public static Optional<ChunkGeneratorFactory> getDefaultChunkGenerator() {
        return defaultChunkGenerator;
    }

    public static void setDefaultChunkGenerator(Optional<ChunkGeneratorFactory> defaultChunkGenerator) {
        FabricGeneratorTypesImpl.defaultChunkGenerator = defaultChunkGenerator;
    }

    public static boolean hasNonDefaultDefaultChunkGenerator() {
        return defaultChunkGenerator.isPresent();
    }

    public static void registerGeneratorTypeAsDefault(Identifier translationKey, ChunkGeneratorFactory chunkGeneratorFactory) {
        if (defaultChunkGenerator.isEmpty()) {
            LOGGER.info("Registering default ChunkGenerator type {}", translationKey);
        } else {
            LOGGER.warn("Overwriting default ChunkGenerator with {}", translationKey);
        }

        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            FabricGeneratorTypesClientImpl.registerGeneratorTypeAsDefault(translationKey, chunkGeneratorFactory);
        }
        setDefaultChunkGenerator(Optional.of(chunkGeneratorFactory));
    }
}
