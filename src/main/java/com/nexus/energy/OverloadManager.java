package com.nexus.energy;

import com.nexus.config.NexusConfig;
import com.nexus.player.NexusPlayerData;

/**
 * Manages Nexus overload mechanics.
 * Tracks and applies overload effects.
 */
public class OverloadManager {
	
	/**
	 * Update overload decay (called every tick).
	 * Overload naturally decays over time when not using abilities.
	 */
	public static void updateOverloadDecay(NexusPlayerData data, float deltaTime) {
		if (!data.isNexusUnlocked()) {
			return;
		}
		
		float decayAmount = NexusConfig.Overload.OVERLOAD_DECAY_RATE * deltaTime;
		data.reduceOverload(decayAmount);
	}
	
	/**
	 * Add overload when using an ability.
	 */
	public static void addOverloadFromAbility(NexusPlayerData data) {
		data.addOverload(NexusConfig.Overload.OVERLOAD_PER_ABILITY_USE);
	}
	
	/**
	 * Add overload during Ascension ability (per tick).
	 */
	public static void addOverloadFromAscension(NexusPlayerData data) {
		data.addOverload(NexusConfig.Overload.OVERLOAD_PER_ASCENSION_TICK);
	}
	
	/**
	 * Check if overload has reached collapse state.
	 */
	public static boolean hasCollapsed(NexusPlayerData data) {
		return data.getOverloadPercentage() >= NexusConfig.Overload.OVERLOAD_MAX;
	}
	
	/**
	 * Trigger collapse effect (player takes damage and loses control).
	 */
	public static void triggerCollapse(NexusPlayerData data) {
		// Reset overload
		data.setOverloadPercentage(0.0f);
		
		// Damage is applied externally via event handler
	}
	
	/**
	 * Get visual effect intensity based on overload.
	 */
	public static float getEffectIntensity(float overloadPercentage) {
		return Math.min(1.0f, overloadPercentage / NexusConfig.Overload.OVERLOAD_MAX);
	}
}
