package com.nexus.energy;

import com.nexus.config.NexusConfig;
import com.nexus.player.NexusPlayerData;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

/**
 * Main energy system tick handler.
 * Updates all energy-related mechanics every tick.
 */
public class EnergySystemHandler {
	
	public static void initialize() {
		ServerTickEvents.END_SERVER_TICK.register(server -> {
			for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
				updatePlayerEnergy(player);
			}
		});
	}
	
	/**
	 * Update all energy systems for a player (called every tick).
	 */
	private static void updatePlayerEnergy(ServerPlayerEntity player) {
		NexusPlayerData data = com.nexus.player.NexusPlayerManager.getPlayerData(player);
		
		// 20 ticks = 1 second
		float deltaTime = 1.0f / 20.0f;
		
		// Update Nexus energy
		if (data.isNexusUnlocked()) {
			NexusEnergyManager.updateEnergyRegeneration(data, deltaTime);
			OverloadManager.updateOverloadDecay(data, deltaTime);
			
			// Check for collapse
			if (OverloadManager.hasCollapsed(data)) {
				triggerCollapse(player, data);
			}
		}
		
		// Update Abyssal energy
		AbyssalEnergyManager.updateAbyssalRegeneration(data, deltaTime);
		AbyssalEnergyManager.updateCorruptionDecay(data, deltaTime);
		
		// Save player data periodically
		com.nexus.player.NexusPlayerManager.savePlayerData(player);
	}
	
	/**
	 * Trigger collapse effect when overload reaches max.
	 */
	private static void triggerCollapse(ServerPlayerEntity player, NexusPlayerData data) {
		// Reset overload
		OverloadManager.triggerCollapse(data);
		
		// Damage player
		player.damage(player.getDamageSources().magic(), NexusConfig.Overload.COLLAPSE_DAMAGE);
		
		// Send message
		player.sendMessage(
			net.minecraft.text.Text.of("\u00a7cYour Nexus has collapsed!"),
			false
		);
	}
}
