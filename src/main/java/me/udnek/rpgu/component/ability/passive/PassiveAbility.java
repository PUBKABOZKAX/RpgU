package me.udnek.rpgu.component.ability.passive;

import me.udnek.itemscoreu.customcomponent.ComponentHolder;
import me.udnek.itemscoreu.customequipmentslot.slot.CustomEquipmentSlot;
import me.udnek.itemscoreu.customequipmentslot.slot.SingleSlot;
import me.udnek.itemscoreu.customequipmentslot.universal.UniversalInventorySlot;
import me.udnek.itemscoreu.customitem.CustomItem;
import me.udnek.itemscoreu.util.LoreBuilder;
import me.udnek.rpgu.component.ability.AbilityComponent;
import me.udnek.rpgu.mechanic.damaging.DamageEvent;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.jetbrains.annotations.NotNull;

public interface PassiveAbility extends ComponentHolder<AbilityComponent<?>> {
    @NotNull CustomEquipmentSlot getSlot();

    default void getLore(@NotNull LoreBuilder loreBuilder) {}

    default void onResurrect(@NotNull CustomItem customItem, @NotNull UniversalInventorySlot slot, boolean activatedBefore,
                             @NotNull EntityResurrectEvent event){
        if (!activatedBefore) event.setCancelled(true);
    }
    default void tick(@NotNull CustomItem customItem, @NotNull LivingEntity livingEntity, @NotNull SingleSlot slot){}
    default void onGlide(@NotNull CustomItem customItem, @NotNull EntityToggleGlideEvent event){}
    default void onDeath(@NotNull CustomItem customItem, @NotNull UniversalInventorySlot slot, @NotNull PlayerDeathEvent event){}
    default void onDamage(@NotNull CustomItem customItem, @NotNull UniversalInventorySlot slot, @NotNull DamageEvent event){}
}
