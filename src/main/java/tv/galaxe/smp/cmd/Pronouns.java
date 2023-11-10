package tv.galaxe.smp.cmd;

import java.util.List;
import java.util.stream.Collectors;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.NodeType;
import net.luckperms.api.node.types.SuffixNode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tv.galaxe.smp.Core;

public class Pronouns implements CommandExecutor {

	private final LuckPerms lp;
	private final List<String> validPronouns;
	private final Integer maxPronouns;

	public Pronouns(LuckPerms lp, Core plugin) {
		this.lp = lp;
		this.validPronouns = plugin.getConfig().getStringList("pronouns.valid");
		this.maxPronouns = plugin.getConfig().getInt("pronouns.max");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("This command must be used as a player!");
			return true;
		} else {
			Player player = (Player) sender;
			CachedMetaData metaData = lp.getPlayerAdapter(Player.class).getMetaData(player);
			String suffix = metaData.getSuffix();
			StringBuilder newSuffix = new StringBuilder();
			Node suffixNode;
			switch ((args.length == 0) ? "" : args[0].toLowerCase()) {
				case "set" :
					if (args.length > maxPronouns + 1) {
						sender.sendMessage("You can not have more than " + maxPronouns + " pronouns set!");
						return true;
					}
					if (args.length < 2) {
						return false;
					}
					newSuffix.append(" (");
					for (int i = 1; i < args.length; i++) {
						if (sender.hasPermission("galaxesmp.pronouns.trusted")
								|| validPronouns.stream().map(String::toLowerCase).collect(Collectors.toList())
										.contains(args[i].toLowerCase())) {
							newSuffix.append(args[i] + ((i == args.length - 1) ? "" : "/"));
						} else {
							sender.sendMessage("'" + args[i] + "' is not an acceptable pronoun!");
							return true;
						}
					}
					newSuffix.append(")");
					suffixNode = SuffixNode.builder(newSuffix.toString(), 100).build();
					lp.getUserManager().modifyUser(player.getUniqueId(), (User user) -> {
						user.data().clear(NodeType.SUFFIX::matches);
						user.data().add(suffixNode);
					});
					sender.sendMessage("Set pronouns to" + newSuffix.toString() + "!");
					return true;
				case "setother" :
					if (!sender.hasPermission("galaxesmp.pronouns.other")) {
						sender.sendMessage("You do not have permission to use this command!");
						return true;
					} else if (args.length > maxPronouns + 2) {
						sender.sendMessage("You can not set more than " + maxPronouns + " pronouns!");
						return true;
					} else if (args.length < 3) {
						return false;
					} else {
						player = sender.getServer().getPlayer(args[1]);
						if (player == null) {
							sender.sendMessage("Could not find player '" + args[1] + "'!");
							return true;
						}
						newSuffix.append(" (");
						for (int i = 2; i < args.length; i++) {
							newSuffix.append(args[i] + ((i == args.length - 1) ? "" : "/"));
						}
						newSuffix.append(")");
						suffixNode = SuffixNode.builder(newSuffix.toString(), 100).build();
						lp.getUserManager().modifyUser(player.getUniqueId(), (User user) -> {
							user.data().clear(NodeType.SUFFIX::matches);
							user.data().add(suffixNode);
						});
						sender.sendMessage("Set " + args[1] + "'s pronouns to" + newSuffix.toString() + "!");
						return true;
					}
				case "clear" :
					lp.getUserManager().modifyUser(player.getUniqueId(), (User user) -> {
						user.data().clear(NodeType.SUFFIX::matches);
					});
					sender.sendMessage("Cleared pronouns!");
					return true;
				case "clearother" :
					if (!sender.hasPermission("galaxesmp.pronouns.other")) {
						sender.sendMessage("You do not have permission to use this command!");
						return true;
					} else if (args.length < 2) {
						return false;
					} else {
						player = sender.getServer().getPlayer(args[1]);
						if (player == null) {
							sender.sendMessage("Could not find player '" + args[1] + "'!");
							return true;
						}
						lp.getUserManager().modifyUser(player.getUniqueId(), (User user) -> {
							user.data().clear(NodeType.SUFFIX::matches);
						});
						sender.sendMessage("Cleared " + args[1] + "'s pronouns!");
						return true;
					}
				case "list" :
					// TODO: List all valid pronouns
					return true;
				case "view" :
				case "" :
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
}
