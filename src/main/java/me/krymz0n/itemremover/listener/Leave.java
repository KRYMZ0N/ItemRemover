package me.krymz0n.itemremover.listener;

import me.krymz0n.itemremover.Main;
import me.krymz0n.itemremover.util.Logging;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Leave implements Listener {
    private final Main plugin;

    public Leave(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent evt) {
        if (plugin.getConfig().getBoolean("Leave") && !evt.getPlayer().isOp()) {
            Player p = evt.getPlayer();
            evt.getPlayer().getInventory().forEach(plugin::remove);
            if (plugin.getConfig().getBoolean("Debug")) {
                System.out.println(ChatColor.RED + " removed an illegal item from: " + p.getName() + "'s inventory!");
                Logging.log("removed an illegal item from: " + p.getName() + "'s inventory!");
            }
        }
    }
}
