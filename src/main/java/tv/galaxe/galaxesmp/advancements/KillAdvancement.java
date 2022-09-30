/* (C)2022 Galaxe */
package tv.galaxe.galaxesmp.advancements;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
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
    Player player = death.getPlayer();
    Player killer = player.getKiller();

    if (killer != null && player.hasPermission("group.admin")) {
      TextComponent item =
          Component.text(killer.getActiveItem().getItemMeta().displayName().toString())
              .hoverEvent(killer.getActiveItem());

      final TextComponent killAnnouncement =
          Component.text("[KILL] ")
              .color(TextColor.color(0x9146FF))
              .append(
                  Component.text(
                      killer.getName()
                          + " just vanquished "
                          + player.getName()
                          + "with "
                          + item
                          + "!"));
      for (Player p : Bukkit.getOnlinePlayers()) {
        p.sendMessage(killAnnouncement);
      }

      killer.giveExpLevels(30);
      killer.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 100, 1));
      killer.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 500, 5));
      killer.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 500, 5));
    }
  }
}
