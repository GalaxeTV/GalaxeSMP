package tv.galaxe.smp.cmd.helpmenus;

import com.destroystokyo.paper.profile.PlayerProfile;
import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerTextures;
import tv.galaxe.smp.Core;

public class GeneralHelp {
	private static Player player;
	private static Gui generalHelpGui;

	// Constructor to prevent instantiation
	public GeneralHelp() {
		generalHelpGui = Gui.gui().title(Component.text("GalaxeSMP Help - General")).rows(6).create();

		// Create ItemStack for player head of a specific UUID for Discord
		PlayerProfile discord = Core.plugin.getServer().createProfile(UUID.randomUUID());
		PlayerTextures textures = discord.getTextures();

		// URL discordSkin;
		try {
			textures.setSkin(new URL(
					"https://textures.minecraft.net/texture/b722098ae79c7abf002fe9684c773ea71db8919bb2ef2053ea0c0684c5a1ce4f"));
		} catch (MalformedURLException exception) {
			Core.plugin.getServer().getLogger().severe("Invalid URL" + exception);
			// throw new RuntimeException("Invalid URL", exception);
		}

		// textures.setSkin(discordSkin);
		discord.setTextures(textures);

		ItemStack playerSkull = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta skullMeta = (SkullMeta) playerSkull.getItemMeta();
		skullMeta.setPlayerProfile(discord);
		playerSkull.setItemMeta(skullMeta);

		// Create GUI w/open and close sounds
		generalHelpGui.setOpenGuiAction(event -> {
			player.playSound(player.getLocation(), "ui.button.click", 1, 1);
		});
		generalHelpGui.setCloseGuiAction(event -> {
			player.playSound(player.getLocation(), "ui.button.click", 1, 1);
		});

		// Disable taking items
		generalHelpGui.setDefaultClickAction(event -> {
			event.setCancelled(true);
		});

		// Add filler
		generalHelpGui.getFiller().fill(Arrays.asList(ItemBuilder.from(Material.PINK_STAINED_GLASS_PANE).asGuiItem(),
				ItemBuilder.from(Material.PURPLE_STAINED_GLASS_PANE).asGuiItem()));

		// Add back button
		generalHelpGui.setItem(6, 5, ItemBuilder.from(Material.BARRIER).name(Component.text("Back"))
				.lore(List.of(Component.text("Click to go back to the main help menu"))).asGuiItem(event -> {
					player.performCommand("help");
				}));

		// Moving between servers help item
		generalHelpGui.setItem(2, 5, ItemBuilder.from(Material.NETHER_STAR)
				.name(Component.text("Moving between servers"))
				.lore(Arrays.asList(Component.text("We have a hub server that you can use to move between servers"),
						Component.text("You can use /server to see what servers are available"),
						Component.text("To connect to other servers, use /server [name] to connect to your server")))
				.asGuiItem());

		GuiItem rulesItem = ItemBuilder.from(Material.WRITTEN_BOOK).name(Component.text("Rules"))
				.lore(Arrays.asList(Component.text("1. No stealing, griefing, or hacking"),
						Component.text("2. If a player wants you to leave their area, you must leave"),
						Component.text("3. Replant crops in spawn farms"),
						Component.text("4. Don't build any base next to spawn, it is a community area"),
						Component.text("5. Follow Discord rules")))
				.asGuiItem(event -> {
					player.performCommand("rules");
				});

		// Rules help item
		generalHelpGui.setItem(3, 3, rulesItem);

		// PvP help item
		generalHelpGui.setItem(3, 7, ItemBuilder.from(Material.NETHERITE_SWORD).name(Component.text("PvP"))
				.lore(Arrays.asList(Component.text(""), Component.text(""))).asGuiItem());

		// Ticket help item
		generalHelpGui.setItem(4, 4, ItemBuilder.from(Material.NAME_TAG).name(Component.text("Tickets"))
				.lore(Arrays.asList(Component.text(""), Component.text(""))).asGuiItem());

		// Teleport help item
		generalHelpGui.setItem(4, 6, ItemBuilder.from(Material.COMPASS).name(Component.text("Teleporting"))
				.lore(Arrays.asList(Component.text(""), Component.text(""))).asGuiItem());

		// Discord help item
		generalHelpGui.setItem(5, 5, ItemBuilder.from(Material.PLAYER_HEAD).name(Component.text("Discord"))
				.lore(Arrays.asList(Component.text(""), Component.text(""))).asGuiItem(event -> {
					player.sendMessage(Component.text());
				}));

		GeneralHelp.openGui(player);
	}

	public static void openGui(Player player) {
		// Open GUI
		generalHelpGui.open(player);
	}
}
