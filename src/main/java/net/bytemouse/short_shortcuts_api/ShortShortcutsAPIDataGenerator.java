package net.bytemouse.short_shortcuts_api;

import net.bytemouse.short_shortcuts_api.functions.ArmorMaterialGenerator;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

import java.io.IOException;
import java.nio.file.Path;


public class ShortShortcutsAPIDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {

		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider((output, registriesFuture) -> {
			try {
				Path path = output.getPath().resolve("data").resolve("shortshortcuts");
				ArmorMaterialGenerator.generate(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
            return null;
        });
	}
}

//ArmorMaterialRegistry.register("meteorite", spec -> {
//			spec.durability(35);
//			spec.protection(3, 6, 8, 3);
//			spec.enchantability(20);
//			spec.equipSound("ITEM_ARMOR_EQUIP_DIAMOND");
//			spec.toughness(2.0f);
//			spec.knockbackResistance(0.1f);
//			spec.repairItem("METEORITE_INGOT");
//		});
