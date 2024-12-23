package me.udnek.rpgu.vanila;

import me.udnek.itemscoreu.customitem.CustomItem;
import me.udnek.itemscoreu.customrecipe.RecipeManager;
import me.udnek.itemscoreu.customrecipe.choice.CustomSingleRecipeChoice;
import me.udnek.rpgu.RpgU;
import me.udnek.rpgu.item.Items;
import me.udnek.rpgu.mechanic.enchanting.EnchantingRecipe;
import me.udnek.rpgu.mechanic.enchanting.upgrade.EnchantingTableUpgrade;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EnchantManaging {
    public static void run(){
        addEnchantingRecipeMaterial(Enchantment.AQUA_AFFINITY, List.of(Material.SEAGRASS));//DONE
        addEnchantingRecipeMaterial(Enchantment.BANE_OF_ARTHROPODS, List.of(Material.STRING));//DONE
        addEnchantingRecipeCustomItem(Enchantment.BLAST_PROTECTION, List.of(Items.BLAST_COAL), Set.of(EnchantingTableUpgrade.DECENT_BOOKSHELF));//DONE
        addEnchantingRecipeMaterial(Enchantment.BREACH, List.of(Material.BREEZE_ROD, Material.IRON_AXE), Set.of(EnchantingTableUpgrade.TRIAL_CHAMBER));//DONE
        addEnchantingRecipeMaterial(Enchantment.CHANNELING, List.of(Material.LIGHTNING_ROD), Set.of(EnchantingTableUpgrade.WATER));//DONE
        addEnchantingRecipeMaterial(Enchantment.BINDING_CURSE, List.of(Material.NETHER_BRICK), Set.of(EnchantingTableUpgrade.LITTLE_BOOKSHELF));//DONE
        addEnchantingRecipeMaterial(Enchantment.VANISHING_CURSE, List.of());//TODO
        addEnchantingRecipeMaterial(Enchantment.DENSITY, List.of(Material.BREEZE_ROD, Material.LADDER), Set.of(EnchantingTableUpgrade.TRIAL_CHAMBER));//DONE
        addEnchantingRecipeMaterial(Enchantment.DEPTH_STRIDER, List.of());//TODO
        addEnchantingRecipeMaterial(Enchantment.EFFICIENCY, List.of(Material.GOLDEN_PICKAXE), Set.of(EnchantingTableUpgrade.LOTS_OF_BOOKSHELF));//DONE
        addEnchantingRecipeMaterial(Enchantment.FEATHER_FALLING, List.of(Material.FEATHER), Set.of(EnchantingTableUpgrade.DECENT_BOOKSHELF));//DONE
        addEnchantingRecipeMaterial(Enchantment.FIRE_ASPECT, List.of(Material.TORCHFLOWER, Material.STICK), Set.of(EnchantingTableUpgrade.LITTLE_BOOKSHELF));//DONE
        addEnchantingRecipeMaterial(Enchantment.FIRE_PROTECTION, List.of(Material.MAGMA_CREAM), Set.of(EnchantingTableUpgrade.DECENT_BOOKSHELF));//DONE
        addEnchantingRecipeMaterial(Enchantment.FLAME, List.of(Material.TORCHFLOWER, Material.FLINT), Set.of(EnchantingTableUpgrade.LITTLE_BOOKSHELF));//DONE
        addEnchantingRecipeMaterial(Enchantment.FORTUNE, List.of(Material.RABBIT_FOOT), Set.of(EnchantingTableUpgrade.LITTLE_BOOKSHELF));//DONE
        addEnchantingRecipeMaterial(Enchantment.FROST_WALKER, List.of(Material.ICE, Material.PACKED_ICE));//DONE
        addEnchantingRecipeMaterial(Enchantment.IMPALING, List.of(Material.KELP), Set.of(EnchantingTableUpgrade.WATER));//DONE
        addEnchantingRecipeMaterial(Enchantment.INFINITY, List.of(Material.WATER_BUCKET, Material.LAVA_BUCKET), Set.of(EnchantingTableUpgrade.LITTLE_BOOKSHELF));//DONE
        addEnchantingRecipeMaterial(Enchantment.KNOCKBACK, List.of(Material.PISTON, Material.STICK));//DONE
        addEnchantingRecipeMaterial(Enchantment.LOOTING, List.of(Material.ENDER_EYE), Set.of(EnchantingTableUpgrade.LITTLE_BOOKSHELF));//DONE
        addEnchantingRecipeCustomItem(Enchantment.LOYALTY, List.of(Items.WOLF_PELT), Set.of(EnchantingTableUpgrade.WATER, EnchantingTableUpgrade.LITTLE_BOOKSHELF));//DONE
        addEnchantingRecipeMaterial(Enchantment.LUCK_OF_THE_SEA, List.of(Material.TROPICAL_FISH));//DONE
        addEnchantingRecipeMaterial(Enchantment.LURE, List.of(Material.PUFFERFISH));//DONE
        addEnchantingRecipeMaterial(Enchantment.MENDING, List.of(Material.NETHER_STAR), Set.of(EnchantingTableUpgrade.LOTS_OF_BOOKSHELF));//DONE
        addEnchantingRecipeMaterial(Enchantment.MULTISHOT, List.of());//TODO
        addEnchantingRecipeMaterial(Enchantment.PIERCING, List.of());//TODO
        addEnchantingRecipeMaterial(Enchantment.POWER, List.of());//TODO
        addEnchantingRecipeMaterial(Enchantment.PROJECTILE_PROTECTION, List.of());//TODO
        addEnchantingRecipeMaterial(Enchantment.PROTECTION, List.of(), Set.of(EnchantingTableUpgrade.LOTS_OF_BOOKSHELF));//TODO
        addEnchantingRecipeMaterial(Enchantment.PUNCH, List.of());//TODO
        addEnchantingRecipeCustomItem(Enchantment.QUICK_CHARGE, List.of(Items.ESOTERIC_SALVE));//DONE
        addEnchantingRecipeMaterial(Enchantment.RESPIRATION, List.of());//TODO
        addEnchantingRecipeMaterial(Enchantment.RIPTIDE, List.of());//TODO
        addEnchantingRecipeMaterial(Enchantment.SHARPNESS, List.of(Material.GRINDSTONE, Material.DIAMOND), Set.of(EnchantingTableUpgrade.LOTS_OF_BOOKSHELF));//DONE
        addEnchantingRecipeMaterial(Enchantment.SILK_TOUCH, List.of());//TODO
        addEnchantingRecipeMaterial(Enchantment.SMITE, List.of(Material.GLISTERING_MELON_SLICE));//TODO
        addEnchantingRecipeMaterial(Enchantment.SOUL_SPEED, List.of(Material.SOUL_SAND), Set.of(EnchantingTableUpgrade.NETHER, EnchantingTableUpgrade.LITTLE_BOOKSHELF));//DONE
        addEnchantingRecipeMaterial(Enchantment.SWEEPING_EDGE, List.of());//TODO
        addEnchantingRecipeMaterial(Enchantment.SWIFT_SNEAK, List.of());//TODO
        addEnchantingRecipeMaterial(Enchantment.THORNS, List.of(Material.IRON_NUGGET, Material.CACTUS), Set.of(EnchantingTableUpgrade.LITTLE_BOOKSHELF));//DONE
        addEnchantingRecipeMaterial(Enchantment.UNBREAKING, List.of(Material.IRON_INGOT, Material.DIAMOND), Set.of(EnchantingTableUpgrade.LOTS_OF_BOOKSHELF));//DONE
        addEnchantingRecipeMaterial(Enchantment.WIND_BURST, List.of());//TODO
    }

    private static void addEnchantingRecipeMaterial(@NotNull Enchantment enchantment, @NotNull List<Material> materials){
        addEnchantingRecipe(enchantment, materials, List.of());
    }

    private static void addEnchantingRecipeMaterial(@NotNull Enchantment enchantment, @NotNull List<Material> materials, @NotNull Set<EnchantingTableUpgrade> tableUpgradeSet){
        addEnchantingRecipe(enchantment, materials, List.of(), tableUpgradeSet);
    }

    private static void addEnchantingRecipeCustomItem(@NotNull Enchantment enchantment, @NotNull List<CustomItem> customItems){
        addEnchantingRecipe(enchantment, List.of(), customItems);
    }

    private static void addEnchantingRecipeCustomItem(@NotNull Enchantment enchantment, @NotNull List<CustomItem> customItems, @NotNull Set<EnchantingTableUpgrade> tableUpgradeSet){
        addEnchantingRecipe(enchantment, List.of(), customItems, tableUpgradeSet);
    }

    private static void addEnchantingRecipe(@NotNull Enchantment enchantment, @NotNull List<Material> materials, @NotNull List<CustomItem> customItems){
        addEnchantingRecipe(enchantment, materials, customItems, Set.of());
    }

    private static void addEnchantingRecipe(@NotNull Enchantment enchantment, @NotNull List<Material> materials, @NotNull List<CustomItem> customItems, @NotNull Set<EnchantingTableUpgrade> tableUpgradeSet){
        List<CustomSingleRecipeChoice> recipeChoices = new ArrayList<>();
        for (Material material : materials){recipeChoices.add(new CustomSingleRecipeChoice(material));}
        for (CustomItem customItem : customItems){recipeChoices.add(new CustomSingleRecipeChoice(customItem));}

        RecipeManager.getInstance().register(
                new EnchantingRecipe(new NamespacedKey(RpgU.getInstance(), enchantment.key().asMinimalString()), enchantment, recipeChoices, tableUpgradeSet)
        );
    }

}
