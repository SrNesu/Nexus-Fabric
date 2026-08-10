package com.nexus.player;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.PersistentState;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Persistent state for managing Nexus data across all players.
 * Stored server-side in the world data.
 */
public class NexusPlayerDataPersistence extends PersistentState {
	private static final String DATA_NAME = "nexus_player_data";
	private final Map<UUID, NexusPlayerData> playerDataMap = new HashMap<>();
	
	public static NexusPlayerDataPersistence getServerState(net.minecraft.server.world.ServerWorld world) {
		return world.getPersistentStateManager()
			.getOrCreate(
				compound -> new NexusPlayerDataPersistence().readData(compound),
				NexusPlayerDataPersistence::new,
				DATA_NAME
		);
	}
	
	/**
	 * Get or create player data.
	 */
	public NexusPlayerData getPlayerData(PlayerEntity player) {
		return getPlayerData(player.getUuid());
	}
	
	public NexusPlayerData getPlayerData(UUID playerUuid) {
		return playerDataMap.computeIfAbsent(playerUuid, uuid -> new NexusPlayerData());
	}
	
	/**
	 * Read data from NBT.
	 */
	private NexusPlayerDataPersistence readData(NbtCompound nbt) {
		NbtCompound playersNbt = nbt.getCompound("Players");
		
		for (String key : playersNbt.getKeys()) {
			try {
				UUID uuid = UUID.fromString(key);
				NbtCompound playerNbt = playersNbt.getCompound(key);
				
				NexusPlayerData data = new NexusPlayerData();
				data.readFromNbt(playerNbt);
				
				playerDataMap.put(uuid, data);
			} catch (IllegalArgumentException e) {
				// Invalid UUID, skip
			}
		}
		
		return this;
	}
	
	@Override
	public @NotNull NbtCompound writeNbt(NbtCompound nbt) {
		NbtCompound playersNbt = new NbtCompound();
		
		for (Map.Entry<UUID, NexusPlayerData> entry : playerDataMap.entrySet()) {
			NbtCompound playerNbt = new NbtCompound();
			entry.getValue().writeToNbt(playerNbt);
			playersNbt.put(entry.getKey().toString(), playerNbt);
		}
		
		nbt.put("Players", playersNbt);
		return nbt;
	}
	
	/**
	 * Mark the data as dirty (needs to be saved).
	 */
	public void markDirty() {
		setDirty(true);
	}
}
