package fr.pumpmyskybukkit;

import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;

import fr.pumpmyskybukkit.commands.CreateIslandCmd;
import fr.pumpmyskybukkit.commands.GoToIslandCmd;
import fr.pumpmyskybukkit.commands.IslandCommandExecutor;

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
		islandCommandExecutor.addSubCommand("info", new InfoIslandCmd());
		islandCommandExecutor.addSubCommand("goto", new GoToIslandCmd());
		
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
