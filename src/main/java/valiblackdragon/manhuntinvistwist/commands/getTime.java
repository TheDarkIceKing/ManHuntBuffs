package valiblackdragon.manhuntinvistwist.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import valiblackdragon.manhuntinvistwist.main;

public class getTime implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        main plugin = main.getPlugin(main.class);
        if(!(sender instanceof Player)) {
            Bukkit.getServer().getConsoleSender().sendMessage("Only players can execute this command");
            return false;
        }
        ItemStack sacrifice = ((Player) sender).getInventory().getItemInMainHand();
        if(plugin.getConfig().getInt("sacs." + sacrifice.getType()) == 0){
            sender.sendMessage("Not possible to sac");
            return false;
        }
        try {plugin.leftovertime.replace((Player) sender, plugin.leftovertime.get((Player) sender) + (plugin.getConfig().getInt("sacs." + sacrifice.getType()))*sacrifice.getAmount());}
        catch (NullPointerException err){}
        ((Player) sender).getInventory().setItemInMainHand(new ItemStack(Material.AIR));
        sender.sendMessage("You have " + plugin.leftovertime.get((Player) sender) + " points");
        return false;
    }
}
