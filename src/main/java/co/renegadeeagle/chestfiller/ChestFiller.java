package co.renegadeeagle.chestfiller;

import co.renegadeeagle.chestfiller.cmd.ChestFillerCmd;
import co.renegadeeagle.chestfiller.listeners.BlockBreakListener;
import co.renegadeeagle.chestfiller.listeners.InventoryCloseListener;
import co.renegadeeagle.chestfiller.listeners.PlayerInteractListener;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

/**
 * Created by Ryan on 6/19/2014.
 */
public class ChestFiller extends JavaPlugin {

    private static Map<String, Integer> creatingAChest = new HashMap<String, Integer>();
    private static List<Location> hasBeenLooted = new ArrayList<Location>();
    private List<String> worlds = new ArrayList<String>();
    public void onEnable(){
        this.saveDefaultConfig();
        this.getConfig().options().copyDefaults(true);
        this.getServer().getPluginCommand("chestfiller").setExecutor(new ChestFillerCmd(this));
        this.registerEvents();
        for(String worldname: this.getConfig().getStringList("worlds")){
            worlds.add(worldname);
            this.getLogger().log(Level.INFO, worldname+" has enabled all chests to work as loot chests, regardless of their position in the world.");
        }
    }
    public void onDisable(){

    }
    public void registerEvents(){
        this.getServer().getPluginManager().registerEvents(new InventoryCloseListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerInteractListener(this), this);
        this.getServer().getPluginManager().registerEvents(new BlockBreakListener(this), this);

    }
    public static Map<String, Integer> getCreatingAChest() {
        return creatingAChest;
    }

    public List<String> getWorlds() {
        return worlds;
    }

    public static List<Location> getHasBeenLooted() {
        return hasBeenLooted;
    }
}
