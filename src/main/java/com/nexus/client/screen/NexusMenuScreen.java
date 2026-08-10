package com.nexus.client.screen;

import com.nexus.player.NexusPlayerManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import com.nexus.ability.AbilityRegistry;

/**
 * Nexus menu screen showing player stats and abilities.
 */
@Environment(EnvType.CLIENT)
public class NexusMenuScreen extends Screen {
	
	public NexusMenuScreen() {
		super(Text.of("Nexus Menu"));
	}
	
	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float delta) {
		this.renderBackground(context);
		
		MinecraftClient client = MinecraftClient.getInstance();
		if (client.player == null) {
			return;
		}
		
		var data = NexusPlayerManager.getPlayerData(client.player);
		
		// Draw title
		context.drawCenteredTextWithShadow(
			client.textRenderer,
		
"NEXUS",
			this.width / 2, 20,
			0xFF00FF00
		);
		
		int y = 50;
		int x = 20;
		
		// Draw player stats
		context.drawTextWithShadow(client.textRenderer, "Level: " + data.getNexusLevel(), x, y, 0xFFFFFF);
		y += 12;
		context.drawTextWithShadow(client.textRenderer, "XP: " + data.getNexusXp(), x, y, 0xFFFFFF);
		y += 12;
		context.drawTextWithShadow(client.textRenderer, "Energy: " + data.getCurrentNexusEnergy() + " / " + data.getMaxNexusEnergy(), x, y, 0x0080FF);
		y += 12;
		context.drawTextWithShadow(client.textRenderer, "Control: " + (int)data.getNexusControl() + "%", x, y, 0xFFFF00);
		y += 12;
		context.drawTextWithShadow(client.textRenderer, "Overload: " + (int)data.getOverloadPercentage() + "%", x, y, 0xFF4040);
		
		// Draw abilities
		y += 25;
		context.drawTextWithShadow(client.textRenderer, "Abilities:", x, y, 0xFF00FF);
		y += 12;
		
		for (var ability : AbilityRegistry.getAllAbilities().values()) {
			if (data.getNexusLevel() >= ability.getMinimumLevel()) {
				context.drawTextWithShadow(client.textRenderer, "[\u00a7a" + ability.getDisplayName() + "\u00a7r]", x, y, 0xFFFFFF);
			} else {
				context.drawTextWithShadow(client.textRenderer, "[\u00a77" + ability.getDisplayName() + "\u00a7r] (Lv." + ability.getMinimumLevel() + ")", x, y, 0xFF0000);
			}
			y += 12;
		}
		
		super.render(context, mouseX, mouseY, delta);
	}
	
	@Override
	public boolean shouldCloseOnEsc() {
		return true;
	}
}
