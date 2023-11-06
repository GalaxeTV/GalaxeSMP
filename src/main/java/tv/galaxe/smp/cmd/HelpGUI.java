package tv.galaxe.smp.cmd;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import java.util.Arrays;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpGUI implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) { // Only allow players to run command
			sender.sendMessage("This command must be used as a player!");
			return false;
		} else {
			Player player = (Player) sender;

			// Create GUI w/open & close sounds
			Gui help = Gui.gui().title(Component.text("GalaxeSMP Help Menu")).rows(6).create();
			help.setOpenGuiAction(event -> {
				player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_STEP, 1, 1);
			});
			help.setCloseGuiAction(event -> {
				player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_BREAK, 1, 1);
			});

			// Disable taking items
			help.setDefaultClickAction(event -> {
				event.setCancelled(true);
			});

			// Add filler
			help.getFiller().fill(Arrays.asList(ItemBuilder.from(Material.PINK_STAINED_GLASS_PANE).asGuiItem(),
					ItemBuilder.from(Material.PURPLE_STAINED_GLASS_PANE).asGuiItem()));

			// Add white pane filler
			help.setItem(3, 3, ItemBuilder.from(Material.WHITE_STAINED_GLASS_PANE).asGuiItem());
			help.setItem(3, 7, ItemBuilder.from(Material.WHITE_STAINED_GLASS_PANE).asGuiItem());
			for (int i = 2; i <= 4; i++) {
				help.setItem(i, 4, ItemBuilder.from(Material.WHITE_STAINED_GLASS_PANE).asGuiItem());
				help.setItem(i, 6, ItemBuilder.from(Material.WHITE_STAINED_GLASS_PANE).asGuiItem());
			}
			for (int i = 1; i <= 6; i++) {
				help.setItem(i, 5, ItemBuilder.from(Material.WHITE_STAINED_GLASS_PANE).asGuiItem());
			}

			// Add phantom membranes
			help.setItem(2, 5, ItemBuilder.from(Material.PHANTOM_MEMBRANE).asGuiItem(event -> {
				/* Event Handling Here */ }));
			help.setItem(4, 5, ItemBuilder.from(Material.PHANTOM_MEMBRANE).asGuiItem(event -> {
				/* Event Handling Here */ }));

			// Add sunflowers
			help.setItem(2, 2, ItemBuilder.from(Material.SUNFLOWER).asGuiItem(event -> {
				/* Event Handling Here */ }));
			help.setItem(5, 2, ItemBuilder.from(Material.SUNFLOWER).asGuiItem(event -> {
				/* Event Handling Here */ }));
			help.setItem(4, 3, ItemBuilder.from(Material.SUNFLOWER).asGuiItem(event -> {
				/* Event Handling Here */ }));
			help.setItem(3, 4, ItemBuilder.from(Material.SUNFLOWER).asGuiItem(event -> {
				/* Event Handling Here */ }));
			help.setItem(3, 6, ItemBuilder.from(Material.SUNFLOWER).asGuiItem(event -> {
				/* Event Handling Here */ }));
			help.setItem(4, 7, ItemBuilder.from(Material.SUNFLOWER).asGuiItem(event -> {
				/* Event Handling Here */ }));
			help.setItem(2, 8, ItemBuilder.from(Material.SUNFLOWER).asGuiItem(event -> {
				/* Event Handling Here */ }));
			help.setItem(5, 8, ItemBuilder.from(Material.SUNFLOWER).asGuiItem(event -> {
				/* Event Handling Here */ }));

			help.open(player);
			return true;
		}
	}
}
