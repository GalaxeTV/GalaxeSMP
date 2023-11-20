package tv.galaxe.smp.cmd;

import com.destroystokyo.paper.profile.PlayerProfile;
import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
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
	private static Gui lockItemsHelp = createGui("GalaxeSMP Locking Items", 6, true, true);
	private static Gui lunarEclipsesHelp = createGui("GalaxeSMP Lunar Eclipses", 6, true, true);
	private static Gui mcmmoHelp = createGui("GalaxeSMP mcMMO Help", 6, true, true);
	private static Gui silkSpawnersHelp = createGui("GalaxeSMP Silk Spawners", 6, false, true);
	private static Gui townyHelp = createGui("GalaxeSMP Towny", 6, false, true);
	private static Gui economyHelp = createGui("GalaxeSMP Economy", 6, false, true);
	private static Gui gravesHelp = createGui("GalaxeSMP Graves", 6, false, true);
	private static Gui eventsHelp = createGui("GalaxeSMP Events", 6, false, true);
	private static Gui pronounsHelp = createGui("GalaxeSMP Pronouns", 6, false, true);
	private static final int colorUltraViolet = 0x515979;
	private static final int colorSpaceCadet = 0x262F58;
	private static final int colorLightOrange = 0xFFDAB6;
	private static final int colorDiscordBlurple = 0x5865F2;

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
				mcmmoHelp.open(player);
				return true;
			case "silkspawners" :
				silkSpawnersHelp.open(player);
				return true;
			case "towny" :
				townyHelp.open(player);
				return true;
			case "economy" :
				economyHelp.open(player);
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
		// ====================
		// Main help menu
		// ====================

		// TextComponents for item names
		Component mainGeneral = Component.text("General").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component mainLockItems = Component.text("Locking Items").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component mainLunarEclipses = Component.text("Lunar Eclipses").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component mainMcmmo = Component.text("mcMMO").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component mainSilkSpawners = Component.text("Silk Spawners").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component mainTowny = Component.text("Towny").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component mainEconomy = Component.text("Economy").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component mainGraves = Component.text("Graves").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component mainEvents = Component.text("Events").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);

		// TextComponents for Lore on each item
		Component mainGeneralLore = Component.text("General information about the server")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component mainLockItemsLore = Component.text("How to lock your items like Fort Knox")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component mainLunarEclipsesLore = Component.text("Why do I hear boss music?")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component mainMcmmoLore = Component.text("Hey, you. You're finally awake.")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component mainSilkSpawnersLore = Component.text("Manmade horrors beyond comprehension")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component mainTownyLore = Component.text("If City Skylines just Minecraft")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component mainEconomyLore1 = Component.text("How to start your own multi-billion")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component mainEconomyLore2 = Component.text("dollar company inside of the GalaxeSMP")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component mainGravesLore1 = Component.text("Have you or a loved one has been diagnosed with death?")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component mainGravesLore2 = Component.text("You may be entitled to financial compensation!")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component mainEventsLore1 = Component.text("\"So you just gonna bring me a birthday gift")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component mainEventsLore2 = Component.text("on my birthday to my birthday party")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component mainEventsLore3 = Component.text("on my birthday with a birthday gift?\"")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component mainEventsLore4 = Component.text(" - Tyler, The Creator").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);

		// General mainHelp item
		mainHelp.setItem(2, 5,
				ItemBuilder.from(Material.DIAMOND_PICKAXE).name(mainGeneral).lore(mainGeneralLore).asGuiItem(event -> {
					generalHelp.open(player);
				}));

		// Events mainHelp item
		mainHelp.setItem(4, 5, ItemBuilder.from(Material.PHANTOM_MEMBRANE).name(mainEvents)
				.lore(List.of(mainEventsLore1, mainEventsLore2, mainEventsLore3, mainEventsLore4)).asGuiItem(event -> {
					eventsHelp.open(player);
				}));

		// Lock items mainHelp item
		mainHelp.setItem(2, 2,
				ItemBuilder.from(Material.CHEST).name(mainLockItems).lore(mainLockItemsLore).asGuiItem(event -> {
					lockItemsHelp.open(player);
				}));

		// Lunar eclipses mainHelp item
		mainHelp.setItem(5, 2, ItemBuilder.from(Material.CLOCK).name(mainLunarEclipses).lore(mainLunarEclipsesLore)
				.asGuiItem(event -> {
					lunarEclipsesHelp.open(player);
				}));

		// mcMMO mainHelp item
		mainHelp.setItem(4, 3,
				ItemBuilder.from(Material.EXPERIENCE_BOTTLE).name(mainMcmmo).lore(mainMcmmoLore).asGuiItem(event -> {
					mcmmoHelp.open(player);
				}));

		// Create ItemStack for Mob Spawner
		ItemStack mobSpawner = new ItemStack(Material.SPAWNER);
		BlockStateMeta mobSpawnerMeta = (BlockStateMeta) mobSpawner.getItemMeta();
		CreatureSpawner spawner = (CreatureSpawner) mobSpawnerMeta.getBlockState();

		spawner.setSpawnedType(org.bukkit.entity.EntityType.PLAYER);
		mobSpawnerMeta.setBlockState(spawner);
		mobSpawner.setItemMeta(mobSpawnerMeta);

		// Silk spawners mainHelp item
		mainHelp.setItem(3, 4,
				ItemBuilder.from(mobSpawner).name(mainSilkSpawners).lore(mainSilkSpawnersLore).asGuiItem(event -> {
					silkSpawnersHelp.open(player);
				}));

		// Towny mainHelp item
		mainHelp.setItem(4, 7,
				ItemBuilder.from(Material.FILLED_MAP).name(mainTowny).lore(mainTownyLore).asGuiItem(event -> {
					townyHelp.open(player);
				}));

		// Selling items mainHelp item
		mainHelp.setItem(2, 8, ItemBuilder.from(Material.GOLD_INGOT).name(mainEconomy)
				.lore(List.of(mainEconomyLore1, mainEconomyLore2)).asGuiItem(event -> {
					economyHelp.open(player);
				}));

		// Graves mainHelp item
		mainHelp.setItem(5, 8, ItemBuilder.from(Material.SKELETON_SKULL).name(mainGraves)
				.lore(List.of(mainGravesLore1, mainGravesLore2)).asGuiItem(event -> {
					gravesHelp.open(player);
				}));

		// ====================
		// General help menu
		// ====================

		// TextComponents for item names
		Component generalServerMove = Component.text("Moving between servers").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component generalRules = Component.text("Rules").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component generalPvp = Component.text("PvP").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component generalTickets = Component.text("Tickets").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component generalTeleporting = Component.text("Teleporting").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component generalDiscord = Component.text("Discord").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);

		// TextComponents for Lore on each item
		Component generalServerMoveLore1 = Component
				.text("We have a hub server that you can use to move between servers")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component generalServerMoveLore2 = Component.text("You can use /server to see what servers are available")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component generalServerMoveLore3 = Component
				.text("To connect to other servers, use /server [name] to connect to your server")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component generalRulesLore1 = Component.text("1. No stealing, griefing, or hacking")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component generalRulesLore2 = Component.text("2. If a player wants you to leave their area, you must leave")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component generalRulesLore3 = Component.text("3. Replant crops in spawn farms")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component generalRulesLore4 = Component.text("4. Don't build any base next to spawn, it is a community area")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component generalRulesLore5 = Component.text("5. Follow Discord rules").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);
		Component generalPvpLore1 = Component
				.text("Players always have to agree to PvP each other outside of PvP designated areas")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component generalPvpLore2 = Component.text("Using cursed items also needs to be agreed upon")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component generalTicketsLore1 = Component.text("If you have an issue with the server, please submit a ticket")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component generalTicketsLore2 = Component.text("on Discord under ").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC).append(Component.text("#smp-tickets")
						.color(TextColor.color(colorDiscordBlurple)).decorate(TextDecoration.ITALIC));
		Component generalTicketHover = Component
				.text("Please give a description on what you are experiencing so we can resolve this quickly.")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component generalTicketMessage = Component.text("Direct link to #smp-tickets")
				.color(TextColor.color(colorDiscordBlurple)).decorate(TextDecoration.ITALIC)
				.hoverEvent(HoverEvent.showText(generalTicketHover))
				.clickEvent(ClickEvent.openUrl("https://discord.com/channels/791759753000622602/791759753000622605"));
		Component generalTeleportingLore1 = Component
				.text("To teleport to someone, run /tp [player] to teleport to them")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component generalTeleportingLore2 = Component
				.text("after they accept the teleport request. You can also use /back")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component generalTeleportingLore3 = Component.text("to teleport to your previous location.")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component generalDiscordLore1 = Component.text("Join the Discord chats for some chill Minecraft vibes")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component generalDiscordLore2 = Component.text("Grab some others to join you and talk and chill")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component generalDiscordMessage = Component.text("discord.gg/galaxe").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC).hoverEvent(HoverEvent.showText(generalDiscordLore2))
				.clickEvent(ClickEvent.openUrl("https://discord.gg/galaxe"));

		// Help Items
		// Moving between servers help item
		generalHelp.setItem(2, 5, ItemBuilder.from(Material.NETHER_STAR).name(generalServerMove)
				.lore(List.of(generalServerMoveLore1, generalServerMoveLore2, generalServerMoveLore3)).asGuiItem());

		// Rules help item
		generalHelp.setItem(3, 3,
				ItemBuilder
						.from(Material.WRITTEN_BOOK).name(generalRules).lore(List.of(generalRulesLore1,
								generalRulesLore2, generalRulesLore3, generalRulesLore4, generalRulesLore5))
						.asGuiItem(event -> {
							player.performCommand("rules");
						}));

		// PvP help item
		generalHelp.setItem(3, 7, ItemBuilder.from(Material.NETHERITE_SWORD).name(generalPvp)
				.lore(List.of(generalPvpLore1, generalPvpLore2)).asGuiItem());

		// Ticket help item
		generalHelp.setItem(4, 4, ItemBuilder.from(Material.NAME_TAG).name(generalTickets)
				.lore(List.of(generalTicketsLore1, generalTicketsLore2)).asGuiItem(event -> {
					player.sendMessage(generalTicketMessage);
				}));

		// Teleport help item
		generalHelp.setItem(4, 6, ItemBuilder.from(Material.COMPASS).name(generalTeleporting)
				.lore(List.of(generalTeleportingLore1, generalTeleportingLore2, generalTeleportingLore3)).asGuiItem());

		// Create ItemStack for Discord head
		ItemStack discordHead = createHead("b722098ae79c7abf002fe9684c773ea71db8919bb2ef2053ea0c0684c5a1ce4f");

		// Discord help item
		generalHelp.setItem(5, 5, ItemBuilder.from(discordHead).name(generalDiscord)
				.lore(List.of(generalDiscordLore1, generalDiscordLore2)).asGuiItem(event -> {
					player.sendMessage(generalDiscordMessage);
				}));

		// ====================
		// Lock items help menu
		// ====================

		// TextComponents for item names
		Component lwcExplained = Component.text("Securing your items").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component lwcLockItem = Component.text("Locking an item").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component lwcUnlockItem = Component.text("Unlocking an item").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component lwcShareLockedItem = Component.text("Sharing a locked item").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component lwcDisableAutoLock = Component.text("Disabling/enabling auto-lock")
				.color(TextColor.color(colorUltraViolet)).decorate(TextDecoration.BOLD);
		Component lwcProtectionFlags = Component.text("Protection flags").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component lwcTypesOfLocking = Component.text("Types of protection").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component lwcLockableItems = Component.text("Lockable items").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);

		// TextComponents for Lore on each item
		Component lwcExplainedLore1 = Component.text("Protecting your items in your base is quite important. On")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcExplainedLore2 = Component
				.text("the GalaxeSMP, you can protect your chests, doors, and other items")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcExplainedLore3 = Component.text("with a couple of commands, and even give your friends access")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcExplainedLore4 = Component.text("to your protected items!")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcLockItemLore1 = Component.text("To lock an item, run /cprivate to prevent other players to access")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcLockItemLore2 = Component.text("your chest/door/etc. You then punch the block you want to protect")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcUnlockItemLore1 = Component.text("To unlock an item to allow others to freely access your chest,")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcUnlockItemLore2 = Component.text("run /cremove to remove a protected block, and punch the block")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcUnlockItemLore3 = Component.text("you want to not protect anymore.")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcShareLockedItemLore1 = Component
				.text("To share a protected chest with someone, run /cmodify. This will")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcShareLockedItemLore2 = Component
				.text("show you all types of options for adding or removing your friends")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcShareLockedItemLore3 = Component.text("to a registered protection")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcDisableAutoLockLore1 = Component
				.text("Locking blocks is enabled by default to help protect all your items.")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcDisableAutoLockLore2 = Component
				.text("If you do not want to have this, run /cnolock to disable this action.")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcDisableAutoLockLore3 = Component
				.text("To re-enable this, run /cnolock again to toggle it back on.")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcProtectionFlagsLore1 = Component
				.text("You can use all sorts of nice granular protection flags to make life easier")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcProtectionFlagsLore2 = Component
				.text("for you or everyone. Redstone, automatic closing doors, hoppers, etc.")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcProtectionFlagsLore3 = Component.text("can be used to make things easier for you.")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcProtectionFlagsLore4 = Component
				.text("\u2727 /credstone allows redstone to interact with this protection")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcProtectionFlagsLore5 = Component
				.text("\u2727 /cmagnet allows items dropped to be sucked into a chest or nearby items")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcProtectionFlagsLore6 = Component.text("\u2727 /chopper allows hoppers to be used with a chest")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcProtectionFlagsLore7 = Component.text("\u2727 /cautoclose automagically closes a door if opened.")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcProtectionFlagsLore8 = Component.text("To see all the flags, run /lwc flag")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcTypesOfLockingLore1 = Component
				.text("There are many types of protections to use for your specific needs")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcTypesOfLockingLore2 = Component.text("\u2727 Private: Only you can access your protected item.")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcTypesOfLockingLore3 = Component.text("\u2727 Public: Anyone can freely access your protected item")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcTypesOfLockingLore4 = Component
				.text("\u2727 Password: Input a password to access your protected item")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcTypesOfLockingLore5 = Component
				.text("\u2727 Donation: Anyone can put items in but cannot take out, like a mailbox")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcTypesOfLockingLore6 = Component
				.text("\u2727 Display: Everyone can only see what is inside, great for lecterns")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcTypesOfLockingLore7 = Component
				.text("\u2727 Supply: Anyone can take items but cannot put anything in, like a supply crate.")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcLockableItemsLore1 = Component.text("A whole lot of items can be locked, such as:")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcLockableItemsLore2 = Component.text("Chests, Furnaces, Dispensers, Droppers,")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcLockableItemsLore3 = Component.text("Barrels, Hoppers, Lecterns, Shulker Boxes,")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcLockableItemsLore4 = Component.text("Doors, Trapdoors, Gates, Trapdoors,")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcLockableItemsLore5 = Component.text("Fence Gates, Signs Banners, and Banners")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		// Help Items
		// Locking items help item
		lockItemsHelp.setItem(2, 3, ItemBuilder.from(Material.IRON_DOOR).name(lwcLockItem)
				.lore(List.of(lwcLockItemLore1, lwcLockItemLore2)).asGuiItem(event -> {
					player.performCommand("cprivate");
				}));

		// Explaining protections help item
		lockItemsHelp.setItem(2, 5,
				ItemBuilder.from(Material.TRIPWIRE_HOOK).name(lwcExplained)
						.lore(List.of(lwcExplainedLore1, lwcExplainedLore2, lwcExplainedLore3, lwcExplainedLore4))
						.asGuiItem(event -> {
							player.performCommand("lwc");
						}));

		// Unlocking items help item
		lockItemsHelp.setItem(2, 7, ItemBuilder.from(Material.OAK_DOOR).name(lwcUnlockItem)
				.lore(List.of(lwcUnlockItemLore1, lwcUnlockItemLore2, lwcUnlockItemLore3)).asGuiItem(event -> {
					player.performCommand("cremove");
				}));

		// Sharing locked items help item
		lockItemsHelp.setItem(3, 4,
				ItemBuilder.from(Material.PAPER).name(lwcShareLockedItem)
						.lore(List.of(lwcShareLockedItemLore1, lwcShareLockedItemLore2, lwcShareLockedItemLore3))
						.asGuiItem(event -> {
							player.performCommand("cmodify");
						}));

		// Disabling auto-lock help item
		lockItemsHelp.setItem(3, 6,
				ItemBuilder.from(Material.STRUCTURE_VOID).name(lwcDisableAutoLock)
						.lore(List.of(lwcDisableAutoLockLore1, lwcDisableAutoLockLore2, lwcDisableAutoLockLore3))
						.asGuiItem(event -> {
							player.performCommand("cnolock");
						}));

		// Types of locking help item
		lockItemsHelp.setItem(4, 5, ItemBuilder.from(Material.LECTERN).name(lwcTypesOfLocking)
				.lore(List.of(lwcTypesOfLockingLore1, lwcTypesOfLockingLore2, lwcTypesOfLockingLore3,
						lwcTypesOfLockingLore4, lwcTypesOfLockingLore5, lwcTypesOfLockingLore6, lwcTypesOfLockingLore7))
				.asGuiItem(event -> {
					player.performCommand("lwc create");
				}));

		// Create ItemStack for enchanted book with Protection IV
		ItemStack enchantedBookItemStack = new ItemStack(Material.ENCHANTED_BOOK);
		enchantedBookItemStack.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);

		// Protection flags help item
		lockItemsHelp.setItem(5, 3,
				ItemBuilder.from(Material.KNOWLEDGE_BOOK).name(lwcProtectionFlags)
						.lore(List.of(lwcProtectionFlagsLore1, lwcProtectionFlagsLore2, lwcProtectionFlagsLore3,
								lwcProtectionFlagsLore4, lwcProtectionFlagsLore5, lwcProtectionFlagsLore6,
								lwcProtectionFlagsLore7, lwcProtectionFlagsLore8))
						.asGuiItem(event -> {
							player.performCommand("lwc flag");
						}));

		// Lockable items help item
		lockItemsHelp.setItem(5, 7,
				ItemBuilder.from(Material.CHEST).name(lwcLockableItems).lore(List.of(lwcLockableItemsLore1,
						lwcLockableItemsLore2, lwcLockableItemsLore3, lwcLockableItemsLore4, lwcLockableItemsLore5))
						.asGuiItem());

		// ====================
		// Lunar eclipses help menu
		// ====================

		// TextComponents for item names
		Component lunarEclipseExplained = Component.text("What is a Lunar Eclipse?")
				.color(TextColor.color(colorUltraViolet)).decorate(TextDecoration.BOLD);
		Component lunarEclipseLoot = Component.text("Lunar eclipse loot").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component lunarEclipseSchedule = Component.text("When does a Lunar eclipse happen?")
				.color(TextColor.color(colorUltraViolet)).decorate(TextDecoration.BOLD);
		Component lunarEclipseMobs = Component.text("The Monsters that will kill you")
				.color(TextColor.color(colorUltraViolet)).decorate(TextDecoration.BOLD);
		Component lunarEclipseHordes = Component.text("Hordes and hordes").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component lunarEclipseAftermath = Component.text("After a Lunar Eclipse")
				.color(TextColor.color(colorUltraViolet)).decorate(TextDecoration.BOLD);
		Component lunarEclipseTips = Component.text("Tips and Tricks to not die")
				.color(TextColor.color(colorUltraViolet)).decorate(TextDecoration.BOLD);

		// TextComponents for Lore on each item
		Component lunarEclipseExplainedLore1 = Component.text("Lunar Eclipses are a server-wide event on the SMP")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lunarEclipseExplainedLore2 = Component.text("where mobs are stronger and have more health,")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lunarEclipseExplainedLore3 = Component
				.text("but drop much better loot as a bonus for fighting hordes")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lunarEclipseLootLore1 = Component
				.text("Loot dropped can be many different combinations but consist of")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lunarEclipseLootLore2 = Component.text("Iron nuggets and ingots, Gold nuggets and ingots, Emeralds,")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lunarEclipseLootLore3 = Component.text("Diamonds, Netherite scrap or Netherite ingots")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lunarEclipseScheduleLore1 = Component.text("Each Lunar Eclipse happens every 21 Minecraft Days")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lunarEclipseScheduleLore2 = Component
				.text("You will also get messages on how much time before it happens")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lunarEclipseMobsLore1 = Component.text("Mobs are much stronger and have unique buffs for each")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lunarEclipseMobsLore2 = Component.text("mob, but be careful as mobs will deal 2x damage and")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lunarEclipseMobsLore3 = Component.text("have 3x the health the mob normally has")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lunarEclipseHordesLore1 = Component
				.text("Hordes of mobs are even more dangerous, as instead of a single mob,")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lunarEclipseHordesLore2 = Component.text("a large number of mobs spawn, all trying to do one thing:")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lunarEclipseHordesLore3 = Component.text("Kill you").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);
		Component lunarEclipseAftermathLore1 = Component
				.text("Once a Lunar Eclipse has passed, you will have until the next")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lunarEclipseAftermathLore2 = Component.text("one to prepare, but you get a full heal and also")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lunarEclipseAftermathLore3 = Component.text("Luck and Hero Of The Village for a bit to recover")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lunarEclipseTipsLore1 = Component.text("Here's some tips to not die")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lunarEclipseTipsLore2 = Component.text("\u2727 Bring some food with you, keep that hunger up")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lunarEclipseTipsLore3 = Component.text("\u2727 Never fight alone, friendship is magic ya know")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lunarEclipseTipsLore4 = Component.text("\u2727 Get some good armor and weapons, you'll need it")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lunarEclipseTipsLore5 = Component
				.text("\u2727 Don't be afraid to run, you can always come back later")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);

		// Help Items
		// Explaining Lunar Eclipses help item
		lunarEclipsesHelp.setItem(2, 5, ItemBuilder.from(Material.CRYING_OBSIDIAN).name(lunarEclipseExplained)
				.lore(List.of(lunarEclipseExplainedLore1, lunarEclipseExplainedLore2, lunarEclipseExplainedLore3))
				.asGuiItem());

		// Loot help item
		lunarEclipsesHelp.setItem(3, 4, ItemBuilder.from(Material.GOLD_INGOT).name(lunarEclipseLoot)
				.lore(List.of(lunarEclipseLootLore1, lunarEclipseLootLore2, lunarEclipseLootLore3)).asGuiItem());

		// Schedule help item
		lunarEclipsesHelp.setItem(3, 6, ItemBuilder.from(Material.CLOCK).name(lunarEclipseSchedule)
				.lore(List.of(lunarEclipseScheduleLore1, lunarEclipseScheduleLore2)).asGuiItem());

		// Mobs help item
		lunarEclipsesHelp.setItem(3, 4, ItemBuilder.from(Material.ZOMBIE_HEAD).name(lunarEclipseMobs)
				.lore(List.of(lunarEclipseMobsLore1, lunarEclipseMobsLore2, lunarEclipseMobsLore3)).asGuiItem());

		// Hordes help item
		lunarEclipsesHelp.setItem(4, 3, ItemBuilder.from(Material.SKELETON_SKULL).name(lunarEclipseHordes)
				.lore(List.of(lunarEclipseHordesLore1, lunarEclipseHordesLore2, lunarEclipseHordesLore3)).asGuiItem());

		// Aftermath help item
		lunarEclipsesHelp.setItem(4, 7, ItemBuilder.from(Material.GOLDEN_APPLE).name(lunarEclipseAftermath)
				.lore(List.of(lunarEclipseAftermathLore1, lunarEclipseAftermathLore2, lunarEclipseAftermathLore3))
				.asGuiItem());

		// Tips help item
		lunarEclipsesHelp.setItem(5, 5,
				ItemBuilder.from(Material.ENCHANTED_BOOK).name(lunarEclipseTips).lore(List.of(lunarEclipseTipsLore1,
						lunarEclipseTipsLore2, lunarEclipseTipsLore3, lunarEclipseTipsLore4, lunarEclipseTipsLore5))
						.asGuiItem());

		// ====================
		// mcMMO help menu
		// ====================

		// TextComponents for item names
		Component mcmmoExplained = Component.text("What is mcMMO?").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component mcmmoSkills = Component.text("Skills").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component mcmmoParty = Component.text("Parties").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component mcmmoAbilities = Component.text("Abilities").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component mcmmoLeaderboards = Component.text("Leaderboards").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);

		// TextComponents for Lore on each item
		Component mcmmoExplainedLore1 = Component.text("mcMMO is a plugin that allows for special skills for normal gameplay").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);
		Component mcmmoExplainedLore2 = Component.text("such as mining, woodcutting, and more!").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);
		Component mcmmoSkillsLore1 = Component.text("There are many skills to level up, such as:").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);
		Component mcmmoSkillsLore2 = Component.text("\u2727 Mining").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);
		Component mcmmoSkillsLore3 = Component.text("\u2727 Woodcutting").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);
		Component mcmmoSkillsLore4 = Component.text("\u2727 Herbalism").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);
		Component mcmmoSkillsLore5 = Component.text("\u2727 Excavation").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);
		Component mcmmoSkillsLore6 = Component.text("\u2727 Fishing").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);
		Component mcmmoSkillsLore7 = Component.text("\u2727 Repair").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);
		Component mcmmoSkillsLore8 = Component.text("... and so much more").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);
		Component mcmmoPartyLore1 = Component.text("You can create a party with your friends to level up skills together").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);
		Component mcmmoPartyLore2 = Component.text("and share experience with each other!").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);
		Component mcmmoPartyLore3 = Component.text("You can create a party by running /party").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);
		Component mcmmoAbilitiesLore1 = Component.text("As you level up your skills, you can unlock special abilities").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);
		Component mcmmoAbilitiesLore2 = Component.text("to help you in your adventures! More levels mean better abilities.").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);
		Component mcmmoAbilitiesLore3 = Component.text("You can check your abilities by running /inspect [player]").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);
		Component mcmmoAbilitiesLore4 = Component.text("You can also check others abilities by running /mctop").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);
		Component mcmmoLeaderboardsLore1 = Component.text("You can see who is the most powerful player by checking the leaderboards!").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);
		Component mcmmoLeaderboardsLore2 = Component.text("You can check the leaderboards by running /mctop").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);
		Component mcmmoLeaderboardsLore3 = Component.text("You can also check the leaderboards for a specific skill by running /mctop [skill]").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);

		// Help Items
		// Explaining mcMMO help item
		mcmmoHelp.setItem(2, 5, ItemBuilder.from(Material.ENCHANTED_BOOK).name(mcmmoExplained)
				.lore(List.of(mcmmoExplainedLore1, mcmmoExplainedLore2)).asGuiItem());

		// Skills help item
		mcmmoHelp.setItem(3, 4, ItemBuilder.from(Material.DIAMOND_PICKAXE).name(mcmmoSkills)
				.lore(List.of(mcmmoSkillsLore1, mcmmoSkillsLore2, mcmmoSkillsLore3, mcmmoSkillsLore4, mcmmoSkillsLore5, mcmmoSkillsLore6, mcmmoSkillsLore7, mcmmoSkillsLore8)).asGuiItem());

		// Party help item
		mcmmoHelp.setItem(3, 6, ItemBuilder.from(Material.CAKE).name(mcmmoParty)
				.lore(List.of(mcmmoPartyLore1, mcmmoPartyLore2, mcmmoPartyLore3)).asGuiItem());

		// Abilities help item
		mcmmoHelp.setItem(4, 3, ItemBuilder.from(Material.DRAGON_BREATH).name(mcmmoAbilities)
				.lore(List.of(mcmmoAbilitiesLore1, mcmmoAbilitiesLore2, mcmmoAbilitiesLore3, mcmmoAbilitiesLore4)).asGuiItem());

		// Leaderboards help item
		mcmmoHelp.setItem(4, 7, ItemBuilder.from(Material.PLAYER_HEAD).name(mcmmoLeaderboards)
				.lore(List.of(mcmmoLeaderboardsLore1, mcmmoLeaderboardsLore2, mcmmoLeaderboardsLore3)).asGuiItem());

		// ====================
		// Silk spawners help menu
		// ====================

		// TextComponents for item names
		Component silkSpawnersExplained = Component.text("What are Silk Spawners?")
				.color(TextColor.color(colorUltraViolet)).decorate(TextDecoration.BOLD);

		// TextComponents for Lore on each item
		Component silkSpawnersExplainedLore1 = Component.text("").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);

		// Help Items

		// ====================
		// Towny help menu
		// ====================

		// TextComponents for item names
		Component townyExplained = Component.text("What is Towny?").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);

		// TextComponents for Lore on each item
		Component townyExplainedLore1 = Component.text("").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);

		// Help Items

		// ====================
		// Economy help menu
		// ====================

		// TextComponents for item names
		Component ecoExplained = Component.text("Why money?").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);

		// TextComponents for Lore on each item
		Component ecoExplainedLore1 = Component.text("").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);

		// Help Items

		// ====================
		// Graves help menu
		// ====================

		// TextComponents for item names
		Component gravesExplained = Component.text("What are graves?").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);

		// TextComponents for Lore on each item
		Component gravesExplainedLore1 = Component.text("").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);

		// Help Items

		// ====================
		// Events help menu
		// ====================

		// TextComponents for item names
		Component eventsExplained = Component.text("").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);

		// TextComponents for Lore on each item
		Component eventsExplainedLore1 = Component.text("").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);

		// Help Items

		// ====================
		// Pronouns help menu
		// ====================

		// TextComponents for item names
		Component pronounsExplained = Component.text("").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);

		// TextComponents for Lore on each item
		Component pronounsExplainedLore1 = Component.text("").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);

		// Help Items
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
			// Format: {{row, column}, {row, column}, ...}
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
