package com.nexus.ability;

import com.nexus.config.NexusConfig;
import com.nexus.player.NexusPlayerData;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.text.Text;

/**
 * Nexus Dominion: Powerful AoE ability.
 * Creates an area where the user gains dominion over enemies.
 */
public class NexusDominionAbility extends Ability {
	public NexusDominionAbility() {
		super("nexus_dominion", "Nexus Dominion", "Gain dominion over an area, buffing yourself and debuffing enemies");
	}
	
	@Override
	public int getEnergyCost(NexusPlayerData data) {
		return NexusConfig.Abilities.NEXUS_DOMINION_COST;
	}
	
	@Override
	public int getMinimumLevel() {
		return NexusConfig.Abilities.NEXUS_DOMINION_MIN_LEVEL;
	}
	
	@Override
	public float getMinimumControl() {
		return 70.0f;
	}
	
	@Override
	public int getCooldownTicks() {
		return NexusConfig.Abilities.NEXUS_DOMINION_COOLDOWN;
	}
	
	@Override
	public float getOverloadIncrease() {
		return NexusConfig.Overload.OVERLOAD_PER_ABILITY_USE * 3.0f;
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
		
		// TODO: Create dominion effect
		// Apply buffs to player and debuffs to enemies in area
		
		player.sendMessage(Text.of("\u00a76[Nexus Dominion] Activated for " + 
			(NexusConfig.Abilities.NEXUS_DOMINION_DURATION / 20) + " seconds"), false);
		
		return new AbilityResult(true, "Nexus Dominion activated");
	}
}
