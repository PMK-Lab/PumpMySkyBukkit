package fr.pumpmyskybukkit.commands;

import java.io.IOException;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import fr.pumpmyskycore.IslandManager.IslandManagerConstant;
import fr.pumpmyskycore.exceptions.IslandIsNotEmptyException;
import fr.pumpmyskycore.exceptions.PlayerDoesNotHaveIslandException;

public class LeaveIslandCmd implements ISubCommand {

	@Override
	public boolean onSubCommand(IslandCommandExecutor exec, Player sender, Command cmd, List<String> args) {
		
		try {
			
			boolean purge = exec.getIslandManager().playerLeaveIsland(sender);
			
			if(purge) {
				
				sender.sendMessage(IslandManagerConstant.ISLAND_CHAT_PREFIX +"§d Ile détruite !");
				
			}else {
				
				sender.sendMessage(IslandManagerConstant.ISLAND_CHAT_PREFIX +"§d Vous avez quitté votre ile !");	
				
			}
			
			new CreateIslandCmd().createIslandChatMessage(sender);
			// join message
			
			
		} catch (PlayerDoesNotHaveIslandException e) {
			
			sender.sendMessage(IslandManagerConstant.ISLAND_CHAT_PREFIX + "§r§c Vous ne possedez ou ne faite parti d'aucune ile !");
			new CreateIslandCmd().createIslandChatMessage(sender);
			
		} catch (IslandIsNotEmptyException e) {
			
			sender.sendMessage(IslandManagerConstant.ISLAND_CHAT_PREFIX +"§c Des membres sont encore présent dans votre île !");
			
		} catch (IOException e) {
			
			e.printStackTrace();
			sender.sendMessage("§cERROR !!!! Envoyez le message suivant au staff : " + e.getClass().getName() + " || " + e.getMessage());
			
		}
		
		return true;
		
	}
	
}
