package tv.galaxe.smp.cmd;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import net.kyori.adventure.text.Component;

public class Pronouns implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("This command must be used as a player!");
			return false;
		} else {
			Player player = (Player) sender;
			if (args.length == 0) {
				player.sendMessage("You must specify your pronouns!");
				return false;
			} else {
				if (args[0] == null || args[0].isEmpty()) {
					PronounsGUI(player);
				}
				else {
					
				}
			}
			return true;
		}
	}

	public void PronounsGUI(Player player) {
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

		pronouns.setItem(3, 4, ItemBuilder.from(Material.BARRIER).name(Component.text("Remove Pronouns")).asGuiItem(event -> {
			/* Event Handling Here */
		}));
	}
}
