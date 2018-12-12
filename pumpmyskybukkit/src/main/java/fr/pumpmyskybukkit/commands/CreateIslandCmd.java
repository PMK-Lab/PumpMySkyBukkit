package fr.pumpmyskybukkit.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import fr.pumpmyskybukkit.BukkitIslandManager;
import fr.pumpmyskycore.exceptions.PlayerAlreadyHaveIslandException;
import fr.pumpmyskycore.exceptions.PlayerDoesNotHaveIslandException;

public class CreateIslandCmd implements ISubCommand {

	private BukkitIslandManager manager;

	public CreateIslandCmd(BukkitIslandManager islandManager) {
		
		this.manager = islandManager;
		
	}

	@Override
	public boolean onSubCommand(Player sender, Command cmd, List<String> args) {
		
		try {
			
			this.manager.createIsland(sender);
			
			sender.sendMessage(IslandConstant.ISLAND_CHAT_PREFIX +"§d Ile créée avec succès !");
			this.teleportIslandChatMessage(sender);
			
			
		} catch (PlayerAlreadyHaveIslandException e) {
			
			sender.sendMessage("Vous possedez déjà une ile !");
			
		} catch (PlayerDoesNotHaveIslandException e) {
			
			sender.sendMessage("§cERROR !!!! Envoyez le message suivant au staff : " + e.getClass().getName() + " || " + e.getPlayerUUID());
			
		}
		
		return true;
		
		/*
		IslandManager is = MainOzone.getIslandManager();
		
		if(is.playerHasIsland(sender)) {
			
			aide(sender);
			aide1(sender);
			return true;
			
		}else {
			// cr�ation de l'ile
			//System.out.println("create island");
			
			try {
				is.createIsland(sender);
				sender.sendMessage(Island.prefix +"�d Ile cr��e avec succ�s !");
				aide1(sender);
				
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
		}
		*/
	}
/*
	@Override
	public void aide(Player p) {
		// TODO Auto-generated method stub
		p.sendMessage(Island.prefix + "�r�c Vous faites parti / poss�dez d�j� une ile.");
		
	}
	*/
	public void teleportIslandChatMessage(Player p) {
		
		TextComponent ici = new TextComponent("ICI");
		ici.setBold(true);
		ici.setColor(ChatColor.DARK_AQUA);
		ici.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/is goto"));
		
		ici.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( "§3§lTéléportation vers votre île !" ).create() ) );
		
		TextComponent msg = new TextComponent("Cliquez ");
		msg.setColor(ChatColor.AQUA);
		msg.addExtra(ici);
		
		TextComponent msg1 = new TextComponent(" pour vous y téléporter !");
		msg1.setColor(ChatColor.AQUA);
		
		msg.addExtra(msg1);		
		
		p.sendMessage(IslandConstant.ISLAND_CHAT_PREFIX +"§d Ile créée avec succès !");
		p.spigot().sendMessage(msg);
		
	}
	
}
