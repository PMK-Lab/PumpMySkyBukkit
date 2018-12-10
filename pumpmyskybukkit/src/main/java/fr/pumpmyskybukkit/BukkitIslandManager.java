package fr.pumpmyskybukkit;

import java.io.IOException;
import java.util.UUID;

import org.bukkit.entity.Player;

import fr.pumpmyskycore.Island;
import fr.pumpmyskycore.IslandManager;
import fr.pumpmyskycore.exceptions.PlayerDoesNotHaveIslandException;
import fr.pumpmyskycore.exceptions.PlayerNotInThisIsland;

public class BukkitIslandManager extends IslandManager<Player>{

	protected MainSky main;
	
	public BukkitIslandManager(MainSky main) throws IOException {
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
	public void playerRemoveIsland(Island island, Player player) throws PlayerDoesNotHaveIslandException, PlayerNotInThisIsland {
		
		if(!this.playerHasIsland(player)) {
			
			throw new PlayerDoesNotHaveIslandException(player.getUniqueId());
			
		}else if(!this.playerGetIsland(player).equals(island)) {
			
			throw new PlayerNotInThisIsland(player.getUniqueId(), this.playerGetIsland(player) , island);
			
		}
		
		island.remove(player.getUniqueId());
		
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
