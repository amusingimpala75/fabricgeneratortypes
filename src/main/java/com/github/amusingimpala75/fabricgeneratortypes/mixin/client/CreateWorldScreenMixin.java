package com.github.amusingimpala75.fabricgeneratortypes.mixin.client;

import com.github.amusingimpala75.fabricgeneratortypes.impl.client.FabricGeneratorTypesClientImpl;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.world.GeneratorType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.Optional;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
@Mixin(CreateWorldScreen.class)
public class CreateWorldScreenMixin {
    @ModifyArg(method = "create", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/world/MoreOptionsDialog;<init>(Lnet/minecraft/util/registry/DynamicRegistryManager$Impl;Lnet/minecraft/world/gen/GeneratorOptions;Ljava/util/Optional;Ljava/util/OptionalLong;)V"), index = 2)
    private static Optional<GeneratorType> modifyArg$moreOptionsDialogConstructor(Optional<GeneratorType> generatorType) {
        if (FabricGeneratorTypesClientImpl.hasNonDefaultDefaultType()) {
            return FabricGeneratorTypesClientImpl.getDefaultType();
        } else {
            return generatorType;
        }
    }
}
