package me.udnek.rpgu.attribute;

import me.udnek.itemscoreu.customattribute.AttributeUtils;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RpgUAttributeUtils {

    public static double attributeAttackSpeedToAttacksPerSecond(double attackSpeed){
        return (4+attackSpeed);
    }

    public static void addSuitableAttribute(ItemStack itemStack, Attribute attribute, NamespacedKey id, double amount){
        ItemMeta itemMeta = itemStack.getItemMeta();
        switch (itemStack.getType().getEquipmentSlot()) {
            case HAND:
            case OFF_HAND:
                AttributeUtils.appendAttribute(itemMeta, attribute, id, amount, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND);
                break;
            default:
                switch (attribute) {
                    case GENERIC_ATTACK_SPEED:
                    case GENERIC_ATTACK_DAMAGE:
                    case GENERIC_MOVEMENT_SPEED:
                        AttributeUtils.appendAttribute(itemMeta, attribute, id, amount, AttributeModifier.Operation.MULTIPLY_SCALAR_1, itemStack.getType().getEquipmentSlot().getGroup());
                        break;
                    default:
                        AttributeUtils.appendAttribute(itemMeta, attribute, id, amount, AttributeModifier.Operation.ADD_NUMBER, itemStack.getType().getEquipmentSlot().getGroup());
                        break;
                }
        }
        itemStack.setItemMeta(itemMeta);
    }
}



















