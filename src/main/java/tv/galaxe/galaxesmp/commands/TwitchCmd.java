/* (C)2022 GalaxeTV */
package tv.galaxe.galaxesmp.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import tv.galaxe.galaxesmp.GalaxeSMP;
import tv.galaxe.galaxesmp.util.TwitchIntegration;

public class TwitchCmd implements CommandExecutor {

  private GalaxeSMP plugin = null;

  /**
   * Plugin instance from the server
   *
   * @param plugin Sets current instance of plugin
   */
  public TwitchCmd(GalaxeSMP plugin) {
    this.plugin = plugin;
  }

  private final String twitchChannel =
      GalaxeSMP.getInstance().getConfig().getString("twitch.channel");

  /**
   * Sends a clickable link to the Twitch channel
   *
   * @param sender Command sender
   * @param command Command
   * @param label Command label
   * @param args Command arguments
   * @return Returns true if the command was executed
   */
  @Override
  public boolean onCommand(
      @NotNull CommandSender sender,
      @NotNull Command command,
      @NotNull String label,
      @NotNull String[] args) {
    if (sender instanceof Player player) {
      final Component twitchUrl =
          Component.text("https://twitch.tv/" + twitchChannel)
              .clickEvent(ClickEvent.openUrl("https://twitch.tv/" + twitchChannel))
              .color(TextColor.color(0x9146FF));
      if (TwitchIntegration.isTwitchStreamLive()) {
        player.sendMessage(
            Component.text(twitchChannel + "'s stream is currently live! Watch now at ")
                .color(NamedTextColor.LIGHT_PURPLE)
                .append(twitchUrl));
      } else {
        player.sendMessage(
            Component.text(twitchChannel + "'s stream is currently offline. Check back later at ")
                .color(NamedTextColor.LIGHT_PURPLE)
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
    return true;
  }
}
