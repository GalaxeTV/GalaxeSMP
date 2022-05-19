/* (C)2022 */
package tv.galaxe.galaxesmp;

import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.ITwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import java.util.Objects;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import tv.galaxe.galaxesmp.commands.InvisibleItemFrames;
import tv.galaxe.galaxesmp.util.TwitchIntegration;

public final class GalaxeSMP extends JavaPlugin {

  private ITwitchClient twitchClient;
  private static GalaxeSMP instance;

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

    this.saveDefaultConfig();
    FileConfiguration config = getConfig();

    String token = config.getString("twitch.oauth_token");

    // Build TwitchClient
    twitchClient =
        TwitchClientBuilder.builder()
            .withClientId(config.getString("twitch.client_id"))
            .withClientSecret(config.getString("twitch.client_secret"))
            .withEnableHelix(true)
            .build();

    twitchClient.getChat().joinChannel("galaxe");
    twitchClient.getClientHelper().enableStreamEventListener("galaxe");
    twitchClient.getClientHelper().enableFollowEventListener("galaxe");

    Objects.requireNonNull(getCommand("invisibleitemframe")).setExecutor(new InvisibleItemFrames());

    twitchClient
        .getEventManager()
        .getEventHandler(SimpleEventHandler.class)
        .registerListener(new TwitchIntegration(this));
  }

  @Override
  public void onDisable() {
    if (twitchClient != null) twitchClient.close();
  }
}
