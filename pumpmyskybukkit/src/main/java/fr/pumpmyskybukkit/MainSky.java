package fr.pumpmyskybukkit;

import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

import fr.pumpmyskybukkit.commands.IslandCommandExecutor;

public class MainSky extends JavaPlugin{

	private BukkitIslandManager islandManager;
	
	@Override
	public void onEnable() {
		super.onEnable();
		
		saveDefaultConfig();
		
		try {
			this.islandManager = new BukkitIslandManager(this);
		} catch (IOException e) {
			
			e.printStackTrace();
			this.getServer().shutdown();
		}
		
		this.getServer().getPluginManager().registerEvents(new SkyListeners(this.islandManager), this);
		
		IslandCommandExecutor islandCommandExecutor = new IslandCommandExecutor();
		//islandCommandExecutor.addSubCommand(sub, i);
		
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
