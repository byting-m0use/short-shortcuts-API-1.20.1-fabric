package net.bytemouse.short_shortcuts_library.functions;

public class ArmorMaterialSpecBuilder {
        public String idName;
        public int durabilityMultiplier;
        public int[] protection = new int[4];
        public int enchantability;
        public String equipSound;
        public float toughness;
        public float knockbackResistance;
        public String repairItem;

        public void durability(int multiplier) {
            this.durabilityMultiplier = multiplier;
        }

        public void protection(int head, int chest, int legs, int boots) {
            this.protection[0] = head;
            this.protection[1] = chest;
            this.protection[2] = legs;
            this.protection[3] = boots;
        }

        public void enchantability(int value) {
            this.enchantability = value;
        }

        public void equipSound(String soundFieldName) {
            this.equipSound = soundFieldName;
        }

        public void toughness(float value) {
            this.toughness = value;
        }

        public void knockbackResistance(float value) {
            this.knockbackResistance = value;
        }

        public void repairItem(String itemFieldName) {
            this.repairItem = itemFieldName;
        }

        public ArmorMaterialSpec build(String enumName) {
            return new ArmorMaterialSpec(
                    enumName,
                    idName,
                    durabilityMultiplier,
                    protection,
                    enchantability,
                    equipSound,
                    toughness,
                    knockbackResistance,
                    repairItem
            );
        }
    }


