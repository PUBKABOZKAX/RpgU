package me.udnek.rpgu.component.ability.property.function;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public interface PropertyFunction<Context, Value> extends Function<Context, Value> {
    @NotNull Value getBase();
    @Override
    @NotNull Value apply(@NotNull Context context);
    @NotNull default Component describe(){
        return describeWithModifier(Function.identity());
    }
    boolean isConstant();
    boolean isZeroConstant();
    @NotNull Component describeWithModifier(@NotNull Function<Double, Double> modifier);
}
