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
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;

public class KickIslandCmd implements ISubCommand, SubTabCompleter {


	@Override
	public boolean onSubCommand(IslandCommandExecutor exec, Player sender, Command cmd, List<String> args) {
		
		BukkitIslandManager manager = exec.getIslandManager();
		
		if(args.size() != 1) {
			
			sender.spigot().sendMessage(new ComponentBuilder(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Synthaxe invalide : /is join <player>").create());
			
		}else {				
					
			OfflinePlayer player = manager.getOfflinePlayerByName(args.get(0));
				
			try {
				
				manager.playerKickPlot(sender, player);
				sender.spigot().sendMessage(new ComponentBuilder(PlotManagerConstant.PLOT_CHAT_PREFIX +"§d Joueur exclus avec succès !").create());
				
				if(player.isOnline()) {
					
					Player p = player.getPlayer();
					
					p.spigot().sendMessage(new ComponentBuilder(PlotManagerConstant.PLOT_CHAT_PREFIX +"§c Vous avez été exclu de l'ile !").create());
					p.performCommand("/spawn");
					
				}
				
			} catch (PlayerDoesNotHavePlotException e) {
				
				sender.spigot().sendMessage(new ComponentBuilder(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Vous ne possedez ou ne faite parti d'aucune ile !").create());
				new CreateIslandCmd().createIslandChatMessage(sender);
				
			} catch (RestrictActionToPlotOwnerException e) {
				
				sender.spigot().sendMessage(new ComponentBuilder(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Vous devez être le créateur de l'ile pour faire cela !").create());
				
			} catch (PlayerIsNotMemberPlotException e) {
				
				sender.spigot().sendMessage(new ComponentBuilder(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Ce joueur n'est pas membre de votre ile !").create());
				
			} catch (IOException e) {
				
				e.printStackTrace();
				sender.spigot().sendMessage(new ComponentBuilder("§cERROR !!!! Envoyez le message suivant au staff : " + e.getClass().getName() + " || " + e.getMessage()).create());
				
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
