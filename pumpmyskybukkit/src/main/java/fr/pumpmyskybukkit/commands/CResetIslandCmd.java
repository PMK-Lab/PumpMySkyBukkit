package fr.pumpmyskybukkit.commands;

import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CResetIslandCmd implements ISubCommand {

	@Override
	public boolean onSubCommand(IslandCommandExecutor exec, Player sender, Command cmd, List<String> args) {
		return false;
		/*
		IslandManager is = MainOzone.getIslandManager();
		
		if(is.playerHasIsland(sender)) {
			
			Island island = MainOzone.getIslandManager().getIsland(sender);
			
			Server server = MainOzone.getInstance().getServer();
			
			if(sender.getUniqueId().toString().equals(island.getOwnerUUID())) {
				
				List<String> futureMemberList = new ArrayList<>();
				
				// reset de l'island
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				// reset & set de l'owner
				
				String owner = sender.getUniqueId().toString();
				
				server.dispatchCommand(server.getConsoleSender(), "bq_admin reset all " + owner); // reset des quetes
				
				island.setOwnerUUID("none");
				
				sender.sendMessage(Island.prefix + "�r�d Votre ile est en cours de reset !");
				sender.teleport(new Location(Bukkit.getWorld("Void"), -29, 84, -480));
				
				try {
					IslandManager.unsetIsland(sender);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					System.out.println("Unset Island ERROR");
					e1.printStackTrace();
				}
				
				CreateIslandCmd ciCmd = new CreateIslandCmd();				
				ciCmd.onSubCommand(sender, cmd, args); // cr�ation de la nouvelle ile	
				
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				// r�cup�ration de la liste des joueurs
				
				for (Iterator<String> i = island.getPlayerList().iterator(); i.hasNext();) {
					
					String uuid = i.next();
					
					futureMemberList.add(uuid); 	// ajout � la future team
					
					i.remove();
					
					OfflinePlayer offp = server.getOfflinePlayer(UUID.fromString(uuid));
					
					server.dispatchCommand(server.getConsoleSender(), "bq_admin reset all " + uuid); // reset des quetes
					
					if(offp.isOnline()) {
						
						((CommandSender) offp).sendMessage(Island.prefix + "�r�d Votre ile est en cours de reset !");
						((Player) offp).teleport(new Location(Bukkit.getWorld("Void"), -29, 84, -480));
						
					}
					
					try {
						IslandManager.unsetIsland(offp);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println("Unset Island ERROR");
						e.printStackTrace();
					}
					
				}
				
				try {
					island.save();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
				// ajout des nouveaux membres
				
				island = MainOzone.getIslandManager().getIsland(sender); 	// r�cup�ration de la nouvelle ile
				
				for (String uuid : futureMemberList) {
				
					OfflinePlayer offp = server.getOfflinePlayer(UUID.fromString(uuid));
					
					island.addPlayer(offp);
					
					if(offp.isOnline()) {
						
						((Player) offp).sendMessage(Island.prefix +"�d Ile recr��e avec succ�s !");
						new CreateIslandCmd().aide1((Player) offp);
						
					}		
					
					
				}
				
				try {
					island.save();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				// reset termin�
				
				return true;
				
			}else {
				
				// impossible car pas propri�taire
				new ResetIslandCmd().aide3(sender);
				return true;
				
			}
			
		}else {
			
			new ResetIslandCmd().aide(sender);		
			return true;
		}
		*/
	}

}
