package com.nexus.progression;

import com.nexus.config.NexusConfig;
import com.nexus.player.NexusPlayerManager;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles XP gain events for players.
 */
public class XpGainHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger("nexus-xp");
	
	public static void initialize() {
		// XP gain events will be registered through various event handlers
	}
	
	/**
	 * Award XP for using an ability.
	 */
	public static void awardAbilityUseXp(ServerPlayerEntity player) {
		var data = NexusPlayerManager.getPlayerData(player);
		if (data.isNexusUnlocked()) {
			NexusProgressionManager.addNexusXp(player, data, NexusConfig.XP.XP_ABILITY_USE);
		}
	}
	
	/**
	 * Award XP for defeating a mob.
	 */
	public static void awardMobKillXp(ServerPlayerEntity player) {
		var data = NexusPlayerManager.getPlayerData(player);
		if (data.isNexusUnlocked()) {
			NexusProgressionManager.addNexusXp(player, data, NexusConfig.XP.XP_MOB_KILL);
		}
	}
	
	/**
	 * Award XP for discovering a structure.
	 */
	public static void awardStructureDiscoveryXp(ServerPlayerEntity player) {
		var data = NexusPlayerManager.getPlayerData(player);
		if (data.isNexusUnlocked()) {
			NexusProgressionManager.addNexusXp(player, data, NexusConfig.XP.XP_STRUCTURE_DISCOVERY);
			player.sendMessage(
				Text.of("\u00a75[Nexus] Structure discovered! +" + NexusConfig.XP.XP_STRUCTURE_DISCOVERY + " XP"),
				false
			);
		}
	}
	
	/**
	 * Award XP for finding an artifact.
	 */
	public static void awardArtifactFoundXp(ServerPlayerEntity player) {
		var data = NexusPlayerManager.getPlayerData(player);
		if (data.isNexusUnlocked()) {
			NexusProgressionManager.addNexusXp(player, data, NexusConfig.XP.XP_ARTIFACT_FOUND);
			player.sendMessage(
				Text.of("\u00a75[Nexus] Artifact found! +" + NexusConfig.XP.XP_ARTIFACT_FOUND + " XP"),
				false
			);
		}
	}
	
	/**
	 * Award XP for defeating a boss.
	 */
	public static void awardBossKillXp(ServerPlayerEntity player) {
		var data = NexusPlayerManager.getPlayerData(player);
		if (data.isNexusUnlocked()) {
			NexusProgressionManager.addNexusXp(player, data, NexusConfig.XP.XP_BOSS_KILL);
			player.sendMessage(
				Text.of("\u00a76[Nexus] Boss defeated! +" + NexusConfig.XP.XP_BOSS_KILL + " XP"),
				false
			);
		}
	}
}
