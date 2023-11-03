package tv.galaxe.smp.advancement;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public final class StaffKill implements Listener {
    Player killer, victim;
    Component murderWeapon;

    // Declare potion effects to be given
    Collection<PotionEffect> buffSet = Stream.of(
        new PotionEffect(PotionEffectType.GLOWING, PotionEffect.INFINITE_DURATION, 0),
        new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, PotionEffect.INFINITE_DURATION, 2),
        new PotionEffect(PotionEffectType.INCREASE_DAMAGE, PotionEffect.INFINITE_DURATION, 2)
        ).collect(Collectors.toCollection(HashSet::new));

    @EventHandler
    public void onStaffKill(PlayerDeathEvent event) {
        if (((victim = event.getEntity()).hasPermission("galaxesmp.staff")) // Victim must have permission
        && (killer = event.getEntity().getKiller()) != null) { // Killer must be player

            murderWeapon = // Define 'murderWeapon' using shorthand if
                (killer.getInventory().getItemInMainHand().getType().equals(Material.AIR)) ?
                Component.text().append(Component.text("[Fists]")).asComponent() :
                killer.getInventory().getItemInMainHand().displayName();

            // Play ender dragon death sound
            event.setDeathSound(Sound.ENTITY_ENDER_DRAGON_DEATH);

            // Build death message
            event.deathMessage(Component.text()
                .append(Component.text("[STAFF KILL] ").color(TextColor.color(0xeb4034)).decoration(TextDecoration.BOLD, true))
                .append(killer.displayName().color(TextColor.color(0x9146FF)).decoration(TextDecoration.BOLD, true))
                .append(Component.text(" just killed ").color(TextColor.color(0xeb4034)))
                .append(victim.displayName().color(TextColor.color(0x9146FF)).decoration(TextDecoration.BOLD, true))
                .append(Component.text(" with ").color(TextColor.color(0xeb4034)))
                .append(murderWeapon)
                .append(Component.text("!").color(TextColor.color(0xeb4034)))
                .asComponent()
            );

            // Reward player with experience and potion effects for current life
            killer.giveExpLevels(30);
            killer.addPotionEffects(buffSet);
        }
    }
}
