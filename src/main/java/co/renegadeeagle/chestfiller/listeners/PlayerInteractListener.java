package co.renegadeeagle.chestfiller.listeners;

import co.renegadeeagle.chestfiller.ChestFiller;
import co.renegadeeagle.chestfiller.util.InventoryStringDeSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import java.util.Random;

/**
 * Created by Ryan on 6/19/2014.
 */
public class PlayerInteractListener implements Listener {

    private static ChestFiller instance;
    public PlayerInteractListener(ChestFiller instance){
            this.instance = instance;
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(event.getClickedBlock().getType() == Material.CHEST){
                if(event.getClickedBlock().getState() instanceof Chest){
                    if(!instance.getHasBeenLooted().contains(event.getClickedBlock().getLocation())) {
                        Chest chest = (Chest) event.getClickedBlock().getState();
                        chest.getInventory().setContents(InventoryStringDeSerializer.StringToInventory(decideOnInventory()).getContents());
                        instance.getHasBeenLooted().add(chest.getLocation());
                    } else if(instance.getHasBeenLooted().contains(event.getClickedBlock()) && instance.getConfig().getBoolean("loot_once") == true) {
                        return;
                    } else if(instance.getHasBeenLooted().contains(event.getClickedBlock()) && instance.getConfig().getBoolean("loot_once") == false){
                        Chest chest = (Chest) event.getClickedBlock().getState();
                        chest.getInventory().setContents(InventoryStringDeSerializer.StringToInventory(decideOnInventory()).getContents());
                    }
                }
            }
        }
    }
    public String decideOnInventory(){
        Random random = new Random();
        int selection = random.nextInt(instance.getConfig().getConfigurationSection("inventories").getKeys(false).size());
        selection++;
        System.out.println(selection);
        return instance.getConfig().getString("inventories."+selection);
    }
}
