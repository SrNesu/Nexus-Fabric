package com.nexus.event;

import com.nexus.player.NexusPlayerManager;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ActionResult;

/**
 * Handles player events related to Nexus system.
 */
public class PlayerEventHandler {
	
	public static void initialize() {
		// Register attack event for mob kill XP
		AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
			if (!world.isClient && entity instanceof LivingEntity living && player instanceof ServerPlayerEntity serverPlayer) {
				// XP will be awarded when mob dies, not on hit
			}
			return ActionResult.PASS;
		});
	}
}
