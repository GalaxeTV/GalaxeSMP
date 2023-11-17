package tv.galaxe.smp.cmd.helpmenus;

import dev.triumphteam.gui.guis.Gui;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
//import org.bukkit.inventory.ItemStack;
//import org.bukkit.inventory.meta.SkullMeta;

public class MainHelp {
	private static Player player = HelpCommand.player;
	public static Gui gui;

	// Constructor to prevent instantiation
	public MainHelp() {
		gui = Gui.gui().title(Component.text("GalaxeSMP Help Menu")).rows(6).create();

	}

	// public static Gui helpGUI;
	// private static Player player;
	//
	// // Constructor to prevent instantiation
	// public MainHelp() {
	// helpGUI = Gui.gui().title(Component.text("GalaxeSMP Help
	// Menu")).rows(6).create();
	// // Coordinates for item placements
	// // Format: {row, column}
	// int[][] whitePaneCoords = {{1, 5}, {2, 4}, {2, 6}, {3, 3}, {3, 5}, {3, 7},
	// {4, 4}, {4, 6}, {5, 5}, {6, 5}};
	//
	// // Create ItemStack for player's head
	// ItemStack playerSkull = new ItemStack(Material.PLAYER_HEAD);
	// SkullMeta skullMeta = (SkullMeta) playerSkull.getItemMeta();
	// skullMeta.setPlayerProfile(player.getPlayerProfile());
	// playerSkull.setItemMeta(skullMeta);
	//
	// /*
	// * Proposed
	// *
	// * // Coordinates for item placements // Format: {row, column}
	// * List<List<Integer>> whitePaneCoords = List.of(List.of(1, 5), List.of(2, 4),
	// * List.of(2, 6), List.of(3, 3), List.of(3, 5), List.of(3, 7), List.of(4, 4),
	// * List.of(4, 6), List.of(5, 5), List.of(6, 5));
	// *
	// * // Add white pane filler for (List<Integer> coords : whitePaneCoords) {
	// * GUI.setItem(coords.get(0), coords.get(1),
	// * ItemBuilder.from(Material.WHITE_STAINED_GLASS_PANE).name(emptyComponent).
	// * asGuiItem());
	// */
	//
	// // TextComponents for GUI
	// Component emptyComponent = Component.text("");
	// Component general =
	// Component.text("General").color(TextColor.color(0x515979)).decorate(TextDecoration.BOLD);
	// Component lockItems = Component.text("Locking
	// Items").color(TextColor.color(0x515979))
	// .decorate(TextDecoration.BOLD);
	// Component lunarEclipses = Component.text("Lunar
	// Eclipses").color(TextColor.color(0x515979))
	// .decorate(TextDecoration.BOLD);
	// Component mcMMO =
	// Component.text("mcMMO").color(TextColor.color(0x515979)).decorate(TextDecoration.BOLD);
	// Component silkSpawners = Component.text("Silk
	// Spawners").color(TextColor.color(0x515979))
	// .decorate(TextDecoration.BOLD);
	// Component pronouns =
	// Component.text("Pronouns").color(TextColor.color(0x515979)).decorate(TextDecoration.BOLD);
	// Component towny =
	// Component.text("Towny").color(TextColor.color(0x515979)).decorate(TextDecoration.BOLD);
	// Component sellingItems = Component.text("Selling
	// Items").color(TextColor.color(0x515979))
	// .decorate(TextDecoration.BOLD);
	// Component graves =
	// Component.text("Graves").color(TextColor.color(0x515979)).decorate(TextDecoration.BOLD);
	// Component events =
	// Component.text("Events").color(TextColor.color(0x515979)).decorate(TextDecoration.BOLD);
	//
	// // TextComponents for Lore on each item
	// Component generalLore = Component.text("General information about the
	// server").color(TextColor.color(0x262F58))
	// .decorate(TextDecoration.ITALIC);
	// Component lockItemsLore = Component.text("How to lock your items like Fort
	// Knox")
	// .color(TextColor.color(0x262F58)).decorate(TextDecoration.ITALIC);
	// Component lunarEclipsesLore = Component.text("Why is it dark and
	// storming?").color(TextColor.color(0x262F58))
	// .decorate(TextDecoration.ITALIC);
	// Component mcMMOLore = Component.text("OH YEAH SPECIAL
	// SKILLS").color(TextColor.color(0x262F58))
	// .decorate(TextDecoration.ITALIC);
	// Component silkSpawnersLore = Component.text("Spawners will never know what
	// hits them")
	// .color(TextColor.color(0x262F58)).decorate(TextDecoration.ITALIC);
	// Component pronounsLore = Component.text("How to set your
	// pronouns").color(TextColor.color(0x262F58))
	// .decorate(TextDecoration.ITALIC);
	// Component townyLore = Component.text("If City Skylines was in
	// Minecraft").color(TextColor.color(0x262F58))
	// .decorate(TextDecoration.ITALIC);
	// Component sellingItemsLore = Component
	// .text("How to start your own multi-billion\ndollar company inside of
	// GalaxeSMP")
	// .color(TextColor.color(0x262F58)).decorate(TextDecoration.ITALIC);
	// Component gravesLore =
	// Component.text("").color(TextColor.color(0x262F58)).decorate(TextDecoration.ITALIC);
	// Component eventsLore = Component.text("Events, celebrations, and
	// more!").color(TextColor.color(0x262F58))
	// .decorate(TextDecoration.ITALIC);
	//
	// // Create GUI w/open & close sounds
	// helpGUI.setOpenGuiAction(event -> {
	// player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_STEP, 1,
	// 1);
	// });
	// helpGUI.setCloseGuiAction(event -> {
	// player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_BREAK, 1,
	// 1);
	// });
	//
	// // Disable taking items
	// helpGUI.setDefaultClickAction(event -> {
	// event.setCancelled(true);
	// });
	//
	// // Add filler
	// helpGUI.getFiller().fill(Arrays.asList(ItemBuilder.from(Material.PINK_STAINED_GLASS_PANE).asGuiItem(),
	// ItemBuilder.from(Material.PURPLE_STAINED_GLASS_PANE).asGuiItem()));
	//
	// // Add white pane filler
	// for (int[] coords : whitePaneCoords) {
	// helpGUI.setItem(coords[0], coords[1],
	// ItemBuilder.from(Material.WHITE_STAINED_GLASS_PANE).name(emptyComponent).asGuiItem());
	// }
	//
	// // General helpGUI item
	// helpGUI.setItem(2, 5,
	// ItemBuilder.from(Material.DIAMOND_PICKAXE).name(general).lore(generalLore).asGuiItem(event
	// -> {
	// GeneralHelp.openGui(player);
	// }));
	//
	// // Events helpGUI item
	// helpGUI.setItem(4, 5,
	// ItemBuilder.from(Material.PHANTOM_MEMBRANE).name(events).lore(eventsLore).asGuiItem(event
	// -> {
	// /* Event Handling Here */
	// }));
	//
	// // Lock items helpGUI item
	// helpGUI.setItem(2, 2,
	// ItemBuilder.from(Material.CHEST).name(lockItems).lore(lockItemsLore).asGuiItem(event
	// -> {
	// /* Event Handling Here */
	// }));
	//
	// // Lunar eclipses helpGUI item
	// helpGUI.setItem(5, 2,
	// ItemBuilder.from(Material.CLOCK).name(lunarEclipses).lore(lunarEclipsesLore).asGuiItem(event
	// -> {
	// /* Event Handling Here */
	// }));
	//
	// helpGUI.setItem(4, 3,
	// ItemBuilder.from(Material.EXPERIENCE_BOTTLE).name(mcMMO).lore(mcMMOLore).asGuiItem(event
	// -> {
	// /* Event Handling Here */
	// }));
	//
	// // Silk spawners helpGUI item
	// helpGUI.setItem(3, 4,
	// ItemBuilder.from(Material.SPAWNER).name(silkSpawners).lore(silkSpawnersLore).asGuiItem(event
	// -> {
	// /* Event Handling Here */
	// }));
	//
	// // Pronouns helpGUI item
	// helpGUI.setItem(3, 6,
	// ItemBuilder.from(playerSkull).name(pronouns).lore(pronounsLore).asGuiItem(event
	// -> {
	// /* Event Handling Here */
	// }));
	//
	// // Towny helpGUI item
	// helpGUI.setItem(4, 7,
	// ItemBuilder.from(Material.FILLED_MAP).name(towny).lore(townyLore).asGuiItem(event
	// -> {
	// /* Event Handling Here */
	// }));
	//
	// // Selling items helpGUI item
	// helpGUI.setItem(2, 8,
	// ItemBuilder.from(Material.GOLD_INGOT).name(sellingItems).lore(sellingItemsLore).asGuiItem(event
	// -> {
	// /* Event Handling Here */
	// }));
	//
	// // Graves helpGUI item
	// helpGUI.setItem(5, 8,
	// ItemBuilder.from(Material.SKELETON_SKULL).name(graves).lore(gravesLore).asGuiItem(event
	// -> {
	// /* Event Handling Here */
	// }));
	// }
	//
	// public static void openGui(Player player) {
	// helpGUI.open(player);
	// }
}
