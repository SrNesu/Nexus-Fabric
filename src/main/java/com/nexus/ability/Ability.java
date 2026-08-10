package com.nexus.ability;

import com.nexus.player.NexusPlayerData;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.text.Text;
import java.util.HashMap;
import java.util.Map;

/**
 * Base class for all Nexus abilities.
 * All abilities extend this and implement their specific logic.
 */
public abstract class Ability {
	private final String abilityId;
	private final String displayName;
	private final String description;
	
	protected Ability(String abilityId, String displayName, String description) {
		this.abilityId = abilityId;
		this.displayName = displayName;
		this.description = description;
	}
	
	/**
	 * Get the ability ID (unique identifier).
	 */
	public String getAbilityId() {
		return abilityId;
	}
	
	/**
	 * Get the display name.
	 */
	public String getDisplayName() {
		return displayName;
	}
	
	/**
	 * Get the description.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Get energy cost for this ability.
	 */
	public abstract int getEnergyCost(NexusPlayerData data);
	
	/**
	 * Get minimum Nexus level required.
	 */
	public abstract int getMinimumLevel();
	
	/**
	 * Get minimum control required (0-100).
	 */
	public abstract float getMinimumControl();
	
	/**
	 * Get cooldown in ticks.
	 */
	public abstract int getCooldownTicks();
	
	/**
	 * Get overload increase when using this ability.
	 */
	public abstract float getOverloadIncrease();
	
	/**
	 * Execute the ability.
	 */
	public abstract AbilityResult execute(ServerPlayerEntity player, NexusPlayerData data);
	
	/**
	 * Check if player can use this ability.
	 */
	public AbilityResult canUse(ServerPlayerEntity player, NexusPlayerData data) {
		if (!data.isNexusUnlocked()) {
			return new AbilityResult(false, "Nexus not unlocked");
		}
		
		if (data.getNexusLevel() < getMinimumLevel()) {
			return new AbilityResult(false, 
				"Requires level " + getMinimumLevel() + ", you are level " + data.getNexusLevel());
		}
		
		if (data.getNexusControl() < getMinimumControl()) {
			return new AbilityResult(false, 
				"Requires " + (int)getMinimumControl() + "% control, you have " + (int)data.getNexusControl() + "%");
		}
		
		if (!data.hasEnoughNexusEnergy(getEnergyCost(data))) {
			return new AbilityResult(false, "Insufficient Nexus energy");
		}
		
		return new AbilityResult(true, "Can use");
	}
	
	/**
	 * Apply ability effects (energy consumption, overload, etc).
	 */
	protected void applyAbilityEffects(ServerPlayerEntity player, NexusPlayerData data) {
		// Consume energy
		data.consumeNexusEnergy(getEnergyCost(data));
		
		// Add overload
		com.nexus.energy.OverloadManager.addOverloadFromAbility(data);
	}
	
	/**
	 * Represents the result of an ability execution.
	 */
	public static class AbilityResult {
		private final boolean success;
		private final String message;
		
		public AbilityResult(boolean success, String message) {
			this.success = success;
			this.message = message;
		}
		
		public boolean isSuccess() {
			return success;
		}
		
		public String getMessage() {
			return message;
		}
	}
}
