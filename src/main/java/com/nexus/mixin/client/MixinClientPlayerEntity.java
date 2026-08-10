package com.nexus.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.network.ClientPlayerEntity;

/**
 * Client-side mixin for player entity.
 * Used for syncing client state with server.
 */
@Mixin(ClientPlayerEntity.class)
public class MixinClientPlayerEntity {
	// Mixins will be added here as needed
}
