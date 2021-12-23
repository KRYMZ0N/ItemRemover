package me.krymz0n.itemremover.listener;

import me.krymz0n.itemremover.Main;
import me.krymz0n.itemremover.util.Logging;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClick implements Listener {
    private final Main plugin;

    public InventoryClick(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent evt) {
        if (plugin.getConfig().getBoolean("InventoryClick") && !evt.getWhoClicked().isOp()) {
            Player p = (Player) evt.getWhoClicked();

            if (evt.getCursor() != null) {
                evt.getWhoClicked().getInventory().forEach(plugin::remove);

                if (plugin.getConfig().getBoolean("Debug")) {
                    plugin.log.info(ChatColor.RED + " removed an illegal item from: " + p.getName() + "'s inventory!");
                    Logging.log("removed an illegal item from: " + p.getName() + "'s inventory!");
                }
            }
        }
    }
}
