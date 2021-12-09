package me.krymz0n.itemremover.listener;

import me.krymz0n.itemremover.Main;
import me.krymz0n.itemremover.util.Logging;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class InventoryPickup implements Listener {
    private final Main plugin;

    public InventoryPickup(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryPickup(PlayerPickupItemEvent evt) {
        if (plugin.getConfig().getBoolean("InventoryPickup") && !evt.getPlayer().isOp()) {
            Player p = evt.getPlayer();
            evt.getPlayer().getInventory().forEach(plugin::remove);
            if (plugin.getConfig().getBoolean("Debug")) {
                System.out.println(ChatColor.RED + " removed an illegal item from: " + p.getName() + "'s inventory!");
                Logging.log("removed an illegal item from: " + p.getName() + "'s inventory!");
            }
        }
    }
}
