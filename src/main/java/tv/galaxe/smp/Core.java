package tv.galaxe.smp;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import tv.galaxe.smp.cmd.HelpCommand;
import tv.galaxe.smp.cmd.InvisibleItemFrame;
import tv.galaxe.smp.event.SilkTouchAmethyst;
import tv.galaxe.smp.event.StaffKill;

public class Core extends JavaPlugin implements Listener {
	public static Plugin plugin;

	@Override
	public void onEnable() {
		plugin = this;
		getServer().getConsoleSender().sendMessage("GalaxeSMP Plugin Enabled");
		saveDefaultConfig();

		// Commands
		getCommand("invisibleitemframe").setExecutor(new InvisibleItemFrame());
		getCommand("help").setExecutor(new HelpCommand());

		// Listeners
		getServer().getPluginManager().registerEvents(new SilkTouchAmethyst(), this);
		getServer().getPluginManager().registerEvents(new StaffKill(), this);
	}

	@Override
	public void onDisable() {
		getServer().getConsoleSender().sendMessage("GalaxeSMP Plugin Disabled");
	}
}
