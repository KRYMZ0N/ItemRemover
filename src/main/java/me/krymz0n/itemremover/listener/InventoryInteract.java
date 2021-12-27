package me.krymz0n.itemremover.listener;

import me.krymz0n.itemremover.Main;
import me.krymz0n.itemremover.util.Logging;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryInteractEvent;

public class InventoryInteract implements Listener {
    private final Main plugin;

    public InventoryInteract(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryInteract(InventoryInteractEvent evt) {
        if (plugin.getConfig().getBoolean("InventoryInteract") && !evt.getWhoClicked().isOp()) {
            Player p = (Player) evt.getWhoClicked();
            evt.getInventory().forEach(plugin::remove);

            if (plugin.getConfig().getBoolean("Debug") && plugin.j == 1) {
                plugin.log.info(ChatColor.RED + " removed an illegal item from: " + p.getName() + "'s inventory! by means of leave");
                Logging.log("removed an illegal item from: " + p.getName() + "'s inventory!");
            }

            plugin.j =3;
        }
    }
}
