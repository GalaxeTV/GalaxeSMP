/* (C)2022 Galaxe */
package tv.galaxe.galaxesmp;

import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.ITwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import java.util.Objects;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import tv.galaxe.galaxesmp.advancements.*;
import tv.galaxe.galaxesmp.commands.*;
import tv.galaxe.galaxesmp.util.*;

public final class GalaxeSMP extends JavaPlugin {

  private static GalaxeSMP instance;
  private ITwitchClient twitchClient;
  private RegisteredServiceProvider<LuckPerms> luckPerms;

  /**
   * Gets the plugin instance to be used
   *
   * @return currently running plugin
   */
  public static GalaxeSMP getInstance() {
    return instance;
  }

  /**
   * Gets Twitch4J API Wrapper
   *
   * @return Twitch wrapper
   */
  public ITwitchClient getTwitchClient() {
    return this.twitchClient;
  }

  /**
   * Gets the LuckPerms API
   *
   * @return LuckPerms API
   */
  public LuckPerms getLuckPerms() {
    return this.luckPerms.getProvider();
  }

  @Override
  public void onEnable() {
    instance = this;

    this.saveDefaultConfig();
    FileConfiguration config = getConfig();

    luckPerms = Bukkit.getServicesManager().getRegistration(LuckPerms.class);

    // Build TwitchClient
    twitchClient =
        TwitchClientBuilder.builder()
            .withClientId(config.getString("twitch.client_id"))
            .withClientSecret(config.getString("twitch.client_secret"))
            .withEnableHelix(true)
            .withEnableChat(true)
            .build();

    twitchClient.getChat().joinChannel("galaxe");
    twitchClient.getClientHelper().enableStreamEventListener("galaxe");
    twitchClient.getClientHelper().enableFollowEventListener("galaxe");

    Objects.requireNonNull(getCommand("invisibleitemframe")).setExecutor(new InvisibleItemFrames());

    getServer().getPluginManager().registerEvents(new KillAdvancement(this), this);

    twitchClient
        .getEventManager()
        .getEventHandler(SimpleEventHandler.class)
        .registerListener(new TwitchIntegration(this));

    getServer().getConsoleSender().sendMessage("[+] GalaxeSMP enabled!");
  }

  @Override
  public void onDisable() {
    if (twitchClient != null) {
      twitchClient.getEventManager().close();
      twitchClient.close();
      twitchClient = null;
    }
    getServer().getConsoleSender().sendMessage("[-] GalaxeSMP disabled!");
  }
}
