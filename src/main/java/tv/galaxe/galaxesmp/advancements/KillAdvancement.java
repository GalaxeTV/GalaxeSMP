/* (C)2022 Galaxe */
package tv.galaxe.galaxesmp.advancements;

import java.util.concurrent.ThreadLocalRandom;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import tv.galaxe.galaxesmp.GalaxeSMP;

public class KillAdvancement implements Listener {
  private final GalaxeSMP plugin;

  /***
   * Plugin instance from the server
   *
   * @param plugin Sets current instance of the plugin
   */
  public KillAdvancement(GalaxeSMP plugin) {
    this.plugin = plugin;
  }

  /***
   * Fetches a "Kill" event from the server
   *
   * @param death Player information
   */
  @EventHandler
  public void OnPlayerDeath(PlayerDeathEvent death) {
    final Player player = death.getEntity();
    Player killer = player.getKiller();
    final EntityDamageEvent lastDamageCause = player.getLastDamageCause();
    if (lastDamageCause != null && killer == null) {
      final Entity lastDamageCauseEntity = lastDamageCause.getEntity();

      if (lastDamageCauseEntity instanceof Player) {
        killer = (Player) lastDamageCauseEntity;

      } else if (lastDamageCauseEntity instanceof final Projectile projectile) {

        if (projectile.getShooter() instanceof Player) {
          killer = (Player) projectile.getShooter();
        }
      }
    }

    if (killer != null && player.hasPermission("group.admin")) {
      final Component killerName = killer.playerListName();
      final Component playerName = player.playerListName();

      final ItemStack item = killer.getInventory().getItemInMainHand();
      TextComponent itemName;
      final Sound deathSound =
          Sound.sound(Key.key("minecraft:entity.ender_dragon.death"), Sound.Source.PLAYER, 1, 1);

      if (item.hasItemMeta() && item != null) {
        itemName =
            Component.text("a " + item.getItemMeta().getDisplayName())
                .hoverEvent(item.asHoverEvent());
      } else if (item.getType() == Material.AIR) {
        itemName = Component.text("Fists");
      } else {
        itemName =
            Component.text("a " + item.getType().name().replace("_", " "))
                .hoverEvent(item.asHoverEvent());
      }

      for (Player p : plugin.getServer().getOnlinePlayers()) {
        p.sendMessage(
            Component.text("[KILL] ")
                .decoration(TextDecoration.BOLD, true)
                .color(TextColor.color(0xeb4034))
                .append(killerName)
                .append(Component.text(" just killed "))
                .color(TextColor.color(0x9146FF))
                .append(playerName)
                .append(Component.text(" with "))
                .append(itemName)
                .decoration(TextDecoration.BOLD, true)
                .append(Component.text("!")));
        p.playSound(deathSound);
      }

      int expLevels = ThreadLocalRandom.current().nextInt(20, 31);
      killer.giveExpLevels(expLevels);
      killer.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 999999, 1));
      killer.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 999999, 5));
      killer.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 999999, 5));
    }
  }
}
