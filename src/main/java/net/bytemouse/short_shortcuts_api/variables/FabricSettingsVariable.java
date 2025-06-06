package net.bytemouse.short_shortcuts_api.variables;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;

import java.util.function.Supplier;

public class FabricSettingsVariable {

    public static final Supplier<Item> ITEM_SETTINGS = () -> new Item(new FabricItemSettings());
}
