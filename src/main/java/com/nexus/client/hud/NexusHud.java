package com.nexus.client.hud;

import com.nexus.player.NexusPlayerManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import com.nexus.NexusMod;

/**
 * Main HUD display for Nexus energy and status.
 */
@Environment(EnvType.CLIENT)
public class NexusHud {
	
	private static final int X_OFFSET = 10;
	private static final int Y_OFFSET = 10;
	private static final int BAR_WIDTH = 100;
	private static final int BAR_HEIGHT = 5;
	
	public static void render(DrawContext context, float partialTick) {
		MinecraftClient client = MinecraftClient.getInstance();
		
		if (client.player == null) {
			return;
		}
		
		var data = NexusPlayerManager.getPlayerData(client.player);
		
		if (!data.isNexusUnlocked()) {
			return;
		}
		
		int y = Y_OFFSET;
		
		// Draw level
		context.drawTextWithShadow(
			client.textRenderer,
			"Nexus Lv." + data.getNexusLevel(),
			X_OFFSET, y,
			0x00FF00
		);
		y += 12;
		
		// Draw energy bar
		drawBar(context, X_OFFSET, y, BAR_WIDTH, BAR_HEIGHT,
			data.getCurrentNexusEnergy(), data.getMaxNexusEnergy(),
			0x0080FF);
		y += 8;
		
		// Draw overload bar
		drawBar(context, X_OFFSET, y, BAR_WIDTH, BAR_HEIGHT,
			(int)data.getOverloadPercentage(), 100,
			0xFF4040);
	}
	
	private static void drawBar(DrawContext context, int x, int y, int width, int height,
							   int current, int max, int color) {
		// Background
		context.fill(x, y, x + width, y + height, 0xFF000000);
		
		// Bar
		int barWidth = (int) ((float) current / max * width);
		context.fill(x, y, x + barWidth, y + height, color);
		
		// Border
		context.fill(x - 1, y - 1, x + width + 1, y, 0xFFFFFFFF);
		context.fill(x - 1, y + height, x + width + 1, y + height + 1, 0xFFFFFFFF);
		context.fill(x - 1, y - 1, x, y + height + 1, 0xFFFFFFFF);
		context.fill(x + width, y - 1, x + width + 1, y + height + 1, 0xFFFFFFFF);
	}
}
