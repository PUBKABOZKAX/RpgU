package me.udnek.rpgu.effect;

import me.udnek.itemscoreu.customeffect.ConstructableCustomEffect;
import me.udnek.rpgu.attribute.Attributes;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionEffectTypeCategory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class BonusAreaOfEffect extends ConstructableCustomEffect {
    @Override
    public @NotNull PotionEffectTypeCategory getCategory() {return PotionEffectTypeCategory.BENEFICIAL;}

    @Override
    public @Nullable PotionEffectType getVanillaDisguise() {return null;}

    @Override
    public @NotNull String getRawId() {return "bonus_area_of_effect";}

    @Override
    public void getCustomAttributes(@NotNull PotionEffect context, @NotNull CustomAttributeConsumer consumer) {
        consumer.consume(Attributes.AREA_OF_EFFECT, context.getAmplifier() + 1, AttributeModifier.Operation.ADD_NUMBER);
    }
}
