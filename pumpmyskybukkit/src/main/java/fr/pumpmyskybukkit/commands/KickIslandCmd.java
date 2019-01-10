package fr.pumpmyskybukkit.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import fr.pumpmyplotcore.Plot;
import fr.pumpmyplotcore.PlotManager.PlotManagerConstant;
import fr.pumpmyplotcore.exceptions.PlayerDoesNotHavePlotException;
import fr.pumpmyplotcore.exceptions.PlayerIsNotMemberPlotException;
import fr.pumpmyplotcore.exceptions.RestrictActionToPlotOwnerException;
import fr.pumpmyskybukkit.BukkitIslandManager;

public class KickIslandCmd implements ISubCommand, SubTabCompleter {


	@Override
	public boolean onSubCommand(IslandCommandExecutor exec, Player sender, Command cmd, List<String> args) {
		
		BukkitIslandManager manager = exec.getIslandManager();
		
		if(args.size() != 1) {
			
			sender.sendMessage(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Synthaxe invalide : /is join <player>");
			
		}else {				
					
			OfflinePlayer player = manager.getOfflinePlayerByName(args.get(0));
				
			try {
				
				manager.playerKickPlot(sender, player);
				sender.sendMessage(PlotManagerConstant.PLOT_CHAT_PREFIX +"§d Joueur exclus avec succès !");
				
				if(player.isOnline()) {
					
					Player p = player.getPlayer();
					
					p.sendMessage(PlotManagerConstant.PLOT_CHAT_PREFIX +"§c Vous avez été exclu de l'ile !");
					p.performCommand("/spawn");
					
				}
				
			} catch (PlayerDoesNotHavePlotException e) {
				
				sender.sendMessage(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Vous ne possedez ou ne faite parti d'aucune ile !");
				new CreateIslandCmd().createIslandChatMessage(sender);
				
			} catch (RestrictActionToPlotOwnerException e) {
				
				sender.sendMessage(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Vous devez être le créateur de l'ile pour faire cela !");
				
			} catch (PlayerIsNotMemberPlotException e) {
				
				sender.sendMessage(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Ce joueur n'est pas membre de votre ile !");
				
			} catch (IOException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			
		}
		
		return true;
	}
	
	@Override
	public List<String> onTabComplete(IslandCommandExecutor exec, Player sender, Command command, String alias, String[] args) {
		
		BukkitIslandManager manager = exec.getIslandManager();		
		List<String> invitersName = new ArrayList<String>();
		
		try {
			
			Plot island = manager.playerGetPlot(sender);
			
			for (String uuid : island.getMembersList()) {
				
				invitersName.add(manager.getMain().getServer().getOfflinePlayer(UUID.fromString(uuid)).getName());
				
			}
			
		} catch (PlayerDoesNotHavePlotException e) {
			
			return invitersName;
			
		}
		
		return invitersName;
		
	}
	
}
