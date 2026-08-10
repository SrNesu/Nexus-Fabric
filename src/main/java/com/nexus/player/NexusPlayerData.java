package com.nexus.player;

import net.minecraft.nbt.NbtCompound;

/**
 * Represents the Nexus power data for a single player.
 * This data is persisted across sessions and deaths.
 */
public class NexusPlayerData {
	// Nexus Energy
	private int currentNexusEnergy;
	private int maxNexusEnergy;
	
	// Nexus Progression
	private int nexusLevel;
	private int nexusXp;
	
	// Nexus Control
	private float nexusControl; // 0-100%
	
	// Overload System
	private float overloadPercentage; // 0-100%
	
	// Abyssal Energy
	private int currentAbyssalEnergy;
	private int maxAbyssalEnergy;
	
	// Abyssal Corruption
	private float corruptionPercentage; // 0-100%
	
	// Ability Tracking
	private boolean nexusUnlocked;
	private long[] abilityCooldowns; // One per ability
	
	public NexusPlayerData() {
		initialize();
	}
	
	/**
	 * Initialize data with default values.
	 */
	private void initialize() {
		this.currentNexusEnergy = 50; // Start with half energy
		this.maxNexusEnergy = 100;
		this.nexusLevel = 1;
		this.nexusXp = 0;
		this.nexusControl = 10.0f; // Start with low control
		this.overloadPercentage = 0.0f;
		this.currentAbyssalEnergy = 0;
		this.maxAbyssalEnergy = 50;
		this.corruptionPercentage = 0.0f;
		this.nexusUnlocked = false;
		this.abilityCooldowns = new long[8]; // 8 abilities
	}
	
	/**
	 * Read data from NBT compound.
	 */
	public void readFromNbt(NbtCompound nbt) {
		this.currentNexusEnergy = nbt.getInt("CurrentNexusEnergy");
		this.maxNexusEnergy = nbt.getInt("MaxNexusEnergy");
		this.nexusLevel = nbt.getInt("NexusLevel");
		this.nexusXp = nbt.getInt("NexusXp");
		this.nexusControl = nbt.getFloat("NexusControl");
		this.overloadPercentage = nbt.getFloat("OverloadPercentage");
		this.currentAbyssalEnergy = nbt.getInt("CurrentAbyssalEnergy");
		this.maxAbyssalEnergy = nbt.getInt("MaxAbyssalEnergy");
		this.corruptionPercentage = nbt.getFloat("CorruptionPercentage");
		this.nexusUnlocked = nbt.getBoolean("NexusUnlocked");
	}
	
	/**
	 * Write data to NBT compound.
	 */
	public void writeToNbt(NbtCompound nbt) {
		nbt.putInt("CurrentNexusEnergy", currentNexusEnergy);
		nbt.putInt("MaxNexusEnergy", maxNexusEnergy);
		nbt.putInt("NexusLevel", nexusLevel);
		nbt.putInt("NexusXp", nexusXp);
		nbt.putFloat("NexusControl", nexusControl);
		nbt.putFloat("OverloadPercentage", overloadPercentage);
		nbt.putInt("CurrentAbyssalEnergy", currentAbyssalEnergy);
		nbt.putInt("MaxAbyssalEnergy", maxAbyssalEnergy);
		nbt.putFloat("CorruptionPercentage", corruptionPercentage);
		nbt.putBoolean("NexusUnlocked", nexusUnlocked);
	}
	
	// ===== NEXUS ENERGY =====
	public int getCurrentNexusEnergy() {
		return currentNexusEnergy;
	}
	
	public void setCurrentNexusEnergy(int energy) {
		this.currentNexusEnergy = Math.max(0, Math.min(energy, maxNexusEnergy));
	}
	
	public void addNexusEnergy(int amount) {
		setCurrentNexusEnergy(currentNexusEnergy + amount);
	}
	
	public void consumeNexusEnergy(int amount) {
		setCurrentNexusEnergy(currentNexusEnergy - amount);
	}
	
	public int getMaxNexusEnergy() {
		return maxNexusEnergy;
	}
	
	public void setMaxNexusEnergy(int max) {
		this.maxNexusEnergy = Math.max(1, max);
		if (currentNexusEnergy > maxNexusEnergy) {
			currentNexusEnergy = maxNexusEnergy;
		}
	}
	
	public boolean hasEnoughNexusEnergy(int required) {
		return currentNexusEnergy >= required;
	}
	
	// ===== NEXUS LEVEL & XP =====
	public int getNexusLevel() {
		return nexusLevel;
	}
	
	public void setNexusLevel(int level) {
		this.nexusLevel = Math.min(level, 100);
	}
	
	public int getNexusXp() {
		return nexusXp;
	}
	
	public void addNexusXp(int amount) {
		this.nexusXp += amount;
	}
	
	public void setNexusXp(int xp) {
		this.nexusXp = xp;
	}
	
	// ===== NEXUS CONTROL =====
	public float getNexusControl() {
		return nexusControl;
	}
	
	public void setNexusControl(float control) {
		this.nexusControl = Math.max(0, Math.min(control, 100));
	}
	
	public void addNexusControl(float amount) {
		setNexusControl(nexusControl + amount);
	}
	
	// ===== OVERLOAD =====
	public float getOverloadPercentage() {
		return overloadPercentage;
	}
	
	public void setOverloadPercentage(float percentage) {
		this.overloadPercentage = Math.max(0, Math.min(percentage, 100));
	}
	
	public void addOverload(float amount) {
		setOverloadPercentage(overloadPercentage + amount);
	}
	
	public void reduceOverload(float amount) {
		setOverloadPercentage(overloadPercentage - amount);
	}
	
	// ===== ABYSSAL ENERGY =====
	public int getCurrentAbyssalEnergy() {
		return currentAbyssalEnergy;
	}
	
	public void setCurrentAbyssalEnergy(int energy) {
		this.currentAbyssalEnergy = Math.max(0, Math.min(energy, maxAbyssalEnergy));
	}
	
	public void addAbyssalEnergy(int amount) {
		setCurrentAbyssalEnergy(currentAbyssalEnergy + amount);
	}
	
	public void consumeAbyssalEnergy(int amount) {
		setCurrentAbyssalEnergy(currentAbyssalEnergy - amount);
	}
	
	public int getMaxAbyssalEnergy() {
		return maxAbyssalEnergy;
	}
	
	public void setMaxAbyssalEnergy(int max) {
		this.maxAbyssalEnergy = Math.max(1, max);
	}
	
	public boolean hasEnoughAbyssalEnergy(int required) {
		return currentAbyssalEnergy >= required;
	}
	
	// ===== CORRUPTION =====
	public float getCorruptionPercentage() {
		return corruptionPercentage;
	}
	
	public void setCorruptionPercentage(float percentage) {
		this.corruptionPercentage = Math.max(0, Math.min(percentage, 100));
	}
	
	public void addCorruption(float amount) {
		setCorruptionPercentage(corruptionPercentage + amount);
	}
	
	public void reduceCorruption(float amount) {
		setCorruptionPercentage(corruptionPercentage - amount);
	}
	
	// ===== NEXUS STATUS =====
	public boolean isNexusUnlocked() {
		return nexusUnlocked;
	}
	
	public void unlockNexus() {
		this.nexusUnlocked = true;
	}
	
	public void resetNexus() {
		initialize();
	}
}
