package fr.pumpmyskybukkit.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.pumpmyplotcore.Plot;
import fr.pumpmyplotcore.PlotManager.PlotManagerConstant;
import fr.pumpmyskybukkit.BukkitIslandManager;

public class JoinIslandCmd implements ISubCommand, SubTabCompleter {

	@Override
	public boolean onSubCommand(IslandCommandExecutor exec, Player sender, Command cmd, List<String> args) {
		
		BukkitIslandManager manager = exec.getIslandManager();
		
		if(args.size() != 1) {
			
			sender.sendMessage(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Synthaxe invalide : /is join <player>");
			
		}else {
			
			if(manager.playerHasPlot(sender)) {
				
				sender.sendMessage(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Vous faites déjà parti d'une ile !");
				new LeaveIslandCmd().leaveIslandChatMessage(sender);
				
			}else {
				
				if(this.onTabComplete(exec, sender, cmd, null, null).contains(args.get(0))) {
					
					OfflinePlayer player = manager.getOfflinePlayerByName(args.get(0));
					
					manager.playerJoinIsland(player);
					
				}else {
					
					sender.sendMessage(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Joueur invalide : /is join <player>");
					
				}
				
			}
			
		}
		
		return false;
	}
	
	@Override
	public List<String> onTabComplete(IslandCommandExecutor exec, CommandSender sender, Command command, String alias, String[] args) {
		
		BukkitIslandManager manager = exec.getIslandManager();
		
		List<String> invitersName = new ArrayList<String>();
		
		for (Plot island : manager.getPlotInvites().getPlayerInvites(((Player) sender).getUniqueId())) {
			
			invitersName.add(manager.getMain().getServer().getOfflinePlayer(UUID.fromString(island.getOwner())).getName());
			
		}
		
		return invitersName;
		
	}
	
}


		// TODO Auto-generated method stub
		/*
		IslandManager is = MainOzone.getIslandManager();
		
		if(!is.playerHasIsland(sender)) {
			
			File f = new File(MainOzone.getInstance().getDataFolder(),"islands_invite.yml");
			YamlConfiguration y = MainOzone.getConf().getConfiguration(f);
			
			if(!y.contains("invite." + sender.getUniqueId().toString())) {
				y.set("invite." + sender.getUniqueId().toString(), new ArrayList<String>());
			}
			
			List<String> l = y.getStringList("invite." + sender.getUniqueId().toString());
			
			if(args.isEmpty()) {
				
				aide2(sender,l);
				return true;
				
			}else {
				
				String u = args.get(0);
				
				if(!l.contains(u)) {
					
					aide1(sender);
					return false;
					
				}else {
					
					// ajout à l'ile		
					try {
						String a = args.get(1);
						
						if(a.equals("-a")) {
							
							for (Iterator<String> i = l.iterator(); i.hasNext();) {
								
								if(i.next().equals(u)) {
									
									i.remove();
									
									OfflinePlayer owner = MainOzone.getInstance().getServer().getOfflinePlayer(UUID.fromString(u));
									
									Island island = MainOzone.getIslandManager().getIsland(owner);
									
									IslandFileData isfd = island.getIslandData();
									
									island.addPlayer(sender);
									island.save();
									
									IslandManager.setIsland(sender, isfd);
									
									y.set("invite." + sender.getUniqueId().toString(), l);
									y.save(f);
									
									sender.sendMessage(Island.prefix + "§r§d Vous avez accepté l'invitation vers l'île " + owner.getName());
									new CreateIslandCmd().aide1(sender);
									
									break;
									
								}
								
							}
							
						}else {
							// remove de l'invitation
							
							for (Iterator<String> i = l.iterator(); i.hasNext();) {
								
								if(i.next().equals(u)) {
									
									i.remove();
									y.set("invite." + sender.getUniqueId().toString(), l);
									y.save(f);
									sender.sendMessage(Island.prefix + "§r§c Invitation suprimée !");
									break;
									
								}
								
							}
							
						}
						
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}					
									
					return true;
					
				}
				
			}
				
		}else {
			// affichage aide
			new CreateIslandCmd().aide(sender);
			return true;
		}
		*/
	
/*
	public void aide2(Player p, List<String> l) {
		// TODO Auto-generated method stub
		
		p.sendMessage(Island.prefix + "§r§d Liste des invitations :");
		
		for (String string : l) {
			
			OfflinePlayer t = MainOzone.getInstance().getServer().getOfflinePlayer(UUID.fromString(string));
			
			TextComponent accept = new TextComponent("§l§2[√]");
			accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/is join " + string + " -a"));
			accept.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( "§3§l Accepter l'invitation" ).create() ) );
			
			TextComponent refuse = new TextComponent("§c§l[✖]");
			refuse.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/is join " + string + " -r"));
			refuse.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( "§3§lRefuser l'invitation" ).create() ) );
		
			TextComponent msg = new TextComponent("Ile de " + t.getName() + " : ");
			msg.setBold(true);
			msg.setColor(ChatColor.AQUA);
			msg.addExtra(accept);
			msg.addExtra(refuse);
			
			p.spigot().sendMessage(msg);
		
		}
		
	}
	
	public void aide1(Player p) {
		
		p.sendMessage(Island.prefix + "§r§c L'invitation n'est pas valide.");
		
	}

	@Override
	public void aide(Player p) {
		// TODO Auto-generated method stub
		p.sendMessage(Island.prefix + "§r§c Vous faites déjà parti d'un");
	}
*/
