package me.krymz0n.itemremover;

import me.krymz0n.itemremover.listener.*;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Main extends JavaPlugin implements Listener {
    public static Main instance;
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
    static LocalDateTime now = LocalDateTime.now();
    String dir = getDataFolder() + "\\logs";

    public String thing = getDataFolder() + "//logs//" + "itemRemove-" + dtf.format(now) + ".log";

    @Override
    public void onEnable() {
        if (instance == null) instance = this;
        saveDefaultConfig();
        PluginManager pm = getServer().getPluginManager();

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

        try {
            if (directory.mkdirs()) {
                System.out.println("New Directory Created");
            } else {
                System.out.println("Directory already exists");

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            if (file.createNewFile()) {
                System.out.println("New file created!");
            } else {
                System.out.println("File already exists!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void remove(ItemStack i) {
        if (i != null && this.getConfig().getStringList("BannedItems").contains(i.getType().name())) {
            i.subtract(i.getAmount());
        }
    }
}
