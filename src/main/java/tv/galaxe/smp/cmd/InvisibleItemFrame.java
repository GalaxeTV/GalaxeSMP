package tv.galaxe.smp.cmd;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.GlowItemFrame;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import tv.galaxe.smp.Core;

public final class InvisibleItemFrame implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) { // Only allow players to run command
			sender.sendMessage("This command must be used as a player!");
			return false;
		}
		Player player = (Player) sender;
		Entity lookingAt = player.getTargetEntity(10);
		if (lookingAt == null) {
			sender.sendMessage("You must be looking at an item frame!");
			return false;
		}

		// Check if player has region permission to change item frame visibility
		RegionQuery query = WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery();
		ApplicableRegionSet set = query.getApplicableRegions(BukkitAdapter.adapt(lookingAt.getLocation()));
		if (!set.testState(WorldGuardPlugin.inst().wrapPlayer(player), Core.INVIS_ITEM_FRAME)
				&& !WorldGuard.getInstance().getPlatform().getSessionManager()
						.hasBypass(WorldGuardPlugin.inst().wrapPlayer(player), (World) player.getWorld())) {
			sender.sendMessage("You do not have permission to do that here!");
			return false;
		}

		// Change item frame visibility
		switch (lookingAt.getType()) {
			case ITEM_FRAME :
				ItemFrame itemFrame = (ItemFrame) lookingAt;
				itemFrame.setVisible(!itemFrame.isVisible());
				return true;
			case GLOW_ITEM_FRAME :
				GlowItemFrame glowItemFrame = (GlowItemFrame) lookingAt;
				glowItemFrame.setVisible(!glowItemFrame.isVisible());
				return true;
			default :
				sender.sendMessage("You must be looking at an item frame!");
				return false;
		}
	}
}
