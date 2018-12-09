package fr.pumpmyskybukkit;

import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

public class MainSky extends JavaPlugin{

	private BukkitIslandManager islandManager;
	
	@Override
	public void onEnable() {
		super.onEnable();
		
		saveDefaultConfig();
		
		this.islandManager = new BukkitIslandManager(this);
		
		this.getServer().getPluginManager().registerEvents(new SkyListeners(this.islandManager), this);
		
		//this.getCommand("island").setExecutor(executor);
		
		
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
		
		
		
	}
	
	public BukkitIslandManager getIslandManager() {
		return this.islandManager;
	}
	
}
