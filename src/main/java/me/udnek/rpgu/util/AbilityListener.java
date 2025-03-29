package me.udnek.rpgu.util;

import com.destroystokyo.paper.event.player.PlayerReadyArrowEvent;
import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.event.entity.EntityLoadCrossbowEvent;
import io.papermc.paper.event.player.PlayerStopUsingItemEvent;
import me.udnek.itemscoreu.customequipmentslot.universal.BaseUniversalSlot;
import me.udnek.itemscoreu.customequipmentslot.universal.UniversalInventorySlot;
import me.udnek.itemscoreu.customitem.CustomItem;
import me.udnek.itemscoreu.util.SelfRegisteringListener;
import me.udnek.rpgu.component.ComponentTypes;
import me.udnek.rpgu.mechanic.damaging.DamageEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;

public class AbilityListener extends SelfRegisteringListener {
    public AbilityListener(@NotNull Plugin plugin) {super(plugin);}

    @EventHandler
    public void abilityRightClick(PlayerInteractEvent event){
        if (!event.getAction().isRightClick()) return;
        CustomItem.consumeIfCustom(event.getItem(), customItem ->
                customItem.getComponents().getOrDefault(ComponentTypes.ACTIVE_ABILITY_ITEM).onRightClick(customItem, event));
    }

    @EventHandler
    public void abilityStopUsing(PlayerStopUsingItemEvent event){
        CustomItem.consumeIfCustom(event.getItem(), customItem ->
                customItem.getComponents().getOrDefault(ComponentTypes.ACTIVE_ABILITY_ITEM).onStopUsing(customItem, event));
    }

    @EventHandler
    public void abilityConsume(PlayerItemConsumeEvent event){
        CustomItem.consumeIfCustom(event.getItem(), customItem ->
                customItem.getComponents().getOrDefault(ComponentTypes.ACTIVE_ABILITY_ITEM).onConsume(customItem, event));
    }

    @EventHandler
    public void entityResurrect(EntityResurrectEvent event){
        AtomicBoolean activatedBefore = new AtomicBoolean(false);
        BiConsumer<BaseUniversalSlot, ItemStack> consumer = (slot, itemStack) ->  {
            CustomItem.consumeIfCustom(itemStack, customItem ->
                     customItem.getComponents().getOrDefault(ComponentTypes.EQUIPPABLE_ITEM).getPassives(passive -> {
                             if (!passive.getSlot().intersects(slot)) return;
                             passive.onResurrect(customItem, slot, activatedBefore.get(), event);
                     }));
            if (!(event.isCancelled())) activatedBefore.set(true);
        };

        UniversalInventorySlot.iterateThroughNotEmpty(consumer, event.getEntity());
    }

    @EventHandler
    public void onDamage(DamageEvent event){
        Entity damager = event.getDamageInstance().getDamager();
        if (!(damager instanceof LivingEntity livingEntity)) return;
        BiConsumer<BaseUniversalSlot, ItemStack> consumer = (slot, itemStack) ->
                CustomItem.consumeIfCustom(itemStack, customItem ->
                        customItem.getComponents().getOrDefault(ComponentTypes.EQUIPPABLE_ITEM).getPassives(passive -> {
                            if (!passive.getSlot().intersects(slot)) return;
                            passive.onDamage(customItem, slot, event);
                        }));

        UniversalInventorySlot.iterateThroughNotEmpty(consumer, livingEntity);
    }

    @EventHandler
    public void playerDeath(PlayerDeathEvent event){
        BiConsumer<BaseUniversalSlot, ItemStack> consumer = (slot, itemStack) ->
                CustomItem.consumeIfCustom(itemStack, customItem ->
                        customItem.getComponents().getOrDefault(ComponentTypes.EQUIPPABLE_ITEM).getPassives(passive -> {
                            if (!passive.getSlot().intersects(slot)) return;
                            passive.onDeath(customItem, slot, event);
                        }));

        UniversalInventorySlot.iterateThroughNotEmpty(consumer, event.getEntity());
    }

    @EventHandler
    public void bowShoot(EntityShootBowEvent event){
        BiConsumer<BaseUniversalSlot, ItemStack> consumer = (slot, itemStack) ->
            CustomItem.consumeIfCustom(itemStack, customItem ->
                        customItem.getComponents().getOrDefault(ComponentTypes.EQUIPPABLE_ITEM).getPassives(passive -> {
                            if (!passive.getSlot().intersects(slot)) return;
                            passive.onFire(customItem, slot, event);
                        }));

        UniversalInventorySlot.iterateThroughNotEmpty(consumer, event.getEntity());
    }

    @EventHandler
    public void arrowChoose(PlayerReadyArrowEvent event){
        BiConsumer<BaseUniversalSlot, ItemStack> consumer = (slot, itemStack) ->
                CustomItem.consumeIfCustom(itemStack, customItem ->
                    customItem.getComponents().getOrDefault(ComponentTypes.EQUIPPABLE_ITEM).getPassives(passive -> {
                        if (!passive.getSlot().intersects(slot)) return;
                        passive.onChooseArrow(customItem, slot, event);
                    }));

        UniversalInventorySlot.iterateThroughNotEmpty(consumer, event.getPlayer());
    }

    @EventHandler
    public void arrowLoad(EntityLoadCrossbowEvent event){
        BiConsumer<BaseUniversalSlot, ItemStack> consumer = (slot, itemStack) ->
                CustomItem.consumeIfCustom(itemStack, customItem ->
                        customItem.getComponents().getOrDefault(ComponentTypes.EQUIPPABLE_ITEM).getPassives(passive -> {
                            if (!passive.getSlot().intersects(slot)) return;
                            passive.onLoadToCrossbow(customItem, slot, event);
                        }));

        UniversalInventorySlot.iterateThroughNotEmpty(consumer, event.getEntity());
    }

    @EventHandler
    public void abilityGlide(EntityToggleGlideEvent event){
        if (!(event.isGliding())) return;
        if (!(event.getEntity() instanceof LivingEntity livingEntity)) return;
        ItemStack item = null;
        EntityEquipment equipment = livingEntity.getEquipment();
        if (equipment == null) return;
        for (ItemStack armorContent : equipment.getArmorContents()) {
            if (armorContent == null) continue;
            if (armorContent.hasData(DataComponentTypes.GLIDER)){
                item = armorContent;
                break;
            }
        }
        if (item == null) return;
        CustomItem.consumeIfCustom(item, customItem ->
                customItem.getComponents().getOrDefault(ComponentTypes.EQUIPPABLE_ITEM).getPassives(passive ->
                        passive.onGlide(customItem, event)));
    }
}
