package me.rigelmc.rigelmcmod.blocking;

import me.rigelmc.rigelmcmod.FreedomService;
import me.rigelmc.rigelmcmod.RigelMCMod;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.Material;
import java.util.Arrays;
import java.util.List;

public class ItemBlocker extends FreedomService
{
    public static final List<Material> BLOCKED_ITEMS = Arrays.asList(new Material[]
    {
        Material.MAP, Material.EMPTY_MAP
    });

    public ItemBlocker(RigelMCMod plugin)
    {
        super(plugin);
    }

    @Override
    protected void onStart()
    {
    }

    @Override
    protected void onStop()
    {
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryItemMove(InventoryMoveItemEvent event)
    {
        if (BLOCKED_ITEMS.contains(event.getItem().getType()))
        {
            event.setCancelled(true);
            event.getSource().remove(event.getItem().getType());
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerPickupItem(PlayerPickupItemEvent event)
    {
        if (BLOCKED_ITEMS.contains(event.getItem().getType()))
        {
            event.setCancelled(true);
            event.getItem().remove();
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryOpen(InventoryOpenEvent event)
    {
        for (Material material : BLOCKED_ITEMS)
        {
            if (event.getInventory().contains(material))
            {
                event.getInventory().remove(material);
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDropItem(PlayerDropItemEvent event)
    {
        if (BLOCKED_ITEMS.contains(event.getItemDrop().getItemStack().getType()))
        {
            event.setCancelled(true);
            event.getPlayer().getInventory().remove(event.getItemDrop().getItemStack().getType());
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryInteract(InventoryInteractEvent event)
    {
        Boolean doclear = false;
        for (Material material : BLOCKED_ITEMS)
        {
            for (ItemStack itemstack : event.getInventory().getContents())
            {
                if (doclear)
                {
                    break;
                }
                try
                {
                    if (itemstack.getType().equals(material))
                    {
                        doclear = true;
                        break;
                    }
                }
                catch (NullPointerException e)
                {
                    continue;
                }
            }
        }
        if (doclear)
        {
            event.setCancelled(true);
            for (Material material : BLOCKED_ITEMS)
            {
                event.getInventory().remove(material);
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClick(InventoryClickEvent event)
    {
        Boolean doclear = false;
        for (Material material : BLOCKED_ITEMS)
        {
            for (ItemStack itemstack : event.getInventory().getContents())
            {
                if (doclear)
                {
                    break;
                }
                try
                {
                    if (itemstack.getType().equals(material))
                    {
                        doclear = true;
                        break;
                    }
                }
                catch (NullPointerException e)
                {
                    continue;
                }
            }
        }
        if (doclear)
        {
            event.setCancelled(true);
            for (Material material : BLOCKED_ITEMS)
            {
                event.getInventory().remove(material);
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerItemHeld(PlayerItemHeldEvent event)
    {
        Boolean doclear = false;
        for (Material material : BLOCKED_ITEMS)
        {
            for (ItemStack itemstack : event.getPlayer().getInventory().getContents())
            {
                if (doclear)
                {
                    break;
                }
                try
                {
                    if (itemstack.getType().equals(material))
                    {
                        doclear = true;
                        break;
                    }
                }
                catch (NullPointerException e)
                {
                    continue;
                }
            }
        }
        if (doclear)
        {
            event.setCancelled(true);
            for (Material material : BLOCKED_ITEMS)
            {
                event.getPlayer().getInventory().remove(material);
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryDrag(InventoryDragEvent event)
    {
        Boolean doclear = false;
        for (Material material : BLOCKED_ITEMS)
        {
            for (ItemStack itemstack : event.getInventory().getContents())
            {
                if (doclear)
                {
                    break;
                }
                try
                {
                    if (itemstack.getType().equals(material))
                    {
                        doclear = true;
                        break;
                    }
                }
                catch (NullPointerException e)
                {
                    continue;
                }
            }
        }
        if (doclear)
        {
            event.setCancelled(true);
            for (Material material : BLOCKED_ITEMS)
            {
                event.getInventory().remove(material);
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClose(InventoryCloseEvent event)
    {
        Boolean doclear = false;
        for (Material material : BLOCKED_ITEMS)
        {
            for (ItemStack itemstack : event.getInventory().getContents())
            {
                if (doclear)
                {
                    break;
                }
                try
                {
                    if (itemstack.getType().equals(material))
                    {
                        doclear = true;
                        break;
                    }
                }
                catch (NullPointerException e)
                {
                    continue;
                }
            }
        }
        if (doclear)
        {
            for (Material material : BLOCKED_ITEMS)
            {
                event.getInventory().remove(material);
            }
        }
    }
}
