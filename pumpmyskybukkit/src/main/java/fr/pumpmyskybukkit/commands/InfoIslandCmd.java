package fr.pumpmyskybukkit.commands;

import java.util.List;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import fr.pumpmyplotcore.Plot;
import fr.pumpmyplotcore.PlotManager.PlotManagerConstant;
import fr.pumpmyplotcore.exceptions.PlayerDoesNotHavePlotException;
import fr.pumpmyskybukkit.BukkitIslandManager;
import net.md_5.bungee.api.chat.TextComponent;

public class InfoIslandCmd implements ISubCommand {

	@Override
	public boolean onSubCommand(IslandCommandExecutor exec, Player sender, Command cmd, List<String> args) {
		
		BukkitIslandManager manager = exec.getIslandManager();
		
		try {
			
			Plot island = manager.playerGetPlot(sender);
			
			sender.spigot().sendMessage(new TextComponent(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§f " + island.getName() + "(§r§7§o" + manager.getMain().getServer().getOfflinePlayer(UUID.fromString(island.getOwner())).getName() + "§r§f)"));
			
			sender.spigot().sendMessage(new TextComponent("  §a█§r§f Home( x:" + island.getHomeX() + " | y:" + island.getHomeY() + " | z:" + island.getHomeZ() + " )"));
			
			sender.spigot().sendMessage(new TextComponent("  §a█§r§f Membres :"));
			
			for (String string : island.getMembersList()) {
				
				sender.spigot().sendMessage(new TextComponent("   §a»§r§f" + manager.getMain().getServer().getOfflinePlayer(UUID.fromString(string)).getName()));
				
			}
			
		} catch (PlayerDoesNotHavePlotException e) {
			
			sender.spigot().sendMessage(new TextComponent(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Vous ne possedez ou ne faite parti d'aucune ile !"));
			new CreateIslandCmd().createIslandChatMessage(sender);
			
		}
		
		return true;
		
	}

}
