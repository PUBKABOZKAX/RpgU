package me.udnek.rpgu.item.equipment;

import me.udnek.itemscoreu.customattribute.AttributeUtils;
import me.udnek.itemscoreu.customattribute.CustomAttributeModifier;
import me.udnek.itemscoreu.customattribute.CustomAttributesContainer;
import me.udnek.itemscoreu.customcomponent.instance.CustomItemAttributesComponent;
import me.udnek.itemscoreu.customequipmentslot.CustomEquipmentSlot;
import me.udnek.itemscoreu.customitem.ConstructableCustomItem;
import me.udnek.itemscoreu.customitem.RepairData;
import me.udnek.rpgu.RpgU;
import me.udnek.rpgu.attribute.Attributes;
import me.udnek.rpgu.item.Items;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.EquippableComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.function.Consumer;

public class EvocationRobe extends ConstructableCustomItem {
    @Override
    public @NotNull String getRawId() {return "evocation_robe";}

    @Override
    public @Nullable ItemRarity getRarity() {
        return ItemRarity.UNCOMMON;
    }

    @Override
    public @NotNull Material getMaterial() {return Material.DIAMOND_CHESTPLATE;}

    @Override
    public @Nullable ItemFlag[] getTooltipHides() {return new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES};}

    @Override
    public @Nullable EquippableComponent getEquippable() {
        EquippableComponent equippable = new ItemStack(Material.DIAMOND_CHESTPLATE).getItemMeta().getEquippable();
        equippable.setModel(new NamespacedKey(RpgU.getInstance(), "evocation_robe"));
        equippable.setSlot(EquipmentSlot.CHEST);
        return equippable;
    }

    @Override
    protected void generateRecipes(@NotNull Consumer<@NotNull Recipe> consumer) {
        ShapedRecipe recipe = new ShapedRecipe(getNewRecipeKey(), getItem());
        recipe.shape(
                "IEI",
                "FLF",
                "FTF");

        recipe.setIngredient('E', new RecipeChoice.MaterialChoice(Material.ENDER_EYE));
        recipe.setIngredient('I', new RecipeChoice.MaterialChoice(Material.IRON_INGOT));
        recipe.setIngredient('F', new RecipeChoice.ExactChoice(Items.FABRIC.getItem()));
        recipe.setIngredient('T', new RecipeChoice.MaterialChoice(Material.TOTEM_OF_UNDYING));
        recipe.setIngredient('L', new RecipeChoice.MaterialChoice(Material.LEATHER_CHESTPLATE));

        consumer.accept(recipe);
    }

    @Override
    public @Nullable RepairData getRepairData() {
        return new RepairData(Set.of(Items.FABRIC), Set.of(Material.IRON_INGOT));
    }

    @Override
    public void initializeComponents() {
        super.initializeComponents();
        getComponents().set(new CustomItemAttributesComponent(new CustomAttributesContainer.Builder()
                .add(Attributes.MAGICAL_POTENTIAL, new CustomAttributeModifier(5,  AttributeModifier.Operation.ADD_NUMBER, CustomEquipmentSlot.CHEST))
                .add(Attributes.COOLDOWN_TIME, new CustomAttributeModifier(-0.3,  AttributeModifier.Operation.MULTIPLY_SCALAR_1, CustomEquipmentSlot.CHEST))
                .add(Attributes.CAST_RANGE, new CustomAttributeModifier(0.5,  AttributeModifier.Operation.ADD_SCALAR, CustomEquipmentSlot.CHEST))
        .build()));
    }

    @Override
    public void initializeAttributes(@NotNull ItemMeta itemMeta) {
        super.initializeAttributes(itemMeta);
        AttributeUtils.addAttribute(itemMeta, Attribute.MAX_HEALTH, new NamespacedKey(RpgU.getInstance(), "chestplate_max_health"), 2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST);
        AttributeUtils.addAttribute(itemMeta, Attribute.ARMOR, new NamespacedKey(RpgU.getInstance(), "chestplate_armor"), 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST);
    }
}
