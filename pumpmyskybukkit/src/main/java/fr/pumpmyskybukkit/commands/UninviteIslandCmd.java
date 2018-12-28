package fr.pumpmyskybukkit.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import fr.pumpmyskybukkit.MainSky;

public class UninviteIslandCmd implements ISubCommand, TabCompleter {

	private MainSky main;

	public UninviteIslandCmd(MainSky mainSky) {
		
		this.main = mainSky;
		
	}

	@Override
	public boolean onSubCommand(IslandCommandExecutor exec, Player sender, Command cmd, List<String> args) {
		
		if(args.isEmpty()) {
			// show list of current invite
						
			
		}else {
			
			
			
		}
		
		return true;
		
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		
		ArrayList<String> l = new ArrayList<>();
		
		for (OfflinePlayer offlinePlayer : this.main.getServer().getOfflinePlayers()) {
			
			l.add(offlinePlayer.getName());
			
		}
		
		return l;
		
	}

}
