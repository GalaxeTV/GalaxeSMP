/* (C)2022 */
package tv.galaxe.galaxesmp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.jetbrains.annotations.NotNull;

public class InvisibleItemFrames implements CommandExecutor {

  /**
   * Checks if used is currently looking at an ITEM_FRAME or a GLOWING_ITEM_FRAME and toggles
   * visibility of those blocks, otherwise it will return an error message to the user
   *
   * @param sender Source of the command
   * @param command Command which was executed
   * @param label Alias of the command which was used
   * @param args Passed command arguments
   * @return Proper command executed to the server
   */
  @Override
  public boolean onCommand(
      @NotNull CommandSender sender,
      @NotNull Command command,
      @NotNull String label,
      @NotNull String[] args) {
    if (sender instanceof Player) {
      Player player = ((Player) sender).getPlayer();

      Entity block = player.getTargetEntity(10);

      if (block != null) {
        if (block.getType() == EntityType.ITEM_FRAME) {
          ItemFrame itemFrame = (ItemFrame) block;
          itemFrame.setVisible(!itemFrame.isVisible());
        } else if (block.getType() == EntityType.GLOW_ITEM_FRAME) {
          GlowItemFrame glowItemFrame = (GlowItemFrame) block;
          glowItemFrame.setVisible(!glowItemFrame.isVisible());
        }
      } else {
        player.sendMessage(
            ChatColor.DARK_RED + "You must be looking at an item frame or a glowing item frame.");
      }
    } else {
      sender.sendMessage(ChatColor.DARK_RED + "You must be a player to use this command.");
    }
    return true;
  }
}
