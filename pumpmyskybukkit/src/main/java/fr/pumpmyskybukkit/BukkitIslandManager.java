package fr.pumpmyskybukkit;

import org.bukkit.entity.Player;

import fr.pumpmyskycore.Island;
import fr.pumpmyskycore.IslandManager;

public class BukkitIslandManager extends IslandManager<Player>{

	protected MainSky main;
	
	public BukkitIslandManager(MainSky main) {
		super(main.getDataFolder().toPath());
		
		this.main = main;
		
	}

	@Override
	public Island createIsland(Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Island playerGetIsland(Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void playerAddIsland(Island island, Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playerRemoveIsland(Island island, Player player) {
		// TODO Auto-generated method stub
		
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
	
	
}
