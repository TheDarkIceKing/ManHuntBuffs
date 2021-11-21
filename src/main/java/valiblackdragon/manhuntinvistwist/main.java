package valiblackdragon.manhuntinvistwist;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import valiblackdragon.manhuntinvistwist.commands.getTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class main extends JavaPlugin implements Listener {
    public HashMap<Player, Integer> leftovertime = new HashMap<Player, Integer>();
    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        getCommand("sacrifice").setExecutor((CommandExecutor)new getTime());
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        if(!leftovertime.containsKey(e.getPlayer())) {
            leftovertime.put(e.getPlayer(), 150);
        }
    }
    @EventHandler
    public void openMenu(PlayerInteractEvent e){
        if(e.getPlayer().getInventory().getItemInMainHand().isSimilar(new ItemStack(Material.PAINTING))){
            Inventory buyscreen = Bukkit.createInventory((InventoryHolder)e.getPlayer(), 9, ChatColor.AQUA + "Buy buffs (" + leftovertime.get(e.getPlayer()) + ")");
            ItemStack GodMode = new ItemStack(Material.TOTEM_OF_UNDYING);
            List<String> lore = new ArrayList<>();
            lore.add("cost");
            lore.add("200");
            ItemMeta GodMeta = GodMode.getItemMeta();
            GodMeta.setLore(lore);
            GodMeta.setDisplayName(ChatColor.GOLD + "GODMODE FOR 30 SECONDS");
            GodMode.setItemMeta(GodMeta);

            ItemStack Invis = new ItemStack(Material.ENDER_EYE);
            lore = new ArrayList<>();
            lore.add("cost");
            lore.add("100");
            ItemMeta InvisMeta = Invis.getItemMeta();
            InvisMeta.setLore(lore);
            InvisMeta.setDisplayName(ChatColor.MAGIC + " Invisible " + ChatColor.DARK_GRAY + " for 60 seconds");
            Invis.setItemMeta(InvisMeta);

            ItemStack Strength = new ItemStack(Material.NETHERITE_AXE);
            lore = new ArrayList<>();
            lore.add("cost");
            lore.add("50");
            ItemMeta StrengthMeta = Strength.getItemMeta();
            StrengthMeta.setLore(lore);
            StrengthMeta.setDisplayName(ChatColor.DARK_PURPLE + "Become STRONG for 60 seconds");
            Strength.setItemMeta(StrengthMeta);

            ItemStack Speed = new ItemStack(Material.SADDLE);
            lore = new ArrayList<>();
            lore.add("cost");
            lore.add("25");
            ItemMeta SpeedMeta = Speed.getItemMeta();
            SpeedMeta.setLore(lore);
            SpeedMeta.setDisplayName(ChatColor.RED + "Become SUPER SPEEDY for 30 seconds");
            Speed.setItemMeta(SpeedMeta);

            ItemStack Jump = new ItemStack(Material.RABBIT_FOOT);
            lore = new ArrayList<>();
            lore.add("cost");
            lore.add("25");
            ItemMeta JumpMeta = Jump.getItemMeta();
            JumpMeta.setLore(lore);
            JumpMeta.setDisplayName(ChatColor.GREEN + "BUNNYhop for 60 seconds");
            Jump.setItemMeta(JumpMeta);

            ItemStack Haste = new ItemStack(Material.NETHERITE_PICKAXE);
            lore = new ArrayList<>();
            lore.add("cost");
            lore.add("25");
            ItemMeta HasteMeta = Haste.getItemMeta();
            HasteMeta.setLore(lore);
            HasteMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Get in a hurry for 30 seconds");
            Haste.setItemMeta(HasteMeta);

            ItemStack Regen = new ItemStack(Material.END_CRYSTAL);
            lore = new ArrayList<>();
            lore.add("cost");
            lore.add("50");
            ItemMeta RegenMeta = Regen.getItemMeta();
            RegenMeta.setLore(lore);
            RegenMeta.setDisplayName(ChatColor.DARK_PURPLE + "Become REYNA for 60 seconds");
            Regen.setItemMeta(RegenMeta);

            ItemStack FireRes = new ItemStack(Material.BLAZE_POWDER);
            lore = new ArrayList<>();
            lore.add("cost");
            lore.add("50");
            ItemMeta FireResMeta = FireRes.getItemMeta();
            FireResMeta.setLore(lore);
            FireResMeta.setDisplayName(ChatColor.DARK_RED + "Become HOT for 60 seconds");
            FireRes.setItemMeta(FireResMeta);

            ItemStack HealthBoost = new ItemStack(Material.DRAGON_EGG);
            lore = new ArrayList<>();
            lore.add("cost");
            lore.add("75");
            ItemMeta HealthBoostMeta = HealthBoost.getItemMeta();
            HealthBoostMeta.setLore(lore);
            HealthBoostMeta.setDisplayName(ChatColor.BLACK + "Become BEEFY for 60 seconds");
            HealthBoost.setItemMeta(HealthBoostMeta);

            buyscreen.setItem(0, HealthBoost);
            buyscreen.setItem(1, FireRes);
            buyscreen.setItem(2, Regen);
            buyscreen.setItem(3, Haste);
            buyscreen.setItem(4, Jump);
            buyscreen.setItem(5, Speed);
            buyscreen.setItem(6, Strength);
            buyscreen.setItem(7, Invis);
            buyscreen.setItem(8, GodMode);
            e.getPlayer().openInventory(buyscreen);
        }
    }

    @EventHandler
    public void clickItem(InventoryClickEvent event) throws InterruptedException {
//        event.getWhoClicked().sendMessage(event.getCurrentItem().getType().toString());
        if (event.getView().getTitle().contains("buffs")) {
                event.setCancelled(true);
            if (event.getCurrentItem().getType().equals(Material.TOTEM_OF_UNDYING)){
                if(leftovertime.get(event.getWhoClicked()) < 200) return;
                leftovertime.replace((Player) event.getWhoClicked(), leftovertime.get(event.getWhoClicked()) - 200);
                event.getWhoClicked().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 600, 5000));
            }
            if (event.getCurrentItem().getType().equals(Material.ENDER_EYE)){
                if(leftovertime.get(event.getWhoClicked()) < 100) return;
                leftovertime.replace((Player) event.getWhoClicked(), leftovertime.get(event.getWhoClicked()) - 100);
                event.getWhoClicked().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 1200, 1, true, false, true));
            }
            if (event.getCurrentItem().getType().equals(Material.NETHERITE_AXE)){
                if(leftovertime.get(event.getWhoClicked()) < 50) return;
                leftovertime.replace((Player) event.getWhoClicked(), leftovertime.get(event.getWhoClicked()) - 50);
                event.getWhoClicked().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1200, 1));
            }
            if (event.getCurrentItem().getType().equals(Material.SADDLE)){
                if(leftovertime.get(event.getWhoClicked()) < 25) return;
                leftovertime.replace((Player) event.getWhoClicked(), leftovertime.get(event.getWhoClicked()) - 25);
                event.getWhoClicked().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 2));
            }
            if (event.getCurrentItem().getType().equals(Material.RABBIT_FOOT)){
                if(leftovertime.get(event.getWhoClicked()) < 25) return;
                leftovertime.replace((Player) event.getWhoClicked(), leftovertime.get(event.getWhoClicked()) - 25);
                event.getWhoClicked().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1200, 2));
            }
            if (event.getCurrentItem().getType().equals(Material.NETHERITE_PICKAXE)){
                if(leftovertime.get(event.getWhoClicked()) < 25) return;
                leftovertime.replace((Player) event.getWhoClicked(), leftovertime.get(event.getWhoClicked()) - 25);
                event.getWhoClicked().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 1200, 100));
            }
            if (event.getCurrentItem().getType().equals(Material.END_CRYSTAL)){
                if(leftovertime.get(event.getWhoClicked()) < 50) return;
                leftovertime.replace((Player) event.getWhoClicked(), leftovertime.get(event.getWhoClicked()) - 50);
                event.getWhoClicked().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1200, 100));
            }
            if (event.getCurrentItem().getType().equals(Material.BLAZE_POWDER)){
                if(leftovertime.get(event.getWhoClicked()) < 50) return;
                leftovertime.replace((Player) event.getWhoClicked(), leftovertime.get(event.getWhoClicked()) - 50);
                event.getWhoClicked().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 1200, 1));
            }
            if (event.getCurrentItem().getType().equals(Material.DRAGON_EGG)){
                if(leftovertime.get(event.getWhoClicked()) < 75) return;
                leftovertime.replace((Player) event.getWhoClicked(), leftovertime.get(event.getWhoClicked()) - 75);
                event.getWhoClicked().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(60);
                event.getWhoClicked().setHealth(60);
                new BukkitRunnable() {
                    @Override
                    public void run() {

                        event.getWhoClicked().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
                    }
                }.runTaskLater(this, 600);


            }
            event.setCancelled(true);
            event.getWhoClicked().closeInventory();
        }
    }


}
