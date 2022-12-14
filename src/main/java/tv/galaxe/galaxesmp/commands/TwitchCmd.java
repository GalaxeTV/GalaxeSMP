/* (C)2022 GalaxeTV */
package tv.galaxe.galaxesmp.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import tv.galaxe.galaxesmp.GalaxeSMP;
import tv.galaxe.galaxesmp.util.TwitchIntegration;

public class TwitchCmd implements CommandExecutor {

  private GalaxeSMP plugin = null;

  public TwitchCmd(GalaxeSMP plugin) {
    this.plugin = plugin;
  }

  private final String twitchChannel = plugin.getConfig().getString("twitch.channel");

  @Override
  public boolean onCommand(
      @NotNull CommandSender sender,
      @NotNull Command command,
      @NotNull String label,
      @NotNull String[] args) {
    if (sender instanceof Player player) {
      final Component twitchUrl =
          Component.text("https://twitch.tv/" + twitchChannel)
              .clickEvent(ClickEvent.openUrl("https://twitch.tv/" + twitchChannel));
      if (TwitchIntegration.isTwitchStreamLive()) {
        player.sendMessage(
            Component.text(twitchChannel + "'s stream is currently live! Watch now at ")
                .append(twitchUrl));
      } else {
        player.sendMessage(
            Component.text(twitchChannel + "'s  stream is currently offline. Check back later at ")
                .append(twitchUrl));
      }
    } else {
      if (TwitchIntegration.isTwitchStreamLive()) {
        sender.sendMessage(
            twitchChannel
                + "'s Twitch stream is live! Watch now at https://twitch.tv/"
                + twitchChannel);
      } else {
        sender.sendMessage(
            twitchChannel
                + "'s Twitch stream is not live! You can join offline chat at: https://twitch.tv/"
                + twitchChannel);
      }
    }
    return false;
  }
}
