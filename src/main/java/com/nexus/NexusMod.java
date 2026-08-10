package com.nexus;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NexusMod implements ModInitializer {
	public static final String MOD_ID = "nexus";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("[Nexus] Initializing Nexus Mod (Fabric 1.20.1)...");
		
		// Initialize registries and systems
		NexusRegistry.initialize();
		
		LOGGER.info("[Nexus] Nexus Mod initialized successfully!");
	}
}
