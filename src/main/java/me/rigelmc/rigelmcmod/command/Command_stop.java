package me.rigelmc.rigelmcmod.command;

import me.rigelmc.rigelmcmod.rank.Rank;
import me.rigelmc.rigelmcmod.util.FUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.apache.commons.lang3.StringUtils;

@CommandPermissions(level = Rank.SUPER_ADMIN, source = SourceType.BOTH)
@CommandParameters(description = "Kicks everyone and stops the server.", usage = "/<command> [reason]")
public class Command_stop extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        FUtil.bcastMsg("Server is going offline!", ChatColor.LIGHT_PURPLE);
        String r = "Server is going offline, come back in about 20 seconds.";
        if (args.length > 0)
        {
            r = StringUtils.join(args, " ", 0, args.length);
        }
        for (Player player : server.getOnlinePlayers())
        {
            player.kickPlayer(r);
        }
        server.shutdown();
        return true;
    }
}
