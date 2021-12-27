package me.krymz0n.itemremover.listener;

import me.krymz0n.itemremover.Main;
import me.krymz0n.itemremover.util.Logging;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryPickupItemEvent;

public class InventoryPickup implements Listener {
    private final Main plugin;

    public InventoryPickup(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryPickup(InventoryPickupItemEvent evt) {
        Player p = (Player) evt.getInventory().getViewers();
        if (plugin.getConfig().getBoolean("InventoryPickup") && !p.isOp()) {
            p.sendMessage("You have picked up something");
            evt.getInventory().forEach(plugin::remove);

            if (plugin.getConfig().getBoolean("Debug") && plugin.j == 1) {
                plugin.log.info(ChatColor.RED + " removed an illegal item from: " + p.getName() + "'s inventory! by means of leave");
                Logging.log("removed an illegal item from: " + p.getName() + "'s inventory!");
            }

            plugin.j =3;
        }
    }
}
