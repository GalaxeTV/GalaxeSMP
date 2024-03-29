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
	private static Gui mainHelp = createGui("GalaxeMC Help Menu", 6, false);
	private static Gui generalHelp = createGui("GalaxeMC General Help", 6, true);
	private static Gui lockItemsHelp = createGui("GalaxeMC Locking Items", 6, true);
	private static Gui lunarEclipsesHelp = createGui("GalaxeMC Lunar Eclipses", 6, true);
	private static Gui mcmmoHelp = createGui("GalaxeMC mcMMO Help", 6, true);
	private static Gui silkSpawnersHelp = createGui("GalaxeMC Silk Spawners", 6, true);
	private static Gui townyHelp = createGui("GalaxeMC Towny", 6, true);
	private static Gui economyHelp = createGui("GalaxeMC Economy", 6, true);
	private static Gui gravesHelp = createGui("GalaxeMC Graves", 6, true);
	private static Gui eventsHelp = createGui("GalaxeMC Events", 6, true);
	private static Gui pronounsHelp = createGui("GalaxeMC Pronouns", 6, true);
	private static Gui genesisHelp = createGui("GalaxeMC Genesis", 6, true);
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
			case "genesis" :
				genesisHelp.open(player);
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
		Component mainGenesis = Component.text("Genesis").color(TextColor.color(colorUltraViolet))
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
		Component mainEconomyLore2 = Component.text("dollar company inside of the GalaxeMC")
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
		Component mainGenesisLore1 = Component.text("\"It's morphin time!\"").color(TextColor.color(colorLightOrange))
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component mainGenesisLore2 = Component.text(" - Power Rangers").color(TextColor.color(colorLightOrange))
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

		// Genesis mainHelp item
		mainHelp.setItem(6, 5, ItemBuilder.from(Material.END_CRYSTAL).name(mainGenesis)
				.lore(List.of(mainGenesisLore1, mainGenesisLore2)).asGuiItem(event -> {
					genesisHelp.open(player);
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
				.text("Please give a description on what you are experiencing so we can resolve this quickly")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component generalTicketMessage = Component.text("Direct link to #smp-tickets")
				.color(TextColor.color(colorDiscordBlurple)).decorate(TextDecoration.ITALIC)
				.hoverEvent(HoverEvent.showText(generalTicketHover))
				.clickEvent(ClickEvent.openUrl("https://discord.com/channels/791759753000622602/791759753000622605"));
		Component generalTeleportingLore1 = Component
				.text("To teleport to someone, run /tpa [player] to teleport to them")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component generalTeleportingLore2 = Component
				.text("after they accept the teleport request, you can also use /back")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component generalTeleportingLore3 = Component.text("to teleport to your previous location")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component generalTeleportingLore4 = Component
				.text("To have other players teleport to you, run /tpahere [player]")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component generalDiscordLore1 = Component.text("Join the Discord server for some chill Minecraft vibes")
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
		generalHelp.setItem(4, 6,
				ItemBuilder
						.from(Material.COMPASS).name(generalTeleporting).lore(List.of(generalTeleportingLore1,
								generalTeleportingLore2, generalTeleportingLore3, generalTeleportingLore4))
						.asGuiItem());

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
				.text("the GalaxeMC, you can protect your chests, doors, and other items")
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
		Component lwcUnlockItemLore3 = Component.text("you want to not protect anymore")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcShareLockedItemLore1 = Component
				.text("To share a protected chest with someone, run /cmodify, this will")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcShareLockedItemLore2 = Component
				.text("show you all types of options for adding or removing your friends")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcShareLockedItemLore3 = Component.text("to a registered protection")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcDisableAutoLockLore1 = Component
				.text("Locking blocks is enabled by default to help protect all your items")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcDisableAutoLockLore2 = Component
				.text("If you do not want to have this, run /cnolock to disable this action")
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
		Component lwcProtectionFlagsLore3 = Component.text("can be used to make things easier for you")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcProtectionFlagsLore4 = Component
				.text("\u2727 /credstone allows redstone to interact with this protection")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcProtectionFlagsLore5 = Component
				.text("\u2727 /cmagnet allows items dropped to be sucked into a chest or nearby items")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcProtectionFlagsLore6 = Component.text("\u2727 /chopper allows hoppers to be used with a chest")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcProtectionFlagsLore7 = Component.text("\u2727 /cautoclose automagically closes a door if opened")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcProtectionFlagsLore8 = Component.text("To see all the flags, run /lwc flag")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcTypesOfLockingLore1 = Component
				.text("There are many types of protections to use for your specific needs")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcTypesOfLockingLore2 = Component
				.text("\u2727 Private: Only the owner can access the protected item")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcTypesOfLockingLore3 = Component.text("\u2727 Public: Anyone can freely access the protected item")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcTypesOfLockingLore4 = Component.text("\u2727 Password: Requires a password for access")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcTypesOfLockingLore5 = Component
				.text("\u2727 Donation: Others can deposit items, but withdrawals are restricted (e.g., mailbox)")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcTypesOfLockingLore6 = Component
				.text("\u2727 Display: Items are visible to everyone (e.g., great for lecterns)")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcTypesOfLockingLore7 = Component
				.text("\u2727 Supply: Open for withdrawals, but no deposits allowed (e.g., supply crate)")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcLockableItemsLore1 = Component.text("A whole lot of items can be locked, such as:")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcLockableItemsLore2 = Component.text("Chests, Furnaces, Dispensers, Droppers,")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcLockableItemsLore3 = Component.text("Barrels, Hoppers, Lecterns, Shulker Boxes,")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component lwcLockableItemsLore4 = Component.text("Doors, Trapdoors, Gates, Signs, and Banners")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);

		// Help Items
		// Explaining protections help item
		lockItemsHelp.setItem(2, 5,
				ItemBuilder.from(Material.TRIPWIRE_HOOK).name(lwcExplained)
						.lore(List.of(lwcExplainedLore1, lwcExplainedLore2, lwcExplainedLore3, lwcExplainedLore4))
						.asGuiItem(event -> {
							player.performCommand("lwc");
						}));

		// Locking items help item
		lockItemsHelp.setItem(2, 3, ItemBuilder.from(Material.IRON_DOOR).name(lwcLockItem)
				.lore(List.of(lwcLockItemLore1, lwcLockItemLore2)).asGuiItem(event -> {
					player.performCommand("cprivate");
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
		lockItemsHelp
				.setItem(5, 7,
						ItemBuilder
								.from(Material.CHEST).name(lwcLockableItems).lore(List.of(lwcLockableItemsLore1,
										lwcLockableItemsLore2, lwcLockableItemsLore3, lwcLockableItemsLore4))
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
		Component mcmmoExplainedLore1 = Component
				.text("mcMMO is a plugin that allows for special skills for normal gameplay")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component mcmmoExplainedLore2 = Component.text("such as mining, woodcutting, and more!")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component mcmmoSkillsLore1 = Component.text("There are many skills to level up, such as:")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
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
		Component mcmmoPartyLore1 = Component
				.text("You can create a party with your friends to level up skills together")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component mcmmoPartyLore2 = Component.text("and share experience with each other!")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component mcmmoPartyLore3 = Component.text("You can create a party by running /party")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component mcmmoAbilitiesLore1 = Component.text("As you level up your skills, you can unlock special abilities")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component mcmmoAbilitiesLore2 = Component
				.text("to help you in your adventures! More levels mean better abilities")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component mcmmoAbilitiesLore3 = Component.text("You can check your abilities by running /inspect [player]")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component mcmmoAbilitiesLore4 = Component.text("You can also check others abilities by running /mctop")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component mcmmoLeaderboardsLore1 = Component
				.text("You can see who is the most powerful player by checking the leaderboards!")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component mcmmoLeaderboardsLore2 = Component.text("You can check the leaderboards by running /mctop")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component mcmmoLeaderboardsLore3 = Component
				.text("You can also check the leaderboards for a specific skill by running /mctop [skill]")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);

		// Help Items
		// Explaining mcMMO help item
		mcmmoHelp.setItem(2, 5, ItemBuilder.from(Material.ENCHANTED_BOOK).name(mcmmoExplained)
				.lore(List.of(mcmmoExplainedLore1, mcmmoExplainedLore2)).asGuiItem());

		// Skills help item
		mcmmoHelp
				.setItem(3, 4,
						ItemBuilder.from(Material.DIAMOND_PICKAXE).name(mcmmoSkills)
								.lore(List.of(mcmmoSkillsLore1, mcmmoSkillsLore2, mcmmoSkillsLore3, mcmmoSkillsLore4,
										mcmmoSkillsLore5, mcmmoSkillsLore6, mcmmoSkillsLore7, mcmmoSkillsLore8))
								.asGuiItem());

		// Party help item
		mcmmoHelp.setItem(3, 6, ItemBuilder.from(Material.CAKE).name(mcmmoParty)
				.lore(List.of(mcmmoPartyLore1, mcmmoPartyLore2, mcmmoPartyLore3)).asGuiItem(event -> {
					player.performCommand("party");
				}));

		// Abilities help item
		mcmmoHelp.setItem(4, 3,
				ItemBuilder.from(Material.DRAGON_BREATH).name(mcmmoAbilities).lore(
						List.of(mcmmoAbilitiesLore1, mcmmoAbilitiesLore2, mcmmoAbilitiesLore3, mcmmoAbilitiesLore4))
						.asGuiItem(event -> {
							player.performCommand("inspect " + player.getName());
						}));

		// Leaderboards help item
		mcmmoHelp.setItem(4, 7,
				ItemBuilder.from(Material.NETHER_STAR).name(mcmmoLeaderboards)
						.lore(List.of(mcmmoLeaderboardsLore1, mcmmoLeaderboardsLore2, mcmmoLeaderboardsLore3))
						.asGuiItem(event -> {
							player.performCommand("mctop");
						}));

		// ====================
		// Silk spawners help menu
		// ====================

		// TextComponents for item names
		Component silkSpawnersExplained = Component.text("What are Silk Spawners?")
				.color(TextColor.color(colorUltraViolet)).decorate(TextDecoration.BOLD);
		Component silkSpawnersObtaining = Component.text("Obtaining a Silk Spawner")
				.color(TextColor.color(colorUltraViolet)).decorate(TextDecoration.BOLD);
		Component silkSpawnersPlacing = Component.text("Placing a Silk Spawner")
				.color(TextColor.color(colorUltraViolet)).decorate(TextDecoration.BOLD);
		Component silkSpawnerscChanging = Component.text("Changing a Silk Spawner")
				.color(TextColor.color(colorUltraViolet)).decorate(TextDecoration.BOLD);

		// TextComponents for Lore on each item
		Component silkSpawnersExplainedLore1 = Component.text("Spawners before couldn't be mined, which is not great")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component silkSpawnersExplainedLore2 = Component.text("when you want to move a spawner to a new location")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component silkSpawnersExplainedLore3 = Component.text("or to make a farm. With Silk Spawners, you can mine")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component silkSpawnersExplainedLore4 = Component.text("spawners with a Silk Touch pickaxe to get the spawner")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component silkSpawnersExplainedLore5 = Component.text("item, which can be placed down again")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component silkSpawnersObtainingLore1 = Component.text("To obtain a Silk Spawner, you need to mine a spawner")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component silkSpawnersObtainingLore2 = Component.text("with a Silk Touch pickaxe. You can also buy a spawner")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component silkSpawnersObtainingLore3 = Component.text("from other players.")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component silkSpawnersPlacingLore1 = Component.text("To place a Silk Spawner, just place it on any block")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component silkSpawnersPlacingLore2 = Component.text("Ya silly little goober")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component silkSpawnersChangingLore1 = Component.text("To change a Silk Spawner, you need to have a spawn egg")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component silkSpawnersChangingLore2 = Component.text("of the mob you want to change it to, you can also get")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component silkSpawnersChangingLore3 = Component.text("spawn eggs from the shop or from other players")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);

		// Help Items
		// Explaining Silk Spawners help item
		silkSpawnersHelp.setItem(2, 5,
				ItemBuilder.from(Material.SPAWNER).name(silkSpawnersExplained)
						.lore(List.of(silkSpawnersExplainedLore1, silkSpawnersExplainedLore2,
								silkSpawnersExplainedLore3, silkSpawnersExplainedLore4, silkSpawnersExplainedLore5))
						.asGuiItem());

		// Obtaining a Silk Spawner help item
		silkSpawnersHelp.setItem(3, 4,
				ItemBuilder.from(Material.IRON_INGOT).name(silkSpawnersObtaining).lore(
						List.of(silkSpawnersObtainingLore1, silkSpawnersObtainingLore2, silkSpawnersObtainingLore3))
						.asGuiItem());

		// Placing a Silk Spawner help item
		silkSpawnersHelp.setItem(3, 6, ItemBuilder.from(Material.WRITABLE_BOOK).name(silkSpawnersPlacing)
				.lore(List.of(silkSpawnersPlacingLore1, silkSpawnersPlacingLore2)).asGuiItem());

		// Changing a Silk Spawner help item
		silkSpawnersHelp.setItem(4, 5,
				ItemBuilder.from(Material.CREEPER_SPAWN_EGG).name(silkSpawnerscChanging)
						.lore(List.of(silkSpawnersChangingLore1, silkSpawnersChangingLore2, silkSpawnersChangingLore3))
						.asGuiItem());

		// ====================
		// Towny help menu
		// ====================

		// TextComponents for item names
		Component townyExplained = Component.text("What is Towny?").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component townyClaiming = Component.text("Claiming").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component townyTowns = Component.text("Towns").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component townyNations = Component.text("Nations").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component townyJoinTown = Component.text("Joining a Town").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component townyGrowTown = Component.text("Growing a Town").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component townyJoinNation = Component.text("Joining a Nation").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component townyMoney = Component.text("Money").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component townyNomad = Component.text("Nomad").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component townyRuins = Component.text("Ruins").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component townyPlots = Component.text("Plots").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component townyResident = Component.text("Resident").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component townyMayor = Component.text("Mayor").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component townyKing = Component.text("Nation Leader").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component townyWiki = Component.text("Wiki").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);

		// TextComponents for Lore on each item
		Component townyExplainedLore1 = Component.text("Ever wanted to rule the world?")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyExplainedLore2 = Component.text("Well, now you can! You can be just like Fallen Kingdom")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyExplainedLore3 = Component.text("and rule your own kingdom! (minus the CaptainSparklez)")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyClaimingLore1 = Component.text("You can claim land by using a golden shovel")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyClaimingLore2 = Component.text("and right clicking two corners of the area you want to claim")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyClaimingLore3 = Component.text("You can also unclaim land by using a golden shovel")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyClaimingLore4 = Component.text("and right clicking a corner of the area you want to unclaim")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyTownsLore1 = Component.text("You can create a town by running /town new [town name]")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyTownsLore2 = Component
				.text("If you want to delete your town, you can by running /town delete [town name]")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyTownsLore3 = Component
				.text("Rename your town if the name isn't fitting by running /town set name [town name]")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyTownsLore4 = Component.text("You can also set the town spawn by running /town set spawn")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyTownsLore5 = Component
				.text("There is an ungodly amount of things you can do with Towns, so please refer to the Wiki!")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyNationsLore1 = Component.text("You can create a nation by running /nation new [nation name]")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyNationsLore2 = Component
				.text("If you want to delete your nation, you can by running /nation delete [nation name]")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyNationsLore3 = Component
				.text("Rename your nation if the name isn't fitting by running /nation set name [nation name]")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyNationsLore4 = Component.text("You can also set the nation spawn by running /nation set spawn")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyNationsLore5 = Component.text(
				"If you thought towns were a lot, nations have even more things, lots of studying to do on the Wiki")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyJoinTownLore1 = Component.text("You can join a town by running /town join [town name]")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyJoinTownLore2 = Component.text("You can also leave a town by running /town leave")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyJoinTownLore3 = Component.text("You can also check the town you are in by running /town")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyGrowTownLore1 = Component
				.text("You can grow a town by inviting players to your town by running /town add [player]")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyGrowTownLore2 = Component
				.text("You can also promote players to assistant by running /town rank add [player] assistant")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyGrowTownLore3 = Component
				.text("You can also promote players to mayor by running /town rank add [player] mayor")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyJoinNationLore1 = Component
				.text("As a mayor join a nation by running /nation join [nation name]")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyJoinNationLore2 = Component
				.text("If this nation isn't for you, you can leave a nation by running /nation leave")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyJoinNationLore3 = Component.text("You can also check the nation you are in by running /nation")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyJoinNationLore4 = Component
				.text("Nation leaders can also invite towns to their nation by running /nation add [town name]")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyMoneyLore1 = Component
				.text("Depending on the town, there may be taxes or some fee to live there")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyMoneyLore2 = Component
				.text("This fee will automatically be deducted, and you can check your balance by running /balance")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyMoneyLore3 = Component
				.text("There is even a way for you to check taxes in general for the town with /towny prices")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyNomadLore1 = Component.text("If you don't want to join a town or nation, you can be a nomad")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyNomadLore2 = Component.text("Nomads just roam or live not in a town. You don't pay taxes, but")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyNomadLore3 = Component.text("you also don't get the benefits of being in a town or nation")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyRuinsLore1 = Component
				.text("What happens if one stops paying taxes or the town no longer is active?")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyRuinsLore2 = Component
				.text("Well, the town will be marked as ruins, and you can claim it by running /town claim")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyRuinsLore3 = Component
				.text("Ruins are griefable, residents can't do anything really do much, and other debuffs.")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyPlotsLore1 = Component
				.text("Want to buy a plot in a town? Run /plot claim to get a plot for a price")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyPlotsLore2 = Component.text("You can also sell plots in a town by running /plot forsale [price]")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyPlotsLore3 = Component.text("Stats of plots are always /plot")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyResidentLore1 = Component.text("You can check your towny status by running /resident")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyResidentLore2 = Component
				.text("You can also check other players towny status by running /resident [player]")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyMayorLore1 = Component.text("Mayors run towns, kinda self explanatory...")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyMayorLore2 = Component.text("Mayors can also check the town bank by running /town bankhistory")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyMayorLore3 = Component
				.text("If you're a mayor, make sure your town is running nice and smooth for everyone")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyKingLore1 = Component.text("Nation leaders, are in-fact, leaders of a nation")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyKingLore2 = Component
				.text("Nation leaders can also check the nation bank by running /nation bankhistory")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyKingLore3 = Component
				.text("Hope you leaders out there leading nations all are leading good towns and stuff")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyWikiLore1 = Component
				.text("You know how much is not in this helpful little menu? A lot apparently")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyWikiLore2 = Component
				.text("I am not joking, there is so much and I can only put in so much stuff here")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyWikiLore3 = Component
				.text("and I seriously don't know how even after I put this all in here, there is still more")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyWikiLore4 = Component.text("So please, please, PLEASE, read the wiki")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyWikiMessage = Component.text("Click here for the encyclopedia of Towny")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component townyWikiHover = Component.text("By the time you are finished, you are going to be galaxy brain")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);

		// Help Items
		// Explaining Towny help item
		townyHelp.setItem(1, 5, ItemBuilder.from(Material.ENCHANTED_BOOK).name(townyExplained)
				.lore(List.of(townyExplainedLore1, townyExplainedLore2, townyExplainedLore3)).asGuiItem(event -> {
					player.performCommand("towny");
				}));

		// Claiming help item
		townyHelp.setItem(2, 2,
				ItemBuilder.from(Material.GOLDEN_SHOVEL).name(townyClaiming)
						.lore(List.of(townyClaimingLore1, townyClaimingLore2, townyClaimingLore3, townyClaimingLore4))
						.asGuiItem(event -> {
							player.performCommand("town claim");
						}));

		// Towns help item
		townyHelp.setItem(2, 4,
				ItemBuilder.from(Material.BRICKS).name(townyTowns).lore(
						List.of(townyTownsLore1, townyTownsLore2, townyTownsLore3, townyTownsLore4, townyTownsLore5))
						.asGuiItem(event -> {
							player.performCommand("town");
						}));

		// Nations help item
		townyHelp.setItem(2, 6,
				ItemBuilder.from(Material.GOLD_BLOCK).name(townyNations).lore(List.of(townyNationsLore1,
						townyNationsLore2, townyNationsLore3, townyNationsLore4, townyNationsLore5))
						.asGuiItem(event -> {
							player.performCommand("nation");
						}));

		// Joining a Town help item
		townyHelp.setItem(2, 8, ItemBuilder.from(Material.IRON_DOOR).name(townyJoinTown)
				.lore(List.of(townyJoinTownLore1, townyJoinTownLore2, townyJoinTownLore3)).asGuiItem());

		// Growing a Town help item
		townyHelp.setItem(3, 1, ItemBuilder.from(Material.CRAFTING_TABLE).name(townyGrowTown)
				.lore(List.of(townyGrowTownLore1, townyGrowTownLore2, townyGrowTownLore3)).asGuiItem());

		// Joining a Nation help item
		townyHelp.setItem(3, 3,
				ItemBuilder.from(Material.LECTERN).name(townyJoinNation).lore(
						List.of(townyJoinNationLore1, townyJoinNationLore2, townyJoinNationLore3, townyJoinNationLore4))
						.asGuiItem());

		// Money help item
		townyHelp.setItem(3, 5, ItemBuilder.from(Material.GOLD_INGOT).name(townyMoney)
				.lore(List.of(townyMoneyLore1, townyMoneyLore2, townyMoneyLore3)).asGuiItem(event -> {
					player.performCommand("balance");
				}));

		// Ruins help item
		townyHelp.setItem(3, 7, ItemBuilder.from(Material.MOSSY_COBBLESTONE).name(townyRuins)
				.lore(List.of(townyRuinsLore1, townyRuinsLore2, townyRuinsLore3)).asGuiItem());

		// Plots help item
		townyHelp.setItem(3, 9, ItemBuilder.from(Material.SCAFFOLDING).name(townyPlots)
				.lore(List.of(townyPlotsLore1, townyPlotsLore2, townyPlotsLore3)).asGuiItem(event -> {
					player.performCommand("plot");
				}));

		// Nomad help item
		townyHelp.setItem(4, 2, ItemBuilder.from(Material.LEATHER_BOOTS).name(townyNomad)
				.lore(List.of(townyNomadLore1, townyNomadLore2, townyNomadLore3)).asGuiItem());

		// Resident help item
		townyHelp.setItem(4, 4,
				ItemBuilder.from(createHead("126ec1ca185b47aad39f931db8b0a8500ded86a127a204886ed4b3783ad1775c"))
						.name(townyResident).lore(List.of(townyResidentLore1, townyResidentLore2)).asGuiItem(event -> {
							player.performCommand("resident");
						}));

		// Mayor help item
		townyHelp.setItem(4, 6,
				ItemBuilder.from(createHead("26599cbb8868237e3d864bb128ac51a0ec4a5a85e241232ee3ed6b0afac9b5c7"))
						.name(townyMayor).lore(List.of(townyMayorLore1, townyMayorLore2, townyMayorLore3))
						.asGuiItem(event -> {
							player.performCommand("town");
						}));

		// King help item
		townyHelp.setItem(4, 8,
				ItemBuilder.from(createHead("6225dde9a1b16d7ab3bbba210dc786334b692e9be737068435686c87dc93d947"))
						.name(townyKing).lore(List.of(townyKingLore1, townyKingLore2, townyKingLore3))
						.asGuiItem(event -> {
							player.performCommand("nation");
						}));

		// Wiki help item
		townyHelp.setItem(5, 5, ItemBuilder.from(Material.KNOWLEDGE_BOOK).name(townyWiki)
				.lore(List.of(townyWikiLore1, townyWikiLore2, townyWikiLore3, townyWikiLore4)).asGuiItem(event -> {
					player.sendMessage(townyWikiMessage.hoverEvent(HoverEvent.showText(townyWikiHover)).clickEvent(
							ClickEvent.openUrl("https://github.com/TownyAdvanced/Towny/wiki/How-Towny-Works")));
				}));

		// ====================
		// Economy help menu
		// ====================

		// TextComponents for item names
		Component ecoExplained = Component.text("Why money?").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component ecoSellingItems = Component.text("Selling items").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component ecoBuyingItems = Component.text("Buying items").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component ecoBank = Component.text("Bank").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component ecoShop = Component.text("Shop").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component ecoItemWorth = Component.text("Item Values").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);

		// TextComponents for Lore on each item
		Component ecoExplainedLore1 = Component.text("Outside of trading items for goods or services,")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component ecoExplainedLore2 = Component.text("money is a great way to get what you need quickly")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component ecoExplainedLore3 = Component.text("You can earn money by selling items to the server")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component ecoExplainedLore4 = Component.text("or other players, or by trading items for money")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component ecoSellingItemsLore1 = Component.text("You can sell items to the server by running /sell,")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component ecoSellingItemsLore2 = Component.text("you can specify an item with an amount, what is")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component ecoSellingItemsLore3 = Component.text("in your hand, or all items in your inventory")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component ecoBuyingItemsLore1 = Component.text("You can buy items from the server by going to the server shop")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component ecoBuyingItemsLore2 = Component
				.text("You can also buy items from other players either directly through")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component ecoBuyingItemsLore3 = Component.text("/pay or a player-run sign shop")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component ecoBankLore1 = Component.text("All money you earn is stored in a bank automatically for you")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component ecoBankLore2 = Component.text("You can check your balance by running /balance")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component ecoBankLore3 = Component.text("There is also a leaderboard on who has the most money")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component ecoBankLore4 = Component.text("You can check the leaderboard by running /baltop")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component ecoShopLore1 = Component.text("The server will have a shop where you can buy items,")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component ecoShopLore2 = Component.text("but you can also make your own shop to sell items to other players")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component ecoShopLore3 = Component
				.text("You can make a sign shop by looking at the example by clicking on this item")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component ecoShopMessage1 = Component.text("Trade Sign Example:").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);
		Component ecoShopMessage2 = Component.text("[Trade]").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);
		Component ecoShopMessage3 = Component.text("$69420").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);
		Component ecoShopMessage4 = Component.text("1 dirt:794").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);
		Component ecoShopMessage5 = Component.text("For more information, click here")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component ecoShopHover = Component.text("Click to go to the wiki").color(TextColor.color(colorLightOrange))
				.decorate(TextDecoration.ITALIC);
		Component ecoItemWorthLore1 = Component.text("You can check the value of an item by running /worth")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);

		// Help Items
		// Explaining money help item
		economyHelp.setItem(2, 5, ItemBuilder.from(Material.GOLD_INGOT).name(ecoExplained)
				.lore(List.of(ecoExplainedLore1, ecoExplainedLore2, ecoExplainedLore3, ecoExplainedLore4)).asGuiItem());

		// Selling items help item
		economyHelp.setItem(3, 4, ItemBuilder.from(Material.DIAMOND).name(ecoSellingItems)
				.lore(List.of(ecoSellingItemsLore1, ecoSellingItemsLore2, ecoSellingItemsLore3)).asGuiItem());

		// Buying items help item
		economyHelp.setItem(3, 6, ItemBuilder.from(Material.EMERALD).name(ecoBuyingItems)
				.lore(List.of(ecoBuyingItemsLore1, ecoBuyingItemsLore2, ecoBuyingItemsLore3)).asGuiItem());

		// Bank help item
		economyHelp.setItem(4, 3, ItemBuilder.from(Material.WRITABLE_BOOK).name(ecoBank)
				.lore(List.of(ecoBankLore1, ecoBankLore2, ecoBankLore3, ecoBankLore4)).asGuiItem(event -> {
					player.performCommand("balance");
				}));

		// Shop help item
		economyHelp.setItem(4, 7, ItemBuilder.from(Material.BARREL).name(ecoShop)
				.lore(List.of(ecoShopLore1, ecoShopLore2, ecoShopLore3)).asGuiItem(event -> {
					for (Component message : List.of(ecoShopMessage1, ecoShopMessage2, ecoShopMessage3, ecoShopMessage4,
							ecoShopMessage5)) {
						player.sendMessage(message.hoverEvent(HoverEvent.showText(ecoShopHover)).clickEvent(
								ClickEvent.openUrl("https://wiki.mc-ess.net/wiki/Sign_Tutorial#Trade_Sign")));
					}
				}));

		// Item worth help item
		economyHelp.setItem(5, 5, ItemBuilder.from(Material.LAPIS_LAZULI).name(ecoItemWorth)
				.lore(List.of(ecoItemWorthLore1)).asGuiItem(event -> {
					player.performCommand("worth inventory");
				}));

		// ====================
		// Graves help menu
		// ====================

		// TextComponents for item names
		Component gravesExplained = Component.text("What are graves?").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component gravesDeath = Component.text("Dying").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component gravesLooting = Component.text("Looting").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component gravesLocation = Component.text("Finding your grave").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);

		// TextComponents for Lore on each item
		Component gravesExplainedLore1 = Component.text("When you die, you respawn. Easy right?")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component gravesExplainedLore2 = Component.text("Well, what if you die in lava? Or in the void?")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component gravesExplainedLore3 = Component
				.text("Or your items are so far away they will disappear and are lost?")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component gravesExplainedLore4 = Component.text("Well, with graves, you can get your items back!")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component gravesDeathLore1 = Component.text("When you die, a grave will spawn at your location")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component gravesDeathLore2 = Component.text("and your items will be stored in the grave")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component gravesDeathLore3 = Component.text("You can also see the location of your grave by running /graves")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component gravesDeathLore4 = Component.text("Be quick though, as your grave will disappear after 3 hours")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component gravesLootingLore1 = Component.text("You can loot your grave by right clicking on it")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component gravesLootingLore2 = Component.text("However, other people can get your items after 5 minutes")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component gravesLootingLore3 = Component.text("You can loot other graves by breaking them")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component gravesLocationLore1 = Component
				.text("When you die, you can see the location of your grave with a compass")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component gravesLocationLore2 = Component.text("The compass will show how far away your grave is")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component gravesLocationLore3 = Component.text("as well as the time left before your grave disappears")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);

		// Help Items
		// Explaining graves help item
		gravesHelp.setItem(2, 5,
				ItemBuilder.from(Material.GRAVEL).name(gravesExplained).lore(
						List.of(gravesExplainedLore1, gravesExplainedLore2, gravesExplainedLore3, gravesExplainedLore4))
						.asGuiItem());

		// Dying help item
		gravesHelp.setItem(3, 4, ItemBuilder.from(Material.BONE).name(gravesDeath)
				.lore(List.of(gravesDeathLore1, gravesDeathLore2, gravesDeathLore3, gravesDeathLore4)).asGuiItem());

		// Looting help item
		gravesHelp.setItem(3, 6, ItemBuilder.from(Material.CHEST).name(gravesLooting)
				.lore(List.of(gravesLootingLore1, gravesLootingLore2, gravesLootingLore3)).asGuiItem());

		// Location help item
		gravesHelp.setItem(4, 5, ItemBuilder.from(Material.COMPASS).name(gravesLocation)
				.lore(List.of(gravesLocationLore1, gravesLocationLore2, gravesLocationLore3)).asGuiItem());

		// ====================
		// Events help menu
		// ====================

		// TextComponents for item names
		Component eventsExplained = Component.text("Server-wide events").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component eventsAnnouncement = Component.text("Announcements").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component eventsCreate = Component.text("Creating events").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component eventsJoin = Component.text("Joining events").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component eventsSupport = Component.text("Getting support for events").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);

		// TextComponents for Lore on each item
		Component eventsExplainedLore1 = Component.text("We have some very special events that run throughout the")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component eventsExplainedLore2 = Component
				.text("year thanks to our community. These events are fun to be a part of")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component eventsExplainedLore3 = Component.text("and are a great way to get to know the community better")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component eventsExplainedLore4 = Component
				.text("You can also create your own events for the community to enjoy")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component eventsAnnouncementLore1 = Component
				.text("When an event is happening, an announcement will be made on Discord")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component eventsAnnouncementLore2 = Component
				.text("This announcement will tell you what the event is, when it is")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component eventsAnnouncementLore3 = Component.text("and what special things go along with it")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component eventsCreateLore1 = Component.text("Creating an event is as simple as just making it known to people")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component eventsCreateLore2 = Component.text("You can also make a ticket to get support for your event")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component eventsCreateLore3 = Component.text("to help get visibility for your event")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component eventsJoinLore1 = Component.text("To join an event, you can just join the server and participate")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component eventsJoinLore2 = Component
				.text("No commands, just /tp to who is at the event and now you are participating")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component eventsJoinLore3 = Component.text("Ya silly goober, just have fun!")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component eventsSupportLore1 = Component.text("If you need support for an event, you can make a ticket")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component eventsSupportLore2 = Component
				.text("This will help get visibility for your event and get people to join")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component eventsSupportLore3 = Component.text(
				"You can also have some staff members help you with some aspect of the event, such as block protection,")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component eventsSupportLore4 = Component
				.text("special items you can't get, or even just to help you with the event")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component eventsSupportLore5 = Component.text("We are here to make your event as fun as possible!")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component eventsSupportMessage = Component.text("Direct link to #smp-tickets")
				.color(TextColor.color(colorDiscordBlurple)).decorate(TextDecoration.ITALIC);

		// Help Items
		// Explaining events help item
		eventsHelp.setItem(2, 5,
				ItemBuilder.from(Material.FIREWORK_ROCKET).name(eventsExplained).lore(
						List.of(eventsExplainedLore1, eventsExplainedLore2, eventsExplainedLore3, eventsExplainedLore4))
						.asGuiItem());

		// Announcements help item
		eventsHelp.setItem(3, 4, ItemBuilder.from(Material.PAPER).name(eventsAnnouncement)
				.lore(List.of(eventsAnnouncementLore1, eventsAnnouncementLore2, eventsAnnouncementLore3)).asGuiItem());

		// Creating events help item
		eventsHelp.setItem(3, 6, ItemBuilder.from(Material.WRITABLE_BOOK).name(eventsCreate)
				.lore(List.of(eventsCreateLore1, eventsCreateLore2, eventsCreateLore3)).asGuiItem());

		// Joining events help item
		eventsHelp.setItem(4, 3, ItemBuilder.from(Material.CAKE).name(eventsJoin)
				.lore(List.of(eventsJoinLore1, eventsJoinLore2, eventsJoinLore3)).asGuiItem());

		// Support for events help item
		eventsHelp
				.setItem(4, 7,
						ItemBuilder
								.from(Material.BOOK).name(eventsSupport).lore(List.of(eventsSupportLore1,
										eventsSupportLore2, eventsSupportLore3, eventsSupportLore4, eventsSupportLore5))
								.asGuiItem(event -> {
									player.sendMessage(eventsSupportMessage.clickEvent(ClickEvent.openUrl(
											"https://discord.com/channels/791759753000622602/791759753000622605")));
								}));

		// ====================
		// Pronouns help menu
		// ====================

		// TextComponents for item names
		Component pronounsExplained = Component.text("Pronouns").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component pronounsSet = Component.text("Setting your pronouns").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component pronounsView = Component.text("Viewing other's pronouns").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component pronounsList = Component.text("List of pronouns").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component pronounsClear = Component.text("Clearing your pronouns").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);

		// TextComponents for Lore on each item
		Component pronounsExplainedLore1 = Component
				.text("We believe in inclusivity and respecting individual identities,")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component pronounsExplainedLore2 = Component
				.text("to help with this, we have a pronouns system to allow you to set your pronouns")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component pronounsExplainedLore3 = Component.text("Express yourself freely and comfortably, just be you!")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component pronounsSetLore1 = Component.text("To set your pronouns, run /pronouns set [pronouns]")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component pronounsSetLore2 = Component
				.text("You can also set your pronouns by running /pronouns set and clicking on the pronouns you want")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component pronounsViewLore1 = Component
				.text("Viewing someone's pronouns is seen through every single chat message.")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component pronounsViewLore2 = Component.text("You can also view your own pronouns using /pronouns view")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component pronounsListLore1 = Component
				.text("To prevent abuse of pronouns, we have a set list of pre-approved pronouns")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component pronounsListLore2 = Component.text("You can view the full list of pronouns by running /pronouns list")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component pronounsListLore3 = Component
				.text("If you don't see your pronouns, please make a ticket so we can add your pronouns to the server")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component pronounsClearLore1 = Component.text("To clear your pronouns, run /pronouns clear")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component pronounsClearLore2 = Component
				.text("This will remove your pronouns from chat and other menus and items")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);

		// Help Items
		// Explaining pronouns help item
		pronounsHelp.setItem(2, 5, ItemBuilder.from(Material.PAPER).name(pronounsExplained)
				.lore(List.of(pronounsExplainedLore1, pronounsExplainedLore2, pronounsExplainedLore3)).asGuiItem());

		// Setting your pronouns help item
		pronounsHelp.setItem(3, 4, ItemBuilder.from(Material.WRITABLE_BOOK).name(pronounsSet)
				.lore(List.of(pronounsSetLore1, pronounsSetLore2)).asGuiItem(event -> {
					player.performCommand("pronouns set");
				}));

		// Viewing other's pronouns help item
		pronounsHelp.setItem(3, 6, ItemBuilder.from(Material.PLAYER_HEAD).name(pronounsView)
				.lore(List.of(pronounsViewLore1, pronounsViewLore2)).asGuiItem(event -> {
					player.performCommand("pronouns view " + player.getName());
				}));

		// List of pronouns help item
		pronounsHelp.setItem(4, 3, ItemBuilder.from(Material.BOOK).name(pronounsList)
				.lore(List.of(pronounsListLore1, pronounsListLore2, pronounsListLore3)).asGuiItem(event -> {
					player.performCommand("pronouns list");
				}));

		// Clearing your pronouns help item
		pronounsHelp.setItem(4, 7, ItemBuilder.from(Material.STRUCTURE_VOID).name(pronounsClear)
				.lore(List.of(pronounsClearLore1, pronounsClearLore2)).asGuiItem(event -> {
					player.performCommand("pronouns clear");
				}));

		// ====================
		// Genesis help menu
		// ====================
		// TextComponents for item names
		Component genesisExplained = Component.text("Genesis").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component genesisClasses = Component.text("Classes").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component genesisAbilities = Component.text("Abilities").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component genesisWeaknesses = Component.text("Weaknesses").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);
		Component genesisSpawn = Component.text("Spawns").color(TextColor.color(colorUltraViolet))
				.decorate(TextDecoration.BOLD);

		// TextComponents for Lore on each item
		Component genesisExplainedLore1 = Component.text("Genesis is a whole new plugin that we have developed")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component genesisExplainedLore2 = Component.text("to make playing Minecraft more... interesting")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component genesisExplainedLore3 = Component.text("You become the mob, and you can do mob things, like a mob")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component genesisExplainedLore4 = Component
				.text("Special abilities are given to you, but debuffs are also given")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component genesisClassesLore1 = Component
				.text("There is a list of classes that you can choose from through a menu")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component genesisClassesLore2 = Component
				.text("Choosing a class through /genesis will show a menu for all classes to choose from")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component genesisClassesLore3 = Component.text("Once you pick a class, you can't change it for 10 days")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component genesisAbilitiesLore1 = Component.text("Each class has abilities that you can use to your advantage")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component genesisAbilitiesLore2 = Component.text("Some are passive, and some are activated with a button press")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component genesisAbilitiesLore3 = Component.text("Each class is unique, so try them all out!")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component genesisAbilitiesLore4 = Component.text("All abilities are listed in the Genesis menu")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component genesisWeaknessesLore1 = Component
				.text("With these abilities, there are also weaknesses to make things interesting")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component genesisWeaknessesLore2 = Component
				.text("Some may be losing a couple hearts, or not being able to stand in direct sunlight")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component genesisWeaknessesLore3 = Component.text("Choose wisely, as you can't change your class for 10 days")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component genesisSpawnLore1 = Component.text("When in the spawn of the survival server, you will not have")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component genesisSpawnLore2 = Component.text("your debuffs, but be careful as if you step outside of spawn,")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);
		Component genesisSpawnLore3 = Component.text("you will have your debuffs again")
				.color(TextColor.color(colorLightOrange)).decorate(TextDecoration.ITALIC);

		// Help Items
		// Explaining genesis help item
		genesisHelp.setItem(2, 5,
				ItemBuilder
						.from(Material.END_CRYSTAL).name(genesisExplained).lore(List.of(genesisExplainedLore1,
								genesisExplainedLore2, genesisExplainedLore3, genesisExplainedLore4))
						.asGuiItem(event -> {
							player.performCommand("genesis");
						}));

		// Classes help item
		genesisHelp.setItem(5, 4, ItemBuilder.from(Material.TOTEM_OF_UNDYING).name(genesisClasses)
				.lore(List.of(genesisClassesLore1, genesisClassesLore2, genesisClassesLore3)).asGuiItem());

		// Abilities help item
		genesisHelp.setItem(3, 3, ItemBuilder.from(Material.NETHERITE_SWORD).name(genesisAbilities).lore(
				List.of(genesisAbilitiesLore1, genesisAbilitiesLore2, genesisAbilitiesLore3, genesisAbilitiesLore4))
				.asGuiItem());

		// Weaknesses help item
		genesisHelp.setItem(3, 7, ItemBuilder.from(Material.WOODEN_SWORD).name(genesisWeaknesses)
				.lore(List.of(genesisWeaknessesLore1, genesisWeaknessesLore2, genesisWeaknessesLore3)).asGuiItem());

		// Spawn help item
		genesisHelp.setItem(5, 6, ItemBuilder.from(Material.BEACON).name(genesisSpawn)
				.lore(List.of(genesisSpawnLore1, genesisSpawnLore2, genesisSpawnLore3)).asGuiItem());

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
	private static Gui createGui(String title, int rows, Boolean addBackButton) {
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

		// Add white pane filler
		// Format: {{row, column}, {row, column}, ...}
		int[][] whitePaneCoords = {{1, 5}, {2, 4}, {2, 6}, {3, 3}, {3, 5}, {3, 7}, {4, 4}, {4, 6}, {5, 5}, {6, 5}};
		for (int[] coordsEntry : whitePaneCoords) {
			gui.setItem(coordsEntry[0], coordsEntry[1],
					ItemBuilder.from(Material.WHITE_STAINED_GLASS_PANE).name(emptyComponent).asGuiItem());
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
