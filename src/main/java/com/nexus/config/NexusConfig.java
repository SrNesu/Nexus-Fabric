package com.nexus.config;

/**
 * Central configuration for Nexus mod balancing.
 * All important values are stored here to avoid scattering numbers throughout the code.
 */
public class NexusConfig {
	
	// ===== NEXUS ENERGY CONFIGURATION =====
	public static final class NexusEnergy {
		public static final int BASE_MAX_ENERGY = 100;
		public static final int MAX_LEVEL_BONUS = 50; // +50 per level
		public static final float BASE_REGENERATION = 2.0f; // per second
		public static final float REGENERATION_PER_LEVEL = 0.5f;
		public static final float REGENERATION_MODIFIER_MIN = 0.2f; // When in critical overload
		
		private NexusEnergy() {}
	}
	
	// ===== NEXUS LEVEL CONFIGURATION =====
	public static final class NexusLevel {
		public static final int MAX_LEVEL = 100;
		public static final int BASE_XP_FOR_LEVEL_UP = 500;
		public static final float XP_MULTIPLIER_PER_LEVEL = 1.1f; // Each level requires 10% more XP
		public static final float CONTROL_PER_LEVEL = 0.8f; // +0.8% control per level
		public static final float ABILITY_DAMAGE_MULTIPLIER = 1.5f; // Base damage multiplier at max level
		
		private NexusLevel() {}
	}
	
	// ===== OVERLOAD CONFIGURATION =====
	public static final class Overload {
		public static final float NORMAL_THRESHOLD = 25.0f;
		public static final float UNSTABLE_THRESHOLD = 50.0f;
		public static final float CRITICAL_THRESHOLD = 75.0f;
		public static final float OVERLOAD_MAX = 100.0f;
		
		// Overload increases
		public static final float OVERLOAD_PER_ABILITY_USE = 5.0f;
		public static final float OVERLOAD_PER_ASCENSION_TICK = 3.0f;
		
		// Overload effects
		public static final float REGENERATION_PENALTY_UNSTABLE = 0.8f;
		public static final float REGENERATION_PENALTY_CRITICAL = 0.5f;
		public static final float REGENERATION_PENALTY_OVERLOAD = 0.2f;
		
		// Damage taken when collapsing
		public static final float COLLAPSE_DAMAGE = 20.0f;
		
		public static final float OVERLOAD_DECAY_RATE = 0.5f; // Per second
		
		private Overload() {}
	}
	
	// ===== ABILITY CONFIGURATION =====
	public static final class Abilities {
		// Nexus Blast
		public static final int NEXUS_BLAST_BASE_COST = 15;
		public static final int NEXUS_BLAST_BASE_DAMAGE = 8;
		public static final int NEXUS_BLAST_COOLDOWN = 10; // ticks = ~0.5 seconds
		public static final int NEXUS_BLAST_MIN_LEVEL = 1;
		public static final float NEXUS_BLAST_DAMAGE_PER_TICK = 0.5f; // Damage increases with charge time
		public static final float NEXUS_BLAST_COST_PER_TICK = 0.2f; // Cost increases with charge time
		public static final int NEXUS_BLAST_MAX_CHARGE_TICKS = 100;
		
		// Nexus Pulse
		public static final int NEXUS_PULSE_COST = 25;
		public static final int NEXUS_PULSE_DAMAGE = 12;
		public static final int NEXUS_PULSE_COOLDOWN = 30;
		public static final float NEXUS_PULSE_RANGE = 8.0f;
		public static final float NEXUS_PULSE_KNOCKBACK = 1.5f;
		public static final int NEXUS_PULSE_MIN_LEVEL = 3;
		
		// Nexus Step
		public static final int NEXUS_STEP_COST = 20;
		public static final int NEXUS_STEP_COOLDOWN = 20;
		public static final float NEXUS_STEP_DISTANCE = 15.0f;
		public static final int NEXUS_STEP_MIN_LEVEL = 2;
		
		// Nexus Barrier
		public static final int NEXUS_BARRIER_COST = 30;
		public static final int NEXUS_BARRIER_DURATION = 200; // 10 seconds
		public static final float NEXUS_BARRIER_DAMAGE_REDUCTION = 0.5f; // 50% reduction
		public static final float NEXUS_BARRIER_ABSORPTION = 50.0f;
		public static final int NEXUS_BARRIER_COOLDOWN = 40;
		public static final int NEXUS_BARRIER_MIN_LEVEL = 4;
		
		// Nexus Sense
		public static final int NEXUS_SENSE_COST = 15;
		public static final int NEXUS_SENSE_DURATION = 150; // 7.5 seconds
		public static final float NEXUS_SENSE_RANGE = 32.0f;
		public static final int NEXUS_SENSE_COOLDOWN = 25;
		public static final int NEXUS_SENSE_MIN_LEVEL = 5;
		
		// Nexus Gravity
		public static final int NEXUS_GRAVITY_COST = 40;
		public static final int NEXUS_GRAVITY_DURATION = 120; // 6 seconds
		public static final float NEXUS_GRAVITY_RANGE = 10.0f;
		public static final float NEXUS_GRAVITY_SLOWNESS = 0.7f;
		public static final int NEXUS_GRAVITY_COOLDOWN = 60;
		public static final int NEXUS_GRAVITY_MIN_LEVEL = 6;
		
		// Nexus Dominion
		public static final int NEXUS_DOMINION_COST = 60;
		public static final int NEXUS_DOMINION_DURATION = 180; // 9 seconds
		public static final float NEXUS_DOMINION_RANGE = 16.0f;
		public static final float NEXUS_DOMINION_DAMAGE_BONUS = 1.8f; // 80% more damage
		public static final int NEXUS_DOMINION_COOLDOWN = 120;
		public static final int NEXUS_DOMINION_MIN_LEVEL = 8;
		
		// Nexus Ascension
		public static final int NEXUS_ASCENSION_COST = 80;
		public static final int NEXUS_ASCENSION_DURATION = 200; // 10 seconds
		public static final int NEXUS_ASCENSION_COOLDOWN = 300; // 15 seconds
		public static final float NEXUS_ASCENSION_SPEED_BONUS = 0.5f; // 50% speed
		public static final float NEXUS_ASCENSION_STRENGTH_BONUS = 4.0f; // +4 damage
		public static final float NEXUS_ASCENSION_RESISTANCE_BONUS = 0.5f; // 50% damage reduction
		public static final int NEXUS_ASCENSION_MIN_LEVEL = 10;
		public static final float NEXUS_ASCENSION_OVERLOAD_PER_TICK = 2.0f;
		public static final int NEXUS_ASCENSION_EXHAUSTION_DURATION = 100; // Ticks of weakness after
		
		private Abilities() {}
	}
	
	// ===== ABYSSAL ENERGY CONFIGURATION =====
	public static final class AbyssalEnergy {
		public static final int BASE_MAX_ENERGY = 50;
		public static final int MAX_LEVEL_BONUS = 25;
		public static final float BASE_REGENERATION = 1.0f;
		
		private AbyssalEnergy() {}
	}
	
	// ===== CORRUPTION CONFIGURATION =====
	public static final class Corruption {
		public static final float CORRUPTION_PER_ABILITY_USE = 3.0f;
		public static final float CORRUPTION_MAX = 100.0f;
		public static final float CORRUPTION_DECAY_RATE = 0.2f; // Per second
		
		public static final float CORRUPTED_THRESHOLD = 50.0f;
		public static final float SEVERELY_CORRUPTED_THRESHOLD = 75.0f;
		
		// Effects
		public static final float STRENGTH_PENALTY_CORRUPTED = 0.8f;
		public static final float SPEED_PENALTY_CORRUPTED = 0.9f;
		
		private Corruption() {}
	}
	
	// ===== XP CONFIGURATION =====
	public static final class XP {
		// XP gains
		public static final int XP_ABILITY_USE = 10;
		public static final int XP_MOB_KILL = 50;
		public static final int XP_STRUCTURE_DISCOVERY = 100;
		public static final int XP_ARTIFACT_FOUND = 150;
		public static final int XP_BOSS_KILL = 500;
		
		private XP() {}
	}
	
	private NexusConfig() {}
}
