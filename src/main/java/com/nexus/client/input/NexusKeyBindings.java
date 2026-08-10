package com.nexus.client.input;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

/**
 * Handles all Nexus keybindings.
 */
public class NexusKeyBindings {
	
	public static KeyBinding NEXUS_BLAST;
	public static KeyBinding NEXUS_PULSE;
	public static KeyBinding NEXUS_STEP;
	public static KeyBinding NEXUS_BARRIER;
	public static KeyBinding NEXUS_SENSE;
	public static KeyBinding NEXUS_GRAVITY;
	public static KeyBinding NEXUS_DOMINION;
	public static KeyBinding NEXUS_ASCENSION;
	public static KeyBinding NEXUS_MENU;
	
	public static void register() {
		NEXUS_BLAST = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.nexus.blast",
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_R,
			"category.nexus.abilities"
		));
		
		NEXUS_PULSE = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.nexus.pulse",
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_V,
			"category.nexus.abilities"
		));
		
		NEXUS_STEP = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.nexus.step",
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_G,
			"category.nexus.abilities"
		));
		
		NEXUS_BARRIER = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.nexus.barrier",
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_H,
			"category.nexus.abilities"
		));
		
		NEXUS_SENSE = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.nexus.sense",
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_F,
			"category.nexus.abilities"
		));
		
		NEXUS_GRAVITY = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.nexus.gravity",
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_Z,
			"category.nexus.abilities"
		));
		
		NEXUS_DOMINION = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.nexus.dominion",
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_X,
			"category.nexus.abilities"
		));
		
		NEXUS_ASCENSION = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.nexus.ascension",
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_C,
			"category.nexus.abilities"
		));
		
		NEXUS_MENU = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.nexus.menu",
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_N,
			"category.nexus.menu"
		));
	}
}
