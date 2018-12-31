package fr.pumpmyskybukkit.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface SubTabCompleter {

	public List<String> onTabComplete(IslandCommandExecutor exec, CommandSender sender, Command command, String alias, String[] args);
	
}
