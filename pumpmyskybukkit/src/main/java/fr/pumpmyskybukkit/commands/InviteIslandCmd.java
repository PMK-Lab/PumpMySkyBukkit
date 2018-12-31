package fr.pumpmyskybukkit.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.pumpmyskybukkit.BukkitIslandManager;
import fr.pumpmyskybukkit.MainSky;
import fr.pumpmyskycore.IslandManager.IslandManagerConstant;
import fr.pumpmyskycore.exceptions.PlayerAlreadyInvited;
import fr.pumpmyskycore.exceptions.PlayerDoesNotHaveIslandException;
import fr.pumpmyskycore.exceptions.RestrictActionToOwnerIslandException;

public class InviteIslandCmd implements ISubCommand, SubTabCompleter {

	private MainSky main;

	public InviteIslandCmd(MainSky main) {
		
		this.main = main;
		
	}
	
	@Override
	public boolean onSubCommand(IslandCommandExecutor exec, Player sender, Command cmd, List<String> args) {
			
		if(args.isEmpty() | args.size() != 1) {
			// invalide syntax			
			sender.sendMessage(IslandManagerConstant.ISLAND_CHAT_PREFIX + "§r§c Synthaxe invalide : /is uninvite <player>");						
			
		}else {
			
			BukkitIslandManager manager = exec.getIslandManager();			
			OfflinePlayer player = manager.getOfflinePlayerByName(args.get(0));
			
			if(player != null) {
				
				
			}else {
				
				
				
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
