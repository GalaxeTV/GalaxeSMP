/* (C)2022 Galaxe*/
package tv.galaxe.galaxesmp.util;

import com.github.philippheuer.events4j.simple.domain.EventSubscriber;
import com.github.twitch4j.events.ChannelGoLiveEvent;
import com.github.twitch4j.helix.domain.Stream;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import tv.galaxe.galaxesmp.GalaxeSMP;

public class TwitchIntegration {
  private final GalaxeSMP plugin;

  /**
   * Plugin instance from the server
   *
   * @param plugin Sets current instance of plugin
   */
  public TwitchIntegration(GalaxeSMP plugin) {
    this.plugin = plugin;
  }

  /**
   * Gets a going live event from the Twitch API
   *
   * @param event Stream information
   */
  @EventSubscriber
  public void onStreamUp(ChannelGoLiveEvent event) {
    Stream stream = event.getStream();

    final TextComponent streamUrl =
        Component.text("twitch.tv/galaxe")
            .color(TextColor.color(0x9146FF))
            .clickEvent(ClickEvent.openUrl("https://twitch.tv/galaxe"));

    final TextComponent streamTitle =
        Component.text(stream.getTitle(), NamedTextColor.LIGHT_PURPLE);
    final TextComponent announcement =
        Component.text("[Twitch] ")
            .color(TextColor.color(0x9146FF))
            .append(Component.text("AYO GALAXE JUST WENT LIVE: "))
            .append(streamTitle)
            .append(Component.text(" | ONLY AT "))
            .append(streamUrl);

    for (Player p : Bukkit.getOnlinePlayers()) {
      p.sendMessage(announcement);
    }
  }
}
