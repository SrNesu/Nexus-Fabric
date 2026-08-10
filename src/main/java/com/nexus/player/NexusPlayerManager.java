package com.nexus.player;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Manages Nexus player data.
 * Handles loading, saving, and accessing player-specific Nexus data.
 */
public class NexusPlayerManager {
	private static final Logger LOGGER = LoggerFactory.getLogger("nexus-player");
	private static NexusPlayerDataPersistence persistence;
	
	public static void initialize() {
		// Initialize persistence when server starts
		ServerLifecycleEvents.SERVER_STARTED.register(server -> {
			ServerWorld world = server.getOverworld();
			persistence = NexusPlayerDataPersistence.getServerState(world);
			LOGGER.info("[Nexus] Player data persistence initialized");
		});
	}
	
	/**
	 * Get the Nexus data for a player.
	 */
	public static NexusPlayerData getPlayerData(PlayerEntity player) {
		if (persistence == null) {
			LOGGER.warn("[Nexus] Persistence not initialized, creating new data");
			return new NexusPlayerData();
		}
		return persistence.getPlayerData(player);
	}
	
	/**
	 * Save player data to disk.
	 */
	public static void savePlayerData(PlayerEntity player) {
		if (persistence != null) {
			persistence.markDirty();
		}
	}
	
	/**
	 * Check if player has Nexus unlocked.
	 */
	public static boolean isNexusUnlocked(PlayerEntity player) {
		return getPlayerData(player).isNexusUnlocked();
	}
	
	/**
	 * Unlock Nexus for a player.
	 */
	public static void unlockNexus(PlayerEntity player) {
		NexusPlayerData data = getPlayerData(player);
		if (!data.isNexusUnlocked()) {
			data.unlockNexus();
			savePlayerData(player);
			LOGGER.info("[Nexus] Nexus unlocked for player: {}", player.getName().getString());
		}
	}
}
