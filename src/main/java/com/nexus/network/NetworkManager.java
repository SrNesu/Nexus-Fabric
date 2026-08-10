package com.nexus.network;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import com.nexus.NexusMod;

/**
 * Network channel manager for Nexus mod.
 * Handles all client-server communication.
 */
public class NetworkManager {
	
	// Packet identifiers
	public static final Identifier ACTIVATE_ABILITY_PACKET = 
		new Identifier(NexusMod.MOD_ID, "activate_ability");
	public static final Identifier CHARGE_ABILITY_PACKET = 
		new Identifier(NexusMod.MOD_ID, "charge_ability");
	public static final Identifier SYNC_NEXUS_DATA_PACKET = 
		new Identifier(NexusMod.MOD_ID, "sync_nexus_data");
	
	public static void initialize() {
		// Register server-side packet handlers
		ServerPlayNetworking.registerGlobalReceiver(
			ACTIVATE_ABILITY_PACKET,
			(server, player, handler, buf, responseSender) -> {
				String abilityId = buf.readString();
				server.execute(() -> {
					var data = com.nexus.player.NexusPlayerManager.getPlayerData(player);
					var ability = com.nexus.ability.AbilityRegistry.getAbility(abilityId);
					if (ability != null) {
						ability.execute((net.minecraft.entity.player.ServerPlayerEntity) player, data);
					}
				});
			}
		);
	}
}
