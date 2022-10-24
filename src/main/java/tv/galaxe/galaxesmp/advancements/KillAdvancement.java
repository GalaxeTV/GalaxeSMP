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
   * Handles item metas of a player's currently held hand
   *
   * @param item Item to be checked
   * @return Returns a text component of a formatted item name with associated events
   */
  private TextComponent itemMetaCheck(ItemStack item) {
    if (item.hasItemMeta() && item != null) {
      return Component.text("a " + item.getItemMeta().getDisplayName())
              .hoverEvent(item.asHoverEvent());
    } else if (item.getType() == Material.AIR) {
      return Component.text("Fists");
    } else {
      return Component.text("a " + item.getType().name().replace("_", " "))
              .hoverEvent(item.asHoverEvent());
    }
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

    // Checks to ensure the player was killed by a player, regardless of item, weapon, or entity
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

    // Check if player killed a staff member and if the killer is not null
    if (killer != null && player.hasPermission("group.admin")) {
      // Set names of killer and killed player
      final Component killerName = killer.playerListName();
      final Component playerName = player.playerListName();

      // Gets item in main hand of killer
      final ItemStack item = killer.getInventory().getItemInMainHand();

      // Gets the item name of the killer's main hand
      TextComponent itemName = itemMetaCheck(item);
      final Sound deathSound =
          Sound.sound(Key.key("minecraft:entity.ender_dragon.death"), Sound.Source.PLAYER, 1, 1);

      // Sends a message to everyone on the server that a staff member was killed and plays a death sound
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

      // Sets a random value between 20 and 30 to give experience levels to the killer
      int expLevels = ThreadLocalRandom.current().nextInt(20, 31);
      killer.giveExpLevels(expLevels);

      // Adds multiple potion effects to the killer
      killer.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 72000, 1));
      killer.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 72000, 5));
      killer.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 72000, 5));
    }
  }
}
