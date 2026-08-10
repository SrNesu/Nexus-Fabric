package com.nexus.ability;

import com.nexus.config.NexusConfig;
import com.nexus.player.NexusPlayerData;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.text.Text;

/**
 * Nexus Ascension: Ultimate ability.
 * Manifests extreme Nexus power with major buffs but high costs.
 */
public class NexusAscensionAbility extends Ability {
	public NexusAscensionAbility() {
		super("nexus_ascension", "Nexus Ascension", "Ultimate power manifestation - massive buffs but massive costs");
	}
	
	@Override
	public int getEnergyCost(NexusPlayerData data) {
		return NexusConfig.Abilities.NEXUS_ASCENSION_COST;
	}
	
	@Override
	public int getMinimumLevel() {
		return NexusConfig.Abilities.NEXUS_ASCENSION_MIN_LEVEL;
	}
	
	@Override
	public float getMinimumControl() {
		return 90.0f;
	}
	
	@Override
	public int getCooldownTicks() {
		return NexusConfig.Abilities.NEXUS_ASCENSION_COOLDOWN;
	}
	
	@Override
	public float getOverloadIncrease() {
		return NexusConfig.Overload.OVERLOAD_PER_ABILITY_USE * 5.0f;
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
		
		// TODO: Apply ascension buffs and effects
		// Speed boost
		// Strength boost
		// Resistance boost
		// Visual effects
		
		player.sendMessage(Text.of("\u00a7c\u00a7l[NEXUS ASCENSION] YOU HAVE ASCENDED!"), false);
		
		return new AbilityResult(true, "Nexus Ascension activated");
	}
}
