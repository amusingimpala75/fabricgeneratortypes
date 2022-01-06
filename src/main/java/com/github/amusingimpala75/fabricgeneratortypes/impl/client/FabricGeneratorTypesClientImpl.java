package com.github.amusingimpala75.fabricgeneratortypes.impl.client;

import com.github.amusingimpala75.fabricgeneratortypes.api.ChunkGeneratorFactory;
import com.github.amusingimpala75.fabricgeneratortypes.impl.NonInstantiable;
import com.github.amusingimpala75.fabricgeneratortypes.mixin.client.accessor.GeneratorTypeAccessor;
import net.minecraft.client.world.GeneratorType;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.ApiStatus;

import java.util.Optional;
import java.util.function.BiFunction;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
@ApiStatus.Internal
public final class FabricGeneratorTypesClientImpl extends NonInstantiable {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final BiFunction<Identifier, ChunkGeneratorFactory, GeneratorType> GENERATORTYPE_FACOTRY = (translationKey, chunkGeneratorFactory) -> new GeneratorType(toTranslationString(translationKey)) {
        @Override
        protected ChunkGenerator getChunkGenerator(DynamicRegistryManager registryManager, long seed) {
            return chunkGeneratorFactory.apply(registryManager, seed);
        }
    };

    public static void registerGeneratorType(Identifier translationKey, ChunkGeneratorFactory chunkGeneratorFactory) {
        GeneratorTypeAccessor.accessor$getValues().add(GENERATORTYPE_FACOTRY.apply(translationKey, chunkGeneratorFactory));
    }

    private static Optional<GeneratorType> defaultType = Optional.empty();

    public static Optional<GeneratorType> getDefaultType() {
        return defaultType;
    }

    public static void setDefaultType(Optional<GeneratorType> defaultType) {
        FabricGeneratorTypesClientImpl.defaultType = defaultType;
    }

    public static boolean hasNonDefaultDefaultType() {
        return defaultType.isPresent();
    }

    public static void registerGeneratorTypeAsDefault(Identifier translationKey, ChunkGeneratorFactory chunkGeneratorFactory) {
        GeneratorType type = GENERATORTYPE_FACOTRY.apply(translationKey, chunkGeneratorFactory);
        GeneratorTypeAccessor.accessor$getValues().add(0, type);
        setDefaultType(Optional.of(type));
    }

    /**
     * Turns an {@link Identifier} into a {@link String} with the format:
     *      "[namespace].[path]", where the path has slashes replaced with underscores
     * */
    private static String toTranslationString(Identifier id) {
        return id.getNamespace() + "." + id.getPath().replace('/', '_');
    }
}
