package fr.pumpmyskybukkit.commands;

import java.io.IOException;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import fr.pumpmyskybukkit.BukkitIslandManager;
import fr.pumpmyskycore.IslandManager.IslandManagerConstant;
import fr.pumpmyskycore.exceptions.PlayerAlreadyHaveIslandException;
import fr.pumpmyskycore.exceptions.PlayerDoesNotHaveIslandException;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class CreateIslandCmd implements ISubCommand {

	private BukkitIslandManager manager;

	public CreateIslandCmd(BukkitIslandManager islandManager) {
		
		this.manager = islandManager;
		
	}

	@Override
	public boolean onSubCommand(Player sender, Command cmd, List<String> args) {
		
		try {
			
			this.manager.createIsland(sender);
			
			sender.sendMessage(IslandManagerConstant.ISLAND_CHAT_PREFIX +"§d Ile créée avec succès !");
			this.teleportIslandChatMessage(sender);
			
			
		} catch (PlayerAlreadyHaveIslandException e) {
			
			sender.sendMessage(IslandManagerConstant.ISLAND_CHAT_PREFIX + "§r§c Vous faites parti / possédez déjà une ile (" + e.getIsland().getName() + " )");
			this.teleportIslandChatMessage(sender);
			
		} catch (PlayerDoesNotHaveIslandException e) {
			
			sender.sendMessage("§cERROR !!!! Envoyez le message suivant au staff : " + e.getClass().getName() + " || " + e.getPlayerUUID());
			
		} catch (IOException e) {
			
			e.printStackTrace();
			sender.sendMessage("§cERROR !!!! Envoyez le message suivant au staff : " + e.getClass().getName() + " || " + e.getMessage());
		}
		
		return true;
		
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
		
		p.sendMessage(IslandManagerConstant.ISLAND_CHAT_PREFIX +"§d Ile créée avec succès !");
		p.spigot().sendMessage(msg);
		
	}
	
}
