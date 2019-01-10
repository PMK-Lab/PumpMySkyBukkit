package fr.pumpmyskybukkit;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.InvalidConfigurationException;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.schematic.SchematicFormat;

import fr.pumpmyplotcore.PlotLocation;
import fr.pumpmyplotcore.PlotManager;

@SuppressWarnings("deprecation")
public class BukkitIslandManager extends PlotManager<OfflinePlayer>{

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
