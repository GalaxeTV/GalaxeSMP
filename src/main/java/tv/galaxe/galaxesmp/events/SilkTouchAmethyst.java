/* (C)2023 GalaxeTV */
package tv.galaxe.galaxesmp.events;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import tv.galaxe.galaxesmp.GalaxeSMP;

public class SilkTouchAmethyst implements Listener {

  private final GalaxeSMP plugin;

  /**
   * Plugin instance from the server
   *
   * @param plugin Sets current instance of the plugin
   */
  public SilkTouchAmethyst(GalaxeSMP plugin) {
    this.plugin = plugin;
  }

  /**
   * This event is called when a player breaks a block.
   *
   * @param event The event that was called.
   */
  @EventHandler
  public void onBlockBreak(BlockBreakEvent event) {
    if (event.getBlock().getType() == Material.BUDDING_AMETHYST) {
      if (event
          .getPlayer()
          .getInventory()
          .getItemInMainHand()
          .containsEnchantment(Enchantment.SILK_TOUCH)) {
        event.setDropItems(false);
        event
            .getBlock()
            .getWorld()
            .dropItemNaturally(
                event.getBlock().getLocation(), new ItemStack(Material.BUDDING_AMETHYST, 1));
      }
    }
  }
}
