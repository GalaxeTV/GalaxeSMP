package tv.galaxe.smp.cmd.helpmenus;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import java.util.Arrays;
import java.util.List;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GeneralHelp {
	private static Player player = HelpCommand.player;
	public static Gui gui;

	// Constructor to prevent instantiation
	public GeneralHelp() {
		gui = Gui.gui().title(Component.text("GalaxeSMP Help - General")).rows(6).create();

		// Create GUI w/open and close sounds
		gui.setOpenGuiAction(event -> {
			player.playSound(player.getLocation(), "ui.button.click", 1, 1);
		});
		gui.setCloseGuiAction(event -> {
			player.playSound(player.getLocation(), "ui.button.click", 1, 1);
		});

		// Disable taking items
		gui.setDefaultClickAction(event -> {
			event.setCancelled(true);
		});

		// Add filler
		gui.getFiller().fill(Arrays.asList(ItemBuilder.from(Material.PINK_STAINED_GLASS_PANE).asGuiItem(),
				ItemBuilder.from(Material.PURPLE_STAINED_GLASS_PANE).asGuiItem()));

		// Add back button
		gui.setItem(6, 5, ItemBuilder.from(Material.BARRIER).name(Component.text("Back"))
				.lore(List.of(Component.text("Click to go back to the main help menu"))).asGuiItem(event -> {
					player.performCommand("help");
				}));

		// Moving between servers help item
		gui.setItem(2, 5, ItemBuilder.from(Material.NETHER_STAR).name(Component.text("Moving between servers"))
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
		gui.setItem(3, 3, rulesItem);

		// PvP help item
		gui.setItem(3, 7, ItemBuilder.from(Material.NETHERITE_SWORD).name(Component.text("PvP"))
				.lore(Arrays.asList(Component.text(""), Component.text(""))).asGuiItem());

		// Ticket help item
		gui.setItem(4, 4, ItemBuilder.from(Material.NAME_TAG).name(Component.text("Tickets"))
				.lore(Arrays.asList(Component.text(""), Component.text(""))).asGuiItem());

		// Teleport help item
		gui.setItem(4, 6, ItemBuilder.from(Material.COMPASS).name(Component.text("Teleporting"))
				.lore(Arrays.asList(Component.text(""), Component.text(""))).asGuiItem());

		// Discord help item
		ItemStack discordHead = HelpCommand
				.createHead("b722098ae79c7abf002fe9684c773ea71db8919bb2ef2053ea0c0684c5a1ce4f");
		ItemMeta discordMeta = discordHead.getItemMeta();
		discordMeta.displayName(Component.text("Discord"));
		discordMeta.lore(Arrays.asList(Component.text(""), Component.text("")));
		discordHead.setItemMeta(discordMeta);

		gui.setItem(5, 5, new GuiItem(discordHead, event -> {
			player.sendMessage(Component.text());
		}));
	}
}
