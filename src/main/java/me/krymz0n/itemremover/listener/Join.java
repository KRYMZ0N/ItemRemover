package me.krymz0n.itemremover.listener;

import me.krymz0n.itemremover.Main;
import me.krymz0n.itemremover.util.Logging;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.PlayerInventory;

public class Join implements Listener {
    private final Main plugin;

    public Join(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent evt) {
        final PlayerInventory inv = evt.getPlayer().getInventory();
        if (plugin.getConfig().getBoolean("Join") && !evt.getPlayer().isOp()) {
            Player p = evt.getPlayer();

            evt.getPlayer().getInventory().forEach(plugin::remove);

            if (plugin.getConfig().getBoolean("Debug") && plugin.j == 1) {
                plugin.log.info(ChatColor.RED + " removed an illegal item from: " + p.getName() + "'s inventory! by means of join");
                Logging.log("removed an illegal item from: " + p.getName() + "'s inventory!");
            }
            plugin.j = 3;
        }
    }
}
