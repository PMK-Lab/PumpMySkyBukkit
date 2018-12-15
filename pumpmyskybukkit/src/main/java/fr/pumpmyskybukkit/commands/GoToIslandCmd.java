package fr.pumpmyskybukkit.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import fr.pumpmyskybukkit.BukkitIslandManager;
import fr.pumpmyskycore.Island;
import fr.pumpmyskycore.IslandManager.IslandManagerConstant;
import fr.pumpmyskycore.exceptions.PlayerDoesNotHaveIslandException;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class GoToIslandCmd implements ISubCommand{
	
	@Override
	public boolean onSubCommand(IslandCommandExecutor exec, Player sender, Command cmd, List<String> args) {
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

	public boolean onSubCommand(IslandCommandExecutor exec,Player p, Command cmd) {
		// TODO Auto-generated method stub
		return onSubCommand(exec,p, cmd, null);
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
		
		p.spigot().sendMessage(msg);
		
	}

}
