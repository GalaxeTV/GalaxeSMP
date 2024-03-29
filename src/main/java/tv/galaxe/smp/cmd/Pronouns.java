package tv.galaxe.smp.cmd;

import java.util.HashSet;
import java.util.StringJoiner;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.NodeType;
import net.luckperms.api.node.types.SuffixNode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import tv.galaxe.smp.Core;

public class Pronouns implements CommandExecutor {
	private final Plugin plugin = Core.plugin;
	private final LuckPerms lp = Core.lp;
	private final HashSet<String> validPronouns = Core.validPronouns;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		String suffix = (sender instanceof Player)
				? lp.getPlayerAdapter(Player.class).getMetaData((Player) sender).getSuffix()
				: null;
		StringJoiner pronounList = new StringJoiner(", ");
		StringJoiner newPronouns = new StringJoiner("/", " (", ")");
		Node suffixNode;
		switch ((args.length == 0) ? "" : args[0].toLowerCase()) {

			/*
			 * /pronouns set [pronoun (pronoun (pronoun))]
			 * 
			 * Command that sets the users pronouns
			 */
			case "set" :
				// Error handling & verification
				if (!(sender instanceof Player)) {
					sender.sendMessage("This command must be used as a player!");
					return true;
				}
				if (args.length > plugin.getConfig().getInt("pronouns.max") + 1) {
					sender.sendMessage("You can not have more than " + plugin.getConfig().getInt("pronouns.max")
							+ " pronouns set!");
					return true;
				}
				if (args.length < 2) {
					return false;
				}

				// Build new pronouns string
				for (int i = 1; i < args.length; i++) {
					if (sender.hasPermission("galaxesmp.pronouns.trusted")
							|| validPronouns.contains(args[i].toLowerCase())) {
						newPronouns.add(args[i]);
					} else {
						sender.sendMessage("'" + args[i] + "' is not an acceptable pronoun!");
						return true;
					}
				}

				// Build permission node and assign
				suffixNode = SuffixNode.builder(newPronouns.toString(), 100).build();
				lp.getUserManager().modifyUser(((Player) sender).getUniqueId(), (User user) -> {
					user.data().clear(NodeType.SUFFIX::matches);
					user.data().add(suffixNode);
				});

				// Cleanup
				sender.sendMessage("Set pronouns to" + newPronouns.toString() + "!");
				return true;

			/*
			 * /pronouns setother [player] [pronoun (pronoun (pronoun))]
			 * 
			 * Command that sets the pronouns of a different user. Intended for moderation.
			 */
			case "setother" :
				// Error handling & verification
				if (!sender.hasPermission("galaxesmp.pronouns.other")) {
					sender.sendMessage("You do not have permission to use this command!");
					return true;
				}
				if (args.length > plugin.getConfig().getInt("pronouns.max") + 2) {
					sender.sendMessage(
							"You can not set more than " + plugin.getConfig().getInt("pronouns.max") + " pronouns!");
					return true;
				}
				if (args.length < 3) {
					return false;
				}
				if (sender.getServer().getPlayer(args[1]) == null) {
					sender.sendMessage("Could not find player '" + args[1] + "'!");
					return true;
				}

				// Build new pronouns string
				for (int i = 2; i < args.length; i++) {
					newPronouns.add(args[i]);
				}

				// Build permission node and assign
				suffixNode = SuffixNode.builder(newPronouns.toString(), 100).build();
				lp.getUserManager().modifyUser(sender.getServer().getPlayer(args[1]).getUniqueId(), (User user) -> {
					user.data().clear(NodeType.SUFFIX::matches);
					user.data().add(suffixNode);
				});

				// Cleanup
				sender.sendMessage("Set " + args[1] + "'s pronouns to" + newPronouns.toString() + "!");
				return true;

			/*
			 * /pronouns clear
			 * 
			 * Clears the user's own set pronouns
			 */
			case "clear" :
				// Error handling & verification
				if (!(sender instanceof Player)) {
					sender.sendMessage("This command must be used as a player!");
					return true;
				}

				// Find permission node and remove
				lp.getUserManager().modifyUser(((Player) sender).getUniqueId(), (User user) -> {
					user.data().clear(NodeType.SUFFIX::matches);
				});

				// Cleanup
				sender.sendMessage("Cleared pronouns!");
				return true;

			/*
			 * /pronouns clearother [user]
			 * 
			 * Command that clears the pronouns of a different user. Intended for
			 * moderation.
			 */
			case "clearother" :
				// Error handling & verification
				if (!sender.hasPermission("galaxesmp.pronouns.other")) {
					sender.sendMessage("You do not have permission to use this command!");
					return true;
				}
				if (args.length < 2) {
					return false;
				}
				if (sender.getServer().getPlayer(args[1]) == null) {
					sender.sendMessage("Could not find player '" + args[1] + "'!");
					return true;
				}

				// Find permission node and remove
				lp.getUserManager().modifyUser(sender.getServer().getPlayer(args[1]).getUniqueId(), (User user) -> {
					user.data().clear(NodeType.SUFFIX::matches);
				});

				// Cleanup
				sender.sendMessage("Cleared " + args[1] + "'s pronouns!");
				return true;

			/*
			 * /pronouns list
			 * 
			 * Displays a list of all valid pronouns to the user.
			 */
			case "list" :
				// Build comma delimiter list of valid pronouns and send to the user
				validPronouns.forEach((p) -> pronounList.add(p));
				sender.sendMessage(pronounList.toString());
				return true;

			/*
			 * /pronouns (view)
			 * 
			 * The default out put of /pronouns or /pronouns view Shows the player their
			 * currently set pronouns
			 */
			case "view" :
			case "" :
				if (!(sender instanceof Player)) {
					sender.sendMessage("This command must be used as a player!");
					return true;
				}
				if (suffix == null) {
					sender.sendMessage("You have no set pronouns!");
					return true;
				}
				sender.sendMessage("Your pronouns are" + suffix + "!");
				return true;
			default :
				return false;
		}
	}
}
