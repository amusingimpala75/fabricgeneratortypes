package com.github.amusingimpala75.fabricgeneratortypes.api;

import com.github.amusingimpala75.fabricgeneratortypes.impl.FabricGeneratorTypesImpl;
import com.github.amusingimpala75.fabricgeneratortypes.impl.client.FabricGeneratorTypesClientImpl;
import net.minecraft.client.world.GeneratorType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.world.gen.chunk.ChunkGenerator;

/**
 * Class for registering {@link GeneratorType}s. If registered as default, then it will appear as the first option
 * in the {@link CreateWorldScreen}
 * */
public class FabricGeneratorTypes {
    /**
     * Registers a {@link GeneratorType}
     *
     * @param translationKey the {@link Identifier} used as the translation key for display in the {@link CreateWorldScreen}
     *                       it goes "[namespace].[path]", where path has the slashes replaced this underscores
     * @param chunkGeneratorFactory the implementation for getting the {@link ChunkGenerator} from the {@link DynamicRegistryManager} and the seed
     * */
    public static void registerGeneratorType(Identifier translationKey, ChunkGeneratorFactory chunkGeneratorFactory) {
        FabricGeneratorTypesClientImpl.registerGeneratorType(translationKey, chunkGeneratorFactory);
    }

    /**
     * Registers a {@link GeneratorType} as the default when creating the {@link CreateWorldScreen}
     * If multiple are registered as default, the last one invoked will be the successful one.
     * That said, the other ones will appear at the beginning of the list, before the vanilla {@link GeneratorType}s
     *
     * @param translationKey the {@link Identifier} used as the translation key for display in the {@link CreateWorldScreen}
     *                       it goes "[namespace].[path]", where path has the slashes replaced this underscores
     * @param chunkGeneratorFactory the implementation for getting the {@link ChunkGenerator} from the {@link DynamicRegistryManager} and the seed
     * */
    public static void registerGeneratorTypeAsDefault(Identifier translationKey, ChunkGeneratorFactory chunkGeneratorFactory) {
        FabricGeneratorTypesImpl.registerGeneratorTypeAsDefault(translationKey, chunkGeneratorFactory);
    }
}
