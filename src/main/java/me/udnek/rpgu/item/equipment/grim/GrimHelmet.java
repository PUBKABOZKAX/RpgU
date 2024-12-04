package me.udnek.rpgu.item.equipment.grim;

import me.udnek.rpgu.item.Items;
import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class GrimHelmet extends GrimArmor {
    @Override
    public @NotNull String getRawId() {return "grim_helmet";}

    @Override
    public @NotNull Material getMaterial() {return Material.DIAMOND_HELMET;}

    @Override
    public @NotNull Stats getStats() {
        return new Stats(2, 1, 0.1, 2, 2);
    }

    @Override
    protected void generateRecipes(@NotNull Consumer<@NotNull Recipe> consumer) {
        ShapedRecipe recipe = new ShapedRecipe(getNewRecipeKey(), getItem());
        recipe.shape(
                "BSB",
                "BHB");

        recipe.setIngredient('B', new RecipeChoice.MaterialChoice(Material.BONE));
        recipe.setIngredient('S', new RecipeChoice.MaterialChoice(Material.SKELETON_SKULL));
        recipe.setIngredient('H', new RecipeChoice.MaterialChoice(Material.HONEYCOMB));

        consumer.accept(recipe);
    }
}