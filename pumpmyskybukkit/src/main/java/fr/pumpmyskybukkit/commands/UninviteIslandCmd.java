package fr.pumpmyskybukkit.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import fr.pumpmyplotcore.PlotManager.PlotManagerConstant;
import fr.pumpmyplotcore.exceptions.PlayerDoesNotHavePlotException;
import fr.pumpmyplotcore.exceptions.PlayerDoesNotInvitedPlotException;
import fr.pumpmyplotcore.exceptions.RestrictActionToPlotOwnerException;
import fr.pumpmyskybukkit.BukkitIslandManager;
import net.md_5.bungee.api.chat.ComponentBuilder;

public class UninviteIslandCmd implements ISubCommand, SubTabCompleter {

	@Override
	public boolean onSubCommand(IslandCommandExecutor exec, Player sender, Command cmd, List<String> args) {
		
		if(args.size() != 1) {
			// invalide syntax			
			sender.spigot().sendMessage(new ComponentBuilder(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Synthaxe invalide : /is uninvite <player>").create());						
			
		}else {
			
			BukkitIslandManager manager = exec.getIslandManager();			
			OfflinePlayer player = manager.getOfflinePlayerByName(args.get(0));
			
			if(player != null) {
				
				try {
					
					manager.playerUninvitePlot(sender,player);
					sender.spigot().sendMessage(new ComponentBuilder(PlotManagerConstant.PLOT_CHAT_PREFIX +"§d Invitation supprimée avec succès !").create());
					
					if(player.isOnline()) {
						
						player.getPlayer().spigot().sendMessage(new ComponentBuilder(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§d " + sender.getName() + " a supprimé l'invatitation vous invitant à rejoindre son ile !").create());
						
					}
					
				} catch (PlayerDoesNotHavePlotException e) {
					
					sender.spigot().sendMessage(new ComponentBuilder(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Vous ne possedez ou ne faite parti d'aucune ile !").create());
					new CreateIslandCmd().createIslandChatMessage(sender);
					
				} catch (RestrictActionToPlotOwnerException e) {
					
					sender.spigot().sendMessage(new ComponentBuilder(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Vous devez être le créateur de l'ile pour faire cela !").create());
					
				} catch (PlayerDoesNotInvitedPlotException e) {
					
					sender.spigot().sendMessage(new ComponentBuilder(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Ce joueur n'a pas été invité à rejoindre votre ile !").create());
					
				} catch (IOException e) {
					
					e.printStackTrace();
					sender.spigot().sendMessage(new ComponentBuilder("§cERROR !!!! Envoyez le message suivant au staff : " + e.getClass().getName() + " || " + e.getMessage()).create());
					
				}
				
			}else {
				
				sender.spigot().sendMessage(new ComponentBuilder(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Synthaxe invalide : /is uninvite <player>").create());
				
			}			
			
		}
		
		return true;
		
	}

	@Override
	public List<String> onTabComplete(IslandCommandExecutor exec, Player sender, Command command, String alias, String[] args) {
		
		BukkitIslandManager manager = exec.getIslandManager();
		
		if(manager.playerIsOwner(sender)) {
			
			ArrayList<String> l = new ArrayList<>();
			
			for (OfflinePlayer offlinePlayer : exec.getIslandManager().getMain().getServer().getOfflinePlayers()) {						
				
				l.add(offlinePlayer.getName());
				
			}
			
			return l;
			
		}else {
					
			return new ArrayList<>();
			
		}
		
	}

}
