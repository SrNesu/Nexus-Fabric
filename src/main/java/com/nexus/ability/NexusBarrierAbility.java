package com.nexus.ability;

import com.nexus.config.NexusConfig;
import com.nexus.player.NexusPlayerData;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.text.Text;

/**
 * Nexus Barrier: A protective barrier of Nexus energy.
 * Reduces damage taken temporarily.
 */
public class NexusBarrierAbility extends Ability {
	public NexusBarrierAbility() {
		super("nexus_barrier", "Nexus Barrier", "Create a protective barrier that reduces damage");
	}
	
	@Override
	public int getEnergyCost(NexusPlayerData data) {
		return NexusConfig.Abilities.NEXUS_BARRIER_COST;
	}
	
	@Override
	public int getMinimumLevel() {
		return NexusConfig.Abilities.NEXUS_BARRIER_MIN_LEVEL;
	}
	
	@Override
	public float getMinimumControl() {
		return 30.0f;
	}
	
	@Override
	public int getCooldownTicks() {
		return NexusConfig.Abilities.NEXUS_BARRIER_COOLDOWN;
	}
	
	@Override
	public float getOverloadIncrease() {
		return NexusConfig.Overload.OVERLOAD_PER_ABILITY_USE * 1.2f;
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
		
		// TODO: Apply barrier effect to player
		// This will involve status effects and event listeners
		
		player.sendMessage(Text.of("\u00a75[Nexus Barrier] Activated for " + 
			(NexusConfig.Abilities.NEXUS_BARRIER_DURATION / 20) + " seconds"), false);
		
		return new AbilityResult(true, "Nexus Barrier activated");
	}
}
