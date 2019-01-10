package fr.pumpmyskybukkit.commands;

import java.io.IOException;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import fr.pumpmyplotcore.PlotManager.PlotManagerConstant;
import fr.pumpmyplotcore.exceptions.PlayerAlreadyHavePlotException;
import fr.pumpmyplotcore.exceptions.PlayerDoesNotHavePlotException;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class CreateIslandCmd implements ISubCommand {

	@Override
	public boolean onSubCommand(IslandCommandExecutor executor,Player sender, Command cmd, List<String> args) {
		
		try {
			
			executor.getIslandManager().playerCreatePlot(sender);
			
			sender.sendMessage(PlotManagerConstant.PLOT_CHAT_PREFIX +"§d Ile créée avec succès !");
			new GoToIslandCmd().teleportIslandChatMessage(sender);
			
			
		} catch (PlayerAlreadyHavePlotException e) {
			
			sender.sendMessage(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Vous faites parti / possédez déjà une ile (" + e.getIsland().getName() + " )");
			new GoToIslandCmd().teleportIslandChatMessage(sender);
			
		} catch (PlayerDoesNotHavePlotException e) {
			
			sender.sendMessage("§cERROR !!!! Envoyez le message suivant au staff : " + e.getClass().getName() + " || " + e.getPlayerUUID());
			
		} catch (IOException e) {
			
			e.printStackTrace();
			sender.sendMessage("§cERROR !!!! Envoyez le message suivant au staff : " + e.getClass().getName() + " || " + e.getMessage());
		}
		
		return true;
		
	}
	
	public void createIslandChatMessage(Player p) {
		
		TextComponent ici = new TextComponent("ICI");
		ici.setBold(true);
		ici.setColor(ChatColor.DARK_AQUA);
		ici.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/is create"));
		
		ici.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( "§3§lPour créer votre île !" ).create() ) );
		
		TextComponent msg = new TextComponent("Pour créer votre île, cliquez ");
		msg.setColor(ChatColor.AQUA);
		msg.addExtra(ici);
		
		p.spigot().sendMessage(msg);
		
	}
	
}
