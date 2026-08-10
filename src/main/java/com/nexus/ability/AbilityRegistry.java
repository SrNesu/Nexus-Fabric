package com.nexus.ability;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import com.nexus.NexusMod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages ability registration and retrieval.
 */
public class AbilityRegistry {
	private static final Logger LOGGER = LoggerFactory.getLogger("nexus-abilities");
	private static final Map<String, Ability> ABILITIES = new HashMap<>();
	
	public static void register() {
		LOGGER.info("[Nexus] Registering abilities...");
		
		// Register all abilities here
		registerAbility(new NexusBlastAbility());
		registerAbility(new NexusPulseAbility());
		registerAbility(new NexusStepAbility());
		registerAbility(new NexusBarrierAbility());
		registerAbility(new NexusSenseAbility());
		registerAbility(new NexusGravityAbility());
		registerAbility(new NexusDominionAbility());
		registerAbility(new NexusAscensionAbility());
		
		LOGGER.info("[Nexus] {} abilities registered", ABILITIES.size());
	}
	
	/**
	 * Register an ability.
	 */
	public static void registerAbility(Ability ability) {
		ABILITIES.put(ability.getAbilityId(), ability);
		LOGGER.debug("[Nexus] Registered ability: {}", ability.getAbilityId());
	}
	
	/**
	 * Get an ability by ID.
	 */
	public static Ability getAbility(String abilityId) {
		return ABILITIES.get(abilityId);
	}
	
	/**
	 * Get all registered abilities.
	 */
	public static Map<String, Ability> getAllAbilities() {
		return new HashMap<>(ABILITIES);
	}
}
