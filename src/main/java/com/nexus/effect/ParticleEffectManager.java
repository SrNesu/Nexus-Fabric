package com.nexus.effect;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages particle effects for Nexus abilities.
 */
public class ParticleEffectManager {
	
	public static void spawnNexusBlastParticles(ServerWorld world, Vec3d pos, Vec3d direction) {
		// Spawn multiple particles in the direction of the blast
		for (int i = 0; i < 20; i++) {
			Vec3d spread = direction.multiply(0.3).add(
				(Math.random() - 0.5) * 0.2,
				(Math.random() - 0.5) * 0.2,
				(Math.random() - 0.5) * 0.2
			);
			world.spawnParticles(
				ParticleTypes.END_ROD,
				pos.x, pos.y, pos.z,
				1,
				spread.x, spread.y, spread.z,
				0.5
			);
		}
	}
	
	public static void spawnNexusPulseParticles(ServerWorld world, Vec3d pos) {
		// Spawn particles in a sphere around the player
		for (int i = 0; i < 30; i++) {
			double angle = (Math.PI * 2 * i) / 30;
			double radius = 1.5;
			double x = Math.cos(angle) * radius;
			double z = Math.sin(angle) * radius;
			
			world.spawnParticles(
				ParticleTypes.ELECTRIC_SPARK,
				pos.x + x, pos.y, pos.z + z,
				1,
				0, 0, 0,
				0.3
			);
		}
	}
	
	public static void spawnNexusStepTrail(ServerWorld world, Vec3d pos) {
		// Spawn trailing particles
		for (int i = 0; i < 15; i++) {
			world.spawnParticles(
				ParticleTypes.END_ROD,
				pos.x, pos.y, pos.z,
				1,
				(Math.random() - 0.5) * 0.3,
				(Math.random() - 0.5) * 0.3,
				(Math.random() - 0.5) * 0.3,
				0.2
			);
		}
	}
	
	public static void spawnBarrierParticles(ServerWorld world, Vec3d pos) {
		// Spawn barrier particles around player
		for (int i = 0; i < 25; i++) {
			double angle = (Math.PI * 2 * i) / 25;
			double radius = 1.2;
			double x = Math.cos(angle) * radius;
			double z = Math.sin(angle) * radius;
			
			world.spawnParticles(
				ParticleTypes.END_PORTAL,
				pos.x + x, pos.y + 0.5, pos.z + z,
				1,
				0, 0, 0,
				0.1
			);
		}
	}
	
	public static void spawnOverloadParticles(ServerWorld world, Vec3d pos, float intensity) {
		int particleCount = (int)(10 * intensity);
		for (int i = 0; i < particleCount; i++) {
			world.spawnParticles(
				ParticleTypes.FLAME,
				pos.x, pos.y, pos.z,
				1,
				(Math.random() - 0.5) * 0.5,
				(Math.random() - 0.5) * 0.5,
				(Math.random() - 0.5) * 0.5,
				0.2
			);
		}
	}
}
