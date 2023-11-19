package tv.galaxe.smp.cmd;

import com.destroystokyo.paper.profile.PlayerProfile;
import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerTextures;
import tv.galaxe.smp.Core;

public class HelpCommand implements CommandExecutor {
	private static Player player;
	private static Gui generalHelp;
	private static Gui mainHelp;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) { // Only allow players to run command
			sender.sendMessage("This command must be used as a player!");
			return false;
		}
		player = (Player) sender;
		switch ((args.length == 0) ? "" : args[0].toLowerCase()) {
			case "general" :
				generalHelp.open(player);
				return true;
			case "" :
			default :
				mainHelp.open(player);
				return true;
		}
	}

	// Constructor to prevent instantiation
	public HelpCommand() {
		// Create GUIs
		mainHelp = Gui.gui().title(Component.text("GalaxeSMP Help Menu")).rows(6).create();
		generalHelp = Gui.gui().title(Component.text("GalaxeSMP Help - General")).rows(6).create();

		// Set GUIs w/open and close sounds
		mainHelp.setOpenGuiAction(event -> {
			player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_STEP, 1, 1);
		});
		mainHelp.setCloseGuiAction(event -> {
			player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_BREAK, 1, 1);
		});
		generalHelp.setOpenGuiAction(event -> {
			player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_STEP, 1, 1);
		});
		generalHelp.setCloseGuiAction(event -> {
			player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_BREAK, 1, 1);
		});

		// Disable taking items from all GUIs
		mainHelp.setDefaultClickAction(event -> {
			event.setCancelled(true);
		});
		generalHelp.setDefaultClickAction(event -> {
			event.setCancelled(true);
		});

		// Create empty Component for empty item names
		Component emptyComponent = Component.text("");

		// Add filler to all GUIs
		mainHelp.getFiller().fill(Arrays.asList(ItemBuilder.from(Material.PINK_STAINED_GLASS_PANE).asGuiItem(),
				ItemBuilder.from(Material.PURPLE_STAINED_GLASS_PANE).asGuiItem()));
		generalHelp.getFiller().fill(Arrays.asList(ItemBuilder.from(Material.PINK_STAINED_GLASS_PANE).asGuiItem(),
				ItemBuilder.from(Material.PURPLE_STAINED_GLASS_PANE).asGuiItem()));

		// Add white pane filler for some GUIs
		Map<Integer, Integer> whitePaneCoords = Map.of(1, 5, 2, 4, 2, 6, 3, 3, 3, 5, 3, 7, 4, 4, 4, 6, 5, 5, 6, 5);
		for (Map.Entry<Integer, Integer> coordsEntry : whitePaneCoords.entrySet()) {
			mainHelp.setItem(coordsEntry.getKey(), coordsEntry.getValue(),
					ItemBuilder.from(Material.WHITE_STAINED_GLASS_PANE).name(emptyComponent).asGuiItem());
		}

		// Add back button to some GUIs
		generalHelp.setItem(6, 5, ItemBuilder.from(Material.BARRIER).name(Component.text("Back"))
				.lore(List.of(Component.text("Click to go back to the main help menu"))).asGuiItem(event -> {
					player.performCommand("help");
				}));

		// Create ItemStack for player's head
		ItemStack playerSkull = createHead(player);

		// Create ItemStack for Discord head
		ItemStack discordHead = createHead("b722098ae79c7abf002fe9684c773ea71db8919bb2ef2053ea0c0684c5a1ce4f");
		ItemMeta discordMeta = discordHead.getItemMeta();
		discordMeta.displayName(Component.text("Discord"));
		discordMeta.lore(Arrays.asList(Component.text(""), Component.text("")));
		discordHead.setItemMeta(discordMeta);

		/*
		 * Main help menu
		 */
		// TextComponents for item names
		Component general = Component.text("General").color(TextColor.color(0x515979)).decorate(TextDecoration.BOLD);
		Component lockItems = Component.text("Locking Items").color(TextColor.color(0x515979))
				.decorate(TextDecoration.BOLD);
		Component lunarEclipses = Component.text("Lunar Eclipses").color(TextColor.color(0x515979))
				.decorate(TextDecoration.BOLD);
		Component mcMMO = Component.text("mcMMO").color(TextColor.color(0x515979)).decorate(TextDecoration.BOLD);
		Component silkSpawners = Component.text("Silk Spawners").color(TextColor.color(0x515979))
				.decorate(TextDecoration.BOLD);
		Component pronouns = Component.text("Pronouns").color(TextColor.color(0x515979)).decorate(TextDecoration.BOLD);
		Component towny = Component.text("Towny").color(TextColor.color(0x515979)).decorate(TextDecoration.BOLD);
		Component sellingItems = Component.text("Selling Items").color(TextColor.color(0x515979))
				.decorate(TextDecoration.BOLD);
		Component graves = Component.text("Graves").color(TextColor.color(0x515979)).decorate(TextDecoration.BOLD);
		Component events = Component.text("Events").color(TextColor.color(0x515979)).decorate(TextDecoration.BOLD);

		// TextComponents for Lore on each item
		Component generalLore = Component.text("General information about the	 server")
				.color(TextColor.color(0x262F58)).decorate(TextDecoration.ITALIC);
		Component lockItemsLore = Component.text("How to lock your items like Fort	 Knox")
				.color(TextColor.color(0x262F58)).decorate(TextDecoration.ITALIC);
		Component lunarEclipsesLore = Component.text("Why is it dark and	 storming?")
				.color(TextColor.color(0x262F58)).decorate(TextDecoration.ITALIC);
		Component mcMMOLore = Component.text("OH YEAH SPECIAL	 SKILLS").color(TextColor.color(0x262F58))
				.decorate(TextDecoration.ITALIC);
		Component silkSpawnersLore = Component.text("Spawners will never know what	 hits them")
				.color(TextColor.color(0x262F58)).decorate(TextDecoration.ITALIC);
		Component pronounsLore = Component.text("How to set your	 pronouns").color(TextColor.color(0x262F58))
				.decorate(TextDecoration.ITALIC);
		Component townyLore = Component.text("If City Skylines was in	 Minecraft").color(TextColor.color(0x262F58))
				.decorate(TextDecoration.ITALIC);
		Component sellingItemsLore = Component
				.text("How to start your own multi-billion\ndollar company inside of	 GalaxeSMP")
				.color(TextColor.color(0x262F58)).decorate(TextDecoration.ITALIC);
		Component gravesLore = Component.text("").color(TextColor.color(0x262F58)).decorate(TextDecoration.ITALIC);
		Component eventsLore = Component.text("Events, celebrations, and	 more!").color(TextColor.color(0x262F58))
				.decorate(TextDecoration.ITALIC);

		// General mainHelp item
		mainHelp.setItem(2, 5,
				ItemBuilder.from(Material.DIAMOND_PICKAXE).name(general).lore(generalLore).asGuiItem(event -> {
				}));

		// Events mainHelp item
		mainHelp.setItem(4, 5,
				ItemBuilder.from(Material.PHANTOM_MEMBRANE).name(events).lore(eventsLore).asGuiItem(event -> {
					/* Event Handling Here */
				}));

		// Lock items mainHelp item
		mainHelp.setItem(2, 2, ItemBuilder.from(Material.CHEST).name(lockItems).lore(lockItemsLore).asGuiItem(event -> {
			/* Event Handling Here */
		}));

		// Lunar eclipses mainHelp item
		mainHelp.setItem(5, 2,
				ItemBuilder.from(Material.CLOCK).name(lunarEclipses).lore(lunarEclipsesLore).asGuiItem(event -> {
					/* Event Handling Here */
				}));

		mainHelp.setItem(4, 3,
				ItemBuilder.from(Material.EXPERIENCE_BOTTLE).name(mcMMO).lore(mcMMOLore).asGuiItem(event -> {
					/* Event Handling Here */
				}));

		// Silk spawners mainHelp item
		mainHelp.setItem(3, 4,
				ItemBuilder.from(Material.SPAWNER).name(silkSpawners).lore(silkSpawnersLore).asGuiItem(event -> {
					/* Event Handling Here */
				}));

		// Pronouns mainHelp item
		mainHelp.setItem(3, 6, ItemBuilder.from(playerSkull).name(pronouns).lore(pronounsLore).asGuiItem(event -> {
			/* Event Handling Here */
		}));

		// Towny mainHelp item
		mainHelp.setItem(4, 7, ItemBuilder.from(Material.FILLED_MAP).name(towny).lore(townyLore).asGuiItem(event -> {
			/* Event Handling Here */
		}));

		// Selling items mainHelp item
		mainHelp.setItem(2, 8,
				ItemBuilder.from(Material.GOLD_INGOT).name(sellingItems).lore(sellingItemsLore).asGuiItem(event -> {
					/* Event Handling Here */
				}));

		// Graves mainHelp item
		mainHelp.setItem(5, 8,
				ItemBuilder.from(Material.SKELETON_SKULL).name(graves).lore(gravesLore).asGuiItem(event -> {
					/* Event Handling Here */
				}));

		/*
		 * General help menu
		 */
		// Moving between servers help item
		generalHelp.setItem(2, 5, ItemBuilder.from(Material.NETHER_STAR).name(Component.text("Moving between servers"))
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
		generalHelp.setItem(3, 3, rulesItem);

		// PvP help item
		generalHelp.setItem(3, 7, ItemBuilder.from(Material.NETHERITE_SWORD).name(Component.text("PvP"))
				.lore(Arrays.asList(Component.text(""), Component.text(""))).asGuiItem());

		// Ticket help item
		generalHelp.setItem(4, 4, ItemBuilder.from(Material.NAME_TAG).name(Component.text("Tickets"))
				.lore(Arrays.asList(Component.text(""), Component.text(""))).asGuiItem());

		// Teleport help item
		generalHelp.setItem(4, 6, ItemBuilder.from(Material.COMPASS).name(Component.text("Teleporting"))
				.lore(Arrays.asList(Component.text(""), Component.text(""))).asGuiItem());

		generalHelp.setItem(5, 5, new GuiItem(discordHead, event -> {
			player.sendMessage(Component.text());
		}));
	}

	/**
	 * Creates a player head ItemStack with the specified texture ID
	 * 
	 * @param TextureID
	 *            The texture ID of the head
	 * @return The player head ItemStack
	 */
	public static ItemStack createHead(String TextureID) {
		PlayerProfile headProfile = Core.plugin.getServer().createProfile(UUID.randomUUID());
		PlayerTextures headTextures = headProfile.getTextures();
		try {
			headTextures.setSkin(new URL("https://textures.minecraft.net/texture/" + TextureID));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		headProfile.setTextures(headTextures);
		ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta meta = ((SkullMeta) skull.getItemMeta());
		meta.setPlayerProfile(headProfile);
		skull.setItemMeta(meta);
		return skull;
	}

	/**
	 * Creates a player head ItemStack with the specified player
	 * 
	 * @param player
	 *            The player to get the head of
	 * @return The player head ItemStack
	 */
	public static ItemStack createHead(Player player) {
		return createHead(player.getPlayerProfile().getId().toString());
	}
}
