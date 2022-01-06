package com.github.amusingimpala75.fabricgeneratortypes.mixin;

import com.github.amusingimpala75.fabricgeneratortypes.impl.FabricGeneratorTypesImpl;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.gen.GeneratorOptions;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(GeneratorOptions.class)
public class GeneratorOptionsMixin {

    @Unique
    private static DynamicRegistryManager fabgentypes$manager = null;
    @Unique
    private static long fabgentypes$seed = 0L;
    @Unique
    private static int fabgentypes$demoSeed = 0;

    @Inject(method = "getDefaultOptions", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/GeneratorOptions;<init>(JZZLnet/minecraft/util/registry/SimpleRegistry;)V", shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void inject$getDefaultOptions(DynamicRegistryManager registryManager, CallbackInfoReturnable<GeneratorOptions> cir, long l) {
        fabgentypes$manager = registryManager;
        fabgentypes$seed = l;
    }

    @ModifyArg(method = "getDefaultOptions", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/GeneratorOptions;getRegistryWithReplacedOverworldGenerator(Lnet/minecraft/util/registry/Registry;Lnet/minecraft/util/registry/SimpleRegistry;Lnet/minecraft/world/gen/chunk/ChunkGenerator;)Lnet/minecraft/util/registry/SimpleRegistry;"), index = 2)
    private static ChunkGenerator modifyArg$createOverworldGenerator(ChunkGenerator overworldGenerator) {
        assert fabgentypes$manager != null;
        if (FabricGeneratorTypesImpl.hasNonDefaultDefaultChunkGenerator()) {
            return FabricGeneratorTypesImpl.getDefaultChunkGenerator().get().apply(fabgentypes$getManagerAndSetNull(), fabgentypes$seed);
        } else {
            return overworldGenerator;
        }
    }

    @Inject(method = "createDemo", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/GeneratorOptions;<init>(JZZLnet/minecraft/util/registry/SimpleRegistry;)V", shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void inject$createDemo(DynamicRegistryManager registryManager, CallbackInfoReturnable<GeneratorOptions> cir, int i) {
        fabgentypes$manager = registryManager;
        fabgentypes$demoSeed = i;
    }

    @ModifyArg(method = "createDemo", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/GeneratorOptions;getRegistryWithReplacedOverworldGenerator(Lnet/minecraft/util/registry/Registry;Lnet/minecraft/util/registry/SimpleRegistry;Lnet/minecraft/world/gen/chunk/ChunkGenerator;)Lnet/minecraft/util/registry/SimpleRegistry;"), index = 2)
    private static ChunkGenerator modifyArg$createOverworldGeneratorForDemo(ChunkGenerator overworldGenerator) {
        assert fabgentypes$manager != null;
        if (FabricGeneratorTypesImpl.hasNonDefaultDefaultChunkGenerator()) {
            return FabricGeneratorTypesImpl.getDefaultChunkGenerator().get().apply(fabgentypes$getManagerAndSetNull(), (long) fabgentypes$demoSeed);
        } else {
            return overworldGenerator;
        }
    }

    @Unique
    private static DynamicRegistryManager fabgentypes$getManagerAndSetNull() {
        DynamicRegistryManager manager = fabgentypes$manager;
        fabgentypes$manager = null;
        return manager;
    }
}
