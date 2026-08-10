package com.nexus.client;

import com.nexus.client.hud.NexusHud;
import com.nexus.client.input.NexusKeyBindings;
import com.nexus.client.screen.NexusMenuScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;

/**
 * Client-side event handlers for Nexus mod.
 */
@Environment(EnvType.CLIENT)
public class NexusClientEvents {
	
	public static void initialize() {
		// Register HUD renderer
		HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
			NexusHud.render(drawContext, tickDelta);
		});
		
		// Register client tick for input handling
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			handleAbilityInput(client);
		});
	}
	
	private static void handleAbilityInput(MinecraftClient client) {
		if (client.player == null) {
			return;
		}
		
		// Handle menu key
		if (NexusKeyBindings.NEXUS_MENU.wasPressed()) {
			client.setScreen(new NexusMenuScreen());
		}
		
		// Ability keybinds would be handled here
		// This will send packets to server to activate abilities
	}
}
