package tv.galaxe.smp.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.GlowItemFrame;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;

public final class InvisibleItemFrame implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) { // Only allow players to run command
            sender.sendMessage("This command must be used as a player!");
            return false;
        } else {
            Player player = (Player)sender;
            Entity lookingAt = player.getTargetEntity(10);
            if (lookingAt != null) { // Error handling in case player is not looking at an entity
                switch(lookingAt.getType()) {
                    case ITEM_FRAME:
                        ItemFrame itemFrame = (ItemFrame)lookingAt;
                        itemFrame.setVisible(!itemFrame.isVisible());
                        return true;
                    case GLOW_ITEM_FRAME:
                        GlowItemFrame glowItemFrame = (GlowItemFrame)lookingAt;
                        glowItemFrame.setVisible(!glowItemFrame.isVisible());
                        return true;
                    default: // Error handling in case player is looking at an entity that isn't an item frame or glowing item frame.
                        sender.sendMessage("You must be looking at an item frame!");
                        return false;
                }
            } else { // Error handling continued
                sender.sendMessage("You must be looking at an item frame!");
                return false;
            }
        }
    }
}
