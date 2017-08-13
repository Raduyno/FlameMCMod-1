package me.rigelmc.rigelmcmod.command;

import me.rigelmc.rigelmcmod.rank.Rank;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


@CommandPermissions(level = Rank.SENIOR_ADMIN, source = SourceType.BOTH)
@CommandParameters(description = "For rogue ops and mean people", usage = "/<command> <player>", aliases = "silentkick, sk")
public class Command_timeout extends FreedomCommand
{
    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {      
        if (args.length == 1) 
        {
            return false;
        }
        
        final Player player = getPlayer(args[0]);
        
        if (player == null) 
        {
            msg(ChatColor.RED + "Player not found!");
        }
        
        player.kickPlayer(ChatColor.WHITE + "Disconnected.");
    
        return true;
    }

}
