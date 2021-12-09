package me.krymz0n.itemremover.listener;

import me.krymz0n.itemremover.Main;
import me.krymz0n.itemremover.util.Logging;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;

public class InventoryMove implements Listener {
    private final Main plugin;

    public InventoryMove(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryMove(InventoryMoveItemEvent evt) {
        if (plugin.getConfig().getBoolean("InventoryMove")) {
            Player p = (Player) evt.getInitiator();
            if (!p.isOp()) {
                plugin.remove(evt.getItem());
                evt.getSource().forEach(plugin::remove);
                evt.getDestination().forEach(plugin::remove);

                if (plugin.getConfig().getBoolean("Debug")) {
                    System.out.println(ChatColor.RED + " removed an illegal item from: " + p.getName() + "'s inventory!");
                    Logging.log("removed an illegal item from: " + p.getName() + "'s inventory!");
                }
            }
        }
    }
}
