/* (C)2022 */
package tv.galaxe.galaxesmp;

import java.util.Objects;
import org.bukkit.plugin.java.JavaPlugin;
import tv.galaxe.galaxesmp.commands.InvisibleItemFrames;

public final class GalaxeSMP extends JavaPlugin {

  private static GalaxeSMP instance;

  public static GalaxeSMP getInstance() {
    return instance;
  }

  @Override
  public void onEnable() {
    // Plugin startup logic
    instance = this;
    Objects.requireNonNull(getCommand("invisibleitemframe")).setExecutor(new InvisibleItemFrames());
  }

  @Override
  public void onDisable() {
    // Plugin shutdown logic
  }
}
