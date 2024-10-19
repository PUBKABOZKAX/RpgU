package me.udnek.rpgu.item.ingredients;

import me.udnek.itemscoreu.customitem.ConstructableCustomItem;
import me.udnek.rpgu.item.RpgUCustomItem;
import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class IngotMold extends ConstructableCustomItem implements RpgUCustomItem {
    @Override
    public @NotNull String getRawId() {return "ingot_mold";}
    @Override
    public @NotNull Material getMaterial() {return Material.GUNPOWDER;}
    @Override
    public @Nullable Integer getCustomModelData() {return 3112;}
    @Override
    protected void generateRecipes(@NotNull Consumer<@NotNull Recipe> consumer) {
        ShapedRecipe recipe = new ShapedRecipe(this.getRecipeNamespace(0), getItem());
        recipe.shape(
                "C C",
                "CCC");

        RecipeChoice.MaterialChoice clayBall = new RecipeChoice.MaterialChoice(Material.CLAY_BALL);
        recipe.setIngredient('C', clayBall);

        consumer.accept(recipe);
    }
}
