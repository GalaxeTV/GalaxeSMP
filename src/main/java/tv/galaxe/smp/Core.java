package tv.galaxe.smp;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import tv.galaxe.smp.advancement.StaffKill;
import tv.galaxe.smp.cmd.InvisibleItemFrame;
import tv.galaxe.smp.event.SilkTouchAmethyst;

public class Core extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage("GalaxeSMP Plugin Enabled");
        saveDefaultConfig();

        // Commands
        getCommand("invisibleitemframe").setExecutor(new InvisibleItemFrame());

        // Listeners
        getServer().getPluginManager().registerEvents(new SilkTouchAmethyst(), this);
        getServer().getPluginManager().registerEvents(new StaffKill(), this);
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("GalaxeSMP Plugin Disabled");
    }

}
