package me.krymz0n.itemremover.listener;

import me.krymz0n.itemremover.Main;
import me.krymz0n.itemremover.util.Logging;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.Objects;

public class BlockPlace implements Listener {
    private final Main plugin;

    public BlockPlace(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent evt) {
        if (plugin.getConfig().getBoolean("BlockPlace") && !evt.getPlayer().isOp()) {
            Player p = evt.getPlayer();
            String block = evt.getBlockPlaced().getType().toString();

            if (plugin.getConfig().getStringList("BannedItems").contains(block)) {
                plugin.getConfig().getStringList("BannedItems").forEach(b -> {

                    if (evt.getPlayer().getInventory().contains(Objects.requireNonNull(Material.getMaterial(b)))) {
                        if (Objects.equals(Material.getMaterial(b), Material.END_PORTAL) && evt.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.ENDER_EYE)) {
                            p.getInventory().remove(Objects.requireNonNull(Material.getMaterial(b)));
                            plugin.log.info(ChatColor.RED + " removed an illegal item from: " + p.getName() + "'s inventory!");
                            Logging.log("removed an illegal item from: " + p.getName() + "'s inventory!");

                        } else {
                            if (plugin.getConfig().getBoolean("Debug")) {
                                plugin.log.info(ChatColor.RED + " removed an illegal block from being placed by: " + p.getName() + " at " + p.getLocation().getBlockX() + ", " + p.getLocation().getBlockY() + ", " + p.getLocation().getBlockZ());
                                Logging.log("removed an illegal block from being placed by: " + p.getName() + " at " + p.getLocation().getBlockX() + ", " + p.getLocation().getBlockY() + ", " + p.getLocation().getBlockZ());
                                p.getInventory().remove(Objects.requireNonNull(Material.getMaterial(b)));
                                evt.setCancelled(true);

                            } else {
                                plugin.log.info(ChatColor.RED + " removed an illegal item from: " + p.getName() + "'s inventory!");
                                Logging.log("removed an illegal item from: " + p.getName() + "'s inventory!");
                                p.getInventory().remove(Objects.requireNonNull(Material.getMaterial(b)));
                                evt.setCancelled(true);
                            }
                        }
                    }
                });
            }
        }
    }
}
