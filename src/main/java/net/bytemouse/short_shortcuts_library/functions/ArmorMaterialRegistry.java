package net.bytemouse.short_shortcuts_library.functions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ArmorMaterialRegistry {
        private static final List<ArmorMaterialSpec> entries = new ArrayList<>();

        public static void register(String idName, Consumer<ArmorMaterialSpecBuilder> config) {
            ArmorMaterialSpecBuilder builder = new ArmorMaterialSpecBuilder();
            builder.idName = idName;
            config.accept(builder);

            entries.add(builder.build(idName.toUpperCase())); // generate enum name from ID
        }

        public static List<ArmorMaterialSpec> getAll() {
            return entries;
        }
}

