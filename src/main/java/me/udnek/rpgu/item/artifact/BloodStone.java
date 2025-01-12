package me.udnek.rpgu.item.artifact;

import me.udnek.itemscoreu.customattribute.CustomAttributeModifier;
import me.udnek.itemscoreu.customattribute.CustomAttributesContainer;
import me.udnek.itemscoreu.customcomponent.instance.CustomItemAttributesComponent;
import me.udnek.itemscoreu.customitem.ConstructableCustomItem;
import me.udnek.jeiu.component.HiddenItemComponent;
import me.udnek.rpgu.attribute.Attributes;
import me.udnek.rpgu.equipment.slot.EquipmentSlots;
import org.bukkit.attribute.AttributeModifier;
import org.jetbrains.annotations.NotNull;

public class BloodStone extends ConstructableCustomItem {
    @Override
    public @NotNull String getRawId() {return "blood_stone";}


    @Override
    public void initializeComponents() {
        super.initializeComponents();

        getComponents().set(HiddenItemComponent.INSTANCE);
        CustomAttributeModifier attribute = new CustomAttributeModifier(1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlots.ARTIFACTS);
        getComponents().set(new CustomItemAttributesComponent(new CustomAttributesContainer.Builder().add(Attributes.AREA_OF_EFFECT, attribute).build()));
    }
}
