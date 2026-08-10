package com.nexus.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import com.nexus.NexusMod;

/**
 * Registry for all Nexus sound events.
 */
public class NexusSounds {
	
	// Sound Events
	public static final SoundEvent NEXUS_ACTIVATION = registerSoundEvent("nexus_activation");
	public static final SoundEvent NEXUS_BLAST_CHARGE = registerSoundEvent("nexus_blast_charge");
	public static final SoundEvent NEXUS_BLAST_RELEASE = registerSoundEvent("nexus_blast_release");
	public static final SoundEvent NEXUS_PULSE = registerSoundEvent("nexus_pulse");
	public static final SoundEvent NEXUS_STEP = registerSoundEvent("nexus_step");
	public static final SoundEvent NEXUS_BARRIER = registerSoundEvent("nexus_barrier");
	public static final SoundEvent NEXUS_SENSE = registerSoundEvent("nexus_sense");
	public static final SoundEvent NEXUS_GRAVITY = registerSoundEvent("nexus_gravity");
	public static final SoundEvent NEXUS_DOMINION = registerSoundEvent("nexus_dominion");
	public static final SoundEvent NEXUS_ASCENSION = registerSoundEvent("nexus_ascension");
	public static final SoundEvent NEXUS_OVERLOAD = registerSoundEvent("nexus_overload");
	
	public static void register() {
		// Sounds are registered through constants
	}
	
	private static SoundEvent registerSoundEvent(String name) {
		Identifier id = new Identifier(NexusMod.MOD_ID, name);
		return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
	}
}
