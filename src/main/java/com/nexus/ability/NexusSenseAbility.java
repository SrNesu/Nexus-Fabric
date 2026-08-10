package com.nexus.ability;

import com.nexus.config.NexusConfig;
import com.nexus.player.NexusPlayerData;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.text.Text;

/**
 * Nexus Sense: Supernatural perception ability.
 * Reveals nearby entities through obstacles.
 */
public class NexusSenseAbility extends Ability {
	public NexusSenseAbility() {
		super("nexus_sense", "Nexus Sense", "Perceive entities around you through obstacles");
	}
	
	@Override
	public int getEnergyCost(NexusPlayerData data) {
		return NexusConfig.Abilities.NEXUS_SENSE_COST;
	}
	
	@Override
	public int getMinimumLevel() {
		return NexusConfig.Abilities.NEXUS_SENSE_MIN_LEVEL;
	}
	
	@Override
	public float getMinimumControl() {
		return 40.0f;
	}
	
	@Override
	public int getCooldownTicks() {
		return NexusConfig.Abilities.NEXUS_SENSE_COOLDOWN;
	}
	
	@Override
	public float getOverloadIncrease() {
		return NexusConfig.Overload.OVERLOAD_PER_ABILITY_USE * 0.6f;
	}
	
	@Override
	public AbilityResult execute(ServerPlayerEntity player, NexusPlayerData data) {
		// Check if can use
		AbilityResult canUse = canUse(player, data);
		if (!canUse.isSuccess()) {
			return canUse;
		}
		
		// Apply ability effects
		applyAbilityEffects(player, data);
		
		// TODO: Apply sense effect to nearby entities
		// This will highlight entities for the player
		
		player.sendMessage(Text.of("\u00a75[Nexus Sense] Activated for " + 
			(NexusConfig.Abilities.NEXUS_SENSE_DURATION / 20) + " seconds"), false);
		
		return new AbilityResult(true, "Nexus Sense activated");
	}
}
