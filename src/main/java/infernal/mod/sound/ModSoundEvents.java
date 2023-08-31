package infernal.mod.sound;

import infernal.mod.InfernalMod;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSoundEvents {
    // register handbell sound
    public static final SoundEvent HANDBELL_RINGING = registerSoundEvent("infernal-mod:handbell_ringing");

    private static SoundEvent registerSoundEvent(String identifier) {
        return SoundEvent.of(new Identifier("infernal-mod:handbell_ringing") );
    }

    public static void registerModSoundEvents() {
        InfernalMod.LOGGER.info("Registering ModSoundEvents");
    }
}
