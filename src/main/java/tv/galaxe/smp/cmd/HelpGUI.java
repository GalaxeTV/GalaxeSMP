package tv.galaxe.smp.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tv.galaxe.smp.cmd.helpmenus.*;

public class HelpGUI implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) { // Only allow players to run command
			sender.sendMessage("This command must be used as a player!");
			return false;
		}
		switch ((args.length == 0) ? "" : args[0].toLowerCase()) {
			case "general" :
				GeneralHelp.openGui((Player) sender);
				return true;
			case "" :
			default :
				MainHelp mainHelp = new MainHelp();
				mainHelp.openGui((Player) sender);
				return true;
		}
	}
}
