/*
package me.udnek.rpgu.item.equipment;

import me.udnek.itemscoreu.customitem.ConstructableCustomItem;
import me.udnek.itemscoreu.util.RightClickable;
import me.udnek.rpgu.item.RpgUCustomItem;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.List;

public class CopperPickaxe extends ConstructableCustomItem implements RightClickable, RpgUCustomItem {


    @Override
    public Material getMaterial() {
        return Material.GOLDEN_PICKAXE;
    }

    @Override
    public String getRawId() {
        return "copper_pickaxe";
    }

    @Override
    public void onRightClicks(PlayerInteractEvent event) {
        ItemStack eventItem = event.getItem();
        ItemMeta itemMeta = eventItem.getItemMeta();
        itemMeta.setCustomModelData(itemMeta.getCustomModelData() == 3100 ? 3101 : 3100);
        eventItem.setItemMeta(itemMeta);
        event.getPlayer().getInventory().setItem(event.getHand(), eventItem);
    }

    @Override
    protected List<Recipe> generateRecipes() {
        ShapedRecipe recipe = new ShapedRecipe(getNewRecipeKey(), this.getItem());
        recipe.shape(
                "CCC",
                " S ",
                " S ");

        recipe.setIngredient('C', new RecipeChoice.MaterialChoice(Material.COPPER_INGOT));
        recipe.setIngredient('S', new RecipeChoice.MaterialChoice(Material.STICK));

        return Collections.singletonList(recipe);
    }
}
*/
