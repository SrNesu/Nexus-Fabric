package com.nexus.ability;

import com.nexus.config.NexusConfig;
import com.nexus.player.NexusPlayerData;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.text.Text;

/**
 * Nexus Pulse: An explosion of energy around the player.
 * Damages and pushes nearby enemies.
 */
public class NexusPulseAbility extends Ability {
	public NexusPulseAbility() {
		super("nexus_pulse", "Nexus Pulse", "Explode with Nexus energy, pushing nearby enemies");
	}
	
	@Override
	public int getEnergyCost(NexusPlayerData data) {
		return NexusConfig.Abilities.NEXUS_PULSE_COST;
	}
	
	@Override
	public int getMinimumLevel() {
		return NexusConfig.Abilities.NEXUS_PULSE_MIN_LEVEL;
	}
	
	@Override
	public float getMinimumControl() {
		return 20.0f;
	}
	
	@Override
	public int getCooldownTicks() {
		return NexusConfig.Abilities.NEXUS_PULSE_COOLDOWN;
	}
	
	@Override
	public float getOverloadIncrease() {
		return NexusConfig.Overload.OVERLOAD_PER_ABILITY_USE * 1.5f;
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
		float range = NexusConfig.Abilities.NEXUS_PULSE_RANGE;
		var entities = player.getWorld().getOtherEntities(
			player,
			player.getBoundingBox().expand(range),
			entity -> entity instanceof net.minecraft.entity.LivingEntity && !(entity instanceof ServerPlayerEntity)
		);
		
		// Damage and knockback
		for (var entity : entities) {
			if (entity instanceof net.minecraft.entity.LivingEntity livingEntity) {
				// Calculate damage
				float baseDamage = NexusConfig.Abilities.NEXUS_PULSE_DAMAGE;
				float damageMultiplier = 1.0f + (data.getNexusControl() / 100.0f);
				float finalDamage = baseDamage * damageMultiplier;
				
				// Apply damage
				livingEntity.damage(player.getDamageSources().magic(), finalDamage);
				
				// Apply knockback
				net.minecraft.util.math.Vec3d direction = entity.getPos().subtract(player.getPos()).normalize();
				double knockback = NexusConfig.Abilities.NEXUS_PULSE_KNOCKBACK;
				entity.setVelocity(entity.getVelocity().add(direction.multiply(knockback)));
			}
		}
		
		player.sendMessage(Text.of("\u00a75[Nexus Pulse] Released! Damaged " + entities.size() + " entities"), false);
		
		return new AbilityResult(true, "Nexus Pulse executed");
	}
}
