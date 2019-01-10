package fr.pumpmyskybukkit;

import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;

import fr.pumpmyskybukkit.commands.CreateIslandCmd;
import fr.pumpmyskybukkit.commands.GoToIslandCmd;
import fr.pumpmyskybukkit.commands.InfoIslandCmd;
import fr.pumpmyskybukkit.commands.InviteIslandCmd;
import fr.pumpmyskybukkit.commands.IslandCommandExecutor;
import fr.pumpmyskybukkit.commands.JoinIslandCmd;
import fr.pumpmyskybukkit.commands.KickIslandCmd;
import fr.pumpmyskybukkit.commands.LeaveIslandCmd;
import fr.pumpmyskybukkit.commands.SetHomeIslandCmd;
import fr.pumpmyskybukkit.commands.UninviteIslandCmd;

public class MainSky extends JavaPlugin{

	private BukkitIslandManager islandManager;
	
	@Override
	public void onEnable() {
		super.onEnable();
		
		saveDefaultConfig();
		
		try {
			this.islandManager = new BukkitIslandManager(this);
		} catch (IOException | InvalidConfigurationException e) {
			
			e.printStackTrace();
			this.getServer().shutdown();
			return;
			
		}
		
		this.getServer().getPluginManager().registerEvents(new SkyListeners(this.islandManager), this);

		
		IslandCommandExecutor islandCommandExecutor = new IslandCommandExecutor(this.islandManager);
		
		islandCommandExecutor.addSubCommand("create", new CreateIslandCmd());		
		islandCommandExecutor.addSubCommand("goto", new GoToIslandCmd());
		islandCommandExecutor.addSubCommand("info", new InfoIslandCmd());
		islandCommandExecutor.addSubCommand("leave", new LeaveIslandCmd());
		
		islandCommandExecutor.addSubCommand("invite", new InviteIslandCmd());
		islandCommandExecutor.addSubCommand("uninvite", new UninviteIslandCmd());
		
		islandCommandExecutor.addSubCommand("join", new JoinIslandCmd());
		islandCommandExecutor.addSubCommand("kick", new KickIslandCmd());
		
		islandCommandExecutor.addSubCommand("sethome", new SetHomeIslandCmd());
		
		
		this.getCommand("island").setExecutor(islandCommandExecutor);
		
		
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
		
		
		
	}
	
	public BukkitIslandManager getIslandManager() {
		return this.islandManager;
	}
	
}
