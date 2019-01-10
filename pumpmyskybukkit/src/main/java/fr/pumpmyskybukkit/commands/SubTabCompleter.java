package fr.pumpmyskybukkit.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public interface SubTabCompleter {

	public List<String> onTabComplete(IslandCommandExecutor exec, Player sender, Command command, String alias, String[] args);
	
}
