package me.udnek.rpgu.mechanic.damaging;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Material;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.tag.DamageTypeTags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DamageUtils {

    public static final int PHYSICAL_DAMAGE_BUFFER = 100_000;
    public static final int PHYSICAL_DAMAGE_PRECISION = 100;
    public static final int MAGICAL_DAMAGE_PRECISION = 100;

    public static double serializeToVanillaDamage(@NotNull Damage damage){
        return (damage.getPhysical() + 1) * PHYSICAL_DAMAGE_PRECISION*PHYSICAL_DAMAGE_BUFFER + damage.getMagical() * MAGICAL_DAMAGE_PRECISION;
    }

    public static double difficultyMultiplier(@Nullable Difficulty difficulty){
        if (difficulty == null) difficulty = Difficulty.NORMAL;
        return switch (difficulty){
            case PEACEFUL, EASY -> 0.8;
            case NORMAL -> 1;
            case HARD -> 1.2;
        };
    }

    public static boolean canDamageThisTick(@NotNull DamageType damageType){
        if (damageType != DamageType.ON_FIRE && (
            DamageTypeTags.IS_FIRE.isTagged(damageType) ||
            damageType == DamageType.CACTUS ||
            damageType == DamageType.SWEET_BERRY_BUSH)
        ) {
          return Bukkit.getCurrentTick() % 15 == 0;
        }
        return true;
    }




    public static void damage(@NotNull LivingEntity target, @NotNull Damage damage){
        target.damage(serializeToVanillaDamage(damage));
    }
    public static void damage(@NotNull LivingEntity target, @NotNull Damage damage, @Nullable Entity source){
        target.damage(serializeToVanillaDamage(damage), source);
    }
    public static void damage(@NotNull LivingEntity target, @NotNull Damage damage, @NotNull DamageSource source){
        target.damage(serializeToVanillaDamage(damage), source);
    }



    public static @NotNull ItemStack getItemInMainHand(@NotNull LivingEntity entity){
        if (entity instanceof Player player){
            return player.getInventory().getItemInMainHand();
        }
        EntityEquipment equipment = entity.getEquipment();
        if (equipment == null) return new ItemStack(Material.AIR);
        return equipment.getItemInMainHand();
    }

    public static Damage.Type getDamageType(@NotNull EntityDamageEvent event){
        return switch (event.getCause()){
            case WORLD_BORDER,
                 VOID,
                 LIGHTNING,
                 POISON,
                 MAGIC,
                 WITHER,
                 THORNS,
                 DRAGON_BREATH,
                 CUSTOM,
                 SONIC_BOOM,
                 KILL,
                 SUICIDE
                    -> Damage.Type.MAGICAL;
            default -> Damage.Type.PHYSICAL;
        };
    }
}
