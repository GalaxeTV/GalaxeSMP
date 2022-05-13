package tv.galaxe.galaxesmp;

import org.bukkit.plugin.java.JavaPlugin;
import tv.galaxe.galaxesmp.crafting.InvisibleItemFrames;

public final class GalaxeSMP extends JavaPlugin {

    private static GalaxeSMP instance;

    public static GalaxeSMP getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        InvisibleItemFrames.createInvisibleItemFrames();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
