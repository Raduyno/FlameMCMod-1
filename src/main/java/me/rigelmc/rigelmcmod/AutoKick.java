package me.rigelmc.rigelmcmod;

import me.rigelmc.rigelmcmod.config.ConfigEntry;
import me.rigelmc.rigelmcmod.util.FUtil;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class AutoKick extends FreedomService
{

    public static final long AUTOKICK_RATE = 10 * 20L;
    //
    private BukkitTask kickTask = null;
    private long autoKickTicks;
    private double autoKickThreshold;

    public AutoKick(RigelMCMod plugin)
    {
        super(plugin);
    }

    @Override
    protected void onStart()
    {
        autoKickTicks = (long) ConfigEntry.AUTOKICK_TIME.getInteger() * 1000L;
        autoKickThreshold = ConfigEntry.AUTOKICK_THRESHOLD.getDouble();

        if (!ConfigEntry.AUTOKICK_ENABLED.getBoolean())
        {
            return;
        }

        kickTask = new BukkitRunnable()
        {

            @Override
            public void run()
            {
                autoKickCheck();
            }
        }.runTaskTimer(plugin, AUTOKICK_RATE, AUTOKICK_RATE);
    }

    @Override
    protected void onStop()
    {
        FUtil.cancel(kickTask);
        kickTask = null;
    }

    private void autoKickCheck()
    {

        final boolean doAwayKickCheck
                = plugin.esb.isEssentialsEnabled()
                && ((server.getOnlinePlayers().size() / server.getMaxPlayers()) > autoKickThreshold);

        if (!doAwayKickCheck)
        {
            return;
        }

        for (Player player : server.getOnlinePlayers())
        {
            final long lastActivity = plugin.esb.getLastActivity(player.getName());
            if (lastActivity > 0 && lastActivity + autoKickTicks < System.currentTimeMillis())
            {
            	if (!plugin.al.isAdmin(player))
            	{
            		player.kickPlayer("Automatically kicked by server for inactivity.");
            	}
            }
        }
    }

}
