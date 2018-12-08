package fr.pumpmyskybukkit;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

public class MainSky extends JavaPlugin{

	@Override
	public void onEnable() {
		super.onEnable();
		
		File file = new File(getDataFolder(), "islands");
		initIslandFolder(file);
		
	}
	
	private void initIslandFolder(File f) {
		
		if(!f.exists()) {
			f.mkdir();
		}
		
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
		
		
		
	}
	
}
