package tv.galaxe.smp;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import tv.galaxe.smp.cmd.HelpGUI;
import tv.galaxe.smp.cmd.InvisibleItemFrame;
import tv.galaxe.smp.cmd.Pronouns;
import tv.galaxe.smp.event.SilkTouchAmethyst;
import tv.galaxe.smp.event.StaffKill;
import net.luckperms.api.LuckPerms;

public class Core extends JavaPlugin implements Listener {
	@Override
	public void onEnable() {
		getServer().getConsoleSender().sendMessage("GalaxeSMP Plugin Enabled");
		saveDefaultConfig();

		// Commands
		getCommand("invisibleitemframe").setExecutor(new InvisibleItemFrame());
		getCommand("help").setExecutor(new HelpGUI());
		getCommand("pronouns").setExecutor(new Pronouns());

		// Listeners
		getServer().getPluginManager().registerEvents(new SilkTouchAmethyst(), this);
		getServer().getPluginManager().registerEvents(new StaffKill(), this);

		// LuckPerms
		RegisterServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
		if (provider != null) {
			LuckPerms api = provider.getProvider();
		}
	}

	@Override
	public void onDisable() {
		getServer().getConsoleSender().sendMessage("GalaxeSMP Plugin Disabled");
	}
}
