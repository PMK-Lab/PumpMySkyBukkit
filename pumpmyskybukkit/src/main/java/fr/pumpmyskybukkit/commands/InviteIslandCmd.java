package fr.pumpmyskybukkit.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.pumpmyplotcore.PlotManager.PlotManagerConstant;
import fr.pumpmyplotcore.exceptions.PlayerAlreadyInvitedPlotException;
import fr.pumpmyplotcore.exceptions.PlayerDoesNotHavePlotException;
import fr.pumpmyplotcore.exceptions.RestrictActionToPlotOwnerException;
import fr.pumpmyskybukkit.BukkitIslandManager;
import fr.pumpmyskybukkit.MainSky;

public class InviteIslandCmd implements ISubCommand, SubTabCompleter {

	private MainSky main;

	public InviteIslandCmd(MainSky main) {
		
		this.main = main;
		
	}
	
	@Override
	public boolean onSubCommand(IslandCommandExecutor exec, Player sender, Command cmd, List<String> args) {
			
		if(args.size() != 1) {
			// invalide syntax			
			sender.sendMessage(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Synthaxe invalide : /is uninvite <player>");						
			
		}else {
			
			BukkitIslandManager manager = exec.getIslandManager();			
			OfflinePlayer player = manager.getOfflinePlayerByName(args.get(0));
			
			if(player != null) {
				
				try {
					
					manager.playerInvitePlot(sender, (Player) player);
					
					if(player.isOnline()) {
						
						player.getPlayer().sendMessage(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§d " + sender.getName() + " vous a invité à rejoindre son ile !");
						
					}
					
				} catch (PlayerDoesNotHavePlotException e) {
					
					sender.sendMessage(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Vous ne possedez ou ne faite parti d'aucune ile !");
					new CreateIslandCmd().createIslandChatMessage(sender);
					
				} catch (RestrictActionToPlotOwnerException e) {
					
					sender.sendMessage(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Vous devez être le créateur de l'lie pour faire cela !");
					
				} catch (PlayerAlreadyInvitedPlotException e) {
					
					sender.sendMessage(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Vous avez déjà invité ce joueur à rejoindre votre ile !");
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else {
				
				sender.sendMessage(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Synthaxe invalide : /is invite <player>");
				
			}		
			
		}
		
		return true;
		
	}

	@Override
	public List<String> onTabComplete(IslandCommandExecutor exec, CommandSender sender, Command command, String alias, String[] args) {
		
		Player player = (Player) sender;
		BukkitIslandManager manager = exec.getIslandManager();
		
		if(manager.playerIsOwner(player)) {
			
			ArrayList<String> l = new ArrayList<>();
			
			for (OfflinePlayer offlinePlayer : this.main.getServer().getOfflinePlayers()) {						
				
				l.add(offlinePlayer.getName());
				
			}
			
			return l;
			
		}else {
			
			return new ArrayList<>();
			
		}
		
	}

}
