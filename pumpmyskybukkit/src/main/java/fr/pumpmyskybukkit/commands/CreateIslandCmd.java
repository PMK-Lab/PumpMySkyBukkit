package fr.pumpmyskybukkit.commands;

import java.io.IOException;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.data.DataException;

import fr.pumpmyplotcore.Plot;
import fr.pumpmyplotcore.PlotManager.PlotManagerConstant;
import fr.pumpmyplotcore.exceptions.PlayerAlreadyHavePlotException;
import fr.pumpmyplotcore.exceptions.PlayerDoesNotHavePlotException;
import fr.pumpmyskybukkit.BukkitIslandManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

@SuppressWarnings("deprecation")
public class CreateIslandCmd implements ISubCommand {

	@Override
	public boolean onSubCommand(IslandCommandExecutor executor,Player sender, Command cmd, List<String> args) {
		
		try {
			
			BukkitIslandManager manager = executor.getIslandManager();
			
			Plot plot = manager.playerCreatePlot(sender);
			manager.pasteIslandSchematic(plot.toLocation());			
			
			sender.spigot().sendMessage(new ComponentBuilder(PlotManagerConstant.PLOT_CHAT_PREFIX +"§d Ile créée avec succès !").create());
			new GoToIslandCmd().teleportIslandChatMessage(sender);
			
			
		} catch (PlayerAlreadyHavePlotException e) {
			
			sender.spigot().sendMessage(new ComponentBuilder(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Vous faites parti / possédez déjà une ile (" + e.getIsland().getName() + " )").create());
			new GoToIslandCmd().teleportIslandChatMessage(sender);
			
		} catch (PlayerDoesNotHavePlotException e) {
			
			sender.spigot().sendMessage(new ComponentBuilder("§cERROR !!!! Envoyez le message suivant au staff : " + e.getClass().getName() + " || " + e.getPlayerUUID()).create());
			
		} catch (IOException e) {
			
			e.printStackTrace();
			sender.spigot().sendMessage(new ComponentBuilder("§cERROR !!!! Envoyez le message suivant au staff : " + e.getClass().getName() + " || " + e.getMessage()).create());
			
		} catch (DataException e) {
			
			e.printStackTrace();
			sender.spigot().sendMessage(new ComponentBuilder("§cERROR !!!! Envoyez le message suivant au staff : " + e.getClass().getName() + " || " + e.getMessage()).create());
			
		} catch (MaxChangedBlocksException e) {
			
			e.printStackTrace();
			sender.spigot().sendMessage(new ComponentBuilder("§cERROR !!!! Envoyez le message suivant au staff : " + e.getClass().getName() + " || " + e.getMessage()).create());
			
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
