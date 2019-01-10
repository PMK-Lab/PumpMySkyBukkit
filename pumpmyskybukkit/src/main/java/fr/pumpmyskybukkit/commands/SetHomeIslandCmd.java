package fr.pumpmyskybukkit.commands;

import java.io.IOException;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import fr.pumpmyplotcore.PlotHomeLocation;
import fr.pumpmyplotcore.PlotManager.PlotManagerConstant;
import fr.pumpmyplotcore.exceptions.InvalidePlotHomeLocationException;
import fr.pumpmyplotcore.exceptions.PlayerDoesNotHavePlotException;
import fr.pumpmyplotcore.exceptions.RestrictActionToPlotOwnerException;
import fr.pumpmyskybukkit.BukkitIslandManager;

public class SetHomeIslandCmd implements ISubCommand{
	
	@Override
	public boolean onSubCommand(IslandCommandExecutor exec, Player sender, Command cmd, List<String> args) {
		
		BukkitIslandManager manager = exec.getIslandManager();
		
		Location location = sender.getLocation();
		
		try {
			
			manager.playerSetHomePlot(sender, new PlotHomeLocation(location.getX(), location.getY(), location.getZ()));
			
		} catch (PlayerDoesNotHavePlotException e) {
			
			sender.sendMessage(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Vous ne possedez ou ne faite parti d'aucune ile !");
			new CreateIslandCmd().createIslandChatMessage(sender);
			
		} catch (RestrictActionToPlotOwnerException e) {
			
			sender.sendMessage(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Vous devez être le créateur de l'lie pour faire cela !");
			
		} catch (IOException e) {
			
			e.printStackTrace();
			sender.sendMessage("§cERROR !!!! Envoyez le message suivant au staff : " + e.getClass().getName() + " || " + e.getMessage());
			
		} catch (InvalidePlotHomeLocationException e) {
			
			sender.sendMessage(PlotManagerConstant.PLOT_CHAT_PREFIX + "§r§c Votre position actuelle ne peut pas etre utilisé comme point de spawn de votre ile !");
			
		}	
		
		return true;
		
	}

}
