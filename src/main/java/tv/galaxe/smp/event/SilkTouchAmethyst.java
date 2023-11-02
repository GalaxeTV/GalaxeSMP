package tv.galaxe.smp.event;

import java.util.EnumSet;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public final class SilkTouchAmethyst implements Listener {

    // Define valid tools
    Set<Material> validTools = EnumSet.of(Material.WOODEN_PICKAXE, Material.STONE_PICKAXE, Material.IRON_PICKAXE, Material.GOLDEN_PICKAXE, Material.DIAMOND_PICKAXE, Material.NETHERITE_PICKAXE);

    @EventHandler
    public void onBlockbreak(BlockBreakEvent event) { 
        if ((event.getBlock().getType() == Material.BUDDING_AMETHYST) // Must be mining budding amethyst
        && validTools.contains(event.getPlayer().getInventory().getItemInMainHand().getType()) // Must be holding a valid tool
        && event.getPlayer().getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) { // Must have silktouch
            event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.BUDDING_AMETHYST, 1));
        }
    }
}
