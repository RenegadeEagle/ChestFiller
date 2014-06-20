package co.renegadeeagle.chestfiller.listeners;

import co.renegadeeagle.chestfiller.ChestFiller;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

/**
 * Created by Ryan on 6/19/2014.
 */
public class BlockBreakListener implements Listener {
    private static ChestFiller instance;
    public BlockBreakListener(ChestFiller instance){
        this.instance = instance;
    }
    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event){
        if(event.getBlock().getType() == Material.CHEST){
            if(instance.getConfig().getBoolean("allow_break")){
                if(instance.getWorlds().contains(event.getPlayer().getWorld().getName())) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
