package me.udnek.rpgu.lore;

import com.google.common.base.Preconditions;
import me.udnek.itemscoreu.customitem.ConstructableCustomItem;
import me.udnek.itemscoreu.customitem.CustomItem;
import me.udnek.itemscoreu.customitem.CustomItemProperties;
import me.udnek.itemscoreu.util.LoreBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ActiveAbilityLorePart implements LoreBuilder.Componentable {

    boolean addEmptyAboveHeader = false;
    @Nullable Component header;
    List<@NotNull Component> data = new ArrayList<>();

    @Override
    public void toComponents(@NotNull Consumer<Component> consumer) {
        if (isEmpty()) return;
        consumer.accept(Component.empty());
        if (header != null) consumer.accept(header);
        data.forEach(consumer);
    }

    public void setHeader(@NotNull Component component){header = component;}
    public void addEmptyAboveHeader(){addEmptyAboveHeader = true;}


    public void addFullAbilityDescription(@NotNull ConstructableCustomItem customItem, int linesAmount){
        for (int i = 0; i < linesAmount; i++) addAbilityDescription(customItem, i);
    }
    public void addAbilityDescription(@NotNull ConstructableCustomItem customItem, int line){
        Preconditions.checkArgument(customItem.getRawItemName() != null, "CustomItem raw name can not be null!");
        addAbilityDescription(customItem.getRawItemName(), line);
    }
    public void addAbilityDescription(@NotNull String rawItemName, int line){
       addWithFormat(Component.translatable(rawItemName + ".ability." + line));
    }

    public void addWithFormat(@NotNull Component component){
        add(component.color(NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
    }

    @Override
    public void add(@NotNull Component component) {
        data.add(Component.translatable(TranslationKeys.equipmentDescriptionLine, component));
    }
    @Override
    public void addFirst(@NotNull Component component) {
        data.addFirst(Component.translatable(TranslationKeys.equipmentDescriptionLine, component));
    }

    @Override
    public boolean isEmpty() {return data.isEmpty() && header == null;}

}
