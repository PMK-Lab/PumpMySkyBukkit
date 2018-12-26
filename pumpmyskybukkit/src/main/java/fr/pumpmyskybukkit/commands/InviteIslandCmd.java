package fr.pumpmyskybukkit.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import fr.pumpmyskybukkit.MainSky;

public class InviteIslandCmd implements ISubCommand, TabCompleter {

	private MainSky main;

	public InviteIslandCmd(MainSky main) {
		
		this.main = main;
		
	}
	
	@Override
	public boolean onSubCommand(IslandCommandExecutor exec, Player sender, Command cmd, List<String> args) {
		// TODO Auto-generated method stub
		return false;
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
