/* (C)2022-2023 GalaxeTV */
package tv.galaxe.galaxesmp;

import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.ITwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import java.util.Objects;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import tv.galaxe.galaxesmp.advancements.*;
import tv.galaxe.galaxesmp.commands.*;
import tv.galaxe.galaxesmp.events.SilkTouchAmethyst;
import tv.galaxe.galaxesmp.util.*;

public final class GalaxeSMP extends JavaPlugin {

  private static GalaxeSMP instance;
  private ITwitchClient twitchClient;

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

  @Override
  public void onEnable() {
    instance = this;

    // Get config
    this.saveDefaultConfig();
    FileConfiguration config = getConfig();

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

    // Register commands
    Objects.requireNonNull(getCommand("invisibleitemframe"))
        .setExecutor(new InvisibleItemFramesCmd());
    Objects.requireNonNull(getCommand("twitch")).setExecutor(new TwitchCmd(this));

    // Register server events
    getServer().getPluginManager().registerEvents(new KillAdvancement(this), this);
    getServer().getPluginManager().registerEvents(new SilkTouchAmethyst(this), this);

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
