package fr.pumpmyskybukkit.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import fr.pumpmyskybukkit.BukkitIslandManager;

public class AddIslandCmd implements ISubCommand, TabCompleter {
	
	private BukkitIslandManager manager;
	
	public AddIslandCmd(BukkitIslandManager manager) {
		this.manager = manager;
	}
	
	@Override
	public boolean onSubCommand(Player sender, Command cmd, List<String> args) {
		return false;
		// TODO Auto-generated method stub
		/*
		IslandManager is = MainOzone.getIslandManager();
		
		if(is.playerHasIsland(sender)) {
			
			Island island = MainOzone.getIslandManager().getIsland(sender);
			
			if(!sender.getUniqueId().toString().equals(island.getOwnerUUID())) {
				aide4(sender);
				return true;
			}
			
			if(args.isEmpty()) {
				aide(sender);
				return false;
			}else {
				
				Player p = MainOzone.getInstance().getServer().getPlayer(args.get(0));
				
				if(p != null) {
					// joueur trouv�
					
					if(island.getPlayerList().contains(p.getUniqueId().toString())) {
						
						aide5(sender);
						return false;
					}
					
					if(p.getName().equals(sender.getName())) {
						// check invite soit meme
						aide1(sender);
						return false;
						
					}else {
						// ajout dans la liste d'invitation
						File f = new File(MainOzone.getInstance().getDataFolder(),"islands_invite.yml");
						YamlConfiguration y = MainOzone.getConf().getConfiguration(f);
						
						if(!y.contains("invite." + p.getUniqueId().toString())) {
							y.set("invite." + p.getUniqueId().toString(), new ArrayList<String>());
						}
						
						List<String> l = y.getStringList("invite." + p.getUniqueId().toString());
						
						Island i = is.getIsland(sender);
						
						if(l.contains(i.getOwnerUUID())) {
							//d�j� ajout�
							aide2(sender);
							return false;
						}else {
							// pas encore ajout�
							l.add(i.getOwnerUUID());
							y.set("invite." + p.getUniqueId().toString(), l);
							
							try {
								// r�ussite donc ajout
								y.save(f);
								sender.sendMessage(Island.prefix +"�d " + p.getDisplayName() + " a bien �t� invit� sur votre �le !");
								p.sendMessage(Island.prefix + "�d" + sender.getDisplayName() + " vous a invit� sur son �le !");
								return true;
							} catch (IOException e) {
								// erreur de save
								e.printStackTrace();
								sender.sendMessage("�cException save invite....");
								return false;
							}
							
						}							
						
					}
					
				}else {
					// joueur non valide
					aide(sender);
					return false;
				}
				
			}
				
		}else {
			// affichage aide
			aide3(sender);
			new GoToIslandCmd().aide1(sender);
			return true;
		}
		*/
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		
		List<String> l = new ArrayList<>();
		
		for (OfflinePlayer offlinePlayer : this.manager.getMain().getServer().getOfflinePlayers()) {
			
			l.add(offlinePlayer.getName());
			
		}
		
		return l;
		
	}
	
	/*
	
	private void aide5(Player p) {
		// TODO Auto-generated method stub
		p.sendMessage(Island.prefix + "�r�c Le joueur invit� fait d�j� parti de votre �le !");
		
	}



	public void aide4(Player p) {
		
		p.sendMessage(Island.prefix + "�r�c Vous devez etre propri�taire de l'�le pour faire cela !");
		
	}
	
	private void aide3(Player p) {
		
		p.sendMessage(Island.prefix + "�r�c Sans ile, vous ne pouvez pas ajouter un joueur � celle-ci !");
		
	}

	private void aide2(Player p) {
		// TODO Auto-generated method stub
		p.sendMessage(Island.prefix + "�r�c Vous avez d�j� invit� cette personne � rejoindre votre ile !");
	}

	@Override
	public void aide(Player p) {
		// TODO Auto-generated method stub
		p.sendMessage(Island.prefix + "�r�c Vous devez donn� un nom de joueur non valide et connect� !");
	}
	
	public void aide1(Player p) {
		
		p.sendMessage(Island.prefix + "�r�c Vous ne pouvez pas vous ajouter vous m�me !");
		
	}
	*/
}
