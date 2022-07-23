package es.marcos.autosmelt;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class AutoSmelt extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onBreakOre(BlockBreakEvent e) {
        Block block = e.getBlock();
        GameMode gamemode = e.getPlayer().getGameMode();

        if (gamemode.equals(GameMode.SURVIVAL)) {
            switch (block.getType()) {
                case GOLD_ORE:
                    ItemStack goldIngot = new ItemStack(Material.GOLD_INGOT, 1);
                    e.setDropItems(false);
                    e.getPlayer().getInventory().addItem(goldIngot);
                    e.setExpToDrop(1);
                    break;

                case IRON_ORE:
                    ItemStack ironIngot = new ItemStack(Material.IRON_INGOT, 1);
                    e.setDropItems(false);
                    e.getPlayer().getInventory().addItem(ironIngot);
                    e.setExpToDrop(1); //It is actually 0.7 per ore smelt
                default:
                    break;
            }
        }
    }
}
