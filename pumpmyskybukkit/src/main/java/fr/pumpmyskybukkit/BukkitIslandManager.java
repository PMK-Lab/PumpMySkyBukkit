package fr.pumpmyskybukkit;

import java.io.IOException;
import java.util.UUID;

import org.bukkit.entity.Player;

import fr.pumpmyskycore.Island;
import fr.pumpmyskycore.IslandManager;

public class BukkitIslandManager extends IslandManager<Player>{

	protected MainSky main;
	
	public BukkitIslandManager(MainSky main) throws IOException {
		super(main.getDataFolder().toPath());
		
		this.main = main;
		
	}

	@Override
	public boolean playerHasIsland(Player player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteIsland(Island i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UUID getMinecraftUUID(Player player) {
		return player.getUniqueId();
	}
	
	
}
