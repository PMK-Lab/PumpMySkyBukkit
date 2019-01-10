package fr.pumpmyskybukkit.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import fr.pumpmyplotcore.Plot;
import fr.pumpmyplotcore.PlotHomeLocation;
import fr.pumpmyplotcore.PlotManager.PlotManagerConstant;
import fr.pumpmyplotcore.exceptions.PlayerDoesNotHavePlotException;
import fr.pumpmyskybukkit.BukkitIslandManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class GoToIslandCmd implements ISubCommand{
	
	@Override
	public boolean onSubCommand(IslandCommandExecutor exec, Player sender, Command cmd, List<String> args) {
		
		BukkitIslandManager manager = exec.getIslandManager();
		
		try {
			
			PlotHomeLocation loc = manager.playerGetHomePlot(sender);
			
			sender.teleport(new Location(Bukkit.getWorld("Void"), loc.getX(), loc.getY(), loc.getZ()));
			
		} catch (PlayerDoesNotHavePlotException e) {
			
			sender.sendMessage(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Sans île, vous ne pouvez pas vous téléporter à celle-ci !");
			new CreateIslandCmd().createIslandChatMessage(sender);
			
		}
		
		return true;		
		
	}

	public boolean onSubCommand(IslandCommandExecutor exec,Player p, Command cmd) {
		// TODO Auto-generated method stub
		return onSubCommand(exec,p, cmd, null);
	}
	
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
