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
import fr.pumpmyplotcore.exceptions.PlayerAlreadyHavePlotException;
import fr.pumpmyplotcore.exceptions.PlayerDoesNotHavePlotException;
import fr.pumpmyplotcore.exceptions.PlayerDoesNotInvitedPlotException;
import fr.pumpmyskybukkit.BukkitIslandManager;

public class JoinIslandCmd implements ISubCommand, SubTabCompleter {

	@Override
	public boolean onSubCommand(IslandCommandExecutor exec, Player sender, Command cmd, List<String> args) {
		
		BukkitIslandManager manager = exec.getIslandManager();
		
		if(args.size() != 1) {
			
			sender.sendMessage(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Synthaxe invalide : /is join <player>");
			
		}else {				
					
			OfflinePlayer player = manager.getOfflinePlayerByName(args.get(0));
						
			try {
				
				manager.playerAcceptInvitePlot(sender, player);
				
			} catch (PlayerAlreadyHavePlotException e) {
				
				sender.sendMessage(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Vous faites déjà parti d'une ile !");
				new LeaveIslandCmd().leaveIslandChatMessage(sender);
				
			} catch (PlayerDoesNotHavePlotException | PlayerDoesNotInvitedPlotException e) {
				
				sender.sendMessage(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Ce joueur ne vous a pas invité !");
				new LeaveIslandCmd().leaveIslandChatMessage(sender);
				
			} catch (IOException e) {
				
				e.printStackTrace();
				sender.sendMessage("§cERROR !!!! Envoyez le message suivant au staff : " + e.getClass().getName() + " || " + e.getMessage());
				
			}			
			
		}
		
		return false;
	}
	
	@Override
	public List<String> onTabComplete(IslandCommandExecutor exec, Player sender, Command command, String alias, String[] args) {
		
		BukkitIslandManager manager = exec.getIslandManager();
		
		List<String> invitersName = new ArrayList<String>();
		
		for (Plot island : manager.getPlotInvites().getPlayerInvites(sender.getUniqueId())) {
			
			invitersName.add(manager.getMain().getServer().getOfflinePlayer(UUID.fromString(island.getOwner())).getName());
			
		}
		
		return invitersName;
		
	}
	
}
