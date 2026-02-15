package me.udnek.rpgu.item.ingredients.illagerite;

import io.papermc.paper.registry.keys.StructureKeys;
import me.udnek.coreu.custom.component.instance.TranslatableThing;
import me.udnek.coreu.custom.item.ConstructableCustomItem;
import me.udnek.coreu.nms.Nms;
import me.udnek.coreu.nms.loot.condition.LootConditionWrapper;
import me.udnek.coreu.nms.loot.entry.NmsCustomEntry;
import me.udnek.coreu.nms.loot.pool.PoolWrapper;
import me.udnek.coreu.nms.loot.util.ItemStackCreator;
import net.kyori.adventure.key.Key;
import org.bukkit.inventory.Recipe;
import org.bukkit.loot.LootTables;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NullMarked;

import java.util.Set;
import java.util.function.Consumer;

import static org.bukkit.loot.LootTables.*;

@NullMarked
public class IllageriteKey extends ConstructableCustomItem {

    public static LootTables[] ILLAGER_LOOT_TABLES =  new LootTables[]{ PILLAGER, EVOKER, ILLUSIONER, VINDICATOR, WITCH };
    public static Set<Key> ILLAGER_STRUCTURE_KEYS = Set.of(
            StructureKeys.MANSION, StructureKeys.PILLAGER_OUTPOST,  Key.key("nova_structures:mangrove_witch_hut"), Key.key("nova_structures:witch_villa"),
            Key.key("nova_structures:village_swamp"), Key.key("nova_structures:illager_camp"), Key.key("nova_structures:illager_hideout"),
            Key.key("nova_structures:illager_manor"), Key.key("nova_structures:badlands_miner_outpost")
    );

    @Override
    public String getRawId() {return "illagerite_key";
    }
    @Override
    public @Nullable TranslatableThing getTranslations() {return TranslatableThing.ofEngAndRu("Illagerite Key", "Злодеянитовый ключь");}

    @Override
    protected void generateRecipes(Consumer<Recipe> consumer) {
        //TODO
    }

    @Override
    public void globalInitialization() {
        super.globalInitialization();

        for (LootTables illagerLootTable : ILLAGER_LOOT_TABLES) {
            Nms.get().getLootTableWrapper(illagerLootTable.getLootTable()).addPool(new PoolWrapper.Builder(
                    new NmsCustomEntry.Builder(new ItemStackCreator.Custom(this))
                            .addCondition(LootConditionWrapper.randomChange(0.05f))
                            .addCondition(LootConditionWrapper.structure(ILLAGER_STRUCTURE_KEYS))
                            .buildAndWrap())
                    .build());
        }
    }
}
