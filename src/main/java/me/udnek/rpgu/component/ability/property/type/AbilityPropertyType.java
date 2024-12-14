package me.udnek.rpgu.component.ability.property.type;

import me.udnek.itemscoreu.customcomponent.ConstructableComponentType;
import me.udnek.itemscoreu.customcomponent.CustomComponentType;
import me.udnek.rpgu.component.ability.AbilityComponent;
import me.udnek.rpgu.component.ability.property.AbilityProperty;
import org.jetbrains.annotations.NotNull;

public interface AbilityPropertyType<Component extends AbilityProperty<?, ?>> extends CustomComponentType<AbilityComponent<?>, Component> {
}
