package me.udnek.rpgu.item.ingredients;

import me.udnek.itemscoreu.customitem.ConstructableCustomItem;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapelessRecipe;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class BlastCoal extends ConstructableCustomItem {
    @Override
    public @NotNull String getRawId() {return "blast_coal";}

    @Override
    protected void generateRecipes(@NotNull Consumer<@NotNull Recipe> consumer) {
        ShapelessRecipe recipe = new ShapelessRecipe(getNewRecipeKey(), getItem().add(2));

        RecipeChoice.MaterialChoice coal = new RecipeChoice.MaterialChoice(Tag.ITEMS_COALS);
        RecipeChoice.MaterialChoice gunpowder = new RecipeChoice.MaterialChoice(Material.GUNPOWDER);
        recipe.addIngredient(coal);
        recipe.addIngredient(coal);
        recipe.addIngredient(coal);
        recipe.addIngredient(gunpowder);
        recipe.addIngredient(gunpowder);

        consumer.accept(recipe);
    }
}
