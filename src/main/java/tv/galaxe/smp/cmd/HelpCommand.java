package tv.galaxe.smp.cmd;

import com.destroystokyo.paper.profile.PlayerProfile;

import dev.triumphteam.gui.guis.Gui;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerTextures;
import tv.galaxe.smp.Core;

public class HelpCommand implements CommandExecutor {
	private static Player player;
	private static Gui generalHelp;
	private static Gui mainHelp;

	public HelpCommand() {
		// Define GUIs Here
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) { // Only allow players to run command
			sender.sendMessage("This command must be used as a player!");
			return false;
		}
		player = (Player) sender;
		switch ((args.length == 0) ? "" : args[0].toLowerCase()) {
			case "general" :
				generalHelp.open(player);
				return true;
			case "" :
			default :
				mainHelp.open(player);
				return true;
		}
	}

	public static ItemStack createHead(String TextureID) {
		PlayerProfile headProfile = Core.plugin.getServer().createProfile(UUID.randomUUID());
		PlayerTextures headTextures = headProfile.getTextures();
		try {
			headTextures.setSkin(new URL("https://textures.minecraft.net/texture/" + TextureID));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		headProfile.setTextures(headTextures);
		ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta meta = ((SkullMeta) skull.getItemMeta());
		meta.setPlayerProfile(headProfile);
		skull.setItemMeta(meta);
		return skull;
	}
}
