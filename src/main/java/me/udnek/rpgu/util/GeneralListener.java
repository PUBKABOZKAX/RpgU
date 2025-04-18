package me.udnek.rpgu.util;

import com.google.gson.JsonParser;
import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.ItemAttributeModifiers;
import me.udnek.itemscoreu.customcomponent.instance.AutoGeneratingFilesItem;
import me.udnek.itemscoreu.customevent.CustomItemGeneratedEvent;
import me.udnek.itemscoreu.customevent.InitializationEvent;
import me.udnek.itemscoreu.customevent.ResourcepackInitializationEvent;
import me.udnek.itemscoreu.customitem.CustomItem;
import me.udnek.itemscoreu.customitem.ItemUtils;
import me.udnek.itemscoreu.customitem.VanillaItemManager;
import me.udnek.itemscoreu.customregistry.InitializationProcess;
import me.udnek.itemscoreu.resourcepack.path.VirtualRpJsonFile;
import me.udnek.itemscoreu.util.SelfRegisteringListener;
import me.udnek.rpgu.RpgU;
import me.udnek.rpgu.component.ComponentTypes;
import me.udnek.rpgu.item.Items;
import me.udnek.rpgu.lore.AttributeLoreGenerator;
import me.udnek.rpgu.vanilla.EnchantManaging;
import me.udnek.rpgu.vanilla.RecipeManaging;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.VillagerAcquireTradeEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Objects;

public class GeneralListener extends SelfRegisteringListener {
    public GeneralListener(JavaPlugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void setBasePlayerHealth(PlayerJoinEvent event){
        double basePlayerHealth = 10;
        Player player = event.getPlayer();
        new BukkitRunnable(){
            @Override
            public void run() {
                AttributeInstance attribute = player.getAttribute(Attribute.MAX_HEALTH);
                double value = Objects.requireNonNull(attribute).getValue();
                if (value != basePlayerHealth) attribute.setBaseValue(basePlayerHealth);
                if (value >= basePlayerHealth) player.setHealth(value);
            }
        }.runTaskLater(RpgU.getInstance(), 5);
    }

    @EventHandler
    public void onResourcepackInitialization(ResourcepackInitializationEvent event){
        String model = """
                {
                	"parent": "rpgu:item/gui/alloying/progress/template",
                	"textures": {
                		"layer0": "rpgu:item/gui/alloying/progress/%lvl%"
                	}
                }""";
        for (int i = 0; i <= 29; i++) {
            event.addFile(new VirtualRpJsonFile(
                    JsonParser.parseString(model.replace("%lvl%", String.valueOf(i))).getAsJsonObject(),
                    AutoGeneratingFilesItem.GENERATED.getModelPath(new NamespacedKey(RpgU.getInstance(), "gui/alloying/progress/"+i))));
        }
        String definition = """
                {
                	"model": {
                		"type": "minecraft:model",
                		"model": "rpgu:item/gui/alloying/progress/%lvl%"
                	}
                }""";
        for (int i = 0; i <= 29; i++) {
            event.addFile(new VirtualRpJsonFile(
                    JsonParser.parseString(definition.replace("%lvl%", String.valueOf(i))).getAsJsonObject(),
                    AutoGeneratingFilesItem.GENERATED.getDefinitionPath(new NamespacedKey(RpgU.getInstance(), "gui/alloying/progress/"+i))));
        }
    }


    @EventHandler(priority = EventPriority.HIGH)
    public void villagerTrades(VillagerAcquireTradeEvent event){
        if (!(event.getEntity() instanceof Villager)) return;
        MerchantRecipe recipe = event.getRecipe();
        Material material = recipe.getResult().getType();
        if (CustomItem.isCustom(recipe.getResult())) return;
        if (ItemUtils.isSameIds(recipe.getResult(), new ItemStack(Material.ENCHANTED_BOOK))) event.setCancelled(true);

        ItemStack itemStack = switch (material) {
            case Material.STONE_AXE -> Items.FLINT_AXE.getItem();
            case Material.STONE_PICKAXE -> Items.FLINT_PICKAXE.getItem();
            case Material.STONE_SHOVEL -> Items.FLINT_SHOVEL.getItem();
            case Material.STONE_HOE -> Items.FLINT_HOE.getItem();
            case Material.STONE_SWORD -> Items.FLINT_SWORD.getItem();

            case Material.DIAMOND_AXE -> Items.FERRUDAM_AXE.getItem();
            case Material.DIAMOND_PICKAXE -> Items.FERRUDAM_PICKAXE.getItem();
            case Material.DIAMOND_SHOVEL -> Items.FERRUDAM_SHOVEL.getItem();
            case Material.DIAMOND_HOE -> Items.FERRUDAM_HOE.getItem();
            case Material.DIAMOND_SWORD -> Items.FERRUDAM_SWORD.getItem();

            case Material.DIAMOND_HELMET -> Items.FERRUDAM_HELMET.getItem();
            case Material.DIAMOND_CHESTPLATE -> Items.FERRUDAM_CHESTPLATE.getItem();
            case Material.DIAMOND_LEGGINGS -> Items.FERRUDAM_LEGGINGS.getItem();
            case Material.DIAMOND_BOOTS -> Items.FERRUDAM_BOOTS.getItem();

            default -> null;
        };
        if (itemStack == null) return;
        event.setRecipe(replaceRecipe(recipe, itemStack));
    }

    public @NotNull MerchantRecipe replaceRecipe(@NotNull MerchantRecipe recipe, @NotNull ItemStack newItem){
        Map<Enchantment, Integer> oldItemEnchants = recipe.getResult().getEnchantments();
        newItem.addEnchantments(oldItemEnchants);
        MerchantRecipe customRecipe = new MerchantRecipe(
                newItem, recipe.getUses(),
                recipe.getMaxUses(),
                recipe.hasExperienceReward(),
                recipe.getVillagerExperience(),
                recipe.getPriceMultiplier(),
                recipe.getDemand(),
                recipe.getSpecialPrice(),
                recipe.shouldIgnoreDiscounts());

        customRecipe.setIngredients(recipe.getIngredients());
        return customRecipe;
    }

    @EventHandler
    public void craftingTableInventoryRename(InventoryOpenEvent event){
        if (event.getInventory().getType() == InventoryType.WORKBENCH &&
            event.getView().title().toString().equals(Component.translatable("container.crafting").toString()))
        {
            event.titleOverride(Component.translatable(Material.CRAFTING_TABLE.translationKey()));
        }

    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void itemGenerates(CustomItemGeneratedEvent event){
        event.getCustomItem().getComponents().getOrDefault(ComponentTypes.ACTIVE_ABILITY_ITEM).getLore(event.getLoreBuilder());
        event.getCustomItem().getComponents().getOrDefault(ComponentTypes.EQUIPPABLE_ITEM).getPassives(component ->
                component.getLore(event.getLoreBuilder()));
        AttributeLoreGenerator.generate(event.getItemStack(), event.getLoreBuilder());
        if (VanillaItemManager.isReplaced(event.getCustomItem())){
            ItemAttributeModifiers attributeModifiers = event.getItemStack().getData(DataComponentTypes.ATTRIBUTE_MODIFIERS);
            if (attributeModifiers == null) return;
            event.getItemStack().setData(DataComponentTypes.ATTRIBUTE_MODIFIERS, attributeModifiers.showInTooltip(false));
        }
    }


    @EventHandler
    public void recipeInitialization(InitializationEvent event){
        if (event.getStep() != InitializationProcess.Step.AFTER_REGISTRIES_INITIALIZATION) return;
        RecipeManaging.run();
        EnchantManaging.run();
    }
}
