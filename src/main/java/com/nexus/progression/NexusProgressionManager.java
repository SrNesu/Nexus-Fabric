package com.nexus.progression;

import com.nexus.config.NexusConfig;
import com.nexus.player.NexusPlayerData;
import net.minecraft.text.Text;
import net.minecraft.entity.player.ServerPlayerEntity;

/**
 * Manages Nexus XP and leveling progression.
 */
public class NexusProgressionManager {
	
	/**
	 * Add XP to a player and handle level ups.
	 */
	public static void addNexusXp(ServerPlayerEntity player, NexusPlayerData data, int xpAmount) {
		if (!data.isNexusUnlocked()) {
			return;
		}
		
		data.addNexusXp(xpAmount);
		
		// Check for level up
		while (hasEnoughXpForLevelUp(data)) {
			levelUp(player, data);
		}
	}
	
	/**
	 * Check if player has enough XP to level up.
	 */
	private static boolean hasEnoughXpForLevelUp(NexusPlayerData data) {
		int currentXp = data.getNexusXp();
		int requiredXp = getXpRequiredForLevel(data.getNexusLevel() + 1);
		return currentXp >= requiredXp;
	}
	
	/**
	 * Get the total XP required to reach a specific level.
	 */
	private static int getXpRequiredForLevel(int targetLevel) {
		if (targetLevel <= 1) {
			return 0;
		}
		
		int totalXp = 0;
		for (int level = 1; level < targetLevel; level++) {
			totalXp += (int) (NexusConfig.NexusLevel.BASE_XP_FOR_LEVEL_UP 
				* Math.pow(NexusConfig.NexusLevel.XP_MULTIPLIER_PER_LEVEL, level - 1));
		}
		return totalXp;
	}
	
	/**
	 * Get the XP needed from current level to next level.
	 */
	public static int getXpNeededForNextLevel(NexusPlayerData data) {
		int nextLevelXp = getXpRequiredForLevel(data.getNexusLevel() + 1);
		int currentXp = data.getNexusXp();
		return Math.max(0, nextLevelXp - currentXp);
	}
	
	/**
	 * Get the XP progress towards the next level (0-100%).
	 */
	public static float getXpProgress(NexusPlayerData data) {
		int currentLevel = data.getNexusLevel();
		int currentXp = data.getNexusXp();
		
		int currentLevelXp = getXpRequiredForLevel(currentLevel);
		int nextLevelXp = getXpRequiredForLevel(currentLevel + 1);
		
		int xpInLevel = currentXp - currentLevelXp;
		int xpNeededForLevel = nextLevelXp - currentLevelXp;
		
		if (xpNeededForLevel <= 0) {
			return 0.0f;
		}
		
		return (float) xpInLevel / (float) xpNeededForLevel;
	}
	
	/**
	 * Handle level up.
	 */
	private static void levelUp(ServerPlayerEntity player, NexusPlayerData data) {
		int newLevel = data.getNexusLevel() + 1;
		
		if (newLevel > NexusConfig.NexusLevel.MAX_LEVEL) {
			return;
		}
		
		// Subtract XP for the level
		int xpForLevel = getXpRequiredForLevel(newLevel);
		data.setNexusXp(data.getNexusXp() - xpForLevel);
		
		// Increase level
		data.setNexusLevel(newLevel);
		
		// Increase max energy
		int newMaxEnergy = NexusConfig.NexusEnergy.BASE_MAX_ENERGY 
			+ (newLevel * NexusConfig.NexusEnergy.MAX_LEVEL_BONUS);
		data.setMaxNexusEnergy(newMaxEnergy);
		
		// Increase control
		float newControl = data.getNexusControl() + NexusConfig.NexusLevel.CONTROL_PER_LEVEL;
		data.setNexusControl(newControl);
		
		// Increase max Abyssal energy
		int newMaxAbyssal = NexusConfig.AbyssalEnergy.BASE_MAX_ENERGY 
			+ (newLevel * NexusConfig.AbyssalEnergy.MAX_LEVEL_BONUS);
		data.setMaxAbyssalEnergy(newMaxAbyssal);
		
		// Send message to player
		player.sendMessage(
			Text.of("\u00a76[Nexus] Level Up! You are now level \u00a7e" + newLevel),
			false
		);
		
		com.nexus.NexusMod.LOGGER.info("[Nexus] Player {} reached level {}", 
			player.getName().getString(), newLevel);
	}
	
	/**
	 * Check if player meets level requirement for an ability.
	 */
	public static boolean meetsLevelRequirement(NexusPlayerData data, int requiredLevel) {
		return data.getNexusLevel() >= requiredLevel;
	}
	
	/**
	 * Check if player meets control requirement for an ability.
	 */
	public static boolean meetsControlRequirement(NexusPlayerData data, float requiredControl) {
		return data.getNexusControl() >= requiredControl;
	}
}
