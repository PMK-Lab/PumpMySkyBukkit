package fr.pumpmyskybukkit.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import fr.pumpmyskybukkit.MainSky;

public class InviteIslandCmd implements ISubCommand, TabCompleter {

	private MainSky main;

	public InviteIslandCmd(MainSky main) {
		
		this.main = main;
		
	}
	
	@Override
	public boolean onSubCommand(IslandCommandExecutor exec, Player sender, Command cmd, List<String> args) {
		
		try {
			
			Island island = exec.getIslandManager().playerGetIsland(sender);
			
			if(!island.getOwner().equals(sender.getUniqueId().toString())) {
				
				// error permission island message / vous devez etre propriétaire de l'ile pour faire cela
				sender.sendMessage(IslandManagerConstant.ISLAND_CHAT_PREFIX + "§r§c Vous devez etre propriétaire de l'ile pour faire cela !");
				return true;
				
			}
			
			if(args.isEmpty()) {
				// show lis of current invite
				
				
				
			}else {
				
				String offlinePlayerString = args.get(0);
				
				if(!this.onTabComplete(sender, cmd, null, null).contains(offlinePlayerString) & (offlinePlayerString.startsWith("-") & !this.onTabComplete(sender, cmd, null, null).contains(offlinePlayerString.substring(1)))) {
					// offline player not found
					sender.sendMessage(IslandManagerConstant.ISLAND_CHAT_PREFIX +"§c Joueur invalide !");					
					
				}else {
					// offline player found
					
					if(offlinePlayerString.startsWith("-")) {
						// delete invite
						
						String offlinePlayerName = offlinePlayerString.substring(1);
						@SuppressWarnings("deprecation")
						OfflinePlayer player = this.main.getServer().getOfflinePlayer(offlinePlayerName);
						
						//exec.getIslandManager().playerUninviteIslande(player);
						
						sender.sendMessage(IslandManagerConstant.ISLAND_CHAT_PREFIX +"§d Invitation annulée !");						
						
					}else {
						// invite player
						
						OfflinePlayer player = this.main.getServer().getOfflinePlayer(offlinePlayerString);
						
						//exec.getIslandManager().playerInviteIslande(player);
						
						sender.sendMessage(IslandManagerConstant.ISLAND_CHAT_PREFIX +"§d Invitation bien envoyé !");
						
						if(player.isOnline()) {
							
							Player onlinePlayer = player.getPlayer();
							
							onlinePlayer.sendMessage(IslandManagerConstant.ISLAND_CHAT_PREFIX +"§d " + sender.getName() + " vous a invité à rejoindre son ile !");
							
						}
						
					}
					
				}
				
			}
			
		} catch (PlayerDoesNotHaveIslandException e) {
			
			sender.sendMessage(IslandManagerConstant.ISLAND_CHAT_PREFIX + "§r§c Vous ne possedez ou ne faite parti d'aucune ile !");
			new CreateIslandCmd().createIslandChatMessage(sender);
			
		}
		
		
		
		return true;
		
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		
		ArrayList<String> l = new ArrayList<>();
		
		for (OfflinePlayer offlinePlayer : this.main.getServer().getOfflinePlayers()) {
			
			l.add(offlinePlayer.getName());
			
		}
		
		return l;
		
	}

}
