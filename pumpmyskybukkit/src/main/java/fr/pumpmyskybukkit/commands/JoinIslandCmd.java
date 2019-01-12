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
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;

public class JoinIslandCmd implements ISubCommand, SubTabCompleter {

	@Override
	public boolean onSubCommand(IslandCommandExecutor exec, Player sender, Command cmd, List<String> args) {
		
		BukkitIslandManager manager = exec.getIslandManager();
		
		if(args.size() != 1) {
			
			sender.spigot().sendMessage(new ComponentBuilder(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Synthaxe invalide : /is join <player>").create());
			
		}else {				
					
			OfflinePlayer player = manager.getOfflinePlayerByName(args.get(0));
			
			if(player != null) {
				
				try {
					
					manager.playerAcceptInvitePlot(sender, player);
					sender.spigot().sendMessage(new ComponentBuilder(PlotManagerConstant.PLOT_CHAT_PREFIX +"§d Ile rejoint avec succès !").create());
					
					if(player.isOnline()) {
						
						player.getPlayer().spigot().sendMessage(new ComponentBuilder(PlotManagerConstant.PLOT_CHAT_PREFIX +"§d " + sender.getName() + " a rejoint votre ile !").create());
						
					}
					
				} catch (PlayerAlreadyHavePlotException e) {
					
					sender.spigot().sendMessage(new ComponentBuilder(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Vous faites déjà parti d'une ile !").create());
					new LeaveIslandCmd().leaveIslandChatMessage(sender);
					
				} catch (PlayerDoesNotHavePlotException | PlayerDoesNotInvitedPlotException e) {
					
					sender.spigot().sendMessage(new ComponentBuilder(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Ce joueur ne vous a pas invité !").create());
					
				} catch (IOException e) {
					
					e.printStackTrace();
					sender.spigot().sendMessage(new ComponentBuilder("§cERROR !!!! Envoyez le message suivant au staff : " + e.getClass().getName() + " || " + e.getMessage()).create());
					
				}				
				
			}else {
				
				sender.spigot().sendMessage(new ComponentBuilder(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Joueur invalide : /is join <player>").create());				
				
			}		
			
			
		}
		
		return true;
	}
	
	@Override
	public List<String> onTabComplete(IslandCommandExecutor exec, Player sender, Command command, String alias, String[] args) {
		
		BukkitIslandManager manager = exec.getIslandManager();
		
		List<String> invitersName = new ArrayList<String>();
		
		/*for (Plot island : manager.getPlotInvites().getPlayerInvites()) {
			
			invitersName.add(manager.getMain().getServer().getOfflinePlayer(UUID.fromString(island.getOwner())).getName());
			
		}*/
		
		return invitersName;
		
	}
	
}
