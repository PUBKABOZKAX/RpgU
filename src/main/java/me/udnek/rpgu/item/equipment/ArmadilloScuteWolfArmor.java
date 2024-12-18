package me.udnek.rpgu.item.equipment;

import me.udnek.itemscoreu.customattribute.AttributeUtils;
import me.udnek.itemscoreu.customitem.ConstructableCustomItem;
import me.udnek.rpgu.RpgU;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.EquippableComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class ArmadilloScuteWolfArmor extends ConstructableCustomItem {
    @Override
    public @NotNull String getRawId() {return "armadillo_scute_wolf_armor";}

    @Override
    public @NotNull Material getMaterial() {return Material.GUNPOWDER;}

    @Override
    public void initializeAttributes(@NotNull ItemMeta itemMeta) {
        AttributeUtils.appendAttribute(itemMeta, Attribute.ARMOR, new NamespacedKey(RpgU.getInstance(), "base_armor_" + getRawId()), 11, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.BODY);
    }

    @Override
    public @Nullable ItemFlag[] getTooltipHides() {return new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES};}

    @Override
    public @Nullable Integer getMaxDamage() {return (int) Material.WOLF_ARMOR.getMaxDurability();}

    @Override
    public @Nullable EquippableComponent getEquippable() {
        EquippableComponent equippable = new ItemStack(Material.WOLF_ARMOR).getItemMeta().getEquippable();
        equippable.setSlot(Material.WOLF_ARMOR.getEquipmentSlot());
        equippable.setModel(new NamespacedKey(NamespacedKey.MINECRAFT, "armadillo_scute"));
        return equippable;
    }

    @Override
    public @Nullable String getRawItemName() {return Material.WOLF_ARMOR.translationKey();}
    
    @Override
    protected void generateRecipes(@NotNull Consumer<@NotNull Recipe> consumer) {
        ShapedRecipe recipe = new ShapedRecipe(getNewRecipeKey(), this.getItem());
        recipe.shape(
                "S  ",
                "SSS",
                "S S");

        recipe.setIngredient('S', new RecipeChoice.MaterialChoice(Material.ARMADILLO_SCUTE));

        consumer.accept(recipe);
    }
}
