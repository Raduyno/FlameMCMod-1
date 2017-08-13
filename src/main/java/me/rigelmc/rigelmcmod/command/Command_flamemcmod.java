package me.rigelmc.rigelmcmod.command;

import me.rigelmc.rigelmcmod.RigelMCMod;
import me.rigelmc.rigelmcmod.config.ConfigEntry;
import me.rigelmc.rigelmcmod.rank.Rank;
import me.rigelmc.rigelmcmod.util.FLog;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.NON_OP, source = SourceType.BOTH)
@CommandParameters(description = "Shows information about FlameMCMod or reloads it.", usage = "/<command> [reload]", aliases = "fmcm")
public class Command_flamemcmod extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length == 1)
        {
            if (!args[0].equals("reload"))
            {
                return false;
            }

            if (!plugin.al.isAdmin(sender))
            {
                noPerms();
                return true;
            }

            plugin.config.load();
            plugin.services.stop();
            plugin.services.start();

            final String message = String.format("%s v%s reloaded.",
                    RigelMCMod.pluginName,
                    RigelMCMod.pluginVersion);

            msg(message);
            FLog.info(message);
            return true;
        }
        msg("FlameMCMod for 'FlameMC', an all-op freebuild server.", ChatColor.GOLD);
        msg("Version: " + "1.0", ChatColor.GOLD);
        msg("Compiled by: " + "Raduino", ChatColor.GOLD);
        msg("Compile date: " + "11/08/2017", ChatColor.GOLD);
        msg("Running on " + ConfigEntry.SERVER_NAME.getString() + ".", ChatColor.GOLD);
        msg("Credits to Raduino", ChatColor.GOLD);
        msg("Visit " + ChatColor.AQUA + "(forum soon)" + ChatColor.GREEN + " for more information.", ChatColor.GREEN);
        return true;
    }
}
