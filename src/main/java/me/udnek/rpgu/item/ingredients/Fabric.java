package me.udnek.rpgu.item.ingredients;

import me.udnek.itemscoreu.customitem.ConstructableCustomItem;
import me.udnek.itemscoreu.nms.Nms;
import me.udnek.itemscoreu.nms.loot.ItemStackCreator;
import me.udnek.itemscoreu.nms.loot.entry.NmsCustomLootEntryBuilder;
import me.udnek.itemscoreu.nms.loot.pool.NmsLootPoolBuilder;
import me.udnek.rpgu.item.Items;
import me.udnek.rpgu.item.RpgUCustomItem;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.loot.LootTables;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class Fabric extends ConstructableCustomItem implements RpgUCustomItem {
    @Override
    public @Nullable Integer getCustomModelData() {return 3108;}
    @Override
    public @NotNull Material getMaterial() {return Material.GUNPOWDER;}
    @Override
    public @NotNull String getRawId() {return "fabric";}
    @Override
    protected void generateRecipes(@NotNull Consumer<@NotNull Recipe> consumer) {
        ShapedRecipe recipe = new ShapedRecipe(this.getRecipeNamespace(0), getItem().add());
        recipe.shape("SWS");

        RecipeChoice.MaterialChoice string = new RecipeChoice.MaterialChoice(Material.STRING);
        RecipeChoice.MaterialChoice wool = new RecipeChoice.MaterialChoice(Tag.WOOL);
        recipe.setIngredient('S', string);
        recipe.setIngredient('W', wool);

        consumer.accept(recipe);
    }
    @Override
    public void afterInitialization() {
        super.afterInitialization();
        NmsLootPoolBuilder lootPoolBuilder = new NmsLootPoolBuilder(
                NmsCustomLootEntryBuilder.fromVanilla(
                        LootTables.BLAZE.getLootTable(),
                        itemStack -> itemStack.getType() == Material.BLAZE_ROD,
                        new ItemStackCreator.Custom(Items.FABRIC)
                )
        );
        Nms.get().addLootPool(
                LootTables.ZOMBIE.getLootTable(),
                lootPoolBuilder
        );
        Nms.get().addLootPool(
                LootTables.HUSK.getLootTable(),
                lootPoolBuilder
        );
        Nms.get().addLootPool(
                LootTables.DROWNED.getLootTable(),
                lootPoolBuilder
        );
    }
}
