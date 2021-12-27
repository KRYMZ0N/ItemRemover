package me.krymz0n.itemremover.listener;

import me.krymz0n.itemremover.Main;
import me.krymz0n.itemremover.util.Logging;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropItem implements Listener {
    private final Main plugin;

    public DropItem(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent evt) {
        if (plugin.getConfig().getBoolean("DropItem") && !evt.getPlayer().isOp()) {
            Player p = evt.getPlayer();
            plugin.remove(evt.getItemDrop().getItemStack());
            evt.getPlayer().getInventory().forEach(plugin::remove);

            if (plugin.getConfig().getBoolean("Debug") && plugin.j == 1) {
                plugin.log.info(ChatColor.RED + " removed an illegal item from: " + p.getName() + "'s inventory! by means of leave");
                Logging.log("removed an illegal item from: " + p.getName() + "'s inventory!");
            }

            plugin.j =3;
        }
    }
}
