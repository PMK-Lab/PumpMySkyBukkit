package fr.pumpmyskybukkit;

import org.bukkit.entity.Player;

import fr.pumpmyskycore.Island;
import fr.pumpmyskycore.IslandManager;
import fr.pumpmyskycore.exceptions.PlayerAlreadyHaveIslandException;
import fr.pumpmyskycore.exceptions.PlayerDoesNotHaveIslandException;

public class BukkitIslandManager extends IslandManager<Player>{

	protected MainSky main;
	
	public BukkitIslandManager(MainSky main) {
		super(main.getDataFolder().toPath());
		
		this.main = main;
		
	}

	@Override
	public Island createIsland(Player player) throws PlayerAlreadyHaveIslandException, PlayerDoesNotHaveIslandException {
		
		if(this.playerHasIsland(player)) {
			
			throw new PlayerAlreadyHaveIslandException(player.getUniqueId(), this.playerGetIsland(player));
			
		}
		
		return Island.create(player.getUniqueId());
		
	}

	@Override
	public Island playerGetIsland(Player player) throws PlayerDoesNotHaveIslandException {
		
		if(!this.playerHasIsland(player)) {
			
			throw new PlayerDoesNotHaveIslandException(player.getUniqueId());
			
		}
		
		return Island.get(player.getUniqueId());
		
	}

	@Override
	public void playerAddIsland(Island island, Player player) throws PlayerDoesNotHaveIslandException {
		
		if(!this.playerHasIsland(player)) {
			
			throw new PlayerDoesNotHaveIslandException(player.getUniqueId());
			
		}
		
		island.add(player.getUniqueId());
		
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
