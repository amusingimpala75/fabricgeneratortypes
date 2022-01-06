# Custom GeneratorType API

Library mod that allows registration of custom GeneratorTypes.

### Usage:

Add Jitpack to your repositories:
```groovy
repositories {
    maven {
        name = 'Jitpack'
        url = 'https://jitpack.io'
    }
}
```
Add this as dependency:
```groovy
dependencies {
    modImplementation 'com.github.amusingimpala75:fabricgeneratortypes:${project.fabric_generator_types_version}'
    //Optional
    include 'com.github.amusingimpala75:fabricgeneratortypes:${project.fabric_generator_types_version}'
}
```

The latest version is: (todo), add it to your gradle.properties

To use the API, call either:
- FabricGeneratorTypes#registerGeneratorType
- FabricGeneratorTypes#registerGeneratorTypeAsDefault

Both of which are in the [FabricGeneratorTypes](https://github.com/amusingimpala75/fabricgeneratortypes/blob/master/src/main/java/com/github/amusingimpala75/fabricgeneratortypes/api/FabricGeneratorTypes.java) class. 

They take in:
- an Identifier for the translation key, which gets converted into {namespace}.{path}, where the path has slashes replaced with underscores
- a functional interface which, when provided with a DynamicRegistryManager and a seed, returns a ChunkGenerator

If a GeneratorType is registered as default, then it will be used if no settings are changed in the CreateWorldScreen, and a fresh server will start with that world type

Note that if multiple mods register **default** GeneratorTypes, the last one registered will be the one used.

See the testmod for examples