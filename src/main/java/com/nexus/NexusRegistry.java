package com.nexus;

import com.nexus.item.NexusItems;
import com.nexus.entity.NexusEntities;
import com.nexus.sound.NexusSounds;
import com.nexus.ability.AbilityRegistry;

/**
 * Central registry for all Nexus mod content.
 * Initialize all registries in a controlled manner.
 */
public class NexusRegistry {
	
	public static void initialize() {
		NexusItems.register();
		NexusEntities.register();
		NexusSounds.register();
		AbilityRegistry.register();
	}
}
