package com.nexus.ability;

import com.nexus.config.NexusConfig;
import com.nexus.player.NexusPlayerData;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.text.Text;

/**
 * Nexus Gravity: Advanced ability manipulating gravity.
 * Slows and pulls nearby entities.
 */
public class NexusGravityAbility extends Ability {
	public NexusGravityAbility() {
		super("nexus_gravity", "Nexus Gravity", "Manipulate gravity to slow enemies");
	}
	
	@Override
	public int getEnergyCost(NexusPlayerData data) {
		return NexusConfig.Abilities.NEXUS_GRAVITY_COST;
	}
	
	@Override
	public int getMinimumLevel() {
		return NexusConfig.Abilities.NEXUS_GRAVITY_MIN_LEVEL;
	}
	
	@Override
	public float getMinimumControl() {
		return 50.0f;
	}
	
	@Override
	public int getCooldownTicks() {
		return NexusConfig.Abilities.NEXUS_GRAVITY_COOLDOWN;
	}
	
	@Override
	public float getOverloadIncrease() {
		return NexusConfig.Overload.OVERLOAD_PER_ABILITY_USE * 2.0f;
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
		
		// Get nearby entities
		float range = NexusConfig.Abilities.NEXUS_GRAVITY_RANGE;
		var entities = player.getWorld().getOtherEntities(
			player,
			player.getBoundingBox().expand(range),
			entity -> entity instanceof net.minecraft.entity.LivingEntity && !(entity instanceof ServerPlayerEntity)
		);
		
		// Apply slowness effect
		for (var entity : entities) {
			if (entity instanceof net.minecraft.entity.LivingEntity livingEntity) {
				// TODO: Apply slowness effect
			}
		}
		
		player.sendMessage(Text.of("\u00a75[Nexus Gravity] Activated for " + 
			(NexusConfig.Abilities.NEXUS_GRAVITY_DURATION / 20) + " seconds"), false);
		
		return new AbilityResult(true, "Nexus Gravity activated");
	}
}
