package com.nexus.energy;

import com.nexus.config.NexusConfig;
import com.nexus.player.NexusPlayerData;

/**
 * Manages Abyssal Energy and corruption.
 */
public class AbyssalEnergyManager {
	
	/**
	 * Update Abyssal energy regeneration.
	 * Abyssal energy regenerates slower than Nexus and increases corruption.
	 */
	public static void updateAbyssalRegeneration(NexusPlayerData data, float deltaTime) {
		float baseRegen = NexusConfig.AbyssalEnergy.BASE_REGENERATION 
			+ (data.getNexusLevel() * NexusConfig.AbyssalEnergy.MAX_LEVEL_BONUS / 100.0f);
		
		float finalRegen = baseRegen * deltaTime;
		data.addAbyssalEnergy((int) finalRegen);
	}
	
	/**
	 * Update corruption decay.
	 * Corruption decreases over time when not using Abyssal abilities.
	 */
	public static void updateCorruptionDecay(NexusPlayerData data, float deltaTime) {
		float decayAmount = NexusConfig.Corruption.CORRUPTION_DECAY_RATE * deltaTime;
		data.reduceCorruption(decayAmount);
	}
	
	/**
	 * Add corruption when using Abyssal abilities.
	 */
	public static void addCorruptionFromAbility(NexusPlayerData data) {
		data.addCorruption(NexusConfig.Corruption.CORRUPTION_PER_ABILITY_USE);
	}
	
	/**
	 * Get corruption state.
	 */
	public static CorruptionState getCorruptionState(float corruptionPercentage) {
		if (corruptionPercentage >= NexusConfig.Corruption.SEVERELY_CORRUPTED_THRESHOLD) {
			return CorruptionState.SEVERELY_CORRUPTED;
		} else if (corruptionPercentage >= NexusConfig.Corruption.CORRUPTED_THRESHOLD) {
			return CorruptionState.CORRUPTED;
		}
		return CorruptionState.NORMAL;
	}
	
	public enum CorruptionState {
		NORMAL("Normal"),
		CORRUPTED("Corrupted"),
		SEVERELY_CORRUPTED("Severely Corrupted");
		
		private final String displayName;
		
		CorruptionState(String displayName) {
			this.displayName = displayName;
		}
		
		public String getDisplayName() {
			return displayName;
		}
	}
}
