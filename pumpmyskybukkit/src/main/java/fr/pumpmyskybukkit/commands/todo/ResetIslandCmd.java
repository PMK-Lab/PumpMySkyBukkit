package fr.pumpmyskybukkit.commands.todo;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import fr.pumpmyskybukkit.commands.ISubCommand;
import fr.pumpmyskybukkit.commands.IslandCommandExecutor;

public class ResetIslandCmd implements ISubCommand {

	private static ArrayList<String> confirmUUIDList = new ArrayList<>();
	
	@Override
	public boolean onSubCommand(IslandCommandExecutor exec, Player sender, Command cmd, List<String> args) {
		return false;
		/*
		IslandManager is = MainOzone.getIslandManager();
		
		if(is.playerHasIsland(sender)) {
			
			Island island = MainOzone.getIslandManager().getIsland(sender);
			
			if(sender.getUniqueId().toString().equals(island.getOwnerUUID())) {
				
				// demande de confirmation reset island
				aide2(sender);
				return true;
				
			}else {
				
				// doit etre propriétaire
				aide3(sender);
				return true;
				
			}
			
		}else {
			
			aide(sender);		
			return true;
		}
		*/
	}
/*	
	public void aide3(Player p) {
		// TODO Auto-generated method stub
		p.sendMessage(Island.prefix + "§r§c Vous devez être le créateur de l'lie pour faire cela !");
	}

	private void aide2(Player sender) {
		// TODO Auto-generated method stub
		
		TextComponent valide = new TextComponent("§l§2[√]");
		valide.setBold(true);
		valide.setColor(ChatColor.DARK_AQUA);
		valide.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/is c-reset"));
		
		valide.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( "§3§lReset l'ile !" ).create() ) );
		
		TextComponent msg = new TextComponent(Island.prefix + "§r§cConfirmez vous le reset ? ");
		msg.addExtra(valide);
		
		sender.spigot().sendMessage(msg);
		
		sender.sendMessage("§cATTENTION, vous perdrez votre progression au niveau des quetes, ainsi que vos constructions !");
		
	}

	@Override
	public void aide(Player p) {
		// TODO Auto-generated method stub
		p.sendMessage(Island.prefix + "§r§c Vous devez avoir une île pour pouvoir reset la votre !");
		
	}

	public static ArrayList<String> getConfirmUUIDList() {
		return confirmUUIDList;
	}

	public static void setConfirmUUIDList(ArrayList<String> confirmUUIDList) {
		ResetIslandCmd.confirmUUIDList = confirmUUIDList;
	}
*/
}
