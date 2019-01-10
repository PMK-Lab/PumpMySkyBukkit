package fr.pumpmyskybukkit.commands;

import java.io.IOException;
import java.util.List;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.pumpmyplotcore.PlotManager.PlotManagerConstant;
import fr.pumpmyplotcore.exceptions.PlayerDoesNotHavePlotException;
import fr.pumpmyplotcore.exceptions.PlayerDoesNotInvitedPlotException;
import fr.pumpmyplotcore.exceptions.RestrictActionToPlotOwnerException;
import fr.pumpmyskybukkit.BukkitIslandManager;
import fr.pumpmyskybukkit.MainSky;

public class UninviteIslandCmd implements ISubCommand, SubTabCompleter {

	private MainSky main;

	public UninviteIslandCmd(MainSky mainSky) {
		
		this.main = mainSky;
		
	}

	@Override
	public boolean onSubCommand(IslandCommandExecutor exec, Player sender, Command cmd, List<String> args) {
		
		if(args.isEmpty() | args.size() != 1) {
			// invalide syntax			
			sender.sendMessage(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Synthaxe invalide : /is uninvite <player>");						
			
		}else {
			
			BukkitIslandManager manager = exec.getIslandManager();			
			OfflinePlayer player = manager.getOfflinePlayerByName(args.get(0));
			
			if(player != null) {
				
				try {
					
					manager.playerUninvitePlot(sender, (Player) player);
					
					if(player.isOnline()) {
						
						player.getPlayer().sendMessage(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§d " + sender.getName() + " a supprimé l'invatitation qu'il vous inventant à rejoindre son ile !");
						
					}
					
				} catch (PlayerDoesNotHavePlotException e) {
					
					sender.sendMessage(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Vous ne possedez ou ne faite parti d'aucune ile !");
					new CreateIslandCmd().createIslandChatMessage(sender);
					
				} catch (RestrictActionToPlotOwnerException e) {
					
					sender.sendMessage(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Vous devez être le créateur de l'ile pour faire cela !");
					
				} catch (PlayerDoesNotInvitedPlotException e) {
					
					sender.sendMessage(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Ce joueur n'a pas été invité à rejoindre votre ile !");
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else {
				
				sender.sendMessage(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Synthaxe invalide : /is uninvite <player>");
				
			}			
			
		}
		
		return true;
		
	}

	@Override
	public List<String> onTabComplete(IslandCommandExecutor exec, CommandSender sender, Command command, String alias,
			String[] args) {
		// TODO Auto-generated method stub
		return null;
	}

}
