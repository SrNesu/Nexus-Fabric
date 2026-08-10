package com.nexus.energy;

import com.nexus.config.NexusConfig;
import com.nexus.player.NexusPlayerData;

/**
 * Manages Nexus energy regeneration and consumption.
 */
public class NexusEnergyManager {
	
	/**
	 * Update energy regeneration (called every tick).
	 * Regeneration is affected by overload level.
	 */
	public static void updateEnergyRegeneration(NexusPlayerData data, float deltaTime) {
		if (!data.isNexusUnlocked()) {
			return;
		}
		
		// Calculate regeneration based on level and overload
		float baseRegen = NexusConfig.NexusEnergy.BASE_REGENERATION 
			+ (data.getNexusLevel() * NexusConfig.NexusEnergy.REGENERATION_PER_LEVEL);
		
		// Apply overload penalty
		float overloadPercentage = data.getOverloadPercentage();
		float regenerationModifier = calculateRegenerationModifier(overloadPercentage);
		
		float finalRegen = baseRegen * regenerationModifier * deltaTime;
		
		data.addNexusEnergy((int) finalRegen);
	}
	
	/**
	 * Calculate regeneration modifier based on overload.
	 */
	private static float calculateRegenerationModifier(float overloadPercentage) {
		if (overloadPercentage < NexusConfig.Overload.NORMAL_THRESHOLD) {
			return 1.0f;
		} else if (overloadPercentage < NexusConfig.Overload.UNSTABLE_THRESHOLD) {
			return NexusConfig.Overload.REGENERATION_PENALTY_UNSTABLE;
		} else if (overloadPercentage < NexusConfig.Overload.CRITICAL_THRESHOLD) {
			return NexusConfig.Overload.REGENERATION_PENALTY_CRITICAL;
		} else {
			return NexusConfig.Overload.REGENERATION_PENALTY_OVERLOAD;
		}
	}
	
	/**
	 * Get the current overload state.
	 */
	public static OverloadState getOverloadState(float overloadPercentage) {
		if (overloadPercentage >= NexusConfig.Overload.OVERLOAD_MAX) {
			return OverloadState.COLLAPSE;
		} else if (overloadPercentage >= NexusConfig.Overload.CRITICAL_THRESHOLD) {
			return OverloadState.CRITICAL;
		} else if (overloadPercentage >= NexusConfig.Overload.UNSTABLE_THRESHOLD) {
			return OverloadState.OVERLOAD;
		} else if (overloadPercentage >= NexusConfig.Overload.NORMAL_THRESHOLD) {
			return OverloadState.UNSTABLE;
		}
		return OverloadState.NORMAL;
	}
	
	public enum OverloadState {
		NORMAL("Normal"),
		UNSTABLE("Unstable"),
		OVERLOAD("Overload"),
		CRITICAL("Critical"),
		COLLAPSE("Collapse");
		
		private final String displayName;
		
		OverloadState(String displayName) {
			this.displayName = displayName;
		}
		
		public String getDisplayName() {
			return displayName;
		}
	}
}
