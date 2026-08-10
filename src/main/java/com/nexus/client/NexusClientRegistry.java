package com.nexus.client;

import com.nexus.client.input.NexusKeyBindings;

/**
 * Client-side registry for Nexus mod.
 * Handles keybindings, rendering, and client events.
 */
public class NexusClientRegistry {
	
	public static void initialize() {
		NexusKeyBindings.register();
	}
}
