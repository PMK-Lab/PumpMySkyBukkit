package fr.pumpmyskybukkit.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class GoToIslandCmd implements ISubCommand{

	@Override
	public boolean onSubCommand(Player sender, Command cmd, List<String> args) {
		return false;
		/*
		IslandManager is = MainOzone.getIslandManager();
		
		if(is.playerHasIsland(sender)) {
			// t�l�portation
			
			Island island = MainOzone.getIslandManager().getIsland(sender);
			
			sender.teleport(new Location(Bukkit.getWorld("Void"), island.getSpawnX(), island.getSpawnY(), island.getSpawnZ()));
			return true;
			
		}else {
			// affichage aide
			aide(sender);
			aide1(sender);
			return false;
			
		}
		*/
	}

	public boolean onSubCommand(Player p, Command cmd) {
		// TODO Auto-generated method stub
		return onSubCommand(p, cmd, null);
	}
/*
	@Override
	public void aide(Player p) {
		// TODO Auto-generated method stub
		
		p.sendMessage(Island.prefix + "�r�c Sans �le, vous ne pouvez pas vous t�l�porter � celle-ci !");
		
	}
	
	public void aide1(Player p) {
		
		TextComponent ici = new TextComponent("ICI");
		ici.setBold(true);
		ici.setColor(ChatColor.DARK_AQUA);
		ici.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/is create"));
		
		ici.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( "�3�lPour cr�er votre �le !" ).create() ) );
		
		TextComponent msg = new TextComponent("Pour cr�er votre �le, cliquez ");
		msg.setColor(ChatColor.AQUA);
		msg.addExtra(ici);
		
		p.spigot().sendMessage(msg); // clique = aide create island
		
	}
	
*/	

}
