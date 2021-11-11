package utilities.catscraft;

import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class EventUtility {

    public static void handleClickEventCancel (Plugin plugin, InventoryClickEvent event) {
        event.setResult(Event.Result.DENY);
        event.setCancelled(true);

        new BukkitRunnable() {
            @Override
            public void run() {
                event.setCursor(new ItemStack(Material.AIR));
            }
        }.runTaskLater(plugin, 1);
    }

}
