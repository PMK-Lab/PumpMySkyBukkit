package fr.pumpmyskybukkit;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

import fr.pumpmyskycore.Island;
import fr.pumpmyskycore.IslandLocation;
import fr.pumpmyskycore.IslandManager;

public class BukkitIslandManager extends IslandManager<Player>{

	protected MainSky main;
	
	public BukkitIslandManager(MainSky main) throws IOException, InvalidConfigurationException {
		super(main.getDataFolder().toPath());
		
		this.main = main;
		
	}
	
	public MainSky getMain() {
		return this.main;
	}

	@Override
	public UUID getMinecraftUUID(Player player) {
		return player.getUniqueId();
	}

	@Override
	public Island getIsland(File f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Island getIsland(IslandLocation l) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
