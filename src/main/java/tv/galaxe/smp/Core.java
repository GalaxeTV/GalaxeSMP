package tv.galaxe.smp;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import tv.galaxe.smp.cmd.HelpGUI;
import tv.galaxe.smp.cmd.InvisibleItemFrame;
import tv.galaxe.smp.event.SilkTouchAmethyst;
import tv.galaxe.smp.event.StaffKill;

public class Core extends JavaPlugin implements Listener {
	@Override
	public void onEnable() {
		getServer().getConsoleSender().sendMessage("GalaxeSMP Plugin Enabled");
		saveDefaultConfig();

		// Commands
		getCommand("invisibleitemframe").setExecutor(new InvisibleItemFrame());
		getCommand("help").setExecutor(new HelpGUI());

		// Listeners
		getServer().getPluginManager().registerEvents(new SilkTouchAmethyst(), this);
		getServer().getPluginManager().registerEvents(new StaffKill(), this);
	}

	@Override
	public void onDisable() {
		getServer().getConsoleSender().sendMessage("GalaxeSMP Plugin Disabled");
	}
}
