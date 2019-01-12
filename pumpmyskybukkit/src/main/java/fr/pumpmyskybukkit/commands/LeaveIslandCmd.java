package fr.pumpmyskybukkit.commands;

import java.io.IOException;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import fr.pumpmyplotcore.PlotManager.PlotManagerConstant;
import fr.pumpmyplotcore.exceptions.PlayerDoesNotHavePlotException;
import fr.pumpmyplotcore.exceptions.PlotIsNotEmptyException;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class LeaveIslandCmd implements ISubCommand {

	@Override
	public boolean onSubCommand(IslandCommandExecutor exec, Player sender, Command cmd, List<String> args) {
		
		try {
			
			boolean purge = exec.getIslandManager().playerLeavePlot(sender);
			
			if(purge) {
				
				sender.spigot().sendMessage(new ComponentBuilder(PlotManagerConstant.PLOT_CHAT_PREFIX +"§d Ile détruite !").create());
				
			}else {
				
				sender.spigot().sendMessage(new ComponentBuilder(PlotManagerConstant.PLOT_CHAT_PREFIX +"§d Vous avez quitté votre ile !").create());	
				
			}
			
			new CreateIslandCmd().createIslandChatMessage(sender);
			sender.performCommand("/spawn");
			
			
		} catch (PlayerDoesNotHavePlotException e) {
			
			sender.spigot().sendMessage(new ComponentBuilder(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Vous ne possedez ou ne faite parti d'aucune ile !").create());
			new CreateIslandCmd().createIslandChatMessage(sender);
			
		} catch (PlotIsNotEmptyException e) {
			
			sender.spigot().sendMessage(new ComponentBuilder(PlotManagerConstant.PLOT_CHAT_PREFIX +"§c Des membres sont encore présent dans votre île !").create());
			
		} catch (IOException e) {
			
			e.printStackTrace();
			sender.spigot().sendMessage(new ComponentBuilder("§cERROR !!!! Envoyez le message suivant au staff : " + e.getClass().getName() + " || " + e.getMessage()).create());
			
		}
		
		return true;
		
	}
	
	public void leaveIslandChatMessage(Player player) {
		
		TextComponent ici = new TextComponent("ICI");
		ici.setBold(true);
		ici.setColor(ChatColor.DARK_AQUA);
		ici.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/is leave"));
		
		ici.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( "§3§lCommande \"is leave\"" ).create() ) );
		
		TextComponent msg = new TextComponent("Cliquez ");
		msg.setColor(ChatColor.AQUA);
		msg.addExtra(ici);
		
		TextComponent msg1 = new TextComponent(" pour quitter votre ile !");
		msg1.setColor(ChatColor.AQUA);
		
		msg.addExtra(msg1);		
		
		player.spigot().sendMessage(msg);
		
	}
	
}
