package tv.galaxe.smp.cmd;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Pronouns implements CommandExecutor {

	// Define valid pronouns
	enum pronouns {
		HE, HIM, SHE, HER, THEY, THEM, XE, XEM
	};

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("This command must be used as a player!");
			return false;
		} else {
			Player player = (Player) sender;
			if (args[0].isEmpty()) {
				PronounsGUI(player);
			} else {
				String cmd = args[0].toLowerCase();
				switch (cmd) {
					case "remove" :
						removePronouns(player);
						break;
					case "set" :
						String pronoun = args[1].toLowerCase();
						setPronouns(player, pronoun);
						break;
					case "gui" :
						PronounsGUI(player);
						break;
					case "help" :
						player.sendMessage("Usage: /pronouns [set|remove|gui|help]");
						break;
					default :
						pronounsGUI(player);
				}
			}
			return true;
		}
	}

	public void pronounsGUI(Player player) {
		Gui pronouns = Gui.gui().title(Component.text("Pronouns")).rows(3).create();

		// Disable taking items
		pronouns.setDefaultClickAction(event -> {
			event.setCancelled(true);
		});

		pronouns.getFiller().fill(ItemBuilder.from(Material.GRAY_STAINED_GLASS_PANE).asGuiItem());

		pronouns.setItem(2, 2, ItemBuilder.from(Material.PAPER).name(Component.text("He/Him")).asGuiItem(event -> {
			/* Event Handling Here */
		}));

		pronouns.setItem(2, 3, ItemBuilder.from(Material.PAPER).name(Component.text("She/Her")).asGuiItem(event -> {
			/* Event Handling Here */
		}));

		pronouns.setItem(2, 3, ItemBuilder.from(Material.PAPER).name(Component.text("They/Them")).asGuiItem(event -> {
			/* Event Handling Here */
		}));

		pronouns.setItem(2, 4, ItemBuilder.from(Material.PAPER).name(Component.text("Xe/Xem")).asGuiItem(event -> {
			/* Event Handling Here */
		}));

		pronouns.setItem(2, 5, ItemBuilder.from(Material.PAPER).name(Component.text("He/They")).asGuiItem(event -> {
			/* Event Handling Here */
		}));

		pronouns.setItem(2, 6, ItemBuilder.from(Material.PAPER).name(Component.text("She/They")).asGuiItem(event -> {
			/* Event Handling Here */
		}));

		pronouns.setItem(3, 4,
				ItemBuilder.from(Material.BARRIER).name(Component.text("Remove Pronouns")).asGuiItem(event -> {
					/* Event Handling Here */
				}));

		pronouns.open(player);
	}

	public Boolean setPronouns(Player player, String pronoun) {
		switch (pronoun) {
			case "he/him" :
				// Set pronouns to HE_HIM
				break;
			case "she/her" :
				// Set pronouns to SHE_HER
				break;
			case "they/them" :
				// Set pronouns to THEY_THEM
				break;
			case "xe/xem" :
				// Set pronouns to XE_XEM
				break;
			case "he/they" :
				// Set pronouns to HE_THEY
				break;
			case "she/they" :
				// Set pronouns to SHE_THEY
				break;
			default :
				player.sendMessage("Invalid pronouns!");
				return false;
		}
		return true;
	}

	public Boolean removePronouns(Player player) {
		// Remove player's pronouns
		return true;
	}
}
