package me.rigelmc.rigelmcmod;

import me.rigelmc.rigelmcmod.util.FLog;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import me.rigelmc.rigelmcmod.config.ConfigEntry;
    

public class AntiSpamBot extends FreedomService
{
    
    public static final List<String> SPAMBOT_IPS = new ArrayList();
    
    public AntiSpamBot(RigelMCMod plugin)
    {
        super(plugin);
    }

    @Override
    protected void onStart()
    {
        try
        {
            Scanner scanner = new Scanner(new File(plugin.getDataFolder().getPath() + "/deathbotips.txt"));
            while(scanner.hasNextLine())
            {
                SPAMBOT_IPS.add(scanner.nextLine());
            }
        }
        catch(FileNotFoundException e)
        {
            FLog.warning("The deathbotips.txt file was not found!");
            this.unregister();
        }
        FLog.info("Loaded " + SPAMBOT_IPS.size() + " spambot ips");
    }

    @Override
    protected void onStop()
    {
        SPAMBOT_IPS.clear();
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerPreLogin(AsyncPlayerPreLoginEvent event)
    {
        final String ip = event.getAddress().getHostAddress().trim();
        if (SPAMBOT_IPS.contains(ip))
        {
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, ChatColor.RED + "You've been detected as a spambot. If you believe this is an error please file a permban appeal on " + ChatColor.DARK_BLUE + ConfigEntry.SERVER_PERMBAN_URL.getString());
        }
    }
}
