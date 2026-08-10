package com.nexus.ability;

import com.nexus.config.NexusConfig;
import com.nexus.player.NexusPlayerData;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

/**
 * Nexus Blast: A concentrated burst of energy.
 * Can be charged for increased damage and cost.
 */
public class NexusBlastAbility extends Ability {
	public NexusBlastAbility() {
		super("nexus_blast", "Nexus Blast", "Fire a concentrated blast of Nexus energy");
	}
	
	@Override
	public int getEnergyCost(NexusPlayerData data) {
		return NexusConfig.Abilities.NEXUS_BLAST_BASE_COST;
	}
	
	@Override
	public int getMinimumLevel() {
		return NexusConfig.Abilities.NEXUS_BLAST_MIN_LEVEL;
	}
	
	@Override
	public float getMinimumControl() {
		return 0; // Available from start
	}
	
	@Override
	public int getCooldownTicks() {
		return NexusConfig.Abilities.NEXUS_BLAST_COOLDOWN;
	}
	
	@Override
	public float getOverloadIncrease() {
		return NexusConfig.Overload.OVERLOAD_PER_ABILITY_USE;
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
		
		// Create projectile at player's eye position
		Vec3d eyePos = player.getEyePos();
		Vec3d direction = player.getRotationVec(1.0f).normalize();
		
		// Calculate damage based on control
		int baseDamage = NexusConfig.Abilities.NEXUS_BLAST_BASE_DAMAGE;
		float damageMultiplier = 1.0f + (data.getNexusControl() / 100.0f);
		float finalDamage = baseDamage * damageMultiplier;
		
		// TODO: Create and spawn projectile entity
		// This will be implemented when entity system is set up
		
		player.sendMessage(Text.of("\u00a75[Nexus Blast] Released! Damage: " + (int)finalDamage), false);
		
		return new AbilityResult(true, "Nexus Blast executed");
	}
}
