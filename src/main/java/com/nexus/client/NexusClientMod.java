package com.nexus.client;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NexusClientMod implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("nexus-client");

	@Override
	public void onInitializeClient() {
		LOGGER.info("[Nexus] Initializing Nexus Client...");
		
		// Initialize client-side registries
		NexusClientRegistry.initialize();
		
		LOGGER.info("[Nexus] Nexus Client initialized successfully!");
	}
}
