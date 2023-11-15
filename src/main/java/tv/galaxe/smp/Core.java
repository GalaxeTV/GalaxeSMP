package tv.galaxe.smp;

import dev.triumphteam.gui.guis.Gui;
import java.util.HashSet;
import net.luckperms.api.LuckPerms;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import tv.galaxe.smp.cmd.HelpGUI;
import tv.galaxe.smp.cmd.InvisibleItemFrame;
import tv.galaxe.smp.cmd.Pronouns;
import tv.galaxe.smp.event.SilkTouchAmethyst;
import tv.galaxe.smp.event.StaffKill;

public class Core extends JavaPlugin implements Listener {
	public static Plugin plugin;
	public static LuckPerms lp;
	public static HashSet<String> validPronouns = new HashSet<String>();
	public static Gui helpGUI;

	@Override
	public void onEnable() {
		plugin = this;
		getServer().getConsoleSender().sendMessage("GalaxeSMP Plugin Enabled");
		saveDefaultConfig();
		getConfig().getStringList("pronouns.valid").forEach((p) -> validPronouns.add(p.toLowerCase()));

		// LuckPerms
		lp = getServer().getServicesManager().load(LuckPerms.class);

		// Commands
		getCommand("invisibleitemframe").setExecutor(new InvisibleItemFrame());
		getCommand("help").setExecutor(new HelpGUI());
		getCommand("pronouns").setExecutor(new Pronouns());
		getCommand("galaxereload").setExecutor(new CommandExecutor() {
			public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
				reloadConfig();
				getConfig().getStringList("pronouns.valid").forEach((p) -> validPronouns.add(p.toLowerCase()));
				return true;
			}
		});

		// Listeners
		getServer().getPluginManager().registerEvents(new SilkTouchAmethyst(), this);
		getServer().getPluginManager().registerEvents(new StaffKill(), this);
	}

	@Override
	public void onDisable() {
		getServer().getConsoleSender().sendMessage("GalaxeSMP Plugin Disabled");
	}
}
