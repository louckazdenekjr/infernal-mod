package infernal.mod.model;

import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;

import static infernal.mod.InfernalMod.LOGGER;

public class Models {
    // Register the custom block model
    public static ModelIdentifier TransporterModelId = registerModel(new Identifier("infernal-mod:block/transporter"));


    private static ModelIdentifier registerModel(Identifier pathId) {
        ModelIdentifier modelId = new ModelIdentifier(
             pathId,
            "inventory"
        );

        ModelLoadingRegistry.INSTANCE.registerAppender((resourceManager, consumer) -> {
            consumer.accept(modelId);
        });

        //Model model = (Model) modelLoader.getOrLoadModel(modelId);

        return modelId;
    }

    public static void registerModModels() {
        LOGGER.info("Registering ModModels");
    }
}
