package tv.galaxe.smp.cmd;

import com.destroystokyo.paper.profile.PlayerProfile;
import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerTextures;
import tv.galaxe.smp.Core;

public class HelpCommand implements CommandExecutor {
	private static Player player;
	private static Gui mainHelp = createGui("GalaxeSMP Help Menu", 6, true, false);
	private static Gui generalHelp = createGui("GalaxeSMP General Help", 6, true, true);
	private static Gui lockItemsHelp = createGui("GalaxeSMP Locking Items Help", 6, false, true);
	private static Gui lunarEclipsesHelp = createGui("GalaxeSMP Lunar Eclipses Help", 6, false, true);
	private static Gui mcMMOHelp = createGui("GalaxeSMP mcMMO Help", 6, false, true);
	private static Gui silkSpawnersHelp = createGui("GalaxeSMP Silk Spawners Help", 6, false, true);
	private static Gui townyHelp = createGui("GalaxeSMP Towny Help", 6, false, true);
	private static Gui sellingItemsHelp = createGui("GalaxeSMP Selling Items Help", 6, false, true);
	private static Gui gravesHelp = createGui("GalaxeSMP Graves Help", 6, false, true);
	private static Gui eventsHelp = createGui("GalaxeSMP Events Help", 6, false, true);
	private static Gui pronounsHelp = createGui("GalaxeSMP Pronouns Help", 6, false, true);
	private static final int colorUltraViolet = 0x515979;
	private static final int colorSpaceCadet = 0x262F58;
	private static final int colorLightOrange = 0xFFDAB6;

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
			case "lockitems" :
				lockItemsHelp.open(player);
				return true;
			case "lunareclipses" :
				lunarEclipsesHelp.open(player);
				return true;
			case "mcmmo" :
				mcMMOHelp.open(player);
				return true;
			case "silkspawners" :
				silkSpawnersHelp.open(player);
				return true;
			case "towny" :
				townyHelp.open(player);
				return true;
			case "sellingitems" :
				sellingItemsHelp.open(player);
				return true;
			case "graves" :
				gravesHelp.open(player);
				return true;
			case "events" :
				eventsHelp.open(player);
				return true;
			case "pronouns" :
				pronounsHelp.open(player);
				return true;
			case "" :
			default :
				// Pronouns mainHelp item
				Component pronouns = Component.text("Pronouns").color(TextColor.color(colorUltraViolet))
						.decorate(TextDecoration.BOLD);
				Component pronounsLore = Component.text("How to set your pronouns")
						.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
				mainHelp.setItem(3, 6,
						ItemBuilder.from(createHead(player)).name(pronouns).lore(pronounsLore).asGuiItem(event -> {
							pronounsHelp.open(player);
						}));

				mainHelp.open(player);
				return true;
		}
	}

	// Constructor to prevent instantiation
	public HelpCommand() {
		// Create ItemStack for Discord head
		ItemStack discordHead = createHead("b722098ae79c7abf002fe9684c773ea71db8919bb2ef2053ea0c0684c5a1ce4f");

		// Create ItemStack for Mob Spawner
		ItemStack mobSpawner = new ItemStack(Material.SPAWNER);
		BlockStateMeta mobSpawnerMeta = (BlockStateMeta) mobSpawner.getItemMeta();
		CreatureSpawner spawner = (CreatureSpawner) mobSpawnerMeta.getBlockState();

		spawner.setSpawnedType(org.bukkit.entity.EntityType.PLAYER);
		mobSpawnerMeta.setBlockState(spawner);
		mobSpawner.setItemMeta(mobSpawnerMeta);

		// ====================
		// Main help menu
		// ====================

		// TextComponents for item names
		Component general = Component.text("General").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component lockItems = Component.text("Locking Items").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component lunarEclipses = Component.text("Lunar Eclipses").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component mcMMO = Component.text("mcMMO").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component silkSpawners = Component.text("Silk Spawners").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component towny = Component.text("Towny").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component sellingItems = Component.text("Selling Items").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component graves = Component.text("Graves").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component events = Component.text("Events").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);

		// TextComponents for Lore on each item
		Component generalLore = Component.text("General information about the server")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lockItemsLore = Component.text("How to lock your items like Fort Knox")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lunarEclipsesLore = Component.text("Why do I hear boss music?")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component mcMMOLore = Component.text("Hey, you. You're finally awake.").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);
		Component silkSpawnersLore = Component.text("Manmade horrors beyond comprehension")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyLore = Component.text("If City Skylines just Minecraft").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);
		Component sellingItemsLore1 = Component.text("How to start your own multi-billion")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component sellingItemsLore2 = Component.text("dollar company inside of the GalaxeSMP")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component gravesLore1 = Component.text("Have you or a loved one has been diagnosed with death?")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component gravesLore2 = Component.text("You may be entitled to financial compensation!")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component eventsLore1 = Component.text("\"So you just gonna bring me a birthday gift")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component eventsLore2 = Component.text("on my birthday to my birthday party")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component eventsLore3 = Component.text("on my birthday with a birthday gift?\"")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component eventsLore4 = Component.text(" - Tyler, The Creator").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);

		// General mainHelp item
		mainHelp.setItem(2, 5,
				ItemBuilder.from(Material.DIAMOND_PICKAXE).name(general).lore(generalLore).asGuiItem(event -> {
					generalHelp.open(player);
				}));

		// Events mainHelp item
		mainHelp.setItem(4, 5, ItemBuilder.from(Material.PHANTOM_MEMBRANE).name(events)
				.lore(List.of(eventsLore1, eventsLore2, eventsLore3, eventsLore4)).asGuiItem(event -> {
					eventsHelp.open(player);
				}));

		// Lock items mainHelp item
		mainHelp.setItem(2, 2, ItemBuilder.from(Material.CHEST).name(lockItems).lore(lockItemsLore).asGuiItem(event -> {
			lockItemsHelp.open(player);
		}));

		// Lunar eclipses mainHelp item
		mainHelp.setItem(5, 2,
				ItemBuilder.from(Material.CLOCK).name(lunarEclipses).lore(lunarEclipsesLore).asGuiItem(event -> {
					lunarEclipsesHelp.open(player);
				}));

		// mcMMO mainHelp item
		mainHelp.setItem(4, 3,
				ItemBuilder.from(Material.EXPERIENCE_BOTTLE).name(mcMMO).lore(mcMMOLore).asGuiItem(event -> {
					mcMMOHelp.open(player);
				}));

		// Silk spawners mainHelp item
		mainHelp.setItem(3, 4,
				ItemBuilder.from(mobSpawner).name(silkSpawners).lore(silkSpawnersLore).asGuiItem(event -> {
					silkSpawnersHelp.open(player);
				}));

		// Towny mainHelp item
		mainHelp.setItem(4, 7, ItemBuilder.from(Material.FILLED_MAP).name(towny).lore(townyLore).asGuiItem(event -> {
			townyHelp.open(player);
		}));

		// Selling items mainHelp item
		mainHelp.setItem(2, 8, ItemBuilder.from(Material.GOLD_INGOT).name(sellingItems)
				.lore(List.of(sellingItemsLore1, sellingItemsLore2)).asGuiItem(event -> {
					sellingItemsHelp.open(player);
				}));

		// Graves mainHelp item
		mainHelp.setItem(5, 8, ItemBuilder.from(Material.SKELETON_SKULL).name(graves)
				.lore(List.of(gravesLore1, gravesLore2)).asGuiItem(event -> {
					gravesHelp.open(player);
				}));

		// ====================
		// General help menu
		// ====================

		// TextComponents for item names
		Component serverMove = Component.text("Moving between servers").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component rules = Component.text("Rules").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component pvp = Component.text("PvP").color(TextColor.color(colorUltraViolet)).decorate(TextDecoration.BOLD);
		Component tickets = Component.text("Tickets").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component teleporting = Component.text("Teleporting").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component discord = Component.text("Discord").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);

		// TextComponents for Lore on each item
		Component serverMoveLore1 = Component.text("We have a hub server that you can use to move between servers")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component serverMoveLore2 = Component.text("You can use /server to see what servers are available")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component serverMoveLore3 = Component
				.text("To connect to other servers, use /server [name] to connect to your server")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component rulesLore1 = Component.text("1. No stealing, griefing, or hacking")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component rulesLore2 = Component.text("2. If a player wants you to leave their area, you must leave")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component rulesLore3 = Component.text("3. Replant crops in spawn farms")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component rulesLore4 = Component.text("4. Don't build any base next to spawn, it is a community area")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component rulesLore5 = Component.text("5. Follow Discord rules").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);
		Component pvpLore1 = Component.text("").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);
		Component pvpLore2 = Component.text("").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);
		Component ticketsLore1 = Component.text("").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);
		Component ticketsLore2 = Component.text("").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);
		Component teleportingLore1 = Component.text("").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);
		Component teleportingLore2 = Component.text("").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);
		Component discordLore1 = Component.text("").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);
		Component discordLore2 = Component.text("").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);
		Component discordMessage = Component.text("").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);

		// Moving between servers help item
		generalHelp.setItem(2, 5, ItemBuilder.from(Material.NETHER_STAR).name(serverMove)
				.lore(List.of(serverMoveLore1, serverMoveLore2, serverMoveLore3)).asGuiItem());

		// Rules help item
		generalHelp.setItem(3, 3, ItemBuilder.from(Material.WRITTEN_BOOK).name(rules)
				.lore(List.of(rulesLore1, rulesLore2, rulesLore3, rulesLore4, rulesLore5)).asGuiItem(event -> {
					player.performCommand("rules");
				}));

		// PvP help item
		generalHelp.setItem(3, 7,
				ItemBuilder.from(Material.NETHERITE_SWORD).name(pvp).lore(List.of(pvpLore1, pvpLore2)).asGuiItem());

		// Ticket help item
		generalHelp.setItem(4, 4, ItemBuilder.from(Material.NAME_TAG).name(tickets)
				.lore(List.of(ticketsLore1, ticketsLore2)).asGuiItem());

		// Teleport help item
		generalHelp.setItem(4, 6, ItemBuilder.from(Material.COMPASS).name(teleporting)
				.lore(List.of(teleportingLore1, teleportingLore2)).asGuiItem());

		generalHelp.setItem(5, 5, ItemBuilder.from(discordHead).name(discord).lore(List.of(discordLore1, discordLore2))
				.asGuiItem(event -> {
					player.sendMessage(discordMessage);
				}));
	}

	/**
	 * Creates a GUI with the specified title, number of rows, and whether or not to
	 * fill white panes and add a back button
	 * 
	 * @param title
	 *            The title of the GUI
	 * @param rows
	 *            The number of rows in the GUI
	 * @param fillWhitePanes
	 *            Whether or not to fill the white panes
	 * @param addBackButton
	 *            Whether or not to add a back button to go back to the main help
	 *            menu
	 * @return The created GUI
	 */
	private static Gui createGui(String title, int rows, Boolean fillWhitePanes, Boolean addBackButton) {
		// Create title Component for GUI
		Component titleComponent = Component.text(title).color(TextColor.color(colorSpaceCadet))
				.decorate(TextDecoration.BOLD);

		// Create components for back button
		Component back = Component.text("Back").color(TextColor.color(colorUltraViolet)).decorate(TextDecoration.BOLD);
		Component backLore = Component.text("Click to go back to the main help menu")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);

		// Create GUI
		Gui gui = Gui.gui().title(titleComponent).rows(rows).create();

		// Set GUI w/open and close sounds
		gui.setOpenGuiAction(event -> {
			player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_STEP, 1, 1);
		});
		gui.setCloseGuiAction(event -> {
			player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_BREAK, 1, 1);
		});

		// Disable taking items from all GUIs
		gui.setDefaultClickAction(event -> {
			event.setCancelled(true);
		});

		// Create empty Component for empty item names
		Component emptyComponent = Component.text("");

		// Fill empty slots with pink and purple glass panes
		gui.getFiller()
				.fill(List.of(ItemBuilder.from(Material.PINK_STAINED_GLASS_PANE).name(emptyComponent).asGuiItem(),
						ItemBuilder.from(Material.PURPLE_STAINED_GLASS_PANE).name(emptyComponent).asGuiItem()));

		// Add white pane filler for some GUIs if specified
		if (fillWhitePanes) {
			// Format: row1, column1, row2, column2, etc.
			int[][] whitePaneCoords = {{1, 5}, {2, 4}, {2, 6}, {3, 3}, {3, 5}, {3, 7}, {4, 4}, {4, 6}, {5, 5}, {6, 5}};
			for (int[] coordsEntry : whitePaneCoords) {
				gui.setItem(coordsEntry[0], coordsEntry[1],
						ItemBuilder.from(Material.WHITE_STAINED_GLASS_PANE).name(emptyComponent).asGuiItem());
			}
		}

		// Add back button to some GUIs if specified
		if (addBackButton) {
			// Add back button to some GUIs
			gui.setItem(rows, 5, ItemBuilder.from(Material.BARRIER).name(back).lore(backLore).asGuiItem(event -> {
				player.performCommand("help");
			}));
		}
		return gui;
	}

	/**
	 * Creates a player head ItemStack with the specified texture ID
	 * 
	 * @param TextureID
	 *            The texture ID of the head
	 * @return The player head ItemStack
	 */
	public static ItemStack createHead(String textureID) {
		PlayerProfile headProfile = Core.plugin.getServer().createProfile(UUID.randomUUID());
		PlayerTextures headTextures = headProfile.getTextures();

		try {
			headTextures.setSkin(new URL("https://textures.minecraft.net/texture/" + textureID));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		headProfile.setTextures(headTextures);

		ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
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
		ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setPlayerProfile(player.getPlayerProfile());
		skull.setItemMeta(meta);

		return skull;
	}
}
