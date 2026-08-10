package com.nexus.ability;

import com.nexus.config.NexusConfig;
import com.nexus.player.NexusPlayerData;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

/**
 * Nexus Step: A high-speed dash movement.
 * Moves player quickly in the direction they're looking.
 */
public class NexusStepAbility extends Ability {
	public NexusStepAbility() {
		super("nexus_step", "Nexus Step", "Dash quickly in the direction you're facing");
	}
	
	@Override
	public int getEnergyCost(NexusPlayerData data) {
		return NexusConfig.Abilities.NEXUS_STEP_COST;
	}
	
	@Override
	public int getMinimumLevel() {
		return NexusConfig.Abilities.NEXUS_STEP_MIN_LEVEL;
	}
	
	@Override
	public float getMinimumControl() {
		return 15.0f;
	}
	
	@Override
	public int getCooldownTicks() {
		return NexusConfig.Abilities.NEXUS_STEP_COOLDOWN;
	}
	
	@Override
	public float getOverloadIncrease() {
		return NexusConfig.Overload.OVERLOAD_PER_ABILITY_USE * 0.8f;
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
		
		// Get direction player is looking
		Vec3d direction = player.getRotationVec(1.0f).normalize();
		
		// Calculate distance based on control
		float baseDistance = NexusConfig.Abilities.NEXUS_STEP_DISTANCE;
		float distanceMultiplier = 1.0f + (data.getNexusControl() / 100.0f * 0.5f);
		float finalDistance = baseDistance * distanceMultiplier;
		
		// Apply velocity
		Vec3d newVelocity = direction.multiply(finalDistance / 10.0); // Normalize for velocity
		player.setVelocity(newVelocity);
		
		player.sendMessage(Text.of("\u00a75[Nexus Step] Dashed!"), false);
		
		return new AbilityResult(true, "Nexus Step executed");
	}
}
