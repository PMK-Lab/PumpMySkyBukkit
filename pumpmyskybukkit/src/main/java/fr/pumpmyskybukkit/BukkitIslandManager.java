package fr.pumpmyskybukkit;

import java.io.IOException;
import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

import fr.pumpmyskycore.IslandManager;

public class BukkitIslandManager extends IslandManager<OfflinePlayer>{

	protected MainSky main;
	
	public BukkitIslandManager(MainSky main) throws IOException, InvalidConfigurationException {
		super(main.getDataFolder().toPath());
		
		this.main = main;
		
	}
	
	public MainSky getMain() {
		return this.main;
	}

	@Override
	public UUID getMinecraftUUID(OfflinePlayer player) {
		return player.getUniqueId();
	}
	
	public OfflinePlayer getOfflinePlayerByName(String name) {
		
		for (OfflinePlayer offlinePlayer : this.main.getServer().getOfflinePlayers()) {
			
			if(offlinePlayer.getName().equals(name)) {
				return offlinePlayer;
			}
			
		}
		
		return null;
		
	}
	
	
}
