/* (C)2022-2023 GalaxeTV */
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

    // Get config
    this.saveDefaultConfig();
    FileConfiguration config = getConfig();

    // Register LuckPerms
    luckPerms = Bukkit.getServicesManager().getRegistration(LuckPerms.class);

    // Check if Twitch integration is enabled
    if (config.getBoolean("twitch.enabled")) {
      getServer().getConsoleSender().sendMessage("[+] Twitch integration is enabled!");
      // Build TwitchClient
      twitchClient =
          TwitchClientBuilder.builder()
              .withClientId(config.getString("twitch.client_id"))
              .withClientSecret(config.getString("twitch.client_secret"))
              .withEnableHelix(true)
              .withEnableChat(true)
              .build();

      // Register Twitch Events
      twitchClient.getChat().joinChannel(config.getString("twitch.channel"));
      twitchClient.getClientHelper().enableStreamEventListener(config.getString("twitch.channel"));
      twitchClient.getClientHelper().enableFollowEventListener(config.getString("twitch.channel"));

      // Register Twitch event handler
      twitchClient
          .getEventManager()
          .getEventHandler(SimpleEventHandler.class)
          .registerListener(new TwitchIntegration(this));
    } else {
      twitchClient = null;
      getServer().getConsoleSender().sendMessage("[!] Twitch integration is disabled!");
    }

    // Register commands
    Objects.requireNonNull(getCommand("invisibleitemframe"))
        .setExecutor(new InvisibleItemFramesCmd());
    Objects.requireNonNull(getCommand("twitch")).setExecutor(new TwitchCmd(this));

    // Register server events
    getServer().getPluginManager().registerEvents(new KillAdvancement(this), this);

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
