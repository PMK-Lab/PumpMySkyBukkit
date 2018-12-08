package fr.pumpmyskybukkit;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import fr.pumpmyskycore.IslandManager;
import fr.pumpmyskycore.IslandManager.IslandConstant;

public class MainSky extends JavaPlugin{

	@Override
	public void onEnable() {
		super.onEnable();
		
		File file = new File(getDataFolder(), IslandConstant.ISLAND_FOLDER_NAME);
		IslandManager.initIslandFolder(file);
		
		
		
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
		
		
		
	}
	
}
