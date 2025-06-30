package net.bytemouse.short_shortcuts_api.functions;


import com.squareup.javapoet.*;
import net.minecraft.item.ArmorItem;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class ArmorMaterialGenerator {

    public static void generate(Path outputDir) throws IOException {
        List<ArmorMaterialSpec> specs = ArmorMaterialRegistry.getAll();

        TypeSpec.Builder enumBuilder = TypeSpec.enumBuilder("GeneratedArmorMaterial")
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(ClassName.get("net.minecraft.item", "ArmorMaterial"))
                .addField(FieldSpec.builder(ArrayTypeName.of(TypeName.INT), "BASE_DURABILITY")
                        .addModifiers(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                        .initializer("new int[]{11, 16, 15, 13}")
                        .build())
                .addField(String.class, "name", Modifier.PRIVATE, Modifier.FINAL)
                .addField(int.class, "durabilityMultiplier", Modifier.PRIVATE, Modifier.FINAL)
                .addField(ArrayTypeName.of(TypeName.INT), "protectionAmounts", Modifier.PRIVATE, Modifier.FINAL)
                .addField(int.class, "enchantability", Modifier.PRIVATE, Modifier.FINAL)
                .addField(ClassName.get("net.minecraft.sound", "SoundEvent"), "equipSound", Modifier.PRIVATE, Modifier.FINAL)
                .addField(float.class, "toughness", Modifier.PRIVATE, Modifier.FINAL)
                .addField(float.class, "knockbackResistance", Modifier.PRIVATE, Modifier.FINAL)
                .addField(ParameterizedTypeName.get(ClassName.get("java.util.function", "Supplier"),
                        ClassName.get("net.minecraft.recipe", "Ingredient")), "repairIngredient", Modifier.PRIVATE, Modifier.FINAL);

        enumBuilder.addMethod(MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PRIVATE)
                .addParameters(List.of(
                        ParameterSpec.builder(String.class, "name").build(),
                        ParameterSpec.builder(int.class, "durabilityMultiplier").build(),
                        ParameterSpec.builder(ArrayTypeName.of(TypeName.INT), "protectionAmounts").build(),
                        ParameterSpec.builder(int.class, "enchantability").build(),
                        ParameterSpec.builder(ClassName.get("net.minecraft.sound", "SoundEvent"), "equipSound").build(),
                        ParameterSpec.builder(float.class, "toughness").build(),
                        ParameterSpec.builder(float.class, "knockbackResistance").build(),
                        ParameterSpec.builder(ParameterizedTypeName.get(ClassName.get("java.util.function", "Supplier"),
                                ClassName.get("net.minecraft.recipe", "Ingredient")), "repairIngredient").build()))
                .addCode("this.name = name;\n" +
                        "this.durabilityMultiplier = durabilityMultiplier;\n" +
                        "this.protectionAmounts = protectionAmounts;\n" +
                        "this.enchantability = enchantability;\n" +
                        "this.equipSound = equipSound;\n" +
                        "this.toughness = toughness;\n" +
                        "this.knockbackResistance = knockbackResistance;\n" +
                        "this.repairIngredient = repairIngredient;")
                .build());

        for (ArmorMaterialSpec spec : specs) {
            enumBuilder.addEnumConstant(spec.enumName, TypeSpec.anonymousClassBuilder(
                    "$S, $L, new int[]{$L, $L, $L, $L}, $L, $T.$L, $Lf, $Lf, () -> $T.ofItems($T.$L)",
                    spec.idName, spec.durabilityMultiplier,
                    spec.protectionAmounts[0], spec.protectionAmounts[1], spec.protectionAmounts[2], spec.protectionAmounts[3],
                    spec.enchantability,
                    ClassName.get("net.minecraft.sound", "SoundEvents"), spec.equipSound,
                    spec.toughness, spec.knockbackResistance,
                    ClassName.get("net.minecraft.recipe", "Ingredient"),
                    ClassName.get("com.example.mod", "ModItems"), spec.repairIngredient
            ).build());
        }

        enumBuilder.addMethod(MethodSpec.methodBuilder("getDurability")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .returns(int.class)
                .addParameter(ClassName.get(ArmorItem.Type.class), "type")
                .addCode("return BASE_DURABILITY[type.ordinal()] * this.durabilityMultiplier;")
                .build());

        enumBuilder.addMethod(MethodSpec.methodBuilder("getProtection")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .returns(int.class)
                .addParameter(ClassName.get(ArmorItem.Type.class), "type")
                .addCode("return this.protectionAmounts[type.ordinal()];")
                .build());

        enumBuilder.addMethod(MethodSpec.methodBuilder("getEnchantability")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .returns(int.class)
                .addCode("return this.enchantability;")
                .build());

        enumBuilder.addMethod(MethodSpec.methodBuilder("getEquipSound")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .returns(ClassName.get("net.minecraft.sound", "SoundEvent"))
                .addCode("return this.equipSound;")
                .build());

        enumBuilder.addMethod(MethodSpec.methodBuilder("getRepairIngredient")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .returns(ClassName.get("net.minecraft.recipe", "Ingredient"))
                .addCode("return this.repairIngredient.get();")
                .build());

        enumBuilder.addMethod(MethodSpec.methodBuilder("getName")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .returns(String.class)
                .addCode("return \"modid:\" + this.name;") // You might want to allow dynamic mod ID
                .build());

        enumBuilder.addMethod(MethodSpec.methodBuilder("getToughness")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .returns(float.class)
                .addCode("return this.toughness;")
                .build());

        enumBuilder.addMethod(MethodSpec.methodBuilder("getKnockbackResistance")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .returns(float.class)
                .addCode("return this.knockbackResistance;")
                .build());

        JavaFile javaFile = JavaFile.builder("com.example.generated", enumBuilder.build())
                .build();

        javaFile.writeTo(outputDir);
    }
}

