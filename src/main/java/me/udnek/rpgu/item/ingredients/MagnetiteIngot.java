package me.udnek.rpgu.item.ingredients;

import me.udnek.itemscoreu.customitem.ConstructableCustomItem;
import me.udnek.rpgu.item.Items;
import me.udnek.rpgu.item.RpgUCustomItem;
import org.bukkit.Material;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class MagnetiteIngot extends ConstructableCustomItem implements RpgUCustomItem {
    @Override
    public @NotNull String getRawId() {return "magnetite_ingot";}
    @Override
    public @NotNull Material getMaterial() {return Material.GUNPOWDER;}

    @Override
    protected void generateRecipes(@NotNull Consumer<@NotNull Recipe> consumer) {
        RecipeChoice.ExactChoice rawMagnetite = new RecipeChoice.ExactChoice(Items.RAW_MAGNETITE.getItem());

        FurnaceRecipe recipe = new FurnaceRecipe(
                getNewRecipeKey(),
                getItem(),
                rawMagnetite,
                 0.7f,
                 200
        );

        consumer.accept(recipe);
    }
}
