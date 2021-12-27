package me.krymz0n.itemremover;

import me.krymz0n.itemremover.listener.*;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.logging.Logger;

public final class Main extends JavaPlugin implements Listener {
    public static Main instance;
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
    static LocalDateTime now = LocalDateTime.now();
    String dir = getDataFolder() + "\\logs";
    public Logger log = getLogger();
    public int j;

    public String thing = getDataFolder() + "//logs//" + "itemRemove-" + dtf.format(now) + ".log";

    @Override
    public void onEnable() {
        if (instance == null) instance = this; // Instance
        saveDefaultConfig();
        PluginManager pm = getServer().getPluginManager();

        // Event Registration
        pm.registerEvents(this, this);
        pm.registerEvents(new InventoryPickup(this), this);
        pm.registerEvents(new InventoryClick(this), this);
        pm.registerEvents(new InventoryMove(this), this);
        pm.registerEvents(new DropItem(this), this);
        pm.registerEvents(new InventoryInteract(this), this);
        pm.registerEvents(new Join(this), this);
        pm.registerEvents(new Leave(this), this);
        pm.registerEvents(new InventoryOpen(this), this);
        pm.registerEvents(new BlockPlace(this), this);

        File directory = new File(this.dir);
        File file = new File(thing);

        // Checking if file exists, if not it makes one.
        try {
            if (directory.mkdirs()) {
                log.info("New Directory Created");
            } else {
                log.info("Directory already exists");

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            if (file.createNewFile()) {
                log.info("New file created!");
            } else {
                log.info("File already exists!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void remove(ItemStack i) { //removes items.
        if (i != null && this.getConfig().getStringList("BannedItems").contains(i.getType().toString())) {
            i.subtract(i.getAmount());
            j = 1;
        }
    }

/*    public boolean didRemove(PlayerInventory inventory) {
//        System.out.println(Objects.requireNonNull(inventory.getItem(inventory.getSize())).toString());
//        if (getConfig().getStringList("BannedItems").contains(Objects.requireNonNull(inventory.getItem(inventory.getSize())).toString())) {
//            System.out.println("Inventory was not the same");
//            return true;
//        } else {
//            System.out.println("Inventory was the same");
//            return false;
//        }
*/
}
