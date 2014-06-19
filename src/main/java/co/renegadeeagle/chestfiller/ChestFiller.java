package co.renegadeeagle.chestfiller;

import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Ryan on 6/19/2014.
 */
public class ChestFiller extends JavaPlugin {

    public void onEnable(){
        this.saveDefaultConfig();
        this.getConfig().options().copyDefaults(true);
    }
    public void onDisable(){

    }

    public void registerEvents(){

    }
}
