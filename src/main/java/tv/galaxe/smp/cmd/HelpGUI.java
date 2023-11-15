package tv.galaxe.smp.cmd;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import tv.galaxe.smp.Core;

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
		}
		Player player = (Player) sender;

		Core.helpGUI.setOpenGuiAction(event -> {
			player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_STEP, 1, 1);
		});
		Core.helpGUI.setCloseGuiAction(event -> {
			player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_BREAK, 1, 1);
		});

		// Add phantom membranes
		Core.helpGUI.setItem(2, 5, ItemBuilder.from(Material.PHANTOM_MEMBRANE).asGuiItem(event -> {
			/* Event Handling Here */ }));
		Core.helpGUI.setItem(4, 5, ItemBuilder.from(Material.PHANTOM_MEMBRANE).asGuiItem(event -> {
			/* Event Handling Here */ }));

		// Add sunflowers
		Core.helpGUI.setItem(2, 2, ItemBuilder.from(Material.SUNFLOWER).asGuiItem(event -> {
			/* Event Handling Here */ }));
		Core.helpGUI.setItem(5, 2, ItemBuilder.from(Material.SUNFLOWER).asGuiItem(event -> {
			/* Event Handling Here */ }));
		Core.helpGUI.setItem(4, 3, ItemBuilder.from(Material.SUNFLOWER).asGuiItem(event -> {
			/* Event Handling Here */ }));
		Core.helpGUI.setItem(3, 4, ItemBuilder.from(Material.SUNFLOWER).asGuiItem(event -> {
			/* Event Handling Here */ }));
		Core.helpGUI.setItem(3, 6, ItemBuilder.from(Material.SUNFLOWER).asGuiItem(event -> {
			/* Event Handling Here */ }));
		Core.helpGUI.setItem(4, 7, ItemBuilder.from(Material.SUNFLOWER).asGuiItem(event -> {
			/* Event Handling Here */ }));
		Core.helpGUI.setItem(2, 8, ItemBuilder.from(Material.SUNFLOWER).asGuiItem(event -> {
			/* Event Handling Here */ }));
		Core.helpGUI.setItem(5, 8, ItemBuilder.from(Material.SUNFLOWER).asGuiItem(event -> {
			/* Event Handling Here */ }));

		Core.helpGUI.open(player);
		return true;
	}
}
