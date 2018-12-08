package fr.pumpmyskybukkit;

import org.bukkit.plugin.java.JavaPlugin;

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
