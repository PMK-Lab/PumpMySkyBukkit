package fr.pumpmyskybukkit;

import java.io.IOException;
import java.util.UUID;

import org.bukkit.entity.Player;

import fr.pumpmyskycore.IslandManager;

public class BukkitIslandManager extends IslandManager<Player>{

	protected MainSky main;
	
	public BukkitIslandManager(MainSky main) throws IOException {
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
	
	
}
