package fr.pumpmyskybukkit.commands;

import java.util.List;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import fr.pumpmyskybukkit.BukkitIslandManager;
import fr.pumpmyskycore.Island;
import fr.pumpmyskycore.IslandManager.IslandManagerConstant;
import fr.pumpmyskycore.exceptions.PlayerDoesNotHaveIslandException;

public class InfoIslandCmd implements ISubCommand {

	@Override
	public boolean onSubCommand(IslandCommandExecutor exec, Player sender, Command cmd, List<String> args) {
		
		BukkitIslandManager manager = exec.getIslandManager();
		
		try {
			
			Island island = manager.playerGetIsland(sender);
			
			sender.sendMessage(IslandManagerConstant.ISLAND_CHAT_PREFIX + "§r§f " + island.getName() + "(§r§7§o" + manager.getMain().getServer().getOfflinePlayer(UUID.fromString(island.getOwner())).getName() + "§r§f)");
			
			sender.sendMessage("  §a█§r§f Home( x:" + island.getHomeX() + " | y:" + island.getHomeY() + " | z:" + island.getHomeZ() + " )");
			
			sender.sendMessage("  §a█§r§f Membres :");
			
			for (String string : island.getMembersList()) {
				
				sender.sendMessage("   §a»§r§f" + manager.getMain().getServer().getOfflinePlayer(UUID.fromString(string)).getName());
				
			}
			
		} catch (PlayerDoesNotHaveIslandException e) {
			
			sender.sendMessage(IslandManagerConstant.ISLAND_CHAT_PREFIX + "§r§c Vous ne possedez ou ne faite parti d'aucune ile !");
			new CreateIslandCmd().createIslandChatMessage(sender);
			
		}
		
		return true;
		
	}

}
