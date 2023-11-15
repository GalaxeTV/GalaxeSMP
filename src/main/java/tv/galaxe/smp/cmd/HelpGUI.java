package tv.galaxe.smp.cmd;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import java.util.Arrays;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpGUI implements CommandExecutor {
	private static Gui helpGUI;
	private static Player player;

	public HelpGUI() {
		helpGUI = Gui.gui().title(Component.text("GalaxeSMP Help Menu")).rows(6).create();
		helpGUI.setDefaultClickAction(event -> {
			event.setCancelled(true);
		});
		helpGUI.getFiller().fill(Arrays.asList(new GuiItem(Material.PINK_STAINED_GLASS_PANE),
				new GuiItem(Material.PURPLE_STAINED_GLASS_PANE)));
		helpGUI.setItem(3, 3, new GuiItem(Material.WHITE_STAINED_GLASS_PANE));
		helpGUI.setItem(3, 7, new GuiItem(Material.WHITE_STAINED_GLASS_PANE));
		for (int i = 2; i <= 4; i++) {
			helpGUI.setItem(i, 4, new GuiItem(Material.WHITE_STAINED_GLASS_PANE));
			helpGUI.setItem(i, 6, new GuiItem(Material.WHITE_STAINED_GLASS_PANE));
		}
		for (int i = 1; i <= 6; i++) {
			helpGUI.setItem(i, 5, new GuiItem(Material.WHITE_STAINED_GLASS_PANE));
		}

		helpGUI.setOpenGuiAction(event -> {
			player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_STEP, 1, 1);
		});
		helpGUI.setCloseGuiAction(event -> {
			player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_BREAK, 1, 1);
		});

		// Add phantom membranes
		helpGUI.setItem(2, 5, ItemBuilder.from(Material.PHANTOM_MEMBRANE).asGuiItem(event -> {
			/* Event Handling Here */ }));
		helpGUI.setItem(4, 5, ItemBuilder.from(Material.PHANTOM_MEMBRANE).asGuiItem(event -> {
			/* Event Handling Here */ }));

		// Add sunflowers
		helpGUI.setItem(2, 2, ItemBuilder.from(Material.SUNFLOWER).asGuiItem(event -> {
			/* Event Handling Here */ }));
		helpGUI.setItem(5, 2, ItemBuilder.from(Material.SUNFLOWER).asGuiItem(event -> {
			/* Event Handling Here */ }));
		helpGUI.setItem(4, 3, ItemBuilder.from(Material.SUNFLOWER).asGuiItem(event -> {
			/* Event Handling Here */ }));
		helpGUI.setItem(3, 4, ItemBuilder.from(Material.SUNFLOWER).asGuiItem(event -> {
			/* Event Handling Here */ }));
		helpGUI.setItem(3, 6, ItemBuilder.from(Material.SUNFLOWER).asGuiItem(event -> {
			/* Event Handling Here */ }));
		helpGUI.setItem(4, 7, ItemBuilder.from(Material.SUNFLOWER).asGuiItem(event -> {
			/* Event Handling Here */ }));
		helpGUI.setItem(2, 8, ItemBuilder.from(Material.SUNFLOWER).asGuiItem(event -> {
			/* Event Handling Here */ }));
		helpGUI.setItem(5, 8, ItemBuilder.from(Material.SUNFLOWER).asGuiItem(event -> {
			/* Event Handling Here */ }));
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) { // Only allow players to run command
			sender.sendMessage("This command must be used as a player!");
			return false;
		}
		player = (Player) sender;
		helpGUI.open(player);
		return true;
	}
}
