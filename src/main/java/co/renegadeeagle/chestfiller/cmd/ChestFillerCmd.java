package co.renegadeeagle.chestfiller.cmd;

import co.renegadeeagle.chestfiller.ChestFiller;
import co.renegadeeagle.chestfiller.util.InventoryStringDeSerializer;
import co.renegadeeagle.chestfiller.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Created by Ryan on 6/19/2014.
 */
public class ChestFillerCmd implements CommandExecutor {
    private static ChestFiller instance;
    public ChestFillerCmd(ChestFiller instance){
        this.instance = instance;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(cmd.getName().equalsIgnoreCase("chestfiller")){
                if(player.hasPermission("chestfiller.*")){
                    if(args.length == 0){
                        player.sendMessage(StringUtil.getPrefix()+ChatColor.RED+"Not enough arguments! Do /chest add or /chest edit!");
                    }
                    if(args.length == 1){
                        if(args[0].equalsIgnoreCase("add")){
                            int invs = instance.getConfig().getInt("amount_of_inventories")+1;
                            instance.getCreatingAChest().put(player.getName(), invs);
                            Inventory inventory = Bukkit.createInventory(null, 27, "Inventory "+invs);
                            instance.getConfig().set("amount_of_inventories", instance.getConfig().getInt("amount_of_inventories")+1);
                            instance.saveConfig();
                            player.openInventory(inventory);

                        }
                        if(args[0].equalsIgnoreCase("amount")){
                            player.sendMessage(ChatColor.GRAY+"There are currently "+ChatColor.GREEN+instance.getConfig().getInt("amount_of_inventories")+" inventories!");
                        }
                    }
                    if(args.length == 2){
                        if(args[0].equalsIgnoreCase("edit")){
                            try{
                                int id = Integer.parseInt(args[1]);
                                if(instance.getConfig().contains("inventories."+id)){
                                    Inventory inventory = Bukkit.createInventory(null, 27, ChatColor.RED+"Editing - Inventory "+id);
                                    inventory.setContents(InventoryStringDeSerializer.StringToInventory(instance.getConfig().getString("inventories."+id)).getContents());
                                    player.openInventory(inventory);
                                    instance.getCreatingAChest().put(player.getName(), id);
                                }
                            }catch(Exception e){
                                player.sendMessage(ChatColor.RED+"That is not a valid inventory to change! Usage: /chestfiller edit number");
                            }
                        }
                    }
                }
            }
        }else{
            sender.sendMessage("You must run commands as an in-game player!");
        }
        return false;
    }
}
