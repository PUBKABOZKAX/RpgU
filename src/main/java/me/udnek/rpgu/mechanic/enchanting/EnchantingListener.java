package me.udnek.rpgu.mechanic.enchanting;

import io.papermc.paper.datacomponent.DataComponentTypes;
import me.udnek.itemscoreu.customenchantment.NmsEnchantmentContainer;
import me.udnek.itemscoreu.customevent.ResourcepackInitializationEvent;
import me.udnek.itemscoreu.nms.Nms;
import me.udnek.itemscoreu.util.SelfRegisteringListener;
import me.udnek.rpgu.RpgU;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.inventory.BlockInventoryHolder;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class EnchantingListener extends SelfRegisteringListener {
    public EnchantingListener(JavaPlugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void onPlayerOpensInventory(InventoryOpenEvent event){
        if (event.getInventory().getType() != InventoryType.ENCHANTING) return;
        InventoryHolder holder = event.getInventory().getHolder();
        if (holder instanceof BlockInventoryHolder blockInventoryHolder && blockInventoryHolder.getBlock().getType() == Material.ENCHANTING_TABLE){
            event.setCancelled(true);
            new EnchantingTableInventory(blockInventoryHolder.getBlock().getLocation()).open((Player) event.getPlayer());
        }
    }

    @EventHandler
    public void onAnvil(PrepareAnvilEvent event){
        ItemStack result = event.getResult();
        if (result == null) return;
        result.resetData(DataComponentTypes.REPAIR_COST);
        event.setResult(result);
    }

    @EventHandler
    public void onLoad(ServerLoadEvent event){
        NmsEnchantmentContainer enchantment = Nms.get().getEnchantment(Enchantment.PROTECTION);
        enchantment.clearEffects();
        enchantment.addEffect(
                new NamespacedKey(RpgU.getInstance(), "enchantment.protection"),
                Attribute.ARMOR,
                1/4f, 1/4f,
                AttributeModifier.Operation.ADD_NUMBER
        );

        enchantment = Nms.get().getEnchantment(Enchantment.SHARPNESS);
        enchantment.clearEffects();
        enchantment.addEffect(
                new NamespacedKey(RpgU.getInstance(), "enchantment.sharpness"),
                Attribute.ATTACK_DAMAGE,
                1f, 0.5f,
                AttributeModifier.Operation.ADD_NUMBER
        );
    }
}
