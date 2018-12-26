package fr.pumpmyskybukkit.commands;

import java.io.IOException;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import fr.pumpmyskycore.IslandManager.IslandManagerConstant;
import fr.pumpmyskycore.exceptions.IslandIsNotEmptyException;
import fr.pumpmyskycore.exceptions.PlayerDoesNotHaveIslandException;

public class LeaveIslandCmd implements ISubCommand {

	@Override
	public boolean onSubCommand(IslandCommandExecutor exec, Player sender, Command cmd, List<String> args) {
		return false;
		/*
		IslandManager is = MainOzone.getIslandManager();
		
		if(is.playerHasIsland(sender)) {
			// t�l�portation
			
			Island island = MainOzone.getIslandManager().getIsland(sender);
			
			if(island.getOwnerUUID().equals(sender.getUniqueId().toString())) {
				
				if(island.getPlayerList().size() != 0) {
					
					//impossible de quitter
					aide1(sender);
					return true;
					
				}				
				
				island.setOwnerUUID("none");
				
			}
			//quitter
			sender.sendMessage(Island.prefix + "�r�d Vous venez de quitter votre �le !");
			sender.teleport(new Location(Bukkit.getWorld("Void"), -29, 84, -480));
			
			for (Iterator<String> i = island.getPlayerList().iterator(); i.hasNext();) {
				
				if(i.next().equals(sender.getUniqueId().toString()))
					i.remove();
				
			}
			
			try {
				island.save();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				IslandManager.unsetIsland(sender);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return true;
			
		}else {
			// affichage aide
			aide(sender);
			new GoToIslandCmd().aide1(sender);
			return false;
			
		}
		*/
	}
/*
	@Override
	public void aide(Player p) {
		// TODO Auto-generated method stub
		p.sendMessage(Island.prefix + "�r�c Vous devez avoir une �le pour pouvoir la quitter !");
	}
	
	public void aide1(Player p) {
		// TODO Auto-generated method stub
		p.sendMessage(Island.prefix + "�r�c Vous etes le cr�ateur de cette ile, ne laissez pas tomber vos co�quipiers !");
	}
*/
}
