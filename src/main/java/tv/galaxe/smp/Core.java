package tv.galaxe.smp;

import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import java.util.Arrays;
import java.util.HashSet;
import net.kyori.adventure.text.Component;
import net.luckperms.api.LuckPerms;
import org.bukkit.Material;
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
		buildHelpGUI();

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

	private void buildHelpGUI() {
		helpGUI = Gui.gui().title(Component.text("GalaxeSMP Help Menu")).rows(6).create();
		helpGUI.setDefaultClickAction(event -> {
			event.setCancelled(true);
		});
		helpGUI.getFiller().fill(Arrays.asList(new GuiItem(Material.PINK_STAINED_GLASS_PANE),
				new GuiItem(Material.PURPLE_STAINED_GLASS_PANE)));
		helpGUI.setItem(3, 3, new GuiItem(Material.WHITE_STAINED_GLASS_PANE));
		helpGUI.setItem(3, 7, new GuiItem(Material.WHITE_STAINED_GLASS_PANE));
		for (int i = 2; i <= 4; i++) {
			helpGUI.setItem(i, 4, new GuiItem(Material.WHITE_STAINED_GLASS_PANE));
			helpGUI.setItem(i, 6, new GuiItem(Material.WHITE_STAINED_GLASS_PANE));
		}
		for (int i = 1; i <= 6; i++) {
			helpGUI.setItem(i, 5, new GuiItem(Material.WHITE_STAINED_GLASS_PANE));
		}
	}
}
