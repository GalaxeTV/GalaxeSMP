package tv.galaxe.galaxesmp.crafting;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import tv.galaxe.galaxesmp.GalaxeSMP;

public class InvisibleItemFrames {
    public static void createInvisibleItemFrames() {
        ItemStack invisableFrame = new ItemStack(Material.ITEM_FRAME, 1);
        ItemMeta invisibleFrameMeta = invisableFrame.getItemMeta();
        invisibleFrameMeta.displayName(Component.text("Invisible Item Frame"));

        NamespacedKey key = new NamespacedKey(GalaxeSMP.getInstance(), "invisible_item_frame");

        ShapelessRecipe frame = new ShapelessRecipe(key, invisableFrame);
        frame.addIngredient(1, Material.ITEM_FRAME);
        frame.addIngredient(1, Material.POTION);
        GalaxeSMP.getInstance().getServer().addRecipe(frame);
    }
}
