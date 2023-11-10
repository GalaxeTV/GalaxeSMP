package tv.galaxe.smp;

import net.luckperms.api.LuckPerms;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import tv.galaxe.smp.cmd.HelpGUI;
import tv.galaxe.smp.cmd.InvisibleItemFrame;
import tv.galaxe.smp.cmd.Pronouns;
import tv.galaxe.smp.event.SilkTouchAmethyst;
import tv.galaxe.smp.event.StaffKill;

public class Core extends JavaPlugin implements Listener {

	private LuckPerms lp;

	@Override
	public void onEnable() {
		getServer().getConsoleSender().sendMessage("GalaxeSMP Plugin Enabled");
		saveDefaultConfig();

		// LuckPerms
		this.lp = getServer().getServicesManager().load(LuckPerms.class);

		// Commands
		getCommand("invisibleitemframe").setExecutor(new InvisibleItemFrame());
		getCommand("help").setExecutor(new HelpGUI());
		getCommand("pronouns").setExecutor(new Pronouns(this.lp, this));

		// Listeners
		getServer().getPluginManager().registerEvents(new SilkTouchAmethyst(), this);
		getServer().getPluginManager().registerEvents(new StaffKill(), this);
	}

	@Override
	public void onDisable() {
		getServer().getConsoleSender().sendMessage("GalaxeSMP Plugin Disabled");
	}
}
