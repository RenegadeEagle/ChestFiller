package co.renegadeeagle.chestfiller;

import co.renegadeeagle.chestfiller.cmd.ChestFillerCmd;
import co.renegadeeagle.chestfiller.listeners.InventoryCloseListener;
import co.renegadeeagle.chestfiller.listeners.PlayerInteractListener;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ryan on 6/19/2014.
 */
public class ChestFiller extends JavaPlugin {

    private static Map<String, Integer> creatingAChest = new HashMap<String, Integer>();
    private int amountOfInventories = this.getConfig().getInt("amount_of_inventories");
    public void onEnable(){
        this.saveDefaultConfig();
        this.getConfig().options().copyDefaults(true);
        this.getServer().getPluginCommand("chestfiller").setExecutor(new ChestFillerCmd(this));
        this.registerEvents();
    }
    public void onDisable(){

    }
    public void registerEvents(){
        this.getServer().getPluginManager().registerEvents(new InventoryCloseListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerInteractListener(this), this);

    }
    public int getAmountOfInventories() {
        return amountOfInventories;
    }
    public void setAmountOfInventories(int amountOfInventories) {
        this.amountOfInventories = amountOfInventories;
    }

    public static Map<String, Integer> getCreatingAChest() {
        return creatingAChest;
    }
}
