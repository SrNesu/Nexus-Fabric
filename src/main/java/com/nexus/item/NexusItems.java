package com.nexus.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import com.nexus.NexusMod;

/**
 * Registry for all Nexus items.
 */
public class NexusItems {
	
	// Nexus Items
	public static final Item NEXUS_SHARD = registerItem("nexus_shard", new Item(new Item.Settings()));
	public static final Item NEXUS_CORE = registerItem("nexus_core", new Item(new Item.Settings()));
	public static final Item ABYSSAL_CRYSTAL = registerItem("abyssal_crystal", new Item(new Item.Settings()));
	public static final Item NEXUS_ARTIFACT = registerItem("nexus_artifact", new Item(new Item.Settings()));
	
	public static void register() {
		// Add items to creative tabs
		ItemGroupEvents.modifyEntriesByTab(ItemGroups.MATERIALS, itemGroup -> {
			itemGroup.add(NEXUS_SHARD);
			itemGroup.add(NEXUS_CORE);
			itemGroup.add(ABYSSAL_CRYSTAL);
			itemGroup.add(NEXUS_ARTIFACT);
		});
	}
	
	private static Item registerItem(String name, Item item) {
		return Registry.register(Registries.ITEM, new Identifier(NexusMod.MOD_ID, name), item);
	}
}
