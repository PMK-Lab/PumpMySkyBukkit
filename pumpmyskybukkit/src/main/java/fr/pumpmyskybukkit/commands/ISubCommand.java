package fr.pumpmyskybukkit.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public interface ISubCommand {
	
	public boolean onSubCommand(IslandCommandExecutor exec,Player sender, Command cmd, List<String> args);
	
}
