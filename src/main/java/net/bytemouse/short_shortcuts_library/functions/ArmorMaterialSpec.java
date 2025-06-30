package net.bytemouse.short_shortcuts_library.functions;

public class ArmorMaterialSpec {

    public final String enumName;
    public final String idName;
    public final int durabilityMultiplier;
    public final int[] protectionAmounts;
    public final int enchantability;
    public final String equipSound;
    public final float toughness;
    public final float knockbackResistance;
    public final String repairIngredient;

    public ArmorMaterialSpec(String enumName, String idName, int durabilityMultiplier, int[] protectionAmounts, int enchantability, String equipSound, float toughness, float knockbackResistance, String repairIngredient) {
        this.enumName = enumName;
        this.idName = idName;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }
}

