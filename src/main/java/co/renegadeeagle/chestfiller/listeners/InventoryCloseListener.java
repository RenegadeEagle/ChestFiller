package co.renegadeeagle.chestfiller.listeners;

import co.renegadeeagle.chestfiller.ChestFiller;
import co.renegadeeagle.chestfiller.util.InventoryStringDeSerializer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

/**
 * Created by Ryan on 6/19/2014.
 */
public class InventoryCloseListener implements Listener {
    private static ChestFiller instance;
    public InventoryCloseListener(ChestFiller instance){
        this.instance = instance;
    }
    @EventHandler
    public void onInventoryCloseEvent(InventoryCloseEvent event){
        if(event.getPlayer() instanceof Player){
            Player player = ((Player) event.getPlayer());
            if(instance.getCreatingAChest().containsKey(event.getPlayer().getName())) {
                int id = instance.getCreatingAChest().get(player.getName());
                instance.getConfig().set("inventories."+id, InventoryStringDeSerializer.InventoryToString(event.getInventory()));
                instance.saveConfig();
                instance.getCreatingAChest().remove(event.getPlayer().getName());
                player.sendMessage(ChatColor.GRAY+"Chest saved.");
            }
        }
    }
}
